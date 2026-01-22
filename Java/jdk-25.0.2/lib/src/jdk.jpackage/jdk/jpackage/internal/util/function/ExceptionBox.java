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
package jdk.jpackage.internal.util.function;

import java.lang.reflect.InvocationTargetException;

public class ExceptionBox extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public static RuntimeException rethrowUnchecked(Throwable throwable) {
        if (throwable instanceof RuntimeException err) {
            throw err;
        }

        if (throwable instanceof Error err) {
            throw err;
        }

        if (throwable instanceof InvocationTargetException err) {
            throw rethrowUnchecked(err.getCause());
        }

        throw new ExceptionBox(throwable);
    }

    private ExceptionBox(Throwable throwable) {
        super(throwable);
    }
}
