package com.sportradar.mts.sdk.ws.entities.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sportradar.mts.sdk.ws.entities.payout.Payout;

/**
 * Represents the context of a bet, including odds change and payout cap.
 */
public class BetContext {

    @JsonProperty("oddsChange")
    private OddsChange oddsChange;
    @JsonProperty("payoutCap")
    private Payout[] payoutCap;

    /**
     * Creates a new instance of the BetContext.Builder class.
     *
     * @return A new instance of the BetContext.Builder class.
     */
    public static Builder newBuilder() {
        return new Builder();
    }

    /**
     * Gets the odds change of the bet context.
     *
     * @return The odds change of the bet context.
     */
    public OddsChange getOddsChange() {
        return this.oddsChange;
    }

    /**
     * Sets the odds change of the bet context.
     *
     * @param value The odds change to set.
     */
    public void setOddsChange(OddsChange value) {
        this.oddsChange = value;
    }

    /**
     * Gets the payout cap of the bet context.
     *
     * @return The payout cap of the bet context.
     */
    public Payout[] getPayoutCap() {
        return this.payoutCap;
    }

    /**
     * Sets the payout cap of the bet context.
     *
     * @param value The payout cap to set.
     */
    public void setPayoutCap(Payout[] value) {
        this.payoutCap = value;
    }

    /**
     * Builder class for creating instances of the BetContext class.
     */
    public static class Builder {

        private final BetContext instance = new BetContext();

        private Builder() {
        }

        /**
         * Builds and returns the BetContext instance.
         *
         * @return The built BetContext instance.
         */
        public BetContext build() {
            return this.instance;
        }

        /**
         * Sets the odds change of the bet context.
         *
         * @param value The odds change to set.
         * @return The Builder instance.
         */
        public Builder setOddsChange(OddsChange value) {
            this.instance.setOddsChange(value);
            return this;
        }

        /**
         * Sets the payout cap of the bet context.
         *
         * @param value The payout cap to set.
         * @return The Builder instance.
         */
        public Builder setPayoutCap(Payout... value) {
            this.instance.setPayoutCap(value);
            return this;
        }
    }
}