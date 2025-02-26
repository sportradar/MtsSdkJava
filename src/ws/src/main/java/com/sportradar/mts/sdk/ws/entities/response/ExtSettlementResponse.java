package com.sportradar.mts.sdk.ws.entities.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sportradar.mts.sdk.ws.entities.common.AcceptanceStatus;

/**
 * Represents an extended settlement response.
 */
public class ExtSettlementResponse extends ContentResponse {

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
     * Creates a new instance of the ExtSettlementResponse.Builder class.
     *
     * @return A new instance of the ExtSettlementResponse.Builder class.
     */
    public static Builder newBuilder() {
        return new Builder();
    }

    /**
     * Gets the code.
     *
     * @return The code.
     */
    public int getCode() {
        return this.code;
    }

    /**
     * Sets the code.
     *
     * @param value The code to set.
     */
    public void setCode(int value) {
        this.code = value;
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
     * @param value The signature to set.
     */
    public void setSignature(String value) {
        this.signature = value;
    }

    /**
     * Gets the settlement ID.
     *
     * @return The settlement ID.
     */
    public String getSettlementId() {
        return this.settlementId;
    }

    /**
     * Sets the settlement ID.
     *
     * @param value The settlement ID to set.
     */
    public void setSettlementId(String value) {
        this.settlementId = value;
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
     * @param value The message to set.
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
     * @param value The ticket ID to set.
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
     * @param value The acceptance status to set.
     */
    public void setStatus(AcceptanceStatus value) {
        this.status = value;
    }

    /**
     * Builder class for creating instances of ExtSettlementResponse.
     */
    public static class Builder {

        private final ExtSettlementResponse instance = new ExtSettlementResponse();

        private Builder() {
        }

        /**
         * Builds the ExtSettlementResponse instance.
         *
         * @return The ExtSettlementResponse instance.
         */
        public ExtSettlementResponse build() {
            return this.instance;
        }

        /**
         * Sets the code.
         *
         * @param value The code to set.
         * @return The Builder instance.
         */
        public Builder setCode(int value) {
            this.instance.setCode(value);
            return this;
        }

        /**
         * Sets the signature.
         *
         * @param value The signature to set.
         * @return The Builder instance.
         */
        public Builder setSignature(String value) {
            this.instance.setSignature(value);
            return this;
        }

        /**
         * Sets the settlement ID.
         *
         * @param value The settlement ID to set.
         * @return The Builder instance.
         */
        public Builder setSettlementId(String value) {
            this.instance.setSettlementId(value);
            return this;
        }

        /**
         * Sets the message.
         *
         * @param value The message to set.
         * @return The Builder instance.
         */
        public Builder setMessage(String value) {
            this.instance.setMessage(value);
            return this;
        }

        /**
         * Sets the ticket ID.
         *
         * @param value The ticket ID to set.
         * @return The Builder instance.
         */
        public Builder setTicketId(String value) {
            this.instance.setTicketId(value);
            return this;
        }

        /**
         * Sets the acceptance status.
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
