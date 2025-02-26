package com.sportradar.mts.sdk.ws.entities.odds;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

/**
 * Represents hong kong odds.
 */
public class HongKongOdds extends Odds {

    @JsonProperty("value")
    private BigDecimal value;

    /**
     * Creates a new instance of the HongKongOdds.Builder class.
     *
     * @return A new instance of the HongKongOdds.Builder class.
     */
    public static Builder newBuilder() {
        return new Builder();
    }

    /**
     * Gets the value of the hong kong odds.
     *
     * @return The value of the hong kong odds.
     */
    public BigDecimal getValue() {
        return this.value;
    }

    /**
     * Sets the value of the hong kong odds.
     *
     * @param value The value of the hong kong odds.
     */
    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public static class Builder {

        private final HongKongOdds instance = new HongKongOdds();

        private Builder() {
        }

        /**
         * Builds and returns the HongKongOdds instance.
         *
         * @return The built HongKongOdds instance.
         */
        public HongKongOdds build() {
            return this.instance;
        }

        /**
         * Sets the value of the hong kong odds.
         *
         * @param value The value of the hong kong odds.
         * @return The HongKongOdds.Builder instance.
         */
        public Builder setValue(BigDecimal value) {
            this.instance.setValue(value);
            return this;
        }
    }
}
