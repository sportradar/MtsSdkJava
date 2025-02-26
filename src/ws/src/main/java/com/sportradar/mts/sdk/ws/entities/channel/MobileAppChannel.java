package com.sportradar.mts.sdk.ws.entities.channel;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a mobile app channel.
 */
public class MobileAppChannel extends Channel {

    @JsonProperty("ip")
    private String ip;
    @JsonProperty("lang")
    private String lang;
    @JsonProperty("deviceId")
    private String deviceId;

    /**
     * Creates a new instance of MobileAppChannel.Builder.
     *
     * @return The builder instance.
     */
    public static Builder newBuilder() {
        return new Builder();
    }

    /**
     * Gets the IP address of the mobile app channel.
     *
     * @return The IP address.
     */
    public String getIp() {
        return this.ip;
    }

    /**
     * Sets the IP address of the mobile app channel.
     *
     * @param value The IP address to set.
     */
    public void setIp(String value) {
        this.ip = value;
    }

    /**
     * Gets the language of the mobile app channel.
     *
     * @return The language.
     */
    public String getLang() {
        return this.lang;
    }

    /**
     * Sets the language of the mobile app channel.
     *
     * @param value The language to set.
     */
    public void setLang(String value) {
        this.lang = value;
    }

    /**
     * Gets the device ID of the mobile app channel.
     *
     * @return The device ID.
     */
    public String getDeviceId() {
        return this.deviceId;
    }

    /**
     * Sets the device ID of the mobile app channel.
     *
     * @param value The device ID to set.
     */
    public void setDeviceId(String value) {
        this.deviceId = value;
    }

    /**
     * Builder class for MobileAppChannel.
     */
    public static class Builder {

        private final MobileAppChannel instance = new MobileAppChannel();

        private Builder() {
        }

        /**
         * Builds the MobileAppChannel instance.
         *
         * @return The built MobileAppChannel instance.
         */
        public MobileAppChannel build() {
            return this.instance;
        }

        /**
         * Sets the IP address of the mobile app channel.
         *
         * @param value The IP address to set.
         * @return The builder instance.
         */
        public Builder setIp(String value) {
            this.instance.setIp(value);
            return this;
        }

        /**
         * Sets the language of the mobile app channel.
         *
         * @param value The language to set.
         * @return The builder instance.
         */
        public Builder setLang(String value) {
            this.instance.setLang(value);
            return this;
        }

        /**
         * Sets the device ID of the mobile app channel.
         *
         * @param value The device ID to set.
         * @return The builder instance.
         */
        public Builder setDeviceId(String value) {
            this.instance.setDeviceId(value);
            return this;
        }
    }
}
