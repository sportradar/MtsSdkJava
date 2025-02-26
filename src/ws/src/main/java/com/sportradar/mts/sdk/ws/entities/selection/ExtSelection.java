package com.sportradar.mts.sdk.ws.entities.selection;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sportradar.mts.sdk.ws.entities.odds.Odds;

/**
 * Represents an external selection.
 */
public class ExtSelection extends Selection {

    @JsonProperty("expSettleTime")
    private long expSettleTime;
    @JsonProperty("odds")
    private Odds odds;
    @JsonProperty("event")
    private String event;
    @JsonProperty("outcome")
    private String outcome;

    /**
     * Creates a new instance of the ExtSelection.Builder class.
     *
     * @return A new instance of the ExtSelection.Builder class.
     */
    public static Builder newBuilder() {
        return new Builder();
    }

    /**
     * Gets the expected settlement time of the selection in UTC millis.
     *
     * @return The expected settlement time of the selection in UTC millis.
     */
    public long getExpSettleTime() {
        return this.expSettleTime;
    }

    /**
     * Sets the expected settlement time of the selection in UTC millis.
     *
     * @param value The expected settlement time of the selection in UTC millis.
     */
    public void setExpSettleTime(long value) {
        this.expSettleTime = value;
    }

    /**
     * Gets the odds of the selection.
     *
     * @return The odds of the selection.
     */
    public Odds getOdds() {
        return this.odds;
    }

    /**
     * Sets the odds of the selection.
     *
     * @param value The odds of the selection.
     */
    public void setOdds(Odds value) {
        this.odds = value;
    }

    /**
     * Gets the event associated with the selection.
     *
     * @return The event associated with the selection.
     */
    public String getEvent() {
        return this.event;
    }

    /**
     * Sets the event associated with the selection.
     *
     * @param value The event associated with the selection.
     */
    public void setEvent(String value) {
        this.event = value;
    }

    /**
     * Gets the outcome of the selection.
     *
     * @return The outcome of the selection.
     */
    public String getOutcome() {
        return this.outcome;
    }

    /**
     * Sets the outcome of the selection.
     *
     * @param value The outcome of the selection.
     */
    public void setOutcome(String value) {
        this.outcome = value;
    }

    /**
     * Builder class for creating instances of ExtSelection.
     */
    public static class Builder {

        private final ExtSelection instance = new ExtSelection();

        private Builder() {
        }

        /**
         * Builds and returns the ExtSelection instance.
         *
         * @return The ExtSelection instance.
         */
        public ExtSelection build() {
            return this.instance;
        }

        /**
         * Sets the expected settlement time of the selection in UTC millis.
         *
         * @param value The expected settlement time of the selection in UTC millis.
         * @return The Builder instance.
         */
        public Builder setExpSettleTime(long value) {
            this.instance.setExpSettleTime(value);
            return this;
        }

        /**
         * Sets the odds of the selection.
         *
         * @param value The odds of the selection.
         * @return The Builder instance.
         */
        public Builder setOdds(Odds value) {
            this.instance.setOdds(value);
            return this;
        }

        /**
         * Sets the event associated with the selection.
         *
         * @param value The event associated with the selection.
         * @return The Builder instance.
         */
        public Builder setEvent(String value) {
            this.instance.setEvent(value);
            return this;
        }

        /**
         * Sets the outcome of the selection.
         *
         * @param value The outcome of the selection.
         * @return The Builder instance.
         */
        public Builder setOutcome(String value) {
            this.instance.setOutcome(value);
            return this;
        }
    }
}
