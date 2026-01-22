/*
 * Copyright (c) 2012, 2025, Oracle and/or its affiliates. All rights reserved.
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

package jdk.jpackage.internal;

public class WinAppBundler extends AppImageBundler {
    public WinAppBundler() {
        setAppImageSupplier((params, output) -> {
            // Order is important!
            var app = WinFromParams.APPLICATION.fetchFrom(params);
            var env = BuildEnvFromParams.BUILD_ENV.fetchFrom(params);
            WinPackagingPipeline.build()
                    .excludeDirFromCopying(output.getParent())
                    .create().execute(BuildEnv.withAppImageDir(env, output), app);
        });
    }
}
