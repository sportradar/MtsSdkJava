/*
 * Copyright (C) Sportradar AG. See LICENSE for full license governing this code
 */

package com.sportradar.mts.sdk.api.interfaces;

import com.sportradar.mts.sdk.api.enums.SenderChannel;
import com.sportradar.mts.sdk.api.enums.UfEnvironment;

import java.time.Duration;

/**
 * Defines a contract for classes implementing builder for {@link SdkConfiguration}
 */
public interface SdkConfigurationBuilder {

    /**
     * Sets the username
     * @param username to be set
     * @return {@link SdkConfigurationBuilder}
     */
    SdkConfigurationBuilder setUsername(String username);

    /**
     * Sets the password
     * @param password to be set
     * @return {@link SdkConfigurationBuilder}
     */
    SdkConfigurationBuilder setPassword(String password);

    /**
     * Sets the host used to connect to AMQP broker
     * @param host to be set
     * @return {@link SdkConfigurationBuilder}
     */
    SdkConfigurationBuilder setHost(String host);

    /**
     * Sets the port used to connect to AMQP broker. Port should be set through the setUseSsl method. Manually setting port number should be used only when non-default port is required.
     * @param port to be set
     * @return {@link SdkConfigurationBuilder}
     */
    SdkConfigurationBuilder setPort(int port);

    /**
     * Sets the virtual host
     * @param vhost to be set
     * @return {@link SdkConfigurationBuilder}
     */
    SdkConfigurationBuilder setVirtualHost(String vhost);

    /**
     * Sets the node id
     * @param nodeId to be set
     * @return {@link SdkConfigurationBuilder}
     */
    SdkConfigurationBuilder setNode(int nodeId);

    /**
     * Sets whether ssl should be used
     * @param useSsl value to be set
     * @return {@link SdkConfigurationBuilder}
     */
    SdkConfigurationBuilder setUseSsl(boolean useSsl);

    /**
     * Sets the bookmakerId
     * @param bookmakerId to be set
     * @return {@link SdkConfigurationBuilder}
     */
    SdkConfigurationBuilder setBookmakerId(int bookmakerId);
    
    /**
     * Sets the limitId
     * @param limitId to be set
     * @return {@link SdkConfigurationBuilder}
     */
    SdkConfigurationBuilder setLimitId(int limitId);
    
    /**
     * Sets the currency
     * @param currency to be set
     * @return {@link SdkConfigurationBuilder}
     */
    SdkConfigurationBuilder setCurrency(String currency);

    /**
     * Sets the sender channel
     * @param channel to be set
     * @return {@link SdkConfigurationBuilder}
     */
    SdkConfigurationBuilder setSenderChannel(SenderChannel channel);

    /**
     * Sets the access token
     * @param accessToken to be set
     * @return {@link SdkConfigurationBuilder}
     */
    SdkConfigurationBuilder setAccessToken(String accessToken);

    /**
     * Sets the uf environment
     *
     * @param ufEnvironment to be set
     * @return {@link SdkConfigurationBuilder}
     */
    SdkConfigurationBuilder setUfEnvironment(UfEnvironment ufEnvironment);

    /**
     * This value is used to indicate if the sdk should add market specifiers for specific markets. Only used when building selection using UnifiedOdds ids. (default: true) If this is set to true and the user uses UOF markets, when there are special cases (market 215, or $score in SOV/SBV template), sdk automatically tries to add appropriate specifier; if set to false, user will need to add this manually.
     * @param provideAdditionalMarketSpecifiers value to be set
     * @return {@link SdkConfigurationBuilder}
     */
    SdkConfigurationBuilder setProvideAdditionalMarketSpecifiers(boolean provideAdditionalMarketSpecifiers);

    /**
     * Sets the value indicating if the {@link com.sportradar.mts.sdk.api.Ticket}s sent async have the time-out callback enabled
     *
     * @param ticketTimeOutCallbackEnabled value to be set
     * @return {@link SdkConfigurationBuilder}
     */
    SdkConfigurationBuilder setTicketTimeOutCallbackEnabled(boolean ticketTimeOutCallbackEnabled);

    /**
     * Sets the ticket response timeout(ms). This value is being used only if the ticket is sent blocking or {@link #setTicketTimeOutCallbackEnabled(boolean)} is set to <code>true</code>
     *
     * @param responseTimeout the ticket response timeout to set(ms) (will set for lcoo and live responses)
     * @return {@link SdkConfigurationBuilder}
     */
    SdkConfigurationBuilder setTicketResponseTimeout(int responseTimeout);

    /**
     * Sets the ticket response timeout(ms). This value is being used only if the ticket is sent blocking or {@link #setTicketTimeOutCallbackEnabled(boolean)} is set to <code>true</code>
     *
     * @param responseTimeout the ticket response timeout to set(ms)
     * @return {@link SdkConfigurationBuilder}
     */
    SdkConfigurationBuilder setTicketResponseTimeoutLive(int responseTimeout);

    /**
     * Sets the ticket response timeout(ms). This value is being used only if the ticket is sent blocking or {@link #setTicketTimeOutCallbackEnabled(boolean)} is set to <code>true</code>
     *
     * @param responseTimeout the ticket response timeout to set(ms)
     * @return {@link SdkConfigurationBuilder}
     */
    SdkConfigurationBuilder setTicketResponseTimeoutPrematch(int responseTimeout);

    /**
     * Sets the ticket cancellation response timeout(ms). This value is being used only if the ticket is sent blocking or {@link #setTicketTimeOutCallbackEnabled(boolean)} is set to <code>true</code>
     *
     * @param responseTimeout the ticket cancellation response timeout to set(ms)
     * @return {@link SdkConfigurationBuilder}
     */
    SdkConfigurationBuilder setTicketCancellationResponseTimeout(int responseTimeout);

    /**
     * Sets the ticket cashout response timeout(ms). This value is being used only if the ticket is sent blocking or {@link #setTicketTimeOutCallbackEnabled(boolean)} is set to <code>true</code>
     *
     * @param responseTimeout the ticket cashout response timeout to set(ms)
     * @return {@link SdkConfigurationBuilder}
     */
    SdkConfigurationBuilder setTicketCashoutResponseTimeout(int responseTimeout);

    /**
     * Sets the ticket non-Sportradar response timeout(ms). This value is being used only if the ticket is sent blocking or {@link #setTicketTimeOutCallbackEnabled(boolean)} is set to <code>true</code>
     *
     * @param responseTimeout the ticket non-Sportradar response timeout to set(ms)
     * @return {@link SdkConfigurationBuilder}
     */
    SdkConfigurationBuilder setTicketNonSrSettleResponseTimeout(int responseTimeout);

    /**
     * Sets whether the rabbit consumer channel should be exclusive
     * @param exclusiveConsumer value to be set
     * @return {@link SdkConfigurationBuilder}
     */
    SdkConfigurationBuilder setExclusiveConsumer(boolean exclusiveConsumer);

    /**
     * Sets the Keycloak host for authorization
     *
     * @param keycloakHost the Keycloak host to be set
     * @return {@link SdkConfigurationBuilder}
     */
    SdkConfigurationBuilder setKeycloakHost(String keycloakHost);

    /**
     * Sets the username used to connect authenticate to Keycloak
     *
     * @param keycloakUsername the username used to connect authenticate to Keycloak
     * @return {@link SdkConfigurationBuilder}
     */
    SdkConfigurationBuilder setKeycloakUsername(String keycloakUsername);

    /**
     * Sets the password used to connect authenticate to Keycloak
     *
     * @param keycloakPassword the password used to connect authenticate to Keycloak
     * @return {@link SdkConfigurationBuilder}
     */
    SdkConfigurationBuilder setKeycloakPassword(String keycloakPassword);

    /**
     * Sets the secret used to connect authenticate to Keycloak
     *
     * @param keycloakSecret the secret used to connect authenticate to Keycloak
     * @return {@link SdkConfigurationBuilder}
     */
    SdkConfigurationBuilder setKeycloakSecret(String keycloakSecret);

    /**
     * Sets the Client API host
     *
     * @param mtsClientApiHost the Client API host
     * @return {@link SdkConfigurationBuilder}
     */
    SdkConfigurationBuilder setMtsClientApiHost(String mtsClientApiHost);

    /**
     * Sets the use WebSocket flag
     *
     * @param useWebSocket flag value
     * @return @link SdkConfigurationBuilder}
     */
    SdkConfigurationBuilder setUseWebSocket(boolean useWebSocket);

    /**
     * Sets the auth server URI
     *
     * @param authServer the auth server URI to be set
     * @return {@link SdkConfigurationBuilder}
     */
    SdkConfigurationBuilder setAuthServer(String authServer);

    /**
     * Sets the client ID used for authentication
     *
     * @param authClientId the client ID to be set
     * @return {@link SdkConfigurationBuilder}
     */
    SdkConfigurationBuilder setAuthClientId(String authClientId);

    /**
     * Sets the client secret used for authentication
     *
     * @param authClientSecret the client secret to be set
     * @return {@link SdkConfigurationBuilder}
     */
    SdkConfigurationBuilder setAuthClientSecret(String authClientSecret);

    /**
     * Sets the audience for the authentication
     *
     * @param authAudience the audience to be set
     * @return {@link SdkConfigurationBuilder}
     */
    SdkConfigurationBuilder setAuthAudience(String authAudience);

    /**
     * Sets the timeout for the authentication request
     *
     * @param authRequestTimeout the timeout to be set
     * @return {@link SdkConfigurationBuilder}
     */
    SdkConfigurationBuilder setAuthRequestTimeout(Duration authRequestTimeout);

    /**
     * Sets the delay before retrying authentication
     *
     * @param authRetryDelay the delay to be set
     * @return {@link SdkConfigurationBuilder}
     */
    SdkConfigurationBuilder setAuthRetryDelay(Duration authRetryDelay);

    /**
     * Sets URI of the WebSocket server used for communication
     *
     * @param wsServer the URI of the WebSocket server to be set
     * @return {@link SdkConfigurationBuilder}
     */
    SdkConfigurationBuilder setWsServer(String wsServer);

    /**
     * Sets the WebSocket reconnect timeout
     *
     * @param wsReconnectTimeout the reconnect timeout to be set
     * @return {@link SdkConfigurationBuilder}
     */
    SdkConfigurationBuilder setWsReconnectTimeout(Duration wsReconnectTimeout);

    /**
     * Sets the WebSocket fetch message timeout
     *
     * @param wsFetchMessageTimeout the fetch message timeout to be set
     * @return {@link SdkConfigurationBuilder}
     */
    SdkConfigurationBuilder setWsFetchMessageTimeout(Duration wsFetchMessageTimeout);

    /**
     * Sets the WebSocket send message timeout
     *
     * @param wsSendMessageTimeout the send message timeout to be set
     * @return {@link SdkConfigurationBuilder}
     */
    SdkConfigurationBuilder setWsSendMessageTimeout(Duration wsSendMessageTimeout);

    /**
     * Sets the WebSocket receive message timeout
     *
     * @param wsReceiveMessageTimeout the receive message timeout to be set
     * @return {@link SdkConfigurationBuilder}
     */
    SdkConfigurationBuilder setWsReceiveMessageTimeout(Duration wsReceiveMessageTimeout);

    /**
     * Sets the WebSocket consumer grace timeout
     *
     * @param wsConsumerGraceTimeout the consumer grace timeout to be set
     * @return {@link SdkConfigurationBuilder}
     */
    SdkConfigurationBuilder setWsConsumerGraceTimeout(Duration wsConsumerGraceTimeout);

    /**
     * Sets the WebSocket refresh connection timeout
     *
     * @param wsRefreshConnectionTimeout the refresh connection timeout to be set
     * @return {@link SdkConfigurationBuilder}
     */
    SdkConfigurationBuilder setWsRefreshConnectionTimeout(Duration wsRefreshConnectionTimeout);

    /**
     * Sets the number of WebSocket connections
     *
     * @param wsNumberOfConnections the number of connections to be set
     * @return {@link SdkConfigurationBuilder}
     */
    SdkConfigurationBuilder setWsNumberOfConnections(int wsNumberOfConnections);

    /**
     * Sets the protocol connect timeout
     *
     * @param protocolConnectTimeout the connect timeout to be set
     * @return {@link SdkConfigurationBuilder}
     */
    SdkConfigurationBuilder setProtocolConnectTimeout(Duration protocolConnectTimeout);

    /**
     * Sets the protocol max send buffer size
     *
     * @param protocolMaxSendBufferSize the maximum send buffer size in bytes to be set
     * @return {@link SdkConfigurationBuilder}
     */
    SdkConfigurationBuilder setProtocolMaxSendBufferSize(int protocolMaxSendBufferSize);

    /**
     * Sets the protocol enqueue timeout
     *
     * @param protocolEnqueueTimeout the protocol enqueue timeout to be set
     * @return {@link SdkConfigurationBuilder}
     */
    SdkConfigurationBuilder setProtocolEnqueueTimeout(Duration protocolEnqueueTimeout);

    /**
     * Sets the protocol dequeue timeout
     *
     * @param protocolDequeueTimeout the protocol dequeue timeout to be set
     * @return {@link SdkConfigurationBuilder}
     */
    SdkConfigurationBuilder setProtocolDequeueTimeout(Duration protocolDequeueTimeout);

    /**
     * Sets the protocol receive response timeout
     *
     * @param protocolReceiveResponseTimeout the protocol receive response timeout to be set
     * @return {@link SdkConfigurationBuilder}
     */
    SdkConfigurationBuilder setProtocolReceiveResponseTimeout(Duration protocolReceiveResponseTimeout);

    /**
     * Sets the protocol retry count
     *
     * @param protocolRetryCount the protocol retry count to be set
     * @return {@link SdkConfigurationBuilder}
     */
    SdkConfigurationBuilder setProtocolRetryCount(int protocolRetryCount);

    /**
     * Sets the protocol number of dispatchers
     *
     * @param protocolNumberOfDispatchers the number of dispatchers to be set
     * @return {@link SdkConfigurationBuilder}
     */
    SdkConfigurationBuilder setProtocolNumberOfDispatchers(int protocolNumberOfDispatchers);

    /**
     * Build and return the {@link SdkConfiguration}
     * @return {@link SdkConfiguration}
     */
    SdkConfiguration build();
}
