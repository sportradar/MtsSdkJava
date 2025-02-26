package com.sportradar.mts.sdk.ws.entities.request;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a request to acknowledge a cashout.
 */
public class CashoutAckRequest extends ContentRequest {

    @JsonProperty("acknowledged")
    private boolean acknowledged;
    @JsonProperty("cashoutId")
    private String cashoutId;
    @JsonProperty("cashoutSignature")
    private String cashoutSignature;
    @JsonProperty("ticketId")
    private String ticketId;

    /**
     * Creates a new instance of the CashoutAckRequest.Builder class.
     *
     * @return A new instance of the CashoutAckRequest.Builder class.
     */
    public static Builder newBuilder() {
        return new Builder();
    }

    /**
     * Gets the acknowledged status of the cashout.
     *
     * @return The acknowledged status of the cashout.
     */
    public boolean getAcknowledged() {
        return this.acknowledged;
    }

    /**
     * Sets the acknowledged status of the cashout.
     *
     * @param value The acknowledged status of the cashout.
     */
    public void setAcknowledged(boolean value) {
        this.acknowledged = value;
    }

    /**
     * Gets the cashout ID.
     *
     * @return The cashout ID.
     */
    public String getCashoutId() {
        return this.cashoutId;
    }

    /**
     * Sets the cashout ID.
     *
     * @param value The cashout ID.
     */
    public void setCashoutId(String value) {
        this.cashoutId = value;
    }

    /**
     * Gets the cashout signature.
     *
     * @return The cashout signature.
     */
    public String getCashoutSignature() {
        return this.cashoutSignature;
    }

    /**
     * Sets the cashout signature.
     *
     * @param value The cashout signature.
     */
    public void setCashoutSignature(String value) {
        this.cashoutSignature = value;
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
     * Builder class for creating instances of the CashoutAckRequest class.
     */
    public static class Builder {

        private final CashoutAckRequest instance = new CashoutAckRequest();

        private Builder() {
        }

        /**
         * Builds the CashoutAckRequest instance.
         *
         * @return The built CashoutAckRequest instance.
         */
        public CashoutAckRequest build() {
            return this.instance;
        }

        /**
         * Sets the acknowledged status of the cashout.
         *
         * @param value The acknowledged status of the cashout.
         * @return The Builder instance.
         */
        public Builder setAcknowledged(boolean value) {
            this.instance.setAcknowledged(value);
            return this;
        }

        /**
         * Sets the cashout ID.
         *
         * @param value The cashout ID.
         * @return The Builder instance.
         */
        public Builder setCashoutId(String value) {
            this.instance.setCashoutId(value);
            return this;
        }

        /**
         * Sets the cashout signature.
         *
         * @param value The cashout signature.
         * @return The Builder instance.
         */
        public Builder setCashoutSignature(String value) {
            this.instance.setCashoutSignature(value);
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
