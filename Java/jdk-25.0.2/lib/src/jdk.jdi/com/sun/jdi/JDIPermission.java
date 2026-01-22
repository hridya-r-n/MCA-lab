/*
 * Copyright (c) 2004, 2025, Oracle and/or its affiliates. All rights reserved.
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

package com.sun.jdi;

/**
 * A {@code JDIPermission} object contains a name (also referred
 * to as a "target name") but no actions list; you either have the
 * named permission or you don't.
 *
 * @apiNote
 * This permission cannot be used for controlling access to resources
 * as the Security Manager is no longer supported.
 * Consequently this class is deprecated for removal in a future release.
 *
 * @author  Tim Bell
 * @since   1.5
 *
 * @see Bootstrap
 * @see java.security.BasicPermission
 * @see java.security.Permission
 * @see java.security.Permissions
 * @see java.security.PermissionCollection
 * @see java.lang.SecurityManager
 *
 * @deprecated This class was only useful in conjunction with the Security Manager,
 * which is no longer supported. There is no replacement for this class.
 */

@Deprecated(since="25", forRemoval=true)
public final class JDIPermission extends java.security.BasicPermission {

    private static final long serialVersionUID = -6988461416938786271L;

    /**
     * The {@code JDIPermission} class represents access rights to the
     * {@code VirtualMachineManager}
     * @param name Permission name. Must be "virtualMachineManager".
     * @throws IllegalArgumentException if the name argument is invalid.
     */
    public JDIPermission(String name) {
        super(name);
        if (!name.equals("virtualMachineManager")) {
            throw new IllegalArgumentException("name: " + name);
        }
    }

    /**
     * Constructs a new JDIPermission object.
     *
     * @param name Permission name. Must be "virtualMachineManager".
     * @param actions Must be either null or the empty string.
     * @throws IllegalArgumentException if arguments are invalid.
     */
    public JDIPermission(String name, String actions)
        throws IllegalArgumentException {
        super(name);
        if (!name.equals("virtualMachineManager")) {
            throw new IllegalArgumentException("name: " + name);
        }
        if (actions != null && actions.length() > 0) {
            throw new IllegalArgumentException("actions: " + actions);
        }
    }
}
