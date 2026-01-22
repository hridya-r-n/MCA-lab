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

package jdk.jpackage.internal.pipeline;

import java.util.Objects;

public record DirectedEdge<T>(T tail, T head) {

    public DirectedEdge {
        Objects.requireNonNull(tail);
        Objects.requireNonNull(head);

        if (tail.equals(head)) {
            throw new IllegalArgumentException("Loop edge");
        }
    }

    public T from() {
        return tail;
    }

    public T to() {
        return head;
    }

    public static <U> DirectedEdge<U> create(U tail, U head) {
        return new DirectedEdge<>(tail, head);
    }
}
