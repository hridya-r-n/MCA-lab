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
 * App image packaging type.
 *
 * @see StandardPackageType
 */
public final class AppImagePackageType implements PackageType {

    private AppImagePackageType() {
    }

    /**
     * Singleton
     */
    public static final AppImagePackageType APP_IMAGE = new AppImagePackageType();
}
