package com.sportradar.mts.sdk.ws.entities.balancechangesource;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a withdrawal balance change source.
 */
public class WithdrawalBalanceChangeSource extends BalanceChangeSource {

    @JsonProperty("id")
    private String id;

    /**
     * Creates a new instance of WithdrawalBalanceChangeSource.Builder.
     *
     * @return A new instance of WithdrawalBalanceChangeSource.Builder.
     */
    public static Builder newBuilder() {
        return new Builder();
    }

    /**
     * Gets the ID of the withdrawal balance change source.
     *
     * @return The ID of the withdrawal balance change source.
     */
    public String getId() {
        return this.id;
    }

    /**
     * Sets the ID of the withdrawal balance change source.
     *
     * @param value The ID of the withdrawal balance change source.
     */
    public void setId(String value) {
        this.id = value;
    }

    /**
     * Builder class for creating instances of WithdrawalBalanceChangeSource.
     */
    public static class Builder {

        private final WithdrawalBalanceChangeSource instance = new WithdrawalBalanceChangeSource();

        private Builder() {
        }

        /**
         * Builds and returns the WithdrawalBalanceChangeSource instance.
         *
         * @return The built WithdrawalBalanceChangeSource instance.
         */
        public WithdrawalBalanceChangeSource build() {
            return this.instance;
        }

        /**
         * Sets the ID of the withdrawal balance change source.
         *
         * @param value The ID of the withdrawal balance change source.
         * @return The Builder instance.
         */
        public Builder setId(String value) {
            this.instance.setId(value);
            return this;
        }
    }
}
