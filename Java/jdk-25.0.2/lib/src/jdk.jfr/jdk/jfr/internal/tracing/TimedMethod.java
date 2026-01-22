/*
 * Copyright (c) 2025, Oracle and/or its affiliates. All rights reserved.
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
package jdk.jfr.internal.tracing;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Record class that holds invocation measurements used by the MethodTiming
 * event.
 * <p>
 * Fields in record classes are truly final so might help to have a record here.
 */
record TimedMethod(AtomicLong invocations, AtomicLong time, AtomicLong minimum, AtomicLong maximum, Method method, AtomicBoolean published) {
    TimedMethod(Method method) {
        this(new AtomicLong(), new AtomicLong(), new AtomicLong(Long.MAX_VALUE), new AtomicLong(Long.MIN_VALUE), method, new AtomicBoolean());
    }

    public void updateMinMax(long duration) {
        if (duration == 0) {
            return; // Ignore data due to low-resolution clock
        }
        if (duration > maximum.getPlain()) {
            while (true) {
                long max = maximum.get();
                if (duration <= max) {
                    return;
                }
                if (maximum.weakCompareAndSetVolatile(max, duration)) {
                    return;
                }
            }
        }
        if (duration < minimum.getPlain()) {
            while (true) {
                long min = minimum.get();
                if (duration >= min) {
                    return;
                }
                if (minimum.weakCompareAndSetVolatile(min, duration)) {
                    return;
                }
            }
        }
    }
}
