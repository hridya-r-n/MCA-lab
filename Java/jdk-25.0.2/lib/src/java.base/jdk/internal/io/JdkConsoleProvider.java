/*
 * Copyright (c) 2022, 2025, Oracle and/or its affiliates. All rights reserved.
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
package jdk.internal.io;

import java.nio.charset.Charset;

/**
 * Service provider interface for JdkConsole implementations.
 */
public interface JdkConsoleProvider {
    /**
     * The module name of the JdkConsole default provider.
     */
    String DEFAULT_PROVIDER_MODULE_NAME = "java.base";

    /**
     * {@return the Console instance, or {@code null} if not available}
     * @param isTTY indicates if the jvm is attached to a terminal
     * @param inCharset Standard input charset of the platform console
     * @param outCharset Standard output charset of the platform console
     */
    JdkConsole console(boolean isTTY, Charset inCharset, Charset outCharset);
}
