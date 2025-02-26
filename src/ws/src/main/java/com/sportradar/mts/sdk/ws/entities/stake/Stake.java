package com.sportradar.mts.sdk.ws.entities.stake;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * The Stake class represents a stake in a betting system.
 * It provides static methods to create different types of stakes.
 */
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = FreeCashStake.class, name = "free-cash"),
        @JsonSubTypes.Type(value = FreeRolloverStake.class, name = "free-rollover"),
        @JsonSubTypes.Type(value = BonusStake.class, name = "bonus"),
        @JsonSubTypes.Type(value = FreeStake.class, name = "free"),
        @JsonSubTypes.Type(value = CashStake.class, name = "cash")
})
public class Stake {

    /**
     * Creates a new instance of FreeCashStake.Builder.
     *
     * @return a new instance of FreeCashStake.Builder
     */
    public static FreeCashStake.Builder newFreeCashStakeBuilder() {
        return FreeCashStake.newBuilder();
    }

    /**
     * Creates a new instance of FreeRolloverStake.Builder.
     *
     * @return a new instance of FreeRolloverStake.Builder
     */
    public static FreeRolloverStake.Builder newFreeRolloverStakeBuilder() {
        return FreeRolloverStake.newBuilder();
    }

    /**
     * Creates a new instance of BonusStake.Builder.
     *
     * @return a new instance of BonusStake.Builder
     */
    public static BonusStake.Builder newBonusStakeBuilder() {
        return BonusStake.newBuilder();
    }

    /**
     * Creates a new instance of FreeStake.Builder.
     *
     * @return a new instance of FreeStake.Builder
     */
    public static FreeStake.Builder newFreeStakeBuilder() {
        return FreeStake.newBuilder();
    }

    /**
     * Creates a new instance of CashStake.Builder.
     *
     * @return a new instance of CashStake.Builder
     */
    public static CashStake.Builder newCashStakeBuilder() {
        return CashStake.newBuilder();
    }

}
