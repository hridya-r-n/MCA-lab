/*
 * Copyright (c) 2020, 2025, Oracle and/or its affiliates. All rights reserved.
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

package jdk.internal.access;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.function.IntFunction;

public interface JavaUtilCollectionAccess {
    <E> List<E> listFromTrustedArray(Object[] array);
    <E> List<E> listFromTrustedArrayNullsAllowed(Object[] array);
    <E> List<E> stableList(int size, IntFunction<? extends E> mapper);
    <K, V> Map<K, V> stableMap(Set<K> keys, Function<? super K, ? extends V> mapper);
}
