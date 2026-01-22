/*
 * Copyright (c) 2016, 2024, Oracle and/or its affiliates. All rights reserved.
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

import static java.util.concurrent.TimeUnit.DAYS;
import static java.util.concurrent.TimeUnit.HOURS;
import static java.util.concurrent.TimeUnit.MICROSECONDS;
import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.MINUTES;
import static java.util.concurrent.TimeUnit.SECONDS;

public final class ValueParser {
    public static final String INFINITY = "infinity";
    public static final long MISSING = Long.MIN_VALUE;

    public static long parseTimespanWithInfinity(String s, long defaultValue) {
        try {
            return parseTimespanWithInfinity(s);
        } catch (NumberFormatException nfe) {
            return defaultValue;
        }
    }

    public static long parseTimespanWithInfinity(String s) {
        if (INFINITY.equals(s)) {
            return Long.MAX_VALUE;
        }
        return parseTimespan(s);
    }

    public static long parseTimespan(String s) {
        for (TimespanUnit unit : TimespanUnit.values()) {
            String text = unit.text;
            if (s.endsWith(text)) {
                long value = Long.parseLong(s.substring(0, s.length() - text.length()).strip());
                return unit.toNanos(value);
            }
        }

        try {
            Long.parseLong(s);
        } catch (NumberFormatException nfe) {
            throw new NumberFormatException("'" + s + "' is not a valid timespan. Should be numeric value followed by a unit, i.e. 20 ms. Valid units are ns, us, s, m, h and d.");
        }
        // Only accept values with units
        throw new NumberFormatException("Timespan + '" + s + "' is missing unit. Valid units are ns, us, s, m, h and d.");
    }
}
