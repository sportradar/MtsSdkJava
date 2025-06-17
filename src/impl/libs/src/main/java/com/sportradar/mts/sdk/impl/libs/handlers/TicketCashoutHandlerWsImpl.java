/*
 * Copyright (C) Sportradar AG. See LICENSE for full license governing this code
 */

package com.sportradar.mts.sdk.impl.libs.handlers;

import com.sportradar.mts.sdk.api.TicketCashout;
import com.sportradar.mts.sdk.api.TicketCashoutResponse;
import com.sportradar.mts.sdk.api.exceptions.ResponseTimeoutException;
import com.sportradar.mts.sdk.api.interfaces.TicketCashoutResponseListener;
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

public class TicketCashoutHandlerWsImpl implements TicketCashoutHandler {

    private final String routingKey;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final SdkLogger sdkLogger;

    private final ProtocolEngine engine;
    private final ScheduledExecutorService executorService;
    private TicketCashoutResponseListener ticketCashoutResponseListener;

    private final Object stateLock = new Object();
    private boolean opened;

    public TicketCashoutHandlerWsImpl(
            String routingKey,
            SdkLogger sdkLogger,
            ProtocolEngine engine,
            ScheduledExecutorService executorService) {
        this.routingKey = routingKey == null ? "ticket.cashout" : routingKey;
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
     * Publishes a new {@link TicketCashout} message
     *
     * @param ticketCashoutData - the data from which the message will be built
     */
    @Override
    public void send(TicketCashout ticketCashoutData) {
        sendAsync(ticketCashoutData);
    }

    /**
     * Publishes a new {@link TicketCashout} message and waits for a response with the specified timeout
     *
     * @param ticketCashout - the data from which the message will be built
     * @return - the cashout response from the MTS
     * @throws ResponseTimeoutException - if the max timeout for the response has exceeded
     */
    @Override
    public TicketCashoutResponse sendBlocking(TicketCashout ticketCashout) throws ResponseTimeoutException {
        return sendAsync(ticketCashout).join();
    }

    private CompletableFuture<TicketCashoutResponse> sendAsync(TicketCashout cashout) {
        checkState(isOpen(), SdkInfo.Literals.TICKET_HANDLER_SENDER_CLOSED);
        checkNotNull(cashout, SdkInfo.Literals.TICKET_HANDLER_TICKET_CASHOUT_NULL);

        checkNotNull(ticketCashoutResponseListener, "no response listener set");
        logger.trace(
                "PUBLISH cashout:{}, correlationId:{}",
                cashout.getTicketId(),
                cashout.getCorrelationId()
        );
        logger.trace("PUBLISH {}", cashout.getJsonValue());
        logger.debug("WS SEND cashout correlationId: {}", cashout.getCorrelationId()); // todo dmuren logging
        String msgString = cashout.getJsonValue();
        sdkLogger.logSendMessage(msgString);
        if (StringUtils.isNullOrEmpty(cashout.getCorrelationId())) {
            logger.warn("Ticket {} is missing correlationId", cashout.getTicketId());
        }
        return engine.execute(routingKey, cashout, cashout.getBookmakerId(), TicketCashoutResponse.class,
                        () -> ticketCashoutResponseListener.publishSuccess(cashout))
                .whenComplete((response, throwable) -> { // todo dmuren double check logic
                    if (throwable instanceof ProtocolTimeoutException) {
                        ticketCashoutResponseListener.onTicketResponseTimedOut(cashout);
                    } else if (throwable != null) {
                        ticketCashoutResponseListener.publishFailure(cashout);
                    } else if (response != null) {
                        ticketCashoutResponseReceived(response);
                    }
                });
    }

    @Override
    public void setListener(TicketCashoutResponseListener responseListener) {
        checkNotNull(responseListener, "response listener cannot be null");
        this.ticketCashoutResponseListener = responseListener;
    }

    @Override
    public void ticketCashoutResponseReceived(TicketCashoutResponse ticketCashoutResponse) {
        checkNotNull(ticketCashoutResponse, "TicketCashoutResponse cannot be null");
        sdkLogger.logReceivedMessage(JsonUtils.serializeAsString(ticketCashoutResponse));
        logger.debug("WS RECEIVED ticket correlationId: {}", ticketCashoutResponse.getCorrelationId()); // todo dmuren logging
        executorService.submit(() -> {
            try {
                ticketCashoutResponseListener.responseReceived(ticketCashoutResponse);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

}
