/*
 * Copyright (C) Sportradar AG. See LICENSE for full license governing this code
 */

package com.sportradar.mts.sdk.impl.libs.handlers;

import com.sportradar.mts.sdk.api.TicketAck;
import com.sportradar.mts.sdk.api.interfaces.TicketAckResponseListener;
import com.sportradar.mts.sdk.api.utils.SdkInfo;
import com.sportradar.mts.sdk.impl.libs.adapters.amqp.AmqpPublisher;
import com.sportradar.mts.sdk.impl.libs.logging.SdkLogger;

import java.util.concurrent.ExecutorService;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;

public class TicketAckHandlerImpl extends SenderBase<TicketAck> implements TicketAckHandler {

    private final String routingKey;

    public TicketAckHandlerImpl(AmqpPublisher amqpPublisher,
                                String routingKey,
                                ExecutorService executorService,
                                double messagesPerSecond,
                                SdkLogger sdkLogger) {
        super(amqpPublisher, executorService, messagesPerSecond, sdkLogger);
        this.routingKey = routingKey == null ? "ack.ticket" : routingKey;
    }

    @Override
    public void send(TicketAck ticketAcknowledgment) {
        checkState(isOpen(), SdkInfo.Literals.TICKET_HANDLER_SENDER_CLOSED);
        checkNotNull(ticketAcknowledgment, SdkInfo.Literals.TICKET_HANDLER_TICKET_ACK_NULL);
        publishAsync(ticketAcknowledgment, routingKey);
    }

    @Override
    public void setListener(TicketAckResponseListener responseListener) {
        setPublishListener(responseListener);
    }

    @Override
    protected String getSerializedDto(TicketAck message){
        return message.getJsonValue();
    }
}
