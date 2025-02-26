package com.sportradar.mts.sdk.ws.entities.channel;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a terminal channel.
 */
public class TerminalChannel extends Channel {

    @JsonProperty("terminalId")
    private String terminalId;
    @JsonProperty("shopId")
    private String shopId;
    @JsonProperty("lang")
    private String lang;

    /**
     * Creates a new instance of TerminalChannel.Builder.
     *
     * @return The TerminalChannel.Builder instance.
     */
    public static Builder newBuilder() {
        return new Builder();
    }

    /**
     * Gets the terminal ID.
     *
     * @return The terminal ID.
     */
    public String getTerminalId() {
        return this.terminalId;
    }

    /**
     * Sets the terminal ID.
     *
     * @param value The terminal ID to set.
     */
    public void setTerminalId(String value) {
        this.terminalId = value;
    }

    /**
     * Gets the shop ID.
     *
     * @return The shop ID.
     */
    public String getShopId() {
        return this.shopId;
    }

    /**
     * Sets the shop ID.
     *
     * @param value The shop ID to set.
     */
    public void setShopId(String value) {
        this.shopId = value;
    }

    /**
     * Gets the language.
     *
     * @return The language.
     */
    public String getLang() {
        return this.lang;
    }

    /**
     * Sets the language.
     *
     * @param value The language to set.
     */
    public void setLang(String value) {
        this.lang = value;
    }

    /**
     * Builder class for TerminalChannel.
     */
    public static class Builder {

        private final TerminalChannel instance = new TerminalChannel();

        private Builder() {
        }

        /**
         * Builds the TerminalChannel instance.
         *
         * @return The built TerminalChannel instance.
         */
        public TerminalChannel build() {
            return this.instance;
        }

        /**
         * Sets the terminal ID.
         *
         * @param value The terminal ID to set.
         * @return The TerminalChannel.Builder instance.
         */
        public Builder setTerminalId(String value) {
            this.instance.setTerminalId(value);
            return this;
        }

        /**
         * Sets the shop ID.
         *
         * @param value The shop ID to set.
         * @return The TerminalChannel.Builder instance.
         */
        public Builder setShopId(String value) {
            this.instance.setShopId(value);
            return this;
        }

        /**
         * Sets the language.
         *
         * @param value The language to set.
         * @return The TerminalChannel.Builder instance.
         */
        public Builder setLang(String value) {
            this.instance.setLang(value);
            return this;
        }
    }
}