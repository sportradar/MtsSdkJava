/*
 * Copyright (C) Sportradar AG. See LICENSE for full license governing this code
 */

package com.sportradar.mts.sdk.impl.libs.handlers;

import com.sportradar.mts.sdk.api.TicketNonSrSettle;
import com.sportradar.mts.sdk.api.TicketNonSrSettleResponse;
import com.sportradar.mts.sdk.api.exceptions.ResponseTimeoutException;
import com.sportradar.mts.sdk.api.interfaces.TicketNonSrSettleResponseListener;
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
import java.util.concurrent.ScheduledExecutorService;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;

public class TicketNonSrSettleHandlerWsImpl implements TicketNonSrSettleHandler {
    private final String routingKey;
    private static final Logger logger = LoggerFactory.getLogger(TicketNonSrSettleHandlerWsImpl.class);
    private final SdkLogger sdkLogger;

    private final ProtocolEngine engine;
    private final ExecutorService executorService;
    private TicketNonSrSettleResponseListener ticketNonSrSettleResponseListener;

    private final Object stateLock = new Object();
    private boolean opened;

    public TicketNonSrSettleHandlerWsImpl(
            String routingKey,
            SdkLogger sdkLogger,
            ProtocolEngine engine,
            ScheduledExecutorService executorService) {
        this.routingKey = routingKey == null ? "ticket.nonsrsettle" : routingKey;
        this.sdkLogger = sdkLogger;
        this.engine = engine;
        this.executorService = executorService;
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

    /**
     * Publishes a new {@link TicketNonSrSettle} message
     *
     * @param ticket - the data from which the message will be built
     */
    @Override
    public void send(TicketNonSrSettle ticket) {
        sendAsync(ticket);
    }

    /**
     * Publishes a new {@link TicketNonSrSettle} message and waits for a response with the specified timeout
     *
     * @param ticket - the data from which the message will be built
     * @return - the cashout response from the MTS
     * @throws ResponseTimeoutException - if the max timeout for the response has exceeded
     */
    @Override
    public TicketNonSrSettleResponse sendBlocking(TicketNonSrSettle ticket) throws ResponseTimeoutException {
        return sendAsync(ticket).join();
    }

    private CompletableFuture<TicketNonSrSettleResponse> sendAsync(TicketNonSrSettle ticket) {
        checkState(isOpen(), SdkInfo.Literals.TICKET_HANDLER_SENDER_CLOSED);
        checkNotNull(ticket, SdkInfo.Literals.TICKET_HANDLER_TICKET_NONSR_NULL);

        checkNotNull(ticketNonSrSettleResponseListener, "no response listener set");
        logger.trace(
                "PUBLISH ticket:{}, correlationId:{}",
                ticket.getTicketId(),
                ticket.getCorrelationId()
        );
        logger.trace("PUBLISH {}", ticket.getJsonValue());
        sdkLogger.logSendMessage(ticket.getJsonValue());
        if (StringUtils.isNullOrEmpty(ticket.getCorrelationId())) {
            logger.warn("Ticket {} is missing correlationId", ticket.getTicketId());
        }
        return engine.execute(routingKey, ticket, ticket.getBookmakerId(), TicketNonSrSettleResponse.class,
                        () -> ticketNonSrSettleResponseListener.publishSuccess(ticket))
                .whenComplete((response, throwable) -> {
                    if (throwable instanceof ProtocolTimeoutException) {
                        ticketNonSrSettleResponseListener.onTicketResponseTimedOut(ticket);
                    } else if (throwable != null) {
                        ticketNonSrSettleResponseListener.publishFailure(ticket);
                    } else if (response != null) {
                        setTicketNonSrSettleResponse(response);
                    }
                });
    }

    @Override
    public void setListener(TicketNonSrSettleResponseListener responseListener) {
        checkNotNull(responseListener, "response listener cannot be null");
        this.ticketNonSrSettleResponseListener = responseListener;
    }

    @Override
    public void setTicketNonSrSettleResponse(TicketNonSrSettleResponse ticketNonSrSettleResponse) {
        checkNotNull(ticketNonSrSettleResponse, "TicketNonSrSettleResponse cannot be null");
        sdkLogger.logReceivedMessage(JsonUtils.serializeAsString(ticketNonSrSettleResponse));
        executorService.submit(() -> {
            try {
            ticketNonSrSettleResponseListener.responseReceived(ticketNonSrSettleResponse);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

}
