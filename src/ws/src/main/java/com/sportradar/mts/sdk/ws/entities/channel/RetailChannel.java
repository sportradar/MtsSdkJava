package com.sportradar.mts.sdk.ws.entities.channel;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a retail channel.
 */
public class RetailChannel extends Channel {

    @JsonProperty("shopId")
    private String shopId;
    @JsonProperty("lang")
    private String lang;
    @JsonProperty("deviceId")
    private String deviceId;

    /**
     * Creates a new instance of RetailChannel.Builder.
     *
     * @return The new instance of RetailChannel.Builder.
     */
    public static Builder newBuilder() {
        return new Builder();
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
     * Gets the device ID.
     *
     * @return The device ID.
     */
    public String getDeviceId() {
        return this.deviceId;
    }

    /**
     * Sets the device ID.
     *
     * @param value The device ID to set.
     */
    public void setDeviceId(String value) {
        this.deviceId = value;
    }

    /**
     * Builder class for RetailChannel.
     */
    public static class Builder {

        private final RetailChannel instance = new RetailChannel();

        private Builder() {
        }

        /**
         * Builds and returns the RetailChannel instance.
         *
         * @return The RetailChannel instance.
         */
        public RetailChannel build() {
            return this.instance;
        }

        /**
         * Sets the shop ID.
         *
         * @param value The shop ID to set.
         * @return The Builder instance.
         */
        public Builder setShopId(String value) {
            this.instance.setShopId(value);
            return this;
        }

        /**
         * Sets the language.
         *
         * @param value The language to set.
         * @return The Builder instance.
         */
        public Builder setLang(String value) {
            this.instance.setLang(value);
            return this;
        }

        /**
         * Sets the device ID.
         *
         * @param value The device ID to set.
         * @return The Builder instance.
         */
        public Builder setDeviceId(String value) {
            this.instance.setDeviceId(value);
            return this;
        }
    }
}
