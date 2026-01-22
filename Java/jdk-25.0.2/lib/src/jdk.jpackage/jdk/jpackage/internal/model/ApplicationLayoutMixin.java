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
 * App image directory contents specific to application packaging.
 */
public interface ApplicationLayoutMixin {

    /**
     * Path to launchers directory.
     */
    Path launchersDirectory();

    /**
     * Path to application data directory.
     */
    Path appDirectory();

    /**
     * Path to directory with application's Java modules.
     */
    Path appModsDirectory();

    /**
     * Path to directory with application's desktop integration files.
     */
    Path desktopIntegrationDirectory();

    /**
     * Path to directory with additional application content.
     */
    Path contentDirectory();

    /**
     * Default implementation of {@link ApplicationLayoutMixin} interface.
     */
    record Stub(Path launchersDirectory, Path appDirectory, Path appModsDirectory, Path desktopIntegrationDirectory, Path contentDirectory) implements ApplicationLayoutMixin {
    }
}
