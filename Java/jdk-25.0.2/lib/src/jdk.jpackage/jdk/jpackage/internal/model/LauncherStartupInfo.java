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

import java.lang.constant.ClassDesc;
import java.nio.file.Path;
import java.util.List;

/**
 * Generic startup configuration of an application launcher.
 *
 * @see Launcher#startupInfo()
 */
public interface LauncherStartupInfo {

    /**
     * Gets the qualified name of the main class of this launcher startup configuration.
     * @return the qualified name of the main class of this launcher startup configuration
     */
    String qualifiedClassName();

    /**
     * Returns the simple name of the main class of this launcher startup configuration as given in the source code.
     * @return the simple name of the main class of this launcher startup configuration as given in the source code
     */
    default String simpleClassName() {
        return ClassDesc.of(qualifiedClassName()).displayName();
    }

    /**
     * Gets the package name of the main class of this launcher startup configuration.
     * @return the package name of the main class of this launcher startup configuration
     */
    default String packageName() {
        return ClassDesc.of(qualifiedClassName()).packageName();
    }

    /**
     * Gets JVM options of this launcher startup configuration.
     * @return the JVM options of this launcher startup configuration
     */
    List<String> javaOptions();

    /**
     * Gets the default parameters for the <code>main(String[] args)</code>
     * method of the main class of this launcher startup configuration.
     *
     * @return the default parameters for the <code>main(String[] args)</code>
     * method of the main class of this launcher startup configuration
     */
    List<String> defaultParameters();

    /**
     * Gets the files and directories that should be put on a classpath for
     * an application launcher this launcher startup configuration applies to.
     * @return the classpath of this launcher startup configuration
     */
    List<Path> classPath();

    /**
     * Default implementation of {@link LauncherStartupInfo} interface.
     */
    record Stub(String qualifiedClassName, List<String> javaOptions,
            List<String> defaultParameters, List<Path> classPath)
            implements LauncherStartupInfo {

    }
}
