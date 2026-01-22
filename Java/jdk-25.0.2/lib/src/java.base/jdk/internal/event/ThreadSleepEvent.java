/*
 * Copyright (c) 2020, 2024, Oracle and/or its affiliates. All rights reserved.
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

package jdk.internal.event;

/**
 * Event recording thread sleeping.
 */

public final class ThreadSleepEvent extends Event {
    public long time;
}
