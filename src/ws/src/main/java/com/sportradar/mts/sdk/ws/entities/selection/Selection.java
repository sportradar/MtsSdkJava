package com.sportradar.mts.sdk.ws.entities.selection;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * Represents a selection.
 */
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = UfSelection.class, name = "uf"),
        @JsonSubTypes.Type(value = OddsBoostSelection.class, name = "odds-boost"),
        @JsonSubTypes.Type(value = ExtSelection.class, name = "external"),
        @JsonSubTypes.Type(value = SystemSelection.class, name = "system"),
        @JsonSubTypes.Type(value = WaysSelection.class, name = "ways"),
        @JsonSubTypes.Type(value = UfCustomBetSelection.class, name = "uf-custom-bet"),
        @JsonSubTypes.Type(value = AccumulatorSelection.class, name = "accumulator")
})
public class Selection {

    /**
     * Creates a new instance of {@link UfSelection.Builder}.
     *
     * @return a new instance of {@link UfSelection.Builder}
     */
    public static UfSelection.Builder newUfSelectionBuilder() {
        return UfSelection.newBuilder();
    }

    /**
     * Creates a new instance of {@link OddsBoostSelection.Builder}.
     *
     * @return a new instance of {@link OddsBoostSelection.Builder}
     */
    public static OddsBoostSelection.Builder newOddsBoostSelectionBuilder() {
        return OddsBoostSelection.newBuilder();
    }

    /**
     * Creates a new instance of {@link ExtSelection.Builder}.
     *
     * @return a new instance of {@link ExtSelection.Builder}
     */
    public static ExtSelection.Builder newExtSelectionBuilder() {
        return ExtSelection.newBuilder();
    }

    /**
     * Creates a new instance of {@link SystemSelection.Builder}.
     *
     * @return a new instance of {@link SystemSelection.Builder}
     */
    public static SystemSelection.Builder newSystemSelectionBuilder() {
        return SystemSelection.newBuilder();
    }

    /**
     * Creates a new instance of {@link WaysSelection.Builder}.
     *
     * @return a new instance of {@link WaysSelection.Builder}
     */
    public static WaysSelection.Builder newWaysSelectionBuilder() {
        return WaysSelection.newBuilder();
    }

    /**
     * Creates a new instance of {@link UfCustomBetSelection.Builder}.
     *
     * @return a new instance of {@link UfCustomBetSelection.Builder}
     */
    public static UfCustomBetSelection.Builder newUfCustomBetSelectionBuilder() {
        return UfCustomBetSelection.newBuilder();
    }

    /**
     * Creates a new instance of {@link AccumulatorSelection.Builder}.
     *
     * @return a new instance of {@link AccumulatorSelection.Builder}
     */
    public static AccumulatorSelection.Builder newAccumulatorSelectionBuilder() {
        return AccumulatorSelection.newBuilder();
    }

}
