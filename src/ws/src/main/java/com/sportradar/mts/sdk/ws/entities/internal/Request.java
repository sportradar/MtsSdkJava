package com.sportradar.mts.sdk.ws.entities.internal;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sportradar.mts.sdk.api.SdkTicket;

/**
 * Represents a request object used in the application.
 */
public class Request {

    @JsonProperty("correlationId")
    private String correlationId;
    @JsonProperty("timestampUtc")
    private long timestampUtc;
    @JsonProperty("operatorId")
    private long operatorId;
    @JsonProperty("operation")
    private String operation;
    @JsonProperty("version")
    private String version;
    @JsonProperty("content")
    private SdkTicket content;

    /**
     * Returns a new instance of the Request.Builder class.
     *
     * @return A new instance of the Request.Builder class.
     */
    public static Builder newBuilder() {
        return new Builder();
    }

    /**
     * Gets the correlation ID of the request.
     *
     * @return The correlation ID of the request.
     */
    public String getCorrelationId() {
        return this.correlationId;
    }

    /**
     * Sets the correlation ID of the request.
     *
     * @param value The correlation ID to set.
     */
    public void setCorrelationId(String value) {
        this.correlationId = value;
    }

    /**
     * Gets the UTC millis timestamp of the request.
     *
     * @return The UTC millis timestamp of the request.
     */
    public long getTimestampUtc() {
        return this.timestampUtc;
    }

    /**
     * Sets the UTC millis timestamp of the request.
     *
     * @param value The UTC millis timestamp to set.
     */
    public void setTimestampUtc(long value) {
        this.timestampUtc = value;
    }

    /**
     * Gets the operator ID of the request.
     *
     * @return The operator ID of the request.
     */
    public long getOperatorId() {
        return this.operatorId;
    }

    /**
     * Sets the operator ID of the request.
     *
     * @param value The operator ID to set.
     */
    public void setOperatorId(long value) {
        this.operatorId = value;
    }

    /**
     * Gets the operation of the request.
     *
     * @return The operation of the request.
     */
    public String getOperation() {
        return this.operation;
    }

    /**
     * Sets the operation of the request.
     *
     * @param value The operation to set.
     */
    public void setOperation(String value) {
        this.operation = value;
    }

    /**
     * Gets the version of the request.
     *
     * @return The version of the request.
     */
    public String getVersion() {
        return this.version;
    }

    /**
     * Sets the version of the request.
     *
     * @param value The version to set.
     */
    public void setVersion(String value) {
        this.version = value;
    }

    /**
     * Gets the content of the request.
     *
     * @return The content of the request.
     */
    public SdkTicket getContent() {
        return this.content;
    }

    /**
     * Sets the content of the request.
     *
     * @param value The content to set.
     */
    public void setContent(SdkTicket value) {
        this.content = value;
    }

    /**
     * Builder class for creating instances of the Request class.
     */
    public static class Builder {

        private final Request instance = new Request();

        private Builder() {
        }

        /**
         * Builds and returns the instance of the Request class.
         *
         * @return The instance of the Request class.
         */
        public Request build() {
            return this.instance;
        }

        /**
         * Sets the correlation ID of the request being built.
         *
         * @param value The correlation ID to set.
         * @return The current instance of the Builder class.
         */
        public Builder setCorrelationId(String value) {
            this.instance.setCorrelationId(value);
            return this;
        }

        /**
         * Sets the UTC millis timestamp of the request being built.
         *
         * @param value The UTC millis timestamp to set.
         * @return The current instance of the Builder class.
         */
        public Builder setTimestampUtc(long value) {
            this.instance.setTimestampUtc(value);
            return this;
        }

        /**
         * Sets the operator ID of the request being built.
         *
         * @param value The operator ID to set.
         * @return The current instance of the Builder class.
         */
        public Builder setOperatorId(long value) {
            this.instance.setOperatorId(value);
            return this;
        }

        /**
         * Sets the operation of the request being built.
         *
         * @param value The operation to set.
         * @return The current instance of the Builder class.
         */
        public Builder setOperation(String value) {
            this.instance.setOperation(value);
            return this;
        }

        /**
         * Sets the version of the request being built.
         *
         * @param value The version to set.
         * @return The current instance of the Builder class.
         */
        public Builder setVersion(String value) {
            this.instance.setVersion(value);
            return this;
        }

        /**
         * Sets the content of the request being built.
         *
         * @param value The content to set.
         * @return The current instance of the Builder class.
         */
        public Builder setContent(SdkTicket value) {
            this.instance.setContent(value);
            return this;
        }
    }
}