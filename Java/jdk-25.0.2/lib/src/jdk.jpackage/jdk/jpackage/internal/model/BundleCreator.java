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

import java.io.IOException;
import java.nio.file.Path;


/**
 * Creates a bundle from the given specification.
 */
@FunctionalInterface
public interface BundleCreator<T extends BundleSpec> {

    /**
     * Creates a bundle from the given specification in the given directory.
     *
     * @param spec the bundle specification
     * @param dst the directory where to create the bundle
     * @throws PackagerException if packaging error occurs
     * @throws IOException if an I/O error occurs
     */
    void create(T spec, Path dst) throws PackagerException, IOException;
}
