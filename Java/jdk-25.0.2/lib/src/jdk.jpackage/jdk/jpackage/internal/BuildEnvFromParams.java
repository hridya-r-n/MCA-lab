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
package jdk.jpackage.internal;

import static jdk.jpackage.internal.StandardBundlerParam.PREDEFINED_RUNTIME_IMAGE;
import static jdk.jpackage.internal.StandardBundlerParam.RESOURCE_DIR;
import static jdk.jpackage.internal.StandardBundlerParam.TEMP_ROOT;
import static jdk.jpackage.internal.StandardBundlerParam.VERBOSE;

import java.util.Map;
import jdk.jpackage.internal.model.ConfigException;

final class BuildEnvFromParams {

    static BuildEnv create(Map<String, ? super Object> params) throws ConfigException {

        final var builder = new BuildEnvBuilder(TEMP_ROOT.fetchFrom(params));

        RESOURCE_DIR.copyInto(params, builder::resourceDir);
        VERBOSE.copyInto(params, builder::verbose);

        final var app = FromParams.APPLICATION.findIn(params).orElseThrow();

        final var pkg = FromParams.getCurrentPackage(params);

        if (app.isRuntime()) {
            builder.appImageDir(PREDEFINED_RUNTIME_IMAGE.fetchFrom(params));
        } else if (StandardBundlerParam.hasPredefinedAppImage(params)) {
            builder.appImageDir(StandardBundlerParam.getPredefinedAppImage(params));
        } else if (pkg.isPresent()) {
            builder.appImageDirForPackage();
        } else {
            builder.appImageDirFor(app);
        }

        return builder.create();
    }

    static final BundlerParamInfo<BuildEnv> BUILD_ENV = BundlerParamInfo.createBundlerParam(
            BuildEnv.class, BuildEnvFromParams::create);
}
