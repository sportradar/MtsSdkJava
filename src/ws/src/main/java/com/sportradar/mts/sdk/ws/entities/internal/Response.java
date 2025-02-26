package com.sportradar.mts.sdk.ws.entities.internal;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sportradar.mts.sdk.ws.entities.response.ContentResponse;

/**
 * Represents a response object used in the application.
 */
public class Response {

    @JsonProperty("correlationId")
    private String correlationId;
    @JsonProperty("timestampUtc")
    private long timestampUtc;
    @JsonProperty("operation")
    private String operation;
    @JsonProperty("version")
    private String version;
    @JsonProperty("content")
    private ContentResponse content;

    /**
     * Returns a new instance of the Response.Builder class.
     *
     * @return A new instance of the Response.Builder class.
     */
    public static Builder newBuilder() {
        return new Builder();
    }

    /**
     * Gets the correlation ID of the response.
     *
     * @return The correlation ID.
     */
    public String getCorrelationId() {
        return this.correlationId;
    }

    /**
     * Sets the correlation ID of the response.
     *
     * @param value The correlation ID to set.
     */
    public void setCorrelationId(String value) {
        this.correlationId = value;
    }

    /**
     * Gets the UTC millis timestamp of the response.
     *
     * @return The UTC millis timestamp.
     */
    public long getTimestampUtc() {
        return this.timestampUtc;
    }

    /**
     * Sets the UTC millis timestamp of the response.
     *
     * @param value The UTC millis timestamp to set.
     */
    public void setTimestampUtc(long value) {
        this.timestampUtc = value;
    }

    /**
     * Gets the operation of the response.
     *
     * @return The operation.
     */
    public String getOperation() {
        return this.operation;
    }

    /**
     * Sets the operation of the response.
     *
     * @param value The operation to set.
     */
    public void setOperation(String value) {
        this.operation = value;
    }

    /**
     * Gets the version of the response.
     *
     * @return The version.
     */
    public String getVersion() {
        return this.version;
    }

    /**
     * Sets the version of the response.
     *
     * @param value The version to set.
     */
    public void setVersion(String value) {
        this.version = value;
    }

    /**
     * Gets the content of the response.
     *
     * @return The content.
     */
    public ContentResponse getContent() {
        return this.content;
    }

    /**
     * Sets the content of the response.
     *
     * @param value The content to set.
     */
    public void setContent(ContentResponse value) {
        this.content = value;
    }

    /**
     * Builder class for creating instances of the Response class.
     */
    public static class Builder {

        private final Response instance = new Response();

        private Builder() {
        }

        /**
         * Builds and returns the Response instance.
         *
         * @return The Response instance.
         */
        public Response build() {
            return this.instance;
        }

        /**
         * Sets the correlation ID of the response.
         *
         * @param value The correlation ID to set.
         * @return The Builder instance.
         */
        public Builder setCorrelationId(String value) {
            this.instance.setCorrelationId(value);
            return this;
        }

        /**
         * Sets the UTC millis timestamp of the response.
         *
         * @param value The UTC millis timestamp to set.
         * @return The Builder instance.
         */
        public Builder setTimestampUtc(long value) {
            this.instance.setTimestampUtc(value);
            return this;
        }

        /**
         * Sets the operation of the response.
         *
         * @param value The operation to set.
         * @return The Builder instance.
         */
        public Builder setOperation(String value) {
            this.instance.setOperation(value);
            return this;
        }

        /**
         * Sets the version of the response.
         *
         * @param value The version to set.
         * @return The Builder instance.
         */
        public Builder setVersion(String value) {
            this.instance.setVersion(value);
            return this;
        }

        /**
         * Sets the content of the response.
         *
         * @param value The content to set.
         * @return The Builder instance.
         */
        public Builder setContent(ContentResponse value) {
            this.instance.setContent(value);
            return this;
        }
    }
}
