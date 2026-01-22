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

import jdk.jpackage.internal.util.CompositeProxy;

public interface WinMsiPackage extends Package, WinMsiPackageMixin {

    WinApplication app();

    public static WinMsiPackage create(Package pkg, WinMsiPackageMixin mixin) {
        return CompositeProxy.create(WinMsiPackage.class, pkg, mixin);
    }
}
