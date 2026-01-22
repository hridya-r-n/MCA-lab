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
package jdk.jfr.tracing;

import jdk.jfr.events.MethodTimingEvent;
import jdk.jfr.events.MethodTraceEvent;
import jdk.jfr.internal.JVM;
import jdk.jfr.internal.tracing.PlatformTracer;

/**
 * This class serves as the frontend for method tracing capabilities. The
 * jdk.jfr.tracing package is exported to all modules when the first method
 * tracing filter is applied.
 * <p>
 * A malicious user could craft bytecode that invoke these methods with an
 * invalid method ID, resulting in an event where the method field is
 * incorrect or {@code null}. This is considered acceptable.
 */
public final class MethodTracer {

    private MethodTracer() {
    }

    public static long timestamp() {
        return JVM.counterTime();
    }

    public static void traceObjectInit(long startTime, long methodId) {
        long endTime = JVM.counterTime();
        long duration = endTime - startTime;
        if (MethodTraceEvent.shouldCommit(duration) && JVM.getEventWriter() != null) {
            MethodTraceEvent.commit(startTime, duration, methodId);
        }
    }

    public static void timingObjectInit(long startTime, long methodId) {
        long endTime = JVM.counterTime();
        long duration = endTime - startTime;
        if (MethodTimingEvent.enabled()) {
            PlatformTracer.addObjectTiming(duration);
        }
    }

    public static void traceTimingObjectInit(long startTime, long methodId) {
        long endTime = JVM.counterTime();
        long duration = endTime - startTime;
        if (MethodTraceEvent.shouldCommit(duration) && JVM.getEventWriter() != null) {
            MethodTraceEvent.commit(startTime, duration, methodId);
        }
        if (MethodTimingEvent.enabled()) {
            PlatformTracer.addObjectTiming(duration);
        }
    }

    public static void trace(long startTime, long methodId) {
        long endTime = JVM.counterTime();
        long duration = endTime - startTime;
        if (MethodTraceEvent.shouldCommit(duration)) {
            MethodTraceEvent.commit(startTime, duration, methodId);
        }
    }

    public static void timing(long startTime, long methodId) {
        long endTime = JVM.counterTime();
        long duration = endTime - startTime;
        if (MethodTimingEvent.enabled()) {
            PlatformTracer.addTiming(methodId, duration);
        }
    }

    public static void traceTiming(long startTime, long methodId) {
        long endTime = JVM.counterTime();
        long duration = endTime - startTime;
        if (MethodTimingEvent.enabled()) {
            PlatformTracer.addTiming(methodId, duration);
        }
        if (MethodTraceEvent.shouldCommit(duration)) {
            MethodTraceEvent.commit(startTime, duration, methodId);
        }
    }
}
