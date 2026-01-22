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
package jdk.jpackage.internal.model;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * Utility class to manage application launchers.
 * <p>
 * Use {@link #asList} to get application launchers as a list.
 * <p>
 * Use {@link #mainLauncher()} to get the main application launcher.
 * <p>
 * Use {@link #additionalLaunchers()} to get additional application launchers.
 * <p>
 * Use {@link #fromList} to convert the list of application launchers into {@link ApplicationLaunchers} instance.
 */
public record ApplicationLaunchers(Launcher mainLauncher, List<Launcher> additionalLaunchers) {

    public ApplicationLaunchers {
        Objects.requireNonNull(mainLauncher);
        Objects.requireNonNull(additionalLaunchers);
    }

    public ApplicationLaunchers(Launcher mainLauncher) {
        this(mainLauncher, List.of());
    }

    public List<Launcher> asList() {
        return Optional.ofNullable(mainLauncher).map(v -> {
            return Stream.concat(Stream.of(v), additionalLaunchers.stream()).toList();
        }).orElseGet(List::of);
    }

    public static Optional<ApplicationLaunchers> fromList(List<Launcher> launchers) {
        if (launchers == null || launchers.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(new ApplicationLaunchers(launchers.getFirst(),
                    launchers.subList(1, launchers.size())));
        }
    }
}
