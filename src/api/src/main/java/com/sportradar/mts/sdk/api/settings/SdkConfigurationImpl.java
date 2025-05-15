/*
 * Copyright (C) Sportradar AG. See LICENSE for full license governing this code
 */

package com.sportradar.mts.sdk.api.settings;

import com.sportradar.mts.sdk.api.enums.SenderChannel;
import com.sportradar.mts.sdk.api.enums.UfEnvironment;
import com.sportradar.mts.sdk.api.exceptions.MtsPropertiesException;
import com.sportradar.mts.sdk.api.interfaces.SdkConfiguration;
import com.sportradar.mts.sdk.api.utils.FileUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.time.Duration;
import java.util.Properties;

import static com.google.common.base.Preconditions.checkNotNull;

public class SdkConfigurationImpl implements SdkConfiguration {
    private static final String DEFAULT_SETTINGS_FILE_NAME = "/mts-sdk.properties";

    private final String username;
    private final String password;
    private final String host;
    private final String vhost;
    private final int node;
    private final boolean ssl;
    private final int port;
    private final int bookmakerId;
    private final int limitId;
    private final String currency;
    private final SenderChannel senderChannel;
    private final String accessToken;
    private final boolean provideAdditionalMarketSpecifiers;
    private final int ticketResponseTimeoutLive;
    private final int ticketResponseTimeoutPrematch;
    private final int ticketCancellationResponseTimeout;
    private final int ticketCashoutResponseTimeout;
    private final int ticketNonSrSettleResponseTimeout;
    private final double messagesPerSecond;
    private final boolean ticketTimeOutCallbackEnabled;
    private final boolean exclusiveConsumer;
    private final String keycloakHost;
    private final String keycloakUsername;
    private final String keycloakPassword;
    private final String keycloakSecret;
    private final String mtsClientApiHost;
    private final UfEnvironment ufEnvironment;
    private final boolean useWebsocket; // todo dmuren properties as they should be
    private final URI authServer;
    private final String authClientId;
    private final String authClientSecret;
    private final String authAudience;
    private final Duration authRequestTimeout;
    private final Duration authRetryDelay;
    private final URI wsServer;
    private final Duration wsReconnectTimeout;
    private final Duration wsFetchMessageTimeout;
    private final Duration wsSendMessageTimeout;
    private final Duration wsReceiveMessageTimeout;
    private final Duration wsConsumerGraceTimeout;
    private final Duration wsRefreshConnectionTimeout;
    private final int wsNumberOfConnections;
    private final long operatorId;
    private final Duration protocolConnectTimeout;
    private final int protocolMaxSendBufferSize;
    private final Duration protocolEnqueueTimeout;
    private final Duration protocolDequeueTimeout;
    private final Duration protocolReceiveResponseTimeout;
    private final int protocolRetryCount;
    private final int protocolNumberOfDispatchers;

    @SuppressWarnings("java:S107") // Methods should not have too many parameters
    protected SdkConfigurationImpl(String username,
                                   String password,
                                   String host,
                                   String vhost,
                                   int node,
                                   boolean ssl,
                                   int port,
                                   int ticketResponseTimeoutLive,
                                   int ticketResponseTimeoutPrematch,
                                   int ticketCancellationResponseTimeout,
                                   int ticketCashoutResponseTimeout,
                                   int ticketNonSrSettleResponseTimeout,
                                   double messagesPerSecond,
                                   int bookmakerId,
                                   int limitId,
                                   String currency,
                                   SenderChannel senderChannel,
                                   String accessToken,
                                   UfEnvironment ufEnvironment,
                                   boolean provideAdditionalMarketSpecifiers,
                                   boolean ticketTimeOutCallbackEnabled,
                                   boolean exclusiveConsumer,
                                   String keycloakHost,
                                   String keycloakUsername,
                                   String keycloakPassword,
                                   String keycloakSecret,
                                   String mtsClientApiHost,
                                   boolean useWebsocket,
                                   URI authServer,
                                   String authClientId,
                                   String authClientSecret,
                                   String authAudience,
                                   Duration authRequestTimeout,
                                   Duration authRetryDelay,
                                   URI wsServer,
                                   Duration wsReconnectTimeout,
                                   Duration wsFetchMessageTimeout,
                                   Duration wsSendMessageTimeout,
                                   Duration wsReceiveMessageTimeout,
                                   Duration wsConsumerGraceTimeout,
                                   Duration wsRefreshConnectionTimeout,
                                   int wsNumberOfConnections,
                                   long operatorId,
                                   Duration protocolConnectTimeout,
                                   int protocolMaxSendBufferSize,
                                   Duration protocolEnqueueTimeout,
                                   Duration protocolDequeueTimeout,
                                   Duration protocolReceiveResponseTimeout,
                                   int protocolRetryCount,
                                   int protocolNumberOfDispatchers)
    {
        this.username = username;
        this.password = password;
        this.host = host;
        this.vhost = vhost;
        this.node = node;
        this.ssl = ssl;
        if (port > 0)
        {
            this.port = port;
        }
        else
        {
            this.port = ssl ? 5671 : 5672;
        }
        this.bookmakerId = bookmakerId;
        this.limitId = limitId;
        this.currency = currency;
        this.senderChannel = senderChannel;
        this.accessToken = accessToken;
        this.ufEnvironment = ufEnvironment;
        this.provideAdditionalMarketSpecifiers = provideAdditionalMarketSpecifiers;

        this.ticketResponseTimeoutLive = ticketResponseTimeoutLive;
        this.ticketResponseTimeoutPrematch = ticketResponseTimeoutPrematch;
        this.ticketCancellationResponseTimeout = ticketCancellationResponseTimeout;
        this.ticketCashoutResponseTimeout = ticketCashoutResponseTimeout;
        this.ticketNonSrSettleResponseTimeout = ticketNonSrSettleResponseTimeout;
        this.messagesPerSecond = messagesPerSecond;
        this.ticketTimeOutCallbackEnabled = ticketTimeOutCallbackEnabled;
        this.exclusiveConsumer = exclusiveConsumer;

        this.keycloakHost = keycloakHost;
        this.keycloakUsername = keycloakUsername;
        this.keycloakPassword = keycloakPassword;
        this.keycloakSecret = keycloakSecret;
        this.mtsClientApiHost = mtsClientApiHost;

        this.useWebsocket = useWebsocket;
        this.authServer = authServer;
        this.authClientId = authClientId;
        this.authClientSecret = authClientSecret;
        this.authAudience = authAudience;
        this.authRequestTimeout = authRequestTimeout;
        this.authRetryDelay = authRetryDelay;
        this.wsServer = wsServer;
        this.wsReconnectTimeout = wsReconnectTimeout;
        this.wsFetchMessageTimeout = wsFetchMessageTimeout;
        this.wsSendMessageTimeout = wsSendMessageTimeout;
        this.wsReceiveMessageTimeout = wsReceiveMessageTimeout;
        this.wsConsumerGraceTimeout = wsConsumerGraceTimeout;
        this.wsRefreshConnectionTimeout = wsRefreshConnectionTimeout;
        this.wsNumberOfConnections = wsNumberOfConnections;
        this.operatorId = operatorId;
        this.protocolConnectTimeout = protocolConnectTimeout;
        this.protocolMaxSendBufferSize = protocolMaxSendBufferSize;
        this.protocolEnqueueTimeout = protocolEnqueueTimeout;
        this.protocolDequeueTimeout = protocolDequeueTimeout;
        this.protocolReceiveResponseTimeout = protocolReceiveResponseTimeout;
        this.protocolRetryCount = protocolRetryCount;
        this.protocolNumberOfDispatchers = protocolNumberOfDispatchers;
    }

    protected SdkConfigurationImpl(Properties properties)
    {
        SdkConfiguration config = getConfigInternal(properties);
        this.username = config.getUsername();
        this.password = config.getPassword();
        this.host = config.getHost();
        this.vhost = config.getVirtualHost();
        this.node = config.getNode();
        this.ssl = config.getUseSsl();
        if (config.getPort() > 0)
        {
            this.port = config.getPort();
        }
        else
        {
            this.port = this.ssl ? 5671 : 5672;
        }
        this.bookmakerId = config.getBookmakerId();
        this.limitId = config.getLimitId();
        this.currency = config.getCurrency();
        this.senderChannel = config.getSenderChannel();
        this.accessToken = config.getAccessToken();
        this.ufEnvironment = config.getUfEnvironment();
        this.provideAdditionalMarketSpecifiers = config.getProvideAdditionalMarketSpecifiers();

        // also check PropertiesToSettingsMapper
        this.ticketResponseTimeoutLive = config.getTicketResponseTimeoutLive();
        this.ticketResponseTimeoutPrematch = config.getTicketResponseTimeoutPrematch();
        this.ticketCancellationResponseTimeout = config.getTicketCancellationResponseTimeout();
        this.ticketCashoutResponseTimeout = config.getTicketCashoutResponseTimeout();
        this.ticketNonSrSettleResponseTimeout = config.getTicketNonSrSettleResponseTimeout();
        this.messagesPerSecond = config.getMessagesPerSecond();

        this.ticketTimeOutCallbackEnabled = config.isTicketTimeOutCallbackEnabled();
        this.exclusiveConsumer = config.getExclusiveConsumer();

        this.keycloakHost = config.getKeycloakHost();
        this.keycloakUsername = config.getKeycloakUsername();
        this.keycloakPassword = config.getKeycloakPassword();
        this.keycloakSecret = config.getKeycloakSecret();
        this.mtsClientApiHost = config.getMtsClientApiHost();

        this.useWebsocket = config.getUseWebSocket();
        this.authServer = config.getAuthServer();
        this.authClientId = config.getAuthClientId();
        this.authClientSecret = config.getAuthClientSecret();
        this.authAudience = config.getAuthAudience();
        this.authRequestTimeout = config.getAuthRequestTimeout();
        this.authRetryDelay = config.getAuthRetryDelay();
        this.wsServer = config.getWsServer();
        this.wsReconnectTimeout = config.getWsReconnectTimeout();
        this.wsFetchMessageTimeout = config.getWsFetchMessageTimeout();
        this.wsSendMessageTimeout = config.getWsSendMessageTimeout();
        this.wsReceiveMessageTimeout = config.getWsReceiveMessageTimeout();
        this.wsConsumerGraceTimeout = config.getWsConsumerGraceTimeout();
        this.wsRefreshConnectionTimeout = config.getWsRefreshConnectionTimeout();
        this.wsNumberOfConnections = config.getWsNumberOfConnections();
        this.operatorId = config.getOperatorId();
        this.protocolConnectTimeout = config.getProtocolConnectTimeout();
        this.protocolMaxSendBufferSize = config.getProtocolMaxSendBufferSize();
        this.protocolEnqueueTimeout = config.getProtocolEnqueueTimeout();
        this.protocolDequeueTimeout = config.getProtocolDequeueTimeout();
        this.protocolReceiveResponseTimeout = config.getProtocolReceiveResponseTimeout();
        this.protocolRetryCount = config.getProtocolRetryCount();
        this.protocolNumberOfDispatchers = config.getProtocolNumberOfDispatchers();
    }

    @Override
    public int getNode() { return node; }

    @Override
    public String getVirtualHost() { return vhost; }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getHost() {
        return host;
    }

    @Override
    public int getPort() {
        return port;
    }

    @Override
    public boolean getUseSsl() {
        return ssl;
    }

    @Override
    public int getBookmakerId() { return bookmakerId; }

    @Override
    public int getLimitId() { return limitId; }

    @Override
    public String getCurrency() { return currency; }

    @Override
    public SenderChannel getSenderChannel() { return senderChannel; }

    @Override
    public String getAccessToken() { return accessToken; }

    @Override
    public UfEnvironment getUfEnvironment() {
        return ufEnvironment;
    }

    @Override
    public boolean getProvideAdditionalMarketSpecifiers() { return provideAdditionalMarketSpecifiers; }

    @Override
    public boolean isTicketTimeOutCallbackEnabled() {
        return ticketTimeOutCallbackEnabled;
    }

    public int getTicketResponseTimeoutLive() { return ticketResponseTimeoutLive; }

    public int getTicketResponseTimeoutPrematch() { return ticketResponseTimeoutPrematch; }

    public int getTicketCancellationResponseTimeout() { return ticketCancellationResponseTimeout; }

    public int getTicketCashoutResponseTimeout() { return ticketCashoutResponseTimeout; }

    public int getTicketNonSrSettleResponseTimeout() { return ticketNonSrSettleResponseTimeout; }

    public double getMessagesPerSecond() { return messagesPerSecond; }

    @Override
    public boolean getExclusiveConsumer() {
        return exclusiveConsumer;
    }

    @Override
    public String getKeycloakHost() {
        return keycloakHost;
    }

    @Override
    public String getKeycloakUsername() {
        return keycloakUsername;
    }

    @Override
    public String getKeycloakPassword() {
        return keycloakPassword;
    }

    @Override
    public String getKeycloakSecret() {
        return keycloakSecret;
    }

    @Override
    public String getMtsClientApiHost() {
        return mtsClientApiHost;
    }

    @Override
    public Boolean getUseWebSocket() {
        return useWebsocket;
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
    public String toString() {
        return "SdkConfiguration{" +
                "username='" + "*" + '\'' +
                ", password='" + "*" + '\'' +
                ", host='" + host + '\'' +
                ", node=" + node +
                ", vhost='" + vhost + '\'' +
                ", ssl=" + ssl +
                ", port=" + port +
                ", bookmakerId=" + bookmakerId +
                ", limitId=" + limitId +
                ", currency='" + currency + '\'' +
                ", senderChannel='" + senderChannel + '\'' +
                ", accessToken='*'" +
                ", ufEnvironment='" + ufEnvironment + '\'' +
                ", provideAdditionalMarketSpecifiers='" + provideAdditionalMarketSpecifiers + '\'' +
                ", ticketResponseTimeoutLive=" + ticketResponseTimeoutLive +
                ", ticketResponseTimeoutPrematch=" + ticketResponseTimeoutPrematch +
                ", ticketCancellationResponseTimeout=" + ticketCancellationResponseTimeout +
                ", ticketCashoutResponseTimeout=" + ticketCashoutResponseTimeout +
                ", ticketNonSrSettleResponseTimeout=" + ticketNonSrSettleResponseTimeout +
                ", messagesPerSecond=" + messagesPerSecond +
                ", exclusiveConsumer=" + exclusiveConsumer +
                ", keycloakHost='" + keycloakHost + '\'' +
                ", keycloakUsername='" + "*" + '\'' +
                ", keycloakPassword='" + "*" + '\'' +
                ", keycloakSecret='" + "*" + '\'' +
                ", mtsClientApiHost='" + mtsClientApiHost + '\'' +
                ", useWebsocket='" + useWebsocket + '\'' +
                ", authServer='" + authServer + '\'' +
                ", authClientId='" + "*" + '\'' +
                ", authClientSecret='" + "*" + '\'' +
                ", authAudience='" + authAudience + '\'' +
                ", authRequestTimeout='" + authRequestTimeout + '\'' +
                ", authRetryDelay='" + authRetryDelay + '\'' +
                ", wsServer='" + wsServer + '\'' +
                ", wsReconnectTimeout='" + wsReconnectTimeout + '\'' +
                ", wsFetchMessageTimeout='" + wsFetchMessageTimeout + '\'' +
                ", wsSendMessageTimeout='" + wsSendMessageTimeout + '\'' +
                ", wsReceiveMessageTimeout='" + wsReceiveMessageTimeout + '\'' +
                ", wsConsumerGraceTimeout='" + wsConsumerGraceTimeout + '\'' +
                ", wsRefreshConnectionTimeout='" + wsRefreshConnectionTimeout + '\'' +
                ", wsNumberOfConnections='" + wsNumberOfConnections + '\'' +
                ", operatorId='" + operatorId + '\'' +
                ", protocolConnectTimeout='" + protocolConnectTimeout + '\'' +
                ", protocolMaxSendBufferSize='" + protocolMaxSendBufferSize + '\'' +
                ", protocolEnqueueTimeout='" + protocolEnqueueTimeout + '\'' +
                ", protocolDequeueTimeout='" + protocolDequeueTimeout + '\'' +
                ", protocolReceiveResponseTimeout='" + protocolReceiveResponseTimeout + '\'' +
                ", protocolRetryCount='" + protocolRetryCount + '\'' +
                ", protocolNumberOfDispatchers='" + protocolNumberOfDispatchers + '\'' +
        '}';
    }

    /**
     * Gets the SdkConfiguration from the default settings file ("/mts-sdk.properties")
     * @return SdkConfiguration
     */
    public static SdkConfiguration getConfiguration() {
        try {
            final InputStream fileStream = SdkConfiguration.class.getResourceAsStream(DEFAULT_SETTINGS_FILE_NAME);
            return getConfigWithFile(fileStream);
        } catch (IOException e) {
            throw new MtsPropertiesException(e.getMessage(), e);
        }
    }

    public static SdkConfiguration getConfiguration(String path) {
        try {
            return getConfigWithFile(FileUtils.filePathAsInputStream(path));
        } catch (IOException e) {
            throw new MtsPropertiesException(e.getMessage(), e);
        }
    }

    public static SdkConfiguration getConfiguration(Properties properties) {
        return getConfigInternal(properties);
    }

    /**
     * Gets the SdkConfiguration from the 'application.yml' file located in the resources folder
     *
     * @return {@link SdkConfiguration}
     */
    public static SdkConfiguration getConfigurationFromYaml() {
        SdkYamlConfigurationReader sdkYamlConfigurationReader = new SdkYamlConfigurationReader();
        Properties propertiesFromYaml = sdkYamlConfigurationReader.readPropertiesFromYaml();

        return getConfigInternal(propertiesFromYaml);
    }

    private static SdkConfiguration getConfigWithFile(InputStream inputStream) throws IOException {
        Properties properties = new Properties();
        checkNotNull(inputStream, "properties file not found");
        properties.load(inputStream);
        return getConfigInternal(properties);
    }

    private static SdkConfiguration getConfigInternal(Properties properties) {
        checkNotNull(properties, "properties cannot be null");
        return PropertiesToSettingsMapper.getSettings(properties);
    }
}
