/*
 * Copyright (c) 2018, 2024, Oracle and/or its affiliates. All rights reserved.
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

import jdk.jfr.*;
import jdk.jfr.internal.MirrorEvent;
import jdk.jfr.internal.RemoveFields;

@Category({"Java Development Kit", "Security"})
@Label("Security Property Modification")
@Name("jdk.SecurityPropertyModification")
@Description("Modification of Security property")
@RemoveFields("duration")
@StackFilter({"java.security.Security::setProperty"})
public final class SecurityPropertyModificationEvent extends MirrorEvent {
    @Label("Key")
    public String key;

    @Label("Value")
    public String value;
}
