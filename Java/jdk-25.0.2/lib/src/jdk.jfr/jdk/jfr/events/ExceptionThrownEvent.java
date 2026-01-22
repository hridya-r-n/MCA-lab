/*
 * Copyright (c) 2012, 2025, Oracle and/or its affiliates. All rights reserved.
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
import jdk.jfr.Description;
import jdk.jfr.Label;
import jdk.jfr.Name;
import jdk.jfr.Throttle;
import jdk.jfr.internal.MirrorEvent;
import jdk.jfr.internal.RemoveFields;
import jdk.jfr.internal.Type;

@Name(Type.EVENT_NAME_PREFIX + "JavaExceptionThrow")
@Label("Java Exception")
@Category("Java Application")
@Description("An object derived from java.lang.Exception has been created")
@RemoveFields("duration")
@Throttle
public final class ExceptionThrownEvent extends MirrorEvent {

    @Label("Message")
    public String message;

    @Label("Class")
    public Class<?> thrownClass;
}
