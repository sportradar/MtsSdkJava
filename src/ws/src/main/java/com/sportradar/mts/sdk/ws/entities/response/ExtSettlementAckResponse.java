package com.sportradar.mts.sdk.ws.entities.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sportradar.mts.sdk.ws.entities.common.AcceptanceStatus;

/**
 * Represents an extended settlement acknowledgment response.
 */
public class ExtSettlementAckResponse extends ContentResponse {

    @JsonProperty("code")
    private int code;
    @JsonProperty("signature")
    private String signature;
    @JsonProperty("settlementId")
    private String settlementId;
    @JsonProperty("message")
    private String message;
    @JsonProperty("ticketId")
    private String ticketId;
    @JsonProperty("status")
    private AcceptanceStatus status;

    /**
     * Creates a new instance of the ExtSettlementAckResponse.Builder class.
     *
     * @return A new instance of the ExtSettlementAckResponse.Builder class.
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
     * Gets the signature of the response.
     *
     * @return The signature of the response.
     */
    public String getSignature() {
        return this.signature;
    }

    /**
     * Sets the signature of the response.
     *
     * @param value The signature of the response.
     */
    public void setSignature(String value) {
        this.signature = value;
    }

    /**
     * Gets the settlement ID of the response.
     *
     * @return The settlement ID of the response.
     */
    public String getSettlementId() {
        return this.settlementId;
    }

    /**
     * Sets the settlement ID of the response.
     *
     * @param value The settlement ID of the response.
     */
    public void setSettlementId(String value) {
        this.settlementId = value;
    }

    /**
     * Gets the message of the response.
     *
     * @return The message of the response.
     */
    public String getMessage() {
        return this.message;
    }

    /**
     * Sets the message of the response.
     *
     * @param value The message of the response.
     */
    public void setMessage(String value) {
        this.message = value;
    }

    /**
     * Gets the ticket ID of the response.
     *
     * @return The ticket ID of the response.
     */
    public String getTicketId() {
        return this.ticketId;
    }

    /**
     * Sets the ticket ID of the response.
     *
     * @param value The ticket ID of the response.
     */
    public void setTicketId(String value) {
        this.ticketId = value;
    }

    /**
     * Gets the acceptance status of the response.
     *
     * @return The acceptance status of the response.
     */
    public AcceptanceStatus getStatus() {
        return this.status;
    }

    /**
     * Sets the acceptance status of the response.
     *
     * @param value The acceptance status of the response.
     */
    public void setStatus(AcceptanceStatus value) {
        this.status = value;
    }

    /**
     * Represents a builder for the ExtSettlementAckResponse class.
     */
    public static class Builder {

        private final ExtSettlementAckResponse instance = new ExtSettlementAckResponse();

        private Builder() {
        }

        /**
         * Builds the ExtSettlementAckResponse instance.
         *
         * @return The built ExtSettlementAckResponse instance.
         */
        public ExtSettlementAckResponse build() {
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
         * Sets the signature of the response.
         *
         * @param value The signature of the response.
         * @return The builder instance.
         */
        public Builder setSignature(String value) {
            this.instance.setSignature(value);
            return this;
        }

        /**
         * Sets the settlement ID of the response.
         *
         * @param value The settlement ID of the response.
         * @return The builder instance.
         */
        public Builder setSettlementId(String value) {
            this.instance.setSettlementId(value);
            return this;
        }

        /**
         * Sets the message of the response.
         *
         * @param value The message of the response.
         * @return The builder instance.
         */
        public Builder setMessage(String value) {
            this.instance.setMessage(value);
            return this;
        }

        /**
         * Sets the ticket ID of the response.
         *
         * @param value The ticket ID of the response.
         * @return The builder instance.
         */
        public Builder setTicketId(String value) {
            this.instance.setTicketId(value);
            return this;
        }

        /**
         * Sets the acceptance status of the response.
         *
         * @param value The acceptance status of the response.
         * @return The builder instance.
         */
        public Builder setStatus(AcceptanceStatus value) {
            this.instance.setStatus(value);
            return this;
        }
    }
}
