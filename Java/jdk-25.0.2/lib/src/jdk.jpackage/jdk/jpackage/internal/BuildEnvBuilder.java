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

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.Optional;
import jdk.jpackage.internal.model.Application;
import jdk.jpackage.internal.model.ConfigException;
import jdk.jpackage.internal.resources.ResourceLocator;

final class BuildEnvBuilder {

    BuildEnvBuilder(Path root) {
        this.root = Objects.requireNonNull(root);
    }

    BuildEnv create() throws ConfigException {
        Objects.requireNonNull(appImageDir);

        var exceptionBuilder = I18N.buildConfigException("ERR_BuildRootInvalid", root);
        if (Files.isDirectory(root)) {
            try (var rootDirContents = Files.list(root)) {
                if (rootDirContents.findAny().isPresent()) {
                    // The root directory is not empty.
                    throw exceptionBuilder.create();
                }
            } catch (IOException ioe) {
                throw exceptionBuilder.cause(ioe).create();
            }
        } else if (Files.exists(root)) {
            // The root is not a directory.
            throw exceptionBuilder.create();
        }

        return BuildEnv.withAppImageDir(BuildEnv.create(root, Optional.ofNullable(resourceDir),
                verbose, ResourceLocator.class), appImageDir);
    }

    BuildEnvBuilder verbose(boolean v) {
        verbose = v;
        return this;
    }

    BuildEnvBuilder resourceDir(Path v) {
        resourceDir = v;
        return this;
    }

    BuildEnvBuilder appImageDir(Path v) {
        appImageDir = v;
        return this;
    }

    BuildEnvBuilder appImageDirFor(Application app) {
        appImageDir = defaultAppImageDir(root).resolve(app.appImageDirName());
        return this;
    }

    BuildEnvBuilder appImageDirForPackage() {
        appImageDir = defaultAppImageDir(root);
        return this;
    }

    private static Path defaultAppImageDir(Path root) {
        return root.resolve("image");
    }

    private Path appImageDir;
    private Path resourceDir;
    private boolean verbose;

    private final Path root;
}
