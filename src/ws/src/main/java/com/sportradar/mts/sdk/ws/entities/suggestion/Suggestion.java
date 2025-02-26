package com.sportradar.mts.sdk.ws.entities.suggestion;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = ReofferSuggestion.class, name = "reoffer"),
        @JsonSubTypes.Type(value = AltStakeSuggestion.class, name = "alt-stake")
})
/**
 * Represents a suggestion entity.
 */
public class Suggestion {

    /**
     * Creates a new builder for ReofferSuggestion.
     *
     * @return the builder instance
     */
    public static ReofferSuggestion.Builder newReofferSuggestionBuilder() {
        return ReofferSuggestion.newBuilder();
    }

    /**
     * Creates a new builder for AltStakeSuggestion.
     *
     * @return the builder instance
     */
    public static AltStakeSuggestion.Builder newAltStakeSuggestionBuilder() {
        return AltStakeSuggestion.newBuilder();
    }

}
