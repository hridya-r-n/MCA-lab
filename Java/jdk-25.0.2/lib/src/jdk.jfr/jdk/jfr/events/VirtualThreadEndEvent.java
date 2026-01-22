/*
 * Copyright (c) 2021, 2024, Oracle and/or its affiliates. All rights reserved.
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

package jdk.jfr.events;

import jdk.jfr.Category;
import jdk.jfr.Label;
import jdk.jfr.Name;
import jdk.jfr.internal.MirrorEvent;
import jdk.jfr.internal.RemoveFields;

@Category("Java Application")
@Label("Virtual Thread End")
@Name("jdk.VirtualThreadEnd")
@RemoveFields({"duration", "stackTrace"})
public final class VirtualThreadEndEvent extends MirrorEvent {

    @Label("Thread Id")
    public long javaThreadId;

}
