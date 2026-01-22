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
 * Application launcher startup configuration using Java module.
 * <p>
 * Use {@link #create} method to create objects implementing this interface.
 */
public interface LauncherModularStartupInfo extends LauncherStartupInfo,
        LauncherModularStartupInfoMixin {

    /**
     * Constructs {@link LauncherModularStartupInfo} instance from the given
     * {@link LauncherJarStartupInfo} and {@link LauncherModularStartupInfoMixin} instances.
     *
     * @param info the generic launcher startup information
     * @param mixin the supplementary launcher startup information
     * @return the proxy dispatching calls to the given objects
     */
    public static LauncherModularStartupInfo create(LauncherStartupInfo info,
            LauncherModularStartupInfoMixin mixin) {
        return CompositeProxy.create(LauncherModularStartupInfo.class, info, mixin);
    }
}
