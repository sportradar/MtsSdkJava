package com.sportradar.mts.sdk.ws.entities.cancellation;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents the details of a reoffer cancellation.
 * Extends the CancelDetails class.
 */
public class ReofferCancelDetails extends CancelDetails {

    @JsonProperty("code")
    private int code;
    @JsonProperty("ticketSignature")
    private String ticketSignature;
    @JsonProperty("ticketId")
    private String ticketId;

    /**
     * Returns a new instance of the ReofferCancelDetails.Builder class.
     *
     * @return a new instance of the ReofferCancelDetails.Builder class
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
     * Builder class for creating instances of ReofferCancelDetails.
     */
    public static class Builder {

        private final ReofferCancelDetails instance = new ReofferCancelDetails();

        private Builder() {
        }

        /**
         * Builds and returns the ReofferCancelDetails instance.
         *
         * @return the ReofferCancelDetails instance
         */
        public ReofferCancelDetails build() {
            return this.instance;
        }

        /**
         * Sets the cancellation code.
         *
         * @param value the cancellation code to set
         * @return the Builder instance
         */
        public Builder setCode(int value) {
            this.instance.setCode(value);
            return this;
        }

        /**
         * Sets the ticket signature.
         *
         * @param value the ticket signature to set
         * @return the Builder instance
         */
        public Builder setTicketSignature(String value) {
            this.instance.setTicketSignature(value);
            return this;
        }

        /**
         * Sets the ticket ID.
         *
         * @param value the ticket ID to set
         * @return the Builder instance
         */
        public Builder setTicketId(String value) {
            this.instance.setTicketId(value);
            return this;
        }
    }
}
