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
import java.io.Reader;
import java.lang.module.ModuleDescriptor;
import java.lang.module.ModuleReference;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Properties;
import jdk.internal.util.OperatingSystem;

record ModuleInfo(String name, Optional<String> version, Optional<String> mainClass, Optional<URI> location) {

    Optional<Path> fileLocation() {
        return location.filter(loc -> {
            return loc.getScheme().equals("file");
        }).map(Path::of);
    }

    static ModuleInfo fromModuleReference(ModuleReference mr) {
        final var md = mr.descriptor();
        return new ModuleInfo(md.name(), md.version().map(ModuleDescriptor.Version::toString).or(md::rawVersion), md.mainClass(), mr.location());
    }

    static Optional<ModuleInfo> fromCookedRuntime(String moduleName, Path cookedRuntime) {
        Objects.requireNonNull(moduleName);
        Objects.requireNonNull(cookedRuntime);

        // We can't extract info about version and main class of a module
        // linked in external runtime without running ModuleFinder in that
        // runtime. But this is too much work as the runtime might have been
        // coocked without native launchers. So just make sure the module
        // is linked in the runtime by simply analysing the data
        // of `release` file.

        final Path releaseFile;
        if (!OperatingSystem.isMacOS()) {
            releaseFile = cookedRuntime.resolve("release");
        } else {
            // On Mac `cookedRuntime` can be runtime root or runtime home.
            Path runtimeHome = cookedRuntime.resolve("Contents/Home");
            if (!Files.isDirectory(runtimeHome)) {
                runtimeHome = cookedRuntime;
            }
            releaseFile = runtimeHome.resolve("release");
        }

        try (Reader reader = Files.newBufferedReader(releaseFile)) {
            Properties props = new Properties();
            props.load(reader);
            String moduleList = props.getProperty("MODULES");
            if (moduleList == null) {
                return Optional.empty();
            }

            if ((moduleList.startsWith("\"") && moduleList.endsWith("\""))
                    || (moduleList.startsWith("\'") && moduleList.endsWith(
                    "\'"))) {
                moduleList = moduleList.substring(1, moduleList.length() - 1);
            }

            if (!List.of(moduleList.split("\\s+")).contains(moduleName)) {
                return Optional.empty();
            }
        } catch (IOException|IllegalArgumentException ex) {
            Log.verbose(ex);
            return Optional.empty();
        }

        return Optional.of(new ModuleInfo(moduleName, Optional.empty(), Optional.empty(), Optional.empty()));
    }
}
