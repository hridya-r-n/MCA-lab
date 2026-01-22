/*
 * Copyright (c) 2022, 2025, Oracle and/or its affiliates. All rights reserved.
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
package java.lang.classfile.constantpool;

import java.lang.classfile.TypeKind;

import jdk.internal.classfile.impl.AbstractPoolEntry;

/**
 * Models a {@code CONSTANT_Integer_info} structure, or an {@code int} constant,
 * in the constant pool of a {@code class} file.
 * <p>
 * The use of a {@code IntegerEntry} is modeled by an {@code int}.  Conversions
 * are through {@link ConstantPoolBuilder#intEntry(int)} and {@link #intValue()}.
 *
 * @see ConstantPoolBuilder#intEntry ConstantPoolBuilder::intEntry
 * @jvms 4.4.4 The {@code CONSTANT_Integer_info} and {@code CONSTANT_Float_info}
 *             Structures
 * @since 24
 */
public sealed interface IntegerEntry
        extends AnnotationConstantValueEntry, ConstantValueEntry
        permits AbstractPoolEntry.IntegerEntryImpl {

    /**
     * {@return the {@code int} value}
     *
     * @see ConstantPoolBuilder#intEntry(int)
     */
    int intValue();

    @Override
    default TypeKind typeKind() {
        return TypeKind.INT;
    }
}
