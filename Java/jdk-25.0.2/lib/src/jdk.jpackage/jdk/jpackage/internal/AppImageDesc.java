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
import jdk.jpackage.internal.model.AppImageLayout;
import jdk.jpackage.internal.model.ApplicationLayout;

record AppImageDesc(AppImageLayout appImageLayout, Path path) {

    AppImageDesc {
        Objects.requireNonNull(appImageLayout);
        Objects.requireNonNull(path);
    }

    AppImageLayout resolvedAppImagelayout() {
        return appImageLayout.resolveAt(path);
    }

    Optional<ApplicationLayout> asResolvedApplicationLayout() {
        return asApplicationLayout().map(v -> v.resolveAt(path));
    }

    Optional<ApplicationLayout> asApplicationLayout() {
        if (appImageLayout instanceof ApplicationLayout layout) {
            return Optional.of(layout);
        } else {
            return Optional.empty();
        }
    }
}
