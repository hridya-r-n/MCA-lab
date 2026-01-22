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
import java.util.Optional;
import static jdk.jpackage.internal.model.StandardPackageType.WIN_EXE;
import jdk.jpackage.internal.util.CompositeProxy;

public interface WinExePackage extends Package, WinExePackageMixin {

    public static WinExePackage create(WinMsiPackage msiPackage, Optional<Path> icon) {
        return CompositeProxy.create(WinExePackage.class, createExePackage(
                msiPackage), new WinExePackageMixin.Stub(msiPackage, icon));
    }

    private static Package createExePackage(WinMsiPackage pkg) {
        return new Package.Stub(
                pkg.app(),
                WIN_EXE,
                pkg.packageName(),
                pkg.description(),
                pkg.version(),
                pkg.aboutURL(),
                pkg.licenseFile(),
                pkg.predefinedAppImage(),
                pkg.relativeInstallDir());
    }
}
