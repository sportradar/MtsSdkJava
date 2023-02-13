/*
 * Copyright (C) Sportradar AG. See LICENSE for full license governing this code
 */

package com.sportradar.mts.sdk.impl.libs.adapters.amqp;

import com.google.common.base.Preconditions;
import com.rabbitmq.client.AlreadyClosedException;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ShutdownSignalException;
import com.sportradar.mts.sdk.api.exceptions.MtsSdkProcessException;
import com.sportradar.mts.sdk.api.interfaces.Openable;
import com.sportradar.mts.sdk.api.utils.StringUtils;
import com.sportradar.mts.sdk.impl.libs.threading.RecoverableThread;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.NoRouteToHostException;

import static com.google.common.base.Preconditions.checkArgument;

public abstract class RabbitMqBase implements Openable {

    private static final Logger logger = LoggerFactory.getLogger(RabbitMqBase.class);

    protected static final long WAIT_FOR_TASK_MILLIS = 3000L;

    private static final boolean DURABLE = true; // survive broker restart
    private static final boolean AUTO_DELETE = false; // the exchange will get deleted as soon as there are no more queues bound to it

    protected final ChannelFactory channelFactory;
    protected final String exchangeName;

    private final String instanceName;
    private final String exchangeType;
    private final int concurrencyLevel;
    private final ChannelFactoryProvider channelFactoryProvider;

    private volatile boolean isOpen = false;
    private RecoverableThread[] threads = null;

    private boolean isRegistered = false;

    @SuppressWarnings("java:S1199") // Nested code blocks should not be used
    protected RabbitMqBase(final ChannelFactoryProvider channelFactoryProvider,
                           String instanceName,
                           AmqpCluster mqCluster,
                           String exchangeName,
                           ExchangeType exchangeType,
                           int concurrencyLevel) {

        Preconditions.checkNotNull(channelFactoryProvider, "parameter 'channelFactoryProvider' is null");
        Preconditions.checkNotNull(mqCluster, "parameter 'mqCluster' is null");
        Preconditions.checkNotNull(exchangeName, "parameter 'exchangeName' is null");
        Preconditions.checkNotNull(exchangeType, "parameter 'exchangeType' is null");
        checkArgument(concurrencyLevel > 0, "parameter 'concurrencyLevel' is zero or less");

        switch (exchangeType) {
            case DIRECT:
            case FANOUT:
            case TOPIC: {
                this.exchangeType = exchangeType.name().toLowerCase();
                break;
            }
            default: {
                this.exchangeType = null;
                checkArgument(false, StringUtils.format("Invalid exchange type : {}", exchangeType));
            }
        }
        this.instanceName = ((instanceName == null || instanceName.trim().length() == 0) ? exchangeName : instanceName).trim();
        this.exchangeName = exchangeName;
        this.concurrencyLevel = concurrencyLevel;
        this.channelFactoryProvider = channelFactoryProvider;
        this.channelFactory = this.channelFactoryProvider.getChannelFactory(mqCluster);
    }

    @Override
    public synchronized void open() {
        if (!this.isOpen) {
            if (!this.isRegistered) {
                this.channelFactoryProvider.registerInstance();
                this.isRegistered = true;
            }

            try (final ChannelWrapper channelWrapper = this.channelFactory.getChannel()) {
                // try to declare the exchange if it is not the default one
                if (!this.exchangeName.equals("")) {
                    tryCreateExchange(channelWrapper);
                }
            } catch (Exception exc) {
                throw new MtsSdkProcessException(exc.getMessage(), exc);
            }

            final String threadPrefix = "rabbitmq-" + this.instanceName + "-thread-";
            this.threads = new RecoverableThread[this.concurrencyLevel];
            for (int i = 0; i < this.concurrencyLevel; i++) {
                final int threadId = (i + 1);
                this.threads[i] = new RecoverableThread(threadPrefix + threadId,
                                                        true,
                                                        new BackgroundWork(this, threadId));
            }

            this.isOpen = true;

            if (!this.isRegistered) {
                this.channelFactoryProvider.registerInstance();
                this.isRegistered = true;
            }

            for (int i = 0; i < this.concurrencyLevel; i++) {
                this.threads[i].open();
            }
        }
    }

    private void tryCreateExchange(ChannelWrapper channelWrapper) throws IOException {
        try {
            channelWrapper.getChannel().exchangeDeclare(this.exchangeName,
                                                        this.exchangeType,
                                                        DURABLE,
                                                        AUTO_DELETE,
                                                        null);
        } catch (IOException ioe) {
            logger.warn("Exchange {} creation failed, will try to recreate it", this.exchangeName);
            channelWrapper.getChannel().exchangeDelete(this.exchangeName);
            channelWrapper.getChannel().exchangeDeclare(this.exchangeName,
                                                        this.exchangeType,
                                                        DURABLE,
                                                        AUTO_DELETE,
                                                        null);
        }
    }

    @Override
    public synchronized void close() {
        if (this.isOpen) {
            this.isOpen = false;

            for (int i = 0; i < this.concurrencyLevel; i++) {
                this.threads[i].close();
            }

            if (this.isRegistered) {
                this.channelFactoryProvider.unregisterInstance();
                this.isRegistered = false;
            }
        }
    }

    @Override
    public boolean isOpen() {
        return isOpen;
    }

    protected final void execute(final Runnable command) {
        this.channelFactoryProvider.execute(command);
    }

    protected abstract void doWork(Channel channel, int threadId) throws InterruptedException, IOException;

    private static final class BackgroundWork implements Runnable {

        private final RabbitMqBase parent;
        private final int threadId;
        private long sleepMillis = 0L;

        public BackgroundWork(RabbitMqBase parent, int threadId) {
            this.parent = parent;
            this.threadId = threadId;
        }

        @Override
        public void run() {
            while (true) {
                if (!this.parent.isOpen) {
                    return;
                }

                if (this.sleepMillis > 0L) {
                    try {
                        logger.warn("sleepMillis={}", this.sleepMillis);
                        Thread.sleep(this.sleepMillis);
                    } catch (InterruptedException exc) {
                        Thread.currentThread().interrupt();
                        throw new MtsSdkProcessException(exc.getMessage(), exc);
                    }
                }

                try {
                    try (final ChannelWrapper channelWrapper = this.parent.channelFactory.getChannel()) {
                        this.sleepMillis = 0L;
                        this.parent.doWork(channelWrapper.getChannel(), this.threadId);
                    }

                } catch (IOException exc) {
                    this.sleepMillis = getSleepMillis(isConnectionException(exc), this.sleepMillis);
                    logger.error("Unexpected connection exception while doing background work; sleepMillis={}",
                                 this.sleepMillis,
                                 exc);
                } catch (ShutdownSignalException exc) {
                    this.sleepMillis = getSleepMillis(true, this.sleepMillis);
                    logger.warn("Unexpected connection exception while doing background work; sleepMillis={}",
                                 this.sleepMillis,
                                 exc);
                } catch (Exception e) {
                    logger.warn("unknown exception; unchanged sleepMillis={}", sleepMillis);
                    this.sleepMillis = 1000L;
                    Thread.currentThread().interrupt();
                }
            }
        }

        private static boolean isConnectionException(IOException e) {
            if (e.getCause() == null) {
                return e.getClass().equals(NoRouteToHostException.class);
            }

            return e.getCause().getClass().equals(ShutdownSignalException.class)
                    || e.getCause().getClass().equals(AlreadyClosedException.class)
                    || e.getCause().getClass().equals(NoRouteToHostException.class);
        }

        private static long getSleepMillis(boolean isConnectionException, long oldSleepMillis) {
            if (!isConnectionException) {
                return 1000L;
            }

            if (oldSleepMillis == 0L) {
                return 1000L;
            }

            if (oldSleepMillis < 64000L) {
                oldSleepMillis = oldSleepMillis << 1;
            }
            return oldSleepMillis;
        }
    }
}
