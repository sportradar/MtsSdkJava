package com.sportradar.mts.sdk.ws.entities.request;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a request to acknowledge a ticket.
 */
public class TicketAckRequest extends ContentRequest {

    @JsonProperty("acknowledged")
    private boolean acknowledged;
    @JsonProperty("ticketSignature")
    private String ticketSignature;
    @JsonProperty("ticketId")
    private String ticketId;

    /**
     * Creates a new instance of TicketAckRequest.Builder.
     *
     * @return The new instance of TicketAckRequest.Builder.
     */
    public static Builder newBuilder() {
        return new Builder();
    }

    /**
     * Gets the acknowledged status of the ticket.
     *
     * @return The acknowledged status of the ticket.
     */
    public boolean getAcknowledged() {
        return this.acknowledged;
    }

    /**
     * Sets the acknowledged status of the ticket.
     *
     * @param value The acknowledged status of the ticket.
     */
    public void setAcknowledged(boolean value) {
        this.acknowledged = value;
    }

    /**
     * Gets the ticket signature.
     *
     * @return The ticket signature.
     */
    public String getTicketSignature() {
        return this.ticketSignature;
    }

    /**
     * Sets the ticket signature.
     *
     * @param value The ticket signature.
     */
    public void setTicketSignature(String value) {
        this.ticketSignature = value;
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
     * Builder class for creating instances of TicketAckRequest.
     */
    public static class Builder {

        private final TicketAckRequest instance = new TicketAckRequest();

        private Builder() {
        }

        /**
         * Builds the TicketAckRequest instance.
         *
         * @return The built TicketAckRequest instance.
         */
        public TicketAckRequest build() {
            return this.instance;
        }

        /**
         * Sets the acknowledged status of the ticket.
         *
         * @param value The acknowledged status of the ticket.
         * @return The Builder instance.
         */
        public Builder setAcknowledged(boolean value) {
            this.instance.setAcknowledged(value);
            return this;
        }

        /**
         * Sets the ticket signature.
         *
         * @param value The ticket signature.
         * @return The Builder instance.
         */
        public Builder setTicketSignature(String value) {
            this.instance.setTicketSignature(value);
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
