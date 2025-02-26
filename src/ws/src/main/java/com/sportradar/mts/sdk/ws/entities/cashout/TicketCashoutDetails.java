package com.sportradar.mts.sdk.ws.entities.cashout;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sportradar.mts.sdk.ws.entities.payout.Payout;

/**
 * Represents the details of a ticket cashout.
 */
public class TicketCashoutDetails extends CashoutDetails {

    @JsonProperty("code")
    private int code;
    @JsonProperty("payout")
    private Payout[] payout;
    @JsonProperty("ticketSignature")
    private String ticketSignature;
    @JsonProperty("ticketId")
    private String ticketId;

    /**
     * Creates a new instance of TicketCashoutDetails.Builder.
     *
     * @return A new instance of TicketCashoutDetails.Builder.
     */
    public static Builder newBuilder() {
        return new Builder();
    }

    /**
     * Gets the code associated with the ticket cashout.
     *
     * @return The code associated with the ticket cashout.
     */
    public int getCode() {
        return this.code;
    }

    /**
     * Sets the code associated with the ticket cashout.
     *
     * @param value The code associated with the ticket cashout.
     */
    public void setCode(int value) {
        this.code = value;
    }

    /**
     * Gets the payout details of the ticket cashout.
     *
     * @return The payout details of the ticket cashout.
     */
    public Payout[] getPayout() {
        return this.payout;
    }

    /**
     * Sets the payout details of the ticket cashout.
     *
     * @param value The payout details of the ticket cashout.
     */
    public void setPayout(Payout[] value) {
        this.payout = value;
    }

    /**
     * Gets the ticket signature associated with the ticket cashout.
     *
     * @return The ticket signature associated with the ticket cashout.
     */
    public String getTicketSignature() {
        return this.ticketSignature;
    }

    /**
     * Sets the ticket signature associated with the ticket cashout.
     *
     * @param value The ticket signature associated with the ticket cashout.
     */
    public void setTicketSignature(String value) {
        this.ticketSignature = value;
    }

    /**
     * Gets the ticket ID associated with the ticket cashout.
     *
     * @return The ticket ID associated with the ticket cashout.
     */
    public String getTicketId() {
        return this.ticketId;
    }

    /**
     * Sets the ticket ID associated with the ticket cashout.
     *
     * @param value The ticket ID associated with the ticket cashout.
     */
    public void setTicketId(String value) {
        this.ticketId = value;
    }

    /**
     * Builder class for creating instances of TicketCashoutDetails.
     */
    public static class Builder {

        private final TicketCashoutDetails instance = new TicketCashoutDetails();

        private Builder() {
        }

        /**
         * Builds the TicketCashoutDetails instance.
         *
         * @return The built TicketCashoutDetails instance.
         */
        public TicketCashoutDetails build() {
            return this.instance;
        }

        /**
         * Sets the code associated with the ticket cashout.
         *
         * @param value The code associated with the ticket cashout.
         * @return The Builder instance.
         */
        public Builder setCode(int value) {
            this.instance.setCode(value);
            return this;
        }

        /**
         * Sets the payout details of the ticket cashout.
         *
         * @param value The payout details of the ticket cashout.
         * @return The Builder instance.
         */
        public Builder setPayout(Payout... value) {
            this.instance.setPayout(value);
            return this;
        }

        /**
         * Sets the ticket signature associated with the ticket cashout.
         *
         * @param value The ticket signature associated with the ticket cashout.
         * @return The Builder instance.
         */
        public Builder setTicketSignature(String value) {
            this.instance.setTicketSignature(value);
            return this;
        }

        /**
         * Sets the ticket ID associated with the ticket cashout.
         *
         * @param value The ticket ID associated with the ticket cashout.
         * @return The Builder instance.
         */
        public Builder setTicketId(String value) {
            this.instance.setTicketId(value);
            return this;
        }
    }
}