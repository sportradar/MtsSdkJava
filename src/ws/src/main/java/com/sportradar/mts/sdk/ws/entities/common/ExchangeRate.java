package com.sportradar.mts.sdk.ws.entities.common;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

/**
 * Represents an exchange rate between two currencies.
 */
public class ExchangeRate {

    @JsonProperty("toCurrency")
    private String toCurrency;
    @JsonProperty("rate")
    private BigDecimal rate;
    @JsonProperty("fromCurrency")
    private String fromCurrency;

    /**
     * Creates a new instance of the ExchangeRate.Builder class.
     *
     * @return A new instance of the ExchangeRate.Builder class.
     */
    public static Builder newBuilder() {
        return new Builder();
    }

    /**
     * Gets the currency code of the target currency.
     *
     * @return The currency code of the target currency.
     */
    public String getToCurrency() {
        return this.toCurrency;
    }

    /**
     * Sets the currency code of the target currency.
     *
     * @param value The currency code of the target currency.
     */
    public void setToCurrency(String value) {
        this.toCurrency = value;
    }

    /**
     * Gets the exchange rate value.
     *
     * @return The exchange rate value.
     */
    public BigDecimal getRate() {
        return this.rate;
    }

    /**
     * Sets the exchange rate value.
     *
     * @param value The exchange rate value.
     */
    public void setRate(BigDecimal value) {
        this.rate = value;
    }

    /**
     * Gets the currency code of the source currency.
     *
     * @return The currency code of the source currency.
     */
    public String getFromCurrency() {
        return this.fromCurrency;
    }

    /**
     * Sets the currency code of the source currency.
     *
     * @param value The currency code of the source currency.
     */
    public void setFromCurrency(String value) {
        this.fromCurrency = value;
    }

    /**
     * Builder class for creating instances of the ExchangeRate class.
     */
    public static class Builder {

        private final ExchangeRate instance = new ExchangeRate();

        private Builder() {
        }

        /**
         * Builds and returns the ExchangeRate instance.
         *
         * @return The ExchangeRate instance.
         */
        public ExchangeRate build() {
            return this.instance;
        }

        /**
         * Sets the currency code of the target currency.
         *
         * @param value The currency code of the target currency.
         * @return The Builder instance.
         */
        public Builder setToCurrency(String value) {
            this.instance.setToCurrency(value);
            return this;
        }

        /**
         * Sets the exchange rate value.
         *
         * @param value The exchange rate value.
         * @return The Builder instance.
         */
        public Builder setRate(BigDecimal value) {
            this.instance.setRate(value);
            return this;
        }

        /**
         * Sets the currency code of the source currency.
         *
         * @param value The currency code of the source currency.
         * @return The Builder instance.
         */
        public Builder setFromCurrency(String value) {
            this.instance.setFromCurrency(value);
            return this;
        }
    }
}