/*
 * Copyright (c) 2025, Oracle and/or its affiliates. All rights reserved.
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

package sun.net.util;

import sun.net.ApplicationProxy;

import java.net.Proxy;

public final class ProxyUtil {

    private ProxyUtil() {}

    /**
     * Creates a new {@link Proxy} instance for the given proxy iff it is
     * neither null, {@link Proxy#NO_PROXY Proxy.NO_PROXY}, an
     * {@link ApplicationProxy} instance, nor already a {@code Proxy} instance.
     */
    public static Proxy copyProxy(Proxy proxy) {
        return proxy == null
                || proxy.getClass() == Proxy.class
                || proxy instanceof ApplicationProxy
                ? proxy
                : new Proxy(proxy.type(), proxy.address());
    }

}
