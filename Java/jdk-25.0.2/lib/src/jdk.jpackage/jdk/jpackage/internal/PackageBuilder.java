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
import java.util.Objects;
import java.util.Optional;
import jdk.jpackage.internal.model.Application;
import jdk.jpackage.internal.model.ConfigException;
import jdk.jpackage.internal.model.Package;
import jdk.jpackage.internal.model.Package.Stub;
import jdk.jpackage.internal.model.PackageType;
import jdk.jpackage.internal.model.StandardPackageType;

final class PackageBuilder {

    PackageBuilder(Application app, PackageType type) {
        this.app = Objects.requireNonNull(app);
        this.type = Objects.requireNonNull(type);
    }

    Package create() throws ConfigException {
        final var validatedName = validatedName();

        Path relativeInstallDir;
        if (installDir != null) {
            var normalizedInstallDir = mapInstallDir(installDir, type);
            if (type instanceof StandardPackageType stdType) {
                Optional<Path> installDirName = Optional.of(Path.of(validatedName));
                switch (stdType) {
                    case LINUX_DEB, LINUX_RPM -> {
                        switch (normalizedInstallDir.toString()) {
                            case "/usr", "/usr/local" -> {
                                installDirName = Optional.empty();
                            }
                        }
                    }
                    case WIN_EXE, WIN_MSI -> {
                        installDirName = Optional.empty();
                    }
                    case MAC_DMG, MAC_PKG -> {
                        installDirName = Optional.of(app.appImageDirName());
                    }
                }
                normalizedInstallDir = installDirName.map(normalizedInstallDir::resolve).orElse(normalizedInstallDir);
            }
            relativeInstallDir = normalizedInstallDir;
        } else {
            relativeInstallDir = defaultInstallDir().orElseThrow(UnsupportedOperationException::new);
        }

        if (relativeInstallDir.isAbsolute()) {
            relativeInstallDir = relativeInstallDir.getRoot().relativize(relativeInstallDir);
        }

        return new Stub(
                app,
                type,
                validatedName,
                Optional.ofNullable(description).orElseGet(app::description),
                version = Optional.ofNullable(version).orElseGet(app::version),
                Optional.ofNullable(aboutURL),
                Optional.ofNullable(licenseFile),
                Optional.ofNullable(predefinedAppImage),
                relativeInstallDir);
    }

    PackageBuilder name(String v) {
        name = v;
        return this;
    }

    Optional<String> name() {
        return Optional.ofNullable(name);
    }

    PackageBuilder fileName(Path v) {
        fileName = v;
        return this;
    }

    Optional<Path> fileName() {
        return Optional.ofNullable(fileName);
    }

    PackageBuilder description(String v) {
        description = v;
        return this;
    }

    Optional<String> description() {
        return Optional.ofNullable(description);
    }

    PackageBuilder version(String v) {
        version = v;
        return this;
    }

    Optional<String> version() {
        return Optional.ofNullable(version);
    }

    PackageBuilder aboutURL(String v) {
        aboutURL = v;
        return this;
    }

    Optional<String> aboutURL() {
        return Optional.ofNullable(aboutURL);
    }

    PackageBuilder licenseFile(Path v) {
        licenseFile = v;
        return this;
    }

    Optional<Path> licenseFile() {
        return Optional.ofNullable(licenseFile);
    }

    PackageBuilder predefinedAppImage(Path v) {
        predefinedAppImage = v;
        return this;
    }

    Optional<Path> predefinedAppImage() {
        return Optional.ofNullable(predefinedAppImage);
    }

    PackageBuilder installDir(Path v) {
        installDir = v;
        return this;
    }

    Optional<Path> installDir() {
        return Optional.ofNullable(installDir);
    }

    Optional<Path> defaultInstallDir() {
        if (type instanceof StandardPackageType stdType) {
            return defaultInstallDir(stdType, validatedName(), app);
        } else {
            return Optional.empty();
        }
    }

    private String validatedName() {
        return name().orElseGet(app::name);
    }

    private static Path mapInstallDir(Path installDir, PackageType pkgType)
            throws ConfigException {
        var ex = buildConfigException("error.invalid-install-dir", installDir).create();

        if (installDir.getNameCount() == 0) {
            throw ex;
        }

        if (installDir.getFileName().equals(Path.of(""))) {
            // Trailing '/' or '\\'. Strip them away.
            installDir = installDir.getParent();
        }

        if (installDir.toString().isEmpty()) {
            throw ex;
        }

        switch (pkgType) {
            case StandardPackageType.WIN_EXE, StandardPackageType.WIN_MSI -> {
                if (installDir.isAbsolute()) {
                    throw ex;
                }
            }
            default -> {
                if (!installDir.isAbsolute()) {
                    throw ex;
                }
            }
        }

        if (!installDir.normalize().toString().equals(installDir.toString())) {
            // Don't allow '..' or '.' in path components
            throw ex;
        }

        return installDir;
    }

    private static Optional<Path> defaultInstallDir(StandardPackageType pkgType, String pkgName, Application app) {
        switch (pkgType) {
            case WIN_EXE, WIN_MSI -> {
                return Optional.of(app.appImageDirName());
            }
            case LINUX_DEB, LINUX_RPM -> {
                return Optional.of(Path.of("/opt").resolve(pkgName));
            }
            case MAC_DMG, MAC_PKG -> {
                final Path dirName = app.appImageDirName();
                final Path base;
                if (app.isRuntime()) {
                    base = Path.of("/Library/Java/JavaVirtualMachines");
                } else {
                    base = Path.of("/Applications");
                }
                return Optional.of(base.resolve(dirName));
            }
            default -> {
                return Optional.empty();
            }
        }
    }

    private String name;
    private Path fileName;
    private String description;
    private String version;
    private String aboutURL;
    private Path licenseFile;
    private Path predefinedAppImage;
    private Path installDir;

    private final PackageType type;
    private final Application app;
}
