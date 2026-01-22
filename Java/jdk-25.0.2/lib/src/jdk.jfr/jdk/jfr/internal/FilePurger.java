/*
 * Copyright (c) 2019, 2025, Oracle and/or its affiliates. All rights reserved.
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

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.SequencedSet;

// This class keeps track of files that can't be deleted
// so they can at a later staged be removed.
final class FilePurger {

    private static final SequencedSet<Path> paths = new LinkedHashSet<>();

    public static synchronized void add(Path p) {
        paths.add(p);
        if (paths.size() > 1000) {
            removeOldest();
        }
    }

    public static synchronized void purge() {
        if (paths.isEmpty()) {
            return;
        }

        for (Path p : new ArrayList<>(paths)) {
            if (delete(p)) {
                paths.remove(p);
            }
        }
    }

    private static void removeOldest() {
        paths.removeFirst();
    }

    private static boolean delete(Path p) {
        if (!Files.exists(p)) {
            return true;
        }
        try {
            Files.delete(p);
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
