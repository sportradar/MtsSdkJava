package com.sportradar.mts.sdk.ws.entities.common;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum BalanceChangeStatus {
    INVALID("invalid"),
    VALID("valid");

    private static final Map<String, BalanceChangeStatus> VALUES = new HashMap();

    static {
        for (final BalanceChangeStatus val : EnumSet.allOf(BalanceChangeStatus.class)) {
            VALUES.put(val.jsonVal, val);
        }
    }

    private final String jsonVal;

    BalanceChangeStatus(final String jsonVal) {
        this.jsonVal = jsonVal;
    }

    @JsonCreator
    public static BalanceChangeStatus fromValue(final String value) {
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
