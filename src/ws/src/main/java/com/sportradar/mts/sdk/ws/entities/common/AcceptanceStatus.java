package com.sportradar.mts.sdk.ws.entities.common;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum AcceptanceStatus {
    REJECTED("rejected"),
    ACCEPTED("accepted");

    private static final Map<String, AcceptanceStatus> VALUES = new HashMap();

    static {
        for (final AcceptanceStatus val : EnumSet.allOf(AcceptanceStatus.class)) {
            VALUES.put(val.jsonVal, val);
        }
    }

    private final String jsonVal;

    AcceptanceStatus(final String jsonVal) {
        this.jsonVal = jsonVal;
    }

    @JsonCreator
    public static AcceptanceStatus fromValue(final String value) {
        return value == null ? null : VALUES.get(value);
    }

    @JsonValue
    public String getJsonValue() {
        return this.jsonVal;
    }

    public String toString() {
        return this.jsonVal;
    }

}
