package com.sportradar.mts.sdk.ws.entities.casinospin;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents an ordinary casino spin.
 */
public class OrdinaryCasinoSpin extends CasinoSpin {

    @JsonProperty("count")
    private int count;
    @JsonProperty("winningCount")
    private Integer winningCount;

    /**
     * Creates a new instance of the OrdinaryCasinoSpin.Builder class.
     *
     * @return A new instance of the OrdinaryCasinoSpin.Builder class.
     */
    public static Builder newBuilder() {
        return new Builder();
    }

    /**
     * Gets the count of the spins.
     *
     * @return The count of the spins.
     */
    public int getCount() {
        return this.count;
    }

    /**
     * Sets the count of the spins.
     *
     * @param value The count of the spins.
     */
    public void setCount(int value) {
        this.count = value;
    }

    /**
     * Gets the count of the winning spins.
     *
     * @return The count of the winning spins.
     */
    public Integer getWinningCount() {
        return this.winningCount;
    }

    /**
     * Sets the count of the winning spins.
     *
     * @param value The count of the winning spins.
     */
    public void setWinningCount(Integer value) {
        this.winningCount = value;
    }

    /**
     * Represents a builder for the OrdinaryCasinoSpin class.
     */
    public static class Builder {

        private final OrdinaryCasinoSpin instance = new OrdinaryCasinoSpin();

        private Builder() {
        }

        /**
         * Builds and returns the OrdinaryCasinoSpin instance.
         *
         * @return The OrdinaryCasinoSpin instance.
         */
        public OrdinaryCasinoSpin build() {
            return this.instance;
        }

        /**
         * Sets the count of the spins.
         *
         * @param value The count of the spins.
         * @return The builder instance.
         */
        public Builder setCount(int value) {
            this.instance.setCount(value);
            return this;
        }

        /**
         * Sets the count of the winning spins.
         *
         * @param value The count of the winning spins.
         * @return The builder instance.
         */
        public Builder setWinningCount(Integer value) {
            this.instance.setWinningCount(value);
            return this;
        }
    }
}
