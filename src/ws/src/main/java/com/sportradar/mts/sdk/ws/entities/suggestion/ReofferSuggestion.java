package com.sportradar.mts.sdk.ws.entities.suggestion;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sportradar.mts.sdk.ws.entities.stake.Stake;

/**
 * Represents a reoffer suggestion.
 * Extends the base class Suggestion.
 */
public class ReofferSuggestion extends Suggestion {

    @JsonProperty("mode")
    private String mode;
    @JsonProperty("stake")
    private Stake[] stake;

    /**
     * Creates a new instance of ReofferSuggestion.Builder.
     *
     * @return A new instance of ReofferSuggestion.Builder.
     */
    public static Builder newBuilder() {
        return new Builder();
    }

    /**
     * Gets the mode of the reoffer suggestion.
     *
     * @return The mode of the reoffer suggestion.
     */
    public String getMode() {
        return this.mode;
    }

    /**
     * Sets the mode of the reoffer suggestion.
     *
     * @param value The mode of the reoffer suggestion.
     */
    public void setMode(String value) {
        this.mode = value;
    }

    /**
     * Gets the stake of the reoffer suggestion.
     *
     * @return The stake of the reoffer suggestion.
     */
    public Stake[] getStake() {
        return this.stake;
    }

    /**
     * Sets the stake of the reoffer suggestion.
     *
     * @param value The stake of the reoffer suggestion.
     */
    public void setStake(Stake[] value) {
        this.stake = value;
    }

    /**
     * Builder class for creating instances of ReofferSuggestion.
     */
    public static class Builder {

        private final ReofferSuggestion instance = new ReofferSuggestion();

        private Builder() {
        }

        /**
         * Builds and returns the ReofferSuggestion instance.
         *
         * @return The ReofferSuggestion instance.
         */
        public ReofferSuggestion build() {
            return this.instance;
        }

        /**
         * Sets the mode of the reoffer suggestion.
         *
         * @param value The mode of the reoffer suggestion.
         * @return The Builder instance.
         */
        public Builder setMode(String value) {
            this.instance.setMode(value);
            return this;
        }

        /**
         * Sets the stake of the reoffer suggestion.
         *
         * @param value The stake of the reoffer suggestion.
         * @return The Builder instance.
         */
        public Builder setStake(Stake... value) {
            this.instance.setStake(value);
            return this;
        }
    }
}
