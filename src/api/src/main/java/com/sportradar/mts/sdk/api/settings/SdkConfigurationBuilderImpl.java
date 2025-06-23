/*
 * Copyright (C) Sportradar AG. See LICENSE for full license governing this code
 */

package com.sportradar.mts.sdk.api.settings;

import com.sportradar.mts.sdk.api.Ticket;
import com.sportradar.mts.sdk.api.enums.SenderChannel;
import com.sportradar.mts.sdk.api.enums.UfEnvironment;
import com.sportradar.mts.sdk.api.interfaces.SdkConfiguration;
import com.sportradar.mts.sdk.api.interfaces.SdkConfigurationBuilder;
import com.sportradar.mts.sdk.api.utils.SdkInfo;
import com.sportradar.mts.sdk.api.utils.StringUtils;

import java.time.Duration;
import java.util.Properties;

public class SdkConfigurationBuilderImpl implements SdkConfigurationBuilder {

    private Properties properties;

    public SdkConfigurationBuilderImpl()
    {
        properties = new Properties();
    }

    /**
     * Sets the username
     *
     * @param username to be set
     * @return {@link SdkConfigurationBuilder}
     */
    @Override
    public SdkConfigurationBuilder setUsername(String username) {
        if(StringUtils.isNullOrEmpty(username))
        {
            throw new IllegalArgumentException(SdkInfo.Literals.CONFIG_BUILDER_PARAM_EMPTY);
        }
        properties.setProperty(SettingsKeys.USERNAME, username);
        return this;
    }

    /**
     * Sets the password
     *
     * @param password to be set
     * @return {@link SdkConfigurationBuilder}
     */
    @Override
    public SdkConfigurationBuilder setPassword(String password) {
        if(StringUtils.isNullOrEmpty(password))
        {
            throw new IllegalArgumentException(SdkInfo.Literals.CONFIG_BUILDER_PARAM_EMPTY);
        }
        properties.setProperty(SettingsKeys.PASSWORD, password);
        return this;
    }

    /**
     * Sets the host
     *
     * @param host to be set
     * @return {@link SdkConfigurationBuilder}
     */
    @Override
    public SdkConfigurationBuilder setHost(String host) {
        if(StringUtils.isNullOrEmpty(host))
        {
            throw new IllegalArgumentException(SdkInfo.Literals.CONFIG_BUILDER_PARAM_EMPTY);
        }
        properties.setProperty(SettingsKeys.HOST, host);
        return this;
    }

    /**
     * Sets the port
     *
     * @param port to be set
     * @return {@link SdkConfigurationBuilder}
     */
    @Override
    public SdkConfigurationBuilder setPort(int port) {
        if(port < 1)
        {
            throw new IllegalArgumentException("Port number not valid.");
        }
        properties.setProperty(SettingsKeys.PORT, String.valueOf(port));
        return this;
    }

    /**
     * Sets the virtual host
     *
     * @param vhost to be set
     * @return {@link SdkConfigurationBuilder}
     */
    @Override
    public SdkConfigurationBuilder setVirtualHost(String vhost) {
        if(StringUtils.isNullOrEmpty(vhost))
        {
            throw new IllegalArgumentException(SdkInfo.Literals.CONFIG_BUILDER_PARAM_EMPTY);
        }
        properties.setProperty(SettingsKeys.VIRTUAL_HOST, vhost);
        return this;
    }

    /**
     * Sets the node id
     *
     * @param nodeId to be set
     * @return {@link SdkConfigurationBuilder}
     */
    @Override
    public SdkConfigurationBuilder setNode(int nodeId) {
        if(nodeId < 1)
        {
            throw new IllegalArgumentException(SdkInfo.Literals.CONFIG_BUILDER_PARAM_ZERO);
        }
        properties.setProperty(SettingsKeys.NODE_ID, String.valueOf(nodeId));
        return this;
    }

    /**
     * Sets whether ssl should be used
     *
     * @param useSsl value indicating if ssl connection should be used
     * @return {@link SdkConfigurationBuilder}
     */
    @Override
    public SdkConfigurationBuilder setUseSsl(boolean useSsl) {
        properties.setProperty(SettingsKeys.SSL, String.valueOf(useSsl));
        return this;
    }

    /**
     * Sets the bookmakerId
     *
     * @param bookmakerId to be set
     * @return {@link SdkConfigurationBuilder}
     */
    @Override
    public SdkConfigurationBuilder setBookmakerId(int bookmakerId) {
        if(bookmakerId < 1)
        {
            throw new IllegalArgumentException(SdkInfo.Literals.CONFIG_BUILDER_PARAM_ZERO);
        }
        properties.setProperty(SettingsKeys.BOOKMAKER_ID, String.valueOf(bookmakerId));
        return this;
    }

    /**
     * Sets the limitId
     *
     * @param limitId to be set
     * @return {@link SdkConfigurationBuilder}
     */
    @Override
    public SdkConfigurationBuilder setLimitId(int limitId) {
        if(limitId < 1)
        {
            throw new IllegalArgumentException(SdkInfo.Literals.CONFIG_BUILDER_PARAM_ZERO);
        }
        properties.setProperty(SettingsKeys.LIMIT_ID, String.valueOf(limitId));
        return this;
    }

    /**
     * Set the currency
     *
     * @param currency to be set
     * @return {@link SdkConfigurationBuilder}
     */
    @Override
    public SdkConfigurationBuilder setCurrency(String currency) {
        if(StringUtils.isNullOrEmpty(currency) || currency.length() < 3 || currency.length() > 4)
        {
            throw new IllegalArgumentException(SdkInfo.Literals.CONFIG_BUILDER_PARAM_EMPTY);
        }
        properties.setProperty(SettingsKeys.CURRENCY, currency);
        return this;
    }

    /**
     * Sets the sender channel
     *
     * @param channel to be set
     * @return {@link SdkConfigurationBuilder}
     */
    @Override
    public SdkConfigurationBuilder setSenderChannel(SenderChannel channel) {
        properties.setProperty(SettingsKeys.CHANNEL, channel.toString());
        return this;
    }

    /**
     * Sets the access token
     *
     * @param accessToken to be set
     * @return {@link SdkConfigurationBuilder}
     */
    @Override
    public SdkConfigurationBuilder setAccessToken(String accessToken) {
        if(StringUtils.isNullOrEmpty(accessToken))
        {
            throw new IllegalArgumentException(SdkInfo.Literals.CONFIG_BUILDER_PARAM_EMPTY);
        }
        properties.setProperty(SettingsKeys.ACCESS_TOKEN, accessToken);
        return this;
    }

    /**
     * Sets the uf environment
     *
     * @param ufEnvironment to be set
     * @return {@link SdkConfigurationBuilder}
     */
    @Override
    public SdkConfigurationBuilder setUfEnvironment(UfEnvironment ufEnvironment) {
        if(ufEnvironment == null)
        {
            throw new IllegalArgumentException("Value cannot be a null reference");
        }
        properties.setProperty(SettingsKeys.UF_ENVIRONMENT, ufEnvironment.toString());
        return this;
    }

    /**
     * This value is used to indicate if the sdk should add market specifiers for specific markets. Only used when building selection using UnifiedOdds ids
     *
     * @param provideAdditionalMarketSpecifiers value indicating if additional market specifiers should be provided
     * @return {@link SdkConfigurationBuilder}
     */
    @Override
    public SdkConfigurationBuilder setProvideAdditionalMarketSpecifiers(boolean provideAdditionalMarketSpecifiers) {
        properties.setProperty(SettingsKeys.PROVIDE_ADDITIONAL_MARKET_SPECIFIERS, String.valueOf(provideAdditionalMarketSpecifiers));
        return this;
    }

    /**
     * Sets the value indicating if the {@link Ticket}s sent async have the time-out callback enabled
     *
     * @param ticketTimeOutCallbackEnabled value to be set
     * @return {@link SdkConfigurationBuilder}
     */
    @Override
    public SdkConfigurationBuilder setTicketTimeOutCallbackEnabled(boolean ticketTimeOutCallbackEnabled) {
        properties.setProperty(SettingsKeys.TICKET_TIMEOUT_CALLBACK_ENABLED, String.valueOf(ticketTimeOutCallbackEnabled));
        return this;
    }

    /**
     * Sets the ticket response timeout(ms). This value is being used only if the ticket is sent blocking or {@link #setTicketTimeOutCallbackEnabled(boolean)} is set to <code>true</code>
     *
     * @param responseTimeout the ticket response timeout to set(ms)
     * @return {@link SdkConfigurationBuilder}
     */
    @Override
    public SdkConfigurationBuilder setTicketResponseTimeout(int responseTimeout) {
        properties.setProperty(SettingsKeys.TICKET_RESPONSE_TIMEOUT_LIVE, String.valueOf(responseTimeout));
        properties.setProperty(SettingsKeys.TICKET_RESPONSE_TIMEOUT_PREMATCH, String.valueOf(responseTimeout));
        return this;
    }

    /**
     * Sets the ticket response timeout(ms). This value is being used only if the ticket is sent blocking or {@link #setTicketTimeOutCallbackEnabled(boolean)} is set to <code>true</code>
     *
     * @param responseTimeout the ticket response timeout to set(ms)
     * @return {@link SdkConfigurationBuilder}
     */
    @Override
    public SdkConfigurationBuilder setTicketResponseTimeoutLive(int responseTimeout) {
        properties.setProperty(SettingsKeys.TICKET_RESPONSE_TIMEOUT_LIVE, String.valueOf(responseTimeout));
        return this;
    }

    /**
     * Sets the ticket response timeout(ms). This value is being used only if the ticket is sent blocking or {@link #setTicketTimeOutCallbackEnabled(boolean)} is set to <code>true</code>
     *
     * @param responseTimeout the ticket response timeout to set(ms)
     * @return {@link SdkConfigurationBuilder}
     */
    @Override
    public SdkConfigurationBuilder setTicketResponseTimeoutPrematch(int responseTimeout) {
        properties.setProperty(SettingsKeys.TICKET_RESPONSE_TIMEOUT_PREMATCH, String.valueOf(responseTimeout));
        return this;
    }

    /**
     * Sets the ticket cancellation response timeout(ms). This value is being used only if the ticket is sent blocking or {@link #setTicketTimeOutCallbackEnabled(boolean)} is set to <code>true</code>
     *
     * @param responseTimeout the ticket cancellation response timeout to set(ms)
     * @return {@link SdkConfigurationBuilder}
     */
    @Override
    public SdkConfigurationBuilder setTicketCancellationResponseTimeout(int responseTimeout) {
        properties.setProperty(SettingsKeys.TICKET_CANCELLATION_RESPONSE_TIMEOUT, String.valueOf(responseTimeout));
        return this;
    }

    /**
     * Sets the ticket cashout response timeout(ms). This value is being used only if the ticket is sent blocking or {@link #setTicketTimeOutCallbackEnabled(boolean)} is set to <code>true</code>
     *
     * @param responseTimeout the ticket cashout response timeout to set(ms)
     * @return {@link SdkConfigurationBuilder}
     */
    @Override
    public SdkConfigurationBuilder setTicketCashoutResponseTimeout(int responseTimeout) {
        properties.setProperty(SettingsKeys.TICKET_CASHOUT_RESPONSE_TIMEOUT, String.valueOf(responseTimeout));
        return this;
    }

    /**
     * Sets the ticket non-Sportradar response timeout(ms). This value is being used only if the ticket is sent blocking or {@link #setTicketTimeOutCallbackEnabled(boolean)} is set to <code>true</code>
     *
     * @param responseTimeout the ticket non-Sportradar response timeout to set(ms)
     * @return {@link SdkConfigurationBuilder}
     */
    @Override
    public SdkConfigurationBuilder setTicketNonSrSettleResponseTimeout(int responseTimeout) {
        properties.setProperty(SettingsKeys.TICKET_NON_SR_SETTLE_RESPONSE_TIMEOUT, String.valueOf(responseTimeout));
        return this;
    }

    /**
     * Sets whether the rabbit consumer channel should be exclusive
     *
     * @param exclusiveConsumer value to be set
     * @return {@link SdkConfigurationBuilder}
     */
    @Override
    public SdkConfigurationBuilder setExclusiveConsumer(boolean exclusiveConsumer) {
        properties.setProperty(SettingsKeys.EXCLUSIVE_CONSUMER, String.valueOf(exclusiveConsumer));
        return this;
    }

    /**
     * Sets the Keycloak host for authorization
     *
     * @param keycloakHost the Keycloak host to be set
     * @return {@link SdkConfigurationBuilder}
     */
    @Override
    public SdkConfigurationBuilder setKeycloakHost(String keycloakHost) {
        if(StringUtils.isNullOrEmpty(keycloakHost))
        {
            throw new IllegalArgumentException(SdkInfo.Literals.CONFIG_BUILDER_PARAM_EMPTY);
        }
        properties.setProperty(SettingsKeys.KEYCLOAK_HOST, keycloakHost);
        return this;
    }

    /**
     * Sets the username used to connect authenticate to Keycloak
     *
     * @param keycloakUsername the username used to connect authenticate to Keycloak
     * @return {@link SdkConfigurationBuilder}
     */
    @Override
    public SdkConfigurationBuilder setKeycloakUsername(String keycloakUsername) {
        if(StringUtils.isNullOrEmpty(keycloakUsername))
        {
            throw new IllegalArgumentException(SdkInfo.Literals.CONFIG_BUILDER_PARAM_EMPTY);
        }
        properties.setProperty(SettingsKeys.KEYCLOAK_USERNAME, keycloakUsername);
        return this;
    }

    /**
     * Sets the password used to connect authenticate to Keycloak
     *
     * @param keycloakPassword the password used to connect authenticate to Keycloak
     * @return {@link SdkConfigurationBuilder}
     */
    @Override
    public SdkConfigurationBuilder setKeycloakPassword(String keycloakPassword) {
        if(StringUtils.isNullOrEmpty(keycloakPassword))
        {
            throw new IllegalArgumentException(SdkInfo.Literals.CONFIG_BUILDER_PARAM_EMPTY);
        }
        properties.setProperty(SettingsKeys.KEYCLOAK_PASSWORD, keycloakPassword);
        return this;
    }

    /**
     * Sets the secret used to connect authenticate to Keycloak
     *
     * @param keycloakSecret the secret used to connect authenticate to Keycloak
     * @return {@link SdkConfigurationBuilder}
     */
    @Override
    public SdkConfigurationBuilder setKeycloakSecret(String keycloakSecret) {
        if(StringUtils.isNullOrEmpty(keycloakSecret))
        {
            throw new IllegalArgumentException(SdkInfo.Literals.CONFIG_BUILDER_PARAM_EMPTY);
        }
        properties.setProperty(SettingsKeys.KEYCLOAK_SECRET, keycloakSecret);
        return this;
    }

    /**
     * Sets the Client API host
     *
     * @param mtsClientApiHost the Client API host
     * @return {@link SdkConfigurationBuilder}
     */
    @Override
    public SdkConfigurationBuilder setMtsClientApiHost(String mtsClientApiHost) {
        if(StringUtils.isNullOrEmpty(mtsClientApiHost))
        {
            throw new IllegalArgumentException(SdkInfo.Literals.CONFIG_BUILDER_PARAM_EMPTY);
        }
        properties.setProperty(SettingsKeys.MTS_CLIENT_API_HOST, mtsClientApiHost);
        return this;
    }

    @Override
    public SdkConfigurationBuilder setUseWebSocket(boolean useWebSocket) {
        properties.setProperty(SettingsKeys.USE_WEB_SOCKET, String.valueOf(useWebSocket));
        return this;
    }

    @Override
    public SdkConfigurationBuilder setAuthServer(String authServer) {
        if(StringUtils.isNullOrEmpty(authServer))
        {
            throw new IllegalArgumentException(SdkInfo.Literals.CONFIG_BUILDER_PARAM_EMPTY);
        }
        properties.setProperty(SettingsKeys.AUTH_SERVER, authServer);
        return this;
    }

    @Override
    public SdkConfigurationBuilder setAuthClientId(String authClientId) {
        if(StringUtils.isNullOrEmpty(authClientId))
        {
            throw new IllegalArgumentException(SdkInfo.Literals.CONFIG_BUILDER_PARAM_EMPTY);
        }
        properties.setProperty(SettingsKeys.AUTH_CLIENT_ID, authClientId);
        return this;
    }

    @Override
    public SdkConfigurationBuilder setAuthClientSecret(String authClientSecret) {
        if(StringUtils.isNullOrEmpty(authClientSecret))
        {
            throw new IllegalArgumentException(SdkInfo.Literals.CONFIG_BUILDER_PARAM_EMPTY);
        }
        properties.setProperty(SettingsKeys.AUTH_CLIENT_SECRET, authClientSecret);
        return this;
    }

    @Override
    public SdkConfigurationBuilder setAuthAudience(String authAudience) {
        if(StringUtils.isNullOrEmpty(authAudience))
        {
            throw new IllegalArgumentException(SdkInfo.Literals.CONFIG_BUILDER_PARAM_EMPTY);
        }
        properties.setProperty(SettingsKeys.AUTH_AUDIENCE, authAudience);
        return this;
    }

    @Override
    public SdkConfigurationBuilder setAuthRequestTimeout(Duration authRequestTimeout) {
        if (authRequestTimeout == null)
        {
            throw new IllegalArgumentException(SdkInfo.Literals.CONFIG_BUILDER_PARAM_EMPTY);
        }
        properties.setProperty(SettingsKeys.AUTH_REQUEST_TIMEOUT, String.valueOf(authRequestTimeout));
        return this;
    }

    @Override
    public SdkConfigurationBuilder setAuthRetryDelay(Duration authRetryDelay) {
        if (authRetryDelay == null)
        {
            throw new IllegalArgumentException(SdkInfo.Literals.CONFIG_BUILDER_PARAM_EMPTY);
        }
        properties.setProperty(SettingsKeys.AUTH_RETRY_DELAY, String.valueOf(authRetryDelay));
        return this;
    }

    @Override
    public SdkConfigurationBuilder setWsServer(String wsServer) {
        if(StringUtils.isNullOrEmpty(wsServer))
        {
            throw new IllegalArgumentException(SdkInfo.Literals.CONFIG_BUILDER_PARAM_EMPTY);
        }
        properties.setProperty(SettingsKeys.WS_SERVER, wsServer);
        return this;
    }

    @Override
    public SdkConfigurationBuilder setWsReconnectTimeout(Duration wsReconnectTimeout) {
        if (wsReconnectTimeout == null)
        {
            throw new IllegalArgumentException(SdkInfo.Literals.CONFIG_BUILDER_PARAM_EMPTY);
        }
        properties.setProperty(SettingsKeys.WS_RECONNECT_TIMEOUT, String.valueOf(wsReconnectTimeout));
        return this;
    }

    @Override
    public SdkConfigurationBuilder setWsFetchMessageTimeout(Duration wsFetchMessageTimeout) {
        if (wsFetchMessageTimeout == null)
        {
            throw new IllegalArgumentException(SdkInfo.Literals.CONFIG_BUILDER_PARAM_EMPTY);
        }
        properties.setProperty(SettingsKeys.WS_FETCH_MESSAGE_TIMEOUT, String.valueOf(wsFetchMessageTimeout));
        return this;
    }

    @Override
    public SdkConfigurationBuilder setWsSendMessageTimeout(Duration wsSendMessageTimeout) {
        if (wsSendMessageTimeout == null)
        {
            throw new IllegalArgumentException(SdkInfo.Literals.CONFIG_BUILDER_PARAM_EMPTY);
        }
        properties.setProperty(SettingsKeys.WS_SEND_MESSAGE_TIMEOUT, String.valueOf(wsSendMessageTimeout));
        return this;
    }

    @Override
    public SdkConfigurationBuilder setWsReceiveMessageTimeout(Duration wsReceiveMessageTimeout) {
        if (wsReceiveMessageTimeout == null)
        {
            throw new IllegalArgumentException(SdkInfo.Literals.CONFIG_BUILDER_PARAM_EMPTY);
        }
        properties.setProperty(SettingsKeys.WS_RECEIVE_MESSAGE_TIMEOUT, String.valueOf(wsReceiveMessageTimeout));
        return this;
    }

    @Override
    public SdkConfigurationBuilder setWsConsumerGraceTimeout(Duration wsConsumerGraceTimeout) {
        if (wsConsumerGraceTimeout == null)
        {
            throw new IllegalArgumentException(SdkInfo.Literals.CONFIG_BUILDER_PARAM_EMPTY);
        }
        properties.setProperty(SettingsKeys.WS_CONSUMER_GRACE_TIMEOUT, String.valueOf(wsConsumerGraceTimeout));
        return this;
    }

    @Override
    public SdkConfigurationBuilder setWsRefreshConnectionTimeout(Duration wsRefreshConnectionTimeout) {
        if (wsRefreshConnectionTimeout == null)
        {
            throw new IllegalArgumentException(SdkInfo.Literals.CONFIG_BUILDER_PARAM_EMPTY);
        }
        properties.setProperty(SettingsKeys.WS_REFRESH_CONNECTION_TIMEOUT, String.valueOf(wsRefreshConnectionTimeout));
        return this;
    }

    @Override
    public SdkConfigurationBuilder setWsNumberOfConnections(int wsNumberOfConnections) {
        properties.setProperty(SettingsKeys.WS_NUMBER_OF_CONNECTIONS, String.valueOf(wsNumberOfConnections));
        return this;
    }

    @Override
    public SdkConfigurationBuilder setProtocolConnectTimeout(Duration protocolConnectTimeout) {
        if (protocolConnectTimeout == null)
        {
            throw new IllegalArgumentException(SdkInfo.Literals.CONFIG_BUILDER_PARAM_EMPTY);
        }
        properties.setProperty(SettingsKeys.PROTOCOL_CONNECT_TIMEOUT, String.valueOf(protocolConnectTimeout));
        return this;
    }

    @Override
    public SdkConfigurationBuilder setProtocolMaxSendBufferSize(int protocolMaxSendBufferSize) {
        properties.setProperty(SettingsKeys.PROTOCOL_MAX_SEND_BUFFER_SIZE, String.valueOf(protocolMaxSendBufferSize));
        return this;
    }

    @Override
    public SdkConfigurationBuilder setProtocolEnqueueTimeout(Duration protocolEnqueueTimeout) {
        if (protocolEnqueueTimeout == null)
        {
            throw new IllegalArgumentException(SdkInfo.Literals.CONFIG_BUILDER_PARAM_EMPTY);
        }
        properties.setProperty(SettingsKeys.PROTOCOL_ENQUEUE_TIMEOUT, String.valueOf(protocolEnqueueTimeout));
        return this;
    }

    @Override
    public SdkConfigurationBuilder setProtocolDequeueTimeout(Duration protocolDequeueTimeout) {
        if (protocolDequeueTimeout == null)
        {
            throw new IllegalArgumentException(SdkInfo.Literals.CONFIG_BUILDER_PARAM_EMPTY);
        }
        properties.setProperty(SettingsKeys.PROTOCOL_DEQUEUE_TIMEOUT, String.valueOf(protocolDequeueTimeout));
        return this;
    }

    @Override
    public SdkConfigurationBuilder setProtocolReceiveResponseTimeout(Duration protocolReceiveResponseTimeout) {
        if (protocolReceiveResponseTimeout == null)
        {
            throw new IllegalArgumentException(SdkInfo.Literals.CONFIG_BUILDER_PARAM_EMPTY);
        }
        properties.setProperty(SettingsKeys.PROTOCOL_RECEIVE_RESPONSE_TIMEOUT, String.valueOf(protocolReceiveResponseTimeout));
        return this;
    }

    @Override
    public SdkConfigurationBuilder setProtocolRetryCount(int protocolRetryCount) {
        properties.setProperty(SettingsKeys.PROTOCOL_RETRY_COUNT, String.valueOf(protocolRetryCount));
        return this;
    }

    @Override
    public SdkConfigurationBuilder setProtocolNumberOfDispatchers(int protocolNumberOfDispatchers) {
        properties.setProperty(SettingsKeys.PROTOCOL_NUMBER_OF_DISPATCHERS, String.valueOf(protocolNumberOfDispatchers));
        return this;
    }

    /**
     * Build and return the {@link SdkConfiguration}
     *
     * @return {@link SdkConfiguration}
     */
    @Override
    public SdkConfiguration build() {
        if(!properties.containsKey(SettingsKeys.USERNAME))
        {
            throw new IllegalArgumentException("Missing username");
        }
        if(!properties.containsKey(SettingsKeys.PASSWORD))
        {
            throw new IllegalArgumentException("Missing password");
        }
        if(!properties.containsKey(SettingsKeys.HOST))
        {
            throw new IllegalArgumentException("Missing host");
        }
        return new SdkConfigurationImpl(properties);
    }
}
