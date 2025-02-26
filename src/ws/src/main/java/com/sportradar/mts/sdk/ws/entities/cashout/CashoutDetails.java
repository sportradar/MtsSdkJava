package com.sportradar.mts.sdk.ws.entities.cashout;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * Represents the details of a cashout operation.
 */
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = BetCashoutDetails.class, name = "bet"),
        @JsonSubTypes.Type(value = TicketPartialCashoutDetails.class, name = "ticket-partial"),
        @JsonSubTypes.Type(value = TicketCashoutDetails.class, name = "ticket"),
        @JsonSubTypes.Type(value = BetPartialCashoutDetails.class, name = "bet-partial")
})
public class CashoutDetails {

    /**
     * Creates a new builder for BetCashoutDetails.
     *
     * @return the builder instance
     */
    public static BetCashoutDetails.Builder newBetCashoutDetailsBuilder() {
        return BetCashoutDetails.newBuilder();
    }

    /**
     * Creates a new builder for TicketPartialCashoutDetails.
     *
     * @return the builder instance
     */
    public static TicketPartialCashoutDetails.Builder newTicketPartialCashoutDetailsBuilder() {
        return TicketPartialCashoutDetails.newBuilder();
    }

    /**
     * Creates a new builder for TicketCashoutDetails.
     *
     * @return the builder instance
     */
    public static TicketCashoutDetails.Builder newTicketCashoutDetailsBuilder() {
        return TicketCashoutDetails.newBuilder();
    }

    /**
     * Creates a new builder for BetPartialCashoutDetails.
     *
     * @return the builder instance
     */
    public static BetPartialCashoutDetails.Builder newBetPartialCashoutDetailsBuilder() {
        return BetPartialCashoutDetails.newBuilder();
    }

}
