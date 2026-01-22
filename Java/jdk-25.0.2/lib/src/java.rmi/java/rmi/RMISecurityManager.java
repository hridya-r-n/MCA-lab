/*
 * Copyright (c) 1996, 2024, Oracle and/or its affiliates. All rights reserved.
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

package java.rmi;

/**
 * RMISecurityManager was originally specified to allow an application implement
 * a security policy identical to the policy implemented by {@link SecurityManager}.
 * This feature no longer exists.
 *
 * @author  Roger Riggs
 * @author  Peter Jones
 * @since 1.1
 * @deprecated There is no replacement for the Security Manager.
 */
@SuppressWarnings("removal")
@Deprecated(since="1.8", forRemoval = true)
public class RMISecurityManager extends SecurityManager {

    /**
     * Constructs a new {@code RMISecurityManager}.
     *
     * @since 1.1
     */
    public RMISecurityManager() {
    }
}
