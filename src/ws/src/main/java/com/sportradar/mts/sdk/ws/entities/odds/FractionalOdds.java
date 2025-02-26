package com.sportradar.mts.sdk.ws.entities.odds;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigInteger;

/**
 * Represents fractional odds.
 */
public class FractionalOdds extends Odds {

    @JsonProperty("numerator")
    private BigInteger numerator;
    @JsonProperty("denominator")
    private BigInteger denominator;

    /**
     * Creates a new instance of the FractionalOdds.Builder class.
     *
     * @return A new instance of the FractionalOdds.Builder class.
     */
    public static Builder newBuilder() {
        return new Builder();
    }

    /**
     * Gets the value of the numerator (top, first part) of the fractional odds.
     *
     * @return The value of the numerator.
     */
    public BigInteger getNumerator() {
        return this.numerator;
    }

    /**
     * Sets the value of the numerator (top, first part) of the fractional odds.
     *
     * @param value The value of the decimal odds.
     */
    public void setNumerator(BigInteger value) {
        this.numerator = value;
    }

    /**
     * Gets the value of the denominator (bottom, last part) of the fractional odds.
     *
     * @return The value of the denominator.
     */
    public BigInteger getDenominator() {
        return this.denominator;
    }

    /**
     * Sets the value of the denominator (bottom, last part) of the fractional odds.
     *
     * @param value The value of the decimal odds.
     */
    public void setDenominator(BigInteger value) {
        this.denominator = value;
    }

    public static class Builder {

        private final FractionalOdds instance = new FractionalOdds();

        private Builder() {
        }

        /**
         * Builds and returns the FractionalOdds instance.
         *
         * @return The built FractionalOdds instance.
         */
        public FractionalOdds build() {
            return this.instance;
        }

        /**
         * Sets the value of the denominator (bottom, last part) of the fractional odds.
         *
         * @param value The value of the denominator (bottom, last part) of the fractional odds.
         * @return The FractionalOdds.Builder instance.
         */
        public Builder setNumerator(BigInteger value) {
            this.instance.setNumerator(value);
            return this;
        }

        /**
         * Sets the value of the denominator (bottom, last part) of the fractional odds.
         *
         * @param value The value of the denominator (bottom, last part) of the fractional odds.
         * @return The FractionalOdds.Builder instance.
         */
        public Builder setDenominator(BigInteger value) {
            this.instance.setDenominator(value);
            return this;
        }
    }
}
