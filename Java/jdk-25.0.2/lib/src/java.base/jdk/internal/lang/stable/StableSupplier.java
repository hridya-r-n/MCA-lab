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

package jdk.internal.lang.stable;

import jdk.internal.vm.annotation.ForceInline;

import java.util.function.Supplier;

/**
 * Implementation of a stable supplier.
 * <p>
 * @implNote This implementation can be used early in the boot sequence as it does not
 *           rely on reflection, MethodHandles, Streams etc.
 *
 * @param <T> the return type
 */
public record StableSupplier<T>(StableValueImpl<T> delegate,
                                Supplier<? extends T> original) implements Supplier<T> {

    @ForceInline
    @Override
    public T get() {
        return delegate.orElseSet(original);
    }

    @Override
    public int hashCode() {
        return System.identityHashCode(this);
    }

    @Override
    public boolean equals(Object obj) {
        return obj == this;
    }

    @Override
    public String toString() {
        final Object t = delegate.wrappedContentsAcquire();
        return t == this ? "(this StableSupplier)" : StableValueImpl.renderWrapped(t);
    }

    public static <T> StableSupplier<T> of(Supplier<? extends T> original) {
        return new StableSupplier<>(StableValueImpl.of(), original);
    }

}
