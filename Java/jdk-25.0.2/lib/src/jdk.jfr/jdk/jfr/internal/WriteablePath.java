/*
 * Copyright (c) 2016, 2025, Oracle and/or its affiliates. All rights reserved.
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

package jdk.jfr.internal;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public final class WriteablePath {
    private final Path path;
    private final Path real;

    public WriteablePath(Path path) throws IOException {
        // verify that the path is writeable
        if (Files.exists(path) && !Files.isWritable(path)) {
            // throw same type of exception as FileOutputStream
            // constructor, if file can't be opened.
            throw new FileNotFoundException("Could not write to file: " + path.toAbsolutePath());
        }
        // will throw if non-writeable
        BufferedWriter fw = Files.newBufferedWriter(path);
        fw.close();
        this.path = path;
        this.real = path.toRealPath();
    }

    public Path getPath() {
        return path;
    }

    public Path getReal() {
        return real;
    }

    public String getRealPathText() {
        return real.toString();
    }
}
