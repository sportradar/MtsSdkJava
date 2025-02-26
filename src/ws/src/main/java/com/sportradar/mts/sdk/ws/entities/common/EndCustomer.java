package com.sportradar.mts.sdk.ws.entities.common;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

/**
 * Represents an end customer.
 */
public class EndCustomer {

    @JsonProperty("confidence")
    private BigDecimal confidence;
    @JsonProperty("id")
    private String id;

    /**
     * Creates a new instance of the {@code EndCustomer.Builder} class.
     *
     * @return A new instance of the {@code EndCustomer.Builder} class.
     */
    public static Builder newBuilder() {
        return new Builder();
    }

    /**
     * Gets the confidence value of the end customer.
     *
     * @return The confidence value of the end customer.
     */
    public BigDecimal getConfidence() {
        return this.confidence;
    }

    /**
     * Sets the confidence value of the end customer.
     *
     * @param value The confidence value to set.
     */
    public void setConfidence(BigDecimal value) {
        this.confidence = value;
    }

    /**
     * Gets the ID of the end customer.
     *
     * @return The ID of the end customer.
     */
    public String getId() {
        return this.id;
    }

    /**
     * Sets the ID of the end customer.
     *
     * @param value The ID to set.
     */
    public void setId(String value) {
        this.id = value;
    }

    /**
     * A builder class for creating instances of the {@code EndCustomer} class.
     */
    public static class Builder {

        private final EndCustomer instance = new EndCustomer();

        private Builder() {
        }

        /**
         * Builds and returns the instance of the {@code EndCustomer} class.
         *
         * @return The instance of the {@code EndCustomer} class.
         */
        public EndCustomer build() {
            return this.instance;
        }

        /**
         * Sets the confidence value of the end customer.
         *
         * @param value The confidence value to set.
         * @return The builder instance.
         */
        public Builder setConfidence(BigDecimal value) {
            this.instance.setConfidence(value);
            return this;
        }

        /**
         * Sets the ID of the end customer.
         *
         * @param value The ID to set.
         * @return The builder instance.
         */
        public Builder setId(String value) {
            this.instance.setId(value);
            return this;
        }
    }
}
