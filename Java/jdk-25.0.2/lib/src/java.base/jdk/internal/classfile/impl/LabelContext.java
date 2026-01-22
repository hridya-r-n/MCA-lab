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
package jdk.internal.classfile.impl;

import java.lang.classfile.Label;

public sealed interface LabelContext
        permits TerminalCodeBuilder, CodeImpl {
    Label newLabel();
    Label getLabel(int bci);
    void setLabelTarget(Label label, int bci);
    int labelToBci(Label label);
    default boolean canWriteDirect(LabelContext original) { return false; }
}
