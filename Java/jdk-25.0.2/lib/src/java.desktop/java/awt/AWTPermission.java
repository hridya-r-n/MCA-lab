/*
 * Copyright (c) 1997, 2024, Oracle and/or its affiliates. All rights reserved.
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

package java.awt;

import java.io.Serial;
import java.security.BasicPermission;

/**
 * This class is for AWT permissions.
 * An {@code AWTPermission} contains a target name but
 * no actions list; you either have the named permission
 * or you don't.
 *
 * @apiNote
 * This permission cannot be used for controlling access to resources
 * as the Security Manager is no longer supported.
 * Consequently this class is deprecated for removal in a future release.
 *
 * @see java.security.BasicPermission
 * @see java.security.Permission
 * @see java.security.Permissions
 * @see java.security.PermissionCollection
 * @see java.lang.SecurityManager
 *
 * @author Marianne Mueller
 * @author Roland Schemers
 * @deprecated There is no replacement for this class.
 */
@Deprecated(since="24", forRemoval=true)
public final class AWTPermission extends BasicPermission {

    /**
     * Use serialVersionUID from JDK 1.2 for interoperability.
     */
    @Serial
    private static final long serialVersionUID = 8890392402588814465L;

    /**
     * Creates a new {@code AWTPermission} with the specified name.
     * The name is the symbolic name of the {@code AWTPermission},
     * such as "topLevelWindow", "systemClipboard", etc. An asterisk
     * may be used to indicate all AWT permissions.
     *
     * @param name the name of the AWTPermission
     *
     * @throws NullPointerException if {@code name} is {@code null}.
     * @throws IllegalArgumentException if {@code name} is empty.
     */

    public AWTPermission(String name)
    {
        super(name);
    }

    /**
     * Creates a new {@code AWTPermission} object with the specified name.
     * The name is the symbolic name of the {@code AWTPermission}, and the
     * actions string is currently unused and should be {@code null}.
     *
     * @param name the name of the {@code AWTPermission}
     * @param actions should be {@code null}
     *
     * @throws NullPointerException if {@code name} is {@code null}.
     * @throws IllegalArgumentException if {@code name} is empty.
     */

    public AWTPermission(String name, String actions)
    {
        super(name, actions);
    }
}
