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

import java.nio.file.Path;
import java.util.Optional;

public interface WinExePackageMixin {

    WinMsiPackage msiPackage();

    Optional<Path> icon();

    record Stub(WinMsiPackage msiPackage, Optional<Path> icon) implements WinExePackageMixin {}
}
