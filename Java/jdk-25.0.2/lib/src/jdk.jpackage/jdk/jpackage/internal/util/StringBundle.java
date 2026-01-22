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
package jdk.jpackage.internal.util;

import java.text.MessageFormat;
import java.util.ResourceBundle;


/**
 * String bundle contains locale-specific strings.
 * It can be viewed a specialized variant of {@link ResourceBundle}.
 * <p>
 * Use {@link #fromResourceBundle(ResourceBundle)} to obtain {@link StringBundle}
 * instance from {@link ResourceBundle} object.
 */
@FunctionalInterface
public interface StringBundle {

    /**
     * Gets a string for the given key from this string bundle.
     * @param key the key for the desired string
     * @return the string for the given key
     *
     * @see ResourceBundle#getString(String)
     */
    String getString(String key);

    /**
     * Gets a formatted message built from the pattern string matching
     * the given key in this string bundle and the given arguments.
     * <p>
     * If non-empty list of arguments provided the function calls {@link MessageFormat#format(String, Object...)}.
     * Otherwise, it returns the result of {@link #getString(String)} method call.
     *
     * @param key the key for the desired pattern
     * @param args the array of arguments for formatting or an empty array for no formatting
     * @return the formatted message
     */
    default String format(String key, Object ... args) {
        var str = getString(key);
        if (args.length != 0) {
            return MessageFormat.format(str, args);
        } else {
            return str;
        }
    }

    /**
     * Gets {@link StringBundle} instance from the given {@link ResourceBundle} object.
     * @param bundle the resource bundle
     * @return the string bundle
     */
    public static StringBundle fromResourceBundle(ResourceBundle bundle) {
        return bundle::getString;
    }
}
