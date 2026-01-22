/*
 * Copyright (c) 2021, 2025, Oracle and/or its affiliates. All rights reserved.
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

package jdk.internal.foreign;

import java.lang.foreign.MemorySegment;
import java.lang.foreign.SegmentAllocator;

public final class SlicingAllocator implements SegmentAllocator {

    private final MemorySegment segment;

    private long sp = 0L;

    public SlicingAllocator(MemorySegment segment) {
        this.segment = segment;
    }

    public long currentOffset() {
        return sp;
    }

    public void resetTo(long offset) {
        if (offset < 0 || offset > sp)
            throw new IllegalArgumentException(String.format("offset %d should be in [0, %d] ", offset, sp));
        this.sp = offset;
    }

    public boolean canAllocate(long byteSize, long byteAlignment) {
        long min = segment.address();
        long start = Utils.alignUp(min + sp, byteAlignment) - min;
        return start + byteSize <= segment.byteSize();
    }

    MemorySegment trySlice(long byteSize, long byteAlignment) {
        long min = segment.address();
        long start = Utils.alignUp(min + sp, byteAlignment) - min;
        MemorySegment slice = segment.asSlice(start, byteSize, byteAlignment);
        sp = start + byteSize;
        return slice;
    }

    @Override
    public MemorySegment allocate(long byteSize, long byteAlignment) {
        Utils.checkAllocationSizeAndAlign(byteSize, byteAlignment);
        // try to slice from current segment first...
        return trySlice(byteSize, byteAlignment);
    }
}
