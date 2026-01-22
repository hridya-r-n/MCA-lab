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
package jdk.internal.vm.vector;

import jdk.internal.reflect.CallerSensitive;
import jdk.internal.reflect.Reflection;

/**
 * Miscellaneous utility methods.
 */
public class Utils {
    public static final boolean DEBUG = Boolean.getBoolean("jdk.incubator.vector.DEBUG");

    public static boolean isNonCapturingLambda(Object o) {
        return o.getClass().getDeclaredFields().length == 0;
    }

    @CallerSensitive
    public static void debug(String format, Object... args) {
        if (DEBUG) {
            Class<?> caller = Reflection.getCallerClass();
            System.out.printf("DEBUG: %s: ", caller.getSimpleName());
            System.out.printf(format, args);
            System.out.println();
        }
    }
}
