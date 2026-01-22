/*
 * Copyright (c) 2012, 2025, Oracle and/or its affiliates. All rights reserved.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */
package jdk.internal.event;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Helper class for exception events.
 */
public final class ThrowableTracer {

    private static final AtomicLong numThrowables = new AtomicLong();

    public static void traceError(Class<?> clazz, String message) {
        if (OutOfMemoryError.class.isAssignableFrom(clazz)) {
            return;
        }
        if (ErrorThrownEvent.enabled() || ExceptionThrownEvent.enabled()) {
            long timestamp = ErrorThrownEvent.timestamp();
            if (ErrorThrownEvent.enabled()) {
                ErrorThrownEvent.commit(timestamp, message, clazz);
            }
            if (ExceptionThrownEvent.shouldThrottleCommit(timestamp)) {
                ExceptionThrownEvent.commit(timestamp, message, clazz);
            }
        }
        numThrowables.incrementAndGet();
    }

    public static void traceThrowable(Class<?> clazz, String message) {
        if (ExceptionThrownEvent.enabled()) {
            long timestamp = ErrorThrownEvent.timestamp();
            if (ExceptionThrownEvent.shouldThrottleCommit(timestamp)) {
                ExceptionThrownEvent.commit(timestamp, message, clazz);
            }
        }
        numThrowables.incrementAndGet();
    }

    public static void emitStatistics() {
        long timestamp = ExceptionStatisticsEvent.timestamp();
        ExceptionStatisticsEvent.commit(timestamp, numThrowables.get());
    }
}
