package com.sportradar.mts.sdk.ws.entities.casinospin;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * Represents a casino spin.
 * This class is used as a base class for different types of casino spins.
 * It provides static methods to create instances of different types of casino spins.
 */
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = BonusCasinoSpin.class, name = "bonus"),
        @JsonSubTypes.Type(value = OrdinaryCasinoSpin.class, name = "ordinary"),
        @JsonSubTypes.Type(value = FreeCasinoSpin.class, name = "free")
})
public class CasinoSpin {

    /**
     * Creates a new instance of BonusCasinoSpin.Builder.
     *
     * @return a new instance of BonusCasinoSpin.Builder
     */
    public static BonusCasinoSpin.Builder newBonusCasinoSpinBuilder() {
        return BonusCasinoSpin.newBuilder();
    }

    /**
     * Creates a new instance of OrdinaryCasinoSpin.Builder.
     *
     * @return a new instance of OrdinaryCasinoSpin.Builder
     */
    public static OrdinaryCasinoSpin.Builder newOrdinaryCasinoSpinBuilder() {
        return OrdinaryCasinoSpin.newBuilder();
    }

    /**
     * Creates a new instance of FreeCasinoSpin.Builder.
     *
     * @return a new instance of FreeCasinoSpin.Builder
     */
    public static FreeCasinoSpin.Builder newFreeCasinoSpinBuilder() {
        return FreeCasinoSpin.newBuilder();
    }

}
