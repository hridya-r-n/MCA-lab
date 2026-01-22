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
@Label("X509 Validation")
@Name("jdk.X509Validation")
@Description("Serial numbers from X.509 Certificates forming chain of trust")
@RemoveFields("duration")
public final class X509ValidationEvent extends MirrorEvent {
    @CertificateId
    @Label("Certificate Id")
    @Unsigned
    public long certificateId;

    @Label("Certificate Position")
    @Description("Certificate position in chain of trust, 1 = trust anchor")
    public int certificatePosition;

    @Label("Validation Counter")
    public long validationCounter;
}
