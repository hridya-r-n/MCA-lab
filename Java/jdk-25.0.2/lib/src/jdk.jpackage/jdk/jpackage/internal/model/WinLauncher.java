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

import static java.util.stream.Collectors.toMap;

import java.io.InputStream;
import java.util.Map;
import java.util.Optional;
import jdk.jpackage.internal.resources.ResourceLocator;
import jdk.jpackage.internal.util.CompositeProxy;

public interface WinLauncher extends Launcher, WinLauncherMixin {

    @Override
    default Optional<String> executableSuffix() {
        return Optional.of(".exe");
    }

    @Override
    default InputStream executableResource() {
        return ResourceLocator.class.getResourceAsStream(
                isConsole() ? "jpackageapplauncher.exe" : "jpackageapplauncherw.exe");
    }

    @Override
    default Map<String, String> extraAppImageFileData() {
        return shortcuts().stream().collect(toMap(WinShortcut::name, v -> Boolean.toString(true)));
    }

    public static WinLauncher create(Launcher launcher, WinLauncherMixin mixin) {
        return CompositeProxy.create(WinLauncher.class, launcher, mixin);
    }
}
