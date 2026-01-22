/*
 * Copyright (c) 2024, Oracle and/or its affiliates. All rights reserved.
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
package com.sun.tools.jnativescan;

import java.lang.classfile.MethodModel;
import java.lang.classfile.constantpool.MemberRefEntry;
import java.lang.classfile.instruction.InvokeInstruction;
import java.lang.constant.ClassDesc;
import java.lang.constant.MethodTypeDesc;

record MethodRef(ClassDesc owner, String name, MethodTypeDesc type) {
    public static MethodRef ofModel(MethodModel model) {
        return new MethodRef(model.parent().orElseThrow().thisClass().asSymbol(),
                model.methodName().stringValue(), model.methodTypeSymbol());
    }

    public static MethodRef ofInvokeInstruction(InvokeInstruction instruction) {
        return new MethodRef(instruction.owner().asSymbol(),
                instruction.name().stringValue(), instruction.typeSymbol());
    }

    @Override
    public String toString() {
        return JNativeScanTask.qualName(owner) + "::" + name + type.displayDescriptor();
    }
}
