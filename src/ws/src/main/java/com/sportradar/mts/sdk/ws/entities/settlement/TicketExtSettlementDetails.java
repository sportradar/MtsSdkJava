package com.sportradar.mts.sdk.ws.entities.settlement;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sportradar.mts.sdk.ws.entities.payout.Payout;

/**
 * Represents the external settlement details for a ticket.
 */
public class TicketExtSettlementDetails extends ExtSettlementDetails {

    @JsonProperty("payout")
    private Payout[] payout;
    @JsonProperty("ticketSignature")
    private String ticketSignature;
    @JsonProperty("ticketId")
    private String ticketId;

    /**
     * Creates a new instance of TicketExtSettlementDetails.Builder.
     *
     * @return A new instance of TicketExtSettlementDetails.Builder.
     */
    public static Builder newBuilder() {
        return new Builder();
    }

    /**
     * Gets the payout details for the ticket.
     *
     * @return An array of Payout objects representing the payout details.
     */
    public Payout[] getPayout() {
        return this.payout;
    }

    /**
     * Sets the payout details for the ticket.
     *
     * @param value An array of Payout objects representing the payout details.
     */
    public void setPayout(Payout[] value) {
        this.payout = value;
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
     * Builder class for creating instances of TicketExtSettlementDetails.
     */
    public static class Builder {

        private final TicketExtSettlementDetails instance = new TicketExtSettlementDetails();

        private Builder() {
        }

        /**
         * Builds and returns the TicketExtSettlementDetails instance.
         *
         * @return The TicketExtSettlementDetails instance.
         */
        public TicketExtSettlementDetails build() {
            return this.instance;
        }

        /**
         * Sets the payout details for the ticket.
         *
         * @param value An array of Payout objects representing the payout details.
         * @return The Builder instance.
         */
        public Builder setPayout(Payout... value) {
            this.instance.setPayout(value);
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
