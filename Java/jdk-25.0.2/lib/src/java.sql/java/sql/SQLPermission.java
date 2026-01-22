/*
 * Copyright (c) 1999, 2024, Oracle and/or its affiliates. All rights reserved.
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


package java.sql;

import java.security.*;

/**
 * A {@code SQLPermission} object contains
 * a name (also referred to as a "target name") but no actions
 * list; there is either a named permission or there is not.
 * The target name is the name of the permission. The
 * naming convention follows the  hierarchical property naming convention.
 * In addition, an asterisk
 * may appear at the end of the name, following a ".", or by itself, to
 * signify a wildcard match. For example: {@code loadLibrary.*}
 * and {@code *} signify a wildcard match,
 * while {@code *loadLibrary} and {@code a*b} do not.
 *
 * @apiNote
 * This permission cannot be used for controlling access to resources
 * as the Security Manager is no longer supported.
 *
 * @since 1.3
 * @see java.security.BasicPermission
 * @see java.security.Permission
 * @see java.security.Permissions
 * @see java.security.PermissionCollection
 * @see java.lang.SecurityManager
 *
 */

public final class SQLPermission extends BasicPermission {

    /**
     * Creates a new {@code SQLPermission} object with the specified name.
     * The name is the symbolic name of the {@code SQLPermission}.
     *
     * @param name the name of this {@code SQLPermission} object, which must
     * be either {@code  setLog}, {@code callAbort}, {@code setSyncFactory},
     *  {@code deregisterDriver}, or {@code setNetworkTimeout}
     * @throws NullPointerException if {@code name} is {@code null}.
     * @throws IllegalArgumentException if {@code name} is empty.
     */

    public SQLPermission(String name) {
        super(name);
    }

    /**
     * Creates a new {@code SQLPermission} object with the specified name.
     * The name is the symbolic name of the {@code SQLPermission}; the
     * actions {@code String} is currently unused and should be
     * {@code null}.
     *
     * @param name the name of this {@code SQLPermission} object, which must
     * be either {@code  setLog}, {@code callAbort}, {@code setSyncFactory},
     *  {@code deregisterDriver}, or {@code setNetworkTimeout}
     * @param actions should be {@code null}
     * @throws NullPointerException if {@code name} is {@code null}.
     * @throws IllegalArgumentException if {@code name} is empty.
     */

    public SQLPermission(String name, String actions) {
        super(name, actions);
    }

    /**
     * Private serial version unique ID to ensure serialization
     * compatibility.
     */
    static final long serialVersionUID = -1439323187199563495L;

}
