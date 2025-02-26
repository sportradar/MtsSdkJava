package com.sportradar.mts.sdk.ws.entities.balancechangesource;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a balance change source related to a ticket.
 */
public class TicketBalanceChangeSource extends BalanceChangeSource {

    @JsonProperty("action")
    private TicketBalanceChangeAction action;
    @JsonProperty("id")
    private String id;

    /**
     * Creates a new instance of TicketBalanceChangeSource.Builder.
     *
     * @return A new instance of TicketBalanceChangeSource.Builder.
     */
    public static Builder newBuilder() {
        return new Builder();
    }

    /**
     * Gets the action associated with the balance change source.
     *
     * @return The action associated with the balance change source.
     */
    public TicketBalanceChangeAction getAction() {
        return this.action;
    }

    /**
     * Sets the action associated with the balance change source.
     *
     * @param value The action to set.
     */
    public void setAction(TicketBalanceChangeAction value) {
        this.action = value;
    }

    /**
     * Gets the ID of the balance change source.
     *
     * @return The ID of the balance change source.
     */
    public String getId() {
        return this.id;
    }

    /**
     * Sets the ID of the balance change source.
     *
     * @param value The ID to set.
     */
    public void setId(String value) {
        this.id = value;
    }

    /**
     * Represents a builder for creating instances of TicketBalanceChangeSource.
     */
    public static class Builder {

        private final TicketBalanceChangeSource instance = new TicketBalanceChangeSource();

        private Builder() {
        }

        /**
         * Builds the TicketBalanceChangeSource instance.
         *
         * @return The built TicketBalanceChangeSource instance.
         */
        public TicketBalanceChangeSource build() {
            return this.instance;
        }

        /**
         * Sets the action associated with the balance change source.
         *
         * @param value The action to set.
         * @return The builder instance.
         */
        public Builder setAction(TicketBalanceChangeAction value) {
            this.instance.setAction(value);
            return this;
        }

        /**
         * Sets the ID of the balance change source.
         *
         * @param value The ID to set.
         * @return The builder instance.
         */
        public Builder setId(String value) {
            this.instance.setId(value);
            return this;
        }
    }
}
