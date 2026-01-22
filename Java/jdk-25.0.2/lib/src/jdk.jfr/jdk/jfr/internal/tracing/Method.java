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

import jdk.jfr.internal.LogLevel;
import jdk.jfr.internal.LogTag;
import jdk.jfr.internal.Logger;

/**
 * Class that holds information about an instrumented method.
 */
record Method(long methodId, Modification modification, String name) {
    @Override
    public String toString() {
        return name + (modification.timing() ? " +timing" : " -timing") + (modification.tracing() ? " +tracing" : " -tracing") + " (Method ID: " + String.format("0x%08X)", methodId);
    }

    public long classId() {
        return methodId() >> 16;
    }

    public boolean isTiming() {
        return modification.timing();
    }

    public void log(String msg) {
        if (Logger.shouldLog(LogTag.JFR_METHODTRACE, LogLevel.DEBUG)) {
            Logger.log(LogTag.JFR_METHODTRACE, LogLevel.DEBUG, msg + " for " + this);
        }
    }
}
