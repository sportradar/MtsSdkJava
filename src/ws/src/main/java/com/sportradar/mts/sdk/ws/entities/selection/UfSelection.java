package com.sportradar.mts.sdk.ws.entities.selection;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sportradar.mts.sdk.ws.entities.odds.Odds;

/**
 * Represents a selection for a UF event.
 */
public class UfSelection extends Selection {

    @JsonProperty("eventId")
    private String eventId;
    @JsonProperty("productId")
    private String productId;
    @JsonProperty("odds")
    private Odds odds;
    @JsonProperty("outcomeId")
    private String outcomeId;
    @JsonProperty("specifiers")
    private String specifiers;
    @JsonProperty("marketId")
    private String marketId;

    /**
     * Creates a new instance of the UfSelection.Builder class.
     *
     * @return The UfSelection.Builder instance.
     */
    public static Builder newBuilder() {
        return new Builder();
    }

    /**
     * Gets the event ID of the selection.
     *
     * @return The event ID.
     */
    public String getEventId() {
        return this.eventId;
    }

    /**
     * Sets the event ID of the selection.
     *
     * @param value The event ID to set.
     */
    public void setEventId(String value) {
        this.eventId = value;
    }

    /**
     * Gets the product ID of the selection.
     *
     * @return The product ID.
     */
    public String getProductId() {
        return this.productId;
    }

    /**
     * Sets the product ID of the selection.
     *
     * @param value The product ID to set.
     */
    public void setProductId(String value) {
        this.productId = value;
    }

    /**
     * Gets the odds of the selection.
     *
     * @return The odds.
     */
    public Odds getOdds() {
        return this.odds;
    }

    /**
     * Sets the odds of the selection.
     *
     * @param value The odds to set.
     */
    public void setOdds(Odds value) {
        this.odds = value;
    }

    /**
     * Gets the outcome ID of the selection.
     *
     * @return The outcome ID.
     */
    public String getOutcomeId() {
        return this.outcomeId;
    }

    /**
     * Sets the outcome ID of the selection.
     *
     * @param value The outcome ID to set.
     */
    public void setOutcomeId(String value) {
        this.outcomeId = value;
    }

    /**
     * Gets the specifiers of the selection.
     *
     * @return The specifiers.
     */
    public String getSpecifiers() {
        return this.specifiers;
    }

    /**
     * Sets the specifiers of the selection.
     *
     * @param value The specifiers to set.
     */
    public void setSpecifiers(String value) {
        this.specifiers = value;
    }

    /**
     * Gets the market ID of the selection.
     *
     * @return The market ID.
     */
    public String getMarketId() {
        return this.marketId;
    }

    /**
     * Sets the market ID of the selection.
     *
     * @param value The market ID to set.
     */
    public void setMarketId(String value) {
        this.marketId = value;
    }

    /**
     * Builder class for creating instances of UfSelection.
     */
    public static class Builder {

        private final UfSelection instance = new UfSelection();

        private Builder() {
        }

        /**
         * Builds and returns the UfSelection instance.
         *
         * @return The UfSelection instance.
         */
        public UfSelection build() {
            return this.instance;
        }

        /**
         * Sets the event ID of the selection.
         *
         * @param value The event ID to set.
         * @return The Builder instance.
         */
        public Builder setEventId(String value) {
            this.instance.setEventId(value);
            return this;
        }

        /**
         * Sets the product ID of the selection.
         *
         * @param value The product ID to set.
         * @return The Builder instance.
         */
        public Builder setProductId(String value) {
            this.instance.setProductId(value);
            return this;
        }

        /**
         * Sets the odds of the selection.
         *
         * @param value The odds to set.
         * @return The Builder instance.
         */
        public Builder setOdds(Odds value) {
            this.instance.setOdds(value);
            return this;
        }

        /**
         * Sets the outcome ID of the selection.
         *
         * @param value The outcome ID to set.
         * @return The Builder instance.
         */
        public Builder setOutcomeId(String value) {
            this.instance.setOutcomeId(value);
            return this;
        }

        /**
         * Sets the specifiers of the selection.
         *
         * @param value The specifiers to set.
         * @return The Builder instance.
         */
        public Builder setSpecifiers(String value) {
            this.instance.setSpecifiers(value);
            return this;
        }

        /**
         * Sets the market ID of the selection.
         *
         * @param value The market ID to set.
         * @return The Builder instance.
         */
        public Builder setMarketId(String value) {
            this.instance.setMarketId(value);
            return this;
        }
    }
}
