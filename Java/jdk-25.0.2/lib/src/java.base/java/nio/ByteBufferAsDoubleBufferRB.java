/*
 * Copyright (c) 2000, 2024, Oracle and/or its affiliates. All rights reserved.
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

// -- This file was mechanically generated: Do not edit! -- //

package java.nio;

import java.lang.foreign.MemorySegment;
import jdk.internal.foreign.AbstractMemorySegmentImpl;
import jdk.internal.foreign.MemorySessionImpl;
import jdk.internal.foreign.SegmentFactories;
import jdk.internal.vm.annotation.ForceInline;
import java.util.Objects;
import jdk.internal.misc.Unsafe;




final

class ByteBufferAsDoubleBufferRB                  // package-private
    extends ByteBufferAsDoubleBufferB



{







    ByteBufferAsDoubleBufferRB(ByteBuffer bb, MemorySegment segment) {   // package-private












        super(bb, segment);

    }

    ByteBufferAsDoubleBufferRB(ByteBuffer bb,
                                     int mark, int pos, int lim, int cap,
                                     long addr, MemorySegment segment)
    {






        super(bb, mark, pos, lim, cap, addr, segment);

    }

    @Override
    Object base() {
        return bb.hb;
    }

    public DoubleBuffer slice() {
        int pos = this.position();
        int lim = this.limit();
        int rem = (pos <= lim ? lim - pos : 0);
        long addr = byteOffset(pos);
        return new ByteBufferAsDoubleBufferRB(bb, -1, 0, rem, rem, addr, segment);
    }

    @Override
    public DoubleBuffer slice(int index, int length) {
        Objects.checkFromIndexSize(index, length, limit());
        return new ByteBufferAsDoubleBufferRB(bb,
                                                    -1,
                                                    0,
                                                    length,
                                                    length,
                                                    byteOffset(index), segment);
    }

    public DoubleBuffer duplicate() {
        return new ByteBufferAsDoubleBufferRB(bb,
                                                    this.markValue(),
                                                    this.position(),
                                                    this.limit(),
                                                    this.capacity(),
                                                    address, segment);
    }

    public DoubleBuffer asReadOnlyBuffer() {








        return duplicate();

    }


































    public DoubleBuffer put(double x) {






        throw new ReadOnlyBufferException();

    }

    public DoubleBuffer put(int i, double x) {






        throw new ReadOnlyBufferException();

    }

    public DoubleBuffer compact() {

















        throw new ReadOnlyBufferException();

    }

    public boolean isDirect() {
        return bb.isDirect();
    }

    public boolean isReadOnly() {
        return true;
    }




































    @ForceInline
    @Override
    int scaleShifts() {
        return Integer.numberOfTrailingZeros(Double.BYTES);
    }

    @ForceInline
    @Override
    AbstractMemorySegmentImpl heapSegment(Object base,
                                          long offset,
                                          long length,
                                          boolean readOnly,
                                          MemorySessionImpl bufferScope) {
        return SegmentFactories.arrayOfByteSegment(base, offset, length, readOnly, bufferScope);
    }

    public ByteOrder order() {

        return ByteOrder.BIG_ENDIAN;




    }






}
