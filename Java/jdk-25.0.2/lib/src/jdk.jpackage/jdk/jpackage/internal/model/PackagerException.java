/*
 * Copyright (c) 2011, 2025, Oracle and/or its affiliates. All rights reserved.
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
package jdk.jpackage.internal.model;

import jdk.jpackage.internal.util.StringBundle;

/**
 * Signals that error has occurred at packaging phase.
 * <p>
 * The preferred way to construct instances of this class is to use
 * {@link jdk.jpackage.internal.util.LocalizedExceptionBuilder#buildLocalizedException(StringBundle)} methods
 *
 * {@snippet :
 * StringBundle i18n = getStringBundle(); // Some way to obtain a string bundle with localized messages
 *
 * throw buildLocalizedException(i18n).message("error.no.name").create();
 * }
 */
public class PackagerException extends Exception {

    private static final long serialVersionUID = 1L;

    public PackagerException(Throwable cause) {
        super(cause);
    }

    public PackagerException(String key, Throwable cause) {
        super(I18N.getString(key), cause);
    }

    public PackagerException(String key) {
        super(I18N.getString(key));
    }

    public PackagerException(String key, Object... arguments) {
        super(I18N.format(key, arguments));
    }

    public PackagerException(Throwable cause, String key, Object... arguments) {
        super(I18N.format(key, arguments), cause);
    }

    /**
     * Throws the cause of the given {@link RuntimeException} exception
     * as {@link PackagerException} if the cause is of this type or re-throws the given
     * {@link RuntimeException} exception as-is otherwise.
     * <p>
     * Never return a value. It always throws some exception object.
     *
     * @param ex exception to re-throw
     * @return doesn't return value
     * @throws PackagerException
     */
    public static RuntimeException rethrowPackagerException(RuntimeException ex) throws PackagerException {
        if (ex.getCause() instanceof PackagerException pkgEx) {
            throw pkgEx;
        } else {
            throw ex;
        }
    }

}
