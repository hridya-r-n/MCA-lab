/*
 * Copyright (c) 2023, 2024, Oracle and/or its affiliates. All rights reserved.
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
package jdk.jfr.internal.test;

public class DeprecatedMethods {
    public static int counter;

    @Deprecated
    public static void deprecated() {
        counter++;
    }

    @Deprecated(since = "0")
    public static void deprecatedSince() {
        counter++;
    }

    @Deprecated(forRemoval = true)
    public static void deprecatedForRemoval() {
        counter++;
    }

    @Deprecated(since = "0", forRemoval = true)
    public static void deprecatedSinceForRemoval() {
        counter++;
    }

    @Deprecated
    public static void reflectionDeprecated() {
        counter++;
    }

    @Deprecated(since = "0")
    public static void reflectionDeprecatedSince() {
        counter++;
    }

    @Deprecated(forRemoval = true)
    public static void reflectionDeprecatedForRemoval() {
        counter++;
    }

    @Deprecated(since = "0", forRemoval = true)
    public static void reflectionDeprecatedSinceForRemoval() {
        counter++;
    }
}
