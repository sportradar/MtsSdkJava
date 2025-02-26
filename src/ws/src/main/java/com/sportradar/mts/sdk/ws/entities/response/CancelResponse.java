package com.sportradar.mts.sdk.ws.entities.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sportradar.mts.sdk.ws.entities.common.AcceptanceStatus;

/**
 * Represents a response for a cancellation operation.
 */
public class CancelResponse extends ContentResponse {

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
     * Creates a new instance of the CancelResponse.Builder class.
     *
     * @return A new instance of the CancelResponse.Builder class.
     */
    public static Builder newBuilder() {
        return new Builder();
    }

    /**
     * Gets the code associated with the cancellation response.
     *
     * @return The code associated with the cancellation response.
     */
    public int getCode() {
        return this.code;
    }

    /**
     * Sets the code associated with the cancellation response.
     *
     * @param value The code associated with the cancellation response.
     */
    public void setCode(int value) {
        this.code = value;
    }

    /**
     * Gets the signature associated with the cancellation response.
     *
     * @return The signature associated with the cancellation response.
     */
    public String getSignature() {
        return this.signature;
    }

    /**
     * Sets the signature associated with the cancellation response.
     *
     * @param value The signature associated with the cancellation response.
     */
    public void setSignature(String value) {
        this.signature = value;
    }

    /**
     * Gets the cancellation ID associated with the cancellation response.
     *
     * @return The cancellation ID associated with the cancellation response.
     */
    public String getCancellationId() {
        return this.cancellationId;
    }

    /**
     * Sets the cancellation ID associated with the cancellation response.
     *
     * @param value The cancellation ID associated with the cancellation response.
     */
    public void setCancellationId(String value) {
        this.cancellationId = value;
    }

    /**
     * Gets the message associated with the cancellation response.
     *
     * @return The message associated with the cancellation response.
     */
    public String getMessage() {
        return this.message;
    }

    /**
     * Sets the message associated with the cancellation response.
     *
     * @param value The message associated with the cancellation response.
     */
    public void setMessage(String value) {
        this.message = value;
    }

    /**
     * Gets the ticket ID associated with the cancellation response.
     *
     * @return The ticket ID associated with the cancellation response.
     */
    public String getTicketId() {
        return this.ticketId;
    }

    /**
     * Sets the ticket ID associated with the cancellation response.
     *
     * @param value The ticket ID associated with the cancellation response.
     */
    public void setTicketId(String value) {
        this.ticketId = value;
    }

    /**
     * Gets the acceptance status associated with the cancellation response.
     *
     * @return The acceptance status associated with the cancellation response.
     */
    public AcceptanceStatus getStatus() {
        return this.status;
    }

    /**
     * Sets the acceptance status associated with the cancellation response.
     *
     * @param value The acceptance status associated with the cancellation response.
     */
    public void setStatus(AcceptanceStatus value) {
        this.status = value;
    }

    /**
     * Builder class for creating instances of the CancelResponse class.
     */
    public static class Builder {

        private final CancelResponse instance = new CancelResponse();

        private Builder() {
        }

        /**
         * Builds and returns the CancelResponse instance.
         *
         * @return The CancelResponse instance.
         */
        public CancelResponse build() {
            return this.instance;
        }

        /**
         * Sets the code associated with the cancellation response.
         *
         * @param value The code associated with the cancellation response.
         * @return The Builder instance.
         */
        public Builder setCode(int value) {
            this.instance.setCode(value);
            return this;
        }

        /**
         * Sets the signature associated with the cancellation response.
         *
         * @param value The signature associated with the cancellation response.
         * @return The Builder instance.
         */
        public Builder setSignature(String value) {
            this.instance.setSignature(value);
            return this;
        }

        /**
         * Sets the cancellation ID associated with the cancellation response.
         *
         * @param value The cancellation ID associated with the cancellation response.
         * @return The Builder instance.
         */
        public Builder setCancellationId(String value) {
            this.instance.setCancellationId(value);
            return this;
        }

        /**
         * Sets the message associated with the cancellation response.
         *
         * @param value The message associated with the cancellation response.
         * @return The Builder instance.
         */
        public Builder setMessage(String value) {
            this.instance.setMessage(value);
            return this;
        }

        /**
         * Sets the ticket ID associated with the cancellation response.
         *
         * @param value The ticket ID associated with the cancellation response.
         * @return The Builder instance.
         */
        public Builder setTicketId(String value) {
            this.instance.setTicketId(value);
            return this;
        }

        /**
         * Sets the acceptance status associated with the cancellation response.
         *
         * @param value The acceptance status associated with the cancellation response.
         * @return The Builder instance.
         */
        public Builder setStatus(AcceptanceStatus value) {
            this.instance.setStatus(value);
            return this;
        }
    }
}
