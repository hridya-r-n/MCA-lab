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

import java.nio.file.Path;
import java.util.List;

/**
 * Creates Java runtime.
 */
@FunctionalInterface
public interface RuntimeBuilder {

    /**
     * Creates Java runtime in the given app image.
     *
     * @implNote Java runtime should be created in the directory returned by
     *           {@link AppImageLayout#runtimeDirectory()} method called on the
     *           given app image layout object.
     *
     * @param appImageLayout the app image where to create Java runtime.
     * @throws PackagerException if packaging error occurs
     */
    void createRuntime(AppImageLayout appImageLayout) throws PackagerException;

    /**
     * Gets the default set of paths where to find Java modules.
     *
     * @return the default set of paths where to find Java modules
     */
    public static List<Path> getDefaultModulePath() {
        return List.of(
                Path.of(System.getProperty("java.home"), "jmods").toAbsolutePath());
    }
}
