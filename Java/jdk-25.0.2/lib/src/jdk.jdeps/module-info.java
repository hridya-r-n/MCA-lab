/*
 * Copyright (c) 2015, 2025, Oracle and/or its affiliates. All rights reserved.
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

import jdk.internal.javac.ParticipatesInPreview;

/**
 * Defines tools for analysing dependencies in Java libraries and programs,
 * including the <em>{@index jdeps jdeps tool}</em>,
 * <em>{@index javap javap tool}</em>,
 * <em>{@index jdeprscan jdeprscan tool}</em>, and
 * <em>{@index jnativescan jnativescan tool}</em> tools.
 *
 * <p>
 * This module provides the equivalent of command-line access to the
 * <em>javap</em>, <em>jdeps</em>, and <em>jnativescan</em> tools via the
 * {@link java.util.spi.ToolProvider ToolProvider} service provider
 * interface (SPI)</p>
 *
 * <p> Instances of the tools can be obtained by calling
 * {@link java.util.spi.ToolProvider#findFirst ToolProvider.findFirst}
 * or the {@linkplain java.util.ServiceLoader service loader} with the name
 * {@code "javap"} or {@code "jdeps"} as appropriate.
 *
 * <p>
 * <em>jdeprscan</em> only exists as a command line tool, and does not provide
 * any direct API.
 *
 * @toolGuide javap
 * @toolGuide jdeprscan
 * @toolGuide jdeps
 * @toolGuide jnativescan
 *
 * @provides java.util.spi.ToolProvider
 *     Use {@link java.util.spi.ToolProvider#findFirst ToolProvider.findFirst("javap")},
 *     {@link java.util.spi.ToolProvider#findFirst ToolProvider.findFirst("jdeps")},
 *     or {@link java.util.spi.ToolProvider#findFirst ToolProvider.findFirst("jnativescan")}
 *     to obtain an instance of a {@code ToolProvider} that provides the equivalent
 *     of command-line access to the {@code javap}, {@code jdeps}, {@code jnativescan} tool.
 *
 * @moduleGraph
 * @since 9
 */
@ParticipatesInPreview
module jdk.jdeps {
    requires java.compiler;
    requires jdk.compiler;
    requires jdk.internal.opt;

    uses com.sun.tools.javac.platform.PlatformProvider;

    provides java.util.spi.ToolProvider with
        com.sun.tools.javap.Main.JavapToolProvider,
        com.sun.tools.jdeps.Main.JDepsToolProvider,
        com.sun.tools.jnativescan.Main.Provider;
}
