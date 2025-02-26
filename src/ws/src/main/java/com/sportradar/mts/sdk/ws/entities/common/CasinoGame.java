package com.sportradar.mts.sdk.ws.entities.common;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a casino game.
 */
public class CasinoGame {

    @JsonProperty("provider")
    private String provider;
    @JsonProperty("id")
    private String id;

    /**
     * Creates a new instance of the CasinoGame.Builder class.
     *
     * @return A new instance of the CasinoGame.Builder class.
     */
    public static Builder newBuilder() {
        return new Builder();
    }

    /**
     * Get the provider of the casino game.
     *
     * @return the provider
     */
    public String getProvider() {
        return this.provider;
    }

    /**
     * Set the provider of the casino game.
     *
     * @param value the provider to set
     */
    public void setProvider(String value) {
        this.provider = value;
    }

    /**
     * Get the ID of the casino game.
     *
     * @return the ID
     */
    public String getId() {
        return this.id;
    }

    /**
     * Set the ID of the casino game.
     *
     * @param value the ID to set
     */
    public void setId(String value) {
        this.id = value;
    }

    /**
     * A builder for a casino game.
     */
    public static class Builder {

        private final CasinoGame instance = new CasinoGame();

        private Builder() {
        }

        public CasinoGame build() {
            return this.instance;
        }

        /**
         * Set the provider of the casino game.
         *
         * @param value the provider to set
         * @return the builder instance
         */
        public Builder setProvider(String value) {
            this.instance.setProvider(value);
            return this;
        }

        /**
         * Set the ID of the casino game.
         *
         * @param value the ID to set
         * @return the builder instance
         */
        public Builder setId(String value) {
            this.instance.setId(value);
            return this;
        }
    }
}
