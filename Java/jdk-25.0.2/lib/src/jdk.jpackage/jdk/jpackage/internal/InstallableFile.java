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

import java.nio.file.Path;

/**
 * Source file that should be copied somewhere in application image tree.
 *
 * Has two paths:
 *  - path where it should be picked from;
 *  - path where it should be copied in application image;
 */
record InstallableFile(Path srcPath, Path installPath) {
}
