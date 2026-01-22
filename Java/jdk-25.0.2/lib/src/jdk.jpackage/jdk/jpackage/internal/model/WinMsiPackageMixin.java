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
import java.util.UUID;

public interface WinMsiPackageMixin {

    DottedVersion msiVersion();

    boolean withInstallDirChooser();

    boolean withShortcutPrompt();

    Optional<String> helpURL();

    Optional<String> updateURL();

    String startMenuGroupName();

    boolean isSystemWideInstall();

    UUID upgradeCode();

    UUID productCode();

    Optional<Path> serviceInstaller();

    record Stub(DottedVersion msiVersion, boolean withInstallDirChooser, boolean withShortcutPrompt,
            Optional<String> helpURL, Optional<String> updateURL, String startMenuGroupName,
            boolean isSystemWideInstall, UUID upgradeCode, UUID productCode,
            Optional<Path> serviceInstaller) implements WinMsiPackageMixin {}
}
