package com.sportradar.mts.sdk.ws.internal.protocol;

import com.sportradar.mts.sdk.api.SdkTicket;
import com.sportradar.mts.sdk.ws.exceptions.SdkException;
import com.sportradar.mts.sdk.ws.exceptions.SdkNotConnectedException;
import com.sportradar.mts.sdk.ws.internal.connection.msg.SendWsInputMessage;

import java.util.concurrent.CompletableFuture;

public class Awaiter<T extends SdkTicket, R extends SdkTicket> implements AwaiterInterface<R> {

    private final Class<R> responseClass;
    private final Runnable resultListener;
    private final CompletableFuture<R> future;

    private String correlationId;
    private SendWsInputMessage sendWsInputMessage;

    public Awaiter(final Class<R> responseClass, Runnable resultListener) {
        this.responseClass = responseClass;
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
    public CompletableFuture<R> getFuture() {
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
    public boolean checkResponseType(final SdkTicket response) {
        return this.responseClass.isAssignableFrom(response.getClass());
    }

    @Override
    public void notifyPublishSuccess() {
        resultListener.run();
    }

    @Override
    public void completeSuccess(final SdkTicket response) {
        this.future.complete(this.responseClass.cast(response));
    }

    @Override
    public void completeWithException(final SdkException sdkException) {
        this.sendWsInputMessage.suppressSend();
        this.future.completeExceptionally(sdkException);
    }

    @Override
    public void release() {
        if (this.future.isDone()) {
            return;
        }
        completeWithException(new SdkNotConnectedException());
    }
}
