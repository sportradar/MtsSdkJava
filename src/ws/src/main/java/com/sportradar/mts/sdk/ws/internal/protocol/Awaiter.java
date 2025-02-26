package com.sportradar.mts.sdk.ws.internal.protocol;

import com.sportradar.mts.sdk.ws.entities.response.ContentResponse;
import com.sportradar.mts.sdk.ws.exceptions.SdkException;
import com.sportradar.mts.sdk.ws.exceptions.SdkNotConnectedException;
import com.sportradar.mts.sdk.ws.internal.connection.msg.SendWsInputMessage;

import java.util.concurrent.CompletableFuture;

public class Awaiter<R extends ContentResponse> {

    private final Class<R> responseClass;
    private final CompletableFuture<R> future;

    private String correlationId;
    private SendWsInputMessage sendWsInputMessage;

    public Awaiter(final Class<R> responseClass) {
        this.responseClass = responseClass;
        this.future = new CompletableFuture<>();
    }

    public String getCorrelationId() {
        return correlationId;
    }

    public void setCorrelationId(String correlationId) {
        this.correlationId = correlationId;
    }

    public CompletableFuture<R> getFuture() {
        return future;
    }

    public SendWsInputMessage getSendWsInputMessage() {
        return sendWsInputMessage;
    }

    public void setSendWsInputMessage(SendWsInputMessage sendWsInputMessage) {
        this.sendWsInputMessage = sendWsInputMessage;
    }

    public boolean checkResponseType(final ContentResponse response) {
        return this.responseClass.isAssignableFrom(response.getClass());
    }

    public void completeSuccess(final ContentResponse response) {
        this.future.complete(this.responseClass.cast(response));
    }

    public void completeWithException(final SdkException sdkException) {
        this.future.completeExceptionally(sdkException);
    }

    public void release() {
        if (this.future.isDone()) {
            return;
        }
        this.future.completeExceptionally(new SdkNotConnectedException());
    }
}
