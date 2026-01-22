/*
 * Copyright (c) 2025, Oracle and/or its affiliates. All rights reserved.
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

import java.nio.file.Path;
import java.util.Optional;

/**
 * File association.
 */
public interface FileAssociation {

    /**
     * Returns file association description if available or an empty {@link Optional} instance.
     * @return file association description
     */
    Optional<String> description();

    /**
     * Returns file association icon if available or an empty {@link Optional} instance.
     * @see {@link #hasIcon()}
     * @return file association icon
     */
    Optional<Path> icon();

    /**
     * Returns <code>true</code> if the file association has an icon.
     * @see {@link #icon()}
     * @return <code>true</code> if the file association has an icon
     */
    default boolean hasIcon() {
        return icon().isPresent();
    }

    /**
     * Returns file association MIME type. E.g.: <code>application/foo</code>, <code>text/plain<code>.
     * @return file association MIME type
     */
    String mimeType();

    /**
     * Returns file association extension. E.g.: <code>.txt</code>
     * @return file association extension
     */
    String extension();

    /**
     * Default implementation of {@link FileAssociation} interface
     */
    record Stub(Optional<String> description, Optional<Path> icon, String mimeType, String extension) implements FileAssociation {
    }
}
