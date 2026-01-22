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
 */

package sun.jvm.hotspot.code;

import java.io.PrintStream;

/** A placeholder value that has no concrete meaning other than helping constructing
 * other values.
 */
public class MarkerValue extends ScopeValue {
    public boolean isMarker() { return true; }

    public void printOn(PrintStream tty) {
        tty.print("marker");
    }
}
