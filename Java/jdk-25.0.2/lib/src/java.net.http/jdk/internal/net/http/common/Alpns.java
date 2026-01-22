/*
 * Copyright (c) 2023, Oracle and/or its affiliates. All rights reserved.
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
package jdk.internal.net.http.common;

/**
 * A static class to define ALPN constant strings used
 * internally by the HttpClient
 */
public final class Alpns {
    private Alpns() { throw new InternalError(); }

    public static final String HTTP_1_1 = "http/1.1";
    public static final String H2 = "h2";
    public static final String H2C = "h2c";
}
