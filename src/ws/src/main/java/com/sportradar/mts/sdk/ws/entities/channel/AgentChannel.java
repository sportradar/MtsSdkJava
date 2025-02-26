package com.sportradar.mts.sdk.ws.entities.channel;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents an agent channel.
 */
public class AgentChannel extends Channel {

    @JsonProperty("lang")
    private String lang;

    /**
     * Creates a new instance of AgentChannel.Builder.
     *
     * @return A new instance of AgentChannel.Builder.
     */
    public static Builder newBuilder() {
        return new Builder();
    }

    /**
     * Gets the language of the agent channel.
     *
     * @return The language of the agent channel.
     */
    public String getLang() {
        return this.lang;
    }

    /**
     * Sets the language of the agent channel.
     *
     * @param value The language to set.
     */
    public void setLang(String value) {
        this.lang = value;
    }

    /**
     * A builder for the agent channel.
     */
    public static class Builder {

        private final AgentChannel instance = new AgentChannel();

        private Builder() {
        }

        /**
         * Builds the agent channel.
         *
         * @return The built agent channel.
         */
        public AgentChannel build() {
            return this.instance;
        }

        /**
         * Sets the language of the agent channel.
         *
         * @param value The language to set.
         * @return The builder instance.
         */
        public Builder setLang(String value) {
            this.instance.setLang(value);
            return this;
        }
    }
}
