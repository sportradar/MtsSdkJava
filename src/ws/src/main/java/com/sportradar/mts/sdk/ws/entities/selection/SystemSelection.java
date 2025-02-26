package com.sportradar.mts.sdk.ws.entities.selection;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a system selection, which is a type of selection that contains an array of selections and an array of sizes.
 */
public class SystemSelection extends Selection {

    @JsonProperty("selections")
    private Selection[] selections;
    @JsonProperty("size")
    private int[] size;

    /**
     * Creates a new instance of the SystemSelection.Builder class.
     *
     * @return A new instance of the SystemSelection.Builder class.
     */
    public static Builder newBuilder() {
        return new Builder();
    }

    /**
     * Gets the array of selections contained in this system selection.
     *
     * @return The array of selections.
     */
    public Selection[] getSelections() {
        return this.selections;
    }

    /**
     * Sets the array of selections for this system selection.
     *
     * @param value The array of selections.
     */
    public void setSelections(Selection[] value) {
        this.selections = value;
    }

    /**
     * Gets the array of sizes for this system selection.
     *
     * @return The array of sizes.
     */
    public int[] getSize() {
        return this.size;
    }

    /**
     * Sets the array of sizes for this system selection.
     *
     * @param value The array of sizes.
     */
    public void setSize(int[] value) {
        this.size = value;
    }

    /**
     * Represents a builder for creating instances of the SystemSelection class.
     */
    public static class Builder {

        private final SystemSelection instance = new SystemSelection();

        private Builder() {
        }

        /**
         * Builds and returns the SystemSelection instance.
         *
         * @return The SystemSelection instance.
         */
        public SystemSelection build() {
            return this.instance;
        }

        /**
         * Sets the array of selections for the system selection being built.
         *
         * @param value The array of selections.
         * @return The builder instance.
         */
        public Builder setSelections(Selection... value) {
            this.instance.setSelections(value);
            return this;
        }

        /**
         * Sets the array of sizes for the system selection being built.
         *
         * @param value The array of sizes.
         * @return The builder instance.
         */
        public Builder setSize(int... value) {
            this.instance.setSize(value);
            return this;
        }
    }
}
