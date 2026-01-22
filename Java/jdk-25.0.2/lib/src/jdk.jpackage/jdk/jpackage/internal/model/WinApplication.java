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

public interface WinApplication extends Application, WinApplicationMixin {

    public static WinApplication create(Application app) {
        return CompositeProxy.create(WinApplication.class, app, new WinApplicationMixin.Stub(app));
    }
}
