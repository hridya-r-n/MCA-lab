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
package jdk.jfr.internal.tracing;

import java.util.Set;

// // The JVM will skip all classes in the jdk.jfr module, so it's not added here.
public final class ExcludeList {
    private static final String[] EXCLUDED_CLASSES = {
        // Used by MethodTiming event to accumulate invocations.
        "java/util/concurrent/atomic/AtomicLong",
        // Used by EventWriter, directly or indirectly.
        "sun/misc/Unsafe",
        "jdk/internal/misc/Unsafe",
        "java/lang/StringLatin1",
        "java/lang/StringUTF16",
    };

    private static final String[] EXCLUDED_PREFIX = {
        // Used by MethodTiming event to store invocations, including inner classes.
        "java/util/concurrent/ConcurrentHashMap",
        // Can't trigger <clinit> of these classes during PlatformTracer::onMethodTrace(...)
        // Also to avoid recursion with EventWriter::putString
        "jdk/internal/", // jdk/internal/classfile, // jdk/internal/vm, jdk/internal/util, jdk/internal/loader and jdk/internal/foreign
        "java/lang/classfile/"
    };

    private static final Set<String> EXCLUDED_METHODS = Set.of(
        // Long used by MethodTiming event when looking up entry for timing entry
        "java.lang.Long::<init>",
        "java.lang.Long::valueOf",
        "java.lang.Number::<init>",
        // Used by EventWriter::putString, directly or indirectly.
        "java.lang.String::charAt",
        "java.lang.String::length",
        "java.lang.String::coder", // Used by charAt(int)
        "java.lang.String::checkIndex", // Used by charAt(int)
        "java.lang.String::isLatin1", // Used by charAt()
        "java.lang.String::equals", // Used by StringPool
        "java.lang.String::hashCode" // Used by StringPool
    );

    public static boolean containsMethod(String methodName) {
        return EXCLUDED_METHODS.contains(methodName);
    }

    public static boolean containsClass(String className) {
        for (String clazz: EXCLUDED_CLASSES) {
            if (clazz.equals(className)) {
                return true;
            }
        }
        for (String prefix : EXCLUDED_PREFIX) {
            if (className.startsWith(prefix)) {
                return true;
            }
        }
        return false;
    }
}
