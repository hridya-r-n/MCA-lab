/*
 * Copyright (c) 2006, 2024, Oracle and/or its affiliates. All rights reserved.
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

package java.util.zip;

/**
 * Signals that an unrecoverable error has occurred.
 *
 * @deprecated ZipError is no longer used and is obsolete.
 * {@link ZipException} should be used instead.
 * @author  Dave Bristor
 * @since   1.6
 */
@Deprecated(since="24", forRemoval = true)
public class ZipError extends InternalError {
    @java.io.Serial
    private static final long serialVersionUID = 853973422266861979L;

    /**
     * Constructs a ZipError with the given detail message.
     * @param s the {@code String} containing a detail message
     */
    public ZipError(String s) {
        super(s);
    }
}
