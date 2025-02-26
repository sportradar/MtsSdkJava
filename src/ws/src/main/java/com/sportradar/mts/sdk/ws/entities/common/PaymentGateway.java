package com.sportradar.mts.sdk.ws.entities.common;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a payment gateway entity.
 */
public class PaymentGateway {

    @JsonProperty("method")
    private PaymentMethod method;
    @JsonProperty("provider")
    private String provider;
    @JsonProperty("executedAtUtc")
    private long executedAtUtc;
    @JsonProperty("initiatedAtUtc")
    private Long initiatedAtUtc;
    @JsonProperty("referenceId")
    private String referenceId;

    /**
     * Creates a new instance of the PaymentGateway.Builder class.
     *
     * @return A new instance of the PaymentGateway.Builder class.
     */
    public static Builder newBuilder() {
        return new Builder();
    }

    /**
     * Gets the payment method.
     *
     * @return The payment method.
     */
    public PaymentMethod getMethod() {
        return this.method;
    }

    /**
     * Sets the payment method.
     *
     * @param value The payment method to set.
     */
    public void setMethod(PaymentMethod value) {
        this.method = value;
    }

    /**
     * Gets the payment provider.
     *
     * @return The payment provider.
     */
    public String getProvider() {
        return this.provider;
    }

    /**
     * Sets the payment provider.
     *
     * @param value The payment provider to set.
     */
    public void setProvider(String value) {
        this.provider = value;
    }

    /**
     * Gets the UTC millis timestamp when the payment was executed.
     *
     * @return The UTC millis timestamp when the payment was executed.
     */
    public long getExecutedAtUtc() {
        return this.executedAtUtc;
    }

    /**
     * Sets the UTC millis timestamp when the payment was executed.
     *
     * @param value The UTC millis timestamp when the payment was executed to set.
     */
    public void setExecutedAtUtc(long value) {
        this.executedAtUtc = value;
    }

    /**
     * Gets the UTC millis timestamp when the payment was initiated.
     *
     * @return The UTC millis timestamp when the payment was initiated.
     */
    public Long getInitiatedAtUtc() {
        return this.initiatedAtUtc;
    }

    /**
     * Sets the UTC millis timestamp when the payment was initiated.
     *
     * @param value The UTC millis timestamp when the payment was initiated to set.
     */
    public void setInitiatedAtUtc(Long value) {
        this.initiatedAtUtc = value;
    }

    /**
     * Gets the reference ID of the payment.
     *
     * @return The reference ID of the payment.
     */
    public String getReferenceId() {
        return this.referenceId;
    }

    /**
     * Sets the reference ID of the payment.
     *
     * @param value The reference ID of the payment to set.
     */
    public void setReferenceId(String value) {
        this.referenceId = value;
    }

    /**
     * Represents a builder for the PaymentGateway class.
     */
    public static class Builder {

        private final PaymentGateway instance = new PaymentGateway();

        private Builder() {
        }

        /**
         * Builds the PaymentGateway instance.
         *
         * @return The built PaymentGateway instance.
         */
        public PaymentGateway build() {
            return this.instance;
        }

        /**
         * Sets the payment method.
         *
         * @param value The payment method to set.
         * @return The builder instance.
         */
        public Builder setMethod(PaymentMethod value) {
            this.instance.setMethod(value);
            return this;
        }

        /**
         * Sets the payment provider.
         *
         * @param value The payment provider to set.
         * @return The builder instance.
         */
        public Builder setProvider(String value) {
            this.instance.setProvider(value);
            return this;
        }

        /**
         * Sets the UTC millis timestamp when the payment was executed.
         *
         * @param value The UTC millis timestamp when the payment was executed to set.
         * @return The builder instance.
         */
        public Builder setExecutedAtUtc(long value) {
            this.instance.setExecutedAtUtc(value);
            return this;
        }

        /**
         * Sets the UTC millis timestamp when the payment was initiated.
         *
         * @param value The UTC millis timestamp when the payment was initiated to set.
         * @return The builder instance.
         */
        public Builder setInitiatedAtUtc(Long value) {
            this.instance.setInitiatedAtUtc(value);
            return this;
        }

        /**
         * Sets the reference ID of the payment.
         *
         * @param value The reference ID of the payment to set.
         * @return The builder instance.
         */
        public Builder setReferenceId(String value) {
            this.instance.setReferenceId(value);
            return this;
        }
    }
}
