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
import java.lang.classfile.constantpool.ClassEntry;
import java.lang.constant.ClassDesc;
import java.util.Arrays;
import java.util.List;

import jdk.internal.classfile.impl.BoundAttribute;
import jdk.internal.classfile.impl.UnboundAttribute;
import jdk.internal.classfile.impl.Util;

/**
 * Models the {@link Attributes#nestMembers() NestMembers} attribute (JVMS
 * {@jvms 4.7.29}), which indicates that this class is the host of a nest
 * and the other nest members.
 * <p>
 * This attribute only appears on classes, and does not permit {@linkplain
 * AttributeMapper#allowMultiple multiple instances} in a class.  It has a
 * data dependency on the {@linkplain AttributeStability#CP_REFS constant pool}.
 * <p>
 * The attribute was introduced in the Java SE Platform version 11, major
 * version {@value ClassFile#JAVA_11_VERSION}.
 *
 * @see Attributes#nestMembers()
 * @see NestHostAttribute
 * @see Class#getNestMembers()
 * @see Class#isNestmateOf(Class)
 * @jvms 4.7.29 The {@code NestMembers} Attribute
 * @since 24
 */
public sealed interface NestMembersAttribute extends Attribute<NestMembersAttribute>, ClassElement
        permits BoundAttribute.BoundNestMembersAttribute, UnboundAttribute.UnboundNestMembersAttribute {

    /**
     * {@return the classes belonging to the nest hosted by this class}
     *
     * @see Class#getNestMembers()
     */
    List<ClassEntry> nestMembers();

    /**
     * {@return a {@code NestMembers} attribute}
     *
     * @param nestMembers the member classes of the nest
     */
    static NestMembersAttribute of(List<ClassEntry> nestMembers) {
        return new UnboundAttribute.UnboundNestMembersAttribute(nestMembers);
    }

    /**
     * {@return a {@code NestMembers} attribute}
     *
     * @param nestMembers the member classes of the nest
     */
    static NestMembersAttribute of(ClassEntry... nestMembers) {
        return of(List.of(nestMembers));
    }

    /**
     * {@return a {@code NestMembers} attribute}
     *
     * @param nestMembers the member classes of the nest
     * @throws IllegalArgumentException if any of {@code nestMembers} is primitive
     */
    static NestMembersAttribute ofSymbols(List<ClassDesc> nestMembers) {
        return of(Util.entryList(nestMembers));
    }

    /**
     * {@return a {@code NestMembers} attribute}
     *
     * @param nestMembers the member classes of the nest
     * @throws IllegalArgumentException if any of {@code nestMembers} is primitive
     */
    static NestMembersAttribute ofSymbols(ClassDesc... nestMembers) {
        // List version does defensive copy
        return ofSymbols(Arrays.asList(nestMembers));
    }
}
