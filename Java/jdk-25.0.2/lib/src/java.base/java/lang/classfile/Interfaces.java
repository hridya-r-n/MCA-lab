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
package java.lang.classfile;

import java.lang.classfile.constantpool.ClassEntry;
import java.lang.constant.ClassDesc;
import java.util.Arrays;
import java.util.List;

import jdk.internal.classfile.impl.InterfacesImpl;
import jdk.internal.classfile.impl.Util;

/**
 * Models the interfaces (JVMS {@jvms 4.1}) of a class.  An {@code Interfaces}
 * appears at most once in a {@link ClassModel}: if it does not appear, the
 * class has no interfaces, which is equivalent to an {@code Interfaces} whose
 * {@link #interfaces()} returns an empty list.  A {@link ClassBuilder} sets
 * the interfaces to an empty list if the interfaces is not supplied.
 *
 * @see ClassModel#interfaces()
 * @see ClassBuilder#withInterfaces
 * @jvms 4.1 The {@code ClassFile} Structure
 * @since 24
 */
public sealed interface Interfaces
        extends ClassElement
        permits InterfacesImpl {

    /** {@return the interfaces of this class, may be empty} */
    List<ClassEntry> interfaces();

    /**
     * {@return an {@linkplain Interfaces} element}
     * @param interfaces the interfaces
     */
    static Interfaces of(List<ClassEntry> interfaces) {
        return new InterfacesImpl(interfaces);
    }

    /**
     * {@return an {@linkplain Interfaces} element}
     * @param interfaces the interfaces
     */
    static Interfaces of(ClassEntry... interfaces) {
        return of(List.of(interfaces));
    }

    /**
     * {@return an {@linkplain Interfaces} element}
     * @param interfaces the interfaces
     * @throws IllegalArgumentException if any of {@code interfaces} is primitive
     */
    static Interfaces ofSymbols(List<ClassDesc> interfaces) {
        return of(Util.entryList(interfaces));
    }

    /**
     * {@return an {@linkplain Interfaces} element}
     * @param interfaces the interfaces
     * @throws IllegalArgumentException if any of {@code interfaces} is primitive
     */
    static Interfaces ofSymbols(ClassDesc... interfaces) {
        return ofSymbols(Arrays.asList(interfaces));
    }
}
