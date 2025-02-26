package com.sportradar.mts.sdk.ws.entities.odds;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

/**
 * Represents indonesian odds.
 */
public class IndonesianOdds extends Odds {

    @JsonProperty("value")
    private BigDecimal value;

    /**
     * Creates a new instance of the IndonesianOdds.Builder class.
     *
     * @return A new instance of the IndonesianOdds.Builder class.
     */
    public static Builder newBuilder() {
        return new Builder();
    }

    /**
     * Gets the value of the indonesian odds.
     *
     * @return The value of the indonesian odds.
     */
    public BigDecimal getValue() {
        return this.value;
    }

    /**
     * Sets the value of the indonesian odds.
     *
     * @param value The value of the indonesian odds.
     */
    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public static class Builder {

        private final IndonesianOdds instance = new IndonesianOdds();

        private Builder() {
        }

        /**
         * Builds and returns the IndonesianOdds instance.
         *
         * @return The built IndonesianOdds instance.
         */
        public IndonesianOdds build() {
            return this.instance;
        }

        /**
         * Sets the value of the indonesian odds.
         *
         * @param value The value of the indonesian odds.
         * @return The IndonesianOdds.Builder instance.
         */
        public Builder setValue(BigDecimal value) {
            this.instance.setValue(value);
            return this;
        }
    }
}
