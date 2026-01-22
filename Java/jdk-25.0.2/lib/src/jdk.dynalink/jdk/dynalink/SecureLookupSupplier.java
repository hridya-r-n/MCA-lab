/*
 * Copyright (c) 2016, 2024, Oracle and/or its affiliates. All rights reserved.
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

package jdk.dynalink;

import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodHandles.Lookup;
import java.util.Objects;

/**
 * Encapsulates a {@code MethodHandles.Lookup} object.
 *
 * @apiNote
 * SecureLookupSupplier provided a way in older JDK releases to guard access to
 * a {@code MethodHandles.Lookup} object when running with a security manager set.
 *
 * @since 9
 */
public class SecureLookupSupplier {
    /**
     * The name of a runtime permission required to successfully invoke the
     * {@link #getLookup()} method.
     */
    public static final String GET_LOOKUP_PERMISSION_NAME = "dynalink.getLookup";

    private final MethodHandles.Lookup lookup;

    /**
     * Creates a new secure lookup supplier for the given lookup.
     * @param lookup the lookup to secure. Can not be null.
     * @throws NullPointerException if null is passed.
     */
    public SecureLookupSupplier(final MethodHandles.Lookup lookup) {
        this.lookup = Objects.requireNonNull(lookup, "lookup");
    }

    /**
     * Returns the lookup secured by this {@code SecureLookupSupplier}.
     * @return the lookup secured by this {@code SecureLookupSupplier}.
     */
    public final Lookup getLookup() {
        return lookup;
    }

    /**
     * Returns the lookup secured by this {@code SecureLookupSupplier}.
     * @return same as returned value of {@link #getLookup()}.
     */
    protected final Lookup getLookupPrivileged() {
        return lookup;
    }
}
