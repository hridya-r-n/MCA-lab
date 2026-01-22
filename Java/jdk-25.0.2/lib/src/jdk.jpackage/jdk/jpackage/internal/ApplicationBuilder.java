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

import java.nio.file.Path;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import jdk.jpackage.internal.model.AppImageLayout;
import jdk.jpackage.internal.model.Application;
import jdk.jpackage.internal.model.ApplicationLaunchers;
import jdk.jpackage.internal.model.ConfigException;
import jdk.jpackage.internal.model.ExternalApplication;
import jdk.jpackage.internal.model.ExternalApplication.LauncherInfo;
import jdk.jpackage.internal.model.Launcher;
import jdk.jpackage.internal.model.LauncherStartupInfo;
import jdk.jpackage.internal.model.RuntimeBuilder;

final class ApplicationBuilder {

    Application create() throws ConfigException {
        Objects.requireNonNull(appImageLayout);

        final var launchersAsList = Optional.ofNullable(launchers).map(
                ApplicationLaunchers::asList).orElseGet(List::of);

        final var launcherCount = launchersAsList.size();

        if (launcherCount != launchersAsList.stream().map(Launcher::name).distinct().count()) {
            throw buildConfigException("ERR_NoUniqueName").create();
        }

        final String effectiveName;
        if (name != null) {
            effectiveName = name;
        } else if (!launchersAsList.isEmpty()) {
            effectiveName = launchers.mainLauncher().name();
        } else {
            throw buildConfigException("error.no.name").advice("error.no.name.advice").create();
        }

        Objects.requireNonNull(launchersAsList);

        return new Application.Stub(
                effectiveName,
                Optional.ofNullable(description).orElse(effectiveName),
                Optional.ofNullable(version).orElseGet(DEFAULTS::version),
                Optional.ofNullable(vendor).orElseGet(DEFAULTS::vendor),
                Optional.ofNullable(copyright).orElseGet(DEFAULTS::copyright),
                Optional.ofNullable(srcDir),
                Optional.ofNullable(contentDirs).orElseGet(List::of),
                appImageLayout, Optional.ofNullable(runtimeBuilder), launchersAsList, Map.of());
    }

    ApplicationBuilder runtimeBuilder(RuntimeBuilder v) {
        runtimeBuilder = v;
        return this;
    }

    ApplicationBuilder initFromExternalApplication(ExternalApplication app,
            Function<LauncherInfo, Launcher> mapper) {
        if (version == null) {
            version = app.getAppVersion();
        }
        if (name == null) {
            name = app.getAppName();
        }
        runtimeBuilder = null;

        var mainLauncherInfo = new LauncherInfo(app.getLauncherName(), false, Map.of());

        launchers = new ApplicationLaunchers(
                mapper.apply(mainLauncherInfo),
                app.getAddLaunchers().stream().map(mapper).toList());

        return this;
    }

    ApplicationBuilder launchers(ApplicationLaunchers v) {
        launchers = v;
        return this;
    }

    Optional<ApplicationLaunchers> launchers() {
        return Optional.ofNullable(launchers);
    }

    ApplicationBuilder appImageLayout(AppImageLayout v) {
        appImageLayout = v;
        return this;
    }

    ApplicationBuilder name(String v) {
        name = v;
        return this;
    }

    ApplicationBuilder description(String v) {
        description = v;
        return this;
    }

    ApplicationBuilder version(String v) {
        version = v;
        return this;
    }

    ApplicationBuilder vendor(String v) {
        vendor = v;
        return this;
    }

    ApplicationBuilder copyright(String v) {
        copyright = v;
        return this;
    }

    ApplicationBuilder srcDir(Path v) {
        srcDir = v;
        return this;
    }

    ApplicationBuilder contentDirs(List<Path> v) {
        contentDirs = v;
        return this;
    }

    static Launcher overrideLauncherStartupInfo(Launcher launcher, LauncherStartupInfo startupInfo) {
        return new Launcher.Stub(launcher.name(), Optional.of(startupInfo),
                launcher.fileAssociations(), launcher.isService(), launcher.description(),
                launcher.icon(), launcher.defaultIconResourceName(), launcher.extraAppImageFileData());
    }

    record MainLauncherStartupInfo(String qualifiedClassName) implements LauncherStartupInfo {
        @Override
        public List<String> javaOptions() {
            throw new UnsupportedOperationException();
        }

        @Override
        public List<String> defaultParameters() {
            throw new UnsupportedOperationException();
        }

        @Override
        public List<Path> classPath() {
            throw new UnsupportedOperationException();
        }
    }

    private record Defaults(String version, String vendor) {
        String copyright() {
            return I18N.format("param.copyright.default", new Date());
        }
    }

    private String name;
    private String description;
    private String version;
    private String vendor;
    private String copyright;
    private Path srcDir;
    private List<Path> contentDirs;
    private AppImageLayout appImageLayout;
    private RuntimeBuilder runtimeBuilder;
    private ApplicationLaunchers launchers;

    private static final Defaults DEFAULTS = new Defaults(
            "1.0",
            I18N.getString("param.vendor.default"));
}
