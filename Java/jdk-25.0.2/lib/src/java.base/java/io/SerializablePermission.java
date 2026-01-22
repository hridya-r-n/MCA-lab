/*
 * Copyright (c) 1997, 2025, Oracle and/or its affiliates. All rights reserved.
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

package java.io;

import java.security.BasicPermission;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.StringTokenizer;

/**
 * This class is for Serializable permissions. A SerializablePermission
 * contains a name (also referred to as a "target name") but
 * no actions list; you either have the named permission
 * or you don't.
 *
 * @deprecated
 * This permission cannot be used for controlling access to resources
 * as the Security Manager is no longer supported.
 *
 * @see java.security.BasicPermission
 * @see java.security.Permission
 * @see java.security.Permissions
 * @see java.security.PermissionCollection
 *
 * @author Joe Fialli
 * @since 1.2
 */

/* code was borrowed originally from java.lang.RuntimePermission. */

@Deprecated(since="25", forRemoval=true)
public final class SerializablePermission extends BasicPermission {

    @java.io.Serial
    private static final long serialVersionUID = 8537212141160296410L;

    /**
     * @serial
     */
    private String actions;

    /**
     * Creates a new SerializablePermission with the specified name.
     * The name is the symbolic name of the SerializablePermission, such as
     * "enableSubstitution", etc.
     *
     * @param name the name of the SerializablePermission.
     *
     * @throws NullPointerException if {@code name} is {@code null}.
     * @throws IllegalArgumentException if {@code name} is empty.
     */
    public SerializablePermission(String name)
    {
        super(name);
    }

    /**
     * Creates a new SerializablePermission object with the specified name.
     * The name is the symbolic name of the SerializablePermission, and the
     * actions String is currently unused and should be null.
     *
     * @param name the name of the SerializablePermission.
     * @param actions currently unused and must be set to null
     *
     * @throws NullPointerException if {@code name} is {@code null}.
     * @throws IllegalArgumentException if {@code name} is empty.
     */

    public SerializablePermission(String name, String actions)
    {
        super(name, actions);
    }
}
