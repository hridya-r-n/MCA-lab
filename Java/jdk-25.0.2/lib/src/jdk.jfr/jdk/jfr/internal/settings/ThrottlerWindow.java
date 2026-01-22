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
package jdk.jfr.internal.settings;

import jdk.jfr.internal.JVMSupport;

import java.util.StringJoiner;
import java.util.concurrent.atomic.AtomicLong;
import jdk.jfr.internal.JVM;

final class ThrottlerWindow {
    private final AtomicLong measuredPopulationSize = new AtomicLong();
    // Guarded by Throttler.lock.
    ThrottlerParameters parameters = new ThrottlerParameters(0, 0, 0);
    long samplingInterval = 1;
    long projectedPopulationSize;

    private volatile long endTicks;

    void initialize(ThrottlerParameters parameters) {
        if (parameters.windowDurationMillis == 0) {
            endTicks = 0;
            return;
        }
        measuredPopulationSize.set(0);
        endTicks = JVM.counterTime() + JVMSupport.nanosToTicks(1_000_000L * parameters.windowDurationMillis);
    }

    boolean isExpired(long timestamp) {
        long endTicks = this.endTicks;
        if (timestamp == 0) {
            return JVM.counterTime() >= endTicks;
        } else {
            return timestamp >= endTicks;
        }
    }

    boolean sample() {
        long ordinal = measuredPopulationSize.incrementAndGet();
        return ordinal <= projectedPopulationSize && ordinal % samplingInterval == 0;
    }

    long maxSampleSize() {
        return samplingInterval == 0 ? 0 : projectedPopulationSize / samplingInterval;
    }

    long sampleSize() {
        long size = populationSize();
        return size > projectedPopulationSize ? maxSampleSize() : size / samplingInterval;
    }

    long populationSize() {
        return measuredPopulationSize.get();
    }

    long accumulatedDebt() {
        if (projectedPopulationSize == 0) {
            return 0;
        }
        return parameters.samplePointsPerWindow - maxSampleSize() + debt();
    }

    long debt() {
        if (projectedPopulationSize == 0) {
            return 0;
        }
        return sampleSize() - parameters.samplePointsPerWindow;
    }

    public String toString() {
        StringJoiner sb = new StringJoiner(", ");
        sb.add("measuredPopulationSize=" + measuredPopulationSize);
        sb.add("samplingInterval=" + samplingInterval);
        sb.add("projectedPopulationSize=" + projectedPopulationSize);
        return sb.toString();
    }
}
