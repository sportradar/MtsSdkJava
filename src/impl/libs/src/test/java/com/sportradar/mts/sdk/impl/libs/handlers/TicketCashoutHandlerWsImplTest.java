/*
 * Copyright (C) Sportradar AG. See LICENSE for full license governing this code
 */

package com.sportradar.mts.sdk.impl.libs.handlers;

import com.sportradar.mts.sdk.api.TicketCashout;
import com.sportradar.mts.sdk.api.builders.BuilderFactory;
import com.sportradar.mts.sdk.api.builders.TicketCashoutBuilder;
import com.sportradar.mts.sdk.api.interfaces.TicketCashoutResponseListener;
import com.sportradar.mts.sdk.api.utils.SdkInfo;
import com.sportradar.mts.sdk.api.utils.StaticRandom;
import com.sportradar.mts.sdk.impl.libs.SdkHelper;
import com.sportradar.mts.sdk.impl.libs.TimeLimitedTestBase;
import com.sportradar.mts.sdk.impl.libs.logging.SdkLogger;
import com.sportradar.mts.sdk.ws.internal.protocol.ProtocolEngine;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.ScheduledExecutorService;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.any;

/**
 * Created on 12/05/2017.
 */
public class TicketCashoutHandlerWsImplTest extends TimeLimitedTestBase {

    private ProtocolEngine engine;
    private TicketCashoutResponseListener listener;
    private String routingKey;
    private TicketCashoutHandler handler;
    private TicketCashout ticketCashout;
    private BuilderFactory builderFactory;

    @Before
    public void setUp() {
        ScheduledExecutorService executor = mock(ScheduledExecutorService.class);
        engine = mock(ProtocolEngine.class);
        SdkLogger sdkLogger = mock(SdkLogger.class);
        listener = mock(TicketCashoutResponseListener.class);
        builderFactory = new SdkHelper().getBuilderFactory();

        routingKey = "ticket.cashout";
        handler = new TicketCashoutHandlerWsImpl(routingKey, sdkLogger, engine, executor);
        ticketCashout = getTicketCashout("ticket-" + StaticRandom.S1000, 1111, 60);
    }

    @Test
    public void setListener_OnNullValuePassedTest() {
        thrown.expect(NullPointerException.class);
        thrown.expectMessage("response listener cannot be null");

        handler.setListener(null);
    }

    @Test
    public void newBuilderTest() {
        assertThat(builderFactory.createTicketCashoutBuilder(), is(notNullValue()));
        assertThat(builderFactory.createTicketCashoutBuilder(), instanceOf(TicketCashoutBuilder.class));
    }

    @Test
    public void send_OnTicketCashoutNullTest() {
        thrown.expect(NullPointerException.class);
        thrown.expectMessage(SdkInfo.Literals.TICKET_HANDLER_TICKET_CASHOUT_NULL);

        handler.open();
        handler.send(null);
    }

    @Test
    public void send_OnExtTicketIdNullTest() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("TicketId not valid");

        ticketCashout = getTicketCashout(null, 1234, 80);

        handler.setListener(listener);
        handler.open();
        handler.send(ticketCashout);
    }

    @Test
    public void send_OnMissingListener() {
        thrown.expect(NullPointerException.class);
        thrown.expectMessage("no response listener set");
        handler.open();
        handler.send(ticketCashout);
    }

    @Test
    public void ticketCashoutResponseReceived_ResponseNullTest() {
        thrown.expect(NullPointerException.class);
        thrown.expectMessage("TicketCashoutResponse cannot be null");

        handler.setListener(listener);

        doAnswer(invocation -> {
            handler.ticketCashoutResponseReceived(null);
            return null;
        }).when(engine).execute(anyString(), any(), anyInt(), any(), any());

        handler.open();
        handler.send(ticketCashout);
    }

    private TicketCashout getTicketCashout(String ticketId, int bookmakerId, int cashoutStake) {
        return builderFactory.createTicketCashoutBuilder().
                setTicketId(ticketId).
                setBookmakerId(bookmakerId).
                setCashoutStake(cashoutStake).build();
    }
}
