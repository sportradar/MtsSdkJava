/*
 * Copyright (C) Sportradar AG. See LICENSE for full license governing this code
 */

package com.sportradar.mts.sdk.impl.libs.handlers;

import com.sportradar.mts.sdk.api.TicketReofferCancel;
import com.sportradar.mts.sdk.api.interfaces.TicketReofferCancelResponseListener;
import com.sportradar.mts.sdk.api.utils.SdkInfo;
import com.sportradar.mts.sdk.api.utils.StringUtils;
import com.sportradar.mts.sdk.impl.libs.logging.SdkLogger;
import com.sportradar.mts.sdk.ws.internal.protocol.ProtocolEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;

public class TicketReofferCancelHandlerWsImpl implements TicketReofferCancelHandler {

    private final String routingKey;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final SdkLogger sdkLogger;

    private final ProtocolEngine engine;
    private TicketReofferCancelResponseListener ticketReofferCancelResponseListener;

    private final Object stateLock = new Object();
    private boolean opened;

    public TicketReofferCancelHandlerWsImpl(
            String routingKey,
            SdkLogger sdkLogger,
            ProtocolEngine engine) {
        this.routingKey = routingKey == null ? "cancel.reoffer" : routingKey;
        this.sdkLogger = sdkLogger;
        this.engine = engine;
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
    public void send(TicketReofferCancel ticketReofferCancel) {
        sendAsync(ticketReofferCancel);
    }

    private void sendAsync(TicketReofferCancel reofferCancel) {
        checkState(isOpen(), SdkInfo.Literals.TICKET_HANDLER_SENDER_CLOSED);
        checkNotNull(reofferCancel, SdkInfo.Literals.TICKET_HANDLER_TICKET_REOFFER_CANCEL_NULL);

        checkNotNull(ticketReofferCancelResponseListener, "no response listener set");
        logger.trace(
                "PUBLISH cashout:{}, correlationId:{}",
                reofferCancel.getTicketId(),
                reofferCancel.getCorrelationId()
        );
        logger.trace("PUBLISH {}", reofferCancel.getJsonValue());
        sdkLogger.logSendMessage(reofferCancel.getJsonValue());
        if (StringUtils.isNullOrEmpty(reofferCancel.getCorrelationId())) {
            logger.warn("Ticket {} is missing correlationId", reofferCancel.getTicketId());
        }
        engine.executeNoResponse(routingKey, reofferCancel, reofferCancel.getBookmakerId(),
                        () -> ticketReofferCancelResponseListener.publishSuccess(reofferCancel))
                .whenComplete((response, throwable) -> {
                    if (throwable != null) {
                        ticketReofferCancelResponseListener.publishFailure(reofferCancel);
                    }
                });
    }

    @Override
    public void setListener(TicketReofferCancelResponseListener responseListener) {
        checkNotNull(responseListener, "response listener cannot be null");
        ticketReofferCancelResponseListener = responseListener;
    }
}
