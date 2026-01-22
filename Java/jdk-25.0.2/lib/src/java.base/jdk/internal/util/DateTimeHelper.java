/*
 * Copyright (c) 2025, Oracle and/or its affiliates. All rights reserved.
 * Copyright (c) 2025, Alibaba Group Holding Limited. All Rights Reserved.
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
package jdk.internal.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * Helper for java.time
 */
public final class DateTimeHelper {
    private DateTimeHelper() {
    }
    /**
     * Prints the toString result to the given buf, avoiding extra string allocations.
     */
    public static void formatTo(StringBuilder buf, LocalDateTime dateTime) {
        DateTimeHelper.formatTo(buf, dateTime.toLocalDate());
        buf.append('T');
        DateTimeHelper.formatTo(buf, dateTime.toLocalTime());
    }

    /**
     * Prints the toString result to the given buf, avoiding extra string allocations.
     * Requires extra capacity of 10 to avoid StringBuilder reallocation.
     */
    public static void formatTo(StringBuilder buf, LocalDate date) {
        int year  = date.getYear(),
            month = date.getMonthValue(),
            day   = date.getDayOfMonth();
        int absYear = Math.abs(year);
        if (absYear < 1000) {
            if (year < 0) {
                buf.append('-');
            }
            buf.repeat('0', absYear < 10 ? 3 : absYear < 100 ? 2 : 1);
            buf.append(absYear);
        } else {
            if (year > 9999) {
                buf.append('+');
            }
            buf.append(year);
        }
        buf.append(month < 10 ? "-0" : "-").append(month)
           .append(day < 10 ? "-0" : "-").append(day);
    }

    /**
     * Prints the toString result to the given buf, avoiding extra string allocations.
     * Requires extra capacity of 18 to avoid StringBuilder reallocation.
     */
    public static void formatTo(StringBuilder buf, LocalTime time) {
        int hour   = time.getHour(),
            minute = time.getMinute(),
            second = time.getSecond(),
            nano   = time.getNano();
        buf.append(hour < 10 ? "0" : "").append(hour)
           .append(minute < 10 ? ":0" : ":").append(minute);
        if ((second | nano) > 0) {
            buf.append(second < 10 ? ":0" : ":").append(second);
            if (nano > 0) {
                buf.append('.');
                int zeros = 9 - DecimalDigits.stringSize(nano);
                if (zeros > 0) {
                    buf.repeat('0', zeros);
                }
                int digits;
                if (nano % 1_000_000 == 0) {
                    digits = nano / 1_000_000;
                } else if (nano % 1000 == 0) {
                    digits = nano / 1000;
                } else {
                    digits = nano;
                }
                buf.append(digits);
            }
        }
    }
}
