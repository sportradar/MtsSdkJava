package com.sportradar.mts.sdk.ws.internal.connection.msg;

import com.sportradar.mts.sdk.ws.internal.connection.msg.base.WsInputMessage;
import com.sportradar.mts.sdk.ws.internal.connection.msg.base.WsOutputMessage;

public class SentWsOutputMessage extends WsOutputMessage {

    private final WsInputMessage message;

    public SentWsOutputMessage(final WsInputMessage message) {
        super(message == null ? null : message.getCorrelationId());
        this.message = message;
    }

    public WsInputMessage getMessage() {
        return message;
    }
}
