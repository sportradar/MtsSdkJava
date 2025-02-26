package com.sportradar.mts.sdk.ws.entities.stake;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

/**
 * Represents a bonus stake in the system.
 */
public class BonusStake extends Stake {

    @JsonProperty("mode")
    private StakeMode mode;
    @JsonProperty("traceId")
    private String traceId;
    @JsonProperty("amount")
    private BigDecimal amount;
    @JsonProperty("currency")
    private String currency;

    /**
     * Returns a new instance of the BonusStake.Builder class.
     *
     * @return A new instance of the BonusStake.Builder class.
     */
    public static Builder newBuilder() {
        return new Builder();
    }

    /**
     * Returns the stake mode.
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
     * Returns the trace ID.
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
     * Returns the stake amount.
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
     * Returns the currency.
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
     * Builder class for creating instances of BonusStake.
     */
    public static class Builder {

        private final BonusStake instance = new BonusStake();

        private Builder() {
        }

        /**
         * Builds and returns the BonusStake instance.
         *
         * @return The built BonusStake instance.
         */
        public BonusStake build() {
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