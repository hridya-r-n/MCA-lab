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

import java.lang.constant.ModuleDesc;

import jdk.internal.classfile.impl.AbstractPoolEntry;

/**
 * Models a {@code CONSTANT_Module_info} structure, denoting a module, in the
 * constant pool of a {@code class} file.
 * <p>
 * The use of a {@code ModuleEntry} is modeled by a {@link ModuleDesc}.
 * Conversions are through {@link ConstantPoolBuilder#moduleEntry(ModuleDesc)}
 * and {@link #asSymbol()}.
 * <p>
 * A module entry is composite:
 * {@snippet lang=text :
 * // @link substring="ModuleEntry" target="ConstantPoolBuilder#moduleEntry(Utf8Entry)" :
 * ModuleEntry(Utf8Entry name) // @link substring="name" target="#name()"
 * }
 * where {@code name} is a {@linkplain #asSymbol() module name}.
 *
 * @jvms 4.4.11 The {@code CONSTANT_Module_info} Structure
 * @since 24
 */
public sealed interface ModuleEntry extends PoolEntry
        permits AbstractPoolEntry.ModuleEntryImpl {
    /**
     * {@return the name of the {@linkplain #asSymbol() module}}
     */
    Utf8Entry name();

    /**
     * {@return a symbolic descriptor for the {@linkplain #name() module name}}
     *
     * @apiNote
     * If only symbol equivalence is desired, {@link #matches(ModuleDesc)
     * matches} should be used.  It requires reduced parsing and can
     * improve {@code class} file reading performance.
     */
    ModuleDesc asSymbol();

    /**
     * {@return whether this entry describes the given module}
     *
     * @param desc the module descriptor
     * @since 25
     */
    boolean matches(ModuleDesc desc);
}
