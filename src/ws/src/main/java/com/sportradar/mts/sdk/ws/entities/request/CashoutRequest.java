package com.sportradar.mts.sdk.ws.entities.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sportradar.mts.sdk.ws.entities.cashout.CashoutDetails;

/**
 * Represents a cashout request.
 */
public class CashoutRequest extends ContentRequest {

    @JsonProperty("cashoutId")
    private String cashoutId;
    @JsonProperty("details")
    private CashoutDetails details;

    /**
     * Creates a new instance of CashoutRequest.Builder.
     *
     * @return The new instance of CashoutRequest.Builder.
     */
    public static Builder newBuilder() {
        return new Builder();
    }

    /**
     * Gets the cashout ID.
     *
     * @return The cashout ID.
     */
    public String getCashoutId() {
        return this.cashoutId;
    }

    /**
     * Sets the cashout ID.
     *
     * @param value The cashout ID to set.
     */
    public void setCashoutId(String value) {
        this.cashoutId = value;
    }

    /**
     * Gets the cashout details.
     *
     * @return The cashout details.
     */
    public CashoutDetails getDetails() {
        return this.details;
    }

    /**
     * Sets the cashout details.
     *
     * @param value The cashout details to set.
     */
    public void setDetails(CashoutDetails value) {
        this.details = value;
    }

    /**
     * Represents a builder for CashoutRequest.
     */
    public static class Builder {

        private final CashoutRequest instance = new CashoutRequest();

        private Builder() {
        }

        /**
         * Builds the CashoutRequest instance.
         *
         * @return The built CashoutRequest instance.
         */
        public CashoutRequest build() {
            return this.instance;
        }

        /**
         * Sets the cashout ID.
         *
         * @param value The cashout ID to set.
         * @return The builder instance.
         */
        public Builder setCashoutId(String value) {
            this.instance.setCashoutId(value);
            return this;
        }

        /**
         * Sets the cashout details.
         *
         * @param value The cashout details to set.
         * @return The builder instance.
         */
        public Builder setDetails(CashoutDetails value) {
            this.instance.setDetails(value);
            return this;
        }
    }
}
