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

import static jdk.jpackage.internal.ApplicationImageUtils.createLauncherIconResource;

import java.io.IOException;
import java.io.UncheckedIOException;
import jdk.jpackage.internal.PackagingPipeline.AppImageBuildEnv;
import jdk.jpackage.internal.PackagingPipeline.BuildApplicationTaskID;
import jdk.jpackage.internal.PackagingPipeline.CopyAppImageTaskID;
import jdk.jpackage.internal.PackagingPipeline.PrimaryTaskID;
import jdk.jpackage.internal.PackagingPipeline.TaskID;
import jdk.jpackage.internal.model.ApplicationLayout;
import jdk.jpackage.internal.model.Package;
import jdk.jpackage.internal.model.PackagerException;
import jdk.jpackage.internal.model.WinApplication;
import jdk.jpackage.internal.model.WinLauncher;

final class WinPackagingPipeline {

    enum WinAppImageTaskID implements TaskID {
        REBRAND_LAUNCHERS
    }

    static PackagingPipeline.Builder build() {
        return PackagingPipeline.buildStandard()
                .appImageLayoutForPackaging(Package::appImageLayout)
                .task(CopyAppImageTaskID.COPY).noaction().add()
                .task(WinAppImageTaskID.REBRAND_LAUNCHERS)
                        .addDependency(BuildApplicationTaskID.LAUNCHERS)
                        .addDependent(PrimaryTaskID.BUILD_APPLICATION_IMAGE)
                        .applicationAction(WinPackagingPipeline::rebrandLaunchers).add();
    }

    private static void rebrandLaunchers(AppImageBuildEnv<WinApplication, ApplicationLayout> env)
            throws IOException, PackagerException {
        for (var launcher : env.app().launchers()) {
            final var iconTarget = createLauncherIconResource(env.app(), launcher, env.env()::createResource).map(iconResource -> {
                var iconDir = env.env().buildRoot().resolve("icons");
                var theIconTarget = iconDir.resolve(launcher.executableName() + ".ico");
                try {
                    if (null == iconResource.saveToFile(theIconTarget)) {
                        theIconTarget = null;
                    }
                } catch (IOException ex) {
                    throw new UncheckedIOException(ex);
                }
                return theIconTarget;
            });

            var launcherExecutable = env.resolvedLayout().launchersDirectory().resolve(
                    launcher.executableNameWithSuffix());

            // Update branding of launcher executable
            new ExecutableRebrander(env.app(),
                    (WinLauncher) launcher, env.env()::createResource).execute(
                            env.env(), launcherExecutable, iconTarget);
        }
    }

    static final ApplicationLayout APPLICATION_LAYOUT = ApplicationLayoutUtils.PLATFORM_APPLICATION_LAYOUT;
}
