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

/**
 * Details of application launcher startup configuration using Java module.
 */
public interface LauncherModularStartupInfoMixin {

    /**
     * Gets the main module name.
     * @return the main module name
     */
    String moduleName();

    /**
     * Default implementation of {@link LauncherModularStartupInfoMixin} interface.
     */
    record Stub(String moduleName) implements LauncherModularStartupInfoMixin {
    }

}
