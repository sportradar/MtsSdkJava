package com.sportradar.mts.sdk.ws.internal.utils;

public class TimeUtils {

    public static void sleep(final long millis) {
        try {
            Thread.sleep(millis);
        } catch (final Exception ignored) {
        }
    }

    public static long nowUtcMillis() {
        return System.currentTimeMillis();
    }
}
