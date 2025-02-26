package com.sportradar.mts.sdk.ws.entities.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sportradar.mts.sdk.ws.entities.common.CashoutInformValidation;

/**
 * Represents a cashout inform request.
 */
public class CashoutInformRequest extends ContentRequest {

    @JsonProperty("cashout")
    private CashoutRequest cashout;
    @JsonProperty("validation")
    private CashoutInformValidation validation;

    /**
     * Creates a new instance of {@link Builder}.
     *
     * @return A new instance of {@link Builder}.
     */
    public static Builder newBuilder() {
        return new Builder();
    }

    /**
     * Gets the {@link CashoutRequest}.
     *
     * @return The {@link CashoutRequest}.
     */
    public CashoutRequest getCashout() {
        return this.cashout;
    }

    /**
     * Sets the {@link CashoutRequest}.
     *
     * @param value The cashout request to be set.
     */
    public void setCashout(CashoutRequest value) {
        this.cashout = value;
    }

    /**
     * Gets the {@link CashoutInformValidation}.
     *
     * @return The cashout inform validation.
     */
    public CashoutInformValidation getValidation() {
        return this.validation;
    }

    /**
     * Sets the {@link CashoutInformValidation}.
     *
     * @param value The cashout inform validation to set.
     */
    public void setValidation(CashoutInformValidation value) {
        this.validation = value;
    }

    /**
     * Represents a builder for {@link CashoutInformRequest}.
     */
    public static class Builder {

        private final CashoutInformRequest instance = new CashoutInformRequest();

        private Builder() {
        }

        /**
         * Builds the {@link CashoutInformRequest} instance.
         *
         * @return The built {@link CashoutInformRequest} instance.
         */
        public CashoutInformRequest build() {
            return this.instance;
        }

        /**
         * Sets the {@link CashoutRequest} of the cashout inform request.
         *
         * @param cashout The {@link CashoutRequest} to set.
         * @return Instance of {@link Builder}.
         */
        public Builder setCashoutReq(CashoutRequest cashout) {
            this.instance.setCashout(cashout);
            return this;
        }

        /**
         * Sets the cashout inform validation.
         *
         * @param value The {@link CashoutInformValidation} to set.
         * @return Instance of {@link Builder}.
         */
        public Builder setValidation(CashoutInformValidation value) {
            this.instance.setValidation(value);
            return this;
        }
    }
}
