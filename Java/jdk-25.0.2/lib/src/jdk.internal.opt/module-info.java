/*
 * Copyright (c) 2015, 2024, Oracle and/or its affiliates. All rights reserved.
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

/**
 * Internal option processing API
 *
 * @since 9
 */
module jdk.internal.opt {
    exports jdk.internal.joptsimple to
        jdk.jlink,
        jdk.jshell,
        jdk.jdeps;
    exports jdk.internal.opt to
        jdk.compiler,
        jdk.jartool,
        jdk.javadoc,
        jdk.jlink,
        jdk.jpackage,
        jdk.jdeps;
}
