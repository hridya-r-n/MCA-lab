/*
 * Copyright (c) 2022, 2024, Oracle and/or its affiliates. All rights reserved.
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
package jdk.jfr.internal.management;

import java.io.Closeable;
import java.io.IOException;

/**
 * Purpose of this class is to provide a synchronization point when stopping a
 * recording. Without it, a race can happen where a stream advances beyond the
 * last chunk of the recording.
 *
 * Code that is processing the stream calls check() and Unless the recording is
 * in the process of being stopped, it will just return. On the other hand, if
 * the recording is stopping, the thread waits and when it wakes up an end
 * position should have been set (last chunk position) beyond which the stream
 * processing should not continue.
 */
public final class StreamBarrier implements Closeable {
    private final HiddenWait lock = new HiddenWait();
    private boolean activated = false;
    private boolean used = false;
    private long end = Long.MAX_VALUE;

    // Blocks thread until barrier is deactivated
    public void check() {
        synchronized (lock) {
            while (activated) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    public void setStreamEnd(long timestamp) {
        synchronized(lock) {
            end = timestamp;
        }
    }

    public long getStreamEnd() {
        synchronized(lock) {
            return end;
        }
    }

    public void activate() {
        synchronized (lock) {
            activated = true;
            used = true;
        }
    }

    @Override
    public synchronized void close() throws IOException {
        synchronized (lock) {
            activated = false;
            lock.notifyAll();
        }
    }

    /**
     * Returns {@code true) if barrier is, or has been, in active state, {@code false) otherwise.
     */
    public boolean used() {
        synchronized (lock) {
            return used;
        }
    }
}
