/*
 * Copyright (C) Sportradar AG. See LICENSE for full license governing this code
 */

package com.sportradar.mts.sdk.impl.libs.handlers;

import com.sportradar.mts.sdk.api.Ticket;
import com.sportradar.mts.sdk.api.TicketResponse;
import com.sportradar.mts.sdk.api.builders.BuilderFactory;
import com.sportradar.mts.sdk.api.enums.OddsChangeType;
import com.sportradar.mts.sdk.api.enums.SenderChannel;
import com.sportradar.mts.sdk.api.enums.StakeType;
import com.sportradar.mts.sdk.api.exceptions.ResponseTimeoutException;
import com.sportradar.mts.sdk.api.impl.builders.TicketBuilderImpl;
import com.sportradar.mts.sdk.api.interfaces.TicketResponseListener;
import com.sportradar.mts.sdk.api.utils.JsonUtils;
import com.sportradar.mts.sdk.api.utils.SdkInfo;
import com.sportradar.mts.sdk.impl.libs.SdkHelper;
import com.sportradar.mts.sdk.impl.libs.TimeLimitedTestBase;
import com.sportradar.mts.sdk.impl.libs.logging.SdkLogger;
import com.sportradar.mts.sdk.impl.libs.receivers.TicketResponseWrapper;
import com.sportradar.mts.sdk.ws.exceptions.ProtocolTimeoutException;
import com.sportradar.mts.sdk.ws.internal.protocol.ProtocolEngine;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.ScheduledExecutorService;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.any;

/**
 * @author andrej.resnik on 16/06/16 at 10:03
 */
public class TicketHandlerWsImplTest extends TimeLimitedTestBase {

    private ProtocolEngine engine;
    private ScheduledExecutorService executor;
    private TicketHandlerWsImpl handler;
    private SdkLogger sdkLogger;
    private String routingKey;
    private Ticket ticket;
    private BuilderFactory builderFactory;

    @Before
    public void setUp() {
        engine = mock(ProtocolEngine.class);
        executor = mock(ScheduledExecutorService.class);
        sdkLogger = mock(SdkLogger.class);
        builderFactory = new SdkHelper().getBuilderFactory();

        routingKey = "ticket";
        handler = new TicketHandlerWsImpl(routingKey, sdkLogger, engine, executor);
        handler.setListener(mock(TicketResponseListener.class));
        ticket = getTicket();
    }

    @Test
    public void setListener_OnNullValuePassedTest() {
        thrown.expect(NullPointerException.class);
        thrown.expectMessage("responseListener cannot be null");

        handler.setListener(null);
    }

    @Test
    public void newBuilderTest() {
        assertThat(builderFactory.createTicketBuilder(), is(notNullValue()));
        assertThat(builderFactory.createTicketBuilder(), instanceOf(TicketBuilderImpl.class));
    }

    @Test
    public void sendBlocking_OnTicketNullTest() throws ResponseTimeoutException {
        thrown.expect(NullPointerException.class);
        thrown.expectMessage(SdkInfo.Literals.TICKET_HANDLER_TICKET_NULL);

        handler.open();
        handler.sendBlocking(null);
    }

    @Test
    public void sendBlocking_OnExtTicketIdNullTest() throws ResponseTimeoutException {
        thrown.expect(NullPointerException.class);

        ticket = getTicketWithNullExtTicketID();

        handler.open();
        handler.sendBlocking(ticket);
    }

    @Test
    public void sendBlocking_OnResponseTimeoutExceptionTest() throws ResponseTimeoutException {
        when(engine.execute(anyString(), any(), anyInt(), any(), any()))
                .thenReturn(CompletableFuture.failedFuture(new ProtocolTimeoutException()));

        thrown.expect(CompletionException.class);
        thrown.expectMessage("Response not received in configured time window");

        handler.open();
        handler.sendBlocking(ticket);
    }

    @Test
    public void sendBlockingTest() throws ResponseTimeoutException {
        TicketResponseWrapper response = new TicketResponseWrapper();
        response.setTicketId(ticket.getTicketId());

        doAnswer(invocation -> {
            handler.ticketResponseReceived(response);
            return CompletableFuture.completedFuture(response);
        }).when(engine).execute(anyString(), any(), anyInt(), any(), any());

        handler.open();
        TicketResponse ticketResponse = handler.sendBlocking(ticket);

        verify(engine).execute(eq(routingKey), eq(ticket), eq(ticket.getSender().getBookmakerId()), eq(TicketResponse.class), any());
        assertThat(ticketResponse, is(notNullValue()));

        verify(sdkLogger).logSendMessage(ticket.getJsonValue());
    }

    @Test
    public void send_OnTicketNullTest() {
        thrown.expect(NullPointerException.class);
        thrown.expectMessage(SdkInfo.Literals.TICKET_HANDLER_TICKET_NULL);

        handler.open();
        handler.send(null);
    }

    @Test
    public void send_OnExtTicketIdNullTest() {
        thrown.expect(NullPointerException.class);

        ticket = getTicketWithNullExtTicketID();

        handler.open();
        handler.send(ticket);
    }

    @Test
    public void send_OnListenerNullTest() {

        handler = new TicketHandlerWsImpl(routingKey, sdkLogger, engine, executor);
        TicketResponseWrapper response = new TicketResponseWrapper();
        response.setTicketId(ticket.getTicketId());

        thrown.expect(NullPointerException.class);
        thrown.expectMessage("no response listener set");

        handler.open();
        handler.send(ticket);
    }

    @Test
    public void sendTest() {
        TicketResponseWrapper response = new TicketResponseWrapper();
        response.setTicketId(ticket.getTicketId());

        when(engine.execute(anyString(), any(), anyInt(), eq(TicketResponse.class), any()))
                .thenReturn(CompletableFuture.completedFuture(response));

        handler.open();
        handler.send(ticket);

        verify(engine).execute(eq(routingKey), eq(ticket), eq(ticket.getSender().getBookmakerId()), eq(TicketResponse.class), any());

        verify(sdkLogger).logSendMessage(ticket.getJsonValue());
        verify(sdkLogger).logReceivedMessage(JsonUtils.serializeAsString(response));
    }

    private Ticket getTicket() {
        return builderFactory.createTicketBuilder()
                .setTicketId("CukNorris" + System.currentTimeMillis())
                .setOddsChange(OddsChangeType.ANY)
                .setSender(builderFactory.createSenderBuilder()
                            .setBookmakerId(9985)
                            .setCurrency("EUR")
                            .setLimitId(93)
                            .setSenderChannel(SenderChannel.INTERNET)
                            .setEndCustomer("10.10.10.1", "User" + System.currentTimeMillis(), "en", "MyDeviceId", 12000L).build())
                .addBet(builderFactory.createBetBuilder()
                        .setBetId("BetId-" + System.currentTimeMillis())
                        .setStake(50000, StakeType.TOTAL)
                        .setSumOfWins(102020L)
                        .addSelectedSystem(1)
                        .addSelection(builderFactory.createSelectionBuilder()
                            .setId("lcoo:10/1/*/1")
                            .setOdds(11000)
                            .setBanker(true)
                            .setEventId("9034519")
                            .build())
                        .build())
                .build();
    }

    private Ticket getTicketWithNullExtTicketID() {
        return builderFactory.createTicketBuilder()
                .setOddsChange(OddsChangeType.ANY)
                .setSender(builderFactory.createSenderBuilder()
                        .setBookmakerId(9985)
                        .setCurrency("EUR")
                        .setShopId("Shop 12")
                        .setSenderChannel(SenderChannel.RETAIL)
                        .setLimitId(1000)
                        .build())
                .addBet(builderFactory.createBetBuilder()
                        .setBetId("BetId-" + System.currentTimeMillis())
                        .setStake(50000, StakeType.TOTAL)
                        .setSumOfWins(102020L)
                        .addSelectedSystem(1)
                        .addSelection(builderFactory.createSelectionBuilder()
                                .setId("lcoo:10/1/*/1")
                                .setOdds(11000)
                                .setBanker(true)
                                .setEventId("9034519")
                                .build())
                        .build())
                .build();
    }
}
