package com.sportradar.mts.sdk.ws.entities.stake;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

/**
 * Represents a free rollover stake.
 */
public class FreeRolloverStake extends Stake {

    @JsonProperty("mode")
    private StakeMode mode;
    @JsonProperty("traceId")
    private String traceId;
    @JsonProperty("amount")
    private BigDecimal amount;
    @JsonProperty("currency")
    private String currency;

    /**
     * Creates a new instance of the FreeRolloverStake.Builder class.
     *
     * @return The new instance of the FreeRolloverStake.Builder class.
     */
    public static Builder newBuilder() {
        return new Builder();
    }

    /**
     * Gets the stake mode.
     *
     * @return The stake mode.
     */
    public StakeMode getMode() {
        return this.mode;
    }

    /**
     * Sets the stake mode.
     *
     * @param value The stake mode to set.
     */
    public void setMode(StakeMode value) {
        this.mode = value;
    }

    /**
     * Gets the trace ID.
     *
     * @return The trace ID.
     */
    public String getTraceId() {
        return this.traceId;
    }

    /**
     * Sets the trace ID.
     *
     * @param value The trace ID to set.
     */
    public void setTraceId(String value) {
        this.traceId = value;
    }

    /**
     * Gets the stake amount.
     *
     * @return The stake amount.
     */
    public BigDecimal getAmount() {
        return this.amount;
    }

    /**
     * Sets the stake amount.
     *
     * @param value The stake amount to set.
     */
    public void setAmount(BigDecimal value) {
        this.amount = value;
    }

    /**
     * Gets the currency.
     *
     * @return The currency.
     */
    public String getCurrency() {
        return this.currency;
    }

    /**
     * Sets the currency.
     *
     * @param value The currency to set.
     */
    public void setCurrency(String value) {
        this.currency = value;
    }

    /**
     * Builder class for creating instances of FreeRolloverStake.
     */
    public static class Builder {

        private final FreeRolloverStake instance = new FreeRolloverStake();

        private Builder() {
        }

        /**
         * Builds the FreeRolloverStake instance.
         *
         * @return The built FreeRolloverStake instance.
         */
        public FreeRolloverStake build() {
            return this.instance;
        }

        /**
         * Sets the stake mode.
         *
         * @param value The stake mode to set.
         * @return The Builder instance.
         */
        public Builder setMode(StakeMode value) {
            this.instance.setMode(value);
            return this;
        }

        /**
         * Sets the trace ID.
         *
         * @param value The trace ID to set.
         * @return The Builder instance.
         */
        public Builder setTraceId(String value) {
            this.instance.setTraceId(value);
            return this;
        }

        /**
         * Sets the stake amount.
         *
         * @param value The stake amount to set.
         * @return The Builder instance.
         */
        public Builder setAmount(BigDecimal value) {
            this.instance.setAmount(value);
            return this;
        }

        /**
         * Sets the currency.
         *
         * @param value The currency to set.
         * @return The Builder instance.
         */
        public Builder setCurrency(String value) {
            this.instance.setCurrency(value);
            return this;
        }
    }
}
