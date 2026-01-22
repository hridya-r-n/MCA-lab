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

import jdk.internal.classfile.impl.SuperclassImpl;

/**
 * Models the superclass (JVMS {@jvms 4.1}) of a class.  A {@code Superclass}
 * appears at most once in a {@link ClassModel}: it must be absent for
 * {@linkplain ClassModel#isModuleInfo() module descriptors} or the {@link
 * Object} class, and must be present otherwise.  A {@link ClassBuilder} sets
 * the {@link Object} class as the superclass if the superclass is not supplied
 * and the class to build is required to have a superclass.
 * <p>
 * All {@linkplain ClassFile#ACC_INTERFACE interfaces} have {@link Object} as
 * their superclass.
 *
 * @see ClassModel#superclass()
 * @see ClassBuilder#withSuperclass
 * @jvms 4.1 The {@code ClassFile} Structure
 * @since 24
 */
public sealed interface Superclass
        extends ClassElement
        permits SuperclassImpl {

    /** {@return the superclass} */
    ClassEntry superclassEntry();

    /**
     * {@return a {@linkplain Superclass} element}
     *
     * @param superclassEntry the superclass
     */
    static Superclass of(ClassEntry superclassEntry) {
        return new SuperclassImpl(superclassEntry);
    }
}
