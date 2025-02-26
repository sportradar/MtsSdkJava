package com.sportradar.mts.sdk.ws.entities.settlement;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sportradar.mts.sdk.ws.entities.payout.Payout;

/**
 * Represents external settlement details for a bet.
 */
public class BetExtSettlementDetails extends ExtSettlementDetails {

    @JsonProperty("betId")
    private String betId;
    @JsonProperty("payout")
    private Payout[] payout;
    @JsonProperty("ticketSignature")
    private String ticketSignature;
    @JsonProperty("ticketId")
    private String ticketId;

    /**
     * Creates a new instance of the BetExtSettlementDetails.Builder class.
     *
     * @return A new instance of the BetExtSettlementDetails.Builder class.
     */
    public static Builder newBuilder() {
        return new Builder();
    }

    /**
     * Gets the bet ID.
     *
     * @return The bet ID.
     */
    public String getBetId() {
        return this.betId;
    }

    /**
     * Sets the bet ID.
     *
     * @param value The bet ID.
     */
    public void setBetId(String value) {
        this.betId = value;
    }

    /**
     * Gets the payout details.
     *
     * @return The payout details.
     */
    public Payout[] getPayout() {
        return this.payout;
    }

    /**
     * Sets the payout details.
     *
     * @param value The payout details.
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
     * Builder class for creating instances of BetExtSettlementDetails.
     */
    public static class Builder {

        private final BetExtSettlementDetails instance = new BetExtSettlementDetails();

        private Builder() {
        }

        /**
         * Builds the BetExtSettlementDetails instance.
         *
         * @return The built BetExtSettlementDetails instance.
         */
        public BetExtSettlementDetails build() {
            return this.instance;
        }

        /**
         * Sets the bet ID.
         *
         * @param value The bet ID.
         * @return The Builder instance.
         */
        public Builder setBetId(String value) {
            this.instance.setBetId(value);
            return this;
        }

        /**
         * Sets the payout details.
         *
         * @param value The payout details.
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
