/*
 * Copyright (c) 2022, 2025, Oracle and/or its affiliates. All rights reserved.
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

import java.util.Objects;
import jdk.jpackage.internal.model.Application;
import jdk.jpackage.internal.model.Launcher;

class LauncherAsService {

    LauncherAsService(Application app, Launcher launcher, OverridableResource resource) {
        this.name = launcher.name();

        if (app.mainLauncher().orElseThrow() == launcher) {
            // Main launcher
            this.description = launcher.description();
        } else {
            // Additional launcher
            this.description = String.format("%s (%s)", app.description(), name);
        }

        this.resource = Objects.requireNonNull(resource);
        resource.addSubstitutionDataEntry("SERVICE_DESCRIPTION", description);
    }

    protected OverridableResource getResource() {
        return resource;
    }

    protected String getName() {
        return name;
    }

    protected String getDescription() {
        return description;
    }

    private final String name;
    private final String description;
    private final OverridableResource resource;
}
