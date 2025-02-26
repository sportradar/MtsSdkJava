package com.sportradar.mts.sdk.ws.entities.odds;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

/**
 * Represents decimal odds.
 */
public class DecimalOdds extends Odds {

    @JsonProperty("value")
    private BigDecimal value;

    /**
     * Creates a new instance of the DecimalOdds.Builder class.
     *
     * @return A new instance of the DecimalOdds.Builder class.
     */
    public static Builder newBuilder() {
        return new Builder();
    }

    /**
     * Gets the value of the decimal odds.
     *
     * @return The value of the decimal odds.
     */
    public BigDecimal getValue() {
        return this.value;
    }

    /**
     * Sets the value of the decimal odds.
     *
     * @param value The value of the decimal odds.
     */
    public void setValue(BigDecimal value) {
        this.value = value;
    }

    /**
     * Builder class for creating instances of the DecimalOdds class.
     */
    public static class Builder {

        private final DecimalOdds instance = new DecimalOdds();

        private Builder() {
        }

        /**
         * Builds and returns the DecimalOdds instance.
         *
         * @return The built DecimalOdds instance.
         */
        public DecimalOdds build() {
            return this.instance;
        }

        /**
         * Sets the value of the decimal odds.
         *
         * @param value The value of the decimal odds.
         * @return The DecimalOdds.Builder instance.
         */
        public Builder setValue(BigDecimal value) {
            this.instance.setValue(value);
            return this;
        }
    }
}
