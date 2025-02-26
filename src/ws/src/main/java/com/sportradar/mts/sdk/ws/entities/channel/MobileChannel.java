package com.sportradar.mts.sdk.ws.entities.channel;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a mobile channel.
 */
public class MobileChannel extends Channel {

    @JsonProperty("ip")
    private String ip;
    @JsonProperty("lang")
    private String lang;
    @JsonProperty("deviceId")
    private String deviceId;

    /**
     * Creates a new instance of the MobileChannel.Builder class.
     *
     * @return A new instance of the MobileChannel.Builder class.
     */
    public static Builder newBuilder() {
        return new Builder();
    }

    /**
     * Gets the IP address associated with the mobile channel.
     *
     * @return The IP address associated with the mobile channel.
     */
    public String getIp() {
        return this.ip;
    }

    /**
     * Sets the IP address for the mobile channel.
     *
     * @param value The IP address to set.
     */
    public void setIp(String value) {
        this.ip = value;
    }

    /**
     * Gets the language associated with the mobile channel.
     *
     * @return The language associated with the mobile channel.
     */
    public String getLang() {
        return this.lang;
    }

    /**
     * Sets the language for the mobile channel.
     *
     * @param value The language to set.
     */
    public void setLang(String value) {
        this.lang = value;
    }

    /**
     * Gets the device ID associated with the mobile channel.
     *
     * @return The device ID associated with the mobile channel.
     */
    public String getDeviceId() {
        return this.deviceId;
    }

    /**
     * Sets the device ID for the mobile channel.
     *
     * @param value The device ID to set.
     */
    public void setDeviceId(String value) {
        this.deviceId = value;
    }

    /**
     * Builder class for creating instances of the MobileChannel class.
     */
    public static class Builder {

        private final MobileChannel instance = new MobileChannel();

        private Builder() {
        }

        /**
         * Builds and returns the MobileChannel instance.
         *
         * @return The built MobileChannel instance.
         */
        public MobileChannel build() {
            return this.instance;
        }

        /**
         * Sets the IP address for the mobile channel being built.
         *
         * @param value The IP address to set.
         * @return The Builder instance.
         */
        public Builder setIp(String value) {
            this.instance.setIp(value);
            return this;
        }

        /**
         * Sets the language for the mobile channel being built.
         *
         * @param value The language to set.
         * @return The Builder instance.
         */
        public Builder setLang(String value) {
            this.instance.setLang(value);
            return this;
        }

        /**
         * Sets the device ID for the mobile channel being built.
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