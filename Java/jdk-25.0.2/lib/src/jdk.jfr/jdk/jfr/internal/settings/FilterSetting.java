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
package jdk.jfr.internal.settings;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.StringJoiner;
import java.util.TreeSet;

import jdk.jfr.SettingControl;
import jdk.jfr.internal.PlatformEventType;

/**
 * Base class for settings that consists of semicolon-separated filters.
 */
public abstract class FilterSetting extends SettingControl {
    private final List<String> activeFilters = new ArrayList<>();
    private final PlatformEventType eventType;
    private final String defaultValue;
    private String value;

    public FilterSetting(PlatformEventType eventType, String defaultValue) {
        this.eventType = Objects.requireNonNull(eventType);
        this.defaultValue = defaultValue;
    }

    protected abstract void apply(PlatformEventType eventType, List<String> text);

    protected abstract boolean isValid(String text);

    @Override
    public final String combine(Set<String> settingValues) {
        List<String> filters = normalize(settingValues);
        if (!filters.isEmpty()) {
            return format(filters);
        }
        return defaultValue;
    }

    @Override
    public final void setValue(String settingValue) {
        List<String> filters = normalize(Set.of(settingValue));
        if (activeFilters.equals(filters)) {
            return;
        }
        apply(eventType, filters);
        this.value = settingValue;
        this.activeFilters.clear();
        this.activeFilters.addAll(filters);
    }

    @Override
    public final String getValue() {
        return value;
    }

    // Split, trim, sort and remove duplicates filters.
    private List<String> normalize(Set<String> settingValues) {
        Set<String> filters = new TreeSet<>();
        for (String value : settingValues) {
            for (String filter : value.split(";")) {
                filter = filter.strip();
                if (!filter.isEmpty() && !filter.isBlank() && isValid(filter)) {
                    filters.add(filter);
                }
            }
        }
        return new ArrayList<>(filters);
    }

    private static String format(List<String> filters) {
        StringJoiner sj = new StringJoiner(";");
        filters.forEach(sj::add);
        return sj.toString();
    }
}
