/*
 * Copyright (C) Sportradar AG. See LICENSE for full license governing this code
 */

package com.sportradar.mts.sdk.impl.libs.handlers;

import com.sportradar.mts.sdk.api.Ticket;
import com.sportradar.mts.sdk.api.builders.BuilderFactory;
import com.sportradar.mts.sdk.api.enums.OddsChangeType;
import com.sportradar.mts.sdk.api.enums.SenderChannel;
import com.sportradar.mts.sdk.api.enums.StakeType;
import com.sportradar.mts.sdk.api.exceptions.ResponseTimeoutException;
import com.sportradar.mts.sdk.api.impl.builders.TicketBuilderImpl;
import com.sportradar.mts.sdk.api.interfaces.TicketResponseListener;
import com.sportradar.mts.sdk.api.utils.JsonUtils;
import com.sportradar.mts.sdk.api.utils.MtsDtoMapper;
import com.sportradar.mts.sdk.api.utils.SdkInfo;
import com.sportradar.mts.sdk.impl.libs.LoggerTestAppender;
import com.sportradar.mts.sdk.impl.libs.SdkHelper;
import com.sportradar.mts.sdk.impl.libs.TimeLimitedTestBase;
import com.sportradar.mts.sdk.impl.libs.adapters.amqp.AmqpPublisher;
import com.sportradar.mts.sdk.impl.libs.logging.SdkLogger;
import com.sportradar.mts.sdk.impl.libs.receivers.TicketResponseWrapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.Semaphore;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

/**
 * @author andrej.resnik on 16/06/16 at 10:03
 */
public class TicketHandlerImplTest extends TimeLimitedTestBase {

    private AmqpPublisher publisher;
    private ScheduledExecutorService executor;
    private TicketResponseListener listener;
    private TicketHandler handler;
    private SdkLogger sdkLogger;
    private String routingKey;
    private Ticket ticket;
    private static int count;
    private BuilderFactory builderFactory;
    private ResponseTimeoutHandler<Ticket> responseTimeoutHandler;

    @Before
    public void setUp() {
        publisher = mock(AmqpPublisher.class);
        executor = mock(ScheduledExecutorService.class);
        sdkLogger = mock(SdkLogger.class);
        listener = mock(TicketResponseListener.class);
        responseTimeoutHandler = mock(ResponseTimeoutHandler.class);
        builderFactory = new SdkHelper().getBuilderFactory();

        routingKey = "ticket";
        handler = new TicketHandlerImpl(publisher, routingKey, executor, responseTimeoutHandler, 50, 150, 40, sdkLogger);
        handler.setListener(listener);
        ticket = getTicket();
        count = 0;
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
        thrown.expect(ResponseTimeoutException.class);
        thrown.expectMessage("Timeout reached.");

        handler.open();
        handler.sendBlocking(ticket);
    }

    //TODO: @Test
    public void sendBlocking_RateLimiterTest() {
        TicketResponseWrapper response = new TicketResponseWrapper();
        response.setTicketId(ticket.getTicketId());
        byte[] msg = JsonUtils.serialize(ticket);
        String correlationId = getFormattedCorrelationId(ticket);

        handler = new TicketHandlerImpl(publisher, routingKey, executor, responseTimeoutHandler, 50, 150, 5, sdkLogger);
        handler.setListener(listener);

        Map<Integer, Long> invocationTimestampsActual = new HashMap<>();

        doAnswer(invocation -> {
            invocationTimestampsActual.put(count, System.currentTimeMillis());
            handler.ticketResponseReceived(response);
            return null;
        }).when(publisher).publishAsync(ticket.getTicketId(), msg, correlationId, routingKey, routingKey);

        Map<Integer, Long> invocationTimestampsInitial = new HashMap<>();
        handler.open();

        for (int i = 0; i < 10; i++) {
            count++;
            invocationTimestampsInitial.put(count, System.currentTimeMillis());
            handler.send(ticket);
        }

        verify(publisher, times(10)).publishAsync(ticket.getTicketId(), msg, correlationId, routingKey, routingKey);

        List<Boolean> invocationResults = new ArrayList<>();
//        invocationTimestampsInitial.entrySet().forEach(entry -> System.out.println(invocationTimestampsActual.get(entry.getKey()) - entry.getValue()));

        invocationTimestampsInitial.forEach((key, value) -> {
//            System.out.println("Request no." + entry.getKey() + ":");
            if (key < 6 && (invocationTimestampsActual.get(key) - invocationTimestampsInitial.get(1) <= 1010)) {
//                System.out.println(invocationTimestampsActual.get(entry.getKey()) - invocationTimestampsInitial.get(1));
                invocationResults.add(true);
            } else if (key >= 6 && (invocationTimestampsActual.get(key) - invocationTimestampsInitial.get(6) <= 1010)) {
//                System.out.println(invocationTimestampsActual.get(entry.getKey()) - invocationTimestampsInitial.get(6));
                invocationResults.add(true);
            } else {
                invocationResults.add(false);
            }
        });

        invocationResults.forEach(Assert::assertTrue);
    }

    //TODO: @Test
    public void sendBlockingTest() throws ResponseTimeoutException {
        TicketResponseWrapper response = new TicketResponseWrapper();
        response.setTicketId(ticket.getTicketId());
        byte[] msg = JsonUtils.serialize(ticket);
        String correlationId = getFormattedCorrelationId(ticket);

        doAnswer(invocation -> {
            handler.ticketResponseReceived(response);
            return null;
        }).when(publisher).publishAsync(ticket.getTicketId(), msg, correlationId, routingKey, routingKey);

        handler.open();
        handler.sendBlocking(ticket);

        verify(publisher, times(1)).publishAsync(ticket.getTicketId(), msg, correlationId, routingKey, routingKey);
        assertThat(response, is(notNullValue()));

        String ticketString = JsonUtils.serializeAsString(ticket);
        verify(sdkLogger, times(1)).logSendMessage(ticketString);
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

        handler = new TicketHandlerImpl(publisher, routingKey, executor, responseTimeoutHandler, 50, 150, 40, sdkLogger);
        TicketResponseWrapper response = new TicketResponseWrapper();
        response.setTicketId(ticket.getTicketId());

        thrown.expect(NullPointerException.class);
        thrown.expectMessage("no response listener set");

        handler.open();
        handler.send(ticket);
    }

    //TODO: @Test
    public void sendTest() {
        TicketResponseWrapper response = new TicketResponseWrapper();
        response.setTicketId(ticket.getTicketId());
        byte[] msg = JsonUtils.serialize(ticket);
        String correlationId = getFormattedCorrelationId(ticket);

        when(publisher.isOpen()).thenReturn(true);
        doAnswer(invocation -> {
            handler.ticketResponseReceived(response);
            return null;
        }).when(publisher).publishAsync(ticket.getTicketId(), msg, correlationId, routingKey, routingKey);

        handler.open();
        handler.send(ticket);

        verify(publisher, times(1)).publishAsync(ticket.getTicketId(), JsonUtils.serialize(ticket), correlationId, routingKey,
                                                 routingKey);

        String ticketString = JsonUtils.serializeAsString(ticket);
        verify(sdkLogger, times(1)).logSendMessage(ticketString);
    }

    //TODO: @Test
    public void ticketResponseReceived_ResponseNullTest() throws ResponseTimeoutException {
        thrown.expect(NullPointerException.class);
        thrown.expectMessage("ticketResponse cannot be null");

        byte[] msg = JsonUtils.serialize(MtsDtoMapper.map(ticket));
        String correlationId = getFormattedCorrelationId(ticket);

        when(publisher.isOpen()).thenReturn(true);
        doAnswer(invocation -> {
            handler.ticketResponseReceived(null);
            return null;
        }).when(publisher).publishAsync(ticket.getTicketId(), msg, correlationId, routingKey, routingKey);

        handler.open();
        handler.sendBlocking(ticket);
    }

    //TODO: @Test
    public void ticketResponseReceivedTest() throws InterruptedException {
        TicketResponseWrapper response = new TicketResponseWrapper();
        response.setTicketId(ticket.getTicketId());
        byte[] msg = JsonUtils.serialize(ticket);
        String correlationId = getFormattedCorrelationId(ticket);

        when(publisher.isOpen()).thenReturn(true);
        doAnswer(invocation -> {
            handler.ticketResponseReceived(response);
            return null;
        }).when(publisher).publishAsync(ticket.getTicketId(), msg, correlationId, routingKey, routingKey);

        Semaphore semaphore = new Semaphore(0);
        when(executor.submit(Mockito.any(Runnable.class))).then(invocation -> {
            new Thread((Runnable) invocation.getArguments()[0]).run();
            semaphore.release();
            return null;
        });

        handler.open();
        handler.send(ticket);

        semaphore.acquire();
        verify(listener, times(1)).responseReceived(response);

        String responseString = JsonUtils.serializeAsString(response);
        verify(sdkLogger, times(1)).logReceivedMessage(responseString);
    }

    @Test
    public void close_OnResponsesPendingTest() throws InterruptedException {
        TicketResponseWrapper response = new TicketResponseWrapper();
        response.setTicketId(ticket.getTicketId());

        when(publisher.isOpen()).thenReturn(true);

        LoggerTestAppender appender = new LoggerTestAppender(TicketHandlerImpl.class, loggingEvent -> {
            if (loggingEvent.getFormattedMessage().contains(
                    "there are still ticket responses pending, will wait till completion or timeout")) {
                handler.ticketResponseReceived(response);
            }
        });

        handler = new TicketHandlerImpl(publisher, routingKey, executor, responseTimeoutHandler, 300, 350, 40, sdkLogger);
        handler.setListener(listener);
        handler.open();
        new Thread(() -> {
            try {
                handler.sendBlocking(ticket);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

        Thread.sleep(100);
        handler.close();
        boolean contains = appender.searchLoggingEventByFormattedMessage(
                "there are still ticket responses pending, will wait till completion or timeout");
        assertTrue(contains);
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

    private String getFormattedCorrelationId(Ticket ticket) {
        return "ticket:" + ticket.getTicketId();
    }
}
