/*
 * Copyright (C) Sportradar AG. See LICENSE for full license governing this code
 */

package com.sportradar.mts.sdk.impl.libs.handlers;

import com.sportradar.mts.sdk.api.TicketCancelAck;
import com.sportradar.mts.sdk.api.builders.BuilderFactory;
import com.sportradar.mts.sdk.api.enums.TicketCancelAckStatus;
import com.sportradar.mts.sdk.api.impl.builders.TicketCancelAckBuilderImpl;
import com.sportradar.mts.sdk.api.interfaces.TicketCancelAckResponseListener;
import com.sportradar.mts.sdk.api.utils.JsonUtils;
import com.sportradar.mts.sdk.api.utils.MtsDtoMapper;
import com.sportradar.mts.sdk.api.utils.StaticRandom;
import com.sportradar.mts.sdk.impl.libs.SdkHelper;
import com.sportradar.mts.sdk.impl.libs.TimeLimitedTestBase;
import com.sportradar.mts.sdk.impl.libs.logging.SdkLogger;
import com.sportradar.mts.sdk.ws.internal.protocol.ProtocolEngine;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.CompletableFuture;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.any;

/**
 * @author andrej.resnik on 09/06/16 at 15:07
 */
public class TicketCancelAckHandlerWsImplTest extends TimeLimitedTestBase {

    private TicketCancelAck cancelAck;
    private SdkLogger sdkLogger;
    private String routingKey;
    private TicketCancelAckHandler cancelAckSender;
    private BuilderFactory builderFactory;
    private ProtocolEngine engine;

    @Before
    public void setUp() {
        sdkLogger = mock(SdkLogger.class);
        builderFactory = new SdkHelper().getBuilderFactory();

        routingKey = "ack.cancel";
        engine = mock(ProtocolEngine.class);
        cancelAck = getTicketCancelAcknowledgment();

        when(engine.executeNoResponse(any(), any(), anyInt(), any()))
                .thenReturn(CompletableFuture.completedFuture(null));

        cancelAckSender = new TicketCancelAckHandlerWsImpl(routingKey, sdkLogger, engine);
        cancelAckSender.setListener(mock(TicketCancelAckResponseListener.class));
    }

    @Test
    public void newBuilderTest() {
        assertThat(builderFactory.createTicketCancelAckBuilder(), is(notNullValue()));
        assertThat(builderFactory.createTicketCancelAckBuilder(), instanceOf(TicketCancelAckBuilderImpl.class));
    }

    @Test
    public void sendAsyncTest() {
        cancelAckSender.open();
        cancelAckSender.send(cancelAck);

        verify(engine).executeNoResponse(eq(routingKey), eq(cancelAck), eq(cancelAck.getBookmakerId()), any());

        String ticketCancelAckString = JsonUtils.serializeAsString(MtsDtoMapper.map(cancelAck));
        verify(sdkLogger, times(1)).logSendMessage(ticketCancelAckString);
    }

    private TicketCancelAck getTicketCancelAcknowledgment() {
        return builderFactory.createTicketCancelAckBuilder()
                .setTicketId(StaticRandom.S1000)
                .setAckStatus(TicketCancelAckStatus.CANCELLED)
                .setBookmakerId(9985)
                .setSourceCode(100)
                .build();
    }
}
