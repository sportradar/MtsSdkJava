package com.sportradar.mts.sdk.ws.entities.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sportradar.mts.sdk.ws.entities.common.Amount;
import com.sportradar.mts.sdk.ws.entities.common.BalanceMoveStatus;
import com.sportradar.mts.sdk.ws.entities.common.EndCustomer;
import com.sportradar.mts.sdk.ws.entities.common.PaymentGateway;

/**
 * Represents a request to inform about a deposit transaction.
 */
public class DepositInformRequest extends ContentRequest {

    @JsonProperty("walletId")
    private String walletId;
    @JsonProperty("depositId")
    private String depositId;
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
     * Returns a new instance of the Builder class to build a DepositInformRequest object.
     *
     * @return A new instance of the Builder class.
     */
    public static Builder newBuilder() {
        return new Builder();
    }

    /**
     * Gets the wallet ID associated with the deposit.
     *
     * @return The wallet ID.
     */
    public String getWalletId() {
        return this.walletId;
    }

    /**
     * Sets the wallet ID associated with the deposit.
     *
     * @param value The wallet ID.
     */
    public void setWalletId(String value) {
        this.walletId = value;
    }

    /**
     * Gets the deposit ID.
     *
     * @return The deposit ID.
     */
    public String getDepositId() {
        return this.depositId;
    }

    /**
     * Sets the deposit ID.
     *
     * @param value The deposit ID.
     */
    public void setDepositId(String value) {
        this.depositId = value;
    }

    /**
     * Gets the amount of the deposit.
     *
     * @return The deposit amount.
     */
    public Amount getAmount() {
        return this.amount;
    }

    /**
     * Sets the amount of the deposit.
     *
     * @param value The deposit amount.
     */
    public void setAmount(Amount value) {
        this.amount = value;
    }

    /**
     * Gets the end customer associated with the deposit.
     *
     * @return The end customer.
     */
    public EndCustomer getEndCustomer() {
        return this.endCustomer;
    }

    /**
     * Sets the end customer associated with the deposit.
     *
     * @param value The end customer.
     */
    public void setEndCustomer(EndCustomer value) {
        this.endCustomer = value;
    }

    /**
     * Gets the UTC timestamp when the deposit was executed.
     *
     * @return The UTC timestamp.
     */
    public long getExecutedAtUtc() {
        return this.executedAtUtc;
    }

    /**
     * Sets the UTC timestamp when the deposit was executed.
     *
     * @param value The UTC timestamp.
     */
    public void setExecutedAtUtc(long value) {
        this.executedAtUtc = value;
    }

    /**
     * Gets the UTC timestamp when the deposit was initiated.
     *
     * @return The UTC timestamp.
     */
    public Long getInitiatedAtUtc() {
        return this.initiatedAtUtc;
    }

    /**
     * Sets the UTC timestamp when the deposit was initiated.
     *
     * @param value The UTC timestamp.
     */
    public void setInitiatedAtUtc(Long value) {
        this.initiatedAtUtc = value;
    }

    /**
     * Gets the payment gateway used for the deposit.
     *
     * @return The payment gateway.
     */
    public PaymentGateway getGateway() {
        return this.gateway;
    }

    /**
     * Sets the payment gateway used for the deposit.
     *
     * @param value The payment gateway.
     */
    public void setGateway(PaymentGateway value) {
        this.gateway = value;
    }

    /**
     * Gets the status of the balance move associated with the deposit.
     *
     * @return The balance move status.
     */
    public BalanceMoveStatus getStatus() {
        return this.status;
    }

    /**
     * Sets the status of the balance move associated with the deposit.
     *
     * @param value The balance move status.
     */
    public void setStatus(BalanceMoveStatus value) {
        this.status = value;
    }

    /**
     * Builder class for constructing a DepositInformRequest object.
     */
    public static class Builder {

        private final DepositInformRequest instance = new DepositInformRequest();

        private Builder() {
        }

        /**
         * Builds and returns the DepositInformRequest object.
         *
         * @return The DepositInformRequest object.
         */
        public DepositInformRequest build() {
            return this.instance;
        }

        /**
         * Sets the wallet ID associated with the deposit.
         *
         * @param value The wallet ID.
         * @return The Builder instance.
         */
        public Builder setWalletId(String value) {
            this.instance.setWalletId(value);
            return this;
        }

        /**
         * Sets the deposit ID.
         *
         * @param value The deposit ID.
         * @return The Builder instance.
         */
        public Builder setDepositId(String value) {
            this.instance.setDepositId(value);
            return this;
        }

        /**
         * Sets the amount of the deposit.
         *
         * @param value The deposit amount.
         * @return The Builder instance.
         */
        public Builder setAmount(Amount value) {
            this.instance.setAmount(value);
            return this;
        }

        /**
         * Sets the end customer associated with the deposit.
         *
         * @param value The end customer.
         * @return The Builder instance.
         */
        public Builder setEndCustomer(EndCustomer value) {
            this.instance.setEndCustomer(value);
            return this;
        }

        /**
         * Sets the UTC timestamp when the deposit was executed.
         *
         * @param value The UTC timestamp.
         * @return The Builder instance.
         */
        public Builder setExecutedAtUtc(long value) {
            this.instance.setExecutedAtUtc(value);
            return this;
        }

        /**
         * Sets the UTC timestamp when the deposit was initiated.
         *
         * @param value The UTC timestamp.
         * @return The Builder instance.
         */
        public Builder setInitiatedAtUtc(Long value) {
            this.instance.setInitiatedAtUtc(value);
            return this;
        }

        /**
         * Sets the payment gateway used for the deposit.
         *
         * @param value The payment gateway.
         * @return The Builder instance.
         */
        public Builder setGateway(PaymentGateway value) {
            this.instance.setGateway(value);
            return this;
        }

        /**
         * Sets the status of the balance move associated with the deposit.
         *
         * @param value The balance move status.
         * @return The Builder instance.
         */
        public Builder setStatus(BalanceMoveStatus value) {
            this.instance.setStatus(value);
            return this;
        }
    }
}