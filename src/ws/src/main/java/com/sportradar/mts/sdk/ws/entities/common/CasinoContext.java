package com.sportradar.mts.sdk.ws.entities.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sportradar.mts.sdk.ws.entities.channel.Channel;

/**
 * Represents a casino context, including wallet ID, channel, and end customer information.
 */
public class CasinoContext {

    @JsonProperty("walletId")
    private String walletId;
    @JsonProperty("channel")
    private Channel channel;
    @JsonProperty("endCustomer")
    private EndCustomer endCustomer;

    /**
     * Creates a new instance of the CasinoContext.Builder class.
     *
     * @return A new instance of the CasinoContext.Builder class.
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
     * Gets the channel.
     *
     * @return The channel.
     */
    public Channel getChannel() {
        return this.channel;
    }

    /**
     * Sets the channel.
     *
     * @param value The channel to set.
     */
    public void setChannel(Channel value) {
        this.channel = value;
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
     * Builder class for creating instances of the CasinoContext class.
     */
    public static class Builder {

        private final CasinoContext instance = new CasinoContext();

        private Builder() {
        }

        /**
         * Builds the CasinoContext instance.
         *
         * @return The built CasinoContext instance.
         */
        public CasinoContext build() {
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
         * Sets the channel.
         *
         * @param value The channel to set.
         * @return The Builder instance.
         */
        public Builder setChannel(Channel value) {
            this.instance.setChannel(value);
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
    }
}