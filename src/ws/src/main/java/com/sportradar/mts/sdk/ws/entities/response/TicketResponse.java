package com.sportradar.mts.sdk.ws.entities.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sportradar.mts.sdk.ws.entities.common.AcceptanceStatus;
import com.sportradar.mts.sdk.ws.entities.common.BetDetail;
import com.sportradar.mts.sdk.ws.entities.common.ExchangeRate;

/**
 * Represents a ticket response.
 */
public class TicketResponse extends ContentResponse {

    @JsonProperty("code")
    private int code;
    @JsonProperty("exchangeRate")
    private ExchangeRate[] exchangeRate;
    @JsonProperty("signature")
    private String signature;
    @JsonProperty("betDetails")
    private BetDetail[] betDetails;
    @JsonProperty("message")
    private String message;
    @JsonProperty("ticketId")
    private String ticketId;
    @JsonProperty("status")
    private AcceptanceStatus status;

    /**
     * Creates a new instance of the TicketResponse.Builder class.
     *
     * @return The TicketResponse.Builder instance.
     */
    public static Builder newBuilder() {
        return new Builder();
    }

    /**
     * Gets the code of the ticket response.
     *
     * @return The code of the ticket response.
     */
    public int getCode() {
        return this.code;
    }

    /**
     * Sets the code of the ticket response.
     *
     * @param value The code of the ticket response.
     */
    public void setCode(int value) {
        this.code = value;
    }

    /**
     * Gets the exchange rates of the ticket response.
     *
     * @return The exchange rates of the ticket response.
     */
    public ExchangeRate[] getExchangeRate() {
        return this.exchangeRate;
    }

    /**
     * Sets the exchange rates of the ticket response.
     *
     * @param value The exchange rates of the ticket response.
     */
    public void setExchangeRate(ExchangeRate[] value) {
        this.exchangeRate = value;
    }

    /**
     * Gets the signature of the ticket response.
     *
     * @return The signature of the ticket response.
     */
    public String getSignature() {
        return this.signature;
    }

    /**
     * Sets the signature of the ticket response.
     *
     * @param value The signature of the ticket response.
     */
    public void setSignature(String value) {
        this.signature = value;
    }

    /**
     * Gets the bet details of the ticket response.
     *
     * @return The bet details of the ticket response.
     */
    public BetDetail[] getBetDetails() {
        return this.betDetails;
    }

    /**
     * Sets the bet details of the ticket response.
     *
     * @param value The bet details of the ticket response.
     */
    public void setBetDetails(BetDetail[] value) {
        this.betDetails = value;
    }

    /**
     * Gets the message of the ticket response.
     *
     * @return The message of the ticket response.
     */
    public String getMessage() {
        return this.message;
    }

    /**
     * Sets the message of the ticket response.
     *
     * @param value The message of the ticket response.
     */
    public void setMessage(String value) {
        this.message = value;
    }

    /**
     * Gets the ticket ID of the ticket response.
     *
     * @return The ticket ID of the ticket response.
     */
    public String getTicketId() {
        return this.ticketId;
    }

    /**
     * Sets the ticket ID of the ticket response.
     *
     * @param value The ticket ID of the ticket response.
     */
    public void setTicketId(String value) {
        this.ticketId = value;
    }

    /**
     * Gets the acceptance status of the ticket response.
     *
     * @return The acceptance status of the ticket response.
     */
    public AcceptanceStatus getStatus() {
        return this.status;
    }

    /**
     * Sets the acceptance status of the ticket response.
     *
     * @param value The acceptance status of the ticket response.
     */
    public void setStatus(AcceptanceStatus value) {
        this.status = value;
    }

    /**
     * Represents a builder for the TicketResponse class.
     */
    public static class Builder {

        private final TicketResponse instance = new TicketResponse();

        private Builder() {
        }

        /**
         * Builds the TicketResponse instance.
         *
         * @return The built TicketResponse instance.
         */
        public TicketResponse build() {
            return this.instance;
        }

        /**
         * Sets the code of the ticket response.
         *
         * @param value The code of the ticket response.
         * @return The TicketResponse.Builder instance.
         */
        public Builder setCode(int value) {
            this.instance.setCode(value);
            return this;
        }

        /**
         * Sets the exchange rates of the ticket response.
         *
         * @param value The exchange rates of the ticket response.
         * @return The TicketResponse.Builder instance.
         */
        public Builder setExchangeRate(ExchangeRate... value) {
            this.instance.setExchangeRate(value);
            return this;
        }

        /**
         * Sets the signature of the ticket response.
         *
         * @param value The signature of the ticket response.
         * @return The TicketResponse.Builder instance.
         */
        public Builder setSignature(String value) {
            this.instance.setSignature(value);
            return this;
        }

        /**
         * Sets the bet details of the ticket response.
         *
         * @param value The bet details of the ticket response.
         * @return The TicketResponse.Builder instance.
         */
        public Builder setBetDetails(BetDetail... value) {
            this.instance.setBetDetails(value);
            return this;
        }

        /**
         * Sets the message of the ticket response.
         *
         * @param value The message of the ticket response.
         * @return The TicketResponse.Builder instance.
         */
        public Builder setMessage(String value) {
            this.instance.setMessage(value);
            return this;
        }

        /**
         * Sets the ticket ID of the ticket response.
         *
         * @param value The ticket ID of the ticket response.
         * @return The TicketResponse.Builder instance.
         */
        public Builder setTicketId(String value) {
            this.instance.setTicketId(value);
            return this;
        }

        /**
         * Sets the acceptance status of the ticket response.
         *
         * @param value The acceptance status of the ticket response.
         * @return The TicketResponse.Builder instance.
         */
        public Builder setStatus(AcceptanceStatus value) {
            this.instance.setStatus(value);
            return this;
        }
    }
}
