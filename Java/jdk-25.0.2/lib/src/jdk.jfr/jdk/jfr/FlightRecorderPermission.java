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

package jdk.jfr;

import java.util.Objects;

/**
 * Permission for controlling access to Flight Recorder.
 *
 * @deprecated
 * This permission cannot be used for controlling access to resources
 * as the Security Manager is no longer supported.
 *
 * @since 9
 *
 * @see java.security.BasicPermission
 * @see java.security.Permission
 * @see java.security.Permissions
 * @see java.security.PermissionCollection
 * @see java.lang.SecurityManager
 *
 */
@SuppressWarnings("serial")
@Deprecated(since="25", forRemoval=true)
public final class FlightRecorderPermission extends java.security.BasicPermission {
    /**
     * Constructs a {@code FlightRecorderPermission} with the specified name.
     *
     * @param name the permission name, must be either
     *        {@code "accessFlightRecorder"} or {@code "registerEvent"}, not
     *        {@code null}
     *
     * @throws IllegalArgumentException if {@code name} is empty or not valid
     */
    public FlightRecorderPermission(String name) {
        super(Objects.requireNonNull(name, "name"));
        if (!name.equals("accessFlightRecorder") && !name.equals("registerEvent")) {
            throw new IllegalArgumentException("name: " + name);
        }
    }
}
