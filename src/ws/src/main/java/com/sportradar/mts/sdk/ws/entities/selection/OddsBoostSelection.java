package com.sportradar.mts.sdk.ws.entities.selection;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sportradar.mts.sdk.ws.entities.odds.Odds;

/**
 * Represents an odds boost selection.
 */
public class OddsBoostSelection extends Selection {

    @JsonProperty("selection")
    private Selection selection;
    @JsonProperty("odds")
    private Odds odds;

    /**
     * Creates a new instance of the OddsBoostSelection.Builder class.
     *
     * @return A new instance of the OddsBoostSelection.Builder class.
     */
    public static Builder newBuilder() {
        return new Builder();
    }

    /**
     * Gets the selection.
     *
     * @return The selection.
     */
    public Selection getSelection() {
        return this.selection;
    }

    /**
     * Sets the selection.
     *
     * @param value The selection to set.
     */
    public void setSelection(Selection value) {
        this.selection = value;
    }

    /**
     * Gets the odds.
     *
     * @return The odds.
     */
    public Odds getOdds() {
        return this.odds;
    }

    /**
     * Sets the odds.
     *
     * @param value The odds to set.
     */
    public void setOdds(Odds value) {
        this.odds = value;
    }

    /**
     * Represents a builder for the OddsBoostSelection class.
     */
    public static class Builder {

        private final OddsBoostSelection instance = new OddsBoostSelection();

        private Builder() {
        }

        /**
         * Builds the OddsBoostSelection instance.
         *
         * @return The built OddsBoostSelection instance.
         */
        public OddsBoostSelection build() {
            return this.instance;
        }

        /**
         * Sets the selection.
         *
         * @param value The selection to set.
         * @return The builder instance.
         */
        public Builder setSelection(Selection value) {
            this.instance.setSelection(value);
            return this;
        }

        /**
         * Sets the odds.
         *
         * @param value The odds to set.
         * @return The builder instance.
         */
        public Builder setOdds(Odds value) {
            this.instance.setOdds(value);
            return this;
        }
    }
}
