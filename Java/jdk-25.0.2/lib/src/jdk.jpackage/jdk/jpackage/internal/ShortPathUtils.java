/*
 * Copyright (c) 2024, Oracle and/or its affiliates. All rights reserved.
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

import java.nio.file.Files;
import java.nio.file.Path;
import java.text.MessageFormat;
import java.util.Objects;
import java.util.Optional;


@SuppressWarnings("restricted")
final class ShortPathUtils {
    static String adjustPath(String path) {
        return toShortPath(path).orElse(path);
    }

    static Path adjustPath(Path path) {
        return toShortPath(path).orElse(path);
    }

    static Optional<String> toShortPath(String path) {
        Objects.requireNonNull(path);
        return toShortPath(Path.of(path)).map(Path::toString);
    }

    static Optional<Path> toShortPath(Path path) {
        if (!Files.exists(path)) {
            throw new IllegalArgumentException(String.format("[%s] path does not exist", path));
        }

        var normPath = path.normalize().toAbsolutePath().toString();
        if (normPath.length() > MAX_PATH) {
            return Optional.of(Path.of(getShortPathWrapper(normPath)));
        } else {
            return Optional.empty();
        }
    }

    private static String getShortPathWrapper(final String longPath) {
        String effectivePath;
        if (!longPath.startsWith(LONG_PATH_PREFIX)) {
            effectivePath = LONG_PATH_PREFIX + longPath;
        } else {
            effectivePath = longPath;
        }

        return Optional.ofNullable(getShortPath(effectivePath)).orElseThrow(
                () -> new ShortPathException(MessageFormat.format(I18N.getString(
                        "error.short-path-conv-fail"), effectivePath)));
    }

    static final class ShortPathException extends RuntimeException {

        ShortPathException(String msg) {
            super(msg);
        }

        private static final long serialVersionUID = 1L;
    }

    private static native String getShortPath(String longPath);

    private static final int MAX_PATH = 240;
    // See https://learn.microsoft.com/en-us/windows/win32/api/fileapi/nf-fileapi-getshortpathnamew
    private static final String LONG_PATH_PREFIX = "\\\\?\\";

    static {
        System.loadLibrary("jpackage");
    }
}
