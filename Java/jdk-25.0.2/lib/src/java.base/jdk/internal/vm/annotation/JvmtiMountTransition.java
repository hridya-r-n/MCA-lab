/*
 * Copyright (c) 2021, 2024, Oracle and/or its affiliates. All rights reserved.
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

package jdk.internal.vm.annotation;

import java.lang.annotation.*;

/**
 * A method may be annotated with JvmtiMountTransition to hint
 * it is desirable to omit it from JVMTI stack traces.
 * Normally, a method is annotated with @JvmtiMountTransition if it starts
 * or ends Virtual Thread Mount State (VTMS) transition, so the thread
 * identity is undefined or different at method entry and exit.
 *
 * @implNote
 * This annotation is only used for VirtualThread methods.
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface JvmtiMountTransition {
}
