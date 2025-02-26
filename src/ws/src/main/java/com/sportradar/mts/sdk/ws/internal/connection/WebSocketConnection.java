package com.sportradar.mts.sdk.ws.internal.connection;

import com.sportradar.mts.sdk.ws.exceptions.SdkException;
import com.sportradar.mts.sdk.ws.exceptions.WebSocketConnectionException;
import com.sportradar.mts.sdk.ws.internal.config.ImmutableConfig;
import com.sportradar.mts.sdk.ws.internal.config.WebSocketConnectionConfig;
import com.sportradar.mts.sdk.ws.internal.connection.msg.*;
import com.sportradar.mts.sdk.ws.internal.connection.msg.base.WsInputMessage;
import com.sportradar.mts.sdk.ws.internal.connection.msg.base.WsOutputMessage;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.enums.Opcode;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicReference;

import static com.sportradar.mts.sdk.ws.internal.utils.Delayer.delay;
import static com.sportradar.mts.sdk.ws.internal.utils.ExcSuppress.threadJoin;
import static java.util.concurrent.TimeUnit.MILLISECONDS;

public class WebSocketConnection implements AutoCloseable {

    private final WebSocketConnectionConfig config;
    private final TokenProvider tokenProvider;
    private final BlockingQueue<WsInputMessage> sendQueue;
    private final BlockingQueue<WsOutputMessage> receiveQueue;
    private final AtomicReference<WebSocket> webSocket;

    private volatile boolean connected = false;

    private Thread senderThread;

    public WebSocketConnection(
            final ImmutableConfig config,
            final TokenProvider tokenProvider,
            final BlockingQueue<WsInputMessage> sendQueue,
            final BlockingQueue<WsOutputMessage> receiveQueue) {
        this.config = config;
        this.tokenProvider = tokenProvider;
        this.sendQueue = sendQueue;
        this.receiveQueue = receiveQueue;
        this.webSocket = new AtomicReference<>(null);
    }

    public void connect() {
        reconnectWebSocket(null, true);
        final Thread thread = new Thread(this::sendLoop);
        thread.setDaemon(true);
        this.senderThread = thread;
        this.connected = true;
        thread.start();
    }

    @Override
    public void close() {
        this.connected = false;
        final Thread thread = this.senderThread;
        this.senderThread = null;
        threadJoin(thread);
        final WebSocket ws = this.webSocket.getAndSet(null);
        if (ws != null) {
            ws.close();
        }
    }

    private void sendLoop() {
        while (this.connected) {
            WsInputMessage msg = null;
            try {
                msg = this.sendQueue.poll(config.getWsFetchMessageTimeout().toMillis(), MILLISECONDS);
                if (msg == null) {
                    continue;
                }
                if (msg instanceof SendWsInputMessage) {
                    final List<ByteBuffer> msgs = ((SendWsInputMessage) msg).getContent();
                    final WebSocket ws = this.webSocket.get();
                    try {
                        sendMsg(ws, msgs);
                    } catch (final Exception e) {
                        this.receiveQueue.add(new ExcWsOutputMessage(null, new WebSocketConnectionException(e)));
                        reconnectWebSocket(ws, false);
                        sendMsg(this.webSocket.get(), msgs);
                    }
                    this.receiveQueue.add(new SentWsOutputMessage(msg));
                } else {
                    this.receiveQueue.add(new NotProcessedWsOutputMessage(msg));
                }

            } catch (final InterruptedException ignored) {
            } catch (final Exception exception) {
                this.receiveQueue.add(new ExcWsOutputMessage(msg, new WebSocketConnectionException(exception)));
            }
        }
    }

    private void sendMsg(final WebSocket ws, final List<ByteBuffer> msgs) {
        for (int i = 0; i < msgs.size(); i++) {
            ws.sendFragmentedFrame(Opcode.TEXT, msgs.get(i), i == (msgs.size() - 1));
        }
    }

    private void reconnectWebSocket(final WebSocket ws, final boolean throwExc) {
        if (this.webSocket.get() != ws) {
            return;
        }
        final WebSocket newWs;
        try {
            newWs = new WebSocket(this, config.getWsServer(), tokenProvider.getToken());
            if (!newWs.connectBlocking(config.getWsReconnectTimeout().toMillis(), MILLISECONDS)) {
                throw new WebSocketConnectionException("Socket connect failed.");
            }
        } catch (final Exception exception) {
            final SdkException sdkExc = exception instanceof SdkException
                    ? (SdkException) exception
                    : new WebSocketConnectionException(exception);
            if (throwExc) {
                throw sdkExc;
            }
            this.receiveQueue.add(new ExcWsOutputMessage(null, sdkExc));
            return;
        }
        if (this.webSocket.compareAndSet(ws, newWs)) {
            if (ws != null) {
                delay(ws::close, config.getWsConsumerGraceTimeout().toMillis(), MILLISECONDS);
            }
            delay(() -> reconnectWebSocket(newWs, false), config.getWsRefreshConnectionTimeout().toMillis(), MILLISECONDS);
        } else {
            newWs.close();
        }
    }

    private void onOpen(final WebSocket ws, final ServerHandshake serverHandshake) {
    }

    private void onMessage(final WebSocket ws, final String msg) {
        this.receiveQueue.add(new ReceivedContentWsOutputMessage(msg));
    }

    private void onClose(final WebSocket ws, final int code, final String msg, final boolean remote) {
        this.receiveQueue.add(new ExcWsOutputMessage(null, new WebSocketConnectionException(
                "Socket closed by " + (remote ? "server" : "client") + ", code: " + code + ", reason: " + msg + ".")));
        reconnectWebSocket(ws, false);
    }

    private void onError(final WebSocket ws, final Exception exception) {
        this.receiveQueue.add(new ExcWsOutputMessage(null, new WebSocketConnectionException(exception)));
        reconnectWebSocket(ws, false);
    }

    public static class WebSocket extends WebSocketClient {

        private final WebSocketConnection connection;

        public WebSocket(final WebSocketConnection connection, final URI serverUri, final String token) {
            super(serverUri, headers(token));
            this.connection = connection;
        }

        private static Map<String, String> headers(final String token) {
            final Map<String, String> headers = new HashMap<>();
            headers.put("Authorization", "Bearer " + token);
            return headers;
        }

        @Override
        public void onOpen(final ServerHandshake serverHandshake) {
            this.connection.onOpen(this, serverHandshake);
        }

        @Override
        public void onMessage(final String msg) {
            this.connection.onMessage(this, msg);
        }

        @Override
        public void onClose(final int code, final String msg, final boolean remote) {
            this.connection.onClose(this, code, msg, remote);
        }

        @Override
        public void onError(final Exception exception) {
            this.connection.onError(this, exception);
        }
    }
}
