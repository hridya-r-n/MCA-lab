/*
 * Copyright (c) 2023, Oracle and/or its affiliates. All rights reserved.
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
package jdk.jfr.internal;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation that can remove the fields "duration", "eventThread" and
 * "stackTrace".
 * <p>
 * Example usage:
 * {@snippet :
 *   @RemoveFields({{"duration", "stackTrace"}
 *   class NonThreadEvent extends Event {
 *   }
 * }
 *
 * <p>
 * Calling Event::begin() or Event:end() on an event without a duration field
 * will result in an {@code IllegalStateException}
 **/
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface RemoveFields {
    String[] value() default {};
}
