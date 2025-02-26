package com.sportradar.mts.sdk.ws.entities.balancechangesource;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a balance change source for a deposit transaction.
 */
public class DepositBalanceChangeSource extends BalanceChangeSource {

    @JsonProperty("id")
    private String id;

    /**
     * A builder class for creating instances of DepositBalanceChangeSource.
     */
    public static Builder newBuilder() {
        return new Builder();
    }

    /**
     * Gets the ID of the deposit balance change source.
     *
     * @return The ID of the deposit balance change source.
     */
    public String getId() {
        return this.id;
    }

    /**
     * Sets the ID of the deposit balance change source.
     *
     * @param value The ID of the deposit balance change source.
     */
    public void setId(String value) {
        this.id = value;
    }

    /**
     * A builder class for creating instances of DepositBalanceChangeSource.
     */
    public static class Builder {

        private final DepositBalanceChangeSource instance = new DepositBalanceChangeSource();

        private Builder() {
        }

        /**
         * Builds the deposit balance change source.
         *
         * @return The built deposit balance change source.
         */
        public DepositBalanceChangeSource build() {
            return this.instance;
        }

        /**
         * Sets the ID of the deposit balance change source.
         *
         * @param value The ID of the deposit balance change source.
         * @return The builder instance.
         */
        public Builder setId(String value) {
            this.instance.setId(value);
            return this;
        }
    }
}
