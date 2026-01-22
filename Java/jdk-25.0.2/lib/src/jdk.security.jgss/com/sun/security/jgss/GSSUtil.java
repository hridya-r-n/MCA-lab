/*
 * Copyright (c) 2000, 2025, Oracle and/or its affiliates. All rights reserved.
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

package com.sun.security.jgss;

import javax.security.auth.Subject;
import org.ietf.jgss.GSSName;
import org.ietf.jgss.GSSCredential;

/**
 * GSS-API Utilities for use in conjunction with the JDK implementation
 * of Java GSS-API.
 *
 * @since 1.4
 */
public class GSSUtil {
    /**
     * Do not call.
     */
    private GSSUtil() {}

    /**
     * Use this method to convert a {@code GSSName} and {@code GSSCredential}
     * into a {@code Subject}. Typically this would be used by a server that
     * wants to impersonate a client.
     *
     * The elements from the {@code GSSName} are placed in the principals set
     * of this {@code Subject} and those from the {@code GSSCredential} are
     * placed in the private credentials set of the {@code Subject}. Any
     * Kerberos specific elements that are added to the subject will be
     * instances of the standard Kerberos implementation classes defined in
     * {@link javax.security.auth.kerberos}.
     *
     * @param principals a {@code GSSName} containing one or more mechanism
     * specific representations of the same entity. These mechanism specific
     * representations will be populated in the returned {@code Subject}'s
     * principal set.
     *
     * @param credentials a {@code GSSCredential} containing one or more
     * mechanism specific credentials for the same entity. These mechanism
     * specific credentials will be populated in the returned {@code Subject}'s
     * private credential set. Passing in a value of {@code null} will imply
     * that the private credential set should be left empty.
     *
     * @return a {@code Subject} with the entries that contain elements from
     * the given {@code GSSName} and {@code GSSCredential}.
     */
    public static Subject createSubject(GSSName principals,
                                        GSSCredential credentials) {

        return sun.security.jgss.GSSUtil.getSubject(principals,
                                                    credentials);
    }
}
