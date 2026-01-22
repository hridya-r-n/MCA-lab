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
package com.sun.tools.jnativescan;

import java.io.Serial;

// Exception used in case of fatal error that is reasonably expected and handled.
public class JNativeScanFatalError extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    public JNativeScanFatalError(String message) {
        super(message);
    }

    public JNativeScanFatalError(String message, Throwable cause) {
        super(message, cause);
    }

    public JNativeScanFatalError(Throwable cause) {
        super(cause);
    }
}
