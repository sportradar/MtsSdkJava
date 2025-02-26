package com.sportradar.mts.sdk.ws.internal.config;

import java.time.Duration;

public interface ProtocolHandlerConfig {

    long getOperatorId();

    Duration getProtocolConnectTimeout();

    int getProtocolMaxSendBufferSize();

    Duration getProtocolEnqueueTimeout();

    Duration getProtocolDequeueTimeout();

    Duration getProtocolReceiveResponseTimeout();

    int getProtocolRetryCount();

    int getProtocolNumberOfDispatchers();
}
