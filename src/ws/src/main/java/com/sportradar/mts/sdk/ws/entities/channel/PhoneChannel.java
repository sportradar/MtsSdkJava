package com.sportradar.mts.sdk.ws.entities.channel;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a phone channel in the system.
 */
public class PhoneChannel extends Channel {

    @JsonProperty("lang")
    private String lang;

    /**
     * Creates a new instance of the PhoneChannel.Builder class.
     *
     * @return A new instance of the PhoneChannel.Builder class.
     */
    public static Builder newBuilder() {
        return new Builder();
    }

    /**
     * Gets the language of the phone channel.
     *
     * @return The language of the phone channel.
     */
    public String getLang() {
        return this.lang;
    }

    /**
     * Sets the language of the phone channel.
     *
     * @param value The language to set for the phone channel.
     */
    public void setLang(String value) {
        this.lang = value;
    }

    /**
     * Builder class for creating instances of the PhoneChannel class.
     */
    public static class Builder {

        private final PhoneChannel instance = new PhoneChannel();

        private Builder() {
        }

        /**
         * Builds the PhoneChannel instance.
         *
         * @return The built PhoneChannel instance.
         */
        public PhoneChannel build() {
            return this.instance;
        }

        /**
         * Sets the language of the phone channel.
         *
         * @param value The language to set for the phone channel.
         * @return The Builder instance.
         */
        public Builder setLang(String value) {
            this.instance.setLang(value);
            return this;
        }
    }
}