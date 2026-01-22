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
import java.lang.classfile.constantpool.PackageEntry;
import java.lang.constant.PackageDesc;
import java.lang.module.ModuleDescriptor;
import java.util.Arrays;
import java.util.List;

import jdk.internal.classfile.impl.BoundAttribute;
import jdk.internal.classfile.impl.TemporaryConstantPool;
import jdk.internal.classfile.impl.UnboundAttribute;

/**
 * Models the {@link Attributes#modulePackages() ModulePackages} attribute (JVMS
 * {@jvms 4.7.26}), which can appear on classes that {@linkplain
 * ClassModel#isModuleInfo() represent} module descriptors to indicate packages
 * in the module used by the module descriptor.
 * <p>
 * This attribute only appears on classes, and does not permit {@linkplain
 * AttributeMapper#allowMultiple multiple instances} in a class.  It has a
 * data dependency on the {@linkplain AttributeStability#CP_REFS constant pool}.
 * <p>
 * The attribute was introduced in the Java SE Platform version 9, major version
 * {@value ClassFile#JAVA_9_VERSION}.
 *
 * @see Attributes#modulePackages()
 * @see ModuleDescriptor#packages()
 * @jvms 4.7.26 The {@code ModulePackages} Attribute
 * @since 24
 */
public sealed interface ModulePackagesAttribute
        extends Attribute<ModulePackagesAttribute>, ClassElement
        permits BoundAttribute.BoundModulePackagesAttribute,
                UnboundAttribute.UnboundModulePackagesAttribute {

    /**
     * {@return the packages used by the module descriptor}  This must include
     * all packages opened or exported by the module, as well as the packages of
     * any service providers, and the package for the main class.
     */
    List<PackageEntry> packages();

    /**
     * {@return a {@code ModulePackages} attribute}
     * @param packages the packages
     */
    static ModulePackagesAttribute of(List<PackageEntry> packages) {
        return new UnboundAttribute.UnboundModulePackagesAttribute(packages);
    }

    /**
     * {@return a {@code ModulePackages} attribute}
     * @param packages the packages
     */
    static ModulePackagesAttribute of(PackageEntry... packages) {
        return of(List.of(packages));
    }

    /**
     * {@return a {@code ModulePackages} attribute}
     * @param packages the packages
     */
    static ModulePackagesAttribute ofNames(List<PackageDesc> packages) {
        var p = new PackageEntry[packages.size()];
        for (int i = 0; i < packages.size(); i++) {
            p[i] = TemporaryConstantPool.INSTANCE.packageEntry(TemporaryConstantPool.INSTANCE.utf8Entry(packages.get(i).internalName()));
        }
        return of(p);
    }

    /**
     * {@return a {@code ModulePackages} attribute}
     * @param packages the packages
     */
    static ModulePackagesAttribute ofNames(PackageDesc... packages) {
        // List view, since ref to packages is temporary
        return ofNames(Arrays.asList(packages));
    }
}
