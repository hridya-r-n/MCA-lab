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
package jdk.incubator.vector;

/*package-private*/ class Util {
    public static void requires(boolean cond, String message) {
        if (!cond) {
            throw new InternalError(message);
        }
    }
}
