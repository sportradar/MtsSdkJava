package com.sportradar.mts.sdk.ws.entities.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sportradar.mts.sdk.ws.entities.suggestion.Suggestion;

/**
 * Represents a bet detail.
 */
public class BetDetail {

    @JsonProperty("code")
    private int code;
    @JsonProperty("suggestion")
    private Suggestion suggestion;
    @JsonProperty("betId")
    private String betId;
    @JsonProperty("selectionDetails")
    private SelectionDetail[] selectionDetails;
    @JsonProperty("message")
    private String message;

    /**
     * Creates a new instance of the BetDetail.Builder class.
     *
     * @return A new instance of the BetDetail.Builder class.
     */
    public static Builder newBuilder() {
        return new Builder();
    }

    /**
     * Gets the code of the bet detail.
     *
     * @return The code of the bet detail.
     */
    public int getCode() {
        return this.code;
    }

    /**
     * Sets the code of the bet detail.
     *
     * @param value The code of the bet detail.
     */
    public void setCode(int value) {
        this.code = value;
    }

    /**
     * Gets the suggestion of the bet detail.
     *
     * @return The suggestion of the bet detail.
     */
    public Suggestion getSuggestion() {
        return this.suggestion;
    }

    /**
     * Sets the suggestion of the bet detail.
     *
     * @param value The suggestion of the bet detail.
     */
    public void setSuggestion(Suggestion value) {
        this.suggestion = value;
    }

    /**
     * Gets the bet ID of the bet detail.
     *
     * @return The bet ID of the bet detail.
     */
    public String getBetId() {
        return this.betId;
    }

    /**
     * Sets the bet ID of the bet detail.
     *
     * @param value The bet ID of the bet detail.
     */
    public void setBetId(String value) {
        this.betId = value;
    }

    /**
     * Gets the selection details of the bet detail.
     *
     * @return The selection details of the bet detail.
     */
    public SelectionDetail[] getSelectionDetails() {
        return this.selectionDetails;
    }

    /**
     * Sets the selection details of the bet detail.
     *
     * @param value The selection details of the bet detail.
     */
    public void setSelectionDetails(SelectionDetail[] value) {
        this.selectionDetails = value;
    }

    /**
     * Gets the message of the bet detail.
     *
     * @return The message of the bet detail.
     */
    public String getMessage() {
        return this.message;
    }

    /**
     * Sets the message of the bet detail.
     *
     * @param value The message of the bet detail.
     */
    public void setMessage(String value) {
        this.message = value;
    }

    /**
     * Builder class for creating instances of the BetDetail class.
     */
    public static class Builder {

        private final BetDetail instance = new BetDetail();

        private Builder() {
        }

        /**
         * Builds and returns the BetDetail instance.
         *
         * @return The BetDetail instance.
         */
        public BetDetail build() {
            return this.instance;
        }

        /**
         * Sets the code of the bet detail.
         *
         * @param value The code of the bet detail.
         * @return The Builder instance.
         */
        public Builder setCode(int value) {
            this.instance.setCode(value);
            return this;
        }

        /**
         * Sets the suggestion of the bet detail.
         *
         * @param value The suggestion of the bet detail.
         * @return The Builder instance.
         */
        public Builder setSuggestion(Suggestion value) {
            this.instance.setSuggestion(value);
            return this;
        }

        /**
         * Sets the bet ID of the bet detail.
         *
         * @param value The bet ID of the bet detail.
         * @return The Builder instance.
         */
        public Builder setBetId(String value) {
            this.instance.setBetId(value);
            return this;
        }

        /**
         * Sets the selection details of the bet detail.
         *
         * @param value The selection details of the bet detail.
         * @return The Builder instance.
         */
        public Builder setSelectionDetails(SelectionDetail... value) {
            this.instance.setSelectionDetails(value);
            return this;
        }

        /**
         * Sets the message of the bet detail.
         *
         * @param value The message of the bet detail.
         * @return The Builder instance.
         */
        public Builder setMessage(String value) {
            this.instance.setMessage(value);
            return this;
        }
    }
}
