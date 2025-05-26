package com.sportradar.mts.sdk.api.ws;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sportradar.mts.sdk.api.SdkTicket;
import com.sportradar.mts.sdk.api.TicketResponse;

/**
 * Represents a response object used in the application.
 */
public class Response<T extends SdkTicket> {

    @JsonProperty("timestampUtc")
    private long timestampUtc;
    @JsonProperty("operation")
    private String operation;
    @JsonProperty("version")
    private String version;
    @JsonProperty("content")
    private T content;

    /**
     * Returns a new instance of the Response.Builder class.
     *
     * @return A new instance of the Response.Builder class.
     */
    public static Builder newBuilder() {
        return new Builder();
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
    public T getContent() {
        return this.content;
    }

    /**
     * Sets the content of the response.
     *
     * @param value The content to set.
     */
    public void setContent(T value) {
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
        public Builder setContent(TicketResponse value) {
            this.instance.setContent(value);
            return this;
        }
    }
}
