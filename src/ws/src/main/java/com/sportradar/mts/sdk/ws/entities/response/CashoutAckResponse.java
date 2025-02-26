package com.sportradar.mts.sdk.ws.entities.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sportradar.mts.sdk.ws.entities.common.AcceptanceStatus;

/**
 * Represents a response for a cashout acknowledgement.
 */
public class CashoutAckResponse extends ContentResponse {

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
     * Creates a new instance of the CashoutAckResponse.Builder class.
     *
     * @return A new instance of the CashoutAckResponse.Builder class.
     */
    public static Builder newBuilder() {
        return new Builder();
    }

    /**
     * Gets the code associated with the cashout acknowledgement response.
     *
     * @return The code associated with the cashout acknowledgement response.
     */
    public int getCode() {
        return this.code;
    }

    /**
     * Sets the code associated with the cashout acknowledgement response.
     *
     * @param value The code to set.
     */
    public void setCode(int value) {
        this.code = value;
    }

    /**
     * Gets the signature associated with the cashout acknowledgement response.
     *
     * @return The signature associated with the cashout acknowledgement response.
     */
    public String getSignature() {
        return this.signature;
    }

    /**
     * Sets the signature associated with the cashout acknowledgement response.
     *
     * @param value The signature to set.
     */
    public void setSignature(String value) {
        this.signature = value;
    }

    /**
     * Gets the cashout ID associated with the cashout acknowledgement response.
     *
     * @return The cashout ID associated with the cashout acknowledgement response.
     */
    public String getCashoutId() {
        return this.cashoutId;
    }

    /**
     * Sets the cashout ID associated with the cashout acknowledgement response.
     *
     * @param value The cashout ID to set.
     */
    public void setCashoutId(String value) {
        this.cashoutId = value;
    }

    /**
     * Gets the message associated with the cashout acknowledgement response.
     *
     * @return The message associated with the cashout acknowledgement response.
     */
    public String getMessage() {
        return this.message;
    }

    /**
     * Sets the message associated with the cashout acknowledgement response.
     *
     * @param value The message to set.
     */
    public void setMessage(String value) {
        this.message = value;
    }

    /**
     * Gets the ticket ID associated with the cashout acknowledgement response.
     *
     * @return The ticket ID associated with the cashout acknowledgement response.
     */
    public String getTicketId() {
        return this.ticketId;
    }

    /**
     * Sets the ticket ID associated with the cashout acknowledgement response.
     *
     * @param value The ticket ID to set.
     */
    public void setTicketId(String value) {
        this.ticketId = value;
    }

    /**
     * Gets the acceptance status associated with the cashout acknowledgement response.
     *
     * @return The acceptance status associated with the cashout acknowledgement response.
     */
    public AcceptanceStatus getStatus() {
        return this.status;
    }

    /**
     * Sets the acceptance status associated with the cashout acknowledgement response.
     *
     * @param value The acceptance status to set.
     */
    public void setStatus(AcceptanceStatus value) {
        this.status = value;
    }

    /**
     * Builder class for creating instances of the CashoutAckResponse class.
     */
    public static class Builder {

        private final CashoutAckResponse instance = new CashoutAckResponse();

        private Builder() {
        }

        /**
         * Builds and returns the CashoutAckResponse instance.
         *
         * @return The built CashoutAckResponse instance.
         */
        public CashoutAckResponse build() {
            return this.instance;
        }

        /**
         * Sets the code associated with the cashout acknowledgement response.
         *
         * @param value The code to set.
         * @return The Builder instance.
         */
        public Builder setCode(int value) {
            this.instance.setCode(value);
            return this;
        }

        /**
         * Sets the signature associated with the cashout acknowledgement response.
         *
         * @param value The signature to set.
         * @return The Builder instance.
         */
        public Builder setSignature(String value) {
            this.instance.setSignature(value);
            return this;
        }

        /**
         * Sets the cashout ID associated with the cashout acknowledgement response.
         *
         * @param value The cashout ID to set.
         * @return The Builder instance.
         */
        public Builder setCashoutId(String value) {
            this.instance.setCashoutId(value);
            return this;
        }

        /**
         * Sets the message associated with the cashout acknowledgement response.
         *
         * @param value The message to set.
         * @return The Builder instance.
         */
        public Builder setMessage(String value) {
            this.instance.setMessage(value);
            return this;
        }

        /**
         * Sets the ticket ID associated with the cashout acknowledgement response.
         *
         * @param value The ticket ID to set.
         * @return The Builder instance.
         */
        public Builder setTicketId(String value) {
            this.instance.setTicketId(value);
            return this;
        }

        /**
         * Sets the acceptance status associated with the cashout acknowledgement response.
         *
         * @param value The acceptance status to set.
         * @return The Builder instance.
         */
        public Builder setStatus(AcceptanceStatus value) {
            this.instance.setStatus(value);
            return this;
        }
    }
}
