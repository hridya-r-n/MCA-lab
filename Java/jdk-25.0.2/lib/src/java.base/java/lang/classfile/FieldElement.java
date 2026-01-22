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
 * Marker interface for a member element of a {@link FieldModel}.  Such an
 * element can appear when traversing a {@link FieldModel} unless otherwise
 * specified, be supplied to a {@link FieldBuilder}, and be processed by a
 * {@link FieldTransform}.
 * <p>
 * {@link AccessFlags} is the only member element of a field that appear exactly
 * once during the traversal of a {@link FieldModel}.
 *
 * @see ClassFileElement##membership Membership Elements
 * @see ClassElement
 * @see MethodElement
 * @see CodeElement
 * @sealedGraph
 * @since 24
 */
public sealed interface FieldElement extends ClassFileElement
        permits AccessFlags,
                CustomAttribute, ConstantValueAttribute, DeprecatedAttribute,
                RuntimeInvisibleAnnotationsAttribute, RuntimeInvisibleTypeAnnotationsAttribute,
                RuntimeVisibleAnnotationsAttribute, RuntimeVisibleTypeAnnotationsAttribute,
                SignatureAttribute, SyntheticAttribute, UnknownAttribute {

}
