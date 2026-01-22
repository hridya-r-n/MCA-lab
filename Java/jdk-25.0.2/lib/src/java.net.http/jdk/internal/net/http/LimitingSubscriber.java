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

package jdk.internal.net.http;

import jdk.internal.net.http.ResponseSubscribers.TrustedSubscriber;
import jdk.internal.net.http.common.Utils;

import java.io.IOException;
import java.net.http.HttpResponse.BodySubscriber;
import java.nio.ByteBuffer;
import java.util.List;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.Flow.Subscription;

import static java.util.Objects.requireNonNull;

/**
 * A subscriber limiting the maximum number of bytes that are allowed to be consumed by a downstream subscriber.
 *
 * @param <T> the response type
 */
public final class LimitingSubscriber<T> implements TrustedSubscriber<T> {

    private final BodySubscriber<T> downstreamSubscriber;

    private final long capacity;

    private State state;

    private long length;

    private interface State {

        State TERMINATED = new State() {};

        record Subscribed(Subscription subscription) implements State {}

    }

    /**
     * @param downstreamSubscriber the downstream subscriber to pass received data to
     * @param capacity the maximum number of bytes that are allowed
     * @throws IllegalArgumentException if {@code capacity} is negative
     */
    public LimitingSubscriber(BodySubscriber<T> downstreamSubscriber, long capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("capacity must not be negative: " + capacity);
        }
        this.downstreamSubscriber = requireNonNull(downstreamSubscriber, "downstreamSubscriber");
        this.capacity = capacity;
    }

    @Override
    public void onSubscribe(Subscription subscription) {
        requireNonNull(subscription, "subscription");
        if (state != null) {
            subscription.cancel();
        } else {
            state = new State.Subscribed(subscription);
            downstreamSubscriber.onSubscribe(subscription);
        }
    }

    @Override
    public void onNext(List<ByteBuffer> buffers) {

        // Check arguments
        requireNonNull(buffers, "buffers");
        assert Utils.hasRemaining(buffers);

        // Short-circuit if not subscribed
        if (!(state instanceof State.Subscribed subscribed)) {
            return;
        }

        // See if we may consume the input
        boolean lengthAllocated = allocateLength(buffers);
        if (lengthAllocated) {
            downstreamSubscriber.onNext(buffers);
        } else { // Otherwise, trigger failure
            state = State.TERMINATED;
            downstreamSubscriber.onError(new IOException("body exceeds capacity: " + capacity));
            subscribed.subscription.cancel();
        }

    }

    private boolean allocateLength(List<ByteBuffer> buffers) {
        long bufferLength = Utils.remaining(buffers);
        long nextLength;
        try {
            nextLength = Math.addExact(length, bufferLength);
        } catch (ArithmeticException _) {
            return false;
        }
        if (nextLength > capacity) {
            return false;
        }
        length = nextLength;
        return true;
    }

    @Override
    public void onError(Throwable throwable) {
        requireNonNull(throwable, "throwable");
        if (state instanceof State.Subscribed) {
            state = State.TERMINATED;
            downstreamSubscriber.onError(throwable);
        }
    }

    @Override
    public void onComplete() {
        if (state instanceof State.Subscribed) {
            state = State.TERMINATED;
            downstreamSubscriber.onComplete();
        }
    }

    @Override
    public CompletionStage<T> getBody() {
        return downstreamSubscriber.getBody();
    }

}
