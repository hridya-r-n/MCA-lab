/*
 * Copyright (c) 2018, 2024, Oracle and/or its affiliates. All rights reserved.
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

package sun.jvm.hotspot.gc.g1;

import java.io.PrintStream;
import sun.jvm.hotspot.gc.g1.G1HeapRegion;

public class G1PrintRegionClosure implements G1HeapRegionClosure {
    private PrintStream tty;

    public G1PrintRegionClosure(PrintStream tty) {
        this.tty = tty;
    }

    public void doHeapRegion(G1HeapRegion hr) {
        hr.printOn(tty);
    }
}
