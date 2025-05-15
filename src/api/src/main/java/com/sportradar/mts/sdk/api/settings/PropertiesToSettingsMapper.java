/*
 * Copyright (C) Sportradar AG. See LICENSE for full license governing this code
 */

package com.sportradar.mts.sdk.api.settings;

import com.google.common.base.Preconditions;
import com.sportradar.mts.sdk.api.enums.SenderChannel;
import com.sportradar.mts.sdk.api.enums.UfEnvironment;
import com.sportradar.mts.sdk.api.interfaces.SdkConfiguration;
import com.sportradar.mts.sdk.api.utils.SdkInfo;
import com.sportradar.mts.sdk.api.utils.StringUtils;

import java.net.URI;
import java.time.Duration;
import java.util.Properties;
import java.util.regex.Pattern;

public final class PropertiesToSettingsMapper {

    private static final String MISSING_PROPERTY = "{} missing from properties";

    private PropertiesToSettingsMapper() { throw new IllegalStateException("PropertiesToSettingsMapper class"); }

    @SuppressWarnings("java:S3776") // Cognitive Complexity of methods should not be too high
    public static SdkConfiguration getSettings(Properties properties) {
        String username = properties.getProperty(SettingsKeys.USERNAME);
        String password = properties.getProperty(SettingsKeys.PASSWORD);
        String host = properties.getProperty(SettingsKeys.HOST);
        String vHost = properties.getProperty(SettingsKeys.VIRTUAL_HOST);
        String portString = properties.getProperty(SettingsKeys.PORT);
        String nodeString = properties.getProperty(SettingsKeys.NODE_ID);
        String sslString = properties.getProperty(SettingsKeys.SSL);
        String bookmakerIdString = properties.getProperty(SettingsKeys.BOOKMAKER_ID);
        String limitIdString = properties.getProperty(SettingsKeys.LIMIT_ID);
        String currency = properties.getProperty(SettingsKeys.CURRENCY);
        String channelString = properties.getProperty(SettingsKeys.CHANNEL);
        String accessTokenString = properties.getProperty(SettingsKeys.ACCESS_TOKEN);
        String ufEnvironmentString = properties.getProperty(SettingsKeys.UF_ENVIRONMENT);
        String provideAdditionalMarketSpecifiersString = properties.getProperty(SettingsKeys.PROVIDE_ADDITIONAL_MARKET_SPECIFIERS);

        String ticketResponseTimeoutLiveString = properties.getProperty(SettingsKeys.TICKET_RESPONSE_TIMEOUT_LIVE);
        String ticketResponseTimeoutPrematchString = properties.getProperty(SettingsKeys.TICKET_RESPONSE_TIMEOUT_PREMATCH);
        String ticketCancellationResponseTimeoutString = properties.getProperty(SettingsKeys.TICKET_CANCELLATION_RESPONSE_TIMEOUT);
        String ticketCashoutResponseTimeoutString = properties.getProperty(SettingsKeys.TICKET_CASHOUT_RESPONSE_TIMEOUT);
        String ticketNonSrSettleResponseTimeoutString = properties.getProperty(SettingsKeys.TICKET_NON_SR_SETTLE_RESPONSE_TIMEOUT);
        String messagesPerSecondString = properties.getProperty(SettingsKeys.MESSAGES_PER_SECOND);

        String ticketTimeoutCallbackEnabledString = properties.getProperty(SettingsKeys.TICKET_TIMEOUT_CALLBACK_ENABLED);

        String exclusiveConsumerString = properties.getProperty(SettingsKeys.EXCLUSIVE_CONSUMER);

        String keycloakHost = properties.getProperty(SettingsKeys.KEYCLOAK_HOST);
        String keycloakUsername = properties.getProperty(SettingsKeys.KEYCLOAK_USERNAME);
        String keycloakPassword = properties.getProperty(SettingsKeys.KEYCLOAK_PASSWORD);
        String keycloakSecret = properties.getProperty(SettingsKeys.KEYCLOAK_SECRET);
        String mtsClientApiHost = properties.getProperty(SettingsKeys.MTS_CLIENT_API_HOST);

        String useWebSocketString = properties.getProperty(SettingsKeys.USE_WEB_SOCKET);
        String authServerString = properties.getProperty(SettingsKeys.AUTH_SERVER);
        String authClientIdString = properties.getProperty(SettingsKeys.AUTH_CLIENT_ID);
        String authClientSecretString = properties.getProperty(SettingsKeys.AUTH_CLIENT_SECRET);
        String authAudienceString = properties.getProperty(SettingsKeys.AUTH_AUDIENCE);
        String authRequestTimeoutString = properties.getProperty(SettingsKeys.AUTH_REQUEST_TIMEOUT);
        String authRetryDelayString = properties.getProperty(SettingsKeys.AUTH_RETRY_DELAY);
        String wsServerString = properties.getProperty(SettingsKeys.WS_SERVER);
        String wsReconnectTimeoutString = properties.getProperty(SettingsKeys.WS_RECONNECT_TIMEOUT);
        String wsFetchMessageTimeoutString = properties.getProperty(SettingsKeys.WS_FETCH_MESSAGE_TIMEOUT);
        String wsSendMessageTimeoutString = properties.getProperty(SettingsKeys.WS_SEND_MESSAGE_TIMEOUT);
        String wsReceiveMessageTimeoutString = properties.getProperty(SettingsKeys.WS_RECEIVE_MESSAGE_TIMEOUT);
        String wsConsumerGraceTimeoutString = properties.getProperty(SettingsKeys.WS_CONSUMER_GRACE_TIMEOUT);
        String wsRefreshConnectionTimeoutString = properties.getProperty(SettingsKeys.WS_REFRESH_CONNECTION_TIMEOUT);
        String wsNumberOfConnectionsString = properties.getProperty(SettingsKeys.WS_NUMBER_OF_CONNECTIONS);
        String operatorIdString = properties.getProperty(SettingsKeys.OPERATOR_ID);
        String protocolConnectTimeoutString = properties.getProperty(SettingsKeys.PROTOCOL_CONNECT_TIMEOUT);
        String protocolMaxSendBufferSizeString = properties.getProperty(SettingsKeys.PROTOCOL_MAX_SEND_BUFFER_SIZE);
        String protocolEnqueueTimeoutString = properties.getProperty(SettingsKeys.PROTOCOL_ENQUEUE_TIMEOUT);
        String protocolDequeueTimeoutString = properties.getProperty(SettingsKeys.PROTOCOL_DEQUEUE_TIMEOUT);
        String protocolReceiveResponseTimeoutString = properties.getProperty(SettingsKeys.PROTOCOL_RECEIVE_RESPONSE_TIMEOUT);
        String protocolRetryCountString = properties.getProperty(SettingsKeys.PROTOCOL_RETRY_COUNT);
        String protocolNumberOfDispatchersString = properties.getProperty(SettingsKeys.PROTOCOL_NUMBER_OF_DISPATCHERS);

        Preconditions.checkNotNull(username, StringUtils.format(MISSING_PROPERTY, SettingsKeys.USERNAME));
        Preconditions.checkArgument(!username.isEmpty());
        Preconditions.checkNotNull(password, StringUtils.format(MISSING_PROPERTY, SettingsKeys.PASSWORD));
        Preconditions.checkArgument(!password.isEmpty());
        Preconditions.checkNotNull(host, StringUtils.format(MISSING_PROPERTY, SettingsKeys.HOST));
        Preconditions.checkArgument(!host.contains(":"), StringUtils.format("{} can not contain port number. Only domain name or ip. E.g. mtsgate-ci.betradar.com", SettingsKeys.HOST));
        Preconditions.checkArgument(!host.isEmpty());

        boolean ssl = true;
        if (sslString != null) {
            Preconditions.checkArgument(isBoolean(sslString), "ssl should be boolean");
            ssl = Boolean.valueOf(sslString);
        }

        if(StringUtils.isNullOrEmpty(vHost))
        {
            vHost = "/" + username;
        }
        if(!vHost.startsWith("/"))
        {
            vHost = "/" + vHost;
        }

        Integer port = ssl ? 5671 : 5672;
        if (portString != null) {
            Preconditions.checkArgument(isDecimal(portString), "port should be a number");
            port = Integer.valueOf(portString);
        }

        int nodeId = 1;
        if (nodeString != null) {
            Preconditions.checkArgument(isDecimal(nodeString), "node should be a number");
            nodeId = Integer.valueOf(nodeString);
        }

        int ticketResponseTimeoutLive = SdkInfo.TICKET_RESPONSE_TIMEOUT_LIVE_DEFAULT;
        if (ticketResponseTimeoutLiveString != null) {
            Preconditions.checkArgument(isDecimal(ticketResponseTimeoutLiveString), "ticketResponseTimeoutLive should be a number");
            ticketResponseTimeoutLive = Integer.valueOf(ticketResponseTimeoutLiveString);

            Preconditions.checkArgument(ticketResponseTimeoutLive >= SdkInfo.TICKET_RESPONSE_TIMEOUT_LIVE_MIN, "ticketResponseTimeoutLive must be more than " + SdkInfo.TICKET_RESPONSE_TIMEOUT_LIVE_MIN + "ms");
            Preconditions.checkArgument(ticketResponseTimeoutLive <= SdkInfo.TICKET_RESPONSE_TIMEOUT_LIVE_MAX, "ticketResponseTimeoutLive must be less than " + SdkInfo.TICKET_RESPONSE_TIMEOUT_LIVE_MAX + "ms");
        }

        int ticketResponseTimeoutPrematch = SdkInfo.TICKET_RESPONSE_TIMEOUT_PREMATCH_DEFAULT;
        if (ticketResponseTimeoutPrematchString != null) {
            Preconditions.checkArgument(isDecimal(ticketResponseTimeoutPrematchString), "ticketResponseTimeoutPrematch should be a number");
            ticketResponseTimeoutPrematch = Integer.valueOf(ticketResponseTimeoutPrematchString);

            Preconditions.checkArgument(ticketResponseTimeoutPrematch >= SdkInfo.TICKET_RESPONSE_TIMEOUT_PREMATCH_MIN, "ticketResponseTimeoutPrematch must be more than " + SdkInfo.TICKET_RESPONSE_TIMEOUT_PREMATCH_MIN + "ms");
            Preconditions.checkArgument(ticketResponseTimeoutPrematch <= SdkInfo.TICKET_RESPONSE_TIMEOUT_PREMATCH_MAX, "ticketResponseTimeoutPrematch must be less than " + SdkInfo.TICKET_RESPONSE_TIMEOUT_PREMATCH_MAX + "ms");
        }

        int ticketCancellationResponseTimeout = 600000;
        if (ticketCancellationResponseTimeoutString != null) {
            Preconditions.checkArgument(isDecimal(ticketCancellationResponseTimeoutString), "ticketCancellationResponseTimeout should be a number");
            ticketCancellationResponseTimeout = Integer.valueOf(ticketCancellationResponseTimeoutString);

            Preconditions.checkArgument(ticketCancellationResponseTimeout >= 10000, "ticketCancellationResponseTimeout must be more than 10000ms");
            Preconditions.checkArgument(ticketCancellationResponseTimeout <= 3600000, "ticketCancellationResponseTimeout must be less than 3600000ms");
        }

        int ticketCashoutResponseTimeout = 600000;
        if (ticketCashoutResponseTimeoutString != null) {
            Preconditions.checkArgument(isDecimal(ticketCashoutResponseTimeoutString), "ticketCashoutResponseTimeout should be a number");
            ticketCashoutResponseTimeout = Integer.valueOf(ticketCashoutResponseTimeoutString);

            Preconditions.checkArgument(ticketCashoutResponseTimeout >= 10000, "ticketCashoutResponseTimeout must be more than 10000ms");
            Preconditions.checkArgument(ticketCashoutResponseTimeout <= 3600000, "ticketCashoutResponseTimeout must be less than 3600000ms");
        }

        int ticketNonSrSettleResponseTimeout = 600000;
        if (ticketNonSrSettleResponseTimeoutString != null) {
            Preconditions.checkArgument(isDecimal(ticketNonSrSettleResponseTimeoutString), "ticketNonSrSettleResponseTimeout should be a number");
            ticketNonSrSettleResponseTimeout = Integer.valueOf(ticketNonSrSettleResponseTimeoutString);

            Preconditions.checkArgument(ticketNonSrSettleResponseTimeout >= 10000, "ticketNonSrSettleResponseTimeout must be more than 10000ms");
            Preconditions.checkArgument(ticketNonSrSettleResponseTimeout <= 3600000, "ticketNonSrSettleResponseTimeout must be less than 3600000ms");
        }

        double messagesPerSecond = 40;
        if (messagesPerSecondString != null) {
            messagesPerSecond = Double.valueOf(messagesPerSecondString);
            Preconditions.checkArgument(messagesPerSecond > 0,"messages per second must be positive number");
        }

        int bookmakerId = 0;
        if (bookmakerIdString != null) {
            Preconditions.checkArgument(isDecimal(bookmakerIdString), "bookmakerId should be a number");
            bookmakerId = Integer.valueOf(bookmakerIdString);
            Preconditions.checkArgument(bookmakerId > 0, "bookmakerId should be greater then zero");
        }

        int limitId = 0;
        if (limitIdString != null) {
            Preconditions.checkArgument(isDecimal(limitIdString), "limitId should be a number");
            limitId = Integer.valueOf(limitIdString);
            Preconditions.checkArgument(limitId > 0, "limitId should be greater then zero");
        }
        if(currency != null)
        {
            Preconditions.checkArgument(!currency.isEmpty() && currency.length() >= 3 && currency.length() <= 4, "currency should be 3 or 4 letter sign");
        }

        SenderChannel channel = null;
        if(channelString != null)
        {
            channel = SenderChannel.valueOf(channelString);
        }

        String accessToken = null;
        if(!StringUtils.isNullOrEmpty(accessTokenString))
        {
            accessToken = accessTokenString;
        }

        UfEnvironment ufEnvironment = null;
        if (!StringUtils.isNullOrEmpty(ufEnvironmentString)) {
            ufEnvironment = UfEnvironment.fromString(ufEnvironmentString);
        }

        boolean provideAdditionalMarketSpecifiers = true;
        if(provideAdditionalMarketSpecifiersString != null)
        {
            Preconditions.checkArgument(isBoolean(provideAdditionalMarketSpecifiersString), "provideAdditionalMarketSpecifiers is not valid boolean value");
            if(provideAdditionalMarketSpecifiersString.equalsIgnoreCase("false"))
            {
                provideAdditionalMarketSpecifiers = false;
            }
        }

        boolean ticketTimeoutCallbackEnabled = false;
        if(ticketTimeoutCallbackEnabledString != null)
        {
            Preconditions.checkArgument(isBoolean(ticketTimeoutCallbackEnabledString), "ticketTimeoutCallbackEnabled is not valid boolean value");
            if(ticketTimeoutCallbackEnabledString.equalsIgnoreCase("true"))
            {
                ticketTimeoutCallbackEnabled = true;
            }
        }

        boolean exclusiveConsumer = true;
        if (exclusiveConsumerString != null) {
            Preconditions.checkArgument(isBoolean(exclusiveConsumerString), "exclusiveConsumer should be boolean");
            exclusiveConsumer = Boolean.valueOf(exclusiveConsumerString);
        }

        if (mtsClientApiHost != null) {
            Preconditions.checkNotNull(keycloakHost, StringUtils.format(MISSING_PROPERTY, SettingsKeys.KEYCLOAK_HOST));
            Preconditions.checkNotNull(keycloakSecret, StringUtils.format(MISSING_PROPERTY, SettingsKeys.KEYCLOAK_SECRET));
        }

        // todo dmuren default settings logic doublecheck
        boolean useWebSocket = false;
        if (useWebSocketString != null) {
            Preconditions.checkArgument(isBoolean(useWebSocketString), "useWebSocket should be boolean");
            useWebSocket = Boolean.valueOf(useWebSocketString);
        }

        URI authServer;
        if (authServerString != null) {
            authServer = URI.create(authServerString);
        } else {
            authServer = URI.create(keycloakHost);
        }

        String authClientId;
        if (authClientIdString != null) {
            authClientId = authClientIdString;
        } else {
            authClientId = "mts-client";
        }

        String authClientSecret;
        if (authClientSecretString != null) {
            authClientSecret = authClientSecretString;
        } else {
            authClientSecret = keycloakSecret;
        }

        String authAudience;
        if (authAudienceString != null) {
            authAudience = authAudienceString;
        } else {
            authAudience = "mts";
        }

        Duration authRequestTimeout;
        if (authRequestTimeoutString != null) {
            authRequestTimeout = Duration.ofMillis(Long.parseLong(authRequestTimeoutString));
        } else {
            authRequestTimeout = Duration.ofSeconds(30);
        }

        Duration authRetryDelay;
        if (authRetryDelayString != null) {
            authRetryDelay = Duration.ofMillis(Long.parseLong(authRetryDelayString));
        } else {
            authRetryDelay = Duration.ofSeconds(5);
        }

        URI wsServer;
        if (wsServerString != null) {
            wsServer = URI.create(wsServerString);
        } else {
            wsServer = URI.create(host);
        }

        Duration wsReconnectTimeout;
        if (wsReconnectTimeoutString != null) {
            wsReconnectTimeout = Duration.ofMillis(Long.parseLong(wsReconnectTimeoutString));
        } else {
            wsReconnectTimeout = Duration.ofSeconds(30);
        }

        Duration wsFetchMessageTimeout;
        if (wsFetchMessageTimeoutString != null) {
            wsFetchMessageTimeout = Duration.ofMillis(Long.parseLong(wsFetchMessageTimeoutString));
        } else {
            wsFetchMessageTimeout = Duration.ofSeconds(30);
        }

        Duration wsSendMessageTimeout;
        if (wsSendMessageTimeoutString != null) {
            wsSendMessageTimeout = Duration.ofMillis(Long.parseLong(wsSendMessageTimeoutString));
        } else {
            wsSendMessageTimeout = Duration.ofSeconds(30);
        }

        Duration wsReceiveMessageTimeout;
        if (wsReceiveMessageTimeoutString != null) {
            wsReceiveMessageTimeout = Duration.ofMillis(Long.parseLong(wsReceiveMessageTimeoutString));
        } else {
            wsReceiveMessageTimeout = Duration.ofSeconds(30);
        }

        Duration wsConsumerGraceTimeout;
        if (wsConsumerGraceTimeoutString != null) {
            wsConsumerGraceTimeout = Duration.ofMillis(Long.parseLong(wsConsumerGraceTimeoutString));
        } else {
            wsConsumerGraceTimeout = Duration.ofSeconds(30);
        }

        Duration wsRefreshConnectionTimeout;
        if (wsRefreshConnectionTimeoutString != null) {
            wsRefreshConnectionTimeout = Duration.ofMillis(Long.parseLong(wsRefreshConnectionTimeoutString));
        } else {
            wsRefreshConnectionTimeout = Duration.ofSeconds(30);
        }

        int wsNumberOfConnections;
        if (wsNumberOfConnectionsString != null) {
            wsNumberOfConnections = Integer.parseInt(wsNumberOfConnectionsString);
        } else {
            wsNumberOfConnections = 1;
        }

        long operatorId;
        if (operatorIdString != null) {
            operatorId = Long.parseLong(operatorIdString);
        } else {
            operatorId = 0;
        }

        Duration protocolConnectTimeout;
        if (protocolConnectTimeoutString != null) {
            protocolConnectTimeout = Duration.ofMillis(Long.parseLong(protocolConnectTimeoutString));
        } else {
            protocolConnectTimeout = Duration.ofSeconds(30);
        }

        int protocolMaxSendBufferSize;
        if (protocolMaxSendBufferSizeString != null) {
            protocolMaxSendBufferSize = Integer.parseInt(protocolMaxSendBufferSizeString);
        } else {
            protocolMaxSendBufferSize = 0;
        }

        Duration protocolEnqueueTimeout;
        if (protocolEnqueueTimeoutString != null) {
            protocolEnqueueTimeout = Duration.ofMillis(Long.parseLong(protocolEnqueueTimeoutString));
        } else {
            protocolEnqueueTimeout = Duration.ofSeconds(30);
        }

        Duration protocolDequeueTimeout;
        if (protocolDequeueTimeoutString != null) {
            protocolDequeueTimeout = Duration.ofMillis(Long.parseLong(protocolDequeueTimeoutString));
        } else {
            protocolDequeueTimeout = Duration.ofSeconds(30);
        }

        Duration protocolReceiveResponseTimeout;
        if (protocolReceiveResponseTimeoutString != null) {
            protocolReceiveResponseTimeout = Duration.ofMillis(Long.parseLong(protocolReceiveResponseTimeoutString));
        } else {
            protocolReceiveResponseTimeout = Duration.ofSeconds(30);
        }

        int protocolRetryCount;
        if (protocolRetryCountString != null) {
            protocolRetryCount = Integer.parseInt(protocolRetryCountString);
        } else {
            protocolRetryCount = 3;
        }

        int protocolNumberOfDispatchers;
        if (protocolNumberOfDispatchersString != null) {
            protocolNumberOfDispatchers = Integer.parseInt(protocolNumberOfDispatchersString);
        } else {
            protocolNumberOfDispatchers = 1;
        }

        return new SdkConfigurationImpl(username,
                password,
                host,
                vHost,
                nodeId,
                ssl,
                port,
                ticketResponseTimeoutLive,
                ticketResponseTimeoutPrematch,
                ticketCancellationResponseTimeout,
                ticketCashoutResponseTimeout,
                ticketNonSrSettleResponseTimeout,
                messagesPerSecond,
                bookmakerId,
                limitId,
                currency,
                channel,
                accessToken,
                ufEnvironment,
                provideAdditionalMarketSpecifiers,
                ticketTimeoutCallbackEnabled,
                exclusiveConsumer,
                keycloakHost,
                keycloakUsername,
                keycloakPassword,
                keycloakSecret,
                mtsClientApiHost,
                useWebSocket,
                authServer,
                authClientId,
                authClientSecret,
                authAudience,
                authRequestTimeout,
                authRetryDelay,
                wsServer,
                wsReconnectTimeout,
                wsFetchMessageTimeout,
                wsSendMessageTimeout,
                wsReceiveMessageTimeout,
                wsConsumerGraceTimeout,
                wsRefreshConnectionTimeout,
                wsNumberOfConnections,
                operatorId,
                protocolConnectTimeout,
                protocolMaxSendBufferSize,
                protocolEnqueueTimeout,
                protocolDequeueTimeout,
                protocolReceiveResponseTimeout,
                protocolRetryCount,
                protocolNumberOfDispatchers);
    }

    private static boolean isBoolean(String input) {
        return input.toLowerCase().matches("true|false");
    }

    private static boolean isDecimal(String input) {
        return Pattern.matches("^\\d+$", input);
    }
}
