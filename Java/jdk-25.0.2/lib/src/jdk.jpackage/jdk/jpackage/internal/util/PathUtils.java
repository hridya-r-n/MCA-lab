/*
 * Copyright (c) 2024, 2025, Oracle and/or its affiliates. All rights reserved.
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
package jdk.jpackage.internal.util;

import java.nio.file.Path;
import java.util.Optional;

public final class PathUtils {

    public static String getSuffix(Path path) {
        String filename = replaceSuffix(path.getFileName(), null).toString();
        return path.getFileName().toString().substring(filename.length());
    }

    public static Path addSuffix(Path path, String suffix) {
        Path parent = path.getParent();
        String filename = path.getFileName().toString() + suffix;
        return parent != null ? parent.resolve(filename) : Path.of(filename);
    }

    public static Path replaceSuffix(Path path, String suffix) {
        Path parent = path.getParent();
        String filename = path.getFileName().toString().replaceAll("\\.[^.]*$",
                "") + Optional.ofNullable(suffix).orElse("");
        return parent != null ? parent.resolve(filename) : Path.of(filename);
    }

    public static Path resolveNullablePath(Path base, Path path) {
        return Optional.ofNullable(path).map(base::resolve).orElse(null);
    }

    public static Path normalizedAbsolutePath(Path path) {
        if (path != null) {
            return path.normalize().toAbsolutePath();
        } else {
            return null;
        }
    }

    public static String normalizedAbsolutePathString(Path path) {
        if (path != null) {
            return normalizedAbsolutePath(path).toString();
        } else {
            return null;
        }
    }
}
