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
 */

package sun.jvm.hotspot.runtime;

import sun.jvm.hotspot.debugger.Address;

public class HiddenJavaThread extends JavaThread {

    public HiddenJavaThread(Address addr) {
        super(addr);
    }

    @Override
    public boolean isHiddenFromExternalView() { return true; }

}
