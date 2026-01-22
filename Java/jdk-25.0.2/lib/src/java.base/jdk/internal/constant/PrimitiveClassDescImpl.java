/*
 * Copyright (c) 2018, 2024, Oracle and/or its affiliates. All rights reserved.
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
package jdk.internal.constant;

import java.lang.constant.ClassDesc;
import java.lang.constant.ConstantDescs;
import java.lang.constant.DynamicConstantDesc;
import java.lang.invoke.MethodHandles;

import jdk.internal.vm.annotation.Stable;
import sun.invoke.util.Wrapper;

import static java.util.Objects.requireNonNull;

/**
 * A <a href="package-summary.html#nominal">nominal descriptor</a> for the class
 * constant corresponding to a primitive type (e.g., {@code int.class}).
 */
public final class PrimitiveClassDescImpl
        extends DynamicConstantDesc<Class<?>> implements ClassDesc {

    /** {@link ClassDesc} representing the primitive type {@code int} */
    public static final PrimitiveClassDescImpl CD_int = new PrimitiveClassDescImpl("I");

    /** {@link ClassDesc} representing the primitive type {@code long} */
    public static final PrimitiveClassDescImpl CD_long = new PrimitiveClassDescImpl("J");

    /** {@link ClassDesc} representing the primitive type {@code float} */
    public static final PrimitiveClassDescImpl CD_float = new PrimitiveClassDescImpl("F");

    /** {@link ClassDesc} representing the primitive type {@code double} */
    public static final PrimitiveClassDescImpl CD_double = new PrimitiveClassDescImpl("D");

    /** {@link ClassDesc} representing the primitive type {@code short} */
    public static final PrimitiveClassDescImpl CD_short = new PrimitiveClassDescImpl("S");

    /** {@link ClassDesc} representing the primitive type {@code byte} */
    public static final PrimitiveClassDescImpl CD_byte = new PrimitiveClassDescImpl("B");

    /** {@link ClassDesc} representing the primitive type {@code char} */
    public static final PrimitiveClassDescImpl CD_char = new PrimitiveClassDescImpl("C");

    /** {@link ClassDesc} representing the primitive type {@code boolean} */
    public static final PrimitiveClassDescImpl CD_boolean = new PrimitiveClassDescImpl("Z");

    /** {@link ClassDesc} representing the primitive type {@code void} */
    public static final PrimitiveClassDescImpl CD_void = new PrimitiveClassDescImpl("V");

    private final String descriptor;
    private @Stable Wrapper lazyWrapper; // initialized only after this

    /**
     * Creates a {@linkplain ClassDesc} given a descriptor string for a primitive
     * type.
     *
     * @param descriptor the descriptor string, which must be a one-character
     * string corresponding to one of the nine base types
     * @throws IllegalArgumentException if the descriptor string does not
     * describe a valid primitive type
     * @jvms 4.3 Descriptors
     */
    private PrimitiveClassDescImpl(String descriptor) {
        super(ConstantDescs.BSM_PRIMITIVE_CLASS, requireNonNull(descriptor), ConstantDescs.CD_Class);
        this.descriptor = descriptor;
    }

    public Wrapper wrapper() {
        var wrapper = this.lazyWrapper;
        if (wrapper != null)
            return wrapper;
        return this.lazyWrapper = Wrapper.forBasicType(descriptorString().charAt(0));
    }

    @Override
    public boolean isPrimitive() {
        return true;
    }

    @Override
    public ClassDesc arrayType(int rank) {
        ConstantUtils.validateArrayRank(rank);
        if (this == CD_void)
            throw new IllegalArgumentException("not a valid reference type descriptor: " + "[".repeat(rank) + "V");
        return ArrayClassDescImpl.ofValidated(this, rank);
    }

    @Override
    public ClassDesc arrayType() {
        if (this == CD_void)
            throw new IllegalArgumentException("not a valid reference type descriptor: [V");
        return ArrayClassDescImpl.ofValidated(this, 1);
    }

    @Override
    public String displayName() {
        return wrapper().primitiveSimpleName();
    }

    @Override
    public String descriptorString() {
        return descriptor;
    }

    @Override
    public Class<?> resolveConstantDesc(MethodHandles.Lookup lookup) {
        return wrapper().primitiveType();
    }

    @Override
    public String toString() {
        return String.format("PrimitiveClassDesc[%s]", displayName());
    }
}
