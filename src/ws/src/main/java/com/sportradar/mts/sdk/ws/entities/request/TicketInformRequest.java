package com.sportradar.mts.sdk.ws.entities.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sportradar.mts.sdk.ws.entities.common.BetValidation;

/**
 * Represents a request to inform the ticket.
 */
public class TicketInformRequest extends ContentRequest {

    @JsonProperty("ticket")
    private TicketRequest ticket;
    @JsonProperty("betValidations")
    private BetValidation[] betValidations;

    /**
     * Creates a new instance of the TicketInformRequest.Builder class.
     *
     * @return A new instance of the TicketInformRequest.Builder class.
     */
    public static Builder newBuilder() {
        return new Builder();
    }

    /**
     * Gets the ticket request.
     *
     * @return The ticket request.
     */
    public TicketRequest getTicket() {
        return this.ticket;
    }

    /**
     * Sets the ticket request.
     *
     * @param value The ticket request to set.
     */
    public void setTicket(TicketRequest value) {
        this.ticket = value;
    }

    /**
     * Gets the array of bet validations.
     *
     * @return The array of bet validations.
     */
    public BetValidation[] getBetValidations() {
        return this.betValidations;
    }

    /**
     * Sets the array of bet validations.
     *
     * @param value The array of bet validations to set.
     */
    public void setBetValidations(BetValidation[] value) {
        this.betValidations = value;
    }

    /**
     * Builder class for creating instances of the TicketInformRequest class.
     */
    public static class Builder {

        private final TicketInformRequest instance = new TicketInformRequest();

        private Builder() {
        }

        /**
         * Builds the TicketInformRequest instance.
         *
         * @return The built TicketInformRequest instance.
         */
        public TicketInformRequest build() {
            return this.instance;
        }

        /**
         * Sets the ticket request.
         *
         * @param value The ticket request to set.
         * @return The Builder instance.
         */
        public Builder setTicket(TicketRequest value) {
            this.instance.setTicket(value);
            return this;
        }

        /**
         * Sets the array of bet validations.
         *
         * @param value The array of bet validations to set.
         * @return The Builder instance.
         */
        public Builder setBetValidations(BetValidation... value) {
            this.instance.setBetValidations(value);
            return this;
        }
    }
}