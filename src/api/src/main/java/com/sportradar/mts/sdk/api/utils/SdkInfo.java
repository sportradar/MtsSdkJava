/*
 * Copyright (C) Sportradar AG. See LICENSE for full license governing this code
 */

package com.sportradar.mts.sdk.api.utils;

import com.sportradar.mts.sdk.api.interfaces.SdkConfiguration;

import java.io.InputStream;
import java.util.Properties;

public final class SdkInfo {
    public static final String MTS_TICKET_VERSION = "2.4";
    public static final int RABBIT_PREFETCH_COUNT = 10;
    public static final int TICKET_RESPONSE_TIMEOUT_LIVE_DEFAULT = 17000;
    public static final int TICKET_RESPONSE_TIMEOUT_PREMATCH_DEFAULT = 5000;
    public static final int TICKET_CANCELLATION_RESPONSE_TIMEOUT_DEFAULT = 600000;
    public static final int TICKET_CASHOUT_RESPONSE_TIMEOUT_DEFAULT = 600000;
    public static final int TICKET_NON_SR_RESPONSE_TIMEOUT_DEFAULT = 600000;
    public static final int TICKET_RESPONSE_TIMEOUT_LIVE_MIN = 10000;
    public static final int TICKET_RESPONSE_TIMEOUT_PREMATCH_MIN = 3000;
    public static final int TICKET_CANCELLATION_RESPONSE_TIMEOUT_MIN = 10000;
    public static final int TICKET_CASHOUT_RESPONSE_TIMEOUT_MIN = 10000;
    public static final int TICKET_NON_SR_RESPONSE_TIMEOUT_MIN = 10000;
    public static final int TICKET_RESPONSE_TIMEOUT_LIVE_MAX = 30000;
    public static final int TICKET_RESPONSE_TIMEOUT_PREMATCH_MAX = 30000;
    public static final int TICKET_CANCELLATION_RESPONSE_TIMEOUT_MAX = 3600000;
    public static final int TICKET_CASHOUT_RESPONSE_TIMEOUT_MAX = 3600000;
    public static final int TICKET_NON_SR_RESPONSE_TIMEOUT_MAX = 3600000;
    public static final String API_HOST_INTEGRATION = "https://global.stgapi.betradar.com";
    public static final String API_HOST_PRODUCTION = "https://global.api.betradar.com";

    public static class Literals{
        public static final String CONFIG_BUILDER_PARAM_EMPTY = "Value cannot be a null reference or an empty string";
        public static final String CONFIG_BUILDER_PARAM_ZERO = "Value must be greater than zero";

        public static final String TICKET_HANDLER_SENDER_CLOSED = "Sender is closed";
        public static final String TICKET_HANDLER_TICKET_ACK_NULL = "TicketAcknowledgment cannot be null";
        public static final String TICKET_HANDLER_TICKET_CANCEL_ACK_NULL = "TicketCancelAcknowledgment cannot be null";
        public static final String TICKET_HANDLER_TICKET_NULL = "Ticket cannot be null";
        public static final String TICKET_HANDLER_TICKET_CASHOUT_NULL = "TicketCashout cannot be null";
        public static final String TICKET_HANDLER_TICKET_CANCEL_NULL = "TicketCancel cannot be null";
        public static final String TICKET_HANDLER_TICKET_NONSR_NULL = "ticketNonSrSettle cannot be null";
        public static final String TICKET_HANDLER_TICKET_REOFFER_CANCEL_NULL = "TicketReofferCancel cannot be null";
    }

    private SdkInfo() { throw new IllegalStateException("SdkInfo class"); }

    public static String getVersion()
    {
        try {
            InputStream is = SdkConfiguration.class.getResourceAsStream("/version/sdk.properties");
            Properties props = new Properties();
            props.load(is);
            is.close();
            return props.getProperty("version");
        } catch (Exception e) {
            return "0.0.0.0";
        }
    }
}
