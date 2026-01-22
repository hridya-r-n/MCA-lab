/*
 * Copyright (c) 2023, 2025, Oracle and/or its affiliates. All rights reserved.
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

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import jdk.jfr.Label;
import jdk.jfr.MetadataDefinition;
import jdk.jfr.Name;
import jdk.jfr.SettingControl;
import jdk.jfr.internal.PlatformEventType;
import jdk.jfr.internal.Type;

@MetadataDefinition
@Label("Level")
@Name(Type.SETTINGS_PREFIX + "Level")
public final class LevelSetting extends SettingControl {
    private final PlatformEventType eventType;
    private final List<String> levels;
    private String value;

    public LevelSetting(PlatformEventType eventType, String[] levels) {
        this.eventType = Objects.requireNonNull(eventType);
        this.levels = Arrays.asList(Objects.requireNonNull(levels));
        this.value = levels[0];
    }

    @Override
    public String combine(Set<String> values) {
        int maxIndex = 0; // index 0 contains the default value
        for (String value : values) {
            maxIndex = Math.max(maxIndex, levels.indexOf(value));
        }
        return levels.get(maxIndex);
    }

    @Override
    public void setValue(String value) {
        int index = levels.indexOf(value);
        if (index != -1) {
            this.eventType.setLevel(index);
            this.value = value;
        }
    }

    @Override
    public String getValue() {
        return value;
    }
}
