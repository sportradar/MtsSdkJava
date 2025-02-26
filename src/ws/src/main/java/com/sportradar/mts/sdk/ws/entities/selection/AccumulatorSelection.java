package com.sportradar.mts.sdk.ws.entities.selection;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents an accumulator selection.
 */
public class AccumulatorSelection extends Selection {

    @JsonProperty("selections")
    private Selection[] selections;

    /**
     * Creates a new instance of the AccumulatorSelection.Builder class.
     *
     * @return A new instance of the AccumulatorSelection.Builder class.
     */
    public static Builder newBuilder() {
        return new Builder();
    }

    /**
     * Gets the selections in the accumulator.
     *
     * @return An array of Selection objects representing the selections in the accumulator.
     */
    public Selection[] getSelections() {
        return this.selections;
    }

    /**
     * Sets the selections in the accumulator.
     *
     * @param value An array of Selection objects representing the selections in the accumulator.
     */
    public void setSelections(Selection[] value) {
        this.selections = value;
    }

    /**
     * Builder class for creating instances of AccumulatorSelection.
     */
    public static class Builder {

        private final AccumulatorSelection instance = new AccumulatorSelection();

        private Builder() {
        }

        /**
         * Builds the AccumulatorSelection instance.
         *
         * @return The built AccumulatorSelection instance.
         */
        public AccumulatorSelection build() {
            return this.instance;
        }

        /**
         * Sets the selections in the accumulator.
         *
         * @param value An array of Selection objects representing the selections in the accumulator.
         * @return The Builder instance.
         */
        public Builder setSelections(Selection... value) {
            this.instance.setSelections(value);
            return this;
        }
    }
}
