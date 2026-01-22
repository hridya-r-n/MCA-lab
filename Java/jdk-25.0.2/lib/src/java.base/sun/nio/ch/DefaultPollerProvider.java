/*
 * Copyright (c) 2019, 2023, Oracle and/or its affiliates. All rights reserved.
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
package sun.nio.ch;

import java.io.IOException;

/**
 * Default PollerProvider for Windows based on wepoll.
 */
class DefaultPollerProvider extends PollerProvider {
    DefaultPollerProvider() { }

    @Override
    int defaultReadPollers(Poller.Mode mode) {
        assert mode == Poller.Mode.SYSTEM_THREADS;
        int ncpus = Runtime.getRuntime().availableProcessors();
        return Math.max(Integer.highestOneBit(ncpus / 8), 1);
    }

    @Override
    int fdValToIndex(int fdVal, int toIndex) {
        return (fdVal >> 2) & (toIndex - 1);
    }

    @Override
    Poller readPoller(boolean subPoller) throws IOException {
        assert !subPoller;
        return new WEPollPoller(true);
    }

    @Override
    Poller writePoller(boolean subPoller) throws IOException {
        assert !subPoller;
        return new WEPollPoller(false);
    }
}
