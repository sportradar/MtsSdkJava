package com.sportradar.mts.sdk.ws.entities.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sportradar.mts.sdk.ws.entities.common.Amount;
import com.sportradar.mts.sdk.ws.entities.common.BalanceMoveStatus;
import com.sportradar.mts.sdk.ws.entities.common.EndCustomer;
import com.sportradar.mts.sdk.ws.entities.common.PaymentGateway;

/**
 * Represents a request to inform about a withdrawal.
 */
public class WithdrawalInformRequest extends ContentRequest {

    @JsonProperty("walletId")
    private String walletId;
    @JsonProperty("withdrawalId")
    private String withdrawalId;
    @JsonProperty("amount")
    private Amount amount;
    @JsonProperty("endCustomer")
    private EndCustomer endCustomer;
    @JsonProperty("executedAtUtc")
    private long executedAtUtc;
    @JsonProperty("initiatedAtUtc")
    private Long initiatedAtUtc;
    @JsonProperty("gateway")
    private PaymentGateway gateway;
    @JsonProperty("status")
    private BalanceMoveStatus status;

    /**
     * Returns a new instance of the Builder class to build a WithdrawalInformRequest object.
     *
     * @return A new instance of the Builder class.
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
     * @param value The wallet ID to set.
     */
    public void setWalletId(String value) {
        this.walletId = value;
    }

    /**
     * Gets the withdrawal ID.
     *
     * @return The withdrawal ID.
     */
    public String getWithdrawalId() {
        return this.withdrawalId;
    }

    /**
     * Sets the withdrawal ID.
     *
     * @param value The withdrawal ID to set.
     */
    public void setWithdrawalId(String value) {
        this.withdrawalId = value;
    }

    /**
     * Gets the withdrawal amount.
     *
     * @return The withdrawal amount.
     */
    public Amount getAmount() {
        return this.amount;
    }

    /**
     * Sets the withdrawal amount.
     *
     * @param value The withdrawal amount to set.
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
     * @param value The end customer to set.
     */
    public void setEndCustomer(EndCustomer value) {
        this.endCustomer = value;
    }

    /**
     * Gets the executed timestamp in UTC.
     *
     * @return The executed timestamp in UTC.
     */
    public long getExecutedAtUtc() {
        return this.executedAtUtc;
    }

    /**
     * Sets the executed timestamp in UTC.
     *
     * @param value The executed timestamp in UTC to set.
     */
    public void setExecutedAtUtc(long value) {
        this.executedAtUtc = value;
    }

    /**
     * Gets the initiated timestamp in UTC.
     *
     * @return The initiated timestamp in UTC.
     */
    public Long getInitiatedAtUtc() {
        return this.initiatedAtUtc;
    }

    /**
     * Sets the initiated timestamp in UTC.
     *
     * @param value The initiated timestamp in UTC to set.
     */
    public void setInitiatedAtUtc(Long value) {
        this.initiatedAtUtc = value;
    }

    /**
     * Gets the payment gateway.
     *
     * @return The payment gateway.
     */
    public PaymentGateway getGateway() {
        return this.gateway;
    }

    /**
     * Sets the payment gateway.
     *
     * @param value The payment gateway to set.
     */
    public void setGateway(PaymentGateway value) {
        this.gateway = value;
    }

    /**
     * Gets the balance move status.
     *
     * @return The balance move status.
     */
    public BalanceMoveStatus getStatus() {
        return this.status;
    }

    /**
     * Sets the balance move status.
     *
     * @param value The balance move status to set.
     */
    public void setStatus(BalanceMoveStatus value) {
        this.status = value;
    }

    /**
     * Builder class for constructing WithdrawalInformRequest objects.
     */
    public static class Builder {

        private final WithdrawalInformRequest instance = new WithdrawalInformRequest();

        private Builder() {
        }

        /**
         * Builds and returns the WithdrawalInformRequest object.
         *
         * @return The built WithdrawalInformRequest object.
         */
        public WithdrawalInformRequest build() {
            return this.instance;
        }

        /**
         * Sets the wallet ID.
         *
         * @param value The wallet ID to set.
         * @return The Builder instance.
         */
        public Builder setWalletId(String value) {
            this.instance.setWalletId(value);
            return this;
        }

        /**
         * Sets the withdrawal ID.
         *
         * @param value The withdrawal ID to set.
         * @return The Builder instance.
         */
        public Builder setWithdrawalId(String value) {
            this.instance.setWithdrawalId(value);
            return this;
        }

        /**
         * Sets the withdrawal amount.
         *
         * @param value The withdrawal amount to set.
         * @return The Builder instance.
         */
        public Builder setAmount(Amount value) {
            this.instance.setAmount(value);
            return this;
        }

        /**
         * Sets the end customer.
         *
         * @param value The end customer to set.
         * @return The Builder instance.
         */
        public Builder setEndCustomer(EndCustomer value) {
            this.instance.setEndCustomer(value);
            return this;
        }

        /**
         * Sets the executed timestamp in UTC.
         *
         * @param value The executed timestamp in UTC to set.
         * @return The Builder instance.
         */
        public Builder setExecutedAtUtc(long value) {
            this.instance.setExecutedAtUtc(value);
            return this;
        }

        /**
         * Sets the initiated timestamp in UTC.
         *
         * @param value The initiated timestamp in UTC to set.
         * @return The Builder instance.
         */
        public Builder setInitiatedAtUtc(Long value) {
            this.instance.setInitiatedAtUtc(value);
            return this;
        }

        /**
         * Sets the payment gateway.
         *
         * @param value The payment gateway to set.
         * @return The Builder instance.
         */
        public Builder setGateway(PaymentGateway value) {
            this.instance.setGateway(value);
            return this;
        }

        /**
         * Sets the balance move status.
         *
         * @param value The balance move status to set.
         * @return The Builder instance.
         */
        public Builder setStatus(BalanceMoveStatus value) {
            this.instance.setStatus(value);
            return this;
        }
    }
}