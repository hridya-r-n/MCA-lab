/*
 * Copyright (c) 2019, 2024, Oracle and/or its affiliates. All rights reserved.
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

package jdk.internal.misc;

import jdk.internal.access.JavaLangAccess;
import jdk.internal.access.SharedSecrets;

/**
 * Defines static methods to mark the beginning and end of a possibly blocking
 * operation. The methods are intended to be used with try-finally as follows:
 * {@snippet lang=java :
 *     boolean attempted = Blocker.begin();
 *     try {
 *         // blocking operation
 *     } finally {
 *         Blocker.end(attempted);
 *     }
 * }
 * If invoked from a virtual thread and the underlying carrier thread is a
 * CarrierThread then the code in the block runs as if it were in run in
 * ForkJoinPool.ManagedBlocker. This means the pool can be expanded to support
 * additional parallelism during the blocking operation.
 */
public class Blocker {
    private static final JavaLangAccess JLA;
    static {
        JLA = SharedSecrets.getJavaLangAccess();
        if (JLA == null) {
            throw new InternalError("JavaLangAccess not setup");
        }
    }

    private Blocker() { }

    private static Thread currentCarrierThread() {
        return JLA.currentCarrierThread();
    }

    /**
     * Marks the beginning of a blocking operation.
     * @return true if tryCompensate attempted
     */
    public static boolean begin() {
        if (VM.isBooted()
                && Thread.currentThread().isVirtual()
                && currentCarrierThread() instanceof CarrierThread ct) {
            return ct.beginBlocking();
        }
        return false;
    }

    /**
     * Marks the beginning of a possibly blocking operation.
     * @param blocking true if the operation may block, otherwise false
     * @return true if tryCompensate attempted
     */
    public static boolean begin(boolean blocking) {
        return (blocking) ? begin() : false;
    }

    /**
     * Marks the end of an operation that may have blocked.
     * @param attempted if tryCompensate attempted
     */
    public static void end(boolean attempted) {
        if (attempted) {
            CarrierThread ct = (CarrierThread) currentCarrierThread();
            ct.endBlocking();
        }
    }
}
