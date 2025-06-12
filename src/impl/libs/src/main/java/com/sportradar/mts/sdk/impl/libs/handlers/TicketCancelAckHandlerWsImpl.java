/*
 * Copyright (C) Sportradar AG. See LICENSE for full license governing this code
 */

package com.sportradar.mts.sdk.impl.libs.handlers;

import com.sportradar.mts.sdk.api.TicketCancelAck;
import com.sportradar.mts.sdk.api.interfaces.TicketCancelAckResponseListener;
import com.sportradar.mts.sdk.api.utils.SdkInfo;
import com.sportradar.mts.sdk.api.utils.StringUtils;
import com.sportradar.mts.sdk.impl.libs.logging.SdkLogger;
import com.sportradar.mts.sdk.ws.internal.protocol.ProtocolEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ScheduledExecutorService;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;

public class TicketCancelAckHandlerWsImpl implements TicketCancelAckHandler {

    private final String routingKey;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final SdkLogger sdkLogger;

    private final ProtocolEngine engine;
    private final ScheduledExecutorService executorService;
    private TicketCancelAckResponseListener ticketCancelAckResponseListener;

    private final Object stateLock = new Object();
    private boolean opened;

    public TicketCancelAckHandlerWsImpl(
            String routingKey,
            SdkLogger sdkLogger,
            ProtocolEngine engine,
            ScheduledExecutorService executorService) {
        this.routingKey = routingKey == null ? "ack.cancel" : routingKey;
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
    public void send(TicketCancelAck ticketCancelAck) {
        sendAsync(ticketCancelAck);
    }

    private void sendAsync(TicketCancelAck cancelAck) {
        checkState(isOpen(), SdkInfo.Literals.TICKET_HANDLER_SENDER_CLOSED);
        checkNotNull(cancelAck, SdkInfo.Literals.TICKET_HANDLER_TICKET_CANCEL_ACK_NULL);

        checkNotNull(ticketCancelAckResponseListener, "no response listener set");
        logger.trace(
                "PUBLISH cashout:{}, correlationId:{}",
                cancelAck.getTicketId(),
                cancelAck.getCorrelationId()
        );
        logger.trace("PUBLISH {}", cancelAck.getJsonValue());
        logger.debug("WS SEND cashout correlationId: {}", cancelAck.getCorrelationId()); // todo dmuren logging
        String msgString = cancelAck.getJsonValue();
        sdkLogger.logSendMessage(msgString);
        if (StringUtils.isNullOrEmpty(cancelAck.getCorrelationId())) {
            logger.warn("Ticket {} is missing correlationId", cancelAck.getTicketId());
        }
        engine.execute(routingKey, cancelAck, TicketCancelAck.class,
                        () -> ticketCancelAckResponseListener.publishSuccess(cancelAck))
                .whenComplete((response, throwable) -> { // todo dmuren double check logic
                    if (throwable != null) {
                        ticketCancelAckResponseListener.publishFailure(cancelAck);
                    }
                });
    }

    @Override
    public void setListener(TicketCancelAckResponseListener responseListener) {
        checkNotNull(responseListener, "response listener cannot be null");
        ticketCancelAckResponseListener = responseListener;
    }
}
