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

public interface WinApplicationMixin {

    DottedVersion winVersion();

    record Stub(DottedVersion winVersion) implements WinApplicationMixin {
        public Stub(Application app) {
            this(DottedVersion.greedy(app.version()));
        }
    }
}
