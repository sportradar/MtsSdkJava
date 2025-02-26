package com.sportradar.mts.sdk.ws.entities.common;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum OddsChange {
    NONE("none"),
    ANY("any"),
    HIGHER("higher"),
    LOWER("lower");

    private static final Map<String, OddsChange> VALUES = new HashMap();

    static {
        for (final OddsChange val : EnumSet.allOf(OddsChange.class)) {
            VALUES.put(val.jsonVal, val);
        }
    }

    private final String jsonVal;

    OddsChange(final String jsonVal) {
        this.jsonVal = jsonVal;
    }

    @JsonCreator
    public static OddsChange fromValue(final String value) {
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
