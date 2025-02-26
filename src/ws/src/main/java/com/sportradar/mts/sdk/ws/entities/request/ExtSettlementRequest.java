package com.sportradar.mts.sdk.ws.entities.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sportradar.mts.sdk.ws.entities.settlement.ExtSettlementDetails;

/**
 * Represents an external settlement request.
 */
public class ExtSettlementRequest extends ContentRequest {

    @JsonProperty("details")
    private ExtSettlementDetails details;
    @JsonProperty("settlementId")
    private String settlementId;

    /**
     * Creates a new instance of ExtSettlementRequest.Builder.
     *
     * @return The new instance of ExtSettlementRequest.Builder.
     */
    public static Builder newBuilder() {
        return new Builder();
    }

    /**
     * Gets the details of the external settlement request.
     *
     * @return The details of the external settlement request.
     */
    public ExtSettlementDetails getDetails() {
        return this.details;
    }

    /**
     * Sets the details of the external settlement request.
     *
     * @param value The details of the external settlement request.
     */
    public void setDetails(ExtSettlementDetails value) {
        this.details = value;
    }

    /**
     * Gets the settlement ID of the external settlement request.
     *
     * @return The settlement ID of the external settlement request.
     */
    public String getSettlementId() {
        return this.settlementId;
    }

    /**
     * Sets the settlement ID of the external settlement request.
     *
     * @param value The settlement ID of the external settlement request.
     */
    public void setSettlementId(String value) {
        this.settlementId = value;
    }

    /**
     * Builder class for creating instances of ExtSettlementRequest.
     */
    public static class Builder {

        private final ExtSettlementRequest instance = new ExtSettlementRequest();

        private Builder() {
        }

        /**
         * Builds the external settlement request.
         *
         * @return The built external settlement request.
         */
        public ExtSettlementRequest build() {
            return this.instance;
        }

        /**
         * Sets the details of the external settlement request.
         *
         * @param value The details of the external settlement request.
         * @return The builder instance.
         */
        public Builder setDetails(ExtSettlementDetails value) {
            this.instance.setDetails(value);
            return this;
        }

        /**
         * Sets the settlement ID of the external settlement request.
         *
         * @param value The settlement ID of the external settlement request.
         * @return The builder instance.
         */
        public Builder setSettlementId(String value) {
            this.instance.setSettlementId(value);
            return this;
        }
    }
}
