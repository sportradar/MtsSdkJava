package com.sportradar.mts.sdk.ws.entities.common;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * CashoutInformValidation object.
 */
public class CashoutInformValidation {

    @JsonProperty("code")
    private int code;
    @JsonProperty("message")
    private String message;

    /**
     * Creates a new instance of the {@link Builder}.
     *
     * @return A new instance of the CashoutInformValidation.Builder class.
     */
    public static Builder newBuilder() {
        return new Builder();
    }

    /**
     * Gets the code of the {@link CashoutInformValidation}.
     *
     * @return The code of the cashout inform validation.
     */
    public int getCode() {
        return this.code;
    }

    /**
     * Sets the code of the {@link CashoutInformValidation}.
     *
     * @param value The code of the cashout inform validation.
     */
    public void setCode(int value) {
        this.code = value;
    }

    /**
     * Gets the message of the {@link CashoutInformValidation}.
     *
     * @return The message of the cashout inform validation.
     */
    public String getMessage() {
        return this.message;
    }

    /**
     * Sets the message of the {@link CashoutInformValidation}.
     *
     * @param value The message of the cashout inform validation.
     */
    public void setMessage(String value) {
        this.message = value;
    }

    /**
     * Represents a builder for the {@link CashoutInformValidation} class.
     */
    public static class Builder {

        private final CashoutInformValidation instance = new CashoutInformValidation();

        private Builder() {
        }

        /**
         * Builds the {@link CashoutInformValidation} object.
         *
         * @return The built CashoutInformValidation object.
         */
        public CashoutInformValidation build() {
            return this.instance;
        }

        /**
         * Sets the code of the {@link CashoutInformValidation}.
         *
         * @param value The code of the cashout inform validation.
         * @return Instance of {@link Builder}.
         */
        public Builder setCode(int value) {
            this.instance.setCode(value);
            return this;
        }

        /**
         * Sets the message of the {@link CashoutInformValidation}.
         *
         * @param value The message of the cashout inform validation.
         * @return Instance of {@link Builder}.
         */
        public Builder setMessage(String value) {
            this.instance.setMessage(value);
            return this;
        }
    }
}
