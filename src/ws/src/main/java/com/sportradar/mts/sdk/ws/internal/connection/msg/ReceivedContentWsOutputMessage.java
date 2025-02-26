package com.sportradar.mts.sdk.ws.internal.connection.msg;

import com.sportradar.mts.sdk.ws.internal.connection.msg.base.WsOutputMessage;

public class ReceivedContentWsOutputMessage extends WsOutputMessage {

    private final String content;

    public ReceivedContentWsOutputMessage(final String contebt) {
        super(null);
        this.content = contebt;
    }

    public String getContent() {
        return content;
    }
}
