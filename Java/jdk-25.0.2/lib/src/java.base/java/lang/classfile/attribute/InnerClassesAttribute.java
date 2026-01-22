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

package java.lang.classfile.attribute;

import java.lang.classfile.Attribute;
import java.lang.classfile.AttributeMapper;
import java.lang.classfile.AttributeMapper.AttributeStability;
import java.lang.classfile.Attributes;
import java.lang.classfile.ClassElement;
import java.lang.classfile.ClassFile;
import java.util.List;

import jdk.internal.classfile.impl.BoundAttribute;
import jdk.internal.classfile.impl.UnboundAttribute;

/**
 * Models the {@link Attributes#innerClasses() InnerClasses} attribute (JVMS
 * {@jvms 4.7.6}), which records which classes referenced by this {@code class}
 * file are nested classes.
 * <p>
 * This attribute only appears on classes, and does not permit {@linkplain
 * AttributeMapper#allowMultiple multiple instances} in a class.  It has a
 * data dependency on the {@linkplain AttributeStability#CP_REFS constant pool}.
 * <p>
 * The attribute was introduced in the Java SE Platform version 1.1, major
 * version {@value ClassFile#JAVA_1_VERSION}.
 *
 * @see Attributes#innerClasses()
 * @jvms 4.7.6 The {@code InnerClasses} Attribute
 * @since 24
 */
public sealed interface InnerClassesAttribute
        extends Attribute<InnerClassesAttribute>, ClassElement
        permits BoundAttribute.BoundInnerClassesAttribute,
                UnboundAttribute.UnboundInnerClassesAttribute {

    /**
     * {@return the nested classes used by this {@code class} file}
     */
    List<InnerClassInfo> classes();

    /**
     * {@return an {@code InnerClasses} attribute}
     * @param innerClasses descriptions of the nested classes
     */
    static InnerClassesAttribute of(List<InnerClassInfo> innerClasses) {
        return new UnboundAttribute.UnboundInnerClassesAttribute(innerClasses);
    }

    /**
     * {@return an {@code InnerClasses} attribute}
     * @param innerClasses descriptions of the nested classes
     */
    static InnerClassesAttribute of(InnerClassInfo... innerClasses) {
        return new UnboundAttribute.UnboundInnerClassesAttribute(List.of(innerClasses));
    }
}
