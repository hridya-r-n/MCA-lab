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

import java.lang.classfile.constantpool.Utf8Entry;
import java.lang.constant.MethodTypeDesc;
import java.lang.reflect.AccessFlag;
import java.util.Optional;
import java.util.function.Consumer;

import jdk.internal.classfile.impl.BufferedMethodBuilder;
import jdk.internal.classfile.impl.MethodImpl;
import jdk.internal.classfile.impl.Util;

/**
 * Models a method.  A method can be viewed as a {@linkplain CompoundElement
 * composition} of {@link MethodElement}s, or by random access via accessor
 * methods if only specific parts of the method is needed.
 * <p>
 * Methods can be obtained from {@link ClassModel#methods()}, or in the
 * traversal of member elements of a class.
 * <p>
 * {@link ClassBuilder#withMethod(String, MethodTypeDesc, int, Consumer)} is the
 * main way to construct methods.  {@link ClassBuilder#transformMethod} allows
 * creating a new method by selectively processing the original method elements
 * and directing the results to a method builder.
 * <p>
 * All method attributes are accessible as member elements.
 *
 * @see ClassModel#methods()
 * @see MethodTransform
 * @jvms 4.6 Methods
 * @since 24
 */
public sealed interface MethodModel
        extends CompoundElement<MethodElement>, AttributedElement, ClassElement
        permits BufferedMethodBuilder.Model, MethodImpl {

    /**
     * {@return the access flags}
     *
     * @see AccessFlag.Location#METHOD
     */
    AccessFlags flags();

    /** {@return the class model this method is a member of, if known} */
    Optional<ClassModel> parent();

    /** {@return the name of this method} */
    Utf8Entry methodName();

    /** {@return the method descriptor string of this method} */
    Utf8Entry methodType();

    /** {@return the method type, as a symbolic descriptor} */
    default MethodTypeDesc methodTypeSymbol() {
        return Util.methodTypeSymbol(methodType());
    }

    /** {@return the body of this method, if there is one} */
    Optional<CodeModel> code();
}
