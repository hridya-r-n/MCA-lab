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
package java.lang.classfile.constantpool.snippet;

import java.lang.classfile.ClassFile;
import java.lang.classfile.MethodModel;
import java.lang.classfile.constantpool.Utf8Entry;

class PackageSnippets {

    // @start region=isStaticWorkMethod
    boolean isStaticWorkMethod(MethodModel method) {
        // check static flag first to avoid unnecessary evaluation of UTF-8 entry
        return (method.flags().flagsMask() & ClassFile.ACC_STATIC) != 0
                // use equalsString to avoid full conversion to String for comparison
                // the Utf8Entry can also act as a basic CharSequence without full conversion
                // @link substring="methodName" target="MethodModel#methodName" :
                && method.methodName().equalsString("work"); // @link substring="equalsString" target="Utf8Entry#equalsString"
    }
    // @end
}
