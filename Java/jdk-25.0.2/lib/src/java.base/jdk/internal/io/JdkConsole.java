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

import java.io.PrintWriter;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.Locale;

/**
 * Delegate interface for custom Console implementations.
 * Methods defined here duplicates the ones in Console class.
 * Providers should implement jdk.internal.io.JdkConsoleProvider
 * to instantiate an implementation of this interface.
 */
public interface JdkConsole {
    PrintWriter writer();
    Reader reader();
    JdkConsole println(Object obj);
    JdkConsole print(Object obj);
    JdkConsole format(Locale locale, String format, Object ... args);
    String readLine(Locale locale, String format, Object ... args);
    String readLine();
    char[] readPassword(Locale locale, String format, Object ... args);
    char[] readPassword();
    void flush();
    Charset charset();
}
