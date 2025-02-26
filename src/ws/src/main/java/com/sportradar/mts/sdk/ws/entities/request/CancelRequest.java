package com.sportradar.mts.sdk.ws.entities.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sportradar.mts.sdk.ws.entities.cancellation.CancelDetails;

/**
 * Represents a request to cancel a specific operation.
 */
public class CancelRequest extends ContentRequest {

    @JsonProperty("details")
    private CancelDetails details;
    @JsonProperty("cancellationId")
    private String cancellationId;

    /**
     * Creates a new instance of the CancelRequest.Builder class.
     *
     * @return A new instance of the CancelRequest.Builder class.
     */
    public static Builder newBuilder() {
        return new Builder();
    }

    /**
     * Gets the details of the cancellation request.
     *
     * @return The details of the cancellation request.
     */
    public CancelDetails getDetails() {
        return this.details;
    }

    /**
     * Sets the details of the cancellation request.
     *
     * @param value The details of the cancellation request.
     */
    public void setDetails(CancelDetails value) {
        this.details = value;
    }

    /**
     * Gets the cancellation ID.
     *
     * @return The cancellation ID.
     */
    public String getCancellationId() {
        return this.cancellationId;
    }

    /**
     * Sets the cancellation ID.
     *
     * @param value The cancellation ID.
     */
    public void setCancellationId(String value) {
        this.cancellationId = value;
    }

    /**
     * Represents a builder for the CancelRequest class.
     */
    public static class Builder {

        private final CancelRequest instance = new CancelRequest();

        private Builder() {
        }

        /**
         * Builds the CancelRequest instance.
         *
         * @return The built CancelRequest instance.
         */
        public CancelRequest build() {
            return this.instance;
        }

        /**
         * Sets the details of the cancellation request.
         *
         * @param value The details of the cancellation request.
         * @return The builder instance.
         */
        public Builder setDetails(CancelDetails value) {
            this.instance.setDetails(value);
            return this;
        }

        /**
         * Sets the cancellation ID.
         *
         * @param value The cancellation ID.
         * @return The builder instance.
         */
        public Builder setCancellationId(String value) {
            this.instance.setCancellationId(value);
            return this;
        }
    }
}
