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

import java.lang.constant.ClassDesc;
import java.lang.reflect.AccessFlag;
import java.util.function.Consumer;

import jdk.internal.classfile.impl.AccessFlagsImpl;
import jdk.internal.classfile.impl.ChainedFieldBuilder;
import jdk.internal.classfile.impl.TerminalFieldBuilder;

/**
 * A builder for fields.  The main way to obtain a field builder is via {@link
 * ClassBuilder#withField(String, ClassDesc, Consumer)}.  The {@linkplain
 * ClassBuilder#withField(String, ClassDesc, int) access flag overload} is
 * useful if no attribute needs to be configured, skipping the handler.
 * <p>
 * Refer to {@link ClassFileBuilder} for general guidance and caution around
 * the use of builders for structures in the {@code class} file format.
 *
 * @see ClassBuilder#withField(String, ClassDesc, Consumer)
 * @see FieldModel
 * @see FieldTransform
 * @since 24
 */
public sealed interface FieldBuilder
        extends ClassFileBuilder<FieldElement, FieldBuilder>
        permits TerminalFieldBuilder, ChainedFieldBuilder {

    /**
     * Sets the field access flags.
     *
     * @param flags the access flags, as a bit mask
     * @return this builder
     * @see AccessFlags
     * @see AccessFlag.Location#FIELD
     * @see ClassBuilder#withField(String, ClassDesc, int)
     */
    default FieldBuilder withFlags(int flags) {
        return with(new AccessFlagsImpl(AccessFlag.Location.FIELD, flags));
    }

    /**
     * Sets the field access flags.
     *
     * @param flags the access flags, as a bit mask
     * @return this builder
     * @see AccessFlags
     * @see AccessFlag.Location#FIELD
     * @see ClassBuilder#withField(String, ClassDesc, int)
     */
    default FieldBuilder withFlags(AccessFlag... flags) {
        return with(new AccessFlagsImpl(AccessFlag.Location.FIELD, flags));
    }

}
