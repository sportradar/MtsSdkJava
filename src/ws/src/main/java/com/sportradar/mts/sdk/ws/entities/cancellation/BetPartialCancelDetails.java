package com.sportradar.mts.sdk.ws.entities.cancellation;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

/**
 * Represents the details of a partial cancellation of a bet.
 */
public class BetPartialCancelDetails extends CancelDetails {

    @JsonProperty("code")
    private int code;
    @JsonProperty("percentage")
    private BigDecimal percentage;
    @JsonProperty("betId")
    private String betId;
    @JsonProperty("ticketSignature")
    private String ticketSignature;
    @JsonProperty("ticketId")
    private String ticketId;

    /**
     * Creates a new instance of BetPartialCancelDetails.Builder.
     *
     * @return A new instance of BetPartialCancelDetails.Builder.
     */
    public static Builder newBuilder() {
        return new Builder();
    }

    /**
     * Gets the code of the partial bet cancellation.
     *
     * @return The code of the partial bet cancellation.
     */
    public int getCode() {
        return this.code;
    }

    /**
     * Sets the code of the partial bet cancellation.
     *
     * @param value The code of the partial bet cancellation.
     */
    public void setCode(int value) {
        this.code = value;
    }

    /**
     * Gets the percentage of the bet that was cancelled.
     *
     * @return The percentage of the bet that was cancelled.
     */
    public BigDecimal getPercentage() {
        return this.percentage;
    }

    /**
     * Sets the percentage of the bet that was cancelled.
     *
     * @param value The percentage of the bet that was cancelled.
     */
    public void setPercentage(BigDecimal value) {
        this.percentage = value;
    }

    /**
     * Gets the ID of the bet that was partially cancelled.
     *
     * @return The ID of the bet that was partially cancelled.
     */
    public String getBetId() {
        return this.betId;
    }

    /**
     * Sets the ID of the bet that was partially cancelled.
     *
     * @param value The ID of the bet that was partially cancelled.
     */
    public void setBetId(String value) {
        this.betId = value;
    }

    /**
     * Gets the signature of the ticket that was partially cancelled.
     *
     * @return The signature of the ticket that was partially cancelled.
     */
    public String getTicketSignature() {
        return this.ticketSignature;
    }

    /**
     * Sets the signature of the ticket that was partially cancelled.
     *
     * @param value The signature of the ticket that was partially cancelled.
     */
    public void setTicketSignature(String value) {
        this.ticketSignature = value;
    }

    /**
     * Gets the ID of the ticket that was partially cancelled.
     *
     * @return The ID of the ticket that was partially cancelled.
     */
    public String getTicketId() {
        return this.ticketId;
    }

    /**
     * Sets the ID of the ticket that was partially cancelled.
     *
     * @param value The ID of the ticket that was partially cancelled.
     */
    public void setTicketId(String value) {
        this.ticketId = value;
    }

    /**
     * Builder class for creating instances of BetPartialCancelDetails.
     */
    public static class Builder {

        private final BetPartialCancelDetails instance = new BetPartialCancelDetails();

        private Builder() {
        }

        /**
         * Builds and returns the BetPartialCancelDetails instance.
         *
         * @return The built BetPartialCancelDetails instance.
         */
        public BetPartialCancelDetails build() {
            return this.instance;
        }

        /**
         * Sets the code of the partial bet cancellation.
         *
         * @param value The code of the partial bet cancellation.
         * @return The Builder instance.
         */
        public Builder setCode(int value) {
            this.instance.setCode(value);
            return this;
        }

        /**
         * Sets the percentage of the bet that was cancelled.
         *
         * @param value The percentage of the bet that was cancelled.
         * @return The Builder instance.
         */
        public Builder setPercentage(BigDecimal value) {
            this.instance.setPercentage(value);
            return this;
        }

        /**
         * Sets the ID of the bet that was partially cancelled.
         *
         * @param value The ID of the bet that was partially cancelled.
         * @return The Builder instance.
         */
        public Builder setBetId(String value) {
            this.instance.setBetId(value);
            return this;
        }

        /**
         * Sets the signature of the ticket that was partially cancelled.
         *
         * @param value The signature of the ticket that was partially cancelled.
         * @return The Builder instance.
         */
        public Builder setTicketSignature(String value) {
            this.instance.setTicketSignature(value);
            return this;
        }

        /**
         * Sets the ID of the ticket that was partially cancelled.
         *
         * @param value The ID of the ticket that was partially cancelled.
         * @return The Builder instance.
         */
        public Builder setTicketId(String value) {
            this.instance.setTicketId(value);
            return this;
        }
    }
}
