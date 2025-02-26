package com.sportradar.mts.sdk.ws.entities.odds;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * Represents the odds for a particular event.
 */
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = IndonesianOdds.class, name = "indonesian"),
        @JsonSubTypes.Type(value = HongKongOdds.class, name = "hong-kong"),
        @JsonSubTypes.Type(value = FractionalOdds.class, name = "fractional"),
        @JsonSubTypes.Type(value = DecimalOdds.class, name = "decimal"),
        @JsonSubTypes.Type(value = MoneylineOdds.class, name = "moneyline"),
        @JsonSubTypes.Type(value = MalayOdds.class, name = "malay")
})
public class Odds {

    /**
     * Creates a new builder for DecimalOdds.
     *
     * @return a new instance of DecimalOdds.Builder
     */
    public static DecimalOdds.Builder newDecimalOddsBuilder() {
        return DecimalOdds.newBuilder();
    }

    /**
     * Creates a new builder for IndonesianOdds.
     *
     * @return a new instance of IndonesianOdds.Builder
     */
    public static IndonesianOdds.Builder newIndonesianOddsBuilder() {
        return IndonesianOdds.newBuilder();
    }

    /**
     * Creates a new builder for HongKongOdds.
     *
     * @return a new instance of HongKongOdds.Builder
     */
    public static HongKongOdds.Builder newHongKongOddsBuilder() {
        return HongKongOdds.newBuilder();
    }

    /**
     * Creates a new builder for FractionalOdds.
     *
     * @return a new instance of FractionalOdds.Builder
     */
    public static FractionalOdds.Builder newFractionalOddsBuilder() {
        return FractionalOdds.newBuilder();
    }

    /**
     * Creates a new builder for MoneylineOdds.
     *
     * @return a new instance of MoneylineOdds.Builder
     */
    public static MoneylineOdds.Builder newMoneylineOddsBuilder() {
        return MoneylineOdds.newBuilder();
    }

    /**
     * Creates a new builder for MalayOdds.
     *
     * @return a new instance of MalayOdds.Builder
     */
    public static MalayOdds.Builder newMalayOddsBuilder() {
        return MalayOdds.newBuilder();
    }
}
