/*
 * Copyright (c) 2020, 2025, Oracle and/or its affiliates. All rights reserved.
 * Copyright (c) 2020, Datadog, Inc. All rights reserved.
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

import static jdk.jfr.internal.util.TimespanUnit.SECONDS;
import static jdk.jfr.internal.util.TimespanUnit.MILLISECONDS;

import java.util.Objects;
import java.util.Set;

import jdk.jfr.Description;
import jdk.jfr.Label;
import jdk.jfr.MetadataDefinition;
import jdk.jfr.Name;
import jdk.jfr.SettingControl;
import jdk.jfr.internal.PlatformEventType;
import jdk.jfr.internal.Type;
import jdk.jfr.internal.util.Rate;
import jdk.jfr.internal.util.TimespanUnit;
import jdk.jfr.internal.util.Utils;

@MetadataDefinition
@Label("Throttle")
@Description("Throttles the emission rate for an event")
@Name(Type.SETTINGS_PREFIX + "Throttle")
public final class ThrottleSetting extends SettingControl {
    public static final String DEFAULT_VALUE = "off";
    private final PlatformEventType eventType;
    private final String defaultValue;
    private String value;

    public ThrottleSetting(PlatformEventType eventType, String defaultValue) {
        this.eventType = Objects.requireNonNull(eventType);
        this.defaultValue = validRate(defaultValue);
        this.value = defaultValue;
    }

    private String validRate(String defaultValue) {
        if (DEFAULT_VALUE.equals(defaultValue)) {
            return DEFAULT_VALUE; // Fast path to avoid parsing
        }
        if (Rate.of(defaultValue) == null) {
            Utils.warnInvalidAnnotation(eventType, "Throttle", defaultValue, DEFAULT_VALUE);
            return DEFAULT_VALUE;
        }
        return defaultValue;
    }

    @Override
    public String combine(Set<String> values) {
        Rate max = null;
        String text = null;
        for (String value : values) {
            Rate rate = Rate.of(value);
            if (rate != null) {
                if (max == null || rate.isHigher(max)) {
                    text = value;
                    max = rate;
                }
            }
        }
        return Objects.requireNonNullElse(text, defaultValue);
    }

    @Override
    public void setValue(String value) {
        if ("off".equals(value)) {
            eventType.setThrottle(-2, 1000);
            this.value = value;
            return;
        }

        Rate rate = Rate.of(value);
        if (rate != null) {
            long millis = 1000;
            long samples = rate.amount();
            TimespanUnit unit = rate.unit();
            // if unit is more than 1 s, set millis
            if (unit.nanos > SECONDS.nanos) {
                millis = unit.nanos / MILLISECONDS.nanos;
            }
            // if unit is less than 1 s, scale samples
            if (unit.nanos < SECONDS.nanos) {
                long perSecond = SECONDS.nanos / unit.nanos;
                samples = Utils.multiplyOverflow(samples, perSecond, Long.MAX_VALUE);
            }
            eventType.setThrottle(samples, millis);
            this.value = value;
        }
    }

    @Override
    public String getValue() {
        return value;
    }
}

