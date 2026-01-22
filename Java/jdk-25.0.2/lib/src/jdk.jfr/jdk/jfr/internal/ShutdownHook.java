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

package jdk.jfr.internal;

import jdk.jfr.RecordingState;

/**
 * Class responsible for dumping recordings on exit
 *
 */
final class ShutdownHook extends Thread {
    private final PlatformRecorder recorder;
    Object tlabDummyObject;

    ShutdownHook(PlatformRecorder recorder) {
        super("JFR Shutdown Hook");
        this.recorder = recorder;
    }

    @Override
    public void run() {
        // this allocation is done in order to fetch a new TLAB before
        // starting any "real" operations. In low memory situations,
        // we would like to take an OOM as early as possible.
        tlabDummyObject = new Object();
        PlatformRecorder.setInShutDown();
        for (PlatformRecording recording : recorder.getRecordings()) {
            if (recording.getDumpOnExit() && recording.getState() == RecordingState.RUNNING) {
                dump(recording);
            }
        }
        recorder.destroy();
    }

    private void dump(PlatformRecording recording) {
        try {
            WriteablePath dest = recording.getDestination();
            if (dest == null) {
                dest = recording.makeDumpPath();
                recording.setDestination(dest);
            }
            if (dest != null) {
                recording.stop("Dump on exit");
            }
        } catch (Exception e) {
            if (Logger.shouldLog(LogTag.JFR, LogLevel.DEBUG)) {
                Logger.log(LogTag.JFR, LogLevel.DEBUG, "Could not dump recording " + recording.getName() + " on exit. " + e.getMessage());
            }
        }
    }

    static final class ExceptionHandler implements Thread.UncaughtExceptionHandler {
        @Override
        public void uncaughtException(Thread t, Throwable e) {
            JVM.uncaughtException(t, e);
        }
    }
}
