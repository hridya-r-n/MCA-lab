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

/**
 * Details of Linux application launcher startup configuration using non-modular jar file.
 */
public interface LauncherJarStartupInfoMixin {

    /**
     * Gets the path to the input jar file.
     * @return the path to the input jar file
     */
    Path jarPath();

    /**
     * Returns <code>true</code> if the input jar file has <code>Main-Class</code> entry in the manifest.
     * @return <code>true</code> if the input jar file has <code>Main-Class</code> entry in the manifest
     */
    boolean isJarWithMainClass();

    /**
     * Default implementation of {@link LauncherJarStartupInfoMixin} interface.
     */
    record Stub(Path jarPath, boolean isJarWithMainClass) implements LauncherJarStartupInfoMixin {}
}
