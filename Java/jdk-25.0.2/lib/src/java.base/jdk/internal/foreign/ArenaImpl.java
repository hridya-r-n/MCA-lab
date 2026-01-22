/*
 * Copyright (c) 2023, 2025, Oracle and/or its affiliates. All rights reserved.
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

import java.lang.foreign.Arena;
import java.lang.foreign.MemorySegment.Scope;

public final class ArenaImpl implements Arena {

    private final MemorySessionImpl session;
    private final boolean shouldReserveMemory;
    ArenaImpl(MemorySessionImpl session) {
        this.session = session;
        shouldReserveMemory = session instanceof ImplicitSession;
    }

    @Override
    public Scope scope() {
        return session;
    }

    @Override
    public void close() {
        session.close();
    }

    public NativeMemorySegmentImpl allocateNoInit(long byteSize, long byteAlignment) {
        return SegmentFactories.allocateNativeSegment(byteSize, byteAlignment, session, shouldReserveMemory, false);
    }

    @Override
    public NativeMemorySegmentImpl allocate(long byteSize, long byteAlignment) {
        return SegmentFactories.allocateNativeSegment(byteSize, byteAlignment, session, shouldReserveMemory, true);
    }
}
