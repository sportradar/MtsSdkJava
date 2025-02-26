package com.sportradar.mts.sdk.ws.entities.common;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum PaymentMethod {
    CREDIT_CARD("credit-card");

    private static final Map<String, PaymentMethod> VALUES = new HashMap();

    static {
        for (final PaymentMethod val : EnumSet.allOf(PaymentMethod.class)) {
            VALUES.put(val.jsonVal, val);
        }
    }

    private final String jsonVal;

    PaymentMethod(final String jsonVal) {
        this.jsonVal = jsonVal;
    }

    @JsonCreator
    public static PaymentMethod fromValue(final String value) {
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
