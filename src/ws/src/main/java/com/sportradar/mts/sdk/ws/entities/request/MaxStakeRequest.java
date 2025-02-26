package com.sportradar.mts.sdk.ws.entities.request;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a max stake request.
 */
public class MaxStakeRequest extends ContentRequest {

    @JsonProperty("ticket")
    private TicketRequest ticket;

    /**
     * Gets the ticket for the request.
     *
     * @return The ticket for the request.
     */
    public TicketRequest getTicket() {
        return this.ticket;
    }

    /**
     * Sets the ticket for the request.
     *
     * @param value The ticket for the request.
     */
    public void setTicket(TicketRequest value) {
        this.ticket = value;
    }


    /**
     * Creates a new instance of the MaxStakeRequest.Builder class.
     *
     * @return A new instance of the MaxStakeRequest.Builder class.
     */
    public static Builder newBuilder() {
        return new Builder();
    }

    /**
     * Builder class for creating instances of the MaxStakeRequest class.
     */
    public static class Builder {

        private final MaxStakeRequest instance = new MaxStakeRequest();

        private Builder() {
        }

        /**
         * Builds the MaxStakeRequest instance.
         *
         * @return The built MaxStakeRequest instance.
         */
        public MaxStakeRequest build() {
            return this.instance;
        }

        /**
         * Sets the ticket for the request.
         *
         * @param value The ticket for the request.
         * @return The Builder instance.
         */
        public Builder setTicket(TicketRequest value) {
            this.instance.setTicket(value);
            return this;
        }
    }
}