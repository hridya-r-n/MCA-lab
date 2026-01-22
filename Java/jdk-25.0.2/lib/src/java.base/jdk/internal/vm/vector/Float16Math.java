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
package jdk.internal.vm.vector;

import jdk.internal.vm.annotation.IntrinsicCandidate;
import java.util.function.UnaryOperator;

public class Float16Math {

    @FunctionalInterface
    public interface TernaryOperator<T> {
        T apply(T a, T b, T c);
    }

    @IntrinsicCandidate
    public static <T> T sqrt(Class<T> box_class, T oa, UnaryOperator<T> defaultImpl) {
        assert isNonCapturingLambda(defaultImpl) : defaultImpl;
        return defaultImpl.apply(oa);
    }

    @IntrinsicCandidate
    public static <T> T fma(Class<T> box_class, T oa, T ob, T oc, TernaryOperator<T> defaultImpl) {
        assert isNonCapturingLambda(defaultImpl) : defaultImpl;
        return defaultImpl.apply(oa, ob, oc);
    }

    public static boolean isNonCapturingLambda(Object o) {
        return o.getClass().getDeclaredFields().length == 0;
    }
}
