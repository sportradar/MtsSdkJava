/*
 * Copyright (C) Sportradar AG. See LICENSE for full license governing this code
 */

package com.sportradar.mts.sdk.api.interfaces;

import com.sportradar.mts.sdk.api.enums.SenderChannel;
import com.sportradar.mts.sdk.api.enums.UfEnvironment;

import java.net.URI;
import java.time.Duration;

/**
 *  Defines a contract implemented by classes representing configuration
 */
public interface SdkConfiguration {

    /**
     * Gets an username used when establishing connection to the AQMP broker
     * @return username
     */
    String getUsername();

    /**
     * Gets a password used when establishing connection to the AQMP broker
     * @return password
     */
    String getPassword();

    /**
     * Gets a value specifying the host name of the AQMP broker
     * @return hostname or ip
     */
    String getHost();

    /**
     * Gets a value specifying the virtual host name of the AQMP broker
     * @return virtual host
     */
    String getVirtualHost();

    /**
     * Gets a value specifying whether the connection to AMQP broker should use SSL encryption
     * @return use SSL
     */
    boolean getUseSsl();

    /**
     * Gets the port used to connect to AMQP broker
     * @return port number
     */
    int getPort();

    /**
     * Gets nodeId
     * @return nodeId
     */
    int getNode();

    /**
     *  Gets the BookmakerId associated with the current configuration or 0 if none is provided
     * @return bookmakerId
     */
    int getBookmakerId();

    /**
     *  Gets the channel identifier associated with the current configuration or 0 if none is provided
     * @return limitId
     */
    int getLimitId();

    /**
     * Gets the default currency associated with the current configuration or a null reference if none is provided
     * @return currency
     */
    String getCurrency();

    /**
     * Gets the {@link SenderChannel} specifying the associated channel or a null reference if none is specified
     * @return {@link SenderChannel}
     */
    SenderChannel getSenderChannel();

    /**
     * Gets the access token for the UF feed (only necessary if UF selections will be build)
     * @return access token
     */
    String getAccessToken();

    /**
     * Gets the uf environment for the UF feed (only necessary if UF selections will be build)
     * @return uf environment
     */
    UfEnvironment getUfEnvironment();

    /**
     * Gets the value used to indicate if the sdk should add market specifiers for specific markets. Only used when building selection using UnifiedOdds ids
     * @return provideAdditionalMarketSpecifiers
     */
    boolean getProvideAdditionalMarketSpecifiers();

    /**
     * An indication if the {@link com.sportradar.mts.sdk.api.Ticket}s sent async have the time-out callback enabled.
     * The time-out value is taken from {@link #getTicketResponseTimeoutLive()}, {@link #getTicketCancellationResponseTimeout()}, {@link #getTicketCashoutResponseTimeout()} and {@link #getTicketNonSrSettleResponseTimeout()}.
     *
     * @return <code>true</code> if the time-out callback should be invoked, otherwise <code>false</code>
     */
    boolean isTicketTimeOutCallbackEnabled();

    /**
     * Gets the ticket response timeout(ms) (used when sending in blocking-mode and when the {@link #isTicketTimeOutCallbackEnabled()} is set to <code>true</code>)
     * @return the ticket response timeout
     */
    int getTicketResponseTimeoutLive();

    /**
     * Gets the ticket response timeout(ms) (used when sending in blocking-mode and when the {@link #isTicketTimeOutCallbackEnabled()} is set to <code>true</code>)
     * @return the ticket response timeout
     */
    int getTicketResponseTimeoutPrematch();

    /**
     * Gets the ticket cancellation response timeout(ms) (used when sending in blocking-mode and when the {@link #isTicketTimeOutCallbackEnabled()} is set to <code>true</code>)
     * @return the ticket cancellation response timeout
     */
    int getTicketCancellationResponseTimeout();

    /**
     * Gets the ticket cashout response timeout(ms) (used when sending in blocking-mode and when the {@link #isTicketTimeOutCallbackEnabled()} is set to <code>true</code>)
     * @return the ticket cashout response timeout
     */
    int getTicketCashoutResponseTimeout();

    /**
     * Gets the ticket non-Sportradar response timeout(ms) (used when sending in blocking-mode and when the {@link #isTicketTimeOutCallbackEnabled()} is set to <code>true</code>)
     * @return the ticket cashout response timeout
     */
    int getTicketNonSrSettleResponseTimeout();

    /**
     * Gets the maximum number of messages per second
     * @return messagesPerSecond
     */
    double getMessagesPerSecond();

    /**
     * Gets a value specifying whether the rabbit consumer channel should be exclusive
     * @return use exclusive consumer channel setting
     */
    boolean getExclusiveConsumer();

    /**
     * Gets the Keycloak host for authorization
     * @return the Keycloak host for authorization
     */
    String getKeycloakHost();

    /**
     * Gets the username used to connect authenticate to Keycloak
     * @return the username used to connect authenticate to Keycloak
     */
    String getKeycloakUsername();

    /**
     * Gets the password used to connect authenticate to Keycloak
     * @return the password used to connect authenticate to Keycloak
     */
    String getKeycloakPassword();

    /**
     * Gets the secret used to connect authenticate to Keycloak
     * @return the secret used to connect authenticate to Keycloak
     */
    String getKeycloakSecret();

    /**
     * Gets the Client API host
     * @return the Client API host
     */
    String getMtsClientApiHost();

    /**
     * Gets the MTS SDK connection mode - RabbitMQ or WebSocket
     * @return the MTS SDK connection mode
     */
    Boolean getUseWebSocket();

    /**
     * Gets the URI of the authorization server used for authentication
     * @return the URI of the authorization server
     */
    URI getAuthServer();

    /**
     * Gets the client ID used for authentication
     * @return the client ID
     */
    String getAuthClientId();

    /**
     * Gets the client secret used for authentication
     * @return the client secret
     */
    String getAuthClientSecret();

    /**
     * Gets the audience for which the authentication token is requested
     * @return the audience
     */
    String getAuthAudience();

    /**
     * Gets the timeout for the authentication request
     * @return the timeout duration
     */
    Duration getAuthRequestTimeout();

    /**
     * Gets the delay before retrying authentication after a failure
     * @return the delay duration
     */
    Duration getAuthRetryDelay();

    /**
     * Gets the maximum number of authentication retries
     * @return the maximum retry count
     */
    URI getWsServer();

    /**
     * Gets the WebSocket reconnect timeout
     * @return the reconnect timeout duration
     */
    Duration getWsReconnectTimeout();

    /**
     * Gets the WebSocket fetch message timeout
     * @return the fetch message timeout duration
     */
    Duration getWsFetchMessageTimeout();

    /**
     * Gets the WebSocket send message timeout
     * @return the send message timeout duration
     */
    Duration getWsSendMessageTimeout();

    /**
     * Gets the WebSocket receive message timeout
     * @return the receive message timeout duration
     */
    Duration getWsReceiveMessageTimeout();

    /**
     * Gets the WebSocket consumer grace timeout
     * @return the grace timeout duration
     */
    Duration getWsConsumerGraceTimeout();

    /**
     * Gets the WebSocket refresh connection timeout
     * @return the refresh connection timeout duration
     */
    Duration getWsRefreshConnectionTimeout();

    /**
     * Gets the number of WebSocket connections
     * @return the number of connections
     */
    int getWsNumberOfConnections();

    /**
     * Gets the protocol connect timeout
     * @return the connect timeout duration
     */
    Duration getProtocolConnectTimeout();

    /**
     * Gets the protocol max send buffer size
     * @return the maximum send buffer size in bytes
     */
    int getProtocolMaxSendBufferSize();

    /**
     * Gets the protocol enqueue timeout
     * @return the protocol enqueue timeout duration
     */
    Duration getProtocolEnqueueTimeout();

    /**
     * Gets the protocol dequeue timeout
     * @return the protocol dequeue timeout duration
     */
    Duration getProtocolDequeueTimeout();

    /**
     * Gets the protocol receive response timeout
     * @return the protocol receive response timeout duration
     */
    Duration getProtocolReceiveResponseTimeout();

    /**
     * Gets the protocol retry count
     * @return the number of retries for protocol operations
     */
    int getProtocolRetryCount();

    /**
     * Gets the protocol number of dispatchers
     * @return the number of dispatchers used by the protocol engine
     */
    int getProtocolNumberOfDispatchers();
}