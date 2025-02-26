package com.sportradar.mts.sdk.ws.entities.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sportradar.mts.sdk.ws.entities.common.Bet;

/**
 * Represents a response for max stake.
 */
public class MaxStakeResponse extends ContentResponse {

    @JsonProperty("code")
    private int code;
    @JsonProperty("bets")
    private Bet[] bets;
    @JsonProperty("message")
    private String message;

    /**
     * Gets the code of the response.
     *
     * @return The code of the response.
     */
    public int getCode() {
        return this.code;
    }

    /**
     * Sets the code of the response.
     *
     * @param value The code of the response.
     */
    public void setCode(int value) {
        this.code = value;
    }

    /**
     * Gets bets.
     *
     * @return bets.
     */
    public Bet[] getBets() {
        return this.bets;
    }

    /**
     * Sets bets.
     *
     * @param value bets.
     */
    public void setBets(Bet[] value) {
        this.bets = value;
    }

    /**
     * Gets the message.
     *
     * @return The message.
     */
    public String getMessage() {
        return this.message;
    }

    /**
     * Sets the message.
     *
     * @param value The message.
     */
    public void setMessage(String value) {
        this.message = value;
    }

    /**
     * Creates a new instance of the MaxStakeResponse.Builder class.
     *
     * @return A new instance of the MaxStakeResponse.Builder class.
     */
    public static Builder newBuilder() {
        return new Builder();
    }

    /**
     * Represents a builder for creating instances of the MaxStakeResponse class.
     */
    public static class Builder {

        private final MaxStakeResponse instance = new MaxStakeResponse();

        private Builder() {
        }

        /**
         * Builds and returns the MaxStakeResponse instance.
         *
         * @return The MaxStakeResponse instance.
         */
        public MaxStakeResponse build() {
            return this.instance;
        }

        /**
         * Sets the code of the response.
         *
         * @param value The code of the response.
         * @return The builder instance.
         */
        public Builder setCode(int value) {
            this.instance.setCode(value);
            return this;
        }

        /**
         * Sets bets.
         *
         * @param value Bets.
         * @return The builder instance.
         */
        public Builder setBets(Bet... value) {
            this.instance.setBets(value);
            return this;
        }

        /**
         * Sets the message.
         *
         * @param value The message.
         * @return The builder instance.
         */
        public Builder setMessage(String value) {
            this.instance.setMessage(value);
            return this;
        }
    }
}