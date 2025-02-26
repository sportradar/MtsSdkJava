package com.sportradar.mts.sdk.ws.entities.response;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents an error response from the server.
 * Inherits from the ContentResponse class.
 */
public class ErrorResponse extends ContentResponse {

    @JsonProperty("message")
    private String errorMessage;
    @JsonProperty("code")
    private int errorCode;

    /**
     * Creates a new instance of ErrorResponse.Builder.
     *
     * @return A new instance of ErrorResponse.Builder.
     */
    public static Builder newBuilder() {
        return new Builder();
    }

    /**
     * Gets the error message.
     *
     * @return The error message.
     */
    public String getErrorMessage() {
        return this.errorMessage;
    }

    /**
     * Sets the error message.
     *
     * @param value The error message to set.
     */
    public void setErrorMessage(String value) {
        this.errorMessage = value;
    }

    /**
     * Gets the error code.
     *
     * @return The error code.
     */
    public int getErrorCode() {
        return this.errorCode;
    }

    /**
     * Sets the error code.
     *
     * @param value The error code to set.
     */
    public void setErrorCode(int value) {
        this.errorCode = value;
    }

    /**
     * Builder class for ErrorResponse.
     */
    public static class Builder {

        private final ErrorResponse instance = new ErrorResponse();

        private Builder() {
        }

        /**
         * Builds the ErrorResponse instance.
         *
         * @return The built ErrorResponse instance.
         */
        public ErrorResponse build() {
            return this.instance;
        }

        /**
         * Sets the error message.
         *
         * @param value The error message to set.
         * @return The Builder instance.
         */
        public Builder setErrorMessage(String value) {
            this.instance.setErrorMessage(value);
            return this;
        }

        /**
         * Sets the error code.
         *
         * @param value The error code to set.
         * @return The Builder instance.
         */
        public Builder setErrorCode(int value) {
            this.instance.setErrorCode(value);
            return this;
        }
    }
}
