package com.sportradar.mts.sdk.ws.entities.cancellation;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents the details of a ticket cancellation.
 */
public class TicketCancelDetails extends CancelDetails {

    @JsonProperty("code")
    private int code;
    @JsonProperty("ticketSignature")
    private String ticketSignature;
    @JsonProperty("ticketId")
    private String ticketId;

    /**
     * Creates a new instance of TicketCancelDetails.Builder.
     *
     * @return The new instance of TicketCancelDetails.Builder.
     */
    public static Builder newBuilder() {
        return new Builder();
    }

    /**
     * Gets the cancellation code.
     *
     * @return The cancellation code.
     */
    public int getCode() {
        return this.code;
    }

    /**
     * Sets the cancellation code.
     *
     * @param value The cancellation code to set.
     */
    public void setCode(int value) {
        this.code = value;
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
     * Builder class for creating instances of TicketCancelDetails.
     */
    public static class Builder {

        private final TicketCancelDetails instance = new TicketCancelDetails();

        private Builder() {
        }

        /**
         * Builds the TicketCancelDetails instance.
         *
         * @return The built TicketCancelDetails instance.
         */
        public TicketCancelDetails build() {
            return this.instance;
        }

        /**
         * Sets the cancellation code.
         *
         * @param value The cancellation code to set.
         * @return The Builder instance.
         */
        public Builder setCode(int value) {
            this.instance.setCode(value);
            return this;
        }

        /**
         * Sets the ticket signature.
         *
         * @param value The ticket signature to set.
         * @return The Builder instance.
         */
        public Builder setTicketSignature(String value) {
            this.instance.setTicketSignature(value);
            return this;
        }

        /**
         * Sets the ticket ID.
         *
         * @param value The ticket ID to set.
         * @return The Builder instance.
         */
        public Builder setTicketId(String value) {
            this.instance.setTicketId(value);
            return this;
        }
    }
}
