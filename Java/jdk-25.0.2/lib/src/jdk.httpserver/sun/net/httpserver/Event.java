/*
 * Copyright (c) 2005, 2025, Oracle and/or its affiliates. All rights reserved.
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

package sun.net.httpserver;

import java.util.Objects;

abstract sealed class Event {

    final ExchangeImpl exchange;

    protected Event(ExchangeImpl t) {
        this.exchange = t;
    }

    /**
     * Stopping event for the http server.
     * The event applies to the whole server and is not tied to any particular
     * exchange.
     */
    static final class StopRequested extends Event {
        StopRequested() {
            super(null);
        }
    }

    /**
     * Event indicating that writing is finished for a given exchange.
     */
    static final class WriteFinished extends Event {
        WriteFinished(ExchangeImpl t) {
            super(Objects.requireNonNull(t));
            assert !t.writefinished;
            t.writefinished = true;
        }
    }
}
