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
package jdk.jfr.internal;

import jdk.jfr.Enabled;
import jdk.jfr.Registered;
import jdk.jfr.StackTrace;

/**
 * A mirror event is a fictitious event class that contains metadata about an
 * event, but not the implementation to write the event data to buffers.
 * <p>
 * A mirror event should be used when an event class is in a module where a
 * dependency on the jdk.jfr module is not possible, for example, due to a
 * circular dependency.
 * <p>
 * Subclass the MirrorEvent class and add the exact same fields as the actual
 * event, but with labels, descriptions etc.
 * <p>
 * For the mirror mechanism to work, the mirror class must be registered in the
 * jdk.jfr.internal.MirrorEvents class.
 */
@Registered(false)
@Enabled(false)
@StackTrace(false)
public abstract class MirrorEvent {
}
