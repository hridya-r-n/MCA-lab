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

import java.io.DataInput;
import java.lang.classfile.Attribute;
import java.lang.classfile.AttributeMapper;
import java.lang.classfile.AttributeMapper.AttributeStability;
import java.lang.classfile.Attributes;
import java.lang.classfile.ClassElement;
import java.lang.classfile.ClassFile;

import jdk.internal.classfile.impl.BoundAttribute;
import jdk.internal.classfile.impl.UnboundAttribute;

/**
 * Models the {@link Attributes#sourceDebugExtension() SourceDebugExtension}
 * attribute (JVMS {@jvms 4.7.11}), which stores arbitrary {@linkplain
 * DataInput##modified-utf-8 modified UTF-8} data.
 * <p>
 * This attribute only appears on classes, and does not permit {@linkplain
 * AttributeMapper#allowMultiple multiple instances} in a class.  It has
 * {@linkplain AttributeStability#STATELESS no data dependency}.
 * <p>
 * The attribute was introduced in the Java SE Platform version 5.0, major
 * version {@value ClassFile#JAVA_5_VERSION}.
 *
 * @see Attributes#sourceDebugExtension()
 * @jvms 4.7.11 The {@code SourceDebugExtension} Attribute
 * @since 24
 */
public sealed interface SourceDebugExtensionAttribute
        extends Attribute<SourceDebugExtensionAttribute>, ClassElement
        permits BoundAttribute.BoundSourceDebugExtensionAttribute, UnboundAttribute.UnboundSourceDebugExtensionAttribute {

    /**
     * {@return the debug extension payload}  The payload may denote a string
     * longer than that which can be represented with a {@link String}.
     */
    byte[] contents();

    /**
     * {@return a {@code SourceDebugExtension} attribute}
     * @param contents the extension contents
     */
    static SourceDebugExtensionAttribute of(byte[] contents) {
        return new UnboundAttribute.UnboundSourceDebugExtensionAttribute(contents);
    }
}
