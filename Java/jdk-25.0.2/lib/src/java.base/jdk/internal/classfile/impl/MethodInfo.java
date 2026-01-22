/*
 * Copyright (c) 2022, 2024, Oracle and/or its affiliates. All rights reserved.
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

import java.lang.classfile.constantpool.Utf8Entry;
import java.lang.constant.MethodTypeDesc;

import static java.lang.classfile.ClassFile.ACC_STATIC;

public sealed interface MethodInfo
        permits MethodImpl, TerminalMethodBuilder, BufferedMethodBuilder.Model {
    Utf8Entry methodName();
    Utf8Entry methodType();
    MethodTypeDesc methodTypeSymbol();
    int methodFlags();

    default int receiverSlot() {
        if ((methodFlags() & ACC_STATIC) != 0)
            throw new IllegalStateException("not an instance method");
        return 0;
    }

    int parameterSlot(int paramNo);
}
