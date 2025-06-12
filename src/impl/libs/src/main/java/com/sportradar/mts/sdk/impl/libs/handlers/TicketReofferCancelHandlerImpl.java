/*
 * Copyright (C) Sportradar AG. See LICENSE for full license governing this code
 */

package com.sportradar.mts.sdk.impl.libs.handlers;

import com.sportradar.mts.sdk.api.TicketReofferCancel;
import com.sportradar.mts.sdk.api.interfaces.TicketReofferCancelResponseListener;
import com.sportradar.mts.sdk.api.utils.SdkInfo;
import com.sportradar.mts.sdk.impl.libs.adapters.amqp.AmqpPublisher;
import com.sportradar.mts.sdk.impl.libs.logging.SdkLogger;

import java.util.concurrent.ExecutorService;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;

public class TicketReofferCancelHandlerImpl extends SenderBase<TicketReofferCancel> implements TicketReofferCancelHandler {

    private final String routingKey;

    public TicketReofferCancelHandlerImpl(AmqpPublisher amqpPublisher,
                                          String routingKey,
                                          ExecutorService executorService,
                                          double messagesPerSecond,
                                          SdkLogger sdkLogger) {
        super(amqpPublisher, executorService, messagesPerSecond, sdkLogger);
        this.routingKey = routingKey == null ? "cancel.reoffer" : routingKey;
    }

    @Override
    public void send(TicketReofferCancel ticketReofferCancel){
        checkState(isOpen(), SdkInfo.Literals.TICKET_HANDLER_SENDER_CLOSED);
        checkNotNull(ticketReofferCancel, SdkInfo.Literals.TICKET_HANDLER_TICKET_REOFFER_CANCEL_NULL);
        publishAsync(ticketReofferCancel, routingKey);
    }

    @Override
    public void setListener(TicketReofferCancelResponseListener responseListener) {
        setPublishListener(responseListener);
    }

    @Override
    protected String getSerializedDto(TicketReofferCancel message) {
        return message.getJsonValue();
    }
}
