package com.sportradar.mts.sdk.ws.internal.protocol;

import com.sportradar.mts.sdk.api.SdkTicket;
import com.sportradar.mts.sdk.ws.exceptions.SdkException;
import com.sportradar.mts.sdk.ws.exceptions.SdkNotConnectedException;
import com.sportradar.mts.sdk.ws.internal.connection.msg.SendWsInputMessage;

import java.util.concurrent.CompletableFuture;

public class AwaiterNoResponse implements AwaiterInterface<Void> {

    private final Runnable resultListener;
    private final CompletableFuture<Void> future;

    private String correlationId;
    private SendWsInputMessage sendWsInputMessage;

    public AwaiterNoResponse(Runnable resultListener) {
        this.resultListener = resultListener;
        this.future = new CompletableFuture<>();
    }

    @Override
    public String getCorrelationId() {
        return correlationId;
    }

    public void setCorrelationId(String correlationId) {
        this.correlationId = correlationId;
    }

    @Override
    public CompletableFuture<Void> getFuture() {
        return future;
    }

    @Override
    public SendWsInputMessage getSendWsInputMessage() {
        return sendWsInputMessage;
    }

    public void setSendWsInputMessage(SendWsInputMessage sendWsInputMessage) {
        this.sendWsInputMessage = sendWsInputMessage;
    }

    @Override
    public void notifyPublishSuccess() {
        resultListener.run();
    }

    @Override
    public boolean checkResponseType(SdkTicket content) {
        return true;
    }

    @Override
    public void completeSuccess(SdkTicket content) {
        this.future.complete(null);
    }

    @Override
    public void completeWithException(final SdkException sdkException) {
        this.future.completeExceptionally(sdkException);
    }

    @Override
    public void release() {
        if (this.future.isDone()) {
            return;
        }
        this.future.completeExceptionally(new SdkNotConnectedException());
    }
}
