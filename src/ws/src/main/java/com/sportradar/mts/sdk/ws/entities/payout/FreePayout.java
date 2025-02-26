package com.sportradar.mts.sdk.ws.entities.payout;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

/**
 * Represents a free payout.
 */
public class FreePayout extends Payout {

    @JsonProperty("traceId")
    private String traceId;
    @JsonProperty("amount")
    private BigDecimal amount;
    @JsonProperty("currency")
    private String currency;

    /**
     * Creates a new instance of the FreePayout.Builder class.
     *
     * @return A new instance of the FreePayout.Builder class.
     */
    public static Builder newBuilder() {
        return new Builder();
    }

    /**
     * Gets the trace ID of the free payout.
     *
     * @return The trace ID of the free payout.
     */
    public String getTraceId() {
        return this.traceId;
    }

    /**
     * Sets the trace ID of the free payout.
     *
     * @param value The trace ID of the free payout.
     */
    public void setTraceId(String value) {
        this.traceId = value;
    }

    /**
     * Gets the amount of the free payout.
     *
     * @return The amount of the free payout.
     */
    public BigDecimal getAmount() {
        return this.amount;
    }

    /**
     * Sets the amount of the free payout.
     *
     * @param value The amount of the free payout.
     */
    public void setAmount(BigDecimal value) {
        this.amount = value;
    }

    /**
     * Gets the currency of the free payout.
     *
     * @return The currency of the free payout.
     */
    public String getCurrency() {
        return this.currency;
    }

    /**
     * Sets the currency of the free payout.
     *
     * @param value The currency of the free payout.
     */
    public void setCurrency(String value) {
        this.currency = value;
    }

    /**
     * Builder class for creating instances of the FreePayout class.
     */
    public static class Builder {

        private final FreePayout instance = new FreePayout();

        private Builder() {
        }

        /**
         * Builds and returns the FreePayout instance.
         *
         * @return The FreePayout instance.
         */
        public FreePayout build() {
            return this.instance;
        }

        /**
         * Sets the trace ID of the free payout.
         *
         * @param value The trace ID of the free payout.
         * @return The Builder instance.
         */
        public Builder setTraceId(String value) {
            this.instance.setTraceId(value);
            return this;
        }

        /**
         * Sets the amount of the free payout.
         *
         * @param value The amount of the free payout.
         * @return The Builder instance.
         */
        public Builder setAmount(BigDecimal value) {
            this.instance.setAmount(value);
            return this;
        }

        /**
         * Sets the currency of the free payout.
         *
         * @param value The currency of the free payout.
         * @return The Builder instance.
         */
        public Builder setCurrency(String value) {
            this.instance.setCurrency(value);
            return this;
        }
    }
}