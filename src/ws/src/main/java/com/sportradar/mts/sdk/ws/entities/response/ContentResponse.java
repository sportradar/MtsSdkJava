package com.sportradar.mts.sdk.ws.entities.response;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * Represents a response containing content information.
 * This class is used as a base class for various content response types.
 */
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = DepositInformResponse.class, name = "deposit-inform-reply"),
        @JsonSubTypes.Type(value = ExtSettlementAckResponse.class, name = "ext-settlement-ack-reply"),
        @JsonSubTypes.Type(value = CashoutResponse.class, name = "cashout-reply"),
        @JsonSubTypes.Type(value = CashoutInformResponse.class, name = "cashout-inform-reply"),
        @JsonSubTypes.Type(value = TicketInformResponse.class, name = "ticket-inform-reply"),
        @JsonSubTypes.Type(value = CancelAckResponse.class, name = "cancel-ack-reply"),
        @JsonSubTypes.Type(value = CancelResponse.class, name = "cancel-reply"),
        @JsonSubTypes.Type(value = ErrorResponse.class, name = "error-reply"),
        @JsonSubTypes.Type(value = CasinoSessionsResponse.class, name = "casino-sessions-inform-reply"),
        @JsonSubTypes.Type(value = TicketAckResponse.class, name = "ticket-ack-reply"),
        @JsonSubTypes.Type(value = BalanceChangeInformResponse.class, name = "balance-change-inform-reply"),
        @JsonSubTypes.Type(value = ExtSettlementResponse.class, name = "ext-settlement-reply"),
        @JsonSubTypes.Type(value = CashoutAckResponse.class, name = "cashout-ack-reply"),
        @JsonSubTypes.Type(value = TicketResponse.class, name = "ticket-reply"),
        @JsonSubTypes.Type(value = WithdrawalInformResponse.class, name = "withdrawal-inform-reply"),
        @JsonSubTypes.Type(value = MaxStakeResponse.class, name = "max-stake-reply")
})
public class ContentResponse {

    /**
     * Creates a new instance of the DepositInformResponse builder.
     *
     * @return The DepositInformResponse builder.
     */
    public static DepositInformResponse.Builder newDepositInformResponseBuilder() {
        return DepositInformResponse.newBuilder();
    }

    /**
     * Creates a new instance of the ExtSettlementAckResponse builder.
     *
     * @return The ExtSettlementAckResponse builder.
     */
    public static ExtSettlementAckResponse.Builder newExtSettlementAckResponseBuilder() {
        return ExtSettlementAckResponse.newBuilder();
    }

    /**
     * Creates a new instance of the CashoutResponse builder.
     *
     * @return The CashoutResponse builder.
     */
    public static CashoutResponse.Builder newCashoutResponseBuilder() {
        return CashoutResponse.newBuilder();
    }

    /**
     * Creates a new instance of the {@link CashoutInformResponse.Builder}.
     *
     * @return The CashoutInformResponse builder.
     */
    public static CashoutInformResponse.Builder newCashoutInformResponseBuilder() {
        return CashoutInformResponse.newBuilder();
    }

    /**
     * Creates a new instance of the TicketInformResponse builder.
     *
     * @return The TicketInformResponse builder.
     */
    public static TicketInformResponse.Builder newTicketInformResponseBuilder() {
        return TicketInformResponse.newBuilder();
    }

    /**
     * Creates a new instance of the CancelAckResponse builder.
     *
     * @return The CancelAckResponse builder.
     */
    public static CancelAckResponse.Builder newCancelAckResponseBuilder() {
        return CancelAckResponse.newBuilder();
    }

    /**
     * Creates a new instance of the CancelResponse builder.
     *
     * @return The CancelResponse builder.
     */
    public static CancelResponse.Builder newCancelResponseBuilder() {
        return CancelResponse.newBuilder();
    }

    /**
     * Creates a new instance of the ErrorResponse builder.
     *
     * @return The ErrorResponse builder.
     */
    public static ErrorResponse.Builder newErrorResponseBuilder() {
        return ErrorResponse.newBuilder();
    }

    /**
     * Creates a new instance of the CasinoSessionsResponse builder.
     *
     * @return The CasinoSessionsResponse builder.
     */
    public static CasinoSessionsResponse.Builder newCasinoSessionsResponseBuilder() {
        return CasinoSessionsResponse.newBuilder();
    }

    /**
     * Creates a new instance of the TicketAckResponse builder.
     *
     * @return The TicketAckResponse builder.
     */
    public static TicketAckResponse.Builder newTicketAckResponseBuilder() {
        return TicketAckResponse.newBuilder();
    }

    /**
     * Creates a new instance of the BalanceChangeInformResponse builder.
     *
     * @return The BalanceChangeInformResponse builder.
     */
    public static BalanceChangeInformResponse.Builder newBalanceChangeInformResponseBuilder() {
        return BalanceChangeInformResponse.newBuilder();
    }

    /**
     * Creates a new instance of the ExtSettlementResponse builder.
     *
     * @return The ExtSettlementResponse builder.
     */
    public static ExtSettlementResponse.Builder newExtSettlementResponseBuilder() {
        return ExtSettlementResponse.newBuilder();
    }

    /**
     * Creates a new instance of the CashoutAckResponse builder.
     *
     * @return The CashoutAckResponse builder.
     */
    public static CashoutAckResponse.Builder newCashoutAckResponseBuilder() {
        return CashoutAckResponse.newBuilder();
    }

    /**
     * Creates a new instance of the TicketResponse builder.
     *
     * @return The TicketResponse builder.
     */
    public static TicketResponse.Builder newTicketResponseBuilder() {
        return TicketResponse.newBuilder();
    }

    /**
     * Creates a new instance of the WithdrawalInformResponse builder.
     *
     * @return The WithdrawalInformResponse builder.
     */
    public static WithdrawalInformResponse.Builder newWithdrawalInformResponseBuilder() {
        return WithdrawalInformResponse.newBuilder();
    }

    /**
     * Creates a new instance of the MaxStakeResponse builder.
     *
     * @return The MaxStakeResponse builder.
     */
    public static MaxStakeResponse.Builder newMaxStakeResponseBuilder() {
        return MaxStakeResponse.newBuilder();
    }

}
