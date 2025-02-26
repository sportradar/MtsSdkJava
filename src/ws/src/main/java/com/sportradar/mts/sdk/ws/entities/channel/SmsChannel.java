package com.sportradar.mts.sdk.ws.entities.channel;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents an SMS channel.
 */
public class SmsChannel extends Channel {

    @JsonProperty("lang")
    private String lang;
    @JsonProperty("deviceId")
    private String deviceId;

    /**
     * Creates a new instance of SmsChannel.Builder.
     *
     * @return The SmsChannel.Builder instance.
     */
    public static Builder newBuilder() {
        return new Builder();
    }

    /**
     * Gets the language of the SMS channel.
     *
     * @return The language of the SMS channel.
     */
    public String getLang() {
        return this.lang;
    }

    /**
     * Sets the language of the SMS channel.
     *
     * @param value The language to set.
     */
    public void setLang(String value) {
        this.lang = value;
    }

    /**
     * Gets the device ID of the SMS channel.
     *
     * @return The device ID of the SMS channel.
     */
    public String getDeviceId() {
        return this.deviceId;
    }

    /**
     * Sets the device ID of the SMS channel.
     *
     * @param value The device ID to set.
     */
    public void setDeviceId(String value) {
        this.deviceId = value;
    }

    /**
     * Builder class for SmsChannel.
     */
    public static class Builder {

        private final SmsChannel instance = new SmsChannel();

        private Builder() {
        }

        /**
         * Builds the SmsChannel instance.
         *
         * @return The built SmsChannel instance.
         */
        public SmsChannel build() {
            return this.instance;
        }

        /**
         * Sets the language of the SMS channel.
         *
         * @param value The language to set.
         * @return The SmsChannel.Builder instance.
         */
        public Builder setLang(String value) {
            this.instance.setLang(value);
            return this;
        }

        /**
         * Sets the device ID of the SMS channel.
         *
         * @param value The device ID to set.
         * @return The SmsChannel.Builder instance.
         */
        public Builder setDeviceId(String value) {
            this.instance.setDeviceId(value);
            return this;
        }
    }
}
