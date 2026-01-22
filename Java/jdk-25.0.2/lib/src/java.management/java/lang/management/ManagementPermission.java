/*
 * Copyright (c) 2003, 2025, Oracle and/or its affiliates. All rights reserved.
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

package java.lang.management;

/**
 * This class is for management permissions.
 *
 * @apiNote
 * This permission cannot be used for controlling access to resources
 * as the Security Manager is no longer supported.
 * Consequently this class is deprecated for removal in a future release.
 *
 * @author  Mandy Chung
 * @since   1.5
 *
 * @see java.security.BasicPermission
 * @see java.security.Permission
 * @see java.security.Permissions
 * @see java.security.PermissionCollection
 * @see java.lang.SecurityManager
 *
 * @deprecated This class was only useful in conjunction with the Security Manager,
 * which is no longer supported. There is no replacement for this class.
 *
 */
@Deprecated(since="25", forRemoval=true)
public final class ManagementPermission extends java.security.BasicPermission {
    private static final long serialVersionUID = 1897496590799378737L;

    /**
     * Constructs a ManagementPermission with the specified name.
     *
     * @param name Permission name. Must be either "monitor" or "control".
     *
     * @throws NullPointerException if <code>name</code> is <code>null</code>.
     * @throws IllegalArgumentException if <code>name</code> is empty or invalid.
     */
    public ManagementPermission(String name) {
        super(name);
        if (!name.equals("control") && !name.equals("monitor")) {
            throw new IllegalArgumentException("name: " + name);
        }
    }

    /**
     * Constructs a new ManagementPermission object.
     *
     * @param name Permission name. Must be either "monitor" or "control".
     * @param actions Must be either null or the empty string.
     *
     * @throws NullPointerException if <code>name</code> is <code>null</code>.
     * @throws IllegalArgumentException if <code>name</code> is empty or
     * if arguments are invalid.
     */
    public ManagementPermission(String name, String actions)
        throws IllegalArgumentException {
        super(name);
        if (!name.equals("control") && !name.equals("monitor")) {
            throw new IllegalArgumentException("name: " + name);
        }
        if (actions != null && actions.length() > 0) {
            throw new IllegalArgumentException("actions: " + actions);
        }
    }
}
