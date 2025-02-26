package com.sportradar.mts.sdk.ws.internal.utils;

import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

public class Delayer {

    private static final ScheduledThreadPoolExecutor EXECUTOR;

    static {
        final ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(1, new SdkDelayerThreadFactory());
        executor.setRemoveOnCancelPolicy(true);
        EXECUTOR = executor;
    }

    public static ScheduledFuture<?> delay(final Runnable command, final long delay, final TimeUnit unit) {
        return EXECUTOR.schedule(command, delay, unit);
    }

    private static final class SdkDelayerThreadFactory implements ThreadFactory {
        public Thread newThread(final Runnable r) {
            final Thread t = new Thread(r);
            t.setDaemon(true);
            t.setName("SdkDelayerThread");
            return t;
        }
    }
}
