package com.sportradar.mts.sdk.ws.entities.request;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * Represents a request for content.
 * This class is used as a base class for different types of content requests.
 * It provides static methods to create instances of specific content request types.
 */
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = CancelRequest.class, name = "cancel"),
        @JsonSubTypes.Type(value = TicketInformRequest.class, name = "ticket-inform"),
        @JsonSubTypes.Type(value = TicketRequest.class, name = "ticket"),
        @JsonSubTypes.Type(value = CashoutAckRequest.class, name = "cashout-ack"),
        @JsonSubTypes.Type(value = DepositInformRequest.class, name = "deposit-inform"),
        @JsonSubTypes.Type(value = CashoutRequest.class, name = "cashout"),
        @JsonSubTypes.Type(value = CashoutInformRequest.class, name = "cashout-inform"),
        @JsonSubTypes.Type(value = CasinoSessionsRequest.class, name = "casino-sessions-inform"),
        @JsonSubTypes.Type(value = TicketAckRequest.class, name = "ticket-ack"),
        @JsonSubTypes.Type(value = ExtSettlementRequest.class, name = "ext-settlement"),
        @JsonSubTypes.Type(value = CancelAckRequest.class, name = "cancel-ack"),
        @JsonSubTypes.Type(value = WithdrawalInformRequest.class, name = "withdrawal-inform"),
        @JsonSubTypes.Type(value = ExtSettlementAckRequest.class, name = "ext-settlement-ack"),
        @JsonSubTypes.Type(value = BalanceChangeInformRequest.class, name = "balance-change-inform"),
        @JsonSubTypes.Type(value = MaxStakeRequest.class, name = "max-stake")
})
public class ContentRequest {

    /**
     * Creates a new instance of the CancelRequest builder.
     *
     * @return The CancelRequest builder.
     */
    public static CancelRequest.Builder newCancelRequestBuilder() {
        return CancelRequest.newBuilder();
    }

    /**
     * Creates a new instance of the TicketInformRequest builder.
     *
     * @return The TicketInformRequest builder.
     */
    public static TicketInformRequest.Builder newTicketInformRequestBuilder() {
        return TicketInformRequest.newBuilder();
    }

    /**
     * Creates a new instance of the TicketRequest builder.
     *
     * @return The TicketRequest builder.
     */
    public static TicketRequest.Builder newTicketRequestBuilder() {
        return TicketRequest.newBuilder();
    }

    /**
     * Creates a new instance of the CashoutAckRequest builder.
     *
     * @return The CashoutAckRequest builder.
     */
    public static CashoutAckRequest.Builder newCashoutAckRequestBuilder() {
        return CashoutAckRequest.newBuilder();
    }

    /**
     * Creates a new instance of the DepositInformRequest builder.
     *
     * @return The DepositInformRequest builder.
     */
    public static DepositInformRequest.Builder newDepositInformRequestBuilder() {
        return DepositInformRequest.newBuilder();
    }

    /**
     * Creates a new instance of the CashoutRequest builder.
     *
     * @return The CashoutRequest builder.
     */
    public static CashoutRequest.Builder newCashoutRequestBuilder() {
        return CashoutRequest.newBuilder();
    }

    /**
     * Creates a new instance of the {@link CashoutInformRequest.Builder}.
     *
     * @return The CashoutInformRequest builder.
     */
    public static CashoutInformRequest.Builder newCashoutInformRequestBuilder() {
        return CashoutInformRequest.newBuilder();
    }

    /**
     * Creates a new instance of the CasinoSessionsRequest builder.
     *
     * @return The CasinoSessionsRequest builder.
     */
    public static CasinoSessionsRequest.Builder newCasinoSessionsRequestBuilder() {
        return CasinoSessionsRequest.newBuilder();
    }

    /**
     * Creates a new instance of the TicketAckRequest builder.
     *
     * @return The TicketAckRequest builder.
     */
    public static TicketAckRequest.Builder newTicketAckRequestBuilder() {
        return TicketAckRequest.newBuilder();
    }

    /**
     * Creates a new instance of the ExtSettlementRequest builder.
     *
     * @return The ExtSettlementRequest builder.
     */
    public static ExtSettlementRequest.Builder newExtSettlementRequestBuilder() {
        return ExtSettlementRequest.newBuilder();
    }

    /**
     * Creates a new instance of the CancelAckRequest builder.
     *
     * @return The CancelAckRequest builder.
     */
    public static CancelAckRequest.Builder newCancelAckRequestBuilder() {
        return CancelAckRequest.newBuilder();
    }

    /**
     * Creates a new instance of the WithdrawalInformRequest builder.
     *
     * @return The WithdrawalInformRequest builder.
     */
    public static WithdrawalInformRequest.Builder newWithdrawalInformRequestBuilder() {
        return WithdrawalInformRequest.newBuilder();
    }

    /**
     * Creates a new instance of the ExtSettlementAckRequest builder.
     *
     * @return The ExtSettlementAckRequest builder.
     */
    public static ExtSettlementAckRequest.Builder newExtSettlementAckRequestBuilder() {
        return ExtSettlementAckRequest.newBuilder();
    }

    /**
     * Creates a new instance of the BalanceChangeInformRequest builder.
     *
     * @return The BalanceChangeInformRequest builder.
     */
    public static BalanceChangeInformRequest.Builder newBalanceChangeInformRequestBuilder() {
        return BalanceChangeInformRequest.newBuilder();
    }

    /**
     * Creates a new instance of the MaxStakeRequest builder.
     *
     * @return The MaxStakeRequest builder.
     */
    public static MaxStakeRequest.Builder newMaxStakeRequestBuilder() {
        return MaxStakeRequest.newBuilder();
    }

}
