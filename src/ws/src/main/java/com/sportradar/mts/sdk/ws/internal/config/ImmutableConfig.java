package com.sportradar.mts.sdk.ws.internal.config;

import com.sportradar.mts.sdk.ws.MbsSdkConfig;

import java.net.URI;
import java.time.Duration;

import static com.sportradar.mts.sdk.ws.internal.utils.Extensions.notNull;
import static com.sportradar.mts.sdk.ws.internal.utils.Extensions.withDefault;

public class ImmutableConfig implements
        TokenProviderConfig,
        WebSocketConnectionConfig,
        ConnectionProviderConfig,
        ProtocolHandlerConfig {

    private static final Duration MIN_DURATION = Duration.ofMillis(1);

    private final URI authServer;
    private final URI wsServer;
    private final String authClientId;
    private final String authClientSecret;
    private final String authAudience;
    private final Duration authRequestTimeout;
    private final Duration authRetryDelay;
    private final long operatorId;
    private final Duration protocolConnectTimeout;
    private final int protocolMaxSendBufferSize;
    private final Duration protocolEnqueueTimeout;
    private final Duration protocolDequeueTimeout;
    private final Duration protocolReceiveResponseTimeout;
    private final int protocolRetryCount;
    private final int protocolNumberOfDispatchers;
    private final Duration wsReconnectTimeout;
    private final Duration wsFetchMessageTimeout;
    private final Duration wsSendMessageTimeout;
    private final Duration wsReceiveMessageTimeout;
    private final Duration wsConsumerGraceTimeout;
    private final Duration wsRefreshConnectionTimeout;
    private final int wsNumberOfConnections;

    public ImmutableConfig(final MbsSdkConfig config) {
        this.authServer = notNull(config.getAuthServer(), "authServer");
        this.authClientId = notNull(config.getAuthClientId(), "authClientId");
        this.authClientSecret = notNull(config.getAuthClientSecret(), "authClientSecret");
        this.authAudience = notNull(config.getAuthAudience(), "authAudience");
        this.wsServer = notNull(config.getWsServer(), "wsServer");
        this.operatorId = config.getOperatorId();
        this.authRequestTimeout = withDefault(config.getAuthRetryDelay(), Duration.ofSeconds(5), MIN_DURATION);
        this.authRetryDelay = withDefault(config.getAuthRetryDelay(), Duration.ofMillis(500), MIN_DURATION);
        this.wsNumberOfConnections = withDefault(config.getWsNumberOfConnections(), 1, 1);
        this.wsReconnectTimeout = withDefault(config.getWsReconnectTimeout(), Duration.ofSeconds(10), MIN_DURATION);
        this.wsFetchMessageTimeout = withDefault(config.getWsFetchMessageTimeout(), Duration.ofSeconds(1), MIN_DURATION);
        this.wsSendMessageTimeout = withDefault(config.getWsSendMessageTimeout(), Duration.ofSeconds(1), MIN_DURATION);
        this.wsReceiveMessageTimeout = withDefault(config.getWsReceiveMessageTimeout(), Duration.ofSeconds(30), MIN_DURATION);
        this.wsConsumerGraceTimeout = withDefault(config.getWsConsumerGraceTimeout(), Duration.ofMinutes(1), MIN_DURATION);
        this.wsRefreshConnectionTimeout = withDefault(config.getWsRefreshConnectionTimeout(), Duration.ofMinutes(100), MIN_DURATION);
        this.protocolRetryCount = withDefault(config.getProtocolRetryCount(), 0, 0);
        this.protocolMaxSendBufferSize = withDefault(config.getProtocolMaxSendBufferSize(), 1_000, 1);
        this.protocolConnectTimeout = withDefault(config.getProtocolConnectTimeout(), Duration.ofSeconds(10), MIN_DURATION);
        this.protocolReceiveResponseTimeout = withDefault(config.getProtocolReceiveResponseTimeout(), Duration.ofSeconds(20), MIN_DURATION);
        this.protocolEnqueueTimeout = withDefault(config.getProtocolEnqueueTimeout(), Duration.ofMillis(100), MIN_DURATION);
        this.protocolDequeueTimeout = withDefault(config.getProtocolDequeueTimeout(), Duration.ofSeconds(1), MIN_DURATION);
        this.protocolNumberOfDispatchers = withDefault(config.getProtocolNumberOfDispatchers(), 1, 1);
    }

    @Override
    public URI getAuthServer() {
        return authServer;
    }

    @Override
    public String getAuthClientId() {
        return authClientId;
    }

    @Override
    public String getAuthClientSecret() {
        return authClientSecret;
    }

    @Override
    public String getAuthAudience() {
        return authAudience;
    }

    @Override
    public Duration getAuthRequestTimeout() {
        return authRequestTimeout;
    }

    @Override
    public Duration getAuthRetryDelay() {
        return authRetryDelay;
    }

    @Override
    public long getOperatorId() {
        return operatorId;
    }

    @Override
    public Duration getProtocolConnectTimeout() {
        return protocolConnectTimeout;
    }

    @Override
    public int getProtocolMaxSendBufferSize() {
        return protocolMaxSendBufferSize;
    }

    @Override
    public Duration getProtocolEnqueueTimeout() {
        return protocolEnqueueTimeout;
    }

    @Override
    public Duration getProtocolDequeueTimeout() {
        return protocolDequeueTimeout;
    }

    @Override
    public Duration getProtocolReceiveResponseTimeout() {
        return protocolReceiveResponseTimeout;
    }

    @Override
    public int getProtocolRetryCount() {
        return protocolRetryCount;
    }

    @Override
    public int getProtocolNumberOfDispatchers() {
        return protocolNumberOfDispatchers;
    }

    @Override
    public URI getWsServer() {
        return wsServer;
    }

    @Override
    public Duration getWsReconnectTimeout() {
        return wsReconnectTimeout;
    }

    @Override
    public Duration getWsFetchMessageTimeout() {
        return wsFetchMessageTimeout;
    }

    @Override
    public Duration getWsSendMessageTimeout() {
        return wsSendMessageTimeout;
    }

    @Override
    public Duration getWsReceiveMessageTimeout() {
        return wsReceiveMessageTimeout;
    }

    @Override
    public Duration getWsConsumerGraceTimeout() {
        return wsConsumerGraceTimeout;
    }

    @Override
    public Duration getWsRefreshConnectionTimeout() {
        return wsRefreshConnectionTimeout;
    }

    @Override
    public int getWsNumberOfConnections() {
        return wsNumberOfConnections;
    }
}
