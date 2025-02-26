package com.sportradar.mts.sdk.ws.entities.channel;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a TV app channel.
 */
public class TvAppChannel extends Channel {

    @JsonProperty("ip")
    private String ip;
    @JsonProperty("lang")
    private String lang;
    @JsonProperty("deviceId")
    private String deviceId;

    /**
     * Creates a new instance of TvAppChannel.Builder.
     *
     * @return The new instance of TvAppChannel.Builder.
     */
    public static Builder newBuilder() {
        return new Builder();
    }

    /**
     * Gets the IP address of the TV app channel.
     *
     * @return The IP address of the TV app channel.
     */
    public String getIp() {
        return this.ip;
    }

    /**
     * Sets the IP address of the TV app channel.
     *
     * @param value The IP address to set.
     */
    public void setIp(String value) {
        this.ip = value;
    }

    /**
     * Gets the language of the TV app channel.
     *
     * @return The language of the TV app channel.
     */
    public String getLang() {
        return this.lang;
    }

    /**
     * Sets the language of the TV app channel.
     *
     * @param value The language to set.
     */
    public void setLang(String value) {
        this.lang = value;
    }

    /**
     * Gets the device ID of the TV app channel.
     *
     * @return The device ID of the TV app channel.
     */
    public String getDeviceId() {
        return this.deviceId;
    }

    /**
     * Sets the device ID of the TV app channel.
     *
     * @param value The device ID to set.
     */
    public void setDeviceId(String value) {
        this.deviceId = value;
    }

    /**
     * Builder class for TvAppChannel.
     */
    public static class Builder {

        private final TvAppChannel instance = new TvAppChannel();

        private Builder() {
        }

        /**
         * Builds the TvAppChannel instance.
         *
         * @return The built TvAppChannel instance.
         */
        public TvAppChannel build() {
            return this.instance;
        }

        /**
         * Sets the IP address of the TvAppChannel.
         *
         * @param value The IP address to set.
         * @return The Builder instance.
         */
        public Builder setIp(String value) {
            this.instance.setIp(value);
            return this;
        }

        /**
         * Sets the language of the TvAppChannel.
         *
         * @param value The language to set.
         * @return The Builder instance.
         */
        public Builder setLang(String value) {
            this.instance.setLang(value);
            return this;
        }

        /**
         * Sets the device ID of the TvAppChannel.
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
