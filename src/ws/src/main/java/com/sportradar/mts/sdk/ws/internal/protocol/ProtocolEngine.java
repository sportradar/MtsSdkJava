package com.sportradar.mts.sdk.ws.internal.protocol;

import com.sportradar.mts.sdk.api.SdkTicket;
import com.sportradar.mts.sdk.api.interfaces.SdkConfiguration;
import com.sportradar.mts.sdk.api.utils.JsonUtils;
import com.sportradar.mts.sdk.api.ws.Request;
import com.sportradar.mts.sdk.api.ws.Response;
import com.sportradar.mts.sdk.ws.exceptions.*;
import com.sportradar.mts.sdk.ws.internal.connection.ConnectionProvider;
import com.sportradar.mts.sdk.ws.internal.connection.msg.*;
import com.sportradar.mts.sdk.ws.internal.connection.msg.base.WsInputMessage;
import com.sportradar.mts.sdk.ws.internal.connection.msg.base.WsOutputMessage;
import com.sportradar.mts.sdk.ws.internal.utils.ExcSuppress;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

import static com.sportradar.mts.sdk.ws.internal.utils.Delayer.delay;
import static com.sportradar.mts.sdk.ws.internal.utils.ExcSuppress.threadJoin;
import static java.util.concurrent.TimeUnit.MILLISECONDS;

public class ProtocolEngine implements AutoCloseable {

    private static final int MAX_CHUNK_SIZE = 32_000;
    private static final int MAX_MSG_SIZE = 4 * MAX_CHUNK_SIZE;

    private final SdkConfiguration sdkConfiguration;
    private final ConnectionProvider connectionProvider;
    private final BlockingQueue<WsInputMessage> sendQueue;
    private final BlockingQueue<WsOutputMessage> receiveQueue;
    private final ConcurrentMap<String, AwaiterInterface> correlationIdAwaiter;
    private final AtomicInteger approxRequestCount;
    private final Consumer<Exception> unhandledExceptionHandler;

    private volatile boolean connected = false;

    private Thread[] receiverThreads;

    public ProtocolEngine(
            final SdkConfiguration sdkConfiguration,
            final Consumer<Exception> unhandledExceptionHandler) {
        this.sdkConfiguration = sdkConfiguration;
        this.sendQueue = new LinkedBlockingQueue<>();
        this.receiveQueue = new LinkedBlockingQueue<>();
        this.correlationIdAwaiter = new ConcurrentHashMap<>();
        this.approxRequestCount = new AtomicInteger(0);
        this.connectionProvider = new ConnectionProvider(sdkConfiguration, sendQueue, receiveQueue);
        this.unhandledExceptionHandler = unhandledExceptionHandler;
    }

    public void connect() {
        this.receiverThreads = new Thread[sdkConfiguration.getProtocolNumberOfDispatchers()];
        this.connected = true;
        for (int i = 0; i < this.receiverThreads.length; i++) {
            final Thread thread = new Thread(this::receiveLoop);
            thread.setDaemon(true);
            this.receiverThreads[i] = thread;
            thread.start();
        }
        this.connectionProvider.connect();
    }

    @Override
    public void close() {
        this.connected = false;
        ExcSuppress.close(this.connectionProvider);
        for (int i = 0; i < this.receiverThreads.length; i++) {
            final Thread thread = this.receiverThreads[i];
            this.receiverThreads[i] = null;
            threadJoin(thread);
        }
        for (final String correlationId : new ArrayList<>(correlationIdAwaiter.keySet())) {
            releaseAwaiter(correlationId);
        }
    }

    public <T extends SdkTicket, R extends SdkTicket> CompletableFuture<R> execute(
            final String operation,
            final T content,
            final Class<R> responseClass,
            final Runnable publishSuccessListener) { // todo dmuren sam tale success se mora prestavit ven
        String correlationId = null;
        try {
            checkConnected();

            final Awaiter<T, R> awaiter = createAwaiter(responseClass, publishSuccessListener, content.getCorrelationId());
            correlationId = awaiter.getCorrelationId();

            final Request request = new Request();
            request.setContent(content.getJsonValue());
            request.setOperation(operation);
            request.setOperatorId(sdkConfiguration.getOperatorId());
            request.setCorrelationId(correlationId);

            final List<ByteBuffer> frames = createFrames(request);
            final SendWsInputMessage msg = new SendWsInputMessage(correlationId, frames);
            awaiter.setSendWsInputMessage(msg);
            enqueueSendMsg(awaiter, 0);

            return awaiter.getFuture();
        } catch (final Exception exc) {
            releaseAwaiter(correlationId);
            final SdkException sdkException = exc instanceof SdkException
                    ? (SdkException) exc
                    : new ProtocolSendFailedException(exc);
            return CompletableFuture.supplyAsync(() -> {
                throw sdkException;
            });
        }
    }

    public <T extends SdkTicket> CompletableFuture<Void> executeNoResponse(
            final String operation,
            final T content,
            final Runnable publishSuccessListener) { // todo dmuren sam tale success se mora prestavit ven
        String correlationId = null;
        try {
            checkConnected();

            final AwaiterNoResponse awaiter = createAwaiterNoResponse(publishSuccessListener, content.getCorrelationId());
            correlationId = awaiter.getCorrelationId();

            final Request request = new Request();
            request.setContent(content.getJsonValue());
            request.setOperation(operation);
            request.setOperatorId(sdkConfiguration.getOperatorId());
            request.setCorrelationId(correlationId);

            final List<ByteBuffer> frames = createFrames(request);
            final SendWsInputMessage msg = new SendWsInputMessage(correlationId, frames);
            awaiter.setSendWsInputMessage(msg);
            enqueueSendMsg(awaiter, 0);

            return awaiter.getFuture();
        } catch (final Exception exc) {
            releaseAwaiter(correlationId);
            final SdkException sdkException = exc instanceof SdkException
                    ? (SdkException) exc
                    : new ProtocolSendFailedException(exc);
            return CompletableFuture.supplyAsync(() -> {
                throw sdkException;
            });
        }
    }

    private <T extends SdkTicket, R extends SdkTicket> void enqueueSendMsg(
            final AwaiterInterface awaiter, final int retryCount) {
        if (retryCount > sdkConfiguration.getProtocolRetryCount()) {
            awaiter.completeWithException(new ProtocolTimeoutException());
            releaseAwaiter(awaiter.getCorrelationId());
            return;
        }
        if (awaiter.getFuture().isDone()) {
            releaseAwaiter(awaiter.getCorrelationId());
            return;
        }

        sendQueue.add(awaiter.getSendWsInputMessage());

        final int nextRetryCount = retryCount + 1;
        delay(() -> enqueueSendMsg(awaiter, nextRetryCount),
                sdkConfiguration.getProtocolReceiveResponseTimeout().toMillis(), TimeUnit.MILLISECONDS);
    }

    private List<ByteBuffer> createFrames(final Request request) {
        final String json = JsonUtils.serializeAsString(request);
        System.out.println("Request JSON: \n" + json);
        System.out.println();
        final byte[] bytes = json.getBytes(StandardCharsets.UTF_8);
        if (bytes.length > MAX_MSG_SIZE) {
            throw new ProtocolMessageTooBigException();
        }

        final List<ByteBuffer> result = new ArrayList<>();
        int offset = 0;
        while (offset < bytes.length) {
            final int chunkSize = Math.min(bytes.length - offset, MAX_CHUNK_SIZE);
            final ByteBuffer buffer = ByteBuffer.wrap(bytes, offset, chunkSize);
            result.add(buffer);
            offset += chunkSize;
        }
        return result;
    }

    private <T extends SdkTicket, R extends SdkTicket> Awaiter<T, R> createAwaiter(
            final Class<R> responseClass, Runnable resultListener, String correlationId) {
        if (approxRequestCount.get() > sdkConfiguration.getProtocolMaxSendBufferSize()) {
            throw new ProtocolSendBufferFullException();
        }

        final Awaiter<T, R> awaiter = new Awaiter<>(responseClass, resultListener);
        while (true) {
            if (correlationIdAwaiter.putIfAbsent(correlationId, awaiter) == null) {
                awaiter.setCorrelationId(correlationId);
                approxRequestCount.incrementAndGet();
                break;
            }
        }
        return awaiter;
    }

    private <T extends SdkTicket, R extends SdkTicket> AwaiterNoResponse createAwaiterNoResponse(
            Runnable resultListener, String correlationId) {
        if (approxRequestCount.get() > sdkConfiguration.getProtocolMaxSendBufferSize()) {
            throw new ProtocolSendBufferFullException();
        }

        final AwaiterNoResponse awaiter = new AwaiterNoResponse(resultListener);
        while (true) {
            if (correlationIdAwaiter.putIfAbsent(correlationId, awaiter) == null) {
                awaiter.setCorrelationId(correlationId);
                approxRequestCount.incrementAndGet();
                break;
            }
        }
        return awaiter;
    }

    private void releaseAwaiter(final String correlationId) {
        if (correlationId == null) {
            return;
        }
        final AwaiterInterface awaiter = correlationIdAwaiter.remove(correlationId);
        if (awaiter == null) {
            return;
        }
        approxRequestCount.decrementAndGet();
        awaiter.release();
    }

    private void receiveLoop() {
        while (this.connected) {
            try {
                final WsOutputMessage msg = this.receiveQueue.poll(
                        sdkConfiguration.getProtocolDequeueTimeout().toMillis(), MILLISECONDS);
                if (msg == null) {
                    continue;
                }
                handleWsOutputMsg(msg);
            } catch (final InterruptedException ignored) {
            } catch (final Exception exception) {
                handleException(exception);
            }
        }
    }

    private void handleWsOutputMsg(final WsOutputMessage msg) {
        if (msg instanceof ReceivedContentWsOutputMessage) {
            handleReceivedContentWsOutputMessage((ReceivedContentWsOutputMessage)msg);
        } else if (msg instanceof ExcWsOutputMessage) {
            handleExcWsOutputMessage((ExcWsOutputMessage)msg);
        } else if (msg instanceof NotProcessedWsOutputMessage) {
            handleNotProcessedWsOutputMessage((NotProcessedWsOutputMessage)msg);
        } else if (msg instanceof SentWsOutputMessage) {
            handleSentWsOutputMessage((SentWsOutputMessage)msg);
        }
    }

    private void handleSentWsOutputMessage(SentWsOutputMessage msg) {
        if (msg.getCorrelationId() == null) {
            handleException(new ProtocolInvalidResponseException("Missing CorrelationId in sent message: " + msg));
            return;
        }
        final AwaiterInterface awaiter = correlationIdAwaiter.get(msg.getCorrelationId());
        awaiter.notifyPublishSuccess();
    }

    private void handleReceivedContentWsOutputMessage(final ReceivedContentWsOutputMessage msg) {
        try {
            Response<?> response = JsonUtils.deserialize(msg.getContent(), Response.class);
            if (response.getCorrelationId() == null) {
                handleException(new ProtocolInvalidResponseException("Missing CorrelationId: " + msg.getContent()));
                return;
            }

            if (responseReceived(response.getCorrelationId(), response)) return;

            final ProtocolInvalidResponseException invalidResponseException =
                    new ProtocolInvalidResponseException("Invalid response: " + msg.getContent());

            if (responseReceived(response.getCorrelationId(), invalidResponseException)) return;

            handleException(invalidResponseException);
        } catch (final Exception e) {
            handleException(e);
        }
    }

    private void handleNotProcessedWsOutputMessage(final NotProcessedWsOutputMessage msg) {
        try {
            final ProtocolInvalidResponseException invalidRequestException =
                    new ProtocolInvalidResponseException("Invalid request");
            if (responseReceived(msg.getCorrelationId(), invalidRequestException)) return;

            handleException(invalidRequestException);
        } catch (final Exception e) {
            handleException(e);
        }
    }

    private void handleExcWsOutputMessage(final ExcWsOutputMessage msg) {
        try {
            if (!responseReceived(msg.getCorrelationId(), msg.getException())) handleException(msg.getException());
        } catch (final Exception e) {
            handleException(e);
        }
    }

    private boolean responseReceived(final String correlationId, final Response<?> response) {
        if (correlationId == null) {
            return false;
        }
        final AwaiterInterface awaiter = correlationIdAwaiter.get(correlationId);
        if (response.getContent() != null
                && awaiter != null
                && awaiter.checkResponseType(response.getContent())) {
            awaiter.completeSuccess(response.getContent());
            releaseAwaiter(correlationId);
            return true;
        }
        return false;
    }

    private boolean responseReceived(final String correlationId, final SdkException sdkException) {
        if (correlationId == null) {
            return false;
        }
        final AwaiterInterface awaiter = correlationIdAwaiter.get(correlationId);
        if (awaiter != null) {
            awaiter.completeWithException(sdkException);
            releaseAwaiter(correlationId);
            return true;
        }
        return false;
    }

    private void handleException(final Exception exception) {
        unhandledExceptionHandler.accept(exception);
    }

    private void checkConnected() {
        if (!connected) throw new SdkNotConnectedException();
    }
}
