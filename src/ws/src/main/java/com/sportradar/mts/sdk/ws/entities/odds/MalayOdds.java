package com.sportradar.mts.sdk.ws.entities.odds;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

/**
 * Represents malay odds.
 */
public class MalayOdds extends Odds {

    @JsonProperty("value")
    private BigDecimal value;

    /**
     * Creates a new instance of the MalayOdds.Builder class.
     *
     * @return A new instance of the MalayOdds.Builder class.
     */
    public static Builder newBuilder() {
        return new Builder();
    }

    /**
     * Gets the value of the malay odds.
     *
     * @return The value of the malay odds.
     */
    public BigDecimal getValue() {
        return this.value;
    }

    /**
     * Sets the value of the malay odds.
     *
     * @param value The value of the malay odds.
     */
    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public static class Builder {

        private final MalayOdds instance = new MalayOdds();

        private Builder() {
        }

        /**
         * Builds and returns the MalayOdds instance.
         *
         * @return The built MalayOdds instance.
         */
        public MalayOdds build() {
            return this.instance;
        }

        /**
         * Sets the value of the malay odds.
         *
         * @param value The value of the malay odds.
         * @return The MalayOdds.Builder instance.
         */
        public Builder setValue(BigDecimal value) {
            this.instance.setValue(value);
            return this;
        }
    }
}
