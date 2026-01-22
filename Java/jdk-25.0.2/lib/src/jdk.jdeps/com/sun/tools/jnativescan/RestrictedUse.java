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

import java.util.SortedSet;

sealed interface RestrictedUse {
    record RestrictedMethodRefs(MethodRef referent, SortedSet<MethodRef> referees) implements RestrictedUse {}
    record NativeMethodDecl(MethodRef decl) implements RestrictedUse {}
}
