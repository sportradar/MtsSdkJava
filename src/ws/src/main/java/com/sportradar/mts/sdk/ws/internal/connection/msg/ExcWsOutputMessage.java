package com.sportradar.mts.sdk.ws.internal.connection.msg;

import com.sportradar.mts.sdk.ws.exceptions.SdkException;
import com.sportradar.mts.sdk.ws.internal.connection.msg.base.WsInputMessage;
import com.sportradar.mts.sdk.ws.internal.connection.msg.base.WsOutputMessage;

public class ExcWsOutputMessage extends WsOutputMessage {

    private final WsInputMessage message;
    private final SdkException exception;

    public ExcWsOutputMessage(final WsInputMessage message, final SdkException exception) {
        super(message == null ? null : message.getCorrelationId());
        this.message = message;
        this.exception = exception;
    }

    public WsInputMessage getMessage() {
        return message;
    }

    public SdkException getException() {
        return exception;
    }
}
