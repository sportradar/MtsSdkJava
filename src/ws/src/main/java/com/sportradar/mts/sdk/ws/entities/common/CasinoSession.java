package com.sportradar.mts.sdk.ws.entities.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sportradar.mts.sdk.ws.entities.casinospin.CasinoSpin;
import com.sportradar.mts.sdk.ws.entities.payout.Payout;
import com.sportradar.mts.sdk.ws.entities.stake.Stake;

/**
 * Represents a casino session.
 */
public class CasinoSession {

    @JsonProperty("stake")
    private Stake[] stake;
    @JsonProperty("game")
    private CasinoGame game;
    @JsonProperty("spins")
    private CasinoSpin[] spins;
    @JsonProperty("payout")
    private Payout[] payout;
    @JsonProperty("id")
    private String id;
    @JsonProperty("startUtc")
    private Long startUtc;
    @JsonProperty("endUtc")
    private Long endUtc;

    /**
     * Creates a new instance of the CasinoSession.Builder class.
     *
     * @return A new instance of the CasinoSession.Builder class.
     */
    public static Builder newBuilder() {
        return new Builder();
    }

    /**
     * Gets the stake array.
     *
     * @return The stake array.
     */
    public Stake[] getStake() {
        return this.stake;
    }

    /**
     * Sets the stake array.
     *
     * @param value The stake array to set.
     */
    public void setStake(Stake[] value) {
        this.stake = value;
    }

    /**
     * Gets the casino game.
     *
     * @return The casino game.
     */
    public CasinoGame getGame() {
        return this.game;
    }

    /**
     * Sets the casino game.
     *
     * @param value The casino game to set.
     */
    public void setGame(CasinoGame value) {
        this.game = value;
    }

    /**
     * Gets the casino spins array.
     *
     * @return The casino spins array.
     */
    public CasinoSpin[] getSpins() {
        return this.spins;
    }

    /**
     * Sets the casino spins array.
     *
     * @param value The casino spins array to set.
     */
    public void setSpins(CasinoSpin[] value) {
        this.spins = value;
    }

    /**
     * Gets the payout array.
     *
     * @return The payout array.
     */
    public Payout[] getPayout() {
        return this.payout;
    }

    /**
     * Sets the payout array.
     *
     * @param value The payout array to set.
     */
    public void setPayout(Payout[] value) {
        this.payout = value;
    }

    /**
     * Gets the ID of the casino session.
     *
     * @return The ID of the casino session.
     */
    public String getId() {
        return this.id;
    }

    /**
     * Sets the ID of the casino session.
     *
     * @param value The ID of the casino session to set.
     */
    public void setId(String value) {
        this.id = value;
    }

    /**
     * Gets the start time of the casino session in UTC millis.
     *
     * @return The start time of the casino session in UTC millis.
     */
    public Long getStartUtc() {
        return this.startUtc;
    }

    /**
     * Sets the start time of the casino session in UTC millis.
     *
     * @param value The start time of the casino session in UTC millis to set.
     */
    public void setStartUtc(Long value) {
        this.startUtc = value;
    }

    /**
     * Gets the end time of the casino session in UTC millis.
     *
     * @return The end time of the casino session in UTC millis.
     */
    public Long getEndUtc() {
        return this.endUtc;
    }

    /**
     * Sets the end time of the casino session in UTC millis.
     *
     * @param value The end time of the casino session in UTC millis to set.
     */
    public void setEndUtc(Long value) {
        this.endUtc = value;
    }

    /**
     * Builder class for creating instances of the CasinoSession class.
     */
    public static class Builder {

        private final CasinoSession instance = new CasinoSession();

        private Builder() {
        }

        /**
         * Builds and returns the CasinoSession instance.
         *
         * @return The built CasinoSession instance.
         */
        public CasinoSession build() {
            return this.instance;
        }

        /**
         * Sets the stake array for the CasinoSession instance being built.
         *
         * @param value The stake array to set.
         * @return The Builder instance.
         */
        public Builder setStake(Stake... value) {
            this.instance.setStake(value);
            return this;
        }

        /**
         * Sets the casino game for the CasinoSession instance being built.
         *
         * @param value The casino game to set.
         * @return The Builder instance.
         */
        public Builder setGame(CasinoGame value) {
            this.instance.setGame(value);
            return this;
        }

        /**
         * Sets the casino spins array for the CasinoSession instance being built.
         *
         * @param value The casino spins array to set.
         * @return The Builder instance.
         */
        public Builder setSpins(CasinoSpin... value) {
            this.instance.setSpins(value);
            return this;
        }

        /**
         * Sets the payout array for the CasinoSession instance being built.
         *
         * @param value The payout array to set.
         * @return The Builder instance.
         */
        public Builder setPayout(Payout... value) {
            this.instance.setPayout(value);
            return this;
        }

        /**
         * Sets the ID for the CasinoSession instance being built.
         *
         * @param value The ID to set.
         * @return The Builder instance.
         */
        public Builder setId(String value) {
            this.instance.setId(value);
            return this;
        }

        /**
         * Sets the start time in UTC millis for the CasinoSession instance being built.
         *
         * @param value The start time in UTC millis to set.
         * @return The Builder instance.
         */
        public Builder setStartUtc(Long value) {
            this.instance.setStartUtc(value);
            return this;
        }

        /**
         * Sets the end time in UTC millis for the CasinoSession instance being built.
         *
         * @param value The end time in UTC millis to set.
         * @return The Builder instance.
         */
        public Builder setEndUtc(Long value) {
            this.instance.setEndUtc(value);
            return this;
        }
    }
}