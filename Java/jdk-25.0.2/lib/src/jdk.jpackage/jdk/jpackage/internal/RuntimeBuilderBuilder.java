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

import static jdk.jpackage.internal.I18N.buildConfigException;
import static jdk.jpackage.internal.model.RuntimeBuilder.getDefaultModulePath;
import static jdk.jpackage.internal.util.function.ThrowingSupplier.toSupplier;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.Supplier;
import jdk.jpackage.internal.model.ApplicationLayout;
import jdk.jpackage.internal.model.ConfigException;
import jdk.jpackage.internal.model.LauncherStartupInfo;
import jdk.jpackage.internal.model.RuntimeBuilder;
import jdk.jpackage.internal.util.FileUtils;
import jdk.jpackage.internal.util.PathGroup;

final class RuntimeBuilderBuilder {

    RuntimeBuilder create() {
        return impl.get();
    }

    RuntimeBuilderBuilder modulePath(List<Path> v) {
        modulePath = v;
        return this;
    }

    RuntimeBuilderBuilder forRuntime(Path predefinedRuntimeImage) {
        impl = new CopyingRuntime(this, predefinedRuntimeImage);
        return this;
    }

    RuntimeBuilderConfigBuilder forNewRuntime(List<LauncherStartupInfo> startupInfos) {
        return new RuntimeBuilderConfigBuilder(startupInfos);
    }

    final class RuntimeBuilderConfigBuilder {

        RuntimeBuilderConfigBuilder(List<LauncherStartupInfo> startupInfos) {
            Objects.requireNonNull(startupInfos);
            if (startupInfos.isEmpty()) {
                throw new IllegalArgumentException();
            }
            this.startupInfos = startupInfos;
        }

        RuntimeBuilderBuilder appy() {
            impl = new BuildingRuntime(RuntimeBuilderBuilder.this, addModules,
                    limitModules, validatedOptions(), startupInfos);
            return RuntimeBuilderBuilder.this;
        }

        RuntimeBuilderConfigBuilder addModules(Set<String> v) {
            addModules = v;
            return this;
        }

        RuntimeBuilderConfigBuilder limitModules(Set<String> v) {
            limitModules = v;
            return this;
        }

        RuntimeBuilderConfigBuilder options(List<String> v) {
            options = v;
            return this;
        }

        private List<String> validatedOptions() {
            return Optional.ofNullable(options).orElse(DEFAULT_JLINK_OPTIONS);
        }

        private Set<String> addModules;
        private Set<String> limitModules;
        private List<String> options;
        private final List<LauncherStartupInfo> startupInfos;

        private static final List<String> DEFAULT_JLINK_OPTIONS = List.of(
                "--strip-native-commands",
                "--strip-debug",
                "--no-man-pages",
                "--no-header-files"
        );
    }

    private static RuntimeBuilder createCopyingRuntimeBuilder(Path runtimeDir,
            Path... modulePath) throws ConfigException {
        if (!Files.exists(runtimeDir)) {
            throw buildConfigException()
                    .message("message.runtime-image-dir-does-not-exist",
                            "--runtime-image", runtimeDir)
                    .advice("message.runtime-image-dir-does-not-exist.advice",
                            "--runtime-image")
                    .create();
        }

        return appImageLayout -> {
            try {
                // copy whole runtime, skipping jmods and src.zip
                final var srcPathGroup = new PathGroup(Map.of("root", runtimeDir));
                // JDK8
                srcPathGroup.ghostPath(runtimeDir.resolve("src.zip"));
                // Newer JDKs
                srcPathGroup.ghostPath(runtimeDir.resolve("lib/src.zip"));
                srcPathGroup.ghostPath(runtimeDir.resolve("jmods"));

                srcPathGroup.copy(new PathGroup(Map.of("root", appImageLayout.runtimeDirectory())),
                        LinkOption.NOFOLLOW_LINKS);

                // if module-path given - copy modules to appDir/mods
                List<Path> defaultModulePath = getDefaultModulePath();
                Path dest = ((ApplicationLayout)appImageLayout).appModsDirectory();

                for (Path mp : modulePath) {
                    if (!defaultModulePath.contains(mp.toAbsolutePath())) {
                        FileUtils.copyRecursive(mp, dest);
                    }
                }
            } catch (IOException ex) {
                throw new UncheckedIOException(ex);
            }
        };
    }

    private record CopyingRuntime(RuntimeBuilderBuilder thiz, Path predefinedRuntimeImage)
            implements Supplier<RuntimeBuilder> {

        @Override
        public RuntimeBuilder get() {
            return toSupplier(() -> createCopyingRuntimeBuilder(
                    predefinedRuntimeImage,
                    Optional.ofNullable(thiz.modulePath).orElseGet(List::of).toArray(Path[]::new))
            ).get();
        }
    }

    private record BuildingRuntime(RuntimeBuilderBuilder thiz, Set<String> addModules,
            Set<String> limitModules, List<String> options,
            List<LauncherStartupInfo> startupInfos) implements Supplier<RuntimeBuilder> {

        @Override
        public RuntimeBuilder get() {
            return toSupplier(() -> JLinkRuntimeBuilder.createJLinkRuntimeBuilder(
                    Optional.ofNullable(thiz.modulePath).orElseGet(List::of),
                    Optional.ofNullable(addModules).orElseGet(Set::of),
                    Optional.ofNullable(limitModules).orElseGet(Set::of),
                    Optional.ofNullable(options).orElseGet(List::of),
                    startupInfos)
            ).get();
        }
    }

    private List<Path> modulePath;
    private Supplier<RuntimeBuilder> impl;
}
