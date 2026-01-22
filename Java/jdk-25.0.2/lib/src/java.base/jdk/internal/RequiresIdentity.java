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

package jdk.internal;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE_PARAMETER;

/**
 * Indicates that the annotated parameter or type parameter is not expected to be a
 * Value Based class.
 * Using a parameter or type parameter of a <a href="../lang/doc-files/ValueBased.html">value-based classes</a>
 * should produce warnings about behavior that is inconsistent with identity based semantics.
 *
 * Note this internal annotation is handled specially by the javac compiler.
 * To work properly with {@code --release older-release}, it requires special
 * handling in {@code make/langtools/src/classes/build/tools/symbolgenerator/CreateSymbols.java}
 * and {@code src/jdk.compiler/share/classes/com/sun/tools/javac/jvm/ClassReader.java}.
 *
 * @since 25
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value={PARAMETER, TYPE_PARAMETER})
public @interface RequiresIdentity {
}
