package com.sportradar.mts.sdk.ws.internal.protocol;

import com.sportradar.mts.sdk.api.SdkTicket;
import com.sportradar.mts.sdk.ws.exceptions.SdkException;
import com.sportradar.mts.sdk.ws.internal.connection.msg.base.WsInputMessage;

import java.util.concurrent.CompletableFuture;

public interface AwaiterInterface<T> {
    void release();

    void notifyPublishSuccess();

    boolean checkResponseType(SdkTicket content);

    void completeSuccess(SdkTicket content);

    void completeWithException(SdkException sdkException);

    String getCorrelationId();

    CompletableFuture<T> getFuture();

    WsInputMessage getSendWsInputMessage();
}
