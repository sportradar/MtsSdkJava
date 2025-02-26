package com.sportradar.mts.sdk.ws.entities.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sportradar.mts.sdk.ws.entities.common.AcceptanceStatus;
import com.sportradar.mts.sdk.ws.entities.common.BetDetail;
import com.sportradar.mts.sdk.ws.entities.common.ExchangeRate;

/**
 * Represents a response for ticket information.
 */
public class TicketInformResponse extends ContentResponse {

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
     * Creates a new instance of the TicketInformResponse.Builder class.
     *
     * @return A new instance of the TicketInformResponse.Builder class.
     */
    public static Builder newBuilder() {
        return new Builder();
    }

    /**
     * Gets the code of the response.
     *
     * @return The code of the response.
     */
    public int getCode() {
        return this.code;
    }

    /**
     * Sets the code of the response.
     *
     * @param value The code of the response.
     */
    public void setCode(int value) {
        this.code = value;
    }

    /**
     * Gets the exchange rates.
     *
     * @return The exchange rates.
     */
    public ExchangeRate[] getExchangeRate() {
        return this.exchangeRate;
    }

    /**
     * Sets the exchange rates.
     *
     * @param value The exchange rates.
     */
    public void setExchangeRate(ExchangeRate[] value) {
        this.exchangeRate = value;
    }

    /**
     * Gets the signature.
     *
     * @return The signature.
     */
    public String getSignature() {
        return this.signature;
    }

    /**
     * Sets the signature.
     *
     * @param value The signature.
     */
    public void setSignature(String value) {
        this.signature = value;
    }

    /**
     * Gets the bet details.
     *
     * @return The bet details.
     */
    public BetDetail[] getBetDetails() {
        return this.betDetails;
    }

    /**
     * Sets the bet details.
     *
     * @param value The bet details.
     */
    public void setBetDetails(BetDetail[] value) {
        this.betDetails = value;
    }

    /**
     * Gets the message.
     *
     * @return The message.
     */
    public String getMessage() {
        return this.message;
    }

    /**
     * Sets the message.
     *
     * @param value The message.
     */
    public void setMessage(String value) {
        this.message = value;
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
     * Gets the acceptance status.
     *
     * @return The acceptance status.
     */
    public AcceptanceStatus getStatus() {
        return this.status;
    }

    /**
     * Sets the acceptance status.
     *
     * @param value The acceptance status.
     */
    public void setStatus(AcceptanceStatus value) {
        this.status = value;
    }

    /**
     * Represents a builder for creating instances of the TicketInformResponse class.
     */
    public static class Builder {

        private final TicketInformResponse instance = new TicketInformResponse();

        private Builder() {
        }

        /**
         * Builds and returns the TicketInformResponse instance.
         *
         * @return The TicketInformResponse instance.
         */
        public TicketInformResponse build() {
            return this.instance;
        }

        /**
         * Sets the code of the response.
         *
         * @param value The code of the response.
         * @return The builder instance.
         */
        public Builder setCode(int value) {
            this.instance.setCode(value);
            return this;
        }

        /**
         * Sets the exchange rates.
         *
         * @param value The exchange rates.
         * @return The builder instance.
         */
        public Builder setExchangeRate(ExchangeRate... value) {
            this.instance.setExchangeRate(value);
            return this;
        }

        /**
         * Sets the signature.
         *
         * @param value The signature.
         * @return The builder instance.
         */
        public Builder setSignature(String value) {
            this.instance.setSignature(value);
            return this;
        }

        /**
         * Sets the bet details.
         *
         * @param value The bet details.
         * @return The builder instance.
         */
        public Builder setBetDetails(BetDetail... value) {
            this.instance.setBetDetails(value);
            return this;
        }

        /**
         * Sets the message.
         *
         * @param value The message.
         * @return The builder instance.
         */
        public Builder setMessage(String value) {
            this.instance.setMessage(value);
            return this;
        }

        /**
         * Sets the ticket ID.
         *
         * @param value The ticket ID.
         * @return The builder instance.
         */
        public Builder setTicketId(String value) {
            this.instance.setTicketId(value);
            return this;
        }

        /**
         * Sets the acceptance status.
         *
         * @param value The acceptance status.
         * @return The builder instance.
         */
        public Builder setStatus(AcceptanceStatus value) {
            this.instance.setStatus(value);
            return this;
        }
    }
}
