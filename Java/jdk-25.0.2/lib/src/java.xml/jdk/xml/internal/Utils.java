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

package jdk.xml.internal;

import java.util.Arrays;
import java.util.function.Supplier;

/**
 * General utility. Use JdkXmlUtils for XML processing related functions.
 */
public class Utils {
    // The debug flag
    private static boolean debug = false;

    /*
     * The {@systemProperty jaxp.debug} property is supported by JAXP factories
     * and used to print out information related to the configuration of factories
     * and processors
     */
    static {
        String val = System.getProperty("jaxp.debug");
        // Allow simply setting the prop to turn on debug
        debug = val != null && !"false".equals(val);
    }

    // print out debug information if jaxp.debug is enabled
    public static void dPrint(Supplier<String> msgGen) {
        if (debug) {
            System.err.println("JAXP: " + msgGen.get());
        }
    }

    /**
     * Creates a new array with copies of the original array and additional items
     * appended to the end of it.
     *
     * @param original the original array
     * @param items items to be appended to the original array
     * @return a new array with copies of the original array and additional items
     */
    public static Class<?>[] arraysAppend(final Class<?>[] original, final Class<?>... items) {
        if (original == null && items == null) {
            return null;
        }
        if (items == null) {
            return Arrays.copyOf(original, original.length);
        }
        if (original == null) {
            return Arrays.copyOf(items, items.length);
        }

        Class<?>[] result = Arrays.copyOf(original, original.length + items.length);
        System.arraycopy(items, 0, result, original.length, items.length);
        return result;
    }
}
