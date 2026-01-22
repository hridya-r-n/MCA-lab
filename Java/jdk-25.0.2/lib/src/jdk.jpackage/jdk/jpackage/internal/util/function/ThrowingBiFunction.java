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

import java.util.function.BiFunction;

@FunctionalInterface
public interface ThrowingBiFunction<T, U, R> {

    R apply(T t, U u) throws Throwable;

    public static <T, U, R> BiFunction<T, U, R> toBiFunction(
            ThrowingBiFunction<T, U, R> v) {
        return (t, u) -> {
            try {
                return v.apply(t, u);
            } catch (Throwable ex) {
                throw ExceptionBox.rethrowUnchecked(ex);
            }
        };
    }

}
