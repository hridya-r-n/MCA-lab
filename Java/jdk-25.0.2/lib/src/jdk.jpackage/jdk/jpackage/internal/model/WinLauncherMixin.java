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
package jdk.jpackage.internal.model;

import java.util.Set;

public interface WinLauncherMixin {

    boolean isConsole();

    enum WinShortcut {
        WIN_SHORTCUT_DESKTOP("shortcut"),
        WIN_SHORTCUT_START_MENU("menu"),
        ;

        WinShortcut(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        private final String name;
    }

    Set<WinShortcut> shortcuts();

    record Stub(boolean isConsole, Set<WinShortcut> shortcuts) implements WinLauncherMixin {
    }
}
