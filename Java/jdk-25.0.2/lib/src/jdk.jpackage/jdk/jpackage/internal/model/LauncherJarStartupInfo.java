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

import jdk.jpackage.internal.util.CompositeProxy;

/**
 * Application launcher startup configuration using non-modular jar file.
 * <p>
 * Use {@link #create} method to create objects implementing this interface.
 */
public interface LauncherJarStartupInfo extends LauncherStartupInfo,
        LauncherJarStartupInfoMixin {

    /**
     * Constructs {@link LauncherJarStartupInfo} instance from the given
     * {@link LauncherJarStartupInfo} and {@link LauncherJarStartupInfoMixin} instances.
     *
     * @param info the generic launcher startup information
     * @param mixin the supplementary launcher startup information
     * @return the proxy dispatching calls to the given objects
     */
    public static LauncherJarStartupInfo create(LauncherStartupInfo info,
            LauncherJarStartupInfoMixin mixin) {
        return CompositeProxy.create(LauncherJarStartupInfo.class, info, mixin);
    }
}
