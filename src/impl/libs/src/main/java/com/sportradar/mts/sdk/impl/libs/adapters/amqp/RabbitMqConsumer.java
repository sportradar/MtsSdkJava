/*
 * Copyright (C) Sportradar AG. See LICENSE for full license governing this code
 */

package com.sportradar.mts.sdk.impl.libs.adapters.amqp;

import com.google.common.base.Preconditions;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.QueueingConsumer;
import com.sportradar.mts.sdk.api.utils.SdkInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.management.ManagementFactory;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.google.common.base.Preconditions.checkNotNull;

public final class RabbitMqConsumer extends RabbitMqBase implements AmqpConsumer {

    public static final String AMQP_HEADER_REPLY_ROUTING_KEY = "replyRoutingKey";
    private static final Logger logger = LoggerFactory.getLogger(RabbitMqConsumer.class);
    private AmqpMessageReceiver msgHandler;
    private final String queueName;
    private final String origRoutingKey;
    private final Set<String> routingKeys;
    private final int maxRetryCount;
    private final int prefetchCount;
    private final AmqpCluster amqpCluster;
    // survive broker restart
    private boolean durable = true;
    // there can only be one client for this specific queue : false
    private boolean exclusiveQueue = false;
    // there can only be one consumer for this specific queue : false
    private boolean exclusiveConsumer;
    // the exchange will get deleted as soon as there are no more queues bound to it
    private boolean autoDelete = false;
    // indicates if the sdk should manually ack (on rabbit) each received ticket
    private boolean autoMessageAcknowledgmentEnabled = true;

    @SuppressWarnings("java:S107") // Methods should not have too many parameters
    public RabbitMqConsumer(ChannelFactoryProvider channelFactoryProvider,
                            String routingKey,
                            String instanceName,
                            AmqpCluster mqCluster,
                            String exchangeName,
                            ExchangeType exchangeType,
                            String queueName,
                            int maxRetryCount,
                            int prefetchCount,
                            int concurrencyLevel,
                            boolean deleteQueueOnClose,
                            boolean exclusiveConsumer) {
        super(channelFactoryProvider,
              instanceName,
              mqCluster,
              exchangeName,
              exchangeType,
              concurrencyLevel);

        checkNotNull(queueName, "parameter 'queueName' is null");
        checkNotNull(routingKey, "parameter 'routingKey' is null");
        Preconditions.checkArgument(maxRetryCount > 0, "parameter 'maxRetryCount' is zero or less");
        Preconditions.checkArgument(prefetchCount > 0, "parameter 'prefetchCount' is zero or less");

        this.queueName = queueName;
        this.origRoutingKey = routingKey;
        this.routingKeys = getRoutingKeys(routingKey);
        this.maxRetryCount = maxRetryCount;
        this.prefetchCount = prefetchCount;
        this.amqpCluster = mqCluster;
        this.exclusiveConsumer = exclusiveConsumer;
    }

    @Override
    protected void doWork(Channel channel, int threadId) throws InterruptedException, IOException {

        Map<String, Object> arguments = new HashMap<>();
        arguments.putIfAbsent("x-queue-master-locator", "min-masters");

        channel.basicQos(this.prefetchCount);

        channel.queueDeclare(this.queueName, durable, exclusiveQueue, autoDelete, arguments);

        for (final String key : this.routingKeys) {
            channel.queueBind(this.queueName, this.exchangeName, key, null);
        }

        Format formatter = new SimpleDateFormat("yyyyMMddHHmm");
        long upTime = getSystemUptime();
        String consumerTag = String.format("tag_%s|JAVA|%s|%s|%s|%s", amqpCluster.getEnvironment(), SdkInfo.getVersion(), formatter.format(new Date()), upTime, getPID());

//        java.lang.String queue, boolean noAck, java.lang.String consumerTag, boolean noLocal, boolean exclusive, Consumer callback
//        queue - the name of the queue
//        noAck - true if no handshake is required
//        consumerTag - a client-generated consumer tag to establish context
//        noLocal - flag set to true unless server local buffering is required
//        exclusive - true if this is an exclusive consumer
//        callback - an interface to the consumer object
        QueueingConsumer consumer = new QueueingConsumer(channel);
        String consumerName = channel.basicConsume(this.queueName, autoMessageAcknowledgmentEnabled, consumerTag, false, exclusiveConsumer, null, consumer);

        final int throwSafetyDisconnectExcLimit = (this.prefetchCount * 2);
        final int maxCountOfLocalMsgs = Math.max(1, this.prefetchCount >> 1);
        int throwSafetyDisconnectExcCount = 0;
        int retryQueueSize = 0;
        Queue<DeliveryWrapper> retryQueue = new LinkedList<>();

        while (this.isOpen()) {
            if (Thread.interrupted()) {
                throw new InterruptedException("thread has been interrupted while actively working");
            }

            if (throwSafetyDisconnectExcCount > throwSafetyDisconnectExcLimit) {
                logger.warn("Safety sleep!!! [exName={}, qName={}, rKey={}]",
                            this.exchangeName, this.queueName, this.origRoutingKey);
                Thread.sleep(32000L);
                throwSafetyDisconnectExcCount = 0;
            }

            int retryCount = 0;

            QueueingConsumer.Delivery delivery = consumer.nextDelivery(0L);
            if (delivery == null) {
                if (retryQueueSize > 0) {
                    DeliveryWrapper tmp = retryQueue.poll();
                    delivery = tmp.delivery;
                    retryCount = tmp.retryCount;
                    retryQueueSize--;
                } else {
                    delivery = consumer.nextDelivery(WAIT_FOR_TASK_MILLIS);
                }
            }
            if (delivery == null) {
                continue;
            }

            if(logger.isTraceEnabled()) {
                logger.trace("CONSUME START: consumer={} tId={} received msg with routingKey={}, exchange={} and deliveryTag={}",
                             consumerName,
                             threadId,
                             delivery.getEnvelope().getRoutingKey(),
                             delivery.getEnvelope().getExchange(),
                             delivery.getEnvelope().getDeliveryTag());
                String msg = new String(delivery.getBody());
                logger.trace("CONSUME Message: {}", msg);
                logger.trace("CONSUME Properties: {}", delivery.getProperties());
            }
            MessageStatus messageStatus;
            try {
                messageStatus = this.msgHandler.consume(
                        delivery.getBody(),
                        delivery.getEnvelope().getRoutingKey(),
                        delivery.getProperties().getCorrelationId(),
                        convertHeaders(delivery.getProperties().getHeaders()));
            } catch (Exception exc) {
                logger.error("Exception thrown by consumer (setting result to 'RetryLimited'): ", exc);
                messageStatus = MessageStatus.RETRY_LIMITED;
            }

            if (MessageStatus.CONSUMED_SUCCESSFULLY.equals(messageStatus)) {
                logger.trace("CONSUME END: consumer={} tId={} received result={}",
                             consumerName,
                             threadId,
                             messageStatus);
            } else {
                logger.debug("END: consumer={} tId={} received result={}",
                             consumerName,
                             threadId,
                             messageStatus);
            }

            if(autoMessageAcknowledgmentEnabled){
                continue;
            }
            /*
             * Msg handled
             */
            if (MessageStatus.CONSUMED_SUCCESSFULLY.equals(messageStatus)) {
                channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
                throwSafetyDisconnectExcCount = 0;
                logger.trace("consumer={} tId={} acked msg={}",
                             consumerName,
                             threadId,
                             delivery.getEnvelope().getDeliveryTag());
                continue;
            }

                /*
                 * Msg should be retried forever
                 */
            if (MessageStatus.RETRY_FOREVER.equals(messageStatus)) {
                while (maxCountOfLocalMsgs <= retryQueueSize) {
                    DeliveryWrapper tmp = retryQueue.poll();
                    retryQueueSize--;
                    channel.basicNack(tmp.delivery.getEnvelope().getDeliveryTag(), false, true);
                    throwSafetyDisconnectExcCount++;
                }
                DeliveryWrapper dw = new DeliveryWrapper();
                dw.delivery = delivery;
                dw.retryCount = retryCount;
                retryQueue.add(dw);
                retryQueueSize++;
                continue;
            }

            /*
             * Msg should be retried limited times
             */
            retryCount++;
            if (retryCount >= this.maxRetryCount) {
                logger.error(
                        "msg={}, content={} did not get consumed, retry limit reached={}, dropping msg!!! [exName={}, qName={}, rKey={}]",
                        delivery.getEnvelope().getDeliveryTag(),
                        new String(delivery.getBody()),
                        this.maxRetryCount,
                        this.exchangeName,
                        this.queueName,
                        this.origRoutingKey);
                channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
                try {
                    this.msgHandler.afterLimitReached(delivery.getBody(),
                                                      delivery.getEnvelope().getRoutingKey(),
                                                      delivery.getProperties().getCorrelationId());
                } catch (Exception exc) {
                    logger.error("Exception thrown by consumer after limit reached: ", exc);
                }
            } else {
                while (maxCountOfLocalMsgs <= retryQueueSize) {
                    DeliveryWrapper tmp = retryQueue.poll();
                    retryQueueSize--;
                    channel.basicNack(tmp.delivery.getEnvelope().getDeliveryTag(), false, true);
                    throwSafetyDisconnectExcCount++;
                }
                DeliveryWrapper dw = new DeliveryWrapper();
                dw.delivery = delivery;
                dw.retryCount = retryCount;
                retryQueue.add(dw);
                retryQueueSize++;
            }
        }
    }

    @Override
    public void setMessageReceivedHandler(AmqpMessageReceiver msgHandler) {
        checkNotNull(msgHandler, "checkNotNull cannot be null");
        this.msgHandler = msgHandler;
    }

    private static Set<String> getRoutingKeys(final String input) {
        final String[] keys = input.split(";");
        final Set<String> result = new HashSet<>();
        for (final String key : keys) {
            result.add(key.trim());
        }
        return result;
    }

    private Map<String, Object> convertHeaders(Map<String, Object> headers) {
        Map<String, Object> converted = new HashMap<>();

        if (headers == null || headers.isEmpty()) {
            return converted;
        }

        // convert LongStringHelper$ByteArrayLongString to String
        if (headers.containsKey(AMQP_HEADER_REPLY_ROUTING_KEY)) {
            converted.put(AMQP_HEADER_REPLY_ROUTING_KEY, headers.get(AMQP_HEADER_REPLY_ROUTING_KEY).toString());
            headers.remove(AMQP_HEADER_REPLY_ROUTING_KEY);
        }
        // convert other headers as needed here
        // ...

        // any remaining headers pass through
        converted.putAll(headers);

        return converted;
    }

    private static final class DeliveryWrapper {

        public QueueingConsumer.Delivery delivery;
        public int retryCount;
    }

    public static long getPID()
    {
        try
        {
            String processName = java.lang.management.ManagementFactory.getRuntimeMXBean().getName();
            if (processName != null && processName.length() > 0)
            {
                return Long.parseLong(processName.split("@")[0]);
            }
        }
        catch (Exception e)
        {
            //ignored
        }
        return 0;
    }

    public static long getSystemUptime() {
        long uptime = -1;
        try {
            String os = System.getProperty("os.name").toLowerCase();
            if (os.contains("win")) {
                Process uptimeProc = Runtime.getRuntime().exec("net stats srv");
                BufferedReader in = new BufferedReader(new InputStreamReader(uptimeProc.getInputStream()));
                String line;
                while ((line = in.readLine()) != null) {
                    if (line.startsWith("Statistics since")) {
                        line  = line.replace("Statistics since ", "").trim();
                        SimpleDateFormat format = new SimpleDateFormat();
                        Date bootTime = format.parse(line);
                        uptime = bootTime.getTime()/1000;
                        break;
                    }
                }
            }
            else if (os.contains("mac") || os.contains("nix") || os.contains("nux") || os.contains("aix")) {
                Process uptimeProc = Runtime.getRuntime().exec("uptime");
                BufferedReader in = new BufferedReader(new InputStreamReader(uptimeProc.getInputStream()));
                String line = in.readLine();
                if (line != null) {
                    Pattern parse = Pattern.compile("((\\d+) days,)? (\\d+):(\\d+)");
                    Matcher matcher = parse.matcher(line);
                    if (matcher.find()) {
                        String daysStr = matcher.group(2);
                        String hoursStr = matcher.group(3);
                        String minutesStr = matcher.group(4);
                        int days = daysStr != null ? Integer.parseInt(daysStr) : 0;
                        int hours = hoursStr != null ? Integer.parseInt(hoursStr) : 0;
                        int minutes = minutesStr != null ? Integer.parseInt(minutesStr) : 0;
                        uptime = (minutes * 60000L) + (hours * 60000 * 60L) + (days * 6000 * 60 * 24L);
                    }
                }
            }
        }
        catch(Exception ex)
        {
            try
            {
                uptime = ManagementFactory.getRuntimeMXBean().getUptime();
            }
            catch(Exception ex2)
            {
                //ignored
            }
        }
        return uptime;
    }
}