package com.sportradar.mts.sdk.ws.entities.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sportradar.mts.sdk.ws.entities.common.Bet;
import com.sportradar.mts.sdk.ws.entities.common.TicketContext;

/**
 * Represents a ticket request.
 */
public class TicketRequest extends ContentRequest {

    @JsonProperty("context")
    private TicketContext context;
    @JsonProperty("bets")
    private Bet[] bets;
    @JsonProperty("ticketId")
    private String ticketId;

    /**
     * Creates a new instance of the TicketRequest.Builder class.
     *
     * @return A new instance of the TicketRequest.Builder class.
     */
    public static Builder newBuilder() {
        return new Builder();
    }

    /**
     * Gets the context of the ticket request.
     *
     * @return The context of the ticket request.
     */
    public TicketContext getContext() {
        return this.context;
    }

    /**
     * Sets the context of the ticket request.
     *
     * @param value The context of the ticket request.
     */
    public void setContext(TicketContext value) {
        this.context = value;
    }

    /**
     * Gets the bets of the ticket request.
     *
     * @return The bets of the ticket request.
     */
    public Bet[] getBets() {
        return this.bets;
    }

    /**
     * Sets the bets of the ticket request.
     *
     * @param value The bets of the ticket request.
     */
    public void setBets(Bet[] value) {
        this.bets = value;
    }

    /**
     * Gets the ticket ID of the ticket request.
     *
     * @return The ticket ID of the ticket request.
     */
    public String getTicketId() {
        return this.ticketId;
    }

    /**
     * Sets the ticket ID of the ticket request.
     *
     * @param value The ticket ID of the ticket request.
     */
    public void setTicketId(String value) {
        this.ticketId = value;
    }

    /**
     * Represents a builder for the TicketRequest class.
     */
    public static class Builder {

        private final TicketRequest instance = new TicketRequest();

        private Builder() {
        }

        /**
         * Builds the TicketRequest instance.
         *
         * @return The built TicketRequest instance.
         */
        public TicketRequest build() {
            return this.instance;
        }

        /**
         * Sets the context of the ticket request.
         *
         * @param value The context of the ticket request.
         * @return The builder instance.
         */
        public Builder setContext(TicketContext value) {
            this.instance.setContext(value);
            return this;
        }

        /**
         * Sets the bets of the ticket request.
         *
         * @param value The bets of the ticket request.
         * @return The builder instance.
         */
        public Builder setBets(Bet... value) {
            this.instance.setBets(value);
            return this;
        }

        /**
         * Sets the ticket ID of the ticket request.
         *
         * @param value The ticket ID of the ticket request.
         * @return The builder instance.
         */
        public Builder setTicketId(String value) {
            this.instance.setTicketId(value);
            return this;
        }
    }
}
