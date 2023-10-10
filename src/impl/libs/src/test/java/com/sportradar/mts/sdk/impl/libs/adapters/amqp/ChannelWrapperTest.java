/*
 * Copyright (C) Sportradar AG. See LICENSE for full license governing this code
 */

package com.sportradar.mts.sdk.impl.libs.adapters.amqp;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConnectionFactory;
import com.sportradar.mts.sdk.api.impl.ConnectionStatusImpl;
import com.sportradar.mts.sdk.impl.libs.LoggerTestAppender;
import com.sportradar.mts.sdk.impl.libs.TimeLimitedTestBase;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.*;

/**
 * @author andrej.resnik on 02/08/16 at 14:52
 */
public class ChannelWrapperTest extends TimeLimitedTestBase {

    private Channel underlyingChannel;
    private ChannelWrapper channelWrapper;

    @Before
    public void setUp() {
        ChannelFactoryProviderImpl channelFactoryProvider = new ChannelFactoryProviderImpl(1, new ConnectionStatusImpl());
        ConnectionFactory connectionFactory = mock(ConnectionFactory.class);
        AmqpCluster mqCluster = AmqpCluster.from("username", "password", "vhost", false, new NetworkAddress("host"), 10);
        ConnectionWrapper parentConnection = new ConnectionWrapper(channelFactoryProvider, connectionFactory, mqCluster, new ConnectionStatusImpl());
        long index = 0L;
        underlyingChannel = mock(Channel.class);
        channelWrapper = new ChannelWrapper(parentConnection, index, underlyingChannel);
    }

    @Test
    public void getChannelTest() {
        assertThat(channelWrapper.getChannel(), is(notNullValue()));
    }

    @Test
    public void getIndexTest() {
        assertThat(channelWrapper.getIndex(), is(notNullValue()));
    }

    @Test
    public void close_OKTest() throws IOException, TimeoutException {
        assertThat(underlyingChannel, is(notNullValue()));
        channelWrapper.close();

        verify(underlyingChannel, times(1)).close();
    }

    @Test
    public void close_OnExceptionThrownTest() throws IOException, TimeoutException {
        assertThat(underlyingChannel, is(notNullValue()));
        doThrow(IOException.class).when(underlyingChannel).close();
        LoggerTestAppender appender = new LoggerTestAppender(ChannelWrapper.class);

        channelWrapper.close();

        boolean contains = appender.searchLoggingEventByFormattedMessage("Channel close error: ");
        assertTrue(contains);
    }
}
