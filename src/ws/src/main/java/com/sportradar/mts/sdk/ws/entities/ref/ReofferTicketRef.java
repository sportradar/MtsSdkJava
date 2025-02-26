package com.sportradar.mts.sdk.ws.entities.ref;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a reference to a reoffered ticket.
 */
public class ReofferTicketRef extends TicketRef {

    @JsonProperty("ticketId")
    private String ticketId;

    @JsonProperty("ticketSignature")
    private String ticketSignature;

    /**
     * Creates a new instance of the ReofferTicketRef.Builder class.
     *
     * @return A new instance of the ReofferTicketRef.Builder class.
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
     * Represents a builder for creating instances of the ReofferTicketRef class.
     */
    public static class Builder {

        private final ReofferTicketRef instance = new ReofferTicketRef();

        private Builder() {
        }

        /**
         * Builds the ReofferTicketRef instance.
         *
         * @return The built ReofferTicketRef instance.
         */
        public ReofferTicketRef build() {
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
         * @return The builder instance.
         */
        public Builder setTicketId(String value) {
            this.instance.setTicketId(value);
            return this;
        }
    }
}
