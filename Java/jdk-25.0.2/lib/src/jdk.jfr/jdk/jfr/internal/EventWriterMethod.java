/*
 * Copyright (c) 2016, 2023, Oracle and/or its affiliates. All rights reserved.
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

package jdk.jfr.internal;

import jdk.jfr.internal.util.Bytecode.FieldDesc;
import jdk.jfr.internal.util.Bytecode.MethodDesc;
import jdk.jfr.internal.util.ImplicitFields;

public enum EventWriterMethod {

     BEGIN_EVENT("beginEvent", "(Ljdk/jfr/internal/event/EventConfiguration;J)Z", "???"),
     END_EVENT("endEvent", "()Z", "???"),
     PUT_BYTE("putByte", "(B)V", "B"),
     PUT_SHORT("putShort", "(S)V", "S"),
     PUT_INT("putInt", "(I)V", "I"),
     PUT_LONG("putLong", "(J)V", "J"),
     PUT_FLOAT("putFloat", "(F)V", "F"),
     PUT_DOUBLE("putDouble", "(D)V", "D"),
     PUT_CHAR("putChar", "(C)V", "C"),
     PUT_BOOLEAN("putBoolean", "(Z)V", "Z"),
     PUT_THREAD("putThread", "(Ljava/lang/Thread;)V", "Ljava/lang/Thread;"),
     PUT_CLASS("putClass", "(Ljava/lang/Class;)V", "Ljava/lang/Class;"),
     PUT_STRING("putString", "(Ljava/lang/String;)V", "Ljava/lang/String;"),
     PUT_EVENT_THREAD("putEventThread", "()V", "???"),
     PUT_STACK_TRACE("putStackTrace", "()V", "???");

    final MethodDesc method;
    final String fieldType;

    EventWriterMethod(String methodName, String paramType, String fieldType) {
        this.fieldType = fieldType;
        this.method = MethodDesc.of(methodName, paramType);
    }

    public MethodDesc method() {
        return method;
    }

    /**
     * Return method in {@link EventWriter} class to use when writing event of
     * a certain type.
     *
     * @param v field info
     *
     * @return the method
     */
    public static EventWriterMethod lookupMethod(FieldDesc field) {
        // event thread
        if (field.name().equals(ImplicitFields.EVENT_THREAD)) {
            return EventWriterMethod.PUT_EVENT_THREAD;
        }
        for (EventWriterMethod m : EventWriterMethod.values()) {
            if (field.type().descriptorString().equals(m.fieldType)) {
                return m;
            }
        }
        throw new Error("Unknown field type " + field.type());
    }
}
