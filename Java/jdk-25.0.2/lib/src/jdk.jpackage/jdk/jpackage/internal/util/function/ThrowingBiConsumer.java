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

import java.util.function.BiConsumer;

@FunctionalInterface
public interface ThrowingBiConsumer<T, U> {

    void accept(T t, U u) throws Throwable;

    public static <T, U> BiConsumer<T, U> toBiConsumer(
            ThrowingBiConsumer<T, U> v) {
        return (t, u) -> {
            try {
                v.accept(t, u);
            } catch (Throwable ex) {
                throw ExceptionBox.rethrowUnchecked(ex);
            }
        };
    }
}
