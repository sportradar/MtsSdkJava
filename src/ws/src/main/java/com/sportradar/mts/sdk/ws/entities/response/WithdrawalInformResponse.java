package com.sportradar.mts.sdk.ws.entities.response;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a response object for withdrawal information.
 */
public class WithdrawalInformResponse extends ContentResponse {

    @JsonProperty("code")
    private int code;
    @JsonProperty("message")
    private String message;

    /**
     * Creates a new instance of WithdrawalInformResponse.Builder.
     *
     * @return A new instance of WithdrawalInformResponse.Builder.
     */
    public static Builder newBuilder() {
        return new Builder();
    }

    /**
     * Gets the code associated with the withdrawal inform response.
     *
     * @return The code associated with the withdrawal inform response.
     */
    public int getCode() {
        return this.code;
    }

    /**
     * Sets the code associated with the withdrawal inform response.
     *
     * @param value The code to set.
     */
    public void setCode(int value) {
        this.code = value;
    }

    /**
     * Gets the message associated with the withdrawal inform response.
     *
     * @return The message associated with the withdrawal inform response.
     */
    public String getMessage() {
        return this.message;
    }

    /**
     * Sets the message associated with the withdrawal inform response.
     *
     * @param value The message to set.
     */
    public void setMessage(String value) {
        this.message = value;
    }

    /**
     * Builder class for WithdrawalInformResponse.
     */
    public static class Builder {

        private final WithdrawalInformResponse instance = new WithdrawalInformResponse();

        private Builder() {
        }

        /**
         * Builds the WithdrawalInformResponse object.
         *
         * @return The built WithdrawalInformResponse object.
         */
        public WithdrawalInformResponse build() {
            return this.instance;
        }

        /**
         * Sets the code for the WithdrawalInformResponse object being built.
         *
         * @param value The code to set.
         * @return The Builder instance.
         */
        public Builder setCode(int value) {
            this.instance.setCode(value);
            return this;
        }

        /**
         * Sets the message for the WithdrawalInformResponse object being built.
         *
         * @param value The message to set.
         * @return The Builder instance.
         */
        public Builder setMessage(String value) {
            this.instance.setMessage(value);
            return this;
        }
    }
}
