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
package jdk.jpackage.internal;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import jdk.jpackage.internal.util.CompositeProxy;

final class CompositeProxyTunnel implements CompositeProxy.InvokeTunnel {

    @Override
    public Object invoke(Object obj, Method method, Object[] args) throws Throwable {
        return method.invoke(obj, args);
    }

    @Override
    public Object invokeDefault(Object proxy, Method method, Object[] args) throws Throwable {
        return InvocationHandler.invokeDefault(proxy, method, args);
    }

    static final CompositeProxyTunnel INSTANCE = new CompositeProxyTunnel();

}
