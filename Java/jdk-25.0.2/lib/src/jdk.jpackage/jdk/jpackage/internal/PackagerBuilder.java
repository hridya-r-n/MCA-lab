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

import java.nio.file.Path;
import java.util.Objects;
import jdk.jpackage.internal.PackagingPipeline.StartupParameters;
import jdk.jpackage.internal.model.Package;
import jdk.jpackage.internal.model.PackagerException;

abstract class PackagerBuilder<T extends Package, U extends PackagerBuilder<T, U>> {

    U pkg(T v) {
        pkg = v;
        return thiz();
    }

    U env(BuildEnv v) {
        env = v;
        return thiz();
    }

    U outputDir(Path v) {
        outputDir = v;
        return thiz();
    }

    @SuppressWarnings("unchecked")
    private U thiz() {
        return (U)this;
    }

    protected abstract void configurePackagingPipeline(PackagingPipeline.Builder pipelineBuilder,
            StartupParameters startupParameters);

    Path execute(PackagingPipeline.Builder pipelineBuilder) throws PackagerException {
        Objects.requireNonNull(pkg);
        Objects.requireNonNull(env);
        Objects.requireNonNull(outputDir);

        final var startupParameters = pipelineBuilder.createStartupParameters(env, pkg, outputDir);

        configurePackagingPipeline(pipelineBuilder, startupParameters);

        pipelineBuilder.create().execute(startupParameters);

        return outputDir.resolve(pkg.packageFileNameWithSuffix());
    }

    protected T pkg;
    protected BuildEnv env;
    protected Path outputDir;
}
