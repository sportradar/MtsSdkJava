package com.sportradar.mts.sdk.ws.internal.connection.msg;

import com.sportradar.mts.sdk.ws.internal.connection.msg.base.WsInputMessage;

import java.nio.ByteBuffer;
import java.util.List;

public class SendWsInputMessage extends WsInputMessage {

    private final List<ByteBuffer> content;
    private volatile boolean suppressed = false;

    public SendWsInputMessage(final String correlationId, final List<ByteBuffer> content) {
        super(correlationId);
        this.content = content;
    }

    public List<ByteBuffer> getContent() {
        return content;
    }

    public boolean isSuppressed() {
        return suppressed;
    }

    public void suppressSend() {
        suppressed = true;
    }
}
