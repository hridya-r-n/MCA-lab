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

import java.nio.file.Path;
import java.util.Objects;
import java.util.Optional;

/**
 * Custom application launcher icon.
 * <p>
 * Use {@link #create(Path)} method to create an instance of this type.
 */
public interface CustomLauncherIcon extends LauncherIcon {

    /**
     * Returns path to icon file.
     * @return path to icon file
     */
    Path path();

    /**
     * Returns the given icon as {@link CustomLauncherIcon} type or an empty {@link Optional} instance
     * if the given icon object is not an instance of {@link CustomLauncherIcon} type.
     *
     * @param icon application launcher icon object or <code>null</null>
     * @return the given icon as {@link CustomLauncherIcon} type or an empty {@link Optional} instance
     */
    public static Optional<CustomLauncherIcon> fromLauncherIcon(LauncherIcon icon) {
        if (icon instanceof CustomLauncherIcon customIcon) {
            return Optional.of(customIcon);
        } else {
            return Optional.empty();
        }
    }

    /**
     * Creates object of type {@link CustomLauncherIcon} from the path to icon file.
     * @param path path to icon file
     * @return {@link CustomLauncherIcon} instance
     */
    public static CustomLauncherIcon create(Path path) {
        Objects.requireNonNull(path);
        return new Stub(path);
    }

    /**
     * Default implementation of {@link CustomLauncherIcon} type.
     */
    record Stub(Path path) implements CustomLauncherIcon {
    }
}
