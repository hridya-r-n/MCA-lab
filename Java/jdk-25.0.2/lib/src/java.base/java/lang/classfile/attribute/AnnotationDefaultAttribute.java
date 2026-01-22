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

import java.lang.classfile.AnnotationValue;
import java.lang.classfile.Attribute;
import java.lang.classfile.AttributeMapper;
import java.lang.classfile.AttributeMapper.AttributeStability;
import java.lang.classfile.Attributes;
import java.lang.classfile.ClassFile;
import java.lang.classfile.MethodElement;
import java.lang.reflect.Method;

import jdk.internal.classfile.impl.BoundAttribute;
import jdk.internal.classfile.impl.UnboundAttribute;

/**
 * Models the {@link Attributes#annotationDefault() AnnotationDefault} attribute
 * (JVMS {@jvms 4.7.22}), which records the default value (JLS {@jls 9.6.2}) for
 * the annotation interface element defined by this method.
 * <p>
 * This attribute only appears on methods, and does not permit {@linkplain
 * AttributeMapper#allowMultiple multiple instances} in a method.  It has a
 * data dependency on the {@linkplain AttributeStability#CP_REFS constant pool}.
 * <p>
 * This attribute was introduced in the Java SE Platform version 5.0, major
 * version {@value ClassFile#JAVA_5_VERSION}.
 *
 * @see Attributes#annotationDefault()
 * @jls 9.6.2 Defaults for Annotation Interface Elements
 * @jvms 4.7.22 The {@code AnnotationDefault} Attribute
 * @since 24
 */
public sealed interface AnnotationDefaultAttribute
        extends Attribute<AnnotationDefaultAttribute>, MethodElement
        permits BoundAttribute.BoundAnnotationDefaultAttr,
                UnboundAttribute.UnboundAnnotationDefaultAttribute {

    /**
     * {@return the default value of the annotation interface element defined by
     * the enclosing method}
     *
     * @see Method#getDefaultValue()
     */
    AnnotationValue defaultValue();

    /**
     * {@return an {@code AnnotationDefault} attribute}
     * @param annotationDefault the default value of the annotation interface element
     */
    static AnnotationDefaultAttribute of(AnnotationValue annotationDefault) {
        return new UnboundAttribute.UnboundAnnotationDefaultAttribute(annotationDefault);
    }
}
