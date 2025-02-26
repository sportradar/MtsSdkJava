package com.sportradar.mts.sdk.ws.internal.connection;

import com.sportradar.mts.sdk.ws.internal.config.ImmutableConfig;
import com.sportradar.mts.sdk.ws.internal.connection.msg.base.WsInputMessage;
import com.sportradar.mts.sdk.ws.internal.connection.msg.base.WsOutputMessage;
import com.sportradar.mts.sdk.ws.internal.utils.ExcSuppress;

import java.util.concurrent.BlockingQueue;

public class ConnectionProvider implements AutoCloseable {

    private final TokenProvider tokenProvider;
    private final WebSocketConnection[] connections;

    public ConnectionProvider(
            final ImmutableConfig config,
            final BlockingQueue<WsInputMessage> sendQueue,
            final BlockingQueue<WsOutputMessage> receiveQueue) {
        this.tokenProvider = new TokenProvider(config);
        final int numOfConns = config.getWsNumberOfConnections();
        final WebSocketConnection[] conns = new WebSocketConnection[numOfConns];
        for (int i = 0; i < numOfConns; i++) {
            conns[i] = new WebSocketConnection(config, tokenProvider, sendQueue, receiveQueue);
        }
        this.connections = conns;
    }

    public void connect() {
        tokenProvider.connect();
        for (final WebSocketConnection connection : connections) {
            connection.connect();
        }
    }

    @Override
    public void close() {
        for (final WebSocketConnection connection : connections) {
            ExcSuppress.close(connection);
        }
        ExcSuppress.close(tokenProvider);
    }
}
