package com.sportradar.mts.sdk.ws.entities.balancechangesource;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum TicketBalanceChangeAction {
    PLACE("place"),
    PAYOUT("payout");

    private static final Map<String, TicketBalanceChangeAction> VALUES = new HashMap();

    static {
        for (final TicketBalanceChangeAction val : EnumSet.allOf(TicketBalanceChangeAction.class)) {
            VALUES.put(val.jsonVal, val);
        }
    }

    private final String jsonVal;

    TicketBalanceChangeAction(final String jsonVal) {
        this.jsonVal = jsonVal;
    }

    @JsonCreator
    public static TicketBalanceChangeAction fromValue(final String value) {
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
