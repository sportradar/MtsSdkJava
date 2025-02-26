package com.sportradar.mts.sdk.ws.entities.ref;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * Represents a reference to a ticket.
 */
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = AltStakeTicketRef.class, name = "alt-stake"),
        @JsonSubTypes.Type(value = ReofferTicketRef.class, name = "reoffer")
})
public class TicketRef {

    /**
     * Creates a new instance of AltStakeTicketRef.Builder.
     *
     * @return a new instance of AltStakeTicketRef.Builder
     */
    public static AltStakeTicketRef.Builder newAltStakeTicketRefBuilder() {
        return AltStakeTicketRef.newBuilder();
    }

    /**
     * Creates a new instance of ReofferTicketRef.Builder.
     *
     * @return a new instance of ReofferTicketRef.Builder
     */
    public static ReofferTicketRef.Builder newReofferTicketRefBuilder() {
        return ReofferTicketRef.newBuilder();
    }

}
