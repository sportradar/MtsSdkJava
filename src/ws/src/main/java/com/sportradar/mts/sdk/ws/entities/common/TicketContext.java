package com.sportradar.mts.sdk.ws.entities.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sportradar.mts.sdk.ws.entities.channel.Channel;
import com.sportradar.mts.sdk.ws.entities.payout.Payout;
import com.sportradar.mts.sdk.ws.entities.ref.TicketRef;

/**
 * Represents the context of a ticket.
 */
public class TicketContext {

    @JsonProperty("walletId")
    private String walletId;
    @JsonProperty("ref")
    private TicketRef ref;
    @JsonProperty("channel")
    private Channel channel;
    @JsonProperty("limitId")
    private long limitId;
    @JsonProperty("payoutCap")
    private Payout[] payoutCap;
    @JsonProperty("endCustomer")
    private EndCustomer endCustomer;

    /**
     * Creates a new instance of the TicketContext.Builder class.
     *
     * @return A new instance of the TicketContext.Builder class.
     */
    public static Builder newBuilder() {
        return new Builder();
    }

    /**
     * Gets the wallet ID associated with the ticket context.
     *
     * @return The wallet ID associated with the ticket context.
     */
    public String getWalletId() {
        return this.walletId;
    }

    /**
     * Sets the wallet ID associated with the ticket context.
     *
     * @param value The wallet ID to set.
     */
    public void setWalletId(String value) {
        this.walletId = value;
    }

    /**
     * Gets the reference of the ticket.
     *
     * @return The reference of the ticket.
     */
    public TicketRef getRef() {
        return this.ref;
    }

    /**
     * Sets the reference of the ticket.
     *
     * @param value The reference of the ticket to set.
     */
    public void setRef(TicketRef value) {
        this.ref = value;
    }

    /**
     * Gets the channel associated with the ticket context.
     *
     * @return The channel associated with the ticket context.
     */
    public Channel getChannel() {
        return this.channel;
    }

    /**
     * Sets the channel associated with the ticket context.
     *
     * @param value The channel to set.
     */
    public void setChannel(Channel value) {
        this.channel = value;
    }

    /**
     * Gets the limit ID associated with the ticket context.
     *
     * @return The limit ID associated with the ticket context.
     */
    public long getLimitId() {
        return this.limitId;
    }

    /**
     * Sets the limit ID associated with the ticket context.
     *
     * @param value The limit ID to set.
     */
    public void setLimitId(long value) {
        this.limitId = value;
    }

    /**
     * Gets the payout cap associated with the ticket context.
     *
     * @return The payout cap associated with the ticket context.
     */
    public Payout[] getPayoutCap() {
        return this.payoutCap;
    }

    /**
     * Sets the payout cap associated with the ticket context.
     *
     * @param value The payout cap to set.
     */
    public void setPayoutCap(Payout[] value) {
        this.payoutCap = value;
    }

    /**
     * Gets the end customer associated with the ticket context.
     *
     * @return The end customer associated with the ticket context.
     */
    public EndCustomer getEndCustomer() {
        return this.endCustomer;
    }

    /**
     * Sets the end customer associated with the ticket context.
     *
     * @param value The end customer to set.
     */
    public void setEndCustomer(EndCustomer value) {
        this.endCustomer = value;
    }

    /**
     * Builder class for creating instances of the TicketContext class.
     */
    public static class Builder {

        private final TicketContext instance = new TicketContext();

        private Builder() {
        }

        /**
         * Builds and returns the TicketContext instance.
         *
         * @return The built TicketContext instance.
         */
        public TicketContext build() {
            return this.instance;
        }

        /**
         * Sets the wallet ID associated with the ticket context.
         *
         * @param value The wallet ID to set.
         * @return The Builder instance.
         */
        public Builder setWalletId(String value) {
            this.instance.setWalletId(value);
            return this;
        }

        /**
         * Sets the reference of the ticket.
         *
         * @param value The reference of the ticket to set.
         * @return The Builder instance.
         */
        public Builder setRef(TicketRef value) {
            this.instance.setRef(value);
            return this;
        }

        /**
         * Sets the channel associated with the ticket context.
         *
         * @param value The channel to set.
         * @return The Builder instance.
         */
        public Builder setChannel(Channel value) {
            this.instance.setChannel(value);
            return this;
        }

        /**
         * Sets the limit ID associated with the ticket context.
         *
         * @param value The limit ID to set.
         * @return The Builder instance.
         */
        public Builder setLimitId(long value) {
            this.instance.setLimitId(value);
            return this;
        }

        /**
         * Sets the payout cap associated with the ticket context.
         *
         * @param value The payout cap to set.
         * @return The Builder instance.
         */
        public Builder setPayoutCap(Payout... value) {
            this.instance.setPayoutCap(value);
            return this;
        }

        /**
         * Sets the end customer associated with the ticket context.
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
