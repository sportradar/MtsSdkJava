package com.sportradar.mts.sdk.ws.internal.connection.msg.base;

public abstract class WsInputMessage extends WsMessage {

    public WsInputMessage(final String correlationId) {
        super(correlationId);
    }
}
