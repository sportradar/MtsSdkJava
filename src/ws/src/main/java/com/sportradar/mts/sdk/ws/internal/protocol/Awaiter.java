package com.sportradar.mts.sdk.ws.internal.protocol;

import com.sportradar.mts.sdk.api.SdkTicket;
import com.sportradar.mts.sdk.api.TicketResponse;
import com.sportradar.mts.sdk.ws.exceptions.SdkException;
import com.sportradar.mts.sdk.ws.exceptions.SdkNotConnectedException;
import com.sportradar.mts.sdk.ws.internal.connection.msg.SendWsInputMessage;

import java.util.concurrent.CompletableFuture;

public class Awaiter<T extends SdkTicket, R extends SdkTicket> {

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

    public boolean checkResponseType(final TicketResponse response) {
        return this.responseClass.isAssignableFrom(response.getClass());
    }

    public void notifyPublishSuccess() {
        resultListener.run();
    }

    public void completeSuccess(final TicketResponse response) {
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
