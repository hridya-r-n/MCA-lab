/*
 * Copyright (c) 2019, 2025, Oracle and/or its affiliates. All rights reserved.
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

package jdk.internal.javac;

import java.lang.annotation.*;

/**
 * Indicates the API declaration in question is associated with a
 * <em>preview feature</em>. See JEP 12: "Preview Language and VM
 * Features" (https://openjdk.org/jeps/12).
 *
 * Note this internal annotation is handled specially by the javac compiler.
 * To work properly with {@code --release older-release}, it requires special
 * handling in {@code make/langtools/src/classes/build/tools/symbolgenerator/CreateSymbols.java}
 * and {@code src/jdk.compiler/share/classes/com/sun/tools/javac/jvm/ClassReader.java}.
 *
 * @since 14
 */
// Match the meaningful targets of java.lang.Deprecated, omit local
// variables and parameter declarations
@Target({ElementType.METHOD,
         ElementType.CONSTRUCTOR,
         ElementType.FIELD,
         ElementType.PACKAGE,
         ElementType.MODULE,
         ElementType.TYPE})
 // CLASS retention will hopefully be sufficient for the purposes at hand
@Retention(RetentionPolicy.CLASS)
// *Not* @Documented
public @interface PreviewFeature {
    /**
     * Name of the preview feature the annotated API is associated
     * with.
     */
    public Feature feature();

    public boolean reflective() default false;

    /**
     * Enum of preview features in the current release.
     * Values should be annotated with the feature's {@code JEP}.
     */
    public enum Feature {
        // while building the interim javac, the ClassReader will produce a warning when loading a class
        // keeping the constant of a feature that has been integrated or dropped, serves the purpose of muting such warnings.

        //---
        IMPLICIT_CLASSES, //to be removed when boot JDK is 25
        SCOPED_VALUES,
        @JEP(number=505, title="Structured Concurrency", status="Fifth Preview")
        STRUCTURED_CONCURRENCY,
        CLASSFILE_API,
        STREAM_GATHERERS,
        MODULE_IMPORTS, //remove when the boot JDK is JDK 25
        KEY_DERIVATION, //remove when the boot JDK is JDK 25
        @JEP(number = 502, title = "Stable Values", status = "Preview")
        STABLE_VALUES,
        @JEP(number=470, title="PEM Encodings of Cryptographic Objects", status="Preview")
        PEM_API,
        LANGUAGE_MODEL,
        /**
         * A key for testing.
         */
        @JEP(number=2_147_483_647, title="Test Feature")
        TEST,
        ;
    }

    /**
     * Annotation identifying the JEP associated with a preview feature.
     */
    @Target(ElementType.FIELD)
    @Retention(RetentionPolicy.CLASS)
    @interface JEP {
        /** JEP number */
        int number() default 0;
        /** JEP title in plain text */
        String title();
        /** JEP status such as "Preview", "Second Preview", etc */
        String status() default "Preview";
    }
}
