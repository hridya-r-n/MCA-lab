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

interface BuildEnv {

    Path buildRoot();

    boolean verbose();

    Optional<Path> resourceDir();

    /**
     * Returns path to application image directory.
     *
     * The return value is supposed to be used as a parameter for
     * ApplicationLayout#resolveAt function.
     */
    default Path appImageDir() {
        return buildRoot().resolve("image");
    }

    default Path configDir() {
        return buildRoot().resolve("config");
    }

    OverridableResource createResource(String defaultName);

    static BuildEnv withAppImageDir(BuildEnv env, Path appImageDir) {
        return ((Internal.DefaultBuildEnv)env).copyWithAppImageDir(appImageDir);
    }

    static BuildEnv create(Path buildRoot, Optional<Path> resourceDir, boolean verbose, Class<?> resourceLocator) {
        return new Internal.DefaultBuildEnv(buildRoot, resourceDir, verbose, resourceLocator, Optional.empty());
    }

    static final class Internal {
        private static record DefaultBuildEnv(Path buildRoot, Optional<Path> resourceDir,
                boolean verbose, Class<?> resourceLocator, Optional<Path> optAppImageDir) implements BuildEnv {

            DefaultBuildEnv {
                Objects.requireNonNull(buildRoot);
                Objects.requireNonNull(resourceDir);
                Objects.requireNonNull(resourceLocator);
                Objects.requireNonNull(optAppImageDir);
            }

            DefaultBuildEnv copyWithAppImageDir(Path appImageDir) {
                return new DefaultBuildEnv(buildRoot, resourceDir, verbose, resourceLocator, Optional.of(appImageDir));
            }

            @Override
            public Path appImageDir() {
                return optAppImageDir.orElseGet(BuildEnv.super::appImageDir);
            }

            @Override
            public OverridableResource createResource(String defaultName) {
                final OverridableResource resource;
                if (defaultName != null) {
                    resource = new OverridableResource(defaultName, resourceLocator);
                } else {
                    resource = new OverridableResource();
                }
                return resourceDir.map(resource::setResourceDir).orElse(resource);
            }
        }
    }
}
