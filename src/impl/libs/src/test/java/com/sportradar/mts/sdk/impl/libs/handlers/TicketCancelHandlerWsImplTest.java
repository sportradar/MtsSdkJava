/*
 * Copyright (C) Sportradar AG. See LICENSE for full license governing this code
 */

package com.sportradar.mts.sdk.impl.libs.handlers;

import com.sportradar.mts.sdk.api.TicketCancel;
import com.sportradar.mts.sdk.api.TicketCancelResponse;
import com.sportradar.mts.sdk.api.builders.BuilderFactory;
import com.sportradar.mts.sdk.api.enums.TicketCancellationReason;
import com.sportradar.mts.sdk.api.impl.builders.TicketCancelBuilderImpl;
import com.sportradar.mts.sdk.api.interfaces.TicketCancelResponseListener;
import com.sportradar.mts.sdk.api.utils.JsonUtils;
import com.sportradar.mts.sdk.api.utils.SdkInfo;
import com.sportradar.mts.sdk.impl.libs.SdkHelper;
import com.sportradar.mts.sdk.impl.libs.TimeLimitedTestBase;
import com.sportradar.mts.sdk.impl.libs.logging.SdkLogger;
import com.sportradar.mts.sdk.impl.libs.receivers.TicketCancelResponseWrapper;
import com.sportradar.mts.sdk.ws.internal.protocol.ProtocolEngine;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.Semaphore;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.any;

/**
 * @author andrej.resnik on 16/06/16 at 10:02
 */
@SuppressWarnings("SameParameterValue")
public class TicketCancelHandlerWsImplTest extends TimeLimitedTestBase {

    private ScheduledExecutorService executor;
    private ProtocolEngine engine;
    private SdkLogger sdkLogger;
    private TicketCancelResponseListener listener;
    private TicketCancelHandler handler;
    private TicketCancel ticketCancel;
    private String routingKey;
    private BuilderFactory builderFactory;

    @Before
    public void setUp() {
        executor = mock(ScheduledExecutorService.class);
        engine = mock(ProtocolEngine.class);
        sdkLogger = mock(SdkLogger.class);
        listener = mock(TicketCancelResponseListener.class);
        builderFactory = new SdkHelper().getBuilderFactory();

        routingKey = "cancel";
        handler = new TicketCancelHandlerWsImpl( routingKey, engine, executor, sdkLogger);
        ticketCancel = getTicketCancel();
    }

    @Test
    public void setListener_OnNullValuePassedTest() {
        thrown.expect(NullPointerException.class);
        thrown.expectMessage("response listener cannot be null");

        handler.setListener(null);
    }

    @Test
    public void newBuilderTest() {
        assertThat(builderFactory.createTicketCancelBuilder(), is(notNullValue()));
        assertThat(builderFactory.createTicketCancelBuilder(), instanceOf(TicketCancelBuilderImpl.class));
    }

    @Test
    public void send_OnTicketCancelNullTest() {
        thrown.expect(NullPointerException.class);
        thrown.expectMessage(SdkInfo.Literals.TICKET_HANDLER_TICKET_CANCEL_NULL);

        handler.open();
        handler.send(null);
    }

    @Test
    public void send_OnExtTicketIdNullTest() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("TicketId not valid");

        ticketCancel = buildTicketCancel(9985, null, TicketCancellationReason.CustomerTriggeredPrematch);

        handler.setListener(listener);
        handler.open();
        handler.send(ticketCancel);
    }

    //TODO: @Test
    public void sendTest() {
        TicketCancelResponseWrapper response = new TicketCancelResponseWrapper(ticketCancel.getTicketId());
        response.setExtTicket(ticketCancel.getTicketId());
        byte[] msg = JsonUtils.serialize(ticketCancel);
        String correlationId = getFormattedCorrelationId(ticketCancel);

        doAnswer(invocation -> {
            handler.ticketCancelResponseReceived(response);
            return null;
        }).when(engine).execute(any(), any(), anyInt(), any(), any());

        handler.setListener(listener);
        handler.open();
        handler.send(ticketCancel);

        verify(engine).execute(eq(routingKey), eq(ticketCancel), eq(ticketCancel.getBookmakerId()), eq(TicketCancelResponse.class), any());
        assertThat(response, is(notNullValue()));

        String ticketCancelString = JsonUtils.serializeAsString(ticketCancel);
        verify(sdkLogger, times(1)).logSendMessage(ticketCancelString);
    }

    @Test
    public void ticketCancelResponseReceived_ResponseNullTest() {
        thrown.expect(NullPointerException.class);
        thrown.expectMessage("no response listener set");

        doAnswer(invocation -> {
            handler.ticketCancelResponseReceived(null);
            return null;
        }).when(engine).execute(any(), any(), anyInt(), any(), any());

        handler.open();
        handler.send(ticketCancel);
    }

    @Test
    public void ticketCancelResponseReceivedTest() throws InterruptedException {
        TicketCancelResponseWrapper response = new TicketCancelResponseWrapper(ticketCancel.getTicketId());
        response.setExtTicket(ticketCancel.getTicketId());

        when(engine.execute(any(), any(), anyInt(), any(), any()))
                .thenReturn(CompletableFuture.completedFuture(response));

        Semaphore semaphore = new Semaphore(0);
        when(executor.submit(any(Runnable.class))).then(invocation -> {
            new Thread((Runnable) invocation.getArguments()[0]).run();
            semaphore.release();
            return null;
        });

        handler.open();
        handler.setListener(listener);
        handler.send(ticketCancel);

        semaphore.acquire();
        verify(listener, times(1)).responseReceived(response);

        String responseString = JsonUtils.serializeAsString(response);
        verify(sdkLogger, times(1)).logReceivedMessage(responseString);
    }


    public TicketCancel getTicketCancel() {
        return builderFactory.createTicketCancelBuilder()
                .setBookmakerId(9985)
                .setTicketId("ticket-id-to-cancel")
                .setCode(TicketCancellationReason.CustomerTriggeredPrematch)
                .build();
    }

    private TicketCancel buildTicketCancel(
            Integer bookmakerId,
            String extTicket,
            TicketCancellationReason cancellationReason) {

        return builderFactory.createTicketCancelBuilder()
                .setBookmakerId(bookmakerId)
                .setTicketId(extTicket)
                .setCode(cancellationReason)
                .build();
    }

    private String getFormattedCorrelationId(TicketCancel ticketCancel) {
        return "ticket_cancel:" + ticketCancel.getTicketId();
    }
}
