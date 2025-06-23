/*
 * Copyright (C) Sportradar AG. See LICENSE for full license governing this code
 */

package com.sportradar.mts.sdk.impl.libs.handlers;

import com.sportradar.mts.sdk.api.TicketReofferCancel;
import com.sportradar.mts.sdk.api.builders.BuilderFactory;
import com.sportradar.mts.sdk.api.impl.builders.TicketReofferCancelBuilderImpl;
import com.sportradar.mts.sdk.api.interfaces.TicketReofferCancelResponseListener;
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

public class TicketReofferCancelHandlerWsImplTest {

    private ProtocolEngine engine;
    private TicketReofferCancel ticketReofferCancel;
    private String routingKey;
    private TicketReofferCancelHandler reofferCancelSender;
    private SdkLogger sdkLogger;
    private BuilderFactory builderFactory;

    @Before
    public void setUp() {
        sdkLogger = mock(SdkLogger.class);
        builderFactory = new SdkHelper().getBuilderFactory();

        routingKey = "cancel.reoffer";
        engine = mock(ProtocolEngine.class);

        when(engine.executeNoResponse(any(), any(), anyInt(), any()))
                .thenReturn(CompletableFuture.completedFuture(null));

        reofferCancelSender = new TicketReofferCancelHandlerWsImpl(routingKey, sdkLogger, engine);
        ticketReofferCancel = getTicketReofferCancel();
        reofferCancelSender.setListener(mock(TicketReofferCancelResponseListener.class));
    }

    @Test
    public void newBuilderTest() {
        assertThat(builderFactory.createTicketReofferCancelBuilder(), is(notNullValue()));
        assertThat(builderFactory.createTicketReofferCancelBuilder(), instanceOf(TicketReofferCancelBuilderImpl.class));
    }

    @Test
    public void sendAsyncTest() {
        reofferCancelSender.open();
        reofferCancelSender.send(ticketReofferCancel);

        verify(engine).executeNoResponse(eq(routingKey), eq(ticketReofferCancel), eq(ticketReofferCancel.getBookmakerId()), any());

        String ticketString = JsonUtils.serializeAsString(MtsDtoMapper.map(ticketReofferCancel));
        verify(sdkLogger, times(1)).logSendMessage(ticketString);
    }


    private TicketReofferCancel getTicketReofferCancel() {
        return builderFactory.createTicketReofferCancelBuilder()
                .setTicketId(StaticRandom.S1000)
                .setBookmakerId(9985)
                .build();
    }
}
