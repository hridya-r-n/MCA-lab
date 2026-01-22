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

import java.lang.classfile.*;
import java.lang.classfile.AttributeMapper.AttributeStability;

import jdk.internal.classfile.impl.BoundAttribute;

/**
 * Models an unknown attribute read from a {@code class} file.  An attribute is
 * unknown if it is not recognized by one of the mappers in {@link Attributes}
 * and is not recognized by the {@link ClassFile.AttributesProcessingOption}.
 * <p>
 * An unknown attribute may appear anywhere where an attribute may appear, and
 * has an {@linkplain AttributeStability#UNKNOWN unknown} data dependency.
 *
 * @see CustomAttribute
 * @since 24
 */
public sealed interface UnknownAttribute
        extends Attribute<UnknownAttribute>,
                ClassElement, MethodElement, FieldElement, CodeElement
        permits BoundAttribute.BoundUnknownAttribute {

    /**
     * {@return the uninterpreted contents of the attribute payload}
     */
    byte[] contents();
}
