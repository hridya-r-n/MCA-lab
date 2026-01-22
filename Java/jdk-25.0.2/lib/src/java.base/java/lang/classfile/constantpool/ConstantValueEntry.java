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

import java.lang.classfile.Attributes;
import java.lang.constant.ConstantDesc;

/**
 * Marker interface for constant pool entries that can represent constant values
 * in the {@link Attributes#constantValue() ConstantValue} attribute.
 *
 * @see ConstantPoolBuilder#constantValueEntry
 *      ConstantPoolBuilder::constantValueEntry
 * @sealedGraph
 * @since 24
 */
public sealed interface ConstantValueEntry extends LoadableConstantEntry
        permits DoubleEntry, FloatEntry, IntegerEntry, LongEntry, StringEntry {

    /**
     * {@return the constant value}  The constant value will be an {@link
     * Integer}, {@link Long}, {@link Float}, {@link Double}, or {@link String}.
     *
     * @see ConstantPoolBuilder#constantValueEntry(ConstantDesc)
     */
    @Override
    ConstantDesc constantValue();
}
