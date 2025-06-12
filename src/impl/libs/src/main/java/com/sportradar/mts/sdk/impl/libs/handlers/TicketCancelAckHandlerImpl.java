/*
 * Copyright (C) Sportradar AG. See LICENSE for full license governing this code
 */

package com.sportradar.mts.sdk.impl.libs.handlers;

import com.sportradar.mts.sdk.api.TicketCancelAck;
import com.sportradar.mts.sdk.api.interfaces.TicketCancelAckResponseListener;
import com.sportradar.mts.sdk.api.utils.SdkInfo;
import com.sportradar.mts.sdk.impl.libs.adapters.amqp.AmqpPublisher;
import com.sportradar.mts.sdk.impl.libs.logging.SdkLogger;

import java.util.concurrent.ExecutorService;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;

public class TicketCancelAckHandlerImpl extends SenderBase<TicketCancelAck> implements TicketCancelAckHandler {

    private final String routingKey;

    public TicketCancelAckHandlerImpl(AmqpPublisher amqpPublisher,
                                      String routingKey,
                                      ExecutorService executorService,
                                      double messagesPerSecond,
                                      SdkLogger sdkLogger) {
        super(amqpPublisher, executorService, messagesPerSecond, sdkLogger);
        this.routingKey = routingKey == null ? "ack.cancel" : routingKey;
    }

    @Override
    public void send(TicketCancelAck ticketCancelAck) {
        checkState(isOpen(), SdkInfo.Literals.TICKET_HANDLER_SENDER_CLOSED);
        checkNotNull(ticketCancelAck, SdkInfo.Literals.TICKET_HANDLER_TICKET_CANCEL_ACK_NULL);
        publishAsync(ticketCancelAck, routingKey);
    }

    @Override
    public void setListener(TicketCancelAckResponseListener responseListener) {
        setPublishListener(responseListener);
    }

    @Override
    protected String getSerializedDto(TicketCancelAck message) {
        return message.getJsonValue();
    }
}