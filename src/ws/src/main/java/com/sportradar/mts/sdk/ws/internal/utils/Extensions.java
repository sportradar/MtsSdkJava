package com.sportradar.mts.sdk.ws.internal.utils;

import java.util.UUID;

public class Extensions {

    public static String randomString() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    public static <T> T notNull(final T value, final String name) {
        if (value == null) {
            throw new NullPointerException(name);
        }
        return value;
    }

    public static <T extends Comparable<T>> T withDefault(final T value, final T defaultValue, final T minValue) {
        final T val = value == null ? defaultValue : value;
        return val.compareTo(minValue) < 0 ? minValue : val;
    }
}
