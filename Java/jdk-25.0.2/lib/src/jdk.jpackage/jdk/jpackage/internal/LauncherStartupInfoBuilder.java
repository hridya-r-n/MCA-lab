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
import java.util.List;
import java.util.function.UnaryOperator;
import jdk.jpackage.internal.model.LauncherJarStartupInfo;
import jdk.jpackage.internal.model.LauncherJarStartupInfoMixin;
import jdk.jpackage.internal.model.LauncherModularStartupInfo;
import jdk.jpackage.internal.model.LauncherModularStartupInfoMixin;
import jdk.jpackage.internal.model.LauncherStartupInfo.Stub;
import jdk.jpackage.internal.model.LauncherStartupInfo;

final class LauncherStartupInfoBuilder {

    LauncherStartupInfo create() {
        return decorator.apply(new Stub(qualifiedClassName, javaOptions,
                defaultParameters, classPath));
    }

    LauncherStartupInfoBuilder launcherData(LauncherData launcherData) {
        if (launcherData.isModular()) {
            decorator = new ModuleStartupInfo(launcherData.moduleName());
        } else {
            decorator = new JarStartupInfo(launcherData.mainJarName(),
                    launcherData.isClassNameFromMainJar());
        }
        classPath = launcherData.classPath();
        qualifiedClassName = launcherData.qualifiedClassName();
        return this;
    }

    LauncherStartupInfoBuilder javaOptions(List<String> v) {
        javaOptions = v;
        return this;
    }

    LauncherStartupInfoBuilder defaultParameters(List<String> v) {
        defaultParameters = v;
        return this;
    }

    private static record ModuleStartupInfo(String moduleName) implements UnaryOperator<LauncherStartupInfo> {

        @Override
        public LauncherStartupInfo apply(LauncherStartupInfo base) {
            return LauncherModularStartupInfo.create(base,
                    new LauncherModularStartupInfoMixin.Stub(moduleName));
        }
    }

    private static record JarStartupInfo(Path jarPath,
            boolean isClassNameFromMainJar) implements
            UnaryOperator<LauncherStartupInfo> {

        @Override
        public LauncherStartupInfo apply(LauncherStartupInfo base) {
            return LauncherJarStartupInfo.create(base,
                    new LauncherJarStartupInfoMixin.Stub(jarPath,
                            isClassNameFromMainJar));
        }
    }

    private String qualifiedClassName;
    private List<String> javaOptions;
    private List<String> defaultParameters;
    private List<Path> classPath;
    private UnaryOperator<LauncherStartupInfo> decorator;
}
