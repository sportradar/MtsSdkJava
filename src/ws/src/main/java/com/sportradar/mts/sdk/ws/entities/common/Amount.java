package com.sportradar.mts.sdk.ws.entities.common;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

/**
 * Represents an amount with a currency value.
 */
public class Amount {

    @JsonProperty("currency")
    private String currency;
    @JsonProperty("value")
    private BigDecimal value;

    /**
     * Returns a new instance of the Amount.Builder class.
     *
     * @return a new instance of the Amount.Builder class
     */
    public static Builder newBuilder() {
        return new Builder();
    }

    /**
     * Gets the currency value of the amount.
     *
     * @return the currency value
     */
    public String getCurrency() {
        return this.currency;
    }

    /**
     * Sets the currency value of the amount.
     *
     * @param value the currency value to set
     */
    public void setCurrency(String value) {
        this.currency = value;
    }

    /**
     * Gets the numeric value of the amount.
     *
     * @return the numeric value
     */
    public BigDecimal getValue() {
        return this.value;
    }

    /**
     * Sets the numeric value of the amount.
     *
     * @param value the numeric value to set
     */
    public void setValue(BigDecimal value) {
        this.value = value;
    }

    /**
     * Builder class for creating instances of the Amount class.
     */
    public static class Builder {

        private final Amount instance = new Amount();

        private Builder() {
        }

        /**
         * Builds and returns the Amount instance.
         *
         * @return the built Amount instance
         */
        public Amount build() {
            return this.instance;
        }

        /**
         * Sets the currency value of the amount being built.
         *
         * @param value the currency value to set
         * @return the Builder instance
         */
        public Builder setCurrency(String value) {
            this.instance.setCurrency(value);
            return this;
        }

        /**
         * Sets the numeric value of the amount being built.
         *
         * @param value the numeric value to set
         * @return the Builder instance
         */
        public Builder setValue(BigDecimal value) {
            this.instance.setValue(value);
            return this;
        }
    }
}