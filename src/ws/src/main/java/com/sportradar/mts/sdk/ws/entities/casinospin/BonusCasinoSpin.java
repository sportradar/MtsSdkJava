package com.sportradar.mts.sdk.ws.entities.casinospin;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a bonus casino spin.
 */
public class BonusCasinoSpin extends CasinoSpin {

    @JsonProperty("count")
    private int count;
    @JsonProperty("winningCount")
    private Integer winningCount;

    /**
     * Creates a new instance of BonusCasinoSpin.Builder.
     *
     * @return A new instance of BonusCasinoSpin.Builder.
     */
    public static Builder newBuilder() {
        return new Builder();
    }

    /**
     * Gets the count of the bonus casino spins.
     *
     * @return The count of the bonus casino spins.
     */
    public int getCount() {
        return this.count;
    }

    /**
     * Sets the count of the bonus casino spins.
     *
     * @param value The count of the bonus casino spins.
     */
    public void setCount(int value) {
        this.count = value;
    }

    /**
     * Gets the count of the winning bonus casino spins.
     *
     * @return The count of the winning bonus casino spins.
     */
    public Integer getWinningCount() {
        return this.winningCount;
    }

    /**
     * Sets the count of the winning bonus casino spins.
     *
     * @param value The count of the winning bonus casino spins.
     */
    public void setWinningCount(Integer value) {
        this.winningCount = value;
    }

    /**
     * Builder class for creating instances of the BonusCasinoSpin class.
     */
    public static class Builder {

        private final BonusCasinoSpin instance = new BonusCasinoSpin();

        private Builder() {
        }

        /**
         * Builds and returns the BonusCasinoSpin instance.
         *
         * @return The built BonusCasinoSpin instance.
         */
        public BonusCasinoSpin build() {
            return this.instance;
        }

        /**
         * Sets the count of the bonus casino spins.
         *
         * @param value The count of the bonus casino spins.
         * @return The Builder instance.
         */
        public Builder setCount(int value) {
            this.instance.setCount(value);
            return this;
        }

        /**
         * Sets the count of the winning bonus casino spins.
         *
         * @param value The count of the winning bonus casino spins.
         * @return The Builder instance.
         */
        public Builder setWinningCount(Integer value) {
            this.instance.setWinningCount(value);
            return this;
        }
    }
}
