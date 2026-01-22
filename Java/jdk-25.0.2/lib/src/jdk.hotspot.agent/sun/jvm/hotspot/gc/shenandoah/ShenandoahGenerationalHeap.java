/*
 * Copyright Amazon.com Inc. or its affiliates. All Rights Reserved.
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

package sun.jvm.hotspot.gc.shenandoah;

import sun.jvm.hotspot.debugger.Address;

public class ShenandoahGenerationalHeap extends ShenandoahHeap {
    public ShenandoahGenerationalHeap(Address addr) {
        super(addr);
    }
}
