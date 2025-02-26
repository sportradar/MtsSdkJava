package com.sportradar.mts.sdk.ws.entities.cashout;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sportradar.mts.sdk.ws.entities.payout.Payout;

import java.math.BigDecimal;

/**
 * Represents the details of a partial cashout for a ticket.
 */
public class TicketPartialCashoutDetails extends CashoutDetails {

    @JsonProperty("code")
    private int code;
    @JsonProperty("percentage")
    private BigDecimal percentage;
    @JsonProperty("payout")
    private Payout[] payout;
    @JsonProperty("ticketSignature")
    private String ticketSignature;
    @JsonProperty("ticketId")
    private String ticketId;

    /**
     * Returns a new instance of the Builder for creating TicketPartialCashoutDetails.
     *
     * @return a new instance of the Builder
     */
    public static Builder newBuilder() {
        return new Builder();
    }

    /**
     * Returns the code of the partial cashout.
     *
     * @return the code of the partial cashout
     */
    public int getCode() {
        return this.code;
    }

    /**
     * Sets the code of the partial cashout.
     *
     * @param value the code of the partial cashout
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
     * Returns the payouts of the partial cashout.
     *
     * @return the payouts of the partial cashout
     */
    public Payout[] getPayout() {
        return this.payout;
    }

    /**
     * Sets the payouts of the partial cashout.
     *
     * @param value the payouts of the partial cashout
     */
    public void setPayout(Payout[] value) {
        this.payout = value;
    }

    /**
     * Returns the ticket signature of the partial cashout.
     *
     * @return the ticket signature of the partial cashout
     */
    public String getTicketSignature() {
        return this.ticketSignature;
    }

    /**
     * Sets the ticket signature of the partial cashout.
     *
     * @param value the ticket signature of the partial cashout
     */
    public void setTicketSignature(String value) {
        this.ticketSignature = value;
    }

    /**
     * Returns the ticket ID of the partial cashout.
     *
     * @return the ticket ID of the partial cashout
     */
    public String getTicketId() {
        return this.ticketId;
    }

    /**
     * Sets the ticket ID of the partial cashout.
     *
     * @param value the ticket ID of the partial cashout
     */
    public void setTicketId(String value) {
        this.ticketId = value;
    }

    /**
     * Builder class for creating TicketPartialCashoutDetails.
     */
    public static class Builder {

        private final TicketPartialCashoutDetails instance = new TicketPartialCashoutDetails();

        private Builder() {
        }

        /**
         * Builds and returns the TicketPartialCashoutDetails instance.
         *
         * @return the TicketPartialCashoutDetails instance
         */
        public TicketPartialCashoutDetails build() {
            return this.instance;
        }

        /**
         * Sets the code of the partial cashout.
         *
         * @param value the code of the partial cashout
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
         * Sets the payouts of the partial cashout.
         *
         * @param value the payouts of the partial cashout
         * @return the Builder instance
         */
        public Builder setPayout(Payout... value) {
            this.instance.setPayout(value);
            return this;
        }

        /**
         * Sets the ticket signature of the partial cashout.
         *
         * @param value the ticket signature of the partial cashout
         * @return the Builder instance
         */
        public Builder setTicketSignature(String value) {
            this.instance.setTicketSignature(value);
            return this;
        }

        /**
         * Sets the ticket ID of the partial cashout.
         *
         * @param value the ticket ID of the partial cashout
         * @return the Builder instance
         */
        public Builder setTicketId(String value) {
            this.instance.setTicketId(value);
            return this;
        }
    }
}
