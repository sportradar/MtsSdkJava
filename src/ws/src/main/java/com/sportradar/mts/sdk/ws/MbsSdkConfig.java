package com.sportradar.mts.sdk.ws;

import java.net.URI;
import java.time.Duration;
import java.util.function.BiConsumer;

/**
 * The MbsSdkConfig class represents the configuration for the MBS SDK.
 */
public class MbsSdkConfig {

    private final URI wsServer;
    private final URI authServer;
    private final String authClientId;
    private final String authClientSecret;
    private final String authAudience;
    private final long operatorId;

    private Duration authRequestTimeout;
    private Duration protocolConnectTimeout;
    private Duration authRetryDelay;
    private Integer protocolMaxSendBufferSize;
    private Duration protocolEnqueueTimeout;
    private Duration protocolDequeueTimeout;
    private Duration protocolReceiveResponseTimeout;
    private Integer protocolRetryCount;
    private Integer protocolNumberOfDispatchers;
    private Duration wsReconnectTimeout;
    private Duration wsFetchMessageTimeout;
    private Duration wsSendMessageTimeout;
    private Duration wsReceiveMessageTimeout;
    private Duration wsConsumerGraceTimeout;
    private Duration wsRefreshConnectionTimeout;
    private Integer wsNumberOfConnections;
    private BiConsumer<MbsSdk, Exception> unhandledExceptionHandler;

    /**
     * Constructs a new instance of the MbsSdkConfig class.
     *
     * @param wsServer         The URI of the WebSocket server.
     * @param authServer       The URI of the authentication server.
     * @param authClientId     The client ID for authentication.
     * @param authClientSecret The client secret for authentication.
     * @param authAudience     The audience for authentication.
     * @param operatorId       The ID of the operator.
     */
    public MbsSdkConfig(
            final URI wsServer,
            final URI authServer,
            final String authClientId,
            final String authClientSecret,
            final String authAudience,
            final long operatorId) {
        this.wsServer = wsServer;
        this.authServer = authServer;
        this.authClientId = authClientId;
        this.authClientSecret = authClientSecret;
        this.authAudience = authAudience;
        this.operatorId = operatorId;
    }

    /**
     * Gets the URI of the WebSocket server.
     *
     * @return The URI of the WebSocket server.
     */
    public URI getWsServer() {
        return wsServer;
    }

    /**
     * Gets the URI of the authentication server.
     *
     * @return The URI of the authentication server.
     */
    public URI getAuthServer() {
        return authServer;
    }

    /**
     * Gets the client ID for authentication.
     *
     * @return The client ID for authentication.
     */
    public String getAuthClientId() {
        return authClientId;
    }

    /**
     * Gets the client secret for authentication.
     *
     * @return The client secret for authentication.
     */
    public String getAuthClientSecret() {
        return authClientSecret;
    }

    /**
     * Gets the audience for authentication.
     *
     * @return The audience for authentication.
     */
    public String getAuthAudience() {
        return authAudience;
    }

    /**
     * Gets the ID of the operator.
     *
     * @return The ID of the operator.
     */
    public long getOperatorId() {
        return operatorId;
    }

    /**
     * Gets the timeout for authentication requests.
     *
     * @return The timeout for authentication requests.
     */
    public Duration getAuthRequestTimeout() {
        return authRequestTimeout;
    }

    /**
     * Sets the timeout for authentication requests.
     *
     * @param authRequestTimeout The timeout for authentication requests to set.
     */
    public void setAuthRequestTimeout(Duration authRequestTimeout) {
        this.authRequestTimeout = authRequestTimeout;
    }

    /**
     * Gets the timeout for connecting to the protocol.
     *
     * @return The timeout for connecting to the protocol.
     */
    public Duration getProtocolConnectTimeout() {
        return protocolConnectTimeout;
    }

    /**
     * Sets the timeout for connecting to the protocol.
     *
     * @param protocolConnectTimeout The timeout for connecting to the protocol to set.
     */
    public void setProtocolConnectTimeout(Duration protocolConnectTimeout) {
        this.protocolConnectTimeout = protocolConnectTimeout;
    }

    /**
     * Gets the delay between authentication retries.
     *
     * @return The delay between authentication retries.
     */
    public Duration getAuthRetryDelay() {
        return authRetryDelay;
    }

    /**
     * Sets the delay between authentication retries.
     *
     * @param authRetryDelay The delay between authentication retries to set.
     */
    public void setAuthRetryDelay(Duration authRetryDelay) {
        this.authRetryDelay = authRetryDelay;
    }

    /**
     * Gets the maximum send buffer size for the protocol.
     *
     * @return The maximum send buffer size for the protocol.
     */
    public Integer getProtocolMaxSendBufferSize() {
        return protocolMaxSendBufferSize;
    }

    /**
     * Sets the maximum send buffer size for the protocol.
     *
     * @param protocolMaxSendBufferSize The maximum send buffer size for the protocol to set.
     */
    public void setProtocolMaxSendBufferSize(Integer protocolMaxSendBufferSize) {
        this.protocolMaxSendBufferSize = protocolMaxSendBufferSize;
    }

    /**
     * Gets the timeout for enqueuing messages in the protocol.
     *
     * @return The timeout for enqueuing messages in the protocol.
     */
    public Duration getProtocolEnqueueTimeout() {
        return protocolEnqueueTimeout;
    }

    /**
     * Sets the timeout for enqueuing messages in the protocol.
     *
     * @param protocolEnqueueTimeout The timeout for enqueuing messages in the protocol to set.
     */
    public void setProtocolEnqueueTimeout(Duration protocolEnqueueTimeout) {
        this.protocolEnqueueTimeout = protocolEnqueueTimeout;
    }

    /**
     * Gets the timeout for dequeuing messages in the protocol.
     *
     * @return The timeout for dequeuing messages in the protocol.
     */
    public Duration getProtocolDequeueTimeout() {
        return protocolDequeueTimeout;
    }

    /**
     * Sets the timeout for dequeuing messages in the protocol.
     *
     * @param protocolDequeueTimeout The timeout for dequeuing messages in the protocol to set.
     */
    public void setProtocolDequeueTimeout(Duration protocolDequeueTimeout) {
        this.protocolDequeueTimeout = protocolDequeueTimeout;
    }

    /**
     * Gets the timeout for receiving responses in the protocol.
     *
     * @return The timeout for receiving responses in the protocol.
     */
    public Duration getProtocolReceiveResponseTimeout() {
        return protocolReceiveResponseTimeout;
    }

    /**
     * Sets the timeout for receiving responses in the protocol.
     *
     * @param protocolReceiveResponseTimeout The timeout for receiving responses in the protocol to set.
     */
    public void setProtocolReceiveResponseTimeout(Duration protocolReceiveResponseTimeout) {
        this.protocolReceiveResponseTimeout = protocolReceiveResponseTimeout;
    }

    /**
     * Gets the number of times to retry the protocol.
     *
     * @return The number of times to retry the protocol.
     */
    public Integer getProtocolRetryCount() {
        return protocolRetryCount;
    }

    /**
     * Sets the number of times to retry the protocol.
     *
     * @param protocolRetryCount The number of times to retry the protocol to set.
     */
    public void setProtocolRetryCount(Integer protocolRetryCount) {
        this.protocolRetryCount = protocolRetryCount;
    }

    /**
     * Gets the number of dispatchers for the protocol.
     *
     * @return The number of dispatchers for the protocol.
     */
    public Integer getProtocolNumberOfDispatchers() {
        return protocolNumberOfDispatchers;
    }

    /**
     * Sets the number of dispatchers for the protocol.
     *
     * @param protocolNumberOfDispatchers The number of dispatchers for the protocol to set.
     */
    public void setProtocolNumberOfDispatchers(Integer protocolNumberOfDispatchers) {
        this.protocolNumberOfDispatchers = protocolNumberOfDispatchers;
    }

    /**
     * Gets the timeout for reconnecting the WebSocket.
     *
     * @return The timeout for reconnecting the WebSocket.
     */
    public Duration getWsReconnectTimeout() {
        return wsReconnectTimeout;
    }

    /**
     * Sets the timeout for reconnecting the WebSocket.
     *
     * @param wsReconnectTimeout The timeout for reconnecting the WebSocket to set.
     */
    public void setWsReconnectTimeout(Duration wsReconnectTimeout) {
        this.wsReconnectTimeout = wsReconnectTimeout;
    }

    /**
     * Gets the timeout for fetching messages from the WebSocket.
     *
     * @return The timeout for fetching messages from the WebSocket.
     */
    public Duration getWsFetchMessageTimeout() {
        return wsFetchMessageTimeout;
    }

    /**
     * Sets the timeout for fetching messages from the WebSocket.
     *
     * @param wsFetchMessageTimeout The timeout for fetching messages from the WebSocket to set.
     */
    public void setWsFetchMessageTimeout(Duration wsFetchMessageTimeout) {
        this.wsFetchMessageTimeout = wsFetchMessageTimeout;
    }

    /**
     * Gets the timeout for sending messages through the WebSocket.
     *
     * @return The timeout for sending messages through the WebSocket.
     */
    public Duration getWsSendMessageTimeout() {
        return wsSendMessageTimeout;
    }

    /**
     * Sets the timeout for sending messages through the WebSocket.
     *
     * @param wsSendMessageTimeout The timeout for sending messages through the WebSocket to set.
     */
    public void setWsSendMessageTimeout(Duration wsSendMessageTimeout) {
        this.wsSendMessageTimeout = wsSendMessageTimeout;
    }

    /**
     * Gets the timeout for receiving messages through the WebSocket.
     *
     * @return The timeout for receiving messages through the WebSocket.
     */
    public Duration getWsReceiveMessageTimeout() {
        return wsReceiveMessageTimeout;
    }

    /**
     * Sets the timeout for receiving messages through the WebSocket.
     *
     * @param wsReceiveMessageTimeout The timeout for receiving messages through the WebSocket to set.
     */
    public void setWsReceiveMessageTimeout(Duration wsReceiveMessageTimeout) {
        this.wsReceiveMessageTimeout = wsReceiveMessageTimeout;
    }

    /**
     * Gets the grace timeout for WebSocket consumers.
     *
     * @return The grace timeout for WebSocket consumers.
     */
    public Duration getWsConsumerGraceTimeout() {
        return wsConsumerGraceTimeout;
    }

    /**
     * Sets the grace timeout for WebSocket consumers.
     *
     * @param wsConsumerGraceTimeout The grace timeout for WebSocket consumers to set.
     */
    public void setWsConsumerGraceTimeout(Duration wsConsumerGraceTimeout) {
        this.wsConsumerGraceTimeout = wsConsumerGraceTimeout;
    }

    /**
     * Gets the timeout for refreshing the WebSocket connection.
     *
     * @return The timeout for refreshing the WebSocket connection.
     */
    public Duration getWsRefreshConnectionTimeout() {
        return wsRefreshConnectionTimeout;
    }

    /**
     * Sets the timeout for refreshing the WebSocket connection.
     *
     * @param wsRefreshConnectionTimeout The timeout for refreshing the WebSocket connection to set.
     */
    public void setWsRefreshConnectionTimeout(Duration wsRefreshConnectionTimeout) {
        this.wsRefreshConnectionTimeout = wsRefreshConnectionTimeout;
    }

    /**
     * Gets the number of connections for the WebSocket.
     *
     * @return The number of connections for the WebSocket.
     */
    public Integer getWsNumberOfConnections() {
        return wsNumberOfConnections;
    }

    /**
     * Sets the number of connections for the WebSocket.
     *
     * @param wsNumberOfConnections The number of connections for the WebSocket to set.
     */
    public void setWsNumberOfConnections(Integer wsNumberOfConnections) {
        this.wsNumberOfConnections = wsNumberOfConnections;
    }

    /**
     * Gets the unhandled exception handler for the SDK.
     *
     * @return The unhandled exception handler for the SDK.
     */
    public BiConsumer<MbsSdk, Exception> getUnhandledExceptionHandler() {
        return unhandledExceptionHandler;
    }

    /**
     * Sets the unhandled exception handler for the SDK.
     *
     * @param unhandledExceptionHandler The unhandled exception handler for the SDK to set.
     */
    public void setUnhandledExceptionHandler(BiConsumer<MbsSdk, Exception> unhandledExceptionHandler) {
        this.unhandledExceptionHandler = unhandledExceptionHandler;
    }
}
