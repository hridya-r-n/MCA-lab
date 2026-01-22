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

import java.util.Optional;

/**
 * Default application launcher icon.
 * <p>
 * Default icon is either loaded from the resources of {@link jdk.jpackage/} module or picked from the resource directory.
 * <p>
 * Use {@link #INSTANCE} field to get an instance of this type.
 */
public interface DefaultLauncherIcon extends LauncherIcon {

    /**
     * Returns the given icon as {@link DefaultLauncherIcon} type or an empty {@link Optional} instance
     * if the given icon object is not an instance of {@link DefaultLauncherIcon} type.
     *
     * @param icon application launcher icon object or <code>null</null>
     * @return the given icon as {@link DefaultLauncherIcon} type or an empty {@link Optional} instance
     */
    public static Optional<DefaultLauncherIcon> fromLauncherIcon(LauncherIcon icon) {
        if (icon instanceof DefaultLauncherIcon defaultIcon) {
            return Optional.of(defaultIcon);
        } else {
            return Optional.empty();
        }
    }

    /**
     * Singleton.
     */
    public static DefaultLauncherIcon INSTANCE = new DefaultLauncherIcon() {};
}
