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
 *
 */
package jdk.jfr.internal.management;

/**
 * The HiddenWait class is used to exclude jdk.JavaMonitorWait events
 * from being generated when Object.wait() is called on an object of this type.
 */
public final class HiddenWait {

    public synchronized boolean takeNap(long timeoutMillis) {
        try {
            this.wait(timeoutMillis);
            return true;
        } catch (InterruptedException e) {
            // Ok, ignore
            return false;
        }
    }
}
