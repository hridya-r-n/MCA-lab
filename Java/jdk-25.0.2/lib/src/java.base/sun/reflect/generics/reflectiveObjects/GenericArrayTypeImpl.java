/*
 * Copyright (c) 2003, 2023, Oracle and/or its affiliates. All rights reserved.
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

package sun.reflect.generics.reflectiveObjects;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;
import java.util.Objects;

/**
 * Implementation of GenericArrayType interface for core reflection.
 */
public class GenericArrayTypeImpl
    implements GenericArrayType {
    private final Type genericComponentType;

    // private constructor enforces use of static factory
    private GenericArrayTypeImpl(Type ct) {
        genericComponentType = ct;
    }

    /**
     * Factory method.
     * @param ct - the desired component type of the generic array type
     * being created
     * @return a generic array type with the desired component type
     */
    public static GenericArrayTypeImpl make(Type ct) {
        return new GenericArrayTypeImpl(ct);
    }


    /**
     * Returns a {@code Type} object representing the component type
     * of this array.
     *
     * @return a {@code Type} object representing the component type
     *     of this array
     * @since 1.5
     */
    public Type getGenericComponentType() {
        return genericComponentType; // return cached component type
    }

    public String toString() {
        return getGenericComponentType().getTypeName() + "[]";
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof GenericArrayType that
                && Objects.equals(genericComponentType, that.getGenericComponentType());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(genericComponentType);
    }
}
