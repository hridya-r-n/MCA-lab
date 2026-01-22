/*
 * Copyright (c) 2025, Oracle and/or its affiliates. All rights reserved.
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

package sun.tools.jstat;

public class Timestamp extends Expression {

    public Timestamp() {
        super();
    }

    public Object getValue() {
        return System.currentTimeMillis();
    }

    public String toString() {
        return "jstat.timestamp";
    }
}
