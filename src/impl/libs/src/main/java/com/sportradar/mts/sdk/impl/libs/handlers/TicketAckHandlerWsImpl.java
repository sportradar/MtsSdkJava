/*
 * Copyright (C) Sportradar AG. See LICENSE for full license governing this code
 */

package com.sportradar.mts.sdk.impl.libs.handlers;

import com.sportradar.mts.sdk.api.TicketAck;
import com.sportradar.mts.sdk.api.interfaces.TicketAckResponseListener;
import com.sportradar.mts.sdk.api.utils.SdkInfo;
import com.sportradar.mts.sdk.api.utils.StringUtils;
import com.sportradar.mts.sdk.impl.libs.logging.SdkLogger;
import com.sportradar.mts.sdk.ws.internal.protocol.ProtocolEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ScheduledExecutorService;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;

public class TicketAckHandlerWsImpl implements TicketAckHandler {

    private final String routingKey;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final SdkLogger sdkLogger;

    private final ProtocolEngine engine;
    private final ScheduledExecutorService executorService;
    private TicketAckResponseListener ticketAckResponseListener;

    private final Object stateLock = new Object();
    private boolean opened;

    public TicketAckHandlerWsImpl(
            String routingKey,
            SdkLogger sdkLogger,
            ProtocolEngine engine,
            ScheduledExecutorService executorService) {
        this.routingKey = routingKey == null ? "ack.ticket" : routingKey;
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

    @Override
    public void send(TicketAck ticketAcknowledgment) {
        sendAsync(ticketAcknowledgment);
    }

    private void sendAsync(TicketAck ticketAck) {
        checkState(isOpen(), SdkInfo.Literals.TICKET_HANDLER_SENDER_CLOSED);
        checkNotNull(ticketAck, SdkInfo.Literals.TICKET_HANDLER_TICKET_ACK_NULL);

        checkNotNull(ticketAckResponseListener, "no response listener set");
        logger.trace(
                "PUBLISH cashout:{}, correlationId:{}",
                ticketAck.getTicketId(),
                ticketAck.getCorrelationId()
        );
        logger.trace("PUBLISH {}", ticketAck.getJsonValue());
        logger.debug("WS SEND cashout correlationId: {}", ticketAck.getCorrelationId()); // todo dmuren logging
        String msgString = ticketAck.getJsonValue();
        sdkLogger.logSendMessage(msgString);
        if (StringUtils.isNullOrEmpty(ticketAck.getCorrelationId())) {
            logger.warn("Ticket {} is missing correlationId", ticketAck.getTicketId());
        }
        engine.executeNoResponse(routingKey, ticketAck, ticketAck.getBookmakerId(),
                        () -> ticketAckResponseListener.publishSuccess(ticketAck))
                .whenComplete((response, throwable) -> { // todo dmuren double check logic
                    if (throwable != null) {
                        ticketAckResponseListener.publishFailure(ticketAck);
                    }
                });
    }

    @Override
    public void setListener(TicketAckResponseListener responseListener) {
        checkNotNull(responseListener, "response listener cannot be null");
        ticketAckResponseListener = responseListener;
    }
}
