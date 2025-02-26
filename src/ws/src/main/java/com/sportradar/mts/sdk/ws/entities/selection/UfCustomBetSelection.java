package com.sportradar.mts.sdk.ws.entities.selection;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sportradar.mts.sdk.ws.entities.odds.Odds;

/**
 * Represents a custom bet selection for the UF (Unified Feed) platform.
 * Extends the base Selection class.
 */
public class UfCustomBetSelection extends Selection {

    @JsonProperty("selections")
    private UfSelection[] selections;
    @JsonProperty("odds")
    private Odds odds;

    /**
     * Creates a new instance of the UfCustomBetSelection class.
     */
    public static Builder newBuilder() {
        return new Builder();
    }

    /**
     * Gets the selections for the custom bet.
     *
     * @return An array of UfSelection objects representing the selections.
     */
    public UfSelection[] getSelections() {
        return this.selections;
    }

    /**
     * Sets the selections for the custom bet.
     *
     * @param value An array of UfSelection objects representing the selections.
     */
    public void setSelections(UfSelection[] value) {
        this.selections = value;
    }

    /**
     * Gets the odds for the custom bet.
     *
     * @return An Odds object representing the odds.
     */
    public Odds getOdds() {
        return this.odds;
    }

    /**
     * Sets the odds for the custom bet.
     *
     * @param value An Odds object representing the odds.
     */
    public void setOdds(Odds value) {
        this.odds = value;
    }

    /**
     * Builder class for creating instances of UfCustomBetSelection.
     */
    public static class Builder {

        private final UfCustomBetSelection instance = new UfCustomBetSelection();

        private Builder() {
        }

        /**
         * Builds and returns the UfCustomBetSelection instance.
         *
         * @return The built UfCustomBetSelection instance.
         */
        public UfCustomBetSelection build() {
            return this.instance;
        }

        /**
         * Sets the selections for the custom bet.
         *
         * @param value An array of UfSelection objects representing the selections.
         * @return The Builder instance.
         */
        public Builder setSelections(UfSelection... value) {
            this.instance.setSelections(value);
            return this;
        }

        /**
         * Sets the odds for the custom bet.
         *
         * @param value An Odds object representing the odds.
         * @return The Builder instance.
         */
        public Builder setOdds(Odds value) {
            this.instance.setOdds(value);
            return this;
        }
    }
}
