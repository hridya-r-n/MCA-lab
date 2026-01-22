/*
 * Copyright (c) 2024, Oracle and/or its affiliates. All rights reserved.
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
import sun.jvm.hotspot.memory.SystemDictionary;
import sun.jvm.hotspot.oops.DefaultHeapVisitor;
import sun.jvm.hotspot.oops.Klass;
import sun.jvm.hotspot.oops.ObjectHeap;
import sun.jvm.hotspot.oops.Oop;
import sun.jvm.hotspot.oops.OopUtilities;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VMLocksPrinter {
    private PrintStream tty;
    private Threads threads = VM.getVM().getThreads();

    public VMLocksPrinter(PrintStream tty) {
        this.tty = tty;
    }

    private String ownerThreadName(Address addr) {
        try {
            JavaThread thread = VM.getVM().getThreads().createJavaThreadWrapper(addr);
            return thread.getThreadName();
        } catch (Exception e) {
            return "Unknown thread";
        }
    }

    public void printVMLocks() {
         int maxNum = Mutex.maxNum();
         for (int i = 0; i < maxNum; i++) {
         Mutex mutex = new Mutex(Mutex.at(i));
            if (mutex.owner() != null) {
                sun.jvm.hotspot.runtime.Thread t = new sun.jvm.hotspot.runtime.Thread(mutex.owner());
                int nativeThreadId = t.osThread().threadId();
                tty.println("Internal VM Mutex " + mutex.name() + " is owned by " + ownerThreadName(mutex.owner())
                        + ", nid=" + nativeThreadId + ", address=" + mutex.owner());
               }
         }
    }
}
