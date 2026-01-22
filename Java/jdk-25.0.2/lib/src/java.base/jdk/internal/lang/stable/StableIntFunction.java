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
import jdk.internal.vm.annotation.Stable;

import java.util.function.IntFunction;
import java.util.function.Supplier;

/**
 * Implementation of a stable IntFunction.
 * <p>
 * For performance reasons (~10%), we are not delegating to a StableList but are using
 * the more primitive functions in StableValueUtil that are shared with StableList/StableValueImpl.
 *
 * @implNote This implementation can be used early in the boot sequence as it does not
 *           rely on reflection, MethodHandles, Streams etc.
 *
 * @param <R> the return type
 */
public record StableIntFunction<R>(@Stable StableValueImpl<R>[] delegates,
                                   IntFunction<? extends R> original) implements IntFunction<R> {

    @ForceInline
    @Override
    public R apply(int index) {
        final StableValueImpl<R> delegate;
        try {
            delegate =  delegates[index];
        } catch (ArrayIndexOutOfBoundsException ioob) {
            throw new IllegalArgumentException("Input not allowed: " + index, ioob);
        }
        return delegate.orElseSet(new Supplier<R>() {
                    @Override public R get() { return original.apply(index); }});
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
        return StableUtil.renderElements(this, "StableIntFunction", delegates);
    }

    public static <R> StableIntFunction<R> of(int size, IntFunction<? extends R> original) {
        return new StableIntFunction<>(StableUtil.array(size), original);
    }

}
