package com.sportradar.mts.sdk.ws.entities.channel;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a Call Centre channel.
 */
public class CallCentreChannel extends Channel {

    @JsonProperty("lang")
    private String lang;

    /**
     * Creates a new instance of the CallCentreChannel.Builder class.
     *
     * @return A new instance of the CallCentreChannel.Builder class.
     */
    public static Builder newBuilder() {
        return new Builder();
    }

    /**
     * Gets the language of the Call Centre channel.
     *
     * @return The language of the Call Centre channel.
     */
    public String getLang() {
        return this.lang;
    }

    /**
     * Sets the language of the Call Centre channel.
     *
     * @param value The language to set for the Call Centre channel.
     */
    public void setLang(String value) {
        this.lang = value;
    }

    /**
     * Builder class for creating instances of the CallCentreChannel class.
     */
    public static class Builder {

        private final CallCentreChannel instance = new CallCentreChannel();

        private Builder() {
        }

        /**
         * Builds and returns the CallCentreChannel instance.
         *
         * @return The built CallCentreChannel instance.
         */
        public CallCentreChannel build() {
            return this.instance;
        }

        /**
         * Sets the language of the Call Centre channel.
         *
         * @param value The language to set for the Call Centre channel.
         * @return The Builder instance.
         */
        public Builder setLang(String value) {
            this.instance.setLang(value);
            return this;
        }
    }
}