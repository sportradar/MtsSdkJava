package com.sportradar.mts.sdk.api.ws;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a request object used in the application.
 */
public class Request {

    @JsonProperty("operatorId")
    private long operatorId;
    @JsonProperty("operation")
    private String operation;
    @JsonProperty("content")
    private String content;
    @JsonProperty("correlationId")
    private String correlationId;

    /**
     * Returns a new instance of the Request.Builder class.
     *
     * @return A new instance of the Request.Builder class.
     */
    public static Builder newBuilder() {
        return new Builder();
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
     * Gets the content of the request.
     */
    public String getContent() {
        return content;
    }

    /**
     * Sets the content of the request as a string.
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * Gets the correlation ID of the request.
     */
    public String getCorrelationId() {
        return correlationId;
    }

    /**
     * Sets the correlation ID of the request.
     */
    public void setCorrelationId(String correlationId) {
        this.correlationId = correlationId;
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
         * Sets the content of the request being built.
         *
         * @param value The content to set.
         * @return The current instance of the Builder class.
         */
        public Builder setContent(String value) {
            this.instance.setContent(value);
            return this;
        }

        /**
         * Sets the correlation ID of the request being built.
         */
        public Builder setCorrelationId(String value) {
            this.instance.setCorrelationId(value);
            return this;
        }
    }
}