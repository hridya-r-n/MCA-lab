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
package jdk.jpackage.internal.model;

import java.util.Set;

/**
 * Bundling environment. Defines available bundling operations.
 */
public interface BundlingEnvironment {

    /**
     * Returns the default bundling operation.
     * <p>
     * The returned value should be one of the elements in the collection returned by {@link #enabledOperations()} method.
     * @return the default bundling operation
     * @throws ConfigException in not a single bundling operation can be performed.
     */
    BundlingOperation defaultOperation() throws ConfigException;

    /**
     * Returns supported bundling operations.
     * @return the supported bundling operations
     */
    Set<BundlingOperation> supportedOperations();

    /**
     * Returns enabled bundling operations.
     * <p>
     * The returned value should be a subset of the set returned by {@link #supportedOperations()} method.
     * @return the enabled bundling operations
     */
    default Set<BundlingOperation> enabledOperations() {
        return supportedOperations();
    }

    /**
     * Returns a bundle creator corresponding to the given bundling operation in this bundling environment.
     * @param op the bundling operation
     * @return bundle creator corresponding to the given bundling operation in this bundling environment
     * @throws IllegalArgumentException if the given bundling operation is not enabled in this bundling environment
     */
    BundleCreator<?> getBundleCreator(BundlingOperation op);
}
