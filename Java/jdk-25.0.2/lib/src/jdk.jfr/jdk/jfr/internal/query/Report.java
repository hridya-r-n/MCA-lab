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
package jdk.jfr.internal.query;

import java.io.IOException;
import java.time.Instant;
import java.util.List;

import jdk.jfr.internal.LogLevel;
import jdk.jfr.internal.LogTag;
import jdk.jfr.internal.Logger;
import jdk.jfr.internal.consumer.AbstractEventStream;
import jdk.jfr.internal.dcmd.QueryRecording;
import jdk.jfr.internal.util.Output.BufferedPrinter;
import jdk.jfr.internal.util.UserDataException;
import jdk.jfr.internal.util.UserSyntaxException;

public record Report(String name) {

    public static List<Report> getReports() {
        return ViewFile.getDefault().getViewConfigurations() .stream().map(view -> new Report(view.name())).toList();
    }

    public void print(Instant startTime, Instant endTime) {
        Logger.log(LogTag.JFR, LogLevel.DEBUG, "Writing report " + name);
        BufferedPrinter output = new BufferedPrinter(System.out);
        try (QueryRecording qr = new QueryRecording(startTime, endTime)) {
            AbstractEventStream stream = (AbstractEventStream) qr.getStream();
            stream.setWaitForChunks(false);
            Configuration configuration = new Configuration();
            configuration.startTime = startTime;
            configuration.endTime = endTime;
            configuration.output = output;
            ViewPrinter printer = new ViewPrinter(configuration, stream);
            printer.execute(name);
            Logger.log(LogTag.JFR, LogLevel.DEBUG, "Report " + name + " written successfully.");
        } catch (IOException | UserDataException | UserSyntaxException e) {
            Logger.log(LogTag.JFR, LogLevel.WARN, "Error writing report " + name + " on exit: " + e.getMessage());
        }
        output.flush();
    }
}
