/*
 * Copyright (c) 2025 SAP SE. All rights reserved.
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

import jdk.jfr.internal.settings.CPUThrottleSetting;

/**
 * A rate or fixed period, see {@link jdk.jfr.internal.Rate}
 */
public record TimespanRate(double rate, long periodNanos, boolean isRate) {

    public static final TimespanRate OFF = new TimespanRate(0, 0, false);

    /**
     * Parses the rate string. Supports
     *
     * <ul>
     *  <li>off</li>
     *  <li>time value like "1ms"</li>
     *  <li>rate value like "10/s"</li>
     * </ul>
     */
    public static TimespanRate of(String text) {
        if (text.equals("off")) {
            return OFF;
        }
        boolean isPeriod = !text.contains("/");
        if (isPeriod) {
            var period = ValueParser.parseTimespanWithInfinity(text, Long.MAX_VALUE);
            if (period == Long.MAX_VALUE) {
                return null;
            }
            if (period == 0) {
                return OFF;
            }
            return new TimespanRate(0, period, false);
        }
        Rate r = Rate.of(text);
        if (r == null) {
            return null;
        }
        return new TimespanRate(r.perSecond(), 0, true);
    }

    public static TimespanRate selectHigherResolution(TimespanRate a, TimespanRate b) {
        if (a.isRate && b.isRate) {
            return a.rate() > b.rate() ? a : b;
        }
        if (!a.isRate && !b.isRate) {
            return a.periodNanos() < b.periodNanos() ? a : b;
        }
        if (a.isRate) {
            double bRate = Runtime.getRuntime().availableProcessors() * (1_000_000_000.0 / b.periodNanos());
            return new TimespanRate(Math.max(a.rate(), bRate), 0, true);
        }
        double aRate = Runtime.getRuntime().availableProcessors() * (1_000_000_000.0 / a.periodNanos());
        return new TimespanRate(Math.max(aRate, b.rate()), 0, true);
    }

    @Override
    public String toString() {
        if (isRate) {
            return toRateString();
        }
        return toPeriodString();
    }

    private String toRateString() {
        // idea: try to use the smallest unit possible where the rate is still an integer
        // start with seconds, then try minutes, hours, etc.
        assert isRate;
        if (rate == 0) {
            return "0/s";
        }
        for (TimespanUnit unit : TimespanUnit.values()) {
            double value = rate / unit.nanos * 1_000_000_000.0;
            if (value % 1 == 0) {
                return String.format("%d/%s", (long)value, unit.text);
            }
        }
        // fallback to days if no smaller unit is found
        return String.format("%d/%s", (long)(rate / TimespanUnit.DAYS.nanos * 1_000_000_000.0), TimespanUnit.DAYS.text);
    }

    private String toPeriodString() {
        assert !isRate;
        if (periodNanos == 0) {
            return "0ms";
        }
        return String.format("%dns", periodNanos);
    }
}
