/*
 * Copyright (c) 2019, 2025, Oracle and/or its affiliates. All rights reserved.
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
package java.lang.invoke;

import jdk.internal.foreign.AbstractMemorySegmentImpl;
import jdk.internal.vm.annotation.ForceInline;

import java.lang.foreign.MemorySegment;

import static java.lang.invoke.SegmentVarHandle.*;

// -- This file was mechanically generated: Do not edit! -- //

sealed class VarHandleSegmentAsShorts {

    static final int NON_PLAIN_ACCESS_MIN_ALIGN_MASK = Short.BYTES - 1;

    static VarForm selectForm(long alignmentMask, boolean constantOffset) {
        return (alignmentMask & NON_PLAIN_ACCESS_MIN_ALIGN_MASK) != NON_PLAIN_ACCESS_MIN_ALIGN_MASK ?
                (constantOffset ? CONSTANT_OFFSET_FORM : VARIABLE_OFFSET_FORM) :
                (constantOffset ? VarHandleSegmentAsShortsAligned.CONSTANT_OFFSET_FORM : VarHandleSegmentAsShortsAligned.VARIABLE_OFFSET_FORM);
    }

    static final VarForm CONSTANT_OFFSET_FORM = new VarForm(VarHandleSegmentAsShorts.class, MemorySegment.class, short.class, long.class);
    static final VarForm VARIABLE_OFFSET_FORM = new VarForm(VarHandleSegmentAsShorts.class, MemorySegment.class, short.class, long.class, long.class);

    VarHandleSegmentAsShorts() { throw new AssertionError(); }

    @ForceInline
    static short get(VarHandle ob, Object obb, long base) {
        return get(ob, obb, base, ((SegmentVarHandle) ob).offset);
    }

    @ForceInline
    static short get(VarHandle ob, Object obb, long base, long offset) {
        SegmentVarHandle handle = (SegmentVarHandle)ob;
        AbstractMemorySegmentImpl bb = handle.checkSegment(obb, base, true);
        return SCOPED_MEMORY_ACCESS.getShortUnaligned(bb.sessionImpl(),
                bb.unsafeGetBase(),
                offset(bb, base, offset),
                handle.be);
    }

    @ForceInline
    static void set(VarHandle ob, Object obb, long base, short value) {
        set(ob, obb, base, ((SegmentVarHandle) ob).offset, value);
    }

    @ForceInline
    static void set(VarHandle ob, Object obb, long base, long offset, short value) {
        SegmentVarHandle handle = (SegmentVarHandle)ob;
        AbstractMemorySegmentImpl bb = handle.checkSegment(obb, base, false);
        SCOPED_MEMORY_ACCESS.putShortUnaligned(bb.sessionImpl(),
                bb.unsafeGetBase(),
                offset(bb, base, offset),
                value,
                handle.be);
    }
}

// This class must be accessed through non-aligned VarHandleSegmentAsShorts
final class VarHandleSegmentAsShortsAligned extends VarHandleSegmentAsShorts {

    static final VarForm CONSTANT_OFFSET_FORM = new VarForm(VarHandleSegmentAsShortsAligned.class, VarHandleSegmentAsShorts.CONSTANT_OFFSET_FORM);
    static final VarForm VARIABLE_OFFSET_FORM = new VarForm(VarHandleSegmentAsShortsAligned.class, VarHandleSegmentAsShorts.VARIABLE_OFFSET_FORM);

    VarHandleSegmentAsShortsAligned() { throw new AssertionError(); }

    @ForceInline
    static short convEndian(boolean big, short n) {
        return big == BE ? n : Short.reverseBytes(n);
    }

    @ForceInline
    static short getVolatile(VarHandle ob, Object obb, long base) {
        return getVolatile(ob, obb, base, ((SegmentVarHandle) ob).offset);
    }

    @ForceInline
    static short getVolatile(VarHandle ob, Object obb, long base, long offset) {
        SegmentVarHandle handle = (SegmentVarHandle)ob;
        AbstractMemorySegmentImpl bb = handle.checkSegment(obb, base, true);
        return convEndian(handle.be,
                          SCOPED_MEMORY_ACCESS.getShortVolatile(bb.sessionImpl(),
                                  bb.unsafeGetBase(),
                                  offset(bb, base, offset)));
    }

    @ForceInline
    static void setVolatile(VarHandle ob, Object obb, long base, short value) {
        setVolatile(ob, obb, base, ((SegmentVarHandle) ob).offset, value);
    }

    @ForceInline
    static void setVolatile(VarHandle ob, Object obb, long base, long offset, short value) {
        SegmentVarHandle handle = (SegmentVarHandle)ob;
        AbstractMemorySegmentImpl bb = handle.checkSegment(obb, base, false);
        SCOPED_MEMORY_ACCESS.putShortVolatile(bb.sessionImpl(),
                bb.unsafeGetBase(),
                offset(bb, base, offset),
                convEndian(handle.be, value));
    }

    @ForceInline
    static short getAcquire(VarHandle ob, Object obb, long base) {
        return getAcquire(ob, obb, base, ((SegmentVarHandle) ob).offset);
    }

    @ForceInline
    static short getAcquire(VarHandle ob, Object obb, long base, long offset) {
        SegmentVarHandle handle = (SegmentVarHandle)ob;
        AbstractMemorySegmentImpl bb = handle.checkSegment(obb, base, true);
        return convEndian(handle.be,
                          SCOPED_MEMORY_ACCESS.getShortAcquire(bb.sessionImpl(),
                                  bb.unsafeGetBase(),
                                  offset(bb, base, offset)));
    }

    @ForceInline
    static void setRelease(VarHandle ob, Object obb, long base, short value) {
        setRelease(ob, obb, base, ((SegmentVarHandle) ob).offset, value);
    }

    @ForceInline
    static void setRelease(VarHandle ob, Object obb, long base, long offset, short value) {
        SegmentVarHandle handle = (SegmentVarHandle)ob;
        AbstractMemorySegmentImpl bb = handle.checkSegment(obb, base, false);
        SCOPED_MEMORY_ACCESS.putShortRelease(bb.sessionImpl(),
                bb.unsafeGetBase(),
                offset(bb, base, offset),
                convEndian(handle.be, value));
    }

    @ForceInline
    static short getOpaque(VarHandle ob, Object obb, long base) {
        return getOpaque(ob, obb, base, ((SegmentVarHandle) ob).offset);
    }

    @ForceInline
    static short getOpaque(VarHandle ob, Object obb, long base, long offset) {
        SegmentVarHandle handle = (SegmentVarHandle)ob;
        AbstractMemorySegmentImpl bb = handle.checkSegment(obb, base, true);
        return convEndian(handle.be,
                          SCOPED_MEMORY_ACCESS.getShortOpaque(bb.sessionImpl(),
                                  bb.unsafeGetBase(),
                                  offset(bb, base, offset)));
    }

    @ForceInline
    static void setOpaque(VarHandle ob, Object obb, long base, short value) {
        setOpaque(ob, obb, base, ((SegmentVarHandle) ob).offset, value);
    }

    @ForceInline
    static void setOpaque(VarHandle ob, Object obb, long base, long offset, short value) {
        SegmentVarHandle handle = (SegmentVarHandle)ob;
        AbstractMemorySegmentImpl bb = handle.checkSegment(obb, base, false);
        SCOPED_MEMORY_ACCESS.putShortOpaque(bb.sessionImpl(),
                bb.unsafeGetBase(),
                offset(bb, base, offset),
                convEndian(handle.be, value));
    }
}
