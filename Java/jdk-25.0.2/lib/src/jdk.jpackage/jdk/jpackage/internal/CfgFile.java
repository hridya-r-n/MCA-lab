/*
 * Copyright (c) 2020, 2025, Oracle and/or its affiliates. All rights reserved.
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

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;
import jdk.jpackage.internal.model.Application;
import jdk.jpackage.internal.model.ApplicationLayout;
import jdk.jpackage.internal.model.Launcher;
import jdk.jpackage.internal.model.LauncherJarStartupInfo;
import jdk.jpackage.internal.model.LauncherModularStartupInfo;
import jdk.jpackage.internal.model.LauncherStartupInfo;


/**
 * App launcher's config file.
 */
final class CfgFile {
    CfgFile(Application app, Launcher launcher) {
        startupInfo = launcher.startupInfo().orElseThrow();
        outputFileName = launcher.executableName() + ".cfg";
        version = app.version();
    }

    void create(ApplicationLayout appLayout) throws IOException {
        List<Map.Entry<String, Object>> content = new ArrayList<>();

        final var refs = new Referencies(appLayout);

        content.add(Map.entry("[Application]", SECTION_TAG));

        if (startupInfo instanceof LauncherModularStartupInfo modularStartupInfo) {
            content.add(Map.entry("app.mainmodule", modularStartupInfo.moduleName()
                    + "/" + startupInfo.qualifiedClassName()));
        } else if (startupInfo instanceof LauncherJarStartupInfo jarStartupInfo) {
            Path mainJarPath = refs.appDirectory().resolve(jarStartupInfo.jarPath());

            if (jarStartupInfo.isJarWithMainClass()) {
                content.add(Map.entry("app.mainjar", mainJarPath));
            } else {
                content.add(Map.entry("app.classpath", mainJarPath));
            }

            if (!jarStartupInfo.isJarWithMainClass()) {
                content.add(Map.entry("app.mainclass", startupInfo.qualifiedClassName()));
            }
        } else {
            throw new UnsupportedOperationException();
        }

        for (var value : Optional.ofNullable(startupInfo.classPath()).orElseGet(List::of)) {
            content.add(Map.entry("app.classpath",
                    refs.appDirectory().resolve(value).toString()));
        }

        content.add(Map.entry("[JavaOptions]", SECTION_TAG));

        // always let app know it's version
        content.add(Map.entry(
                "java-options", "-Djpackage.app-version=" + version));

        // add user supplied java options if there are any
        for (var value : Optional.ofNullable(startupInfo.javaOptions()).orElseGet(List::of)) {
            content.add(Map.entry("java-options", value));
        }

        // add module path if there is one
        if (Files.isDirectory(appLayout.appModsDirectory())) {
            content.add(Map.entry("java-options", "--module-path"));
            content.add(Map.entry("java-options", refs.appModsDirectory()));
        }

        var arguments = Optional.ofNullable(startupInfo.defaultParameters()).orElseGet(List::of);
        if (!arguments.isEmpty()) {
            content.add(Map.entry("[ArgOptions]", SECTION_TAG));
            for (var value : arguments) {
                content.add(Map.entry("arguments", value));
            }
        }

        Path cfgFile = appLayout.appDirectory().resolve(outputFileName);
        Files.createDirectories(cfgFile.getParent());

        boolean[] addLineBreakAtSection = new boolean[1];
        Stream<String> lines = content.stream().map(entry -> {
            if (entry.getValue() == SECTION_TAG) {
                if (!addLineBreakAtSection[0]) {
                    addLineBreakAtSection[0] = true;
                    return entry.getKey();
                }
                return "\n" + entry.getKey();
            }
            return entry.getKey() + "=" + entry.getValue();
        });
        Files.write(cfgFile, (Iterable<String>) lines::iterator);
    }

    private record Referencies(Path appModsDirectory) {

        Referencies {
            if (!appModsDirectory.startsWith(appDirectory())) {
                throw new IllegalArgumentException();
            }
        }

        Referencies(ApplicationLayout appLayout) {
            this(Path.of("$APPDIR").resolve(appLayout.appModsDirectory().getFileName()));
        }

        Path appDirectory() {
            return Path.of("$APPDIR");
        }
    }

    private final LauncherStartupInfo startupInfo;
    private final String version;
    private final String outputFileName;

    private static final Object SECTION_TAG = new Object();
}
