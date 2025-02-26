package com.sportradar.mts.sdk.ws.entities.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sportradar.mts.sdk.ws.entities.balancechangesource.BalanceChangeSource;
import com.sportradar.mts.sdk.ws.entities.common.Amount;
import com.sportradar.mts.sdk.ws.entities.common.BalanceChangeStatus;
import com.sportradar.mts.sdk.ws.entities.common.EndCustomer;

/**
 * Represents a request to inform about a balance change.
 */
public class BalanceChangeInformRequest extends ContentRequest {

    @JsonProperty("walletId")
    private String walletId;
    @JsonProperty("balanceChangeId")
    private String balanceChangeId;
    @JsonProperty("amount")
    private Amount amount;
    @JsonProperty("endCustomer")
    private EndCustomer endCustomer;
    @JsonProperty("executedAtUtc")
    private long executedAtUtc;
    @JsonProperty("source")
    private BalanceChangeSource source;
    @JsonProperty("status")
    private BalanceChangeStatus status;

    /**
     * Creates a new instance of the BalanceChangeInformRequest.Builder class.
     *
     * @return A new instance of the BalanceChangeInformRequest.Builder class.
     */
    public static Builder newBuilder() {
        return new Builder();
    }

    /**
     * Gets the wallet ID.
     *
     * @return The wallet ID.
     */
    public String getWalletId() {
        return this.walletId;
    }

    /**
     * Sets the wallet ID.
     *
     * @param value The wallet ID.
     */
    public void setWalletId(String value) {
        this.walletId = value;
    }

    /**
     * Gets the balance change ID.
     *
     * @return The balance change ID.
     */
    public String getBalanceChangeId() {
        return this.balanceChangeId;
    }

    /**
     * Sets the balance change ID.
     *
     * @param value The balance change ID.
     */
    public void setBalanceChangeId(String value) {
        this.balanceChangeId = value;
    }

    /**
     * Gets the amount.
     *
     * @return The amount.
     */
    public Amount getAmount() {
        return this.amount;
    }

    /**
     * Sets the amount.
     *
     * @param value The amount.
     */
    public void setAmount(Amount value) {
        this.amount = value;
    }

    /**
     * Gets the end customer.
     *
     * @return The end customer.
     */
    public EndCustomer getEndCustomer() {
        return this.endCustomer;
    }

    /**
     * Sets the end customer.
     *
     * @param value The end customer.
     */
    public void setEndCustomer(EndCustomer value) {
        this.endCustomer = value;
    }

    /**
     * Gets the executed at UTC millis timestamp.
     *
     * @return The executed at UTC millis timestamp.
     */
    public long getExecutedAtUtc() {
        return this.executedAtUtc;
    }

    /**
     * Sets the executed at UTC millis timestamp.
     *
     * @param value The executed at UTC millis timestamp.
     */
    public void setExecutedAtUtc(long value) {
        this.executedAtUtc = value;
    }

    /**
     * Gets the balance change source.
     *
     * @return The balance change source.
     */
    public BalanceChangeSource getSource() {
        return this.source;
    }

    /**
     * Sets the balance change source.
     *
     * @param value The balance change source.
     */
    public void setSource(BalanceChangeSource value) {
        this.source = value;
    }

    /**
     * Gets the balance change status.
     *
     * @return The balance change status.
     */
    public BalanceChangeStatus getStatus() {
        return this.status;
    }

    /**
     * Sets the balance change status.
     *
     * @param value The balance change status.
     */
    public void setStatus(BalanceChangeStatus value) {
        this.status = value;
    }

    /**
     * Represents a builder for the BalanceChangeInformRequest class.
     */
    public static class Builder {

        private final BalanceChangeInformRequest instance = new BalanceChangeInformRequest();

        private Builder() {
        }

        /**
         * Builds the BalanceChangeInformRequest instance.
         *
         * @return The built BalanceChangeInformRequest instance.
         */
        public BalanceChangeInformRequest build() {
            return this.instance;
        }

        /**
         * Sets the wallet ID.
         *
         * @param value The wallet ID.
         * @return The builder instance.
         */
        public Builder setWalletId(String value) {
            this.instance.setWalletId(value);
            return this;
        }

        /**
         * Sets the balance change ID.
         *
         * @param value The balance change ID.
         * @return The builder instance.
         */
        public Builder setBalanceChangeId(String value) {
            this.instance.setBalanceChangeId(value);
            return this;
        }

        /**
         * Sets the amount.
         *
         * @param value The amount.
         * @return The builder instance.
         */
        public Builder setAmount(Amount value) {
            this.instance.setAmount(value);
            return this;
        }

        /**
         * Sets the end customer.
         *
         * @param value The end customer.
         * @return The builder instance.
         */
        public Builder setEndCustomer(EndCustomer value) {
            this.instance.setEndCustomer(value);
            return this;
        }

        /**
         * Sets the executed at UTC millis timestamp.
         *
         * @param value The executed at UTC millis timestamp.
         * @return The builder instance.
         */
        public Builder setExecutedAtUtc(long value) {
            this.instance.setExecutedAtUtc(value);
            return this;
        }

        /**
         * Sets the balance change source.
         *
         * @param value The balance change source.
         * @return The builder instance.
         */
        public Builder setSource(BalanceChangeSource value) {
            this.instance.setSource(value);
            return this;
        }

        /**
         * Sets the balance change status.
         *
         * @param value The balance change status.
         * @return The builder instance.
         */
        public Builder setStatus(BalanceChangeStatus value) {
            this.instance.setStatus(value);
            return this;
        }
    }
}
