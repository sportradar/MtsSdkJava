/*
 * Copyright (C) Sportradar AG. See LICENSE for full license governing this code
 */

package com.sportradar.mts.sdk.api.settings;

import com.sportradar.mts.sdk.api.Ticket;
import com.sportradar.mts.sdk.api.interfaces.TicketSender;

/**
 * Settings keys used in mapping properties object to SDK settings
 */
public final class SettingsKeys {

    private SettingsKeys() { throw new IllegalStateException("SettingsKeys class"); }

    private static final String PREFIX = "mts.sdk.";
    /**
     * Username to use when connecting to the MTS rabbit
     */
    public static final String USERNAME = PREFIX + "username";
    /**
     * Password to use when connecting to the MTS rabbit
     */
    public static final String PASSWORD = PREFIX + "password";
    /**
     * Hostname to use when connecting to the MTS rabbit
     */
    public static final String HOST = PREFIX + "hostname";
    /**
     * Virtual host to use when connecting to the MTS rabbit
     */
    public static final String VIRTUAL_HOST = PREFIX + "vhost";
    /**
     * Node id to be used when creating routing key
     */
    public static final String NODE_ID = PREFIX + "node";
    /**
     * Use SSL when connecting to MTS rabbit
     */
    public static final String SSL = PREFIX + "ssl";
    /**
     * Port to use when connecting to the MTS rabbit
     */
    public static final String PORT = PREFIX + "port";
    /**
     * Used only when sending with {@link TicketSender#sendBlocking(Ticket)}
     */
    public static final String TICKET_RESPONSE_TIMEOUT_LIVE = PREFIX + "ticketResponseTimeout";
    /**
     * Used only when sending with {@link TicketSender#sendBlocking(Ticket)}
     */
    public static final String TICKET_RESPONSE_TIMEOUT_PREMATCH = PREFIX + "ticketResponseTimeoutPrematch";
    /**
     * Used only when sending with {@link TicketSender#sendBlocking(Ticket)}
     */
    public static final String TICKET_CANCELLATION_RESPONSE_TIMEOUT = PREFIX + "ticketCancellationResponseTimeout";
    /**
     * Used only when sending with {@link TicketSender#sendBlocking(Ticket)}
     */
    public static final String TICKET_CASHOUT_RESPONSE_TIMEOUT = PREFIX + "ticketCashoutResponseTimeout";
    /**
     * Used only when sending with {@link TicketSender#sendBlocking(Ticket)}
     */
    public static final String TICKET_NON_SR_SETTLE_RESPONSE_TIMEOUT = PREFIX + "ticketNonSrSettleResponseTimeout";
    /**
     * Max messages allowed to be send to the MTS for each sender. Default 40
     */
    public static final String MESSAGES_PER_SECOND = PREFIX + "messages_per_second";
    /**
     * Gets the default sender bookmakerId
     */
    public static final String BOOKMAKER_ID = PREFIX + "bookmakerId";
    /**
     * Gets the default sender limitId
     */
    public static final String LIMIT_ID = PREFIX + "limitId";
    /**
     * Gets the default sender currency sign (3-letter ISO)
     */
    public static final String CURRENCY = PREFIX + "currency";
    /**
     * Gets the default sender channel (see SenderChannel for possible values)
     */
    public static final String CHANNEL = PREFIX + "channel";
    /**
     *  Gets the access token for the UoF REST API calls
     */
    public static final String ACCESS_TOKEN = PREFIX + "accessToken";
    /**
     *  Gets the access token for the UoF REST API calls
     */
    public static final String UF_ENVIRONMENT = PREFIX + "ufEnvironment";
    /**
     *  Gets the access token for the UoF REST API calls
     */
    public static final String PROVIDE_ADDITIONAL_MARKET_SPECIFIERS = PREFIX + "provideAdditionalMarketSpecifiers";
    /**
     * An indication if the tickets sent async should have a time-out callback
     */
    public static final String TICKET_TIMEOUT_CALLBACK_ENABLED = PREFIX + "ticketTimeoutCallbackEnabled";
    /**
     * Should the rabbit consumer channel be exclusive
     */
    public static final String EXCLUSIVE_CONSUMER = PREFIX + "exclusiveConsumer";
    /**
     * Gets the Keycloak host for authorization
     */
    public static final String KEYCLOAK_HOST = PREFIX + "keycloakHost";
    /**
     * Gets the username used to connect authenticate to Keycloak
     */
    public static final String KEYCLOAK_USERNAME = PREFIX + "keycloakUsername";
    /**
     * Gets the password used to connect authenticate to Keycloak
     */
    public static final String KEYCLOAK_PASSWORD = PREFIX + "keycloakPassword";
    /**
     * Gets the secret used to connect authenticate to Keycloak
     */
    public static final String KEYCLOAK_SECRET = PREFIX + "keycloakSecret";
    /**
     * Gets the Client API host
     */
    public static final String MTS_CLIENT_API_HOST = PREFIX + "mtsClientApiHost";
    /**
     * Gets the use WebSocket flag
     */
    public static final String USE_WEB_SOCKET = PREFIX + "useWebSocket";

    /**
     * Gets the URI of the authorization server used for authentication
     */
    public static final String AUTH_SERVER = PREFIX + "authServer";

    /**
     * Gets the client ID used for authentication
     */
    public static final String AUTH_CLIENT_ID = PREFIX + "authClientId";

    /**
     * Gets the client secret used for authentication
     */
    public static final String AUTH_CLIENT_SECRET = PREFIX + "authClientSecret";

    /**
     * Gets the audience for which the authentication token is requested
     */
    public static final String AUTH_AUDIENCE = PREFIX + "authAudience";

    /**
     * Gets the authorization request timeout
     */
    public static final String AUTH_REQUEST_TIMEOUT = PREFIX + "authRequestTimeout";

    /**
     * Gets the authorization retry delay
     */
    public static final String AUTH_RETRY_DELAY = PREFIX + "authRetryDelay";

    /**
     * Gets the WebSocket server URL
     */
    public static final String WS_SERVER = PREFIX + "wsServer";

    /**
     * Gets the WebSocket reconnect timeout
     */
    public static final String WS_RECONNECT_TIMEOUT = PREFIX + "wsReconnectTimeout";

    /**
     * Gets the WebSocket fetch message timeout
     */
    public static final String WS_FETCH_MESSAGE_TIMEOUT = PREFIX + "wsFetchMessageTimeout";

    /**
     * Gets the WebSocket send message timeout
     */
    public static final String WS_SEND_MESSAGE_TIMEOUT = PREFIX + "wsSendMessageTimeout";

    /**
     * Gets the WebSocket receive message timeout
     */
    public static final String WS_RECEIVE_MESSAGE_TIMEOUT = PREFIX + "wsReceiveMessageTimeout";

    /**
     * Gets the WebSocket consumer grace timeout
     */
    public static final String WS_CONSUMER_GRACE_TIMEOUT = PREFIX + "wsConsumerGraceTimeout";

    /**
     * Gets the WebSocket refresh connection timeout
     */
    public static final String WS_REFRESH_CONNECTION_TIMEOUT = PREFIX + "wsRefreshConnectionTimeout";

    /**
     * Gets the WebSocket number of connections
     */
    public static final String WS_NUMBER_OF_CONNECTIONS = PREFIX + "wsNumberOfConnections";

    /**
     * Gets the protocol connect timeout
     */
    public static final String PROTOCOL_CONNECT_TIMEOUT = PREFIX + "protocolConnectTimeout";

    /**
     * Gets the protocol max send buffer size
     */
    public static final String PROTOCOL_MAX_SEND_BUFFER_SIZE = PREFIX + "protocolMaxSendBufferSize";

    /**
     * Gets the protocol enqueue timeout
     */
    public static final String PROTOCOL_ENQUEUE_TIMEOUT = PREFIX + "protocolEnqueueTimeout";

    /**
     * Gets the protocol dequeue timeout
     */
    public static final String PROTOCOL_DEQUEUE_TIMEOUT = PREFIX + "protocolDequeueTimeout";

    /**
     * Gets the protocol receive response timeout
     */
    public static final String PROTOCOL_RECEIVE_RESPONSE_TIMEOUT = PREFIX + "protocolReceiveResponseTimeout";

    /**
     * Gets the protocol retry count
     */
    public static final String PROTOCOL_RETRY_COUNT = PREFIX + "protocolRetryCount";

    /**
     * Gets the protocol retry delay
     */
    public static final String PROTOCOL_NUMBER_OF_DISPATCHERS = PREFIX + "protocolNumberOfDispatchers";
}
