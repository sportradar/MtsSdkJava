package com.sportradar.mts.sdk.ws.internal.utils;

public class ExcSuppress {

    public static void close(final AutoCloseable closeable) {
        try {
            if (closeable != null) {
                closeable.close();
            }
        } catch (final Exception ignored) {
        }
    }

    public static void threadJoin(final Thread thread) {
        try {
            if (thread != null) {
                thread.interrupt();
                thread.join();
            }
        } catch (final Exception ignored) {
        }
    }
}
