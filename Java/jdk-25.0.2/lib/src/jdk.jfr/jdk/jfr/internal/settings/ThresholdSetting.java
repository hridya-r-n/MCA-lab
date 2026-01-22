/*
 * Copyright (c) 2016, 2025, Oracle and/or its affiliates. All rights reserved.
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

import static jdk.jfr.internal.util.ValueParser.MISSING;

import java.util.Objects;
import java.util.Set;

import jdk.jfr.Description;
import jdk.jfr.Label;
import jdk.jfr.MetadataDefinition;
import jdk.jfr.Name;
import jdk.jfr.SettingControl;
import jdk.jfr.Timespan;
import jdk.jfr.internal.PlatformEventType;
import jdk.jfr.internal.Type;
import jdk.jfr.internal.util.Utils;
import jdk.jfr.internal.util.ValueParser;

@MetadataDefinition
@Label("Threshold")
@Name(Type.SETTINGS_PREFIX + "Threshold")
@Description("Record event with duration above or equal to threshold")
@Timespan
public final class ThresholdSetting extends SettingControl {
    public static final String DEFAULT_VALUE = "0 ns";
    private static final long typeId = Type.getTypeId(ThresholdSetting.class);
    private final PlatformEventType eventType;
    private final String defaultValue;
    private String value;

    public ThresholdSetting(PlatformEventType eventType, String defaultValue) {
       this.eventType = Objects.requireNonNull(eventType);
       this.defaultValue = Utils.validTimespanInfinity(eventType, "Threshold", defaultValue, DEFAULT_VALUE);
       this.value = defaultValue;
    }

    @Override
    public String combine(Set<String> values) {
        Long min = null;
        String text = null;
        for (String value : values) {
            long nanos = ValueParser.parseTimespanWithInfinity(value, MISSING);
            if (nanos != MISSING) {
                if (min == null || nanos < min) {
                    text = value;
                    min = nanos;
                }
            }
        }
        return Objects.requireNonNullElse(text, defaultValue);
    }

    @Override
    public void setValue(String value) {
        long nanos = ValueParser.parseTimespanWithInfinity(value, MISSING);
        if (nanos != MISSING) {
            eventType.setThreshold(nanos);
            this.value = value;
        }
    }

    @Override
    public String getValue() {
        return value;
    }

    public static boolean isType(long typeId) {
        return ThresholdSetting.typeId == typeId;
    }
}
