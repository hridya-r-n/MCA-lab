/*
 * Copyright (c) 2023, Oracle and/or its affiliates. All rights reserved.
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

import jdk.jfr.internal.RemoveFields;

@RemoveFields({"duration", "eventThread", "stackTrace"})
abstract class AbstractPeriodicEvent extends AbstractJDKEvent {
}
