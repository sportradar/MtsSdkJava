package com.sportradar.mts.sdk.ws.entities.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sportradar.mts.sdk.ws.entities.selection.Selection;
import com.sportradar.mts.sdk.ws.entities.stake.Stake;

/**
 * Represents a bet.
 */
public class Bet {

    @JsonProperty("stake")
    private Stake[] stake;
    @JsonProperty("selections")
    private Selection[] selections;
    @JsonProperty("betId")
    private String betId;
    @JsonProperty("context")
    private BetContext context;

    /**
     * Creates a new instance of the Bet class.
     *
     * @return A new instance of the Bet class.
     */
    public static Builder newBuilder() {
        return new Builder();
    }

    /**
     * Gets the stake of the bet.
     *
     * @return The stake of the bet.
     */
    public Stake[] getStake() {
        return this.stake;
    }

    /**
     * Sets the stake of the bet.
     *
     * @param value The stake of the bet.
     */
    public void setStake(Stake[] value) {
        this.stake = value;
    }

    /**
     * Gets the selections of the bet.
     *
     * @return The selections of the bet.
     */
    public Selection[] getSelections() {
        return this.selections;
    }

    /**
     * Sets the selections of the bet.
     *
     * @param value The selections of the bet.
     */
    public void setSelections(Selection[] value) {
        this.selections = value;
    }

    /**
     * Gets the ID of the bet.
     *
     * @return The ID of the bet.
     */
    public String getBetId() {
        return this.betId;
    }

    /**
     * Sets the ID of the bet.
     *
     * @param value The ID of the bet.
     */
    public void setBetId(String value) {
        this.betId = value;
    }

    /**
     * Gets the context of the bet.
     *
     * @return The context of the bet.
     */
    public BetContext getContext() {
        return this.context;
    }

    /**
     * Sets the context of the bet.
     *
     * @param value The context of the bet.
     */
    public void setContext(BetContext value) {
        this.context = value;
    }

    /**
     * A builder class for creating instances of the Bet class.
     */
    public static class Builder {

        private final Bet instance = new Bet();

        private Builder() {
        }

        /**
         * Builds and returns the instance of the Bet class.
         *
         * @return The instance of the Bet class.
         */
        public Bet build() {
            return this.instance;
        }

        /**
         * Sets the stake of the bet.
         *
         * @param value The stake of the bet.
         * @return The builder instance.
         */
        public Builder setStake(Stake... value) {
            this.instance.setStake(value);
            return this;
        }

        /**
         * Sets the selections of the bet.
         *
         * @param value The selections of the bet.
         * @return The builder instance.
         */
        public Builder setSelections(Selection... value) {
            this.instance.setSelections(value);
            return this;
        }

        /**
         * Sets the ID of the bet.
         *
         * @param value The ID of the bet.
         * @return The builder instance.
         */
        public Builder setBetId(String value) {
            this.instance.setBetId(value);
            return this;
        }

        /**
         * Sets the context of the bet.
         *
         * @param value The context of the bet.
         * @return The builder instance.
         */
        public Builder setContext(BetContext value) {
            this.instance.setContext(value);
            return this;
        }
    }
}
