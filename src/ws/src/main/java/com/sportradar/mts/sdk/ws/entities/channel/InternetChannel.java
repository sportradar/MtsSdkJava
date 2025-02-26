package com.sportradar.mts.sdk.ws.entities.channel;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents an internet channel.
 */
public class InternetChannel extends Channel {

    @JsonProperty("ip")
    private String ip;
    @JsonProperty("lang")
    private String lang;
    @JsonProperty("deviceId")
    private String deviceId;

    /**
     * Creates a new instance of InternetChannel.Builder.
     *
     * @return The InternetChannel.Builder instance.
     */
    public static Builder newBuilder() {
        return new Builder();
    }

    /**
     * Gets the IP address of the internet channel.
     *
     * @return The IP address.
     */
    public String getIp() {
        return this.ip;
    }

    /**
     * Sets the IP address of the internet channel.
     *
     * @param value The IP address to set.
     */
    public void setIp(String value) {
        this.ip = value;
    }

    /**
     * Gets the language of the internet channel.
     *
     * @return The language.
     */
    public String getLang() {
        return this.lang;
    }

    /**
     * Sets the language of the internet channel.
     *
     * @param value The language to set.
     */
    public void setLang(String value) {
        this.lang = value;
    }

    /**
     * Gets the device ID of the internet channel.
     *
     * @return The device ID.
     */
    public String getDeviceId() {
        return this.deviceId;
    }

    /**
     * Sets the device ID of the internet channel.
     *
     * @param value The device ID to set.
     */
    public void setDeviceId(String value) {
        this.deviceId = value;
    }

    /**
     * Builder class for creating instances of InternetChannel.
     */
    public static class Builder {

        private final InternetChannel instance = new InternetChannel();

        private Builder() {
        }

        /**
         * Builds the InternetChannel instance.
         *
         * @return The built InternetChannel instance.
         */
        public InternetChannel build() {
            return this.instance;
        }

        /**
         * Sets the IP address of the internet channel.
         *
         * @param value The IP address to set.
         * @return The Builder instance.
         */
        public Builder setIp(String value) {
            this.instance.setIp(value);
            return this;
        }

        /**
         * Sets the language of the internet channel.
         *
         * @param value The language to set.
         * @return The Builder instance.
         */
        public Builder setLang(String value) {
            this.instance.setLang(value);
            return this;
        }

        /**
         * Sets the device ID of the internet channel.
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
