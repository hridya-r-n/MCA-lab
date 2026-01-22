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
package jdk.jfr.internal.util;

import java.util.ArrayList;
import java.util.List;

import jdk.jfr.internal.RemoveFields;
import jdk.jfr.internal.util.Bytecode.FieldDesc;
/**
 * Class that describes fields that was not directly named
 * in the event definition.
 */
public final class ImplicitFields {
    public static final String START_TIME = "startTime";
    public static final String DURATION = "duration";
    public static final String EVENT_THREAD = "eventThread";
    public static final String STACK_TRACE = "stackTrace";
    public static final FieldDesc FIELD_DURATION = FieldDesc.of(long.class, DURATION);
    public static final FieldDesc FIELD_START_TIME = FieldDesc.of(long.class, START_TIME);

    private final List<String> fields = new ArrayList<>(4);

    public ImplicitFields(Class<?> eventClass) {
        fields.add(START_TIME);
        fields.add(DURATION);
        fields.add(STACK_TRACE);
        fields.add(EVENT_THREAD);
        for (Class<?> c = eventClass; !Utils.isEventBaseClass(c); c = c.getSuperclass()) {
            RemoveFields rf = c.getAnnotation(RemoveFields.class);
            if (rf != null) {
                for (String value : rf.value()) {
                    fields.remove(value);
                }
            }
        }
    }
    public void removeFields(String... fieldNames) {
        for (String fieldName : fieldNames) {
            fields.remove(fieldName);
        }
    }

    public boolean hasDuration() {
        return fields.contains(DURATION);
    }

    public boolean hasEventThread() {
        return fields.contains(EVENT_THREAD);
    }

    public boolean hasStackTrace() {
        return fields.contains(STACK_TRACE);
    }
}
