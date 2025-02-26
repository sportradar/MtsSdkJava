package com.sportradar.mts.sdk.ws.entities.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sportradar.mts.sdk.ws.entities.odds.Odds;
import com.sportradar.mts.sdk.ws.entities.selection.Selection;

/**
 * Represents the details of a selection.
 */
public class SelectionDetail {

    @JsonProperty("code")
    private int code;
    @JsonProperty("selection")
    private Selection selection;
    @JsonProperty("message")
    private String message;
    @JsonProperty("autoAcceptedOdds")
    private Odds autoAcceptedOdds;

    /**
     * Creates a new instance of the SelectionDetail class.
     *
     * @return A new instance of the SelectionDetail class.
     */
    public static Builder newBuilder() {
        return new Builder();
    }

    /**
     * Gets the code of the selection.
     *
     * @return The code of the selection.
     */
    public int getCode() {
        return this.code;
    }

    /**
     * Sets the code of the selection.
     *
     * @param value The code of the selection.
     */
    public void setCode(int value) {
        this.code = value;
    }

    /**
     * Gets the selection.
     *
     * @return The selection.
     */
    public Selection getSelection() {
        return this.selection;
    }

    /**
     * Sets the selection.
     *
     * @param value The selection.
     */
    public void setSelection(Selection value) {
        this.selection = value;
    }

    /**
     * Gets the message associated with the selection.
     *
     * @return The message associated with the selection.
     */
    public String getMessage() {
        return this.message;
    }

    /**
     * Sets the message associated with the selection.
     *
     * @param value The message associated with the selection.
     */
    public void setMessage(String value) {
        this.message = value;
    }

    /**
     * Gets the auto-accepted odds of the selection.
     *
     * @return The auto-accepted odds of the selection.
     */
    public Odds getAutoAcceptedOdds() {
        return this.autoAcceptedOdds;
    }

    /**
     * Sets the auto-accepted odds of the selection.
     *
     * @param value The auto-accepted odds of the selection.
     */
    public void setAutoAcceptedOdds(Odds value) {
        this.autoAcceptedOdds = value;
    }

    /**
     * Represents a builder for the SelectionDetail class.
     */
    public static class Builder {

        private final SelectionDetail instance = new SelectionDetail();

        private Builder() {
        }

        /**
         * Builds the SelectionDetail instance.
         *
         * @return The built SelectionDetail instance.
         */
        public SelectionDetail build() {
            return this.instance;
        }

        /**
         * Sets the code of the selection.
         *
         * @param value The code of the selection.
         * @return The builder instance.
         */
        public Builder setCode(int value) {
            this.instance.setCode(value);
            return this;
        }

        /**
         * Sets the selection.
         *
         * @param value The selection.
         * @return The builder instance.
         */
        public Builder setSelection(Selection value) {
            this.instance.setSelection(value);
            return this;
        }

        /**
         * Sets the message associated with the selection.
         *
         * @param value The message associated with the selection.
         * @return The builder instance.
         */
        public Builder setMessage(String value) {
            this.instance.setMessage(value);
            return this;
        }

        /**
         * Sets the auto-accepted odds of the selection.
         *
         * @param value The auto-accepted odds of the selection.
         * @return The builder instance.
         */
        public Builder setAutoAcceptedOdds(Odds value) {
            this.instance.setAutoAcceptedOdds(value);
            return this;
        }
    }
}
