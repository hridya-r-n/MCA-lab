/*
 * Copyright (c) 2024, 2025, Oracle and/or its affiliates. All rights reserved.
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

package jdk.internal.invoke;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.invoke.VarHandle;

/**
 * Static factories for certain VarHandle/MethodHandle variants.
 * <p>
 * Some methods take no receiver argument. In these cases, the receiver is the
 * lookup class.
 * <p>
 * The methods will throw an {@link InternalError} if the lookup fails.
 * <p>
 * Here is an example of how one of these methods could be used:
 * {@snippet lang=java
 * static MethodHandle BAR_HANDLE =
 *         MhUtil.findVirtual(MethodHandles.lookup(),
 *                 Foo.class,"bar",MethodType.methodType(int.class));
 * }
 */
public final class MhUtil {

    private MhUtil() {}

    public static VarHandle findVarHandle(MethodHandles.Lookup lookup,
                                          String name,
                                          Class<?> type) {
        return findVarHandle(lookup, lookup.lookupClass(), name, type);
    }

    public static VarHandle findVarHandle(MethodHandles.Lookup lookup,
                                          Class<?> recv,
                                          String name,
                                          Class<?> type) {
        try {
            return lookup.findVarHandle(recv, name, type);
        } catch (ReflectiveOperationException e) {
            throw new InternalError(e);
        }
    }

    public static MethodHandle findVirtual(MethodHandles.Lookup lookup,
                                           String name,
                                           MethodType type) {
        return findVirtual(lookup, lookup.lookupClass(), name, type);
    }

    public static MethodHandle findVirtual(MethodHandles.Lookup lookup,
                                           Class<?> refc,
                                           String name,
                                           MethodType type) {
        try {
            return lookup.findVirtual(refc, name, type);
        } catch (ReflectiveOperationException e) {
            throw new InternalError(e);
        }
    }

    public static MethodHandle findStatic(MethodHandles.Lookup lookup,
                                          String name,
                                          MethodType type) {
        return findStatic(lookup, lookup.lookupClass(), name, type);
    }

    public static MethodHandle findStatic(MethodHandles.Lookup lookup,
                                          Class<?> refc,
                                          String name,
                                          MethodType type) {
        try {
            return lookup.findStatic(refc, name, type);
        } catch (ReflectiveOperationException e) {
            throw new InternalError(e);
        }
    }

}
