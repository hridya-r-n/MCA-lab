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

import java.lang.classfile.attribute.*;

/**
 * Marker interface for a member element of a {@link ClassModel}.  Such an
 * element can appear when traversing a {@link ClassModel} unless otherwise
 * specified, be supplied to a {@link ClassBuilder}, and be processed by a
 * {@link ClassTransform}.
 * <p>
 * {@link AccessFlags}, and {@link ClassFileVersion} are member elements of a
 * class that appear exactly once during the traversal of a {@link ClassModel}.
 * {@link Superclass} and {@link Interfaces} may be absent or appear at most
 * once.  A {@link ClassBuilder} may provide an alternative superclass if it is
 * not defined but required.
 *
 * @see ClassFileElement##membership Membership Elements
 * @see MethodElement
 * @see FieldElement
 * @see CodeElement
 * @sealedGraph
 * @since 24
 */
public sealed interface ClassElement extends ClassFileElement
        permits AccessFlags, Superclass, Interfaces, ClassFileVersion,
                FieldModel, MethodModel,
                CustomAttribute, CompilationIDAttribute, DeprecatedAttribute,
                EnclosingMethodAttribute, InnerClassesAttribute,
                ModuleAttribute, ModuleHashesAttribute, ModuleMainClassAttribute,
                ModulePackagesAttribute, ModuleResolutionAttribute, ModuleTargetAttribute,
                NestHostAttribute, NestMembersAttribute, PermittedSubclassesAttribute,
                RecordAttribute,
                RuntimeInvisibleAnnotationsAttribute, RuntimeInvisibleTypeAnnotationsAttribute,
                RuntimeVisibleAnnotationsAttribute, RuntimeVisibleTypeAnnotationsAttribute,
                SignatureAttribute, SourceDebugExtensionAttribute,
                SourceFileAttribute, SourceIDAttribute, SyntheticAttribute, UnknownAttribute {
}
