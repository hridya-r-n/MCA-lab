/*
 * Copyright (c) 2001, 2024, Oracle and/or its affiliates. All rights reserved.
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

package jdk.internal.reflect;

import java.lang.reflect.InvocationTargetException;

abstract class ConstructorAccessorImpl implements ConstructorAccessor {
    /** Matches specification in {@link java.lang.reflect.Constructor} */
    public abstract Object newInstance(Object[] args)
        throws InstantiationException,
               IllegalArgumentException,
               InvocationTargetException;
}
