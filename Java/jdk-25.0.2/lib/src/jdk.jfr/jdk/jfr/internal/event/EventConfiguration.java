/*
 * Copyright (c) 2016, 2025, Oracle and/or its affiliates. All rights reserved.
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

package jdk.jfr.internal.event;

import jdk.jfr.EventType;
import jdk.jfr.SettingControl;
import jdk.jfr.internal.EventControl;
import jdk.jfr.internal.EventInstrumentation;
import jdk.jfr.internal.JVM;
import jdk.jfr.internal.PlatformEventType;
import jdk.jfr.internal.settings.Throttler;

public record EventConfiguration(
    PlatformEventType platformEventType,
    EventType eventType,
    EventControl eventControl,
    SettingControl[] settings,
    Throttler throttler,
    long id) {

    // Accessed by generated code in event class
    public boolean shouldCommit(long duration) {
        return isEnabled() && duration >= platformEventType.getThresholdTicks();
    }

    // Accessed by generated code in event class. Used by:
    // static boolean shouldThrottleCommit(long duration, long timestamp)
    public boolean shouldThrottleCommit(long duration, long timestamp) {
        return isEnabled() && duration >= platformEventType.getThresholdTicks() && throttler.sample(timestamp);
    }

    // Caller must of Event::shouldThrottleCommit must check enablement.
    // Accessed by generated code in event class. Used by:
    // static boolean shouldThrottleCommit(long timestamp)
    public boolean shouldThrottleCommit(long timestamp) {
        return throttler.sample(timestamp);
    }

    // Accessed by generated code in event class
    public SettingControl getSetting(int index) {
        return settings[index];
    }

    // Accessed by generated code in event class
    public boolean isEnabled() {
        return platformEventType.isCommittable();
    }

    public long throttle(long startTime, long rawDuration) {
        // We have already tried to throttle, return as is
        if ((rawDuration & EventInstrumentation.MASK_THROTTLE_BITS) != 0) {
            return rawDuration;
        }
        long endTime = startTime + rawDuration;
        if (throttler.sample(endTime)) {
            return rawDuration | EventInstrumentation.MASK_THROTTLE_CHECK_SUCCESS;
        } else {
            return rawDuration | EventInstrumentation.MASK_THROTTLE_CHECK_FAIL;
        }
    }

    // Not really part of the configuration, but the method
    // needs to be somewhere the event class can access,
    // but not part of the public API.
    public static long timestamp() {
        return JVM.counterTime();
    }

    // Accessed by generated code in event class
    public static long duration(long startTime) {
        if (startTime == 0) {
            // User forgot to invoke begin, or instrumentation was
            // added after the user invoked begin.
            // Returning 0 will make it an instant event
            return 0;
        }
        return timestamp() - startTime;
    }

    public boolean isRegistered() {
        return platformEventType.isRegistered();
    }
}
