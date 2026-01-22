/*
 * Copyright (c) 2024, Oracle and/or its affiliates. All rights reserved.
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
package jdk.jpackage.internal.util.function;

import java.util.function.UnaryOperator;

@FunctionalInterface
public interface ThrowingUnaryOperator<T> {

    T apply(T t) throws Throwable;

    public static <T> UnaryOperator<T> toUnaryOperator(
            ThrowingUnaryOperator<T> v) {
        return t -> {
            try {
                return v.apply(t);
            } catch (Throwable ex) {
                throw ExceptionBox.rethrowUnchecked(ex);
            }
        };
    }

}
