/*
 * Copyright (C) Sportradar AG. See LICENSE for full license governing this code
 */

package com.sportradar.mts.sdk.impl.libs.handlers;

import com.sportradar.mts.sdk.api.TicketCancel;
import com.sportradar.mts.sdk.api.TicketCancelResponse;
import com.sportradar.mts.sdk.api.exceptions.ResponseTimeoutException;
import com.sportradar.mts.sdk.api.interfaces.TicketCancelResponseListener;
import com.sportradar.mts.sdk.api.utils.JsonUtils;
import com.sportradar.mts.sdk.api.utils.SdkInfo;
import com.sportradar.mts.sdk.api.utils.StringUtils;
import com.sportradar.mts.sdk.impl.libs.logging.SdkLogger;
import com.sportradar.mts.sdk.ws.exceptions.ProtocolTimeoutException;
import com.sportradar.mts.sdk.ws.internal.protocol.ProtocolEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;

public class TicketCancelHandlerWsImpl implements TicketCancelHandler {

    private final String routingKey;
    private static final Logger logger = LoggerFactory.getLogger(TicketCancelHandlerWsImpl.class);
    private SdkLogger sdkLogger;

    private final ProtocolEngine engine;
    private final ExecutorService executorService;
    private TicketCancelResponseListener ticketCancelResponseListener;

    private final int responseTimeout;
    private final String replyRoutingKey;

    private final Object stateLock = new Object();
    private boolean opened;

    @SuppressWarnings("java:S107") // Methods should not have too many parameters
    public TicketCancelHandlerWsImpl(String routingKey,
                                     String replyRoutingKey,
                                     ProtocolEngine engine,
                                     ExecutorService executorService,
                                     int responseTimeout,
                                     SdkLogger sdkLogger) {
        this.engine = engine;

        checkNotNull(executorService, "executorService cannot be null");

        this.executorService = executorService;
        this.routingKey = routingKey == null ? "cancel" : routingKey;
        this.replyRoutingKey = replyRoutingKey;
        this.responseTimeout = responseTimeout;

        this.sdkLogger = sdkLogger;
    }

    @Override
    public void open() {
        synchronized (stateLock) {
            engine.connect();
            opened = true;
        }
    }

    @Override
    public void close() {
        synchronized (stateLock) {
            engine.close();
            opened = false;
        }
    }

    @Override
    public boolean isOpen() {
        return opened;
    }

    @Override
    public void send(TicketCancel ticketCancel) {
        sendAsync(ticketCancel);
    }

    @Override
    public TicketCancelResponse sendBlocking(TicketCancel ticketCancel) throws ResponseTimeoutException {
        return sendAsync(ticketCancel).join();
    }

    private CompletableFuture<TicketCancelResponse> sendAsync(
            TicketCancel ticket) {
        checkState(isOpen(), SdkInfo.Literals.TICKET_HANDLER_SENDER_CLOSED);
        checkNotNull(ticket, SdkInfo.Literals.TICKET_HANDLER_TICKET_CANCEL_NULL);
        checkNotNull(ticketCancelResponseListener, "no response listener set");
        logger.trace("PUBLISH ticket:{}, correlationId:{}, routingKey:{}, replyRoutingKey:{}",
                ticket.getTicketId(),
                ticket.getCorrelationId(),
                routingKey,
                replyRoutingKey);
        logger.trace("PUBLISH {}", ticket.getJsonValue());
        logger.debug("WS SEND ticket correlationId: {}", ticket.getCorrelationId()); // todo dmuren logging
        String msgString = ticket.getJsonValue();
        sdkLogger.logSendMessage(msgString);
        if(StringUtils.isNullOrEmpty(ticket.getCorrelationId())) {
            logger.warn("Ticket {} is missing correlationId", ticket.getTicketId());
        }
        return engine.execute(routingKey, ticket, TicketCancelResponse.class, () -> ticketCancelResponseListener.publishSuccess(ticket))
                .handle((response, throwable) -> { // todo dmuren double check logic
                    if (throwable instanceof ProtocolTimeoutException) {
                        ticketCancelResponseListener.onTicketResponseTimedOut(ticket);
                    } else if (throwable != null) {
                        ticketCancelResponseListener.publishFailure(ticket);
                    } else if (response != null) {
                        ticketCancelResponseReceived(response);
                    }
                    throw new IllegalStateException("Unhandled response!");
                });
    }

    @Override
    public void setListener(TicketCancelResponseListener responseListener) {
        checkNotNull(responseListener, "response listener cannot be null");
        this.ticketCancelResponseListener = responseListener;
    }

    @Override
    public void ticketCancelResponseReceived(TicketCancelResponse ticketCancelResponse) {
        checkNotNull(ticketCancelResponse, "ticketCancelResponse cannot be null");
        sdkLogger.logReceivedMessage(JsonUtils.serializeAsString(ticketCancelResponse));
        logger.debug("WS RECEIVED ticket cancel correlationId: {}", ticketCancelResponse.getCorrelationId()); // todo dmuren logging

        executorService.submit(() -> {
            try {
                ticketCancelResponseListener.responseReceived(ticketCancelResponse);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

}
