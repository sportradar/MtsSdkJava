package com.sportradar.mts.sdk.ws.entities.cashout;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sportradar.mts.sdk.ws.entities.payout.Payout;

import java.math.BigDecimal;

/**
 * Represents the details of a partial cashout for a bet.
 */
public class BetPartialCashoutDetails extends CashoutDetails {

    @JsonProperty("code")
    private int code;
    @JsonProperty("percentage")
    private BigDecimal percentage;
    @JsonProperty("betId")
    private String betId;
    @JsonProperty("payout")
    private Payout[] payout;
    @JsonProperty("ticketSignature")
    private String ticketSignature;
    @JsonProperty("ticketId")
    private String ticketId;

    /**
     * Creates a new instance of BetPartialCashoutDetails.Builder.
     *
     * @return A new instance of BetPartialCashoutDetails.Builder.
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
     * Sets the code associated with the partial cashout.
     *
     * @param value the code associated with the partial cashout
     */
    public void setCode(int value) {
        this.code = value;
    }

    /**
     * Returns the percentage of the partial cashout.
     *
     * @return the percentage of the partial cashout
     */
    public BigDecimal getPercentage() {
        return this.percentage;
    }

    /**
     * Sets the percentage of the partial cashout.
     *
     * @param value the percentage of the partial cashout
     */
    public void setPercentage(BigDecimal value) {
        this.percentage = value;
    }

    /**
     * Returns the bet ID associated with the partial cashout.
     *
     * @return the bet ID associated with the partial cashout
     */
    public String getBetId() {
        return this.betId;
    }

    /**
     * Sets the bet ID associated with the partial cashout.
     *
     * @param value the bet ID associated with the partial cashout
     */
    public void setBetId(String value) {
        this.betId = value;
    }

    /**
     * Returns the payouts associated with the partial cashout.
     *
     * @return the payouts associated with the partial cashout
     */
    public Payout[] getPayout() {
        return this.payout;
    }

    /**
     * Sets the payouts associated with the partial cashout.
     *
     * @param value the payouts associated with the partial cashout
     */
    public void setPayout(Payout[] value) {
        this.payout = value;
    }

    /**
     * Returns the ticket signature associated with the partial cashout.
     *
     * @return the ticket signature associated with the partial cashout
     */
    public String getTicketSignature() {
        return this.ticketSignature;
    }

    /**
     * Sets the ticket signature associated with the partial cashout.
     *
     * @param value the ticket signature associated with the partial cashout
     */
    public void setTicketSignature(String value) {
        this.ticketSignature = value;
    }

    /**
     * Returns the ticket ID associated with the partial cashout.
     *
     * @return the ticket ID associated with the partial cashout
     */
    public String getTicketId() {
        return this.ticketId;
    }

    /**
     * Sets the ticket ID associated with the partial cashout.
     *
     * @param value the ticket ID associated with the partial cashout
     */
    public void setTicketId(String value) {
        this.ticketId = value;
    }

    /**
     * Builder class for creating instances of BetPartialCashoutDetails.
     */
    public static class Builder {

        private final BetPartialCashoutDetails instance = new BetPartialCashoutDetails();

        private Builder() {
        }

        /**
         * Builds and returns the instance of BetPartialCashoutDetails.
         *
         * @return the instance of BetPartialCashoutDetails
         */
        public BetPartialCashoutDetails build() {
            return this.instance;
        }

        /**
         * Sets the code associated with the partial cashout.
         *
         * @param value the code associated with the partial cashout
         * @return the Builder instance
         */
        public Builder setCode(int value) {
            this.instance.setCode(value);
            return this;
        }

        /**
         * Sets the percentage of the partial cashout.
         *
         * @param value the percentage of the partial cashout
         * @return the Builder instance
         */
        public Builder setPercentage(BigDecimal value) {
            this.instance.setPercentage(value);
            return this;
        }

        /**
         * Sets the bet ID associated with the partial cashout.
         *
         * @param value the bet ID associated with the partial cashout
         * @return the Builder instance
         */
        public Builder setBetId(String value) {
            this.instance.setBetId(value);
            return this;
        }

        /**
         * Sets the payouts associated with the partial cashout.
         *
         * @param value the payouts associated with the partial cashout
         * @return the Builder instance
         */
        public Builder setPayout(Payout... value) {
            this.instance.setPayout(value);
            return this;
        }

        /**
         * Sets the ticket signature associated with the partial cashout.
         *
         * @param value the ticket signature associated with the partial cashout
         * @return the Builder instance
         */
        public Builder setTicketSignature(String value) {
            this.instance.setTicketSignature(value);
            return this;
        }

        /**
         * Sets the ticket ID associated with the partial cashout.
         *
         * @param value the ticket ID associated with the partial cashout
         * @return the Builder instance
         */
        public Builder setTicketId(String value) {
            this.instance.setTicketId(value);
            return this;
        }
    }
}