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

import java.lang.classfile.AnnotationValue;
import java.lang.constant.ConstantDesc;

/**
 * Marker interface for constant pool entries that can represent constant values
 * associated with elements of annotations.  They are also the only entries that
 * do not refer to other constant pool entries.
 *
 * @apiNote
 * An annotation constant value entry alone is not sufficient to determine
 * the annotation constant; for example, an {@link IntegerEntry} of {@code 1}
 * can mean {@code true} in {@link AnnotationValue.OfBoolean} or {@code 1}
 * in {@link AnnotationValue.OfInt}.
 *
 * @see AnnotationValue.OfConstant
 * @jvms 4.7.16.1 The {@code element_value} structure
 * @sealedGraph
 * @since 24
 */
public sealed interface AnnotationConstantValueEntry extends PoolEntry
        permits DoubleEntry, FloatEntry, IntegerEntry, LongEntry, Utf8Entry {

    /**
     * {@return the constant value}  The constant value will be an {@link
     * Integer}, {@link Long}, {@link Float}, {@link Double} for the primitive
     * constants, or {@link String} for UTF8 constants.
     */
    ConstantDesc constantValue();
}
