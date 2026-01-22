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

import static jdk.jpackage.internal.util.PathUtils.resolveNullablePath;

import java.nio.file.Path;
import jdk.jpackage.internal.util.CompositeProxy;

/**
 * Java runtime app image layout.
 * <p>
 * Use {@link #DEFAULT} field to get the default runtime app image layout or
 * {@link #create(Path)} method to create custom runtime app image layout.
 */
public interface RuntimeLayout extends AppImageLayout {

    @Override
    default RuntimeLayout resolveAt(Path root) {
        return create(new AppImageLayout.Stub(resolveNullablePath(root, rootDirectory()),
                resolveNullablePath(root, runtimeDirectory())));
    }

    /**
     * Creates Java runtime app image layout.
     * <p>
     * {@link #runtimeDirectory()} method
     * called on the created object will return the value of the
     * <code>runtimeDirectory<code> parameter. {@link #rootDirectory()} method
     * called on the created object will return <code>Path.of("")<code> value.
     *
     * @param runtimeDirectory Java runtime directory
     * @return Java runtime app image layout
     */
    static RuntimeLayout create(Path runtimeDirectory) {
        return create(new AppImageLayout.Stub(Path.of(""), runtimeDirectory));
    }

    private static RuntimeLayout create(AppImageLayout layout) {
        return CompositeProxy.create(RuntimeLayout.class, layout);
    }

    /**
     * Singleton.
     * <p>
     * {@link #runtimeDirectory()} of the singleton returns empty string (""), i.e.
     * the runtime directory is the same as the directory at which the layout is
     * resolved.
     */
    static final RuntimeLayout DEFAULT = create(Path.of(""));
}
