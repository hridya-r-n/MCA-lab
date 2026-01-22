/*
 * Copyright (c) 2022, 2025, Oracle and/or its affiliates. All rights reserved.
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

import jdk.jpackage.internal.model.ApplicationLayout;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

final class PackageFile {

    /**
     * Returns path to package file.
     * @param appLayout - application layout
     */
    static Path getPathInAppImage(ApplicationLayout appLayout) {
        return appLayout.appDirectory().resolve(FILENAME);
    }

    PackageFile(String packageName) {
        this.packageName = Objects.requireNonNull(packageName);
    }

    void save(ApplicationLayout appLayout) throws IOException {
        final var dstDir = appLayout.appDirectory();
        Files.createDirectories(dstDir);
        Files.writeString(dstDir.resolve(FILENAME), packageName);
    }

    private final String packageName;

    private static final String FILENAME = ".package";
}
