/*
 * Copyright (c) 2000, 2025, Oracle and/or its affiliates. All rights reserved.
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

package sun.security.util;

import java.util.Map;
import java.util.ResourceBundle;
import java.util.concurrent.ConcurrentHashMap;
import jdk.internal.misc.VM;

/**
 */
public class ResourcesMgr {
    // intended for java.security, javax.security and sun.security resources
    private static final Map<String, ResourceBundle> bundles = new ConcurrentHashMap<>();

    public static String getString(String s) {
        return getBundle("sun.security.util.resources.security").getString(s);
    }

    public static String getAuthResourceString(String s) {
        return getBundle("sun.security.util.resources.auth").getString(s);
    }

    private static ResourceBundle getBundle(String bundleName) {
        if (!VM.isBooted()) {
            // don't expect this be called before the system is fully initialized.
            // This triggers loading of any resource bundle that should be
            // done during initialization of system class loader.
            throw new InternalError("Expected to use ResourceBundle only after booted");
        }
        return bundles.computeIfAbsent(bundleName, ResourceBundle::getBundle);
    }

}
