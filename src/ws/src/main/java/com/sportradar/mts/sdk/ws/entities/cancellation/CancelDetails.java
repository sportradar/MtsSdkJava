package com.sportradar.mts.sdk.ws.entities.cancellation;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * Represents the details of a cancellation.
 * This class provides static methods to create builders for different types of cancellation details.
 */
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = BetCancelDetails.class, name = "bet"),
        @JsonSubTypes.Type(value = ReofferCancelDetails.class, name = "reoffer"),
        @JsonSubTypes.Type(value = TicketPartialCancelDetails.class, name = "ticket-partial"),
        @JsonSubTypes.Type(value = TicketCancelDetails.class, name = "ticket"),
        @JsonSubTypes.Type(value = BetPartialCancelDetails.class, name = "bet-partial")
})
public class CancelDetails {

    /**
     * Creates a new instance of BetCancelDetails.Builder.
     *
     * @return A new instance of BetCancelDetails.Builder.
     */
    public static BetCancelDetails.Builder newBetCancelDetailsBuilder() {
        return BetCancelDetails.newBuilder();
    }

    /**
     * Creates a new instance of ReofferCancelDetails.Builder.
     *
     * @return A new instance of ReofferCancelDetails.Builder.
     */
    public static ReofferCancelDetails.Builder newReofferCancelDetailsBuilder() {
        return ReofferCancelDetails.newBuilder();
    }

    /**
     * Creates a new instance of TicketPartialCancelDetails.Builder.
     *
     * @return A new instance of TicketPartialCancelDetails.Builder.
     */
    public static TicketPartialCancelDetails.Builder newTicketPartialCancelDetailsBuilder() {
        return TicketPartialCancelDetails.newBuilder();
    }

    /**
     * Creates a new instance of TicketCancelDetails.Builder.
     *
     * @return A new instance of TicketCancelDetails.Builder.
     */
    public static TicketCancelDetails.Builder newTicketCancelDetailsBuilder() {
        return TicketCancelDetails.newBuilder();
    }

    /**
     * Creates a new instance of BetPartialCancelDetails.Builder.
     *
     * @return A new instance of BetPartialCancelDetails.Builder.
     */
    public static BetPartialCancelDetails.Builder newBetPartialCancelDetailsBuilder() {
        return BetPartialCancelDetails.newBuilder();
    }

}
