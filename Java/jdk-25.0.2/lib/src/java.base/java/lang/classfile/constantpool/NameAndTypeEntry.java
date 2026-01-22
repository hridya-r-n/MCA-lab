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

import java.lang.constant.ClassDesc;
import java.lang.constant.MethodTypeDesc;
import java.lang.invoke.TypeDescriptor;

import jdk.internal.classfile.impl.AbstractPoolEntry;

/**
 * Models a {@code CONSTANT_NameAndType_info} structure, representing a field or
 * method, in the constant pool of a {@code class} file.
 * <p>
 * The use of a {@code NameAndTypeEntry} is symbolically represented as a
 * {@code String name}, and a {@link ConstantPoolBuilder#nameAndTypeEntry(String,
 * ClassDesc) ClassDesc} or a {@link ConstantPoolBuilder#nameAndTypeEntry(String,
 * MethodTypeDesc) MethodTypeDesc} {@code type}, depending on where this {@code
 * NameAndTypeEntry} appears.  The accessors to the symbolic descriptors for the
 * {@code type} is defined on a per-use-site basis, such as {@link
 * FieldRefEntry#typeSymbol()} returning a {@code ClassDesc}, and {@link
 * MethodRefEntry#typeSymbol()} returning a {@code MethodTypeDesc}.
 * <p>
 * A name and type entry is composite:
 * {@snippet lang=text :
 * NameAndTypeEntry( // @link substring="NameAndTypeEntry" target="ConstantPoolBuilder#nameAndTypeEntry(Utf8Entry, Utf8Entry)"
 *     Utf8Entry name, // @link substring="name" target="#name()"
 *     Utf8Entry type  // @link substring="type" target="#type()"
 * )
 * }
 * where {@code name} is an unqualified name, and {@code type} is a field or
 * method descriptor string.
 *
 * @jvms 4.4.6 The {@code CONSTANT_NameAndType_info} Structure
 * @since 24
 */
public sealed interface NameAndTypeEntry extends PoolEntry
        permits AbstractPoolEntry.NameAndTypeEntryImpl {

    /**
     * {@return the field or method name}
     */
    Utf8Entry name();

    /**
     * {@return the field or method {@linkplain TypeDescriptor descriptor}
     * string}  It is a method descriptor strings if it starts with {@code (}.
     * Otherwise, it is a field descriptor string, and must start with one of
     * the {@code BCDFIJSZL[} characters.
     */
    Utf8Entry type();
}
