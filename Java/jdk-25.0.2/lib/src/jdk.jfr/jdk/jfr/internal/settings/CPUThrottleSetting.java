/*
 * Copyright (c) 2020, 2024, Oracle and/or its affiliates. All rights reserved.
 * Copyright (c) 2020, Datadog, Inc. All rights reserved.
 * Copyright (c) 2025 SAP SE. All rights reserved.
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
import jdk.jfr.SettingControl;
import jdk.jfr.Label;
import jdk.jfr.MetadataDefinition;
import jdk.jfr.Name;
import jdk.jfr.internal.PlatformEventType;
import jdk.jfr.internal.Type;
import jdk.jfr.internal.util.TimespanRate;
import jdk.jfr.internal.util.Utils;

@MetadataDefinition
@Label("CPUThrottleSetting")
@Description("Upper bounds the emission rate for CPU time samples")
@Name(Type.SETTINGS_PREFIX + "Rate")
public final class CPUThrottleSetting extends SettingControl {
    public static final String DEFAULT_VALUE = "0/s";
    private final PlatformEventType eventType;
    private String value = DEFAULT_VALUE;

    public CPUThrottleSetting(PlatformEventType eventType) {
       this.eventType = Objects.requireNonNull(eventType);
    }

    @Override
    public String combine(Set<String> values) {
        TimespanRate highestRate = null;
        for (String value : values) {
            TimespanRate rate = TimespanRate.of(value);
            if (rate != null) {
                if (highestRate == null) {
                    highestRate = rate;
                } else {
                    highestRate = TimespanRate.selectHigherResolution(highestRate, rate);
                }
            }
        }
        return Objects.requireNonNullElse(highestRate.toString(), DEFAULT_VALUE);
    }

    @Override
    public void setValue(String value) {
        TimespanRate rate = TimespanRate.of(value);
        if (rate != null) {
            eventType.setCPUThrottle(rate);
            this.value = value;
        }
    }

    @Override
    public String getValue() {
        return value;
    }
}

