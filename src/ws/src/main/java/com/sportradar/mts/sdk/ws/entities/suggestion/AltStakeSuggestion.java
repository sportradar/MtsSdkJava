package com.sportradar.mts.sdk.ws.entities.suggestion;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sportradar.mts.sdk.ws.entities.stake.Stake;

/**
 * Represents an alternative stake suggestion.
 */
public class AltStakeSuggestion extends Suggestion {

    @JsonProperty("stake")
    private Stake[] stake;

    /**
     * Creates a new instance of the AltStakeSuggestion.Builder class.
     *
     * @return A new instance of the AltStakeSuggestion.Builder class.
     */
    public static Builder newBuilder() {
        return new Builder();
    }

    /**
     * Gets the stake array.
     *
     * @return The stake array.
     */
    public Stake[] getStake() {
        return this.stake;
    }

    /**
     * Sets the stake array.
     *
     * @param value The stake array to set.
     */
    public void setStake(Stake[] value) {
        this.stake = value;
    }

    /**
     * Represents a builder for the AltStakeSuggestion class.
     */
    public static class Builder {

        private final AltStakeSuggestion instance = new AltStakeSuggestion();

        private Builder() {
        }

        /**
         * Builds the AltStakeSuggestion instance.
         *
         * @return The built AltStakeSuggestion instance.
         */
        public AltStakeSuggestion build() {
            return this.instance;
        }

        /**
         * Sets the stake array for the AltStakeSuggestion instance.
         *
         * @param value The stake array to set.
         * @return The builder instance.
         */
        public Builder setStake(Stake... value) {
            this.instance.setStake(value);
            return this;
        }
    }
}
