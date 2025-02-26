package com.sportradar.mts.sdk.ws.internal.connection.msg.base;

public abstract class WsOutputMessage extends WsMessage {

    public WsOutputMessage(final String correlationId) {
        super(correlationId);
    }
}
