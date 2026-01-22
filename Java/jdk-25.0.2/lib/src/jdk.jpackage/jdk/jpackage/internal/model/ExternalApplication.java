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
import java.util.Map;
import java.util.Objects;


/**
 * Description of an external application image.
 */
public interface ExternalApplication {

    /**
     * Returns the list of additional launchers configured for the application.
     * <p>
     * Returns an empty list for an application without additional launchers.
     * @return the list of additional launchers configured for the application
     */
    List<LauncherInfo> getAddLaunchers();

    /**
     * Returns application version.
     * @return the application version
     */
    String getAppVersion();

    /**
     * Returns application name.
     * @return the application name
     */
    String getAppName();

    /**
     * Returns main launcher name.
     * @return the main launcher name
     */
    String getLauncherName();

    /**
     * Returns main class name.
     * @return the main class name
     */
    String getMainClass();

    /**
     * Returns additional properties.
     * @return the additional properties
     */
    Map<String, String> getExtra();

    /**
     * Additional launcher description.
     */
    record LauncherInfo(String name, boolean service, Map<String, String> extra) {
        public LauncherInfo {
            Objects.requireNonNull(name);
            Objects.requireNonNull(extra);
            if (name.isBlank()) {
                throw new IllegalArgumentException();
            }
        }
    }
}
