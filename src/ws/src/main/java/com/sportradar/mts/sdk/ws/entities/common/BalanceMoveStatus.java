package com.sportradar.mts.sdk.ws.entities.common;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum BalanceMoveStatus {
    APPROVED("approved"),
    PENDING("pending"),
    REJECTED("rejected");

    private static final Map<String, BalanceMoveStatus> VALUES = new HashMap();

    static {
        for (final BalanceMoveStatus val : EnumSet.allOf(BalanceMoveStatus.class)) {
            VALUES.put(val.jsonVal, val);
        }
    }

    private final String jsonVal;

    BalanceMoveStatus(final String jsonVal) {
        this.jsonVal = jsonVal;
    }

    @JsonCreator
    public static BalanceMoveStatus fromValue(final String value) {
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
