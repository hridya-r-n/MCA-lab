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

import java.util.function.Supplier;

@FunctionalInterface
public interface ThrowingSupplier<T> {

    T get() throws Throwable;

    public static <T> Supplier<T> toSupplier(ThrowingSupplier<T> v) {
        return () -> {
            try {
                return v.get();
            } catch (Throwable ex) {
                throw ExceptionBox.rethrowUnchecked(ex);
            }
        };
    }

}
