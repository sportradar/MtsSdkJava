package com.sportradar.mts.sdk.ws.entities.casinospin;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a free casino spin.
 */
public class FreeCasinoSpin extends CasinoSpin {

    @JsonProperty("count")
    private int count;
    @JsonProperty("winningCount")
    private Integer winningCount;

    /**
     * Creates a new instance of FreeCasinoSpin.Builder.
     *
     * @return A new instance of FreeCasinoSpin.Builder.
     */
    public static Builder newBuilder() {
        return new Builder();
    }

    /**
     * Gets the count of free spins.
     *
     * @return The count of free spins.
     */
    public int getCount() {
        return this.count;
    }

    /**
     * Sets the count of free spins.
     *
     * @param value The count of free spins.
     */
    public void setCount(int value) {
        this.count = value;
    }

    /**
     * Gets the count of winning free spins.
     *
     * @return The count of winning free spins.
     */
    public Integer getWinningCount() {
        return this.winningCount;
    }

    /**
     * Sets the count of winning free spins.
     *
     * @param value The count of winning free spins.
     */
    public void setWinningCount(Integer value) {
        this.winningCount = value;
    }

    /**
     * Builder class for creating instances of FreeCasinoSpin.
     */
    public static class Builder {

        private final FreeCasinoSpin instance = new FreeCasinoSpin();

        private Builder() {
        }

        /**
         * Builds and returns the FreeCasinoSpin instance.
         *
         * @return The FreeCasinoSpin instance.
         */
        public FreeCasinoSpin build() {
            return this.instance;
        }

        /**
         * Sets the count of free spins.
         *
         * @param value The count of free spins.
         * @return The Builder instance.
         */
        public Builder setCount(int value) {
            this.instance.setCount(value);
            return this;
        }

        /**
         * Sets the count of winning free spins.
         *
         * @param value The count of winning free spins.
         * @return The Builder instance.
         */
        public Builder setWinningCount(Integer value) {
            this.instance.setWinningCount(value);
            return this;
        }
    }
}
