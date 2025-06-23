/*
 * Copyright (C) Sportradar AG. See LICENSE for full license governing this code
 */

package com.sportradar.mts.sdk.impl.libs.handlers;

import com.sportradar.mts.sdk.api.TicketAck;
import com.sportradar.mts.sdk.api.builders.BuilderFactory;
import com.sportradar.mts.sdk.api.enums.TicketAckStatus;
import com.sportradar.mts.sdk.api.impl.builders.TicketAckBuilderImpl;
import com.sportradar.mts.sdk.api.interfaces.TicketAckResponseListener;
import com.sportradar.mts.sdk.api.utils.JsonUtils;
import com.sportradar.mts.sdk.api.utils.MtsDtoMapper;
import com.sportradar.mts.sdk.api.utils.StaticRandom;
import com.sportradar.mts.sdk.impl.libs.SdkHelper;
import com.sportradar.mts.sdk.impl.libs.logging.SdkLogger;
import com.sportradar.mts.sdk.ws.internal.protocol.ProtocolEngine;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.CompletableFuture;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.any;

public class TicketAckHandlerWsImplTest {

    private TicketAck ticketAcknowledgment;
    private String routingKey;
    private TicketAckHandler ackSender;
    private SdkLogger sdkLogger;
    private BuilderFactory builderFactory;
    private ProtocolEngine engine;

    @Before
    public void setUp() {
        sdkLogger = mock(SdkLogger.class);
        builderFactory = new SdkHelper().getBuilderFactory();

        routingKey = "ack.ticket";
        engine = mock(ProtocolEngine.class);
        ticketAcknowledgment = getTicketAcknowledgment();

        when(engine.executeNoResponse(any(), any(), anyInt(), any()))
                .thenReturn(CompletableFuture.completedFuture(null));

        ackSender = new TicketAckHandlerWsImpl(routingKey, sdkLogger, engine);
        ackSender.setListener(mock(TicketAckResponseListener.class));
    }

    @Test
    public void newBuilderTest() {
        assertThat(builderFactory.createTicketAckBuilder(), is(notNullValue()));
        assertThat(builderFactory.createTicketAckBuilder(), instanceOf(TicketAckBuilderImpl.class));
    }

    @Test
    public void sendAsyncTest() {
        ackSender.open();
        ackSender.send(ticketAcknowledgment);

        verify(engine).executeNoResponse(eq(routingKey), eq(ticketAcknowledgment), eq(ticketAcknowledgment.getBookmakerId()), any());

        String ticketAckString = JsonUtils.serializeAsString(MtsDtoMapper.map(ticketAcknowledgment));
        verify(sdkLogger).logSendMessage(ticketAckString);
    }


    private TicketAck getTicketAcknowledgment() {
        return builderFactory.createTicketAckBuilder()
                .setTicketId(StaticRandom.S1000)
                .setAckStatus(TicketAckStatus.ACCEPTED)
                .setBookmakerId(9985)
                .setSourceCode(100)
                .build();
    }
}
