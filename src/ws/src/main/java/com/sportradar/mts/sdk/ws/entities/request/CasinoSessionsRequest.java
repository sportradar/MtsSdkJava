package com.sportradar.mts.sdk.ws.entities.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sportradar.mts.sdk.ws.entities.common.CasinoContext;
import com.sportradar.mts.sdk.ws.entities.common.CasinoSession;

/**
 * Represents a request for casino sessions.
 */
public class CasinoSessionsRequest extends ContentRequest {

    @JsonProperty("sessions")
    private CasinoSession[] sessions;
    @JsonProperty("reportId")
    private String reportId;
    @JsonProperty("context")
    private CasinoContext context;

    /**
     * Creates a new instance of the CasinoSessionsRequest.Builder class.
     *
     * @return A new instance of the CasinoSessionsRequest.Builder class.
     */
    public static Builder newBuilder() {
        return new Builder();
    }

    /**
     * Gets the casino sessions.
     *
     * @return The casino sessions.
     */
    public CasinoSession[] getSessions() {
        return this.sessions;
    }

    /**
     * Sets the casino sessions.
     *
     * @param value The casino sessions to set.
     */
    public void setSessions(CasinoSession[] value) {
        this.sessions = value;
    }

    /**
     * Gets the report ID.
     *
     * @return The report ID.
     */
    public String getReportId() {
        return this.reportId;
    }

    /**
     * Sets the report ID.
     *
     * @param value The report ID to set.
     */
    public void setReportId(String value) {
        this.reportId = value;
    }

    /**
     * Gets the casino context.
     *
     * @return The casino context.
     */
    public CasinoContext getContext() {
        return this.context;
    }

    /**
     * Sets the casino context.
     *
     * @param value The casino context to set.
     */
    public void setContext(CasinoContext value) {
        this.context = value;
    }

    /**
     * Builder class for creating instances of the CasinoSessionsRequest class.
     */
    public static class Builder {

        private final CasinoSessionsRequest instance = new CasinoSessionsRequest();

        private Builder() {
        }

        /**
         * Builds the CasinoSessionsRequest instance.
         *
         * @return The built CasinoSessionsRequest instance.
         */
        public CasinoSessionsRequest build() {
            return this.instance;
        }

        /**
         * Sets the casino sessions.
         *
         * @param value The casino sessions to set.
         * @return The Builder instance.
         */
        public Builder setSessions(CasinoSession... value) {
            this.instance.setSessions(value);
            return this;
        }

        /**
         * Sets the report ID.
         *
         * @param value The report ID to set.
         * @return The Builder instance.
         */
        public Builder setReportId(String value) {
            this.instance.setReportId(value);
            return this;
        }

        /**
         * Sets the casino context.
         *
         * @param value The casino context to set.
         * @return The Builder instance.
         */
        public Builder setContext(CasinoContext value) {
            this.instance.setContext(value);
            return this;
        }
    }
}
