/*
 * Copyright (C) Sportradar AG. See LICENSE for full license governing this code
 */

package com.sportradar.mts.sdk.impl.libs.root;

import com.sportradar.mts.sdk.api.interfaces.*;
import com.sportradar.mts.sdk.impl.libs.LoggerTestAppender;
import com.sportradar.mts.sdk.impl.libs.TimeLimitedTestBase;
import com.sportradar.mts.sdk.impl.libs.adapters.amqp.AmqpMessageReceiver;
import com.sportradar.mts.sdk.impl.libs.adapters.amqp.ChannelFactoryProvider;
import com.sportradar.mts.sdk.impl.libs.handlers.*;
import com.sportradar.mts.sdk.impl.libs.logging.SdkLogger;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * @author andrej.resnik on 20/06/16 at 14:30
 */
@SuppressWarnings("FieldCanBeLocal")
public class SdkRootImplTest extends TimeLimitedTestBase {

    private SdkLogger sdkLogger;
    private ScheduledExecutorService executorService;
    private ChannelFactoryProvider channelFactoryProvider;
    private TicketHandler ticketHandler;
    private AmqpMessageReceiver ticketAmqpMessageReceiver;
    private TicketCancelHandler ticketCancelSender;
    private AmqpMessageReceiver ticketCancelAmqpMessageReceiver;
    private TicketAckHandler ticketAckHandler;
    private TicketCancelAckHandler ticketCancelAckHandler;
    private TicketReofferCancelHandler ticketReofferCancelHandler;
    private SdkRoot sdkRoot;
    private LoggerTestAppender appender;
    private TicketCashoutHandler ticketCashoutHandler;
    private AmqpMessageReceiver ticketCashoutAmqpMessageReceiver;
    private TicketNonSrSettleHandler ticketNonSrSettleHandler;
    private AmqpMessageReceiver ticketNonSrSettleAmpqMessageReceiver;

    @Before
    public void setUp() {
        sdkLogger = mock(SdkLogger.class);
        executorService = mock(ScheduledExecutorService.class);
        channelFactoryProvider = mock(ChannelFactoryProvider.class);
        ticketHandler = mock(TicketHandler.class);
        ticketAmqpMessageReceiver = mock(AmqpMessageReceiver.class);
        ticketCancelSender = mock(TicketCancelHandler.class);
        ticketCancelAmqpMessageReceiver = mock(AmqpMessageReceiver.class);
        ticketAckHandler = mock(TicketAckHandler.class);
        ticketCancelAckHandler = mock(TicketCancelAckHandler.class);
        ticketReofferCancelHandler = mock(TicketReofferCancelHandler.class);
        ticketCashoutHandler = mock(TicketCashoutHandler.class);
        ticketCashoutAmqpMessageReceiver = mock(AmqpMessageReceiver.class);
        ticketNonSrSettleHandler = mock(TicketNonSrSettleHandler.class);
        ticketNonSrSettleAmpqMessageReceiver = mock(AmqpMessageReceiver.class);

        sdkRoot = new SdkRootImpl(
                sdkLogger,
                executorService,
                channelFactoryProvider,
                ticketHandler,
                ticketAmqpMessageReceiver,
                ticketCancelSender,
                ticketCancelAmqpMessageReceiver,
                ticketAckHandler,
                ticketCancelAckHandler,
                ticketReofferCancelHandler,
                ticketCashoutHandler,
                ticketCashoutAmqpMessageReceiver,
                ticketNonSrSettleHandler,
                ticketNonSrSettleAmpqMessageReceiver);

        appender = new LoggerTestAppender(SdkRootImpl.class);
    }

    @Test
    public void open_OkTest() {
        InOrder inOrder = inOrder(
                sdkLogger,
                channelFactoryProvider,
                ticketAmqpMessageReceiver,
                ticketCancelAmqpMessageReceiver,
                ticketAckHandler,
                ticketHandler,
                ticketCancelSender,
                ticketCancelAckHandler);

        sdkRoot.open();

        inOrder.verify(sdkLogger, times(1)).open();
        inOrder.verify(channelFactoryProvider, times(1)).registerInstance();
        verifyNoMoreInteractions(
                sdkLogger,
                channelFactoryProvider,
                ticketAmqpMessageReceiver,
                ticketCancelAmqpMessageReceiver,
                ticketAckHandler,
                ticketHandler,
                ticketCancelSender,
                ticketCancelAckHandler);
        assertTrue(sdkRoot.isOpen());
    }

    @Test
    public void close_OnExecutorServiceAwaitTerminationFalseTest() throws InterruptedException {
        InOrder inOrder = inOrder(
                ticketHandler,
                ticketCancelSender,
                ticketAckHandler,
                ticketCancelAckHandler,
                ticketAmqpMessageReceiver,
                ticketCancelAmqpMessageReceiver,
                channelFactoryProvider,
                executorService,
                sdkLogger);

        when(executorService.awaitTermination(anyLong(), any(TimeUnit.class))).thenReturn(false);

        sdkRoot.close();

        inOrder.verify(ticketHandler, times(1)).close();
        inOrder.verify(ticketCancelSender, times(1)).close();
        inOrder.verify(ticketAckHandler, times(1)).close();
        inOrder.verify(ticketCancelAckHandler, times(1)).close();
        inOrder.verify(ticketAmqpMessageReceiver, times(1)).close();
        inOrder.verify(ticketCancelAmqpMessageReceiver, times(1)).close();
        inOrder.verify(channelFactoryProvider, times(1)).unregisterInstance();
        inOrder.verify(executorService, times(1)).shutdown();
        inOrder.verify(executorService, times(1)).awaitTermination(anyLong(), any(TimeUnit.class));
        inOrder.verify(executorService, times(1)).shutdownNow();
        inOrder.verify(sdkLogger, times(1)).close();
        verifyNoMoreInteractions(
                ticketHandler,
                ticketCancelSender,
                ticketAckHandler,
                ticketCancelAckHandler,
                ticketAmqpMessageReceiver,
                ticketCancelAmqpMessageReceiver,
                channelFactoryProvider,
                executorService,
                sdkLogger);
        assertFalse(sdkRoot.isOpen());
        assertTrue(appender.searchLoggingEventByFormattedMessage(
                "failed to shutdown executor service in time, force stopping"));
    }

    @Test
    public void openClose_OnExecutorServiceAwaitTerminationFalseTest() throws InterruptedException {
        InOrder inOrder = inOrder(
                ticketHandler,
                ticketCancelSender,
                ticketAckHandler,
                ticketCancelAckHandler,
                ticketAmqpMessageReceiver,
                ticketCancelAmqpMessageReceiver,
                channelFactoryProvider,
                executorService,
                sdkLogger);

        sdkRoot.open();

        inOrder.verify(sdkLogger, times(1)).open();
        inOrder.verify(channelFactoryProvider, times(1)).registerInstance();
        assertTrue(sdkRoot.isOpen());

        when(executorService.awaitTermination(anyLong(), any(TimeUnit.class))).thenReturn(false);

        sdkRoot.close();

        inOrder.verify(ticketHandler, times(1)).close();
        inOrder.verify(ticketCancelSender, times(1)).close();
        inOrder.verify(ticketAckHandler, times(1)).close();
        inOrder.verify(ticketCancelAckHandler, times(1)).close();
        inOrder.verify(ticketAmqpMessageReceiver, times(1)).close();
        inOrder.verify(ticketCancelAmqpMessageReceiver, times(1)).close();
        inOrder.verify(channelFactoryProvider, times(1)).unregisterInstance();
        inOrder.verify(executorService, times(1)).shutdown();
        inOrder.verify(executorService, times(1)).awaitTermination(anyLong(), any(TimeUnit.class));
        inOrder.verify(executorService, times(1)).shutdownNow();
        inOrder.verify(sdkLogger, times(1)).close();
        verifyNoMoreInteractions(
                ticketHandler,
                ticketCancelSender,
                ticketAckHandler,
                ticketCancelAckHandler,
                ticketAmqpMessageReceiver,
                ticketCancelAmqpMessageReceiver,
                channelFactoryProvider,
                executorService,
                sdkLogger);
        assertFalse(sdkRoot.isOpen());
        assertTrue(appender.searchLoggingEventByFormattedMessage(
                "failed to shutdown executor service in time, force stopping"));
    }

    @Test
    public void openClose_OnTicketSenderCloseExThrownTest() {
        doAnswer(invocation -> {
            throw new Exception("thrown on ticket sender close");
        })
                .when(ticketHandler).close();

        sdkRoot.open();

        assertTrue(sdkRoot.isOpen());

        sdkRoot.close();

        assertFalse(sdkRoot.isOpen());
        verify(ticketHandler, times(1)).close();
        appender.searchLoggingEventByFormattedMessage("failed to close ticket sender");
    }

    @Test
    public void openClose_OnTicketCancelSenderCloseExThrownTest() {
        doAnswer(invocation -> {
            throw new Exception("thrown on ticket cancel sender close");
        })
                .when(ticketCancelSender).close();

        sdkRoot.open();

        assertTrue(sdkRoot.isOpen());

        sdkRoot.close();

        assertFalse(sdkRoot.isOpen());
        verify(ticketCancelSender, times(1)).close();
        appender.searchLoggingEventByFormattedMessage("failed to close ticket cancel sender");
    }

    @Test
    public void openClose_OnTicketAcknowledgmentSenderCloseExThrownTest() {
        doAnswer(invocation -> {
            throw new Exception("thrown on ticket acknowledgment sender close");
        }).when(ticketAckHandler).close();

        sdkRoot.open();

        assertTrue(sdkRoot.isOpen());

        sdkRoot.close();

        assertFalse(sdkRoot.isOpen());
        verify(ticketAckHandler, times(1)).close();
        appender.searchLoggingEventByFormattedMessage("failed to close ticket acknowledgment sender");
    }

    @Test
    public void openClose_OnTicketCancelAcknowledgmentSenderCloseExThrownTest() {
        doAnswer(invocation -> {
            throw new Exception("thrown on ticket cancel acknowledgment sender close");
        }).when(ticketCancelAckHandler).close();

        sdkRoot.open();

        assertTrue(sdkRoot.isOpen());

        sdkRoot.close();

        assertFalse(sdkRoot.isOpen());
        verify(ticketCancelAckHandler, times(1)).close();
        appender.searchLoggingEventByFormattedMessage("failed to close ticket cancel acknowledgment sender");
    }

    @Test
    public void openClose_OnTicketAmqpMessageReceiverCloseExThrownTest() {
        doAnswer(invocation -> {
            throw new Exception("thrown on message receiver close");
        })
                .when(ticketAmqpMessageReceiver).close();

        sdkRoot.open();

        assertTrue(sdkRoot.isOpen());

        sdkRoot.close();

        assertFalse(sdkRoot.isOpen());
        verify(ticketAmqpMessageReceiver, times(1)).close();
        appender.searchLoggingEventByFormattedMessage("failed to close message receiver");
    }

    @Test
    public void openClose_OnTicketCancelAmqpMessageReceiverCloseExThrownTest() {
        doAnswer(invocation -> {
            throw new Exception("thrown on cancel message receiver close");
        })
                .when(ticketCancelAmqpMessageReceiver).close();

        sdkRoot.open();

        assertTrue(sdkRoot.isOpen());

        sdkRoot.close();

        assertFalse(sdkRoot.isOpen());
        verify(ticketCancelAmqpMessageReceiver, times(1)).close();
        appender.searchLoggingEventByFormattedMessage("failed to close message receiver");
    }

    @Test
    public void openClose_OnChannelFactoryProviderUnregisterInstanceExThrownTest() {
        doAnswer(invocation -> {
            throw new Exception("thrown on channel factory provider unregister instance");
        })
                .when(channelFactoryProvider).unregisterInstance();

        sdkRoot.open();

        assertTrue(sdkRoot.isOpen());
        verify(channelFactoryProvider, times(1)).registerInstance();

        sdkRoot.close();

        assertFalse(sdkRoot.isOpen());
        verify(channelFactoryProvider, times(1)).unregisterInstance();
        appender.searchLoggingEventByFormattedMessage("failed to unregister instance from channel factory provider");
    }

    @Test
    public void openClose_OnExecutorServiceAwaitTerminationInterruptedExThrownTest() throws InterruptedException {
        doAnswer(invocation -> {
            throw new InterruptedException("thrown on executor service await termination");
        })
                .when(executorService).awaitTermination(anyLong(), any(TimeUnit.class));

        sdkRoot.open();

        assertTrue(sdkRoot.isOpen());

        sdkRoot.close();

        assertFalse(sdkRoot.isOpen());
        verify(executorService, times(1)).shutdown();
        verify(executorService, times(1)).awaitTermination(anyLong(), any(TimeUnit.class));
        verify(executorService, times(1)).shutdownNow();
        appender.searchLoggingEventByFormattedMessage("interrupted while waiting for executor service to shutdown");
        appender.searchLoggingEventByFormattedMessage("failed to shutdown executor service in time, force stopping");
    }

    @Test
    public void openClose_OnSdkLoggerCloseExThrownTest() {
        doAnswer(invocation -> {
            throw new Exception("thrown on sdk logger close");
        })
                .when(sdkLogger).close();

        sdkRoot.open();

        assertTrue(sdkRoot.isOpen());
        verify(sdkLogger, times(1)).open();

        sdkRoot.close();

        assertFalse(sdkRoot.isOpen());
        verify(sdkLogger, times(1)).close();
        appender.searchLoggingEventByFormattedMessage("failed to close sdk logger");
    }

    @Test
    public void close_OkTest() throws InterruptedException {
        InOrder inOrder = inOrder(
                ticketHandler,
                ticketCancelSender,
                ticketAckHandler,
                ticketCancelAckHandler,
                ticketAmqpMessageReceiver,
                ticketCancelAmqpMessageReceiver,
                channelFactoryProvider,
                executorService,
                sdkLogger);

        when(executorService.awaitTermination(anyLong(), any(TimeUnit.class))).thenReturn(true);

        sdkRoot.close();

        inOrder.verify(ticketHandler, times(1)).close();
        inOrder.verify(ticketCancelSender, times(1)).close();
        inOrder.verify(ticketAckHandler, times(1)).close();
        inOrder.verify(ticketCancelAckHandler, times(1)).close();
        inOrder.verify(ticketAmqpMessageReceiver, times(1)).close();
        inOrder.verify(ticketCancelAmqpMessageReceiver, times(1)).close();
        inOrder.verify(channelFactoryProvider, times(1)).unregisterInstance();
        inOrder.verify(executorService, times(1)).shutdown();
        inOrder.verify(executorService, times(1)).awaitTermination(anyLong(), any(TimeUnit.class));
        inOrder.verify(sdkLogger, times(1)).close();
        verifyNoMoreInteractions(
                ticketHandler,
                ticketCancelSender,
                ticketAckHandler,
                ticketCancelAckHandler,
                ticketAmqpMessageReceiver,
                ticketCancelAmqpMessageReceiver,
                channelFactoryProvider,
                executorService,
                sdkLogger);
        assertFalse(sdkRoot.isOpen());
        assertFalse(appender.searchLoggingEventByFormattedMessage(
                "failed to shutdown executor service in time, force stopping"));
    }

    @Test
    public void getTicketSenderTest() {
        sdkRoot.open();
        TicketSender retrievedSender = sdkRoot.getTicketSender(mock(TicketResponseListener.class));

        assertEquals(ticketHandler, retrievedSender);
    }

    @Test
    public void getTicketSender_IllegalStateExThrownTest() {
        thrown.expect(IllegalStateException.class);
        thrown.expectMessage("not open yet, call open first");
        sdkRoot.getTicketSender(mock(TicketResponseListener.class));
    }

    @Test
    public void getTicketCancelSenderTest() {
        sdkRoot.open();
        TicketCancelSender retrievedCancelSender = sdkRoot.getTicketCancelSender(mock(TicketCancelResponseListener.class));

        assertEquals(ticketCancelSender, retrievedCancelSender);
    }

    @Test
    public void getTicketCancelSender_IllegalStateExThrownTest() {
        thrown.expect(IllegalStateException.class);
        thrown.expectMessage("not open yet, call open first");
        sdkRoot.getTicketCancelSender(mock(TicketCancelResponseListener.class));
    }

    @Test
    public void getTicketAcknowledgmentSenderTest() {
        sdkRoot.open();
        TicketAckSender retrievedTicketAckSender = sdkRoot.getTicketAcknowledgmentSender(mock(
                TicketAckResponseListener.class));

        assertEquals(ticketAckHandler, retrievedTicketAckSender);
    }

    @Test
    public void getTicketAcknowledgmentSender_IllegalStateExThrownTest() {
        thrown.expect(IllegalStateException.class);
        thrown.expectMessage("not open yet, call open first");
        sdkRoot.getTicketAcknowledgmentSender(mock(TicketAckResponseListener.class));
    }

    @Test
    public void getTicketCancelAcknowledgmentSenderTest() {
        sdkRoot.open();
        TicketCancelAckSender retrievedTicketCancelAckSender = sdkRoot.getTicketCancelAcknowledgmentSender(
                mock(TicketCancelAckResponseListener.class));

        assertEquals(ticketCancelAckHandler, retrievedTicketCancelAckSender);
    }

    @Test
    public void getTicketCancelAcknowledgmentSender_IllegalStateExThrownTest() {
        thrown.expect(IllegalStateException.class);
        thrown.expectMessage("not open yet, call open first");
        sdkRoot.getTicketCancelAcknowledgmentSender(mock(TicketCancelAckResponseListener.class));
    }
}
