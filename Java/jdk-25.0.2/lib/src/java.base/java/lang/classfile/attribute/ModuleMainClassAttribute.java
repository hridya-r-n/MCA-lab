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
import java.lang.classfile.ClassModel;
import java.lang.classfile.constantpool.ClassEntry;
import java.lang.constant.ClassDesc;
import java.lang.module.ModuleDescriptor;

import jdk.internal.classfile.impl.BoundAttribute;
import jdk.internal.classfile.impl.TemporaryConstantPool;
import jdk.internal.classfile.impl.UnboundAttribute;

/**
 * Models the {@link Attributes#moduleMainClass() ModuleMainClass} attribute
 * (JVMS {@jvms 4.7.27}), which appears on classes that {@linkplain
 * ClassModel#isModuleInfo() represent} module descriptors to indicate the main
 * class of the module.
 * <p>
 * This attribute only appears on classes, and does not permit {@linkplain
 * AttributeMapper#allowMultiple multiple instances} in a class.  It has a
 * data dependency on the {@linkplain AttributeStability#CP_REFS constant pool}.
 * <p>
 * The attribute was introduced in the Java SE Platform version 9, major version
 * {@value ClassFile#JAVA_9_VERSION}.
 *
 * @see Attributes#moduleMainClass()
 * @see ModuleDescriptor#mainClass()
 * @jvms 4.7.27 The {@code ModuleMainClass} Attribute
 * @since 24
 */
public sealed interface ModuleMainClassAttribute
        extends Attribute<ModuleMainClassAttribute>, ClassElement
        permits BoundAttribute.BoundModuleMainClassAttribute, UnboundAttribute.UnboundModuleMainClassAttribute {

    /**
     * {@return main class for this module}
     */
    ClassEntry mainClass();

    /**
     * {@return a {@code ModuleMainClass} attribute}
     * @param mainClass the main class
     */
    static ModuleMainClassAttribute of(ClassEntry mainClass) {
        return new UnboundAttribute.UnboundModuleMainClassAttribute(mainClass);
    }

    /**
     * {@return a {@code ModuleMainClass} attribute}
     * @param mainClass the main class
     * @throws IllegalArgumentException if {@code mainClass} represents a primitive type
     */
    static ModuleMainClassAttribute of(ClassDesc mainClass) {
        return new UnboundAttribute.UnboundModuleMainClassAttribute(TemporaryConstantPool.INSTANCE.classEntry(mainClass));
    }
}
