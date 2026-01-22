/*
 * Copyright (c) 2019, 2025, Oracle and/or its affiliates. All rights reserved.
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
package jdk.jpackage.internal.model;

import java.util.List;
import java.util.Map;
import jdk.internal.util.OperatingSystem;
import jdk.jpackage.internal.util.LocalizedExceptionBuilder;
import jdk.jpackage.internal.util.MultiResourceBundle;
import jdk.jpackage.internal.util.StringBundle;

final class I18N {

    static String getString(String key) {
        return BUNDLE.getString(key);
    }

    static String format(String key, Object ... args) {
        return BUNDLE.format(key, args);
    }

    static LocalizedExceptionBuilder<?> buildLocalizedException() {
        return LocalizedExceptionBuilder.buildLocalizedException(BUNDLE);
    }

    private static final StringBundle BUNDLE;

    static {
        var prefix = "jdk.jpackage.internal.resources.";
        BUNDLE = StringBundle.fromResourceBundle(MultiResourceBundle.create(
                prefix + "MainResources",
                Map.of(
                        OperatingSystem.LINUX, List.of(prefix + "LinuxResources"),
                        OperatingSystem.MACOS, List.of(prefix + "MacResources"),
                        OperatingSystem.WINDOWS, List.of(prefix + "WinResources")
                )
        ));
    }
}
