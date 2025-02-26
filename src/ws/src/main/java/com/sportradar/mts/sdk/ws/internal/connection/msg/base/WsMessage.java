package com.sportradar.mts.sdk.ws.internal.connection.msg.base;

public abstract class WsMessage {

    private final String correlationId;

    public WsMessage(final String correlationId) {
        this.correlationId = correlationId == null ? "" : correlationId;
    }

    public String getCorrelationId() {
        return correlationId;
    }
}
