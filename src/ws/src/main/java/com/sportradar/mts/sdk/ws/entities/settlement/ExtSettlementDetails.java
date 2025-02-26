package com.sportradar.mts.sdk.ws.entities.settlement;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * Represents the external settlement details for a settlement.
 */
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = BetExtSettlementDetails.class, name = "bet"),
        @JsonSubTypes.Type(value = TicketExtSettlementDetails.class, name = "ticket")
})
public class ExtSettlementDetails {

    /**
     * Creates a new instance of BetExtSettlementDetails.Builder.
     *
     * @return the new instance of BetExtSettlementDetails.Builder
     */
    public static BetExtSettlementDetails.Builder newBetExtSettlementDetailsBuilder() {
        return BetExtSettlementDetails.newBuilder();
    }

    /**
     * Creates a new instance of TicketExtSettlementDetails.Builder.
     *
     * @return the new instance of TicketExtSettlementDetails.Builder
     */
    public static TicketExtSettlementDetails.Builder newTicketExtSettlementDetailsBuilder() {
        return TicketExtSettlementDetails.newBuilder();
    }
}