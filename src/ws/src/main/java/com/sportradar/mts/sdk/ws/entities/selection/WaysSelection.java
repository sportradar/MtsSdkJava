package com.sportradar.mts.sdk.ws.entities.selection;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a selection with multiple ways.
 */
public class WaysSelection extends Selection {

    @JsonProperty("selections")
    private Selection[] selections;

    /**
     * Creates a new instance of the WaysSelection.Builder class.
     *
     * @return A new instance of the WaysSelection.Builder class.
     */
    public static Builder newBuilder() {
        return new Builder();
    }

    /**
     * Gets the selections within the ways selection.
     *
     * @return An array of Selection objects representing the selections within the ways selection.
     */
    public Selection[] getSelections() {
        return this.selections;
    }

    /**
     * Sets the selections within the ways selection.
     *
     * @param value An array of Selection objects representing the selections within the ways selection.
     */
    public void setSelections(Selection[] value) {
        this.selections = value;
    }

    /**
     * A builder class for creating instances of the WaysSelection class.
     */
    public static class Builder {

        private final WaysSelection instance = new WaysSelection();

        private Builder() {
        }

        /**
         * Builds and returns the WaysSelection instance.
         *
         * @return The built WaysSelection instance.
         */
        public WaysSelection build() {
            return this.instance;
        }

        /**
         * Sets the selections within the ways selection.
         *
         * @param value An array of Selection objects representing the selections within the ways selection.
         * @return The current instance of the Builder class.
         */
        public Builder setSelections(Selection... value) {
            this.instance.setSelections(value);
            return this;
        }
    }
}
