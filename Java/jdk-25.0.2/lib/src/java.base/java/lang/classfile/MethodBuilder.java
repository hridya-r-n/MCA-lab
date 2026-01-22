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

import java.lang.constant.MethodTypeDesc;
import java.lang.reflect.AccessFlag;
import java.util.function.Consumer;

import jdk.internal.classfile.impl.AccessFlagsImpl;
import jdk.internal.classfile.impl.ChainedMethodBuilder;
import jdk.internal.classfile.impl.TerminalMethodBuilder;

/**
 * A builder for methods.  The main way to obtain a method builder is via {@link
 * ClassBuilder#withMethod(String, MethodTypeDesc, int, Consumer)}.  {@link
 * ClassBuilder#withMethodBody(String, MethodTypeDesc, int, Consumer)} is
 * useful if no attribute on the method except {@link CodeModel Code} needs to
 * be configured, skipping the method handler.
 * <p>
 * Refer to {@link ClassFileBuilder} for general guidance and caution around
 * the use of builders for structures in the {@code class} file format.
 *
 * @see MethodModel
 * @see MethodTransform
 * @jvms 4.6 Methods
 * @since 24
 */
public sealed interface MethodBuilder
        extends ClassFileBuilder<MethodElement, MethodBuilder>
        permits ChainedMethodBuilder, TerminalMethodBuilder {

    /**
     * Sets the method access flags.  The {@link AccessFlag#STATIC} flag cannot
     * be modified after the builder is created.
     *
     * @param flags the access flags, as a bit mask
     * @return this builder
     * @throws IllegalArgumentException if the {@link ClassFile#ACC_STATIC
     *         ACC_STATIC} flag is modified
     * @see AccessFlags
     * @see AccessFlag.Location#METHOD
     */
    default MethodBuilder withFlags(int flags) {
        return with(new AccessFlagsImpl(AccessFlag.Location.METHOD, flags));
    }

    /**
     * Sets the method access flags.  The {@link AccessFlag#STATIC} flag cannot
     * be modified after the builder is created.
     *
     * @param flags the access flags, as a bit mask
     * @return this builder
     * @throws IllegalArgumentException if the {@link ClassFile#ACC_STATIC
     *         ACC_STATIC} flag is modified
     * @see AccessFlags
     * @see AccessFlag.Location#METHOD
     */
    default MethodBuilder withFlags(AccessFlag... flags) {
        return with(new AccessFlagsImpl(AccessFlag.Location.METHOD, flags));
    }

    /**
     * Build the method body for this method.
     *
     * @param code a handler receiving a {@link CodeBuilder}
     * @return this builder
     * @see CodeModel
     */
    MethodBuilder withCode(Consumer<? super CodeBuilder> code);

    /**
     * Build the method body for this method by transforming the body of another
     * method.
     * <p>
     * This method behaves as if:
     * {@snippet lang=java :
     * withCode(cob -> cob.transform(code, transform));
     * }
     *
     * @param code the method body to be transformed
     * @param transform the transform to apply to the method body
     * @return this builder
     * @see CodeTransform
     */
    MethodBuilder transformCode(CodeModel code, CodeTransform transform);
}
