package com.sportradar.mts.sdk.ws.entities.odds;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigInteger;

/**
 * Represents moneyline (american) odds.
 */
public class MoneylineOdds extends Odds {

    @JsonProperty("value")
    private BigInteger value;

    /**
     * Creates a new instance of the MoneylineOdds.Builder class.
     *
     * @return A new instance of the MoneylineOdds.Builder class.
     */
    public static Builder newBuilder() {
        return new Builder();
    }

    /**
     * Gets the value of the moneyline odds.
     *
     * @return The value of the moneyline odds.
     */
    public BigInteger getValue() {
        return this.value;
    }

    /**
     * Sets the value of the moneyline odds.
     *
     * @param value The value of the moneyline odds.
     */
    public void setValue(BigInteger value) {
        this.value = value;
    }

    public static class Builder {

        private final MoneylineOdds instance = new MoneylineOdds();

        private Builder() {
        }

        /**
         * Builds and returns the MoneylineOdds instance.
         *
         * @return The built MoneylineOdds instance.
         */
        public MoneylineOdds build() {
            return this.instance;
        }

        /**
         * Sets the value of the moneyline odds.
         *
         * @param value The value of the moneyline odds.
         * @return The MoneylineOdds.Builder instance.
         */
        public Builder setValue(BigInteger value) {
            this.instance.setValue(value);
            return this;
        }
    }
}
