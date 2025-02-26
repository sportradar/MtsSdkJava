package com.sportradar.mts.sdk.ws.entities.ref;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents an alternative stake ticket reference.
 */
public class AltStakeTicketRef extends TicketRef {

    @JsonProperty("ticketId")
    private String ticketId;

    @JsonProperty("ticketSignature")
    private String ticketSignature;


    /**
     * Creates a new instance of the {@code AltStakeTicketRef.Builder} class.
     *
     * @return A new instance of the {@code AltStakeTicketRef.Builder} class.
     */
    public static Builder newBuilder() {
        return new Builder();
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
     * @param value The ticket signature to set.
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
     * @param value The ticket ID to set.
     */
    public void setTicketId(String value) {
        this.ticketId = value;
    }

    /**
     * Represents a builder for the {@code AltStakeTicketRef} class.
     */
    public static class Builder {

        private final AltStakeTicketRef instance = new AltStakeTicketRef();

        private Builder() {
        }

        /**
         * Builds the {@code AltStakeTicketRef} instance.
         *
         * @return The built {@code AltStakeTicketRef} instance.
         */
        public AltStakeTicketRef build() {
            return this.instance;
        }

        /**
         * Sets the ticket signature.
         *
         * @param value The ticket signature to set.
         * @return The builder instance.
         */
        public Builder setTicketSignature(String value) {
            this.instance.setTicketSignature(value);
            return this;
        }

        /**
         * Sets the ticket ID.
         *
         * @param value The ticket ID to set.
         * @return The {@code Builder} instance.
         */
        public Builder setTicketId(String value) {
            this.instance.setTicketId(value);
            return this;
        }
    }
}
