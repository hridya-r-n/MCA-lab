/*
 * Copyright (c) 2023, 2024, Oracle and/or its affiliates. All rights reserved.
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

// This module is primarily an import from a recent tagged version of
//     https://github.com/commonmark/commonmark-java
//
// The following parts are imported:
//
// * commonmark/src/main/java
// * commonmark/src/main/resources
// * commonmark-ext-gfm-tables/src/main/java
// * commonmark-ext-gfm-tables/src/main/resources
//
// For source and resource files, the following transformations are applied:
//
// * legal headers are added
// * package and import statements are updated
// * characters outside the ASCII range are converted to Unicode escapes
// * @SuppressWarnings("fallthrough") is added to getSetextHeadingLevel
// * the value for ENTITY_PATH is updated with the modified package
// * the file `entities.properties` is renamed to `entities.txt`

/**
 * Internal support for Markdown.
 *
 * @since 23
 */
module jdk.internal.md {
    requires jdk.compiler;

    exports jdk.internal.markdown to
            jdk.javadoc,
            jdk.jshell;
    exports jdk.internal.org.commonmark to
            jdk.javadoc,
            jdk.jshell;
    exports jdk.internal.org.commonmark.ext.gfm.tables to
            jdk.javadoc,
            jdk.jshell;
    exports jdk.internal.org.commonmark.node to
            jdk.javadoc,
            jdk.jshell;
    exports jdk.internal.org.commonmark.parser to
            jdk.javadoc,
            jdk.jshell;
    exports jdk.internal.org.commonmark.renderer to
            jdk.javadoc,
            jdk.jshell;
    exports jdk.internal.org.commonmark.renderer.html to
            jdk.javadoc,
            jdk.jshell;

    provides com.sun.tools.javac.api.JavacTrees.DocCommentTreeTransformer
            with jdk.internal.markdown.MarkdownTransformer;
}
