/*
 * Copyright (c) 2007, 2024, Oracle and/or its affiliates. All rights reserved.
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

package sun.font;

import sun.awt.OSInfo;

@SuppressWarnings("restricted")
public class FontManagerNativeLibrary {
    static {
       /* REMIND do we really have to load awt here? */
       System.loadLibrary("awt");
       if (OSInfo.getOSType() == OSInfo.OSType.WINDOWS) {
           /* Ideally fontmanager library should not depend on
              particular implementation of the font scaler.
              However, freetype scaler is basically small wrapper on
              top of freetype library (that is used in binary form).

              This wrapper is compiled into fontmanager and this make
              fontmanger library depending on freetype library.

              On Windows DLL's in the JRE's BIN directory cannot be
              found by windows DLL loading as that directory is not
              on the Windows PATH.

              To avoid link error we have to load freetype explicitly
              before we load fontmanager.

              NB: consider moving freetype wrapper part to separate
                  shared library in order to avoid dependency. */
           System.loadLibrary("freetype");
       }
       System.loadLibrary("fontmanager");
    }

    /*
     * Call this method to ensure libraries are loaded.
     *
     * Method acts as trigger to ensure this class is loaded
     * (and therefore initializer code is executed).
     * Actual loading is performed by static initializer.
     */
    public static void load() {}
}
