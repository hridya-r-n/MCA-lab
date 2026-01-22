/*
 * Copyright (c) 2024, Oracle and/or its affiliates. All rights reserved.
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

package jdk.internal.access;

import java.lang.invoke.MethodHandle;

public interface JavaObjectStreamReflectionAccess {
    MethodHandle defaultReadObject(Class<?> clazz);
    MethodHandle defaultWriteObject(Class<?> clazz);
}
