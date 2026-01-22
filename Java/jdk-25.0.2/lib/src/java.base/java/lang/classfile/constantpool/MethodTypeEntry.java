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

import java.lang.constant.ConstantDesc;
import java.lang.constant.MethodTypeDesc;

import jdk.internal.classfile.impl.AbstractPoolEntry;

/**
 * Models a {@code CONSTANT_MethodType_info} structure, or a symbolic reference
 * to a method type, in the constant pool of a {@code class} file.
 * <p>
 * The use of a {@code MethodTypeEntry} is modeled by a {@link MethodTypeDesc}.
 * Conversions are through {@link ConstantPoolBuilder#methodTypeEntry(MethodTypeDesc)}
 * and {@link #asSymbol()}.
 * <p>
 * A method type entry is composite:
 * {@snippet lang=text :
 * // @link substring="MethodTypeEntry" target="ConstantPoolBuilder#methodTypeEntry(Utf8Entry)" :
 * MethodTypeEntry(Utf8Entry descriptor) // @link substring="descriptor" target="#descriptor()"
 * }
 * where {@code descriptor} is a {@linkplain #asSymbol() method descriptor}
 * string.
 *
 * @jvms 4.4.9 The {@code CONSTANT_MethodType_info} Structure
 * @since 24
 */
public sealed interface MethodTypeEntry
        extends LoadableConstantEntry
        permits AbstractPoolEntry.MethodTypeEntryImpl {

    /**
     * {@inheritDoc}
     * <p>
     * This is equivalent to {@link #asSymbol() asSymbol()}.
     */
    @Override
    default ConstantDesc constantValue() {
        return asSymbol();
    }

    /**
     * {@return the {@linkplain #asSymbol() method descriptor} string}
     */
    Utf8Entry descriptor();

    /**
     * {@return a symbolic descriptor for the {@linkplain #descriptor() method
     * type}}
     *
     * @apiNote
     * If only symbol equivalence is desired, {@link #matches(MethodTypeDesc)
     * matches} should be used.  It requires reduced parsing and can
     * improve {@code class} file reading performance.
     */
    MethodTypeDesc asSymbol();

    /**
     * {@return whether this entry describes the given method type}
     *
     * @param desc the method type descriptor
     * @since 25
     */
    boolean matches(MethodTypeDesc desc);
}
