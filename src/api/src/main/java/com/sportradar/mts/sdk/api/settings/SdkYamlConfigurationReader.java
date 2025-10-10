/*
 * Copyright (C) Sportradar AG. See LICENSE for full license governing this code
 */

package com.sportradar.mts.sdk.api.settings;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.sportradar.mts.sdk.api.exceptions.MtsYamlException;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;

/**
 * Sdk yaml configuration reader
 */
class SdkYamlConfigurationReader {
    private static final String ROOT_SPORTRADAR_TAG = "sportradar";
    private static final String SECOND_LEVEL_TAG = "sdk";
    private static final String THIRD_LEVEL_TAG = "mts";

    /**
     * Reads the SDK {@link Properties} from the default "application.yml" file
     *
     * @return the built {@link Properties}
     */
    Properties readPropertiesFromYaml() {
        return readPropertiesFromYaml("application.yml");
    }

    /**
     * Reads the SDK {@link Properties} from the provided file
     *
     * @param sdkYamlFilename the file from which the properties should be red. The file should be located in the resources folder
     * @return the built {@link Properties}
     */
    Properties readPropertiesFromYaml(String sdkYamlFilename) {
        Preconditions.checkArgument(!Strings.isNullOrEmpty(sdkYamlFilename));

        isYamlReaderDependencyPresent();

        InputStream in = getClass().getClassLoader().getResourceAsStream(sdkYamlFilename);
        if (in != null) {
            Yaml yaml = new Yaml();
            Iterable<Object> objects = yaml.loadAll(in);
            return tryFindSrYamlProperties(objects);
        }

        return handleException("Could not find " + sdkYamlFilename + " file");
    }

    /**
     * Tries to find the root YAML property in which the SDK configuration should be provided, if the object is found
     * the {@link Properties} get built and returned
     *
     * @param objects an {@link Iterable} which may contain the SDK configuration
     * @return a newly build {@link Properties} instance
     */
    @SuppressWarnings("unchecked")
    private Properties tryFindSrYamlProperties(Iterable<Object> objects) {
        for (Object object : objects) {
            if (object instanceof Map) {
                Map<String, Object> castedMap = (Map<String, Object>) object;
                if (castedMap.containsKey(ROOT_SPORTRADAR_TAG) && castedMap.get(ROOT_SPORTRADAR_TAG) instanceof Map) {
                    return extractProperties((Map<String, Object>) castedMap.get(ROOT_SPORTRADAR_TAG));
                }
            }
        }

        return handleException("Could not find the required root property '" + ROOT_SPORTRADAR_TAG + "'");
    }

    /**
     * Provides a new {@link Properties} instance which will be built from the provided {@link Map}
     *
     * @param objects the parsed YAML objects from which the {@link Properties} should be built
     * @return the newly built {@link Properties} instance
     */
    @SuppressWarnings("unchecked")
    private static Properties extractProperties(Map<String, Object> objects) {
        Object sdkObject = objects.get(SECOND_LEVEL_TAG);
        if (!(sdkObject instanceof Map)) {
            return handleException("Could not find '" + SECOND_LEVEL_TAG + "' second level yml property");
        }

        Object ufObject = ((Map<String, Object>) sdkObject).get(THIRD_LEVEL_TAG);
        if (!(ufObject instanceof Map)) {
            return handleException("Could not find '" + THIRD_LEVEL_TAG + "' third level yml property");
        }

        Map<String, Object> sdkConfiguration = (Map<String, Object>) ufObject;

        return mapYamlEntriesToSdkProperties(sdkConfiguration);
    }

    /**
     * Maps the provided YAML configuration to a {@link Properties} instance
     *
     * @param sdkConfiguration the {@link Map} configuration which should be mapped to the {@link Properties}
     * @return a new {@link Properties} instance populated from the provided {@link Map}
     */
    @SuppressWarnings("unchecked")
    private static Properties mapYamlEntriesToSdkProperties(Map<String, Object> sdkConfiguration) {
        Properties result = new Properties();

        handlePossibleProperty(result, sdkConfiguration, "username", SettingsKeys.USERNAME);
        handlePossibleProperty(result, sdkConfiguration, "password", SettingsKeys.PASSWORD);
        handlePossibleProperty(result, sdkConfiguration, "hostname", SettingsKeys.HOST);
        handlePossibleProperty(result, sdkConfiguration, "vhost", SettingsKeys.VIRTUAL_HOST);
        handlePossibleProperty(result, sdkConfiguration, "ssl", SettingsKeys.SSL);
        handlePossibleProperty(result, sdkConfiguration, "port", SettingsKeys.PORT);
        handlePossibleProperty(result, sdkConfiguration, "node", SettingsKeys.NODE_ID);
        handlePossibleProperty(result, sdkConfiguration, "bookmakerId", SettingsKeys.BOOKMAKER_ID);
        handlePossibleProperty(result, sdkConfiguration, "limitId", SettingsKeys.LIMIT_ID);
        handlePossibleProperty(result, sdkConfiguration, "currency", SettingsKeys.CURRENCY);
        handlePossibleProperty(result, sdkConfiguration, "channel", SettingsKeys.CHANNEL);
        handlePossibleProperty(result, sdkConfiguration, "accessToken", SettingsKeys.ACCESS_TOKEN);
        handlePossibleProperty(result, sdkConfiguration, "ufEnvironment", SettingsKeys.UF_ENVIRONMENT);
        handlePossibleProperty(result, sdkConfiguration, "provideAdditionalMarketSpecifiers", SettingsKeys.PROVIDE_ADDITIONAL_MARKET_SPECIFIERS);
        handlePossibleProperty(result, sdkConfiguration, "ticketResponseTimeout", SettingsKeys.TICKET_RESPONSE_TIMEOUT_LIVE);
        handlePossibleProperty(result, sdkConfiguration, "ticketResponseTimeoutPrematch", SettingsKeys.TICKET_RESPONSE_TIMEOUT_PREMATCH);
        handlePossibleProperty(result, sdkConfiguration, "ticketCancellationResponseTimeout", SettingsKeys.TICKET_CANCELLATION_RESPONSE_TIMEOUT);
        handlePossibleProperty(result, sdkConfiguration, "ticketCashoutResponseTimeout", SettingsKeys.TICKET_CASHOUT_RESPONSE_TIMEOUT);
        handlePossibleProperty(result, sdkConfiguration, "ticketNonSrSettleResponseTimeout", SettingsKeys.TICKET_NON_SR_SETTLE_RESPONSE_TIMEOUT);
        handlePossibleProperty(result, sdkConfiguration, "messagesPerSecond", SettingsKeys.MESSAGES_PER_SECOND);
        handlePossibleProperty(result, sdkConfiguration, "ticketTimeoutCallbackEnabled", SettingsKeys.TICKET_TIMEOUT_CALLBACK_ENABLED);
        handlePossibleProperty(result, sdkConfiguration, "exclusiveConsumer", SettingsKeys.EXCLUSIVE_CONSUMER);
        handlePossibleProperty(result, sdkConfiguration, "keycloakHost", SettingsKeys.KEYCLOAK_HOST);
        handlePossibleProperty(result, sdkConfiguration, "keycloakUsername", SettingsKeys.KEYCLOAK_USERNAME);
        handlePossibleProperty(result, sdkConfiguration, "keycloakPassword", SettingsKeys.KEYCLOAK_PASSWORD);
        handlePossibleProperty(result, sdkConfiguration, "keycloakSecret", SettingsKeys.KEYCLOAK_SECRET);
        handlePossibleProperty(result, sdkConfiguration, "mtsClientApiHost", SettingsKeys.MTS_CLIENT_API_HOST);
        handlePossibleProperty(result, sdkConfiguration, "useWebSocket", SettingsKeys.USE_WEB_SOCKET);
        handlePossibleProperty(result, sdkConfiguration, "authServer", SettingsKeys.AUTH_SERVER);
        handlePossibleProperty(result, sdkConfiguration, "authClientId", SettingsKeys.AUTH_CLIENT_ID);
        handlePossibleProperty(result, sdkConfiguration, "authClientSecret", SettingsKeys.AUTH_CLIENT_SECRET);
        handlePossibleProperty(result, sdkConfiguration, "authAudience", SettingsKeys.AUTH_AUDIENCE);
        handlePossibleProperty(result, sdkConfiguration, "authRequestTimeout", SettingsKeys.AUTH_REQUEST_TIMEOUT);
        handlePossibleProperty(result, sdkConfiguration, "authRetryDelay", SettingsKeys.AUTH_RETRY_DELAY);
        handlePossibleProperty(result, sdkConfiguration, "wsServer", SettingsKeys.WS_SERVER);
        handlePossibleProperty(result, sdkConfiguration, "wsReconnectTimeout", SettingsKeys.WS_RECONNECT_TIMEOUT);
        handlePossibleProperty(result, sdkConfiguration, "wsFetchMessageTimeout", SettingsKeys.WS_FETCH_MESSAGE_TIMEOUT);
        handlePossibleProperty(result, sdkConfiguration, "wsSendMessageTimeout", SettingsKeys.WS_SEND_MESSAGE_TIMEOUT);
        handlePossibleProperty(result, sdkConfiguration, "wsReceiveMessageTimeout", SettingsKeys.WS_RECEIVE_MESSAGE_TIMEOUT);
        handlePossibleProperty(result, sdkConfiguration, "wsConsumerGraceTimeout", SettingsKeys.WS_CONSUMER_GRACE_TIMEOUT);
        handlePossibleProperty(result, sdkConfiguration, "wsRefreshConnectionTimeout", SettingsKeys.WS_REFRESH_CONNECTION_TIMEOUT);
        handlePossibleProperty(result, sdkConfiguration, "wsPingInterval", SettingsKeys.WS_PING_INTERVAL);
        handlePossibleProperty(result, sdkConfiguration, "wsNumberOfConnections", SettingsKeys.WS_NUMBER_OF_CONNECTIONS);
        handlePossibleProperty(result, sdkConfiguration, "protocolConnectTimeout", SettingsKeys.PROTOCOL_CONNECT_TIMEOUT);
        handlePossibleProperty(result, sdkConfiguration, "protocolMaxSendBufferSize", SettingsKeys.PROTOCOL_MAX_SEND_BUFFER_SIZE);
        handlePossibleProperty(result, sdkConfiguration, "protocolEnqueueTimeout", SettingsKeys.PROTOCOL_ENQUEUE_TIMEOUT);
        handlePossibleProperty(result, sdkConfiguration, "protocolDequeueTimeout", SettingsKeys.PROTOCOL_DEQUEUE_TIMEOUT);
        handlePossibleProperty(result, sdkConfiguration, "protocolReceiveResponseTimeout", SettingsKeys.PROTOCOL_RECEIVE_RESPONSE_TIMEOUT);
        handlePossibleProperty(result, sdkConfiguration, "protocolRetryCount", SettingsKeys.PROTOCOL_RETRY_COUNT);
        handlePossibleProperty(result, sdkConfiguration, "protocolNumberOfDispatchers", SettingsKeys.PROTOCOL_NUMBER_OF_DISPATCHERS);

        return result;
    }

    /**
     * Checks for a possible property in the provided {@link Map} and populates the provided {@link Properties} with it,
     * if it exists
     *
     * @param result the {@link Properties} instance that should be populated with the property if it exists
     * @param sdkConfiguration the {@link Map} that should be checked for the property existence
     * @param key the property key that should be checked
     * @param propertiesKey the key which should be stored in the provided properties, if the value exists
     */
    private static void handlePossibleProperty(Properties result, Map<String, Object> sdkConfiguration, String key, String propertiesKey) {
        Optional.ofNullable(sdkConfiguration.get(key))
                .ifPresent(v -> result.put(propertiesKey, String.valueOf(v)));
    }

    /**
     * Checks if the required YAML parsing dependency is present
     */
    private static void isYamlReaderDependencyPresent() {
        try {
            Class.forName("org.yaml.snakeyaml.Yaml");
        } catch (Exception ex) {
            throw new MtsYamlException("Yaml configuration reader dependency missing", ex);
        }
    }

    /**
     * Handles the YAML reader exceptions
     *
     * @param message the message of the exception
     * @param <T> whatever needed
     * @return generic return so the method can always be used
     */
    private static <T> T handleException(String message) {
        throw new MtsYamlException(message);
    }
}
