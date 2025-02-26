package com.sportradar.mts.sdk.ws.entities.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sportradar.mts.sdk.ws.entities.common.AcceptanceStatus;

/**
 * A response of a cashout inform operation.
 * Inherits from the ContentResponse class.
 */
public class CashoutInformResponse extends ContentResponse {

    @JsonProperty("code")
    private int code;
    @JsonProperty("signature")
    private String signature;
    @JsonProperty("cashoutId")
    private String cashoutId;
    @JsonProperty("message")
    private String message;
    @JsonProperty("ticketId")
    private String ticketId;
    @JsonProperty("status")
    private AcceptanceStatus status;

    /**
     * Returns a new instance of {@link Builder}.
     *
     * @return A new instance of {@link Builder}.
     */
    public static Builder newBuilder() {
        return new Builder();
    }

    /**
     * Gets the code of the {@link CashoutInformResponse}.
     *
     * @return The code of the cashout inform response.
     */
    public int getCode() {
        return this.code;
    }

    /**
     * Sets the code of the {@link CashoutInformResponse}.
     *
     * @param value The code of the cashout inform response.
     */
    public void setCode(int value) {
        this.code = value;
    }

    /**
     * Gets the signature of the {@link CashoutInformResponse}.
     *
     * @return The signature of the cashout inform response.
     */
    public String getSignature() {
        return this.signature;
    }

    /**
     * Sets the signature of the {@link CashoutInformResponse}.
     *
     * @param value The signature of the cashout inform response.
     */
    public void setSignature(String value) {
        this.signature = value;
    }

    /**
     * Gets the cashout ID of the {@link CashoutInformResponse}.
     *
     * @return The cashout ID of the cashout inform response.
     */
    public String getCashoutId() {
        return this.cashoutId;
    }

    /**
     * Sets the cashout ID of the {@link CashoutInformResponse}.
     *
     * @param value The cashout ID of the cashout inform response.
     */
    public void setCashoutId(String value) {
        this.cashoutId = value;
    }

    /**
     * Gets the message of the {@link CashoutInformResponse}.
     *
     * @return The message of the cashout inform response.
     */
    public String getMessage() {
        return this.message;
    }

    /**
     * Sets the message of the {@link CashoutInformResponse}.
     *
     * @param value The message of the cashout inform response.
     */
    public void setMessage(String value) {
        this.message = value;
    }

    /**
     * Gets the ticket ID of the {@link CashoutInformResponse}.
     *
     * @return The ticket ID of the cashout inform response.
     */
    public String getTicketId() {
        return this.ticketId;
    }

    /**
     * Sets the ticket ID of the {@link CashoutInformResponse}.
     *
     * @param value The ticket ID of the cashout inform response.
     */
    public void setTicketId(String value) {
        this.ticketId = value;
    }

    /**
     * Gets the acceptance status of {@link CashoutInformResponse}.
     *
     * @return The acceptance status of the cashout inform response.
     */
    public AcceptanceStatus getStatus() {
        return this.status;
    }

    /**
     * Sets the acceptance status of the {@link CashoutInformResponse}.
     *
     * @param value The acceptance status of the cashout inform response.
     */
    public void setStatus(AcceptanceStatus value) {
        this.status = value;
    }

    /**
     * Builder class for constructing instances of {@link CashoutInformResponse}.
     */
    public static class Builder {

        private final CashoutInformResponse instance = new CashoutInformResponse();

        private Builder() {
        }

        /**
         * Builds and returns the {@link CashoutInformResponse} instance.
         *
         * @return The CashoutInformResponse instance.
         */
        public CashoutInformResponse build() {
            return this.instance;
        }

        /**
         * Sets the code of the {@link CashoutInformResponse}.
         *
         * @param value The code of the cashout inform response.
         * @return Instance of {@link Builder}.
         */
        public Builder setCode(int value) {
            this.instance.setCode(value);
            return this;
        }

        /**
         * Sets the signature of the {@link CashoutInformResponse}.
         *
         * @param value The signature of the cashout inform response.
         * @return Instance of {@link Builder}.
         */
        public Builder setSignature(String value) {
            this.instance.setSignature(value);
            return this;
        }

        /**
         * Sets the cashout ID of the {@link CashoutInformResponse}.
         *
         * @param value The cashout ID of the cashout inform response.
         * @return Instance of {@link Builder}.
         */
        public Builder setCashoutId(String value) {
            this.instance.setCashoutId(value);
            return this;
        }

        /**
         * Sets the message of the {@link CashoutInformResponse}.
         *
         * @param value The message of the cashout inform response.
         * @return Instance of {@link Builder}.
         */
        public Builder setMessage(String value) {
            this.instance.setMessage(value);
            return this;
        }

        /**
         * Sets the ticket ID of the {@link CashoutInformResponse}.
         *
         * @param value The ticket ID of the cashout inform response.
         * @return Instance of {@link Builder}.
         */
        public Builder setTicketId(String value) {
            this.instance.setTicketId(value);
            return this;
        }

        /**
         * Sets the acceptance status of the {@link CashoutInformResponse}.
         *
         * @param value The acceptance status of the cashout inform response.
         * @return Instance of {@link Builder}.
         */
        public Builder setStatus(AcceptanceStatus value) {
            this.instance.setStatus(value);
            return this;
        }
    }
}