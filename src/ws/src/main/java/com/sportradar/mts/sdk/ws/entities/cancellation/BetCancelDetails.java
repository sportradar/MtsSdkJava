package com.sportradar.mts.sdk.ws.entities.cancellation;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents the details of a bet cancellation.
 */
public class BetCancelDetails extends CancelDetails {

    @JsonProperty("code")
    private int code;
    @JsonProperty("betId")
    private String betId;
    @JsonProperty("ticketSignature")
    private String ticketSignature;
    @JsonProperty("ticketId")
    private String ticketId;

    /**
     * Creates a new instance of BetCancelDetails.Builder.
     *
     * @return A new instance of BetCancelDetails.Builder.
     */
    public static Builder newBuilder() {
        return new Builder();
    }

    /**
     * Gets the code of the bet cancellation.
     *
     * @return The code of the bet cancellation.
     */
    public int getCode() {
        return this.code;
    }

    /**
     * Sets the code of the bet cancellation.
     *
     * @param value The code of the bet cancellation.
     */
    public void setCode(int value) {
        this.code = value;
    }

    /**
     * Gets the ID of the bet that was cancelled.
     *
     * @return The ID of the bet that was cancelled.
     */
    public String getBetId() {
        return this.betId;
    }

    /**
     * Sets the ID of the bet that was cancelled.
     *
     * @param value The ID of the bet that was cancelled.
     */
    public void setBetId(String value) {
        this.betId = value;
    }

    /**
     * Gets the signature of the ticket that was cancelled.
     *
     * @return The signature of the ticket that was cancelled.
     */
    public String getTicketSignature() {
        return this.ticketSignature;
    }

    /**
     * Sets the signature of the ticket that was cancelled.
     *
     * @param value The signature of the ticket that was cancelled.
     */
    public void setTicketSignature(String value) {
        this.ticketSignature = value;
    }

    /**
     * Gets the ID of the ticket that was cancelled.
     *
     * @return The ID of the ticket that was cancelled.
     */
    public String getTicketId() {
        return this.ticketId;
    }

    /**
     * Sets the ID of the ticket that was cancelled.
     *
     * @param value The ID of the ticket that was cancelled.
     */
    public void setTicketId(String value) {
        this.ticketId = value;
    }

    /**
     * A builder class for creating instances of BetCancelDetails.
     */
    public static class Builder {

        private final BetCancelDetails instance = new BetCancelDetails();

        private Builder() {
        }

        /**
         * Builds the bet cancellation.
         *
         * @return The built bet cancellation.
         */
        public BetCancelDetails build() {
            return this.instance;
        }

        /**
         * Sets the code of the bet cancellation.
         *
         * @param value The code of the bet cancellation.
         * @return The builder instance.
         */
        public Builder setCode(int value) {
            this.instance.setCode(value);
            return this;
        }

        /**
         * Sets the ID of the bet that was cancelled.
         *
         * @param value The ID of the bet that was cancelled.
         * @return The builder instance.
         */
        public Builder setBetId(String value) {
            this.instance.setBetId(value);
            return this;
        }

        /**
         * Sets the signature of the ticket that was cancelled.
         *
         * @param value The signature of the ticket that was cancelled.
         * @return The builder instance.
         */
        public Builder setTicketSignature(String value) {
            this.instance.setTicketSignature(value);
            return this;
        }

        /**
         * Sets the ID of the ticket that was cancelled.
         *
         * @param value The ID of the ticket that was cancelled.
         * @return The builder instance.
         */
        public Builder setTicketId(String value) {
            this.instance.setTicketId(value);
            return this;
        }
    }
}
