package com.sportradar.mts.sdk.ws.internal.protocol;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sportradar.mts.sdk.ws.entities.internal.Request;
import com.sportradar.mts.sdk.ws.entities.internal.Response;
import com.sportradar.mts.sdk.ws.entities.request.ContentRequest;
import com.sportradar.mts.sdk.ws.entities.response.ContentResponse;
import com.sportradar.mts.sdk.ws.entities.response.ErrorResponse;
import com.sportradar.mts.sdk.ws.exceptions.*;
import com.sportradar.mts.sdk.ws.internal.config.ImmutableConfig;
import com.sportradar.mts.sdk.ws.internal.config.ProtocolHandlerConfig;
import com.sportradar.mts.sdk.ws.internal.connection.ConnectionProvider;
import com.sportradar.mts.sdk.ws.internal.connection.msg.*;
import com.sportradar.mts.sdk.ws.internal.connection.msg.ExcWsOutputMessage;
import com.sportradar.mts.sdk.ws.internal.connection.msg.NotProcessedWsOutputMessage;
import com.sportradar.mts.sdk.ws.internal.connection.msg.ReceivedContentWsOutputMessage;
import com.sportradar.mts.sdk.ws.internal.connection.msg.SendWsInputMessage;
import com.sportradar.mts.sdk.ws.internal.connection.msg.base.WsInputMessage;
import com.sportradar.mts.sdk.ws.internal.connection.msg.base.WsOutputMessage;
import com.sportradar.mts.sdk.ws.internal.utils.ExcSuppress;
import com.sportradar.mts.sdk.ws.internal.utils.Json;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

import static com.sportradar.mts.sdk.ws.internal.utils.Delayer.delay;
import static com.sportradar.mts.sdk.ws.internal.utils.ExcSuppress.threadJoin;
import static com.sportradar.mts.sdk.ws.internal.utils.Extensions.randomString;
import static com.sportradar.mts.sdk.ws.internal.utils.TimeUtils.nowUtcMillis;
import static java.util.concurrent.TimeUnit.MILLISECONDS;

public class ProtocolEngine implements AutoCloseable {

    private static final int MAX_CHUNK_SIZE = 32_000;
    private static final int MAX_MSG_SIZE = 4 * MAX_CHUNK_SIZE;

    private final ProtocolHandlerConfig config;
    private final ConnectionProvider connectionProvider;
    private final BlockingQueue<WsInputMessage> sendQueue;
    private final BlockingQueue<WsOutputMessage> receiveQueue;
    private final ConcurrentMap<String, Awaiter<?>> correlationIdAwaiter;
    private final AtomicInteger approxRequestCount;
    private final Consumer<Exception> unhandledExceptionHandler;

    private volatile boolean connected = false;

    private Thread[] receiverThreads;

    public ProtocolEngine(final ImmutableConfig config, final Consumer<Exception> unhandledExceptionHandler) {
        this.config = config;
        this.sendQueue = new LinkedBlockingQueue<>();
        this.receiveQueue = new LinkedBlockingQueue<>();
        this.correlationIdAwaiter = new ConcurrentHashMap<>();
        this.approxRequestCount = new AtomicInteger(0);
        this.connectionProvider = new ConnectionProvider(config, sendQueue, receiveQueue);
        this.unhandledExceptionHandler = unhandledExceptionHandler;
    }

    public void connect() {
        this.receiverThreads = new Thread[config.getProtocolNumberOfDispatchers()];
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

    public <T extends ContentRequest, R extends ContentResponse> CompletableFuture<R> execute(
            final String operation, final T content, final Class<R> responseClass) {
        String correlationId = null;
        try {
            checkConnected();

            final Awaiter<R> awaiter = createAwaiter(responseClass);
            correlationId = awaiter.getCorrelationId();

            final Request request = new Request();
            request.setContent(content);
            request.setOperation(operation);
            request.setCorrelationId(correlationId);
            request.setVersion("3.0");
            request.setOperatorId(this.config.getOperatorId());
            request.setTimestampUtc(nowUtcMillis());

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

    private <R extends ContentResponse> void enqueueSendMsg(final Awaiter<R> awaiter, final int retryCount) {
        if (retryCount > config.getProtocolRetryCount()) {
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
                config.getProtocolReceiveResponseTimeout().toMillis(), TimeUnit.MILLISECONDS);
    }

    private List<ByteBuffer> createFrames(final Request request) throws JsonProcessingException {
        final String json = Json.serializeRequest(request);
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

    private <O extends ContentResponse> Awaiter<O> createAwaiter(final Class<O> responseClass) {
        if (approxRequestCount.get() > config.getProtocolMaxSendBufferSize()) {
            throw new ProtocolSendBufferFullException();
        }

        final Awaiter<O> awaiter = new Awaiter<>(responseClass);
        while (true) {
            final String correlationId = randomString();
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
        final Awaiter<?> awaiter = correlationIdAwaiter.remove(correlationId);
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
                        config.getProtocolDequeueTimeout().toMillis(), MILLISECONDS);
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
//        if (msg instanceof final ReceivedContentWsOutputMessage m) {
//            handleReceivedContentWsOutputMessage(m);
//        } else if (msg instanceof final ExcWsOutputMessage m) {
//            handleExcWsOutputMessage(m);
//        } else if (msg instanceof final NotProcessedWsOutputMessage m) {
//            handleNotProcessedWsOutputMessage(m);
//        }
        if (msg instanceof ReceivedContentWsOutputMessage) { // todo dmuren responseReceived pomoje
            handleReceivedContentWsOutputMessage((ReceivedContentWsOutputMessage)msg);
        } else if (msg instanceof ExcWsOutputMessage) { // todo dmuren publishFailure? se cel kup drugih errorjev to triggera
            handleExcWsOutputMessage((ExcWsOutputMessage)msg);
        } else if (msg instanceof NotProcessedWsOutputMessage) { // todo dmuren publishFailure? didn't get published actually ...
            handleNotProcessedWsOutputMessage((NotProcessedWsOutputMessage)msg);
        } else if (msg instanceof SentWsOutputMessage) { // todo dmuren not here originally
            // todo dmuren publishSuccess - mogoce treba not prpeljat listenerja? i guess.
        }
    }

    private void handleReceivedContentWsOutputMessage(final ReceivedContentWsOutputMessage msg) {
        try {
            final Response response = Json.deserializeResponse(msg.getContent());
            if (response.getCorrelationId() == null) {
                handleException(new ProtocolInvalidResponseException("Missing CorrelationId: " + msg.getContent()));
                return;
            }

            if (responseReceived(response.getCorrelationId(), response)) return;

//            if (response.getContent() instanceof ErrorResponse) { // todo dmuren
//                ErrorResponse error = (ErrorResponse) response.getContent();
//                final ServerErrorResponseException sdkException = new ServerErrorResponseException(
//                        error.getErrorCode(), error.getErrorMessage());
//                if (responseReceived(response.getCorrelationId(), sdkException)) return;
//            }

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

    private boolean responseReceived(final String correlationId, final Response response) {
        if (correlationId == null) {
            return false;
        }
        final Awaiter<?> awaiter = correlationIdAwaiter.get(correlationId);
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
        final Awaiter<?> awaiter = correlationIdAwaiter.get(correlationId);
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
