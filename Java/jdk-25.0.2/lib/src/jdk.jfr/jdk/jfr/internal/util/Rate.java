/*
 * Copyright (c) 2024, Oracle and/or its affiliates. All rights reserved.
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
package jdk.jfr.internal.util;

public record Rate(long amount, TimespanUnit unit) {

    public static Rate of(String text) {
        String[] splitted = text.split("/");
        if (splitted.length != 2) {
            return null;
        }
        String value = splitted[0].strip();
        String unit = splitted[1].strip();
        TimespanUnit tu = TimespanUnit.fromText(unit);
        if (unit == null) {
            return null;
        }
        try {
            long v = Long.parseLong(value);
            if (v >= 0) {
                return new Rate(v, tu);
            }
        } catch (NumberFormatException nfe) {
            // Ignore
        }
        return null;
    }

    public boolean isHigher(Rate that) {
        return this.inNanos() > that.inNanos();
    }

    private double inNanos() {
        return (double) amount / unit.nanos;
    }

    public double perSecond() {
        return inNanos() * 1_000_000_000.0;
    }
}
