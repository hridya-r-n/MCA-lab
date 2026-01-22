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
import java.lang.classfile.ClassModel;
import java.lang.classfile.constantpool.Utf8Entry;

import jdk.internal.classfile.impl.BoundAttribute;
import jdk.internal.classfile.impl.TemporaryConstantPool;
import jdk.internal.classfile.impl.UnboundAttribute;

/**
 * Models the {@link Attributes#moduleTarget() ModuleTarget} attribute, which
 * can appear on classes that {@linkplain ClassModel#isModuleInfo() represent}
 * module descriptors, to represent constraints on the target platform.
 * <p>
 * The specification of the {@code ModuleTarget} attribute is:
 * <pre> {@code
 * TargetPlatform_attribute {
 *   // index to CONSTANT_utf8_info structure in constant pool representing
 *   // the string "ModuleTarget"
 *   u2 attribute_name_index;
 *   u4 attribute_length;
 *
 *   // index to CONSTANT_utf8_info structure with the target platform
 *   u2 target_platform_index;
 * }
 * } </pre>
 * <p>
 * This attribute only appears on classes, and does not permit {@linkplain
 * AttributeMapper#allowMultiple multiple instances} in a class.  It has a
 * data dependency on the {@linkplain AttributeStability#CP_REFS constant pool}.
 * <p>
 * This attribute is not predefined in the Java SE Platform.  This is a
 * JDK-specific nonstandard attribute produced by the {@code jdk.jlink} module,
 * which defines the {@code jlink} and {@code jmod} tools.
 *
 * @see Attributes#moduleTarget()
 * @see ModuleHashesAttribute
 * @see ModuleResolutionAttribute
 * @since 24
 */
public sealed interface ModuleTargetAttribute
        extends Attribute<ModuleTargetAttribute>, ClassElement
        permits BoundAttribute.BoundModuleTargetAttribute, UnboundAttribute.UnboundModuleTargetAttribute {

    /**
     * {@return the target platform}
     */
    Utf8Entry targetPlatform();

    /**
     * {@return a {@code ModuleTarget} attribute}
     *
     * @param targetPlatform the target platform
     */
    static ModuleTargetAttribute of(String targetPlatform) {
        return of(TemporaryConstantPool.INSTANCE.utf8Entry(targetPlatform));
    }

    /**
     * {@return a {@code ModuleTarget} attribute}
     *
     * @param targetPlatform the target platform
     */
    static ModuleTargetAttribute of(Utf8Entry targetPlatform) {
        return new UnboundAttribute.UnboundModuleTargetAttribute(targetPlatform);
    }
}
