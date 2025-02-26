package com.sportradar.mts.sdk.ws.entities.cashout;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sportradar.mts.sdk.ws.entities.payout.Payout;

/**
 * Represents the details of a cashout for a specific bet.
 */
public class BetCashoutDetails extends CashoutDetails {

    @JsonProperty("code")
    private int code;
    @JsonProperty("betId")
    private String betId;
    @JsonProperty("payout")
    private Payout[] payout;
    @JsonProperty("ticketSignature")
    private String ticketSignature;
    @JsonProperty("ticketId")
    private String ticketId;

    /**
     * Creates a new instance of BetCashoutDetails.Builder.
     *
     * @return A new instance of BetCashoutDetails.Builder.
     */
    public static Builder newBuilder() {
        return new Builder();
    }

    /**
     * Returns the code associated with the partial cashout.
     *
     * @return the code associated with the partial cashout
     */
    public int getCode() {
        return this.code;
    }

    /**
     * Sets the code associated with the cashout details.
     *
     * @param value the code to set
     */
    public void setCode(int value) {
        this.code = value;
    }

    /**
     * Returns the bet ID associated with the cashout details.
     *
     * @return the bet ID associated with the cashout details
     */
    public String getBetId() {
        return this.betId;
    }

    /**
     * Sets the bet ID associated with the cashout details.
     *
     * @param value the bet ID to set
     */
    public void setBetId(String value) {
        this.betId = value;
    }

    /**
     * Returns the payout details associated with the cashout details.
     *
     * @return the payout details associated with the cashout details
     */
    public Payout[] getPayout() {
        return this.payout;
    }

    /**
     * Sets the payout details associated with the cashout details.
     *
     * @param value the payout details to set
     */
    public void setPayout(Payout[] value) {
        this.payout = value;
    }

    /**
     * Returns the ticket signature associated with the cashout details.
     *
     * @return the ticket signature associated with the cashout details
     */
    public String getTicketSignature() {
        return this.ticketSignature;
    }

    /**
     * Sets the ticket signature associated with the cashout details.
     *
     * @param value the ticket signature to set
     */
    public void setTicketSignature(String value) {
        this.ticketSignature = value;
    }

    /**
     * Returns the ticket ID associated with the cashout details.
     *
     * @return the ticket ID associated with the cashout details
     */
    public String getTicketId() {
        return this.ticketId;
    }

    /**
     * Sets the ticket ID associated with the cashout details.
     *
     * @param value the ticket ID to set
     */
    public void setTicketId(String value) {
        this.ticketId = value;
    }

    /**
     * Builder class for creating instances of {@link BetCashoutDetails}.
     */
    public static class Builder {

        private final BetCashoutDetails instance = new BetCashoutDetails();

        private Builder() {
        }

        /**
         * Builds and returns the {@link BetCashoutDetails} instance.
         *
         * @return the built {@link BetCashoutDetails} instance
         */
        public BetCashoutDetails build() {
            return this.instance;
        }

        /**
         * Sets the code associated with the cashout details.
         *
         * @param value the code to set
         * @return the builder instance
         */
        public Builder setCode(int value) {
            this.instance.setCode(value);
            return this;
        }

        /**
         * Sets the bet ID associated with the cashout details.
         *
         * @param value the bet ID to set
         * @return the builder instance
         */
        public Builder setBetId(String value) {
            this.instance.setBetId(value);
            return this;
        }

        /**
         * Sets the payout details associated with the cashout details.
         *
         * @param value the payout details to set
         * @return the builder instance
         */
        public Builder setPayout(Payout... value) {
            this.instance.setPayout(value);
            return this;
        }

        /**
         * Sets the ticket signature associated with the cashout details.
         *
         * @param value the ticket signature to set
         * @return the builder instance
         */
        public Builder setTicketSignature(String value) {
            this.instance.setTicketSignature(value);
            return this;
        }

        /**
         * Sets the ticket ID associated with the cashout details.
         *
         * @param value the ticket ID to set
         * @return the builder instance
         */
        public Builder setTicketId(String value) {
            this.instance.setTicketId(value);
            return this;
        }
    }
}