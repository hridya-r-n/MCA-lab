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

package java.security;

/**
 * This exception was originally thrown by the {@link AccessController} to
 * indicate that a requested access was denied.
 *
 * @author Li Gong
 * @author Roland Schemers
 * @since 1.2
 * @deprecated This exception was only useful in conjunction with
 *       {@linkplain SecurityManager the Security Manager}, which is no
 *       longer supported. There is no replacement for the Security Manager
 *       or this class.
 */

@Deprecated(since="17", forRemoval=true)
public class AccessControlException extends SecurityException {

    @java.io.Serial
    private static final long serialVersionUID = 5138225684096988535L;

    /**
     * @serial The permission that caused the exception to be thrown.
     */
    private Permission perm;

    /**
     * Constructs an {@code AccessControlException} with the
     * specified, detailed message.
     *
     * @param   s   the detail message.
     */
    public AccessControlException(String s) {
        super(s);
    }

    /**
     * Constructs an {@code AccessControlException} with the
     * specified, detailed message, and the requested permission that caused
     * the exception.
     *
     * @param   s   the detail message.
     * @param   p   the permission that caused the exception.
     */
    public AccessControlException(String s, Permission p) {
        super(s);
        perm = p;
    }

    /**
     * Gets the {@code Permission} object associated with this exception, or
     * {@code null} if there was no corresponding {@code Permission} object.
     *
     * @return the Permission object.
     */
    public Permission getPermission() {
        return perm;
    }
}
