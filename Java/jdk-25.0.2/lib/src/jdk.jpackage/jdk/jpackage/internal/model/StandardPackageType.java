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
 * Standard native package types.
 */
public enum StandardPackageType implements PackageType {
    WIN_MSI(".msi"),
    WIN_EXE(".exe"),
    LINUX_DEB(".deb"),
    LINUX_RPM(".rpm"),
    MAC_PKG(".pkg"),
    MAC_DMG(".dmg");

    StandardPackageType(String suffix) {
        this.suffix = suffix;
    }

    /**
     * Gets file extension of this package type.
     * E.g.: <code>.msi</code>, <code>.dmg</code>, <code>.deb</code>.
     * @return file extension of this package type
     */
    public String suffix() {
        return suffix;
    }

    private final String suffix;
}
