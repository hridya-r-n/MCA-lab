/*
 * Copyright (c) 2023, 2024, Oracle and/or its affiliates. All rights reserved.
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
package jdk.jfr.internal.test;

@Deprecated(since = "0")
public class DeprecatedThing {
    public int counter;

    public void foo() {
        bar();
    }

    public void zoo() {
        System.out.println("Zoo invoked");
        for (int i = 0; i < 1_000_000; i++) {
            bar();
        }
    }

    private void bar() {
        baz();
    }

    public void baz() {
        inc();
    }

    private void inc() {
        counter++;
    }

    @Deprecated(forRemoval = true)
    public void instanceDeprecatedForRemoval() {
        for (int i = 0; i < 1_000_000; i++) {
           inc();
        }
    }

    @Deprecated(since = "0", forRemoval = true)
    public void instanceDeprecatedSinceForRemoval() {
        counter++;
    }
}
