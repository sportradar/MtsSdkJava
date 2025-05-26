/*
 * Copyright (C) Sportradar AG. See LICENSE for full license governing this code
 */

package com.sportradar.mts.sdk.impl.libs.handlers;

import com.sportradar.mts.sdk.api.Ticket;
import com.sportradar.mts.sdk.api.TicketResponse;
import com.sportradar.mts.sdk.api.TicketSenderWs;
import com.sportradar.mts.sdk.api.exceptions.ResponseTimeoutException;
import com.sportradar.mts.sdk.api.interfaces.TicketResponseListener;
import com.sportradar.mts.sdk.api.utils.JsonUtils;
import com.sportradar.mts.sdk.api.utils.SdkInfo;
import com.sportradar.mts.sdk.api.utils.StringUtils;
import com.sportradar.mts.sdk.impl.libs.logging.SdkLogger;
import com.sportradar.mts.sdk.ws.exceptions.ProtocolTimeoutException;
import com.sportradar.mts.sdk.ws.internal.protocol.ProtocolEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ScheduledExecutorService;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;

public class TicketHandlerWsImpl implements TicketHandler {

    private final String routingKey;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final SdkLogger sdkLogger;

    private final ProtocolEngine engine;
    private final ScheduledExecutorService executorService;
    private TicketResponseListener ticketResponseListener;

    private final Object stateLock = new Object();
    private boolean opened;

    public TicketHandlerWsImpl(
            String routingKey,
            SdkLogger sdkLogger,
            ProtocolEngine engine,
            ScheduledExecutorService executorService) {
        this.routingKey = routingKey;
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
     * Sends the {@link Ticket} to the MTS
     *
     * @param ticket ticket to send
     */
    @Override
    public void send(Ticket ticket) {
        sendAsync(ticket);
    }

    /**
     * Sends the {@link Ticket} to the MTS and returns {@link TicketResponse}
     *
     * @param ticket ticket to send
     * @return ticket response
     * @throws ResponseTimeoutException if no response is received in time
     */
    @Override
    public TicketResponse sendBlocking(Ticket ticket) throws ResponseTimeoutException {
        return sendAsync(ticket).join();
    }

    private CompletableFuture<TicketResponse> sendAsync(Ticket ticket) {
        checkState(isOpen(), SdkInfo.Literals.TICKET_HANDLER_SENDER_CLOSED);
        checkNotNull(ticket, SdkInfo.Literals.TICKET_HANDLER_TICKET_NULL);

        checkNotNull(ticketResponseListener, "no response listener set");
        logger.trace(
                "PUBLISH ticket:{}, correlationId:{}",
                ticket.getTicketId(),
                ticket.getCorrelationId()
        );
        logger.trace("PUBLISH {}", ticket.getJsonValue());
        logger.debug("WS SEND ticket correlationId: {}", ticket.getCorrelationId()); // todo dmuren logging
        String msgString = ticket.getJsonValue();
        sdkLogger.logSendMessage(msgString);
        if (StringUtils.isNullOrEmpty(ticket.getCorrelationId())) {
            logger.warn("Ticket {} is missing correlationId", ticket.getTicketId());
        }
        return engine.execute(routingKey, ticket, TicketResponse.class, () -> ticketResponseListener.publishSuccess(ticket))
                .handle((response, throwable) -> { // todo dmuren double check logic
                    if (throwable instanceof ProtocolTimeoutException) {
                        ticketResponseListener.onTicketResponseTimedOut(ticket);
                    } else if (throwable != null) {
                        ticketResponseListener.publishFailure(ticket);
                    } else if (response != null) {
                        ticketResponseReceived(response);
                    }
                    throw new IllegalStateException("Unhandled response!");
                });
    }

    @Override
    public void setListener(TicketResponseListener responseListener) {
        checkNotNull(responseListener, "responseListener cannot be null");
        this.ticketResponseListener = responseListener;
    }

    @Override
    public void ticketResponseReceived(TicketResponse ticketResponse) {
        checkNotNull(ticketResponse, "ticketResponse cannot be null");
        sdkLogger.logReceivedMessage(JsonUtils.serializeAsString(ticketResponse));
        logger.debug("WS RECEIVED ticket correlationId: {}", ticketResponse.getCorrelationId()); // todo dmuren logging

        executorService.submit(() -> {
            try {
                ticketResponseListener.responseReceived(ticketResponse);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

}
