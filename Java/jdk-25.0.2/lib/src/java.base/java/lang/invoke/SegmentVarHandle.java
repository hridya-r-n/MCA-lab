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

import java.lang.foreign.MemoryLayout;
import java.lang.foreign.MemorySegment;
import java.util.Objects;

import jdk.internal.foreign.AbstractMemorySegmentImpl;
import jdk.internal.misc.ScopedMemoryAccess;
import jdk.internal.vm.annotation.ForceInline;

/**
 * A var handle that accesses primitive values in a memory segment.
 */
final class SegmentVarHandle extends VarHandle {

    // Common implementation fields for the VarForms
    static final boolean BE = MethodHandleStatics.UNSAFE.isBigEndian();
    static final ScopedMemoryAccess SCOPED_MEMORY_ACCESS = ScopedMemoryAccess.getScopedMemoryAccess();

    /** endianness **/
    final boolean be;
    /** The layout the accessed segment must be compatible with. */
    final MemoryLayout enclosing;
    /** The offset value, if is constant. vform decides if offset is constant or variable. */
    final long offset;

    SegmentVarHandle(VarForm form, boolean be, MemoryLayout enclosing, long offset, boolean exact) {
        super(form, exact);
        this.be = be;
        this.enclosing = enclosing;
        this.offset = offset;
    }

    @Override
    final MethodType accessModeTypeUncached(VarHandle.AccessType accessType) {
        var getType = vform.methodType_table[0]; // erased, but our value type is erase-compatible
        return getType.parameterCount() == 2
                ? accessType.accessModeType(MemorySegment.class, getType.returnType(), long.class)
                : accessType.accessModeType(MemorySegment.class, getType.returnType(), long.class, long.class);
    }

    @Override
    public SegmentVarHandle withInvokeExactBehavior() {
        return hasInvokeExactBehavior() ?
                this :
                new SegmentVarHandle(vform, be, enclosing, offset, true);
    }

    @Override
    public SegmentVarHandle withInvokeBehavior() {
        return !hasInvokeExactBehavior() ?
                this :
                new SegmentVarHandle(vform, be, enclosing, offset, false);
    }

    // Common implementation methods for the VarForms

    @ForceInline
    static long offset(AbstractMemorySegmentImpl bb, long base, long offset) {
        long segment_base = bb.unsafeGetOffset();
        return segment_base + base + offset;
    }

    @ForceInline
    AbstractMemorySegmentImpl checkSegment(Object obb, long base, boolean ro) {
        AbstractMemorySegmentImpl oo = (AbstractMemorySegmentImpl) Objects.requireNonNull(obb);
        oo.checkEnclosingLayout(base, this.enclosing, ro);
        return oo;
    }
}
