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
import jdk.internal.util.OperatingSystem;
import jdk.jpackage.internal.model.ApplicationLayout;


final class ApplicationLayoutUtils {

    public static final ApplicationLayout PLATFORM_APPLICATION_LAYOUT;

    private static final ApplicationLayout WIN_APPLICATION_LAYOUT = ApplicationLayout.build()
            .setAll("")
            .appDirectory("app")
            .runtimeDirectory("runtime")
            .appModsDirectory(Path.of("app", "mods"))
            .create();

    private static final ApplicationLayout MAC_APPLICATION_LAYOUT = ApplicationLayout.build()
            .launchersDirectory("Contents/MacOS")
            .appDirectory("Contents/app")
            .runtimeDirectory("Contents/runtime/Contents/Home")
            .desktopIntegrationDirectory("Contents/Resources")
            .appModsDirectory("Contents/app/mods")
            .contentDirectory("Contents")
            .create();

    private static final ApplicationLayout LINUX_APPLICATION_LAYOUT = ApplicationLayout.build()
            .launchersDirectory("bin")
            .appDirectory("lib/app")
            .runtimeDirectory("lib/runtime")
            .desktopIntegrationDirectory("lib")
            .appModsDirectory("lib/app/mods")
            .contentDirectory("lib")
            .create();

    static {
        switch (OperatingSystem.current()) {
            case WINDOWS -> PLATFORM_APPLICATION_LAYOUT = WIN_APPLICATION_LAYOUT;
            case MACOS -> PLATFORM_APPLICATION_LAYOUT = MAC_APPLICATION_LAYOUT;
            case LINUX -> PLATFORM_APPLICATION_LAYOUT = LINUX_APPLICATION_LAYOUT;
            default -> {
                throw new UnsupportedOperationException();
            }
        }
    }
}
