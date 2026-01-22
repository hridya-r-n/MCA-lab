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
package jdk.jpackage.internal;

import java.nio.file.Path;
import java.util.Objects;
import java.util.Optional;
import jdk.jpackage.internal.model.ConfigException;
import jdk.jpackage.internal.model.WinExePackage;
import jdk.jpackage.internal.model.WinMsiPackage;

final class WinExePackageBuilder {

    WinExePackageBuilder(WinMsiPackage pkg) {
        this.pkg = Objects.requireNonNull(pkg);
    }

    WinExePackage create() throws ConfigException {
        if (icon != null) {
            LauncherBuilder.validateIcon(icon);
        }
        return WinExePackage.create(pkg, Optional.ofNullable(icon));
    }

    WinExePackageBuilder icon(Path v) {
        icon = v;
        return this;
    }

    private Path icon;

    private final WinMsiPackage pkg;
}
