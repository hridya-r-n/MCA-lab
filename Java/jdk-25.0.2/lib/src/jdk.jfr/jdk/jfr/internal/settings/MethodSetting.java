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

import java.util.List;

import jdk.jfr.Description;
import jdk.jfr.Label;
import jdk.jfr.MetadataDefinition;
import jdk.jfr.Name;
import jdk.jfr.internal.PlatformEventType;
import jdk.jfr.internal.Type;
import jdk.jfr.internal.tracing.Modification;
import jdk.jfr.internal.tracing.Filter;
import jdk.jfr.internal.tracing.PlatformTracer;

@MetadataDefinition
@Label("Filter")
@Description("Methods to be filtered")
@Name(Type.SETTINGS_PREFIX + "Filter")
public final class MethodSetting extends FilterSetting {
    private final Modification modification;
    private volatile static boolean initialized;

    public MethodSetting(PlatformEventType eventType, Modification modification, String defaultValue) {
        super(eventType, defaultValue);
        this.modification = modification;
    }

    @Override
    public boolean isValid(String text) {
        return Filter.isValid(text);
    }

    @Override
    protected void apply(PlatformEventType eventType, List<String> filters) {
        ensureInitialized();
        PlatformTracer.setFilters(modification, filters);
    }

    // Expected to be called when holding external lock, so no extra
    // synchronization is required here.
    private static void ensureInitialized() {
        if (!initialized) {
            PlatformTracer.initialize();
            initialized = true;
        }
    }

    public static boolean isInitialized() {
        return initialized;
    }
}
