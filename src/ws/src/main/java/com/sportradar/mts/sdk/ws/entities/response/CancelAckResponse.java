package com.sportradar.mts.sdk.ws.entities.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sportradar.mts.sdk.ws.entities.common.AcceptanceStatus;

/**
 * Represents a response for a cancellation acknowledgement.
 */
public class CancelAckResponse extends ContentResponse {

    @JsonProperty("code")
    private int code;
    @JsonProperty("signature")
    private String signature;
    @JsonProperty("cancellationId")
    private String cancellationId;
    @JsonProperty("message")
    private String message;
    @JsonProperty("ticketId")
    private String ticketId;
    @JsonProperty("status")
    private AcceptanceStatus status;

    /**
     * Creates a new instance of the CancelAckResponse.Builder class.
     *
     * @return The new instance of the CancelAckResponse.Builder class.
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
     * Gets the cancellation ID.
     *
     * @return The cancellation ID.
     */
    public String getCancellationId() {
        return this.cancellationId;
    }

    /**
     * Sets the cancellation ID.
     *
     * @param value The cancellation ID to set.
     */
    public void setCancellationId(String value) {
        this.cancellationId = value;
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
     * Builder class for creating instances of CancelAckResponse.
     */
    public static class Builder {

        private final CancelAckResponse instance = new CancelAckResponse();

        private Builder() {
        }

        /**
         * Builds the CancelAckResponse instance.
         *
         * @return The built CancelAckResponse instance.
         */
        public CancelAckResponse build() {
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
         * Sets the cancellation ID.
         *
         * @param value The cancellation ID to set.
         * @return The Builder instance.
         */
        public Builder setCancellationId(String value) {
            this.instance.setCancellationId(value);
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