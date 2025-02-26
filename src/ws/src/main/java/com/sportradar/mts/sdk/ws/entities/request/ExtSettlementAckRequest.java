package com.sportradar.mts.sdk.ws.entities.request;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a request to acknowledge an external settlement.
 */
public class ExtSettlementAckRequest extends ContentRequest {

    @JsonProperty("acknowledged")
    private boolean acknowledged;
    @JsonProperty("settlementSignature")
    private String settlementSignature;
    @JsonProperty("settlementId")
    private String settlementId;
    @JsonProperty("ticketId")
    private String ticketId;

    /**
     * Creates a new instance of the ExtSettlementAckRequest.Builder class.
     *
     * @return A new instance of the ExtSettlementAckRequest.Builder class.
     */
    public static Builder newBuilder() {
        return new Builder();
    }

    /**
     * Gets the value indicating whether the settlement has been acknowledged.
     *
     * @return True if the settlement has been acknowledged, otherwise false.
     */
    public boolean getAcknowledged() {
        return this.acknowledged;
    }

    /**
     * Sets the value indicating whether the settlement has been acknowledged.
     *
     * @param value True if the settlement has been acknowledged, otherwise false.
     */
    public void setAcknowledged(boolean value) {
        this.acknowledged = value;
    }

    /**
     * Gets the settlement signature.
     *
     * @return The settlement signature.
     */
    public String getSettlementSignature() {
        return this.settlementSignature;
    }

    /**
     * Sets the settlement signature.
     *
     * @param value The settlement signature.
     */
    public void setSettlementSignature(String value) {
        this.settlementSignature = value;
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
     * @param value The settlement ID.
     */
    public void setSettlementId(String value) {
        this.settlementId = value;
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
     * Builder class for creating instances of ExtSettlementAckRequest.
     */
    public static class Builder {

        private final ExtSettlementAckRequest instance = new ExtSettlementAckRequest();

        private Builder() {
        }

        /**
         * Builds the ExtSettlementAckRequest instance.
         *
         * @return The ExtSettlementAckRequest instance.
         */
        public ExtSettlementAckRequest build() {
            return this.instance;
        }

        /**
         * Sets the value indicating whether the settlement has been acknowledged.
         *
         * @param value True if the settlement has been acknowledged, otherwise false.
         * @return The Builder instance.
         */
        public Builder setAcknowledged(boolean value) {
            this.instance.setAcknowledged(value);
            return this;
        }

        /**
         * Sets the settlement signature.
         *
         * @param value The settlement signature.
         * @return The Builder instance.
         */
        public Builder setSettlementSignature(String value) {
            this.instance.setSettlementSignature(value);
            return this;
        }

        /**
         * Sets the settlement ID.
         *
         * @param value The settlement ID.
         * @return The Builder instance.
         */
        public Builder setSettlementId(String value) {
            this.instance.setSettlementId(value);
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