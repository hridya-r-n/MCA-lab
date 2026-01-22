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
package java.lang.classfile.constantpool;

import java.lang.constant.ConstantDesc;
import java.lang.constant.DirectMethodHandleDesc;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandleInfo;

import jdk.internal.classfile.impl.AbstractPoolEntry;

/**
 * Models a {@code CONSTANT_MethodHandle_info} structure, or a symbolic
 * reference to a {@linkplain MethodHandle method handle}, in the constant pool
 * of a {@code class} file.  The method handle directly accesses an accessible
 * method, field, or constructor.
 * <p>
 * The use of a {@code MethodHandleEntry} is modeled by a {@link
 * DirectMethodHandleDesc}.  Conversions are through {@link
 * ConstantPoolBuilder#methodHandleEntry(DirectMethodHandleDesc)} and {@link
 * #asSymbol()}.
 * <p>
 * A method handle entry is composite:
 * {@snippet lang=text :
 * // @link substring="MethodHandleEntry" target="ConstantPoolBuilder#methodHandleEntry(int, MemberRefEntry)" :
 * MethodHandleEntry(
 *     int refKind, // @link substring="refKind" target="#kind()"
 *     MemberRefEntry reference // @link substring="reference" target="#reference()"
 * )
 * }
 * where {@code refKind} is in the range {@code [1, 9]}.
 *
 * @see ConstantPoolBuilder#methodHandleEntry
 *      ConstantPoolBuilder::methodHandleEntry
 * @jvms 4.4.8 The {@code CONSTANT_MethodHandle_info} Structure
 *
 * @since 24
 */
public sealed interface MethodHandleEntry
        extends LoadableConstantEntry
        permits AbstractPoolEntry.MethodHandleEntryImpl {

    /**
     * {@inheritDoc}
     * <p>
     * This is equivalent to {@link #asSymbol() asSymbol()}.
     */
    @Override
    default ConstantDesc constantValue() {
        return asSymbol();
    }

    /**
     * {@return the reference kind of this method handle (JVMS {@jvms 4.4.8})}
     *
     * @see MethodHandleInfo##refkinds Reference kinds
     */
    int kind();

    /**
     * {@return the constant pool entry describing the field or method,
     * according to the {@linkplain #kind() reference kind}}
     */
    MemberRefEntry reference();

    /**
     * {@return a symbolic descriptor for this method handle}
     *
     * @see ConstantPoolBuilder#methodHandleEntry(DirectMethodHandleDesc)
     *      ConstantPoolBuilder::methodHandleEntry(DirectMethodHandleDesc)
     */
    DirectMethodHandleDesc asSymbol();
}
