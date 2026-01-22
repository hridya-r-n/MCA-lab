/*
 * Copyright (c) 2025, Oracle and/or its affiliates. All rights reserved.
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
package jdk.jpackage.internal.util;

import java.util.Collection;

/**
 * This class consists exclusively of static methods that operate on or return collections.
 */
public final class CollectionUtils {

    /**
     * Casts the given collection to the requested type.
     *
     * @param <T> the type of elements in this output collection
     * @param <B> the type of elements in this input collection
     * @param <C> the output collection type
     * @param v the input collection. Null is permitted.
     * @return the input collection cast to the requested type
     */
    @SuppressWarnings("unchecked")
    public static <T extends B, B, C extends Collection<T>> C toCollection(Collection<B> v) {
        Collection<?> tmp = v;
        return (C) tmp;
    }

    /**
     * Casts the given collection to the requested upper bounded wildcard (UBW) type.
     *
     * @param <T> the type of elements in this output collection
     * @param <B> the upper bound type of elements in this input collection
     * @param <C> the output collection type
     * @param v the input collection. Null is permitted.
     * @return the input collection cast to the requested type
     */
    @SuppressWarnings("unchecked")
    public static <T extends B, B, C extends Collection<T>> C toCollectionUBW(Collection<? extends B> v) {
        Collection<?> tmp = v;
        return (C) tmp;
    }
}
