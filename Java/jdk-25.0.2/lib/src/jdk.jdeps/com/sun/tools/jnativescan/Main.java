/*
 * Copyright (c) 2024, Oracle and/or its affiliates. All rights reserved.
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
package com.sun.tools.jnativescan;

import jdk.internal.joptsimple.*;
import jdk.internal.opt.CommandLine;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.spi.ToolProvider;

public class Main {

    private static boolean DEBUG = Boolean.getBoolean("com.sun.tools.jnativescan.DEBUG");

    private static final int SUCCESS_CODE = 0;
    private static final int FATAL_ERROR_CODE = 1;

    private final PrintWriter out;
    private final PrintWriter err;

    private Main(PrintWriter out, PrintWriter err) {
        this.out = out;
        this.err = err;
    }

    private void printError(String message) {
        err.println("ERROR: " + message);
    }

    private void printUsage()  {
        out.print("""
            Use 'jnativescan --help' for help
            """);
    }

    private void printVersion() {
        out.println(System.getProperty("java.version"));
    }

    public int run(String[] args) {
        if (args.length < 1) {
            printUsage();
            return FATAL_ERROR_CODE;
        }

        try {
            String[] expandedArgs = expandArgFiles(args);
            parseOptionsAndRun(expandedArgs);
        } catch (JNativeScanFatalError fatalError) {
            printError(fatalError.getMessage());
            for (Throwable cause = fatalError.getCause();
                     cause instanceof JNativeScanFatalError jNativeScanFatalError;
                     cause = jNativeScanFatalError.getCause()) {
                err.println("CAUSED BY: " + jNativeScanFatalError.getMessage());
            }
            if (DEBUG) {
                fatalError.printStackTrace(err);
            }
            return FATAL_ERROR_CODE;
        } catch (Throwable e) {
            printError("Unexpected exception encountered");
            e.printStackTrace(err);
            return FATAL_ERROR_CODE;
        }

        return SUCCESS_CODE;
    }

    private void parseOptionsAndRun(String[] expandedArgs) throws JNativeScanFatalError {
        OptionParser parser = new OptionParser(false);
        OptionSpec<Void> helpOpt = parser.acceptsAll(List.of("?", "h", "help"), "help").forHelp();
        OptionSpec<Void> versionOpt = parser.accepts("version", "Print version information and exit");
        OptionSpec<Path> classPathOpt = parser.accepts(
                "class-path",
                "The class path as used at runtime")
                .withRequiredArg()
                .withValuesSeparatedBy(File.pathSeparatorChar)
                .withValuesConvertedBy(PARSE_PATH);
        OptionSpec<Path> modulePathOpt = parser.accepts(
                "module-path",
                "The module path as used at runtime")
                .withRequiredArg()
                .withValuesSeparatedBy(File.pathSeparatorChar)
                .withValuesConvertedBy(PARSE_PATH);
        OptionSpec<Runtime.Version> releaseOpt = parser.accepts(
                "release",
                "The runtime version that will run the application")
                .withRequiredArg()
                .withValuesConvertedBy(PARSE_VERSION);
        OptionSpec<String> addModulesOpt = parser.accepts(
                "add-modules",
                "List of root modules to scan")
                .requiredIf(modulePathOpt)
                .withRequiredArg()
                .withValuesSeparatedBy(',');
        OptionSpec<Void> printNativeAccessOpt = parser.accepts(
                "print-native-access",
                "print a comma separated list of modules that may perform native access operations." +
                        " ALL-UNNAMED is used to indicate unnamed modules.");

        OptionSet optionSet;
        try {
            optionSet = parser.parse(expandedArgs);
        } catch (OptionException oe) {
            throw new JNativeScanFatalError("Parsing options failed: " + oe.getMessage(), oe);
        }

        if (optionSet.nonOptionArguments().size() != 0) {
            throw new JNativeScanFatalError("jnativescan does not accept positional arguments");
        }

        if (optionSet.has(helpOpt)) {
            out.println("""
                The jnativescan tool can be used to find methods that may access native functionality when
                run. This includes restricted method calls and 'native' method declarations.
                """);
            try {
                parser.printHelpOn(out);
                return;
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }

        if (optionSet.has(versionOpt)) {
            printVersion();
            return;
        }

        List<Path> classPathJars = optionSet.valuesOf(classPathOpt);
        List<Path> modulePaths = optionSet.valuesOf(modulePathOpt);
        List<String> rootModules = optionSet.valuesOf(addModulesOpt);
        Runtime.Version version = Optional.ofNullable(optionSet.valueOf(releaseOpt)).orElse(Runtime.version());

        JNativeScanTask.Action action = JNativeScanTask.Action.DUMP_ALL;
        if (optionSet.has(printNativeAccessOpt)) {
            action = JNativeScanTask.Action.PRINT;
        }

        new JNativeScanTask(out, err, classPathJars, modulePaths, rootModules, version, action).run();
    }

    private static String[] expandArgFiles(String[] args) throws JNativeScanFatalError {
        try {
            return CommandLine.parse(args);
        } catch (IOException e) { // file not found
            throw new JNativeScanFatalError(e.getMessage(), e);
        }
    }

    public static void main(String[] args) {
        System.exit(new Main.Provider().run(System.out, System.err, args));
    }

    public static class Provider implements ToolProvider {

        @Override
        public String name() {
            return "jnativescan";
        }

        @Override
        public int run(PrintWriter out, PrintWriter err, String... args) {
            return new Main(out, err).run(args);
        }
    }

    // where
    private static final ValueConverter<Path> PARSE_PATH = new ValueConverter<>() {
        @Override
        public Path convert(String value) {
            return Path.of(value);
        }

        @Override
        public Class<? extends Path> valueType() {
            return Path.class;
        }

        @Override
        public String valuePattern() {
            return "Path";
        }
    };

    private static final ValueConverter<Runtime.Version> PARSE_VERSION = new ValueConverter<>() {
        @Override
        public Runtime.Version convert(String value) {
            try {
                return Runtime.Version.parse(value);
            } catch (IllegalArgumentException e) {
                throw new JNativeScanFatalError("Invalid release: " + value + ": " + e.getMessage());
            }
        }

        @Override
        public Class<? extends Runtime.Version> valueType() {
            return Runtime.Version.class;
        }

        @Override
        public String valuePattern() {
            return "Version";
        }
    };
}
