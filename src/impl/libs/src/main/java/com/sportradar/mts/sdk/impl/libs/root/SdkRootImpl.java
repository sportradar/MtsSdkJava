/*
 * Copyright (C) Sportradar AG. See LICENSE for full license governing this code
 */

package com.sportradar.mts.sdk.impl.libs.root;

import com.sportradar.mts.sdk.api.interfaces.*;
import com.sportradar.mts.sdk.impl.libs.adapters.amqp.AmqpMessageReceiver;
import com.sportradar.mts.sdk.impl.libs.adapters.amqp.ChannelFactoryProvider;
import com.sportradar.mts.sdk.impl.libs.handlers.*;
import com.sportradar.mts.sdk.impl.libs.logging.SdkLogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static com.google.common.base.Preconditions.checkState;

public class SdkRootImpl implements SdkRoot {

    private static final Logger logger = LoggerFactory.getLogger(SdkRootImpl.class);
    private final Object stateLock = new Object();
    private boolean opened;
    private final ScheduledExecutorService executorService;
    private final SdkLogger sdkLogger;
    private final ChannelFactoryProvider channelFactoryProvider;
    private final TicketHandler ticketHandler;
    private final AmqpMessageReceiver ticketAmqpMessageReceiver;
    private final TicketCancelHandler ticketCancelHandler;
    private final AmqpMessageReceiver ticketCancelAmqpMessageReceiver;
    private final TicketAckHandler ticketAckHandler;
    private final TicketCancelAckHandler ticketCancelAckHandler;
    private final TicketReofferCancelHandler ticketReofferCancelHandler;
    private final TicketCashoutHandler ticketCashoutHandler;
    private final AmqpMessageReceiver ticketCashoutAmqpMessageReceiver;
    private final TicketNonSrSettleHandler ticketNonSrSettleHandler;
    private final AmqpMessageReceiver ticketNonSrSettleAmpqMessageReceiver;

    @SuppressWarnings("java:S107") // Methods should not have too many parameters
    public SdkRootImpl(SdkLogger sdkLogger,
                       ScheduledExecutorService executorService,
                       ChannelFactoryProvider channelFactoryProvider,
                       TicketHandler ticketHandler,
                       AmqpMessageReceiver ticketAmqpMessageReceiver,
                       TicketCancelHandler ticketCancelHandler,
                       AmqpMessageReceiver ticketCancelAmqpMessageReceiver,
                       TicketAckHandler ticketAckHandler,
                       TicketCancelAckHandler ticketCancelAckHandler,
                       TicketReofferCancelHandler ticketReofferCancelHandler,
                       TicketCashoutHandler ticketCashoutHandler,
                       AmqpMessageReceiver ticketCashoutAmqpMessageReceiver,
                       TicketNonSrSettleHandler ticketNonSrSettleHandler,
                       AmqpMessageReceiver ticketNonSrSettleAmpqMessageReceiver) {
        this.sdkLogger = sdkLogger;
        this.executorService = executorService;
        this.channelFactoryProvider = channelFactoryProvider;
        this.ticketHandler = ticketHandler;
        this.ticketAmqpMessageReceiver = ticketAmqpMessageReceiver;
        this.ticketCancelHandler = ticketCancelHandler;
        this.ticketCancelAmqpMessageReceiver = ticketCancelAmqpMessageReceiver;
        this.ticketAckHandler = ticketAckHandler;
        this.ticketCancelAckHandler = ticketCancelAckHandler;
        this.ticketReofferCancelHandler = ticketReofferCancelHandler;
        this.ticketCashoutHandler = ticketCashoutHandler;
        this.ticketCashoutAmqpMessageReceiver = ticketCashoutAmqpMessageReceiver;
        this.ticketNonSrSettleHandler = ticketNonSrSettleHandler;
        this.ticketNonSrSettleAmpqMessageReceiver = ticketNonSrSettleAmpqMessageReceiver;
    }

    @Override
    public void open() {
        synchronized (stateLock) {
            sdkLogger.open();
            channelFactoryProvider.registerInstance();
            opened = true;
        }
    }

    @Override
    public void close() {
        synchronized (stateLock) {
            try {
                ticketHandler.close();
            } catch (Exception e) {
                logger.error("failed to close ticket sender", e);
            }
            try {
                ticketCancelHandler.close();
            } catch (Exception e) {
                logger.error("failed to close ticket cancel sender", e);
            }
            try {
                ticketAckHandler.close();
            } catch (Exception e) {
                logger.error("failed to close ticket acknowledgment sender", e);
            }
            try {
                ticketCancelAckHandler.close();
            } catch (Exception e) {
                logger.error("failed to close ticket cancel acknowledgment sender", e);
            }
            try {
                ticketReofferCancelHandler.close();
            } catch (Exception e) {
                logger.error("failed to close reoffer ticket cancel sender", e);
            }
            try {
                ticketAmqpMessageReceiver.close();
            } catch (Exception e) {
                logger.error("failed to close message receiver", e);
            }
            try {
                ticketCancelAmqpMessageReceiver.close();
            } catch (Exception e) {
                logger.error("failed to close message receiver", e);
            }
            try {
                ticketCashoutAmqpMessageReceiver.close();
            } catch (Exception e) {
                logger.error("failed to close cashout message receiver", e);
            }
            try {
                ticketCashoutHandler.close();
            } catch (Exception e) {
                logger.error("failed to close ticket cashout sender", e);
            }
            try {
                ticketNonSrSettleAmpqMessageReceiver.close();
            } catch (Exception e) {
                logger.error("failed to close non-sr message receiver", e);
            }
            try {
                ticketNonSrSettleHandler.close();
            } catch (Exception e) {
                logger.error("failed to close ticket non-sr sender", e);
            }
            try {
                channelFactoryProvider.unregisterInstance();
            } catch (Exception e) {
                logger.error("failed to unregister instance from channel factory provider", e);
            }
            executorService.shutdown();
            boolean terminated = false;
            try {
                terminated = executorService.awaitTermination(20, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                logger.info("interrupted while waiting for executor service to shutdown");
                Thread.currentThread().interrupt();
            }
            if (!terminated) {
                logger.error("failed to shutdown executor service in time, force stopping");
                executorService.shutdownNow();
            }
            try {
                sdkLogger.close();
            } catch (Exception e) {
                logger.error("failed to close sdk logger", e);
            }
            opened = false;
        }
    }

    @Override
    public boolean isOpen() {
        return opened;
    }

    @Override
    public TicketSender getTicketSender(TicketResponseListener responseListener) {
        checkOpened();
        ticketHandler.setListener(responseListener);
        ticketHandler.open();
        if (ticketHandler instanceof TicketHandlerImpl) {
            ticketAmqpMessageReceiver.open();
        }
        return ticketHandler;
    }

    @Override
    public TicketCancelSender getTicketCancelSender(TicketCancelResponseListener responseListener) {
        checkOpened();
        ticketCancelHandler.setListener(responseListener);
        ticketCancelHandler.open();
        if (ticketCancelHandler instanceof TicketCancelHandlerImpl) {
            ticketCancelAmqpMessageReceiver.open();
        }
        return ticketCancelHandler;
    }

    @Override
    public TicketAckSender getTicketAcknowledgmentSender(TicketAckResponseListener responseListener) {
        checkOpened();
        ticketAckHandler.setListener(responseListener);
        ticketAckHandler.open();
        return ticketAckHandler;
    }

    @Override
    public TicketCancelAckSender getTicketCancelAcknowledgmentSender(TicketCancelAckResponseListener responseListener) {
        checkOpened();
        ticketCancelAckHandler.setListener(responseListener);
        ticketCancelAckHandler.open();
        return ticketCancelAckHandler;
    }

    @Override
    public TicketReofferCancelSender getTicketReofferCancelSender(TicketReofferCancelResponseListener responseListener) {
        checkOpened();
        ticketReofferCancelHandler.setListener(responseListener);
        ticketReofferCancelHandler.open();
        return ticketReofferCancelHandler;
    }

    @Override
    public TicketCashoutSender getTicketCashoutSender(TicketCashoutResponseListener responseListener) {
        checkOpened();
        ticketCashoutHandler.setListener(responseListener);
        ticketCashoutHandler.open();
        ticketCashoutAmqpMessageReceiver.open();
        return ticketCashoutHandler;
    }

    @Override
    public TicketNonSrSettleSender getTicketNonSrSettleSender(TicketNonSrSettleResponseListener responseListener) {
        checkOpened();
        ticketNonSrSettleHandler.setListener(responseListener);
        ticketNonSrSettleHandler.open();
        ticketNonSrSettleAmpqMessageReceiver.open();
        return ticketNonSrSettleHandler;
    }

    private void checkOpened() {
        checkState(opened, "not open yet, call open first");
    }
}
