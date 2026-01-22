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

package jdk.internal.vm.annotation;

import java.lang.annotation.*;

/**
 * A method may be annotated with JvmtiHideEvents to hint that JVMTI events
 * should not be generated in context of the annotated method.
 *
 * @implNote
 * This annotation is only used for some VirtualThread and Continuation methods.
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface JvmtiHideEvents {
}
