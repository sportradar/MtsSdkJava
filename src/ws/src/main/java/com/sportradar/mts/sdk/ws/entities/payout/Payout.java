package com.sportradar.mts.sdk.ws.entities.payout;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * A class used to create instances of the {@link FreePayout}, {@link CashPayout} and {@link WithheldPayout} classes.
 */
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = FreePayout.class, name = "free"),
        @JsonSubTypes.Type(value = CashPayout.class, name = "cash"),
        @JsonSubTypes.Type(value = WithheldPayout.class, name = "withheld")
})
public class Payout {

    /**
     * Creates a new instance of the {@link FreePayout.Builder} class.
     *
     * @return A new instance of the {@link FreePayout.Builder} class.
     */
    public static FreePayout.Builder newFreePayoutBuilder() {
        return FreePayout.newBuilder();
    }

    /**
     * Creates a new instance of the {@link CashPayout.Builder} class.
     *
     * @return A new instance of the {@link CashPayout.Builder} class.
     */
    public static CashPayout.Builder newCashPayoutBuilder() {
        return CashPayout.newBuilder();
    }

    /**
     * Creates a new instance of the {@link WithheldPayout.Builder} class.
     *
     * @return A new instance of the {@link WithheldPayout.Builder} class.
     */
    public static WithheldPayout.Builder newWithheldPayoutBuilder() {
        return WithheldPayout.newBuilder();
    }

}
