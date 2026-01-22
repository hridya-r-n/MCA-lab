/*
 * Copyright (c) 2000, 2025, Oracle and/or its affiliates. All rights reserved.
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

package sun.nio.ch;

import java.io.FileDescriptor;
import java.io.IOException;
import jdk.internal.access.JavaIOFileDescriptorAccess;
import jdk.internal.access.SharedSecrets;

/**
 * Allows different platforms to call different native methods
 * for read and write operations.
 */

abstract class NativeDispatcher {
    private static final JavaIOFileDescriptorAccess JIOFDA = SharedSecrets.getJavaIOFileDescriptorAccess();

    abstract int read(FileDescriptor fd, long address, int len)
        throws IOException;

    /**
     * Returns {@code true} if pread/pwrite needs to be synchronized with
     * position sensitive methods.
     */
    boolean needsPositionLock() {
        return false;
    }

    int pread(FileDescriptor fd, long address, int len, long position)
        throws IOException
    {
        throw new IOException("Operation Unsupported");
    }

    abstract long readv(FileDescriptor fd, long address, int len)
        throws IOException;

    abstract int write(FileDescriptor fd, long address, int len)
        throws IOException;

    int pwrite(FileDescriptor fd, long address, int len, long position)
        throws IOException
    {
        throw new IOException("Operation Unsupported");
    }

    abstract long writev(FileDescriptor fd, long address, int len)
        throws IOException;

    abstract void close(FileDescriptor fd) throws IOException;

    /**
     * Prepare the given file descriptor for closing. If a virtual thread is blocked
     * on the file descriptor then it is unparked so that it stops polling. On Unix systems,
     * if a platform thread is blocked on the file descriptor then the file descriptor is
     * dup'ed to a special fd and the thread signalled so that the syscall fails with EINTR.
     */
    final void preClose(FileDescriptor fd, long reader, long writer) throws IOException {
        if (NativeThread.isVirtualThread(reader) || NativeThread.isVirtualThread(writer)) {
            int fdVal = JIOFDA.get(fd);
            Poller.stopPoll(fdVal);
        }
        if (NativeThread.isNativeThread(reader) || NativeThread.isNativeThread(writer)) {
            implPreClose(fd, reader, writer);
        }
    }

    /**
     * This method does nothing by default. On Unix systems the file descriptor is dup'ed
     * to a special fd and native threads signalled.
     */

    void implPreClose(FileDescriptor fd, long reader, long writer) throws IOException {
        // Do nothing by default; this is only needed on Unix
    }

    /**
     * Duplicates a file descriptor.
     * @param fd1 the file descriptor to duplicate
     * @param fd2 the new file descriptor, the socket or file that it is connected
     *            to will be closed by this method
     */
    void dup(FileDescriptor fd1, FileDescriptor fd2) throws IOException {
        throw new UnsupportedOperationException();
    }
}
