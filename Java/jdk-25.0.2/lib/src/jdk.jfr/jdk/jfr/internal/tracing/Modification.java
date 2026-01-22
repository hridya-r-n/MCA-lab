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

/**
 * Class that holds the type of instrumentation that can be applied to a method.
 */
public record Modification(boolean timing, boolean tracing) {
    public static final Modification TIMING = new Modification(true, false);
    public static final Modification TRACING = new Modification(false, true);
    public static final Modification NONE = new Modification(false, false);

    static Modification valueOf(int traceType) {
        return new Modification((traceType & 1) != 0, (traceType & 2) != 0);
    }

    int toInt() {
        return (timing ? 1 : 0) + (tracing ? 2 : 0);
    }
}
