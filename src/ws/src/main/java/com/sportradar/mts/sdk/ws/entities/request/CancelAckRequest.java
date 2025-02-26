package com.sportradar.mts.sdk.ws.entities.request;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a request to acknowledge the cancellation of a ticket.
 */
public class CancelAckRequest extends ContentRequest {

    @JsonProperty("cancellationSignature")
    private String cancellationSignature;
    @JsonProperty("acknowledged")
    private boolean acknowledged;
    @JsonProperty("cancellationId")
    private String cancellationId;
    @JsonProperty("ticketId")
    private String ticketId;

    /**
     * Creates a new instance of the CancelAckRequest.Builder class.
     *
     * @return A new instance of the CancelAckRequest.Builder class.
     */
    public static Builder newBuilder() {
        return new Builder();
    }

    /**
     * Gets the cancellation signature.
     *
     * @return The cancellation signature.
     */
    public String getCancellationSignature() {
        return this.cancellationSignature;
    }

    /**
     * Sets the cancellation signature.
     *
     * @param value The cancellation signature.
     */
    public void setCancellationSignature(String value) {
        this.cancellationSignature = value;
    }

    /**
     * Gets the acknowledged status.
     *
     * @return The acknowledged status.
     */
    public boolean getAcknowledged() {
        return this.acknowledged;
    }

    /**
     * Sets the acknowledged status.
     *
     * @param value The acknowledged status.
     */
    public void setAcknowledged(boolean value) {
        this.acknowledged = value;
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
     * Gets the ticket ID.
     *
     * @return The ticket ID.
     */
    public String getTicketId() {
        return this.ticketId;
    }

    /**
     * Sets the ticket ID.
     *
     * @param value The ticket ID.
     */
    public void setTicketId(String value) {
        this.ticketId = value;
    }

    /**
     * Builder class for creating instances of CancelAckRequest.
     */
    public static class Builder {

        private final CancelAckRequest instance = new CancelAckRequest();

        private Builder() {
        }

        /**
         * Builds the CancelAckRequest instance.
         *
         * @return The CancelAckRequest instance.
         */
        public CancelAckRequest build() {
            return this.instance;
        }

        /**
         * Sets the cancellation signature.
         *
         * @param value The cancellation signature.
         * @return The Builder instance.
         */
        public Builder setCancellationSignature(String value) {
            this.instance.setCancellationSignature(value);
            return this;
        }

        /**
         * Sets the acknowledged status.
         *
         * @param value The acknowledged status.
         * @return The Builder instance.
         */
        public Builder setAcknowledged(boolean value) {
            this.instance.setAcknowledged(value);
            return this;
        }

        /**
         * Sets the cancellation ID.
         *
         * @param value The cancellation ID.
         * @return The Builder instance.
         */
        public Builder setCancellationId(String value) {
            this.instance.setCancellationId(value);
            return this;
        }

        /**
         * Sets the ticket ID.
         *
         * @param value The ticket ID.
         * @return The Builder instance.
         */
        public Builder setTicketId(String value) {
            this.instance.setTicketId(value);
            return this;
        }
    }
}
