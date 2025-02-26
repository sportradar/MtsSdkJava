package com.sportradar.mts.sdk.ws.entities.stake;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum StakeMode {
    UNIT("unit"),
    TOTAL("total");

    private static final Map<String, StakeMode> VALUES = new HashMap();

    static {
        for (final StakeMode val : EnumSet.allOf(StakeMode.class)) {
            VALUES.put(val.jsonVal, val);
        }
    }

    private final String jsonVal;

    StakeMode(final String jsonVal) {
        this.jsonVal = jsonVal;
    }

    @JsonCreator
    public static StakeMode fromValue(final String value) {
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
