package com.sportradar.mts.sdk.ws.entities.cancellation;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

/**
 * Represents the details of a partial cancellation of a ticket.
 * Extends the CancelDetails class.
 */
public class TicketPartialCancelDetails extends CancelDetails {

    @JsonProperty("code")
    private int code;
    @JsonProperty("percentage")
    private BigDecimal percentage;
    @JsonProperty("ticketSignature")
    private String ticketSignature;
    @JsonProperty("ticketId")
    private String ticketId;

    /**
     * Returns a new instance of the TicketPartialCancelDetails.Builder class.
     *
     * @return a new instance of the TicketPartialCancelDetails.Builder class
     */
    public static Builder newBuilder() {
        return new Builder();
    }

    /**
     * Returns the cancellation code.
     *
     * @return the cancellation code
     */
    public int getCode() {
        return this.code;
    }

    /**
     * Sets the cancellation code.
     *
     * @param value the cancellation code to set
     */
    public void setCode(int value) {
        this.code = value;
    }

    /**
     * Returns the percentage of the ticket that was cancelled.
     *
     * @return the percentage of the ticket that was cancelled
     */
    public BigDecimal getPercentage() {
        return this.percentage;
    }

    /**
     * Sets the percentage of the ticket that was cancelled.
     *
     * @param value the percentage of the ticket that was cancelled
     */
    public void setPercentage(BigDecimal value) {
        this.percentage = value;
    }

    /**
     * Returns the ticket signature.
     *
     * @return the ticket signature
     */
    public String getTicketSignature() {
        return this.ticketSignature;
    }

    /**
     * Sets the ticket signature.
     *
     * @param value the ticket signature to set
     */
    public void setTicketSignature(String value) {
        this.ticketSignature = value;
    }

    /**
     * Returns the ticket ID.
     *
     * @return the ticket ID
     */
    public String getTicketId() {
        return this.ticketId;
    }

    /**
     * Sets the ticket ID.
     *
     * @param value the ticket ID to set
     */
    public void setTicketId(String value) {
        this.ticketId = value;
    }

    /**
     * Represents the builder for the TicketPartialCancelDetails class.
     */
    public static class Builder {

        private final TicketPartialCancelDetails instance = new TicketPartialCancelDetails();

        private Builder() {
        }

        /**
         * Builds and returns the instance of the TicketPartialCancelDetails class.
         *
         * @return the instance of the TicketPartialCancelDetails class
         */
        public TicketPartialCancelDetails build() {
            return this.instance;
        }

        /**
         * Sets the cancellation code.
         *
         * @param value the cancellation code to set
         * @return the instance of the builder
         */
        public Builder setCode(int value) {
            this.instance.setCode(value);
            return this;
        }

        /**
         * Sets the percentage of the ticket that was cancelled.
         *
         * @param value the percentage of the ticket that was cancelled
         * @return the instance of the builder
         */
        public Builder setPercentage(BigDecimal value) {
            this.instance.setPercentage(value);
            return this;
        }

        /**
         * Sets the ticket signature.
         *
         * @param value the ticket signature to set
         * @return the instance of the builder
         */
        public Builder setTicketSignature(String value) {
            this.instance.setTicketSignature(value);
            return this;
        }

        /**
         * Sets the ticket ID.
         *
         * @param value the ticket ID to set
         * @return the instance of the builder
         */
        public Builder setTicketId(String value) {
            this.instance.setTicketId(value);
            return this;
        }
    }
}
