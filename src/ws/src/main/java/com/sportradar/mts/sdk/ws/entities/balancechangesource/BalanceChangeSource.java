package com.sportradar.mts.sdk.ws.entities.balancechangesource;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * Represents a source of balance change.
 * This class provides static methods to create instances of specific balance change sources.
 */
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = TicketBalanceChangeSource.class, name = "ticket"),
        @JsonSubTypes.Type(value = DepositBalanceChangeSource.class, name = "deposit"),
        @JsonSubTypes.Type(value = WithdrawalBalanceChangeSource.class, name = "withdrawal")
})
public class BalanceChangeSource {

    /**
     * Creates a new instance of TicketBalanceChangeSource.Builder.
     *
     * @return a new instance of TicketBalanceChangeSource.Builder
     */
    public static TicketBalanceChangeSource.Builder newTicketBalanceChangeSourceBuilder() {
        return TicketBalanceChangeSource.newBuilder();
    }

    /**
     * Creates a new instance of DepositBalanceChangeSource.Builder.
     *
     * @return a new instance of DepositBalanceChangeSource.Builder
     */
    public static DepositBalanceChangeSource.Builder newDepositBalanceChangeSourceBuilder() {
        return DepositBalanceChangeSource.newBuilder();
    }

    /**
     * Creates a new instance of WithdrawalBalanceChangeSource.Builder.
     *
     * @return a new instance of WithdrawalBalanceChangeSource.Builder
     */
    public static WithdrawalBalanceChangeSource.Builder newWithdrawalBalanceChangeSourceBuilder() {
        return WithdrawalBalanceChangeSource.newBuilder();
    }

}
