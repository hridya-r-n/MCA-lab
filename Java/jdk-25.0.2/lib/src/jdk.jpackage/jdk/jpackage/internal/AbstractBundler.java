/*
 * Copyright (c) 2014, 2025, Oracle and/or its affiliates. All rights reserved.
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

package jdk.jpackage.internal;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;
import jdk.jpackage.internal.util.FileUtils;


/**
 * AbstractBundler
 *
 * This is the base class all bundlers extend from.
 * It contains methods and parameters common to all bundlers.
 * The concrete implementations are in the platform specific bundlers.
 */
abstract class AbstractBundler implements Bundler {

    @Override
    public String toString() {
        return getName();
    }

    @Override
    public void cleanup(Map<String, ? super Object> params) {
        try {
            FileUtils.deleteRecursive(
                    StandardBundlerParam.TEMP_ROOT.fetchFrom(params));
        } catch (IOException e) {
            Log.verbose(e.getMessage());
        }
    }
}
