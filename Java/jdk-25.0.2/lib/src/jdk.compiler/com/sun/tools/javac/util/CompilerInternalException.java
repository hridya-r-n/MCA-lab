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

package com.sun.tools.javac.util;

/** The super class of all compiler internal exceptions
 *
 *  <p><b>This is NOT part of any supported API.
 *  If you write code that depends on this, you do so at your own risk.
 *  This code and its internal interfaces are subject to change or
 *  deletion without notice.</b>
 */
public class CompilerInternalException extends RuntimeException {
    private static final long serialVersionUID = 0;

    @SuppressWarnings("this-escape")
    public CompilerInternalException(boolean dumpStackTraceOnError) {
        /* by default the stacktrace wont be filled, meaning that method CompilerInternalException::fillInStackTrace
         * will always be invoked, if we do want to dump the stacktrace then we will invoke super::fillInStackTrace
         * there is a bit of a dance here that could be fixed once flexible constructor bodies exits the preview
         * state
         */
        if (dumpStackTraceOnError) {
            super.fillInStackTrace();
        }
    }

    @Override
    public Throwable fillInStackTrace() {
        return this;
    }
}
