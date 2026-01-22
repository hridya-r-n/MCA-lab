/*
 * Copyright (c) 2021, 2025, Oracle and/or its affiliates. All rights reserved.
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
package jdk.jfr.internal.jfc.model;

import jdk.jfr.internal.tracing.Filter;

// Corresponds to <text>
final class XmlText extends XmlInput {

    @Override
    public String getOptionSyntax() {
        StringBuilder sb = new StringBuilder();
        sb.append(getName());
        sb.append("=<");
        sb.append(getContentType().orElse("text"));
        sb.append(">");
        sb.append("  (");
        String content = getContentOrEmptyQuote();
        if (isTimespan()) {
            // "20 ms" becomes "20ms"
            content = content.replaceAll("\\s", "");
        }
        sb.append(content);
        sb.append(")");
        return sb.toString();
    }

    @Override
    public void configure(String value) {
        if (isTimespan()) {
            value = Utilities.parseTimespan(value);
        }
        setContent(value);
        notifyListeners();
    }

    @Override
    public void configure(UserInterface ui) throws AbortException {
        ui.println();
        ui.println(getLabel() + ": " + getContentOrEmptyQuote() + "  (default)");
        while (!readInput(ui)) {
            ;
        }
    }

    @Override
    protected Result evaluate() {
        return Result.of(getContent());
    }

    private boolean readInput(UserInterface ui) throws AbortException {
        String line = ui.readLine();
        if (line.isBlank()) {
            ui.println("Using default: " + getContentOrEmptyQuote());
            return true;
        }
        if (isMethodFilter()) {
            if (!Filter.isValid(line)) {
                ui.println("""
                Not a valid method filter. A filter can be an annotation \
                (@jakarta.ws.rs.GET), a full qualified class name (com.example.Foo), \
                a fully qualified method reference (java.lang.HashMap::resize) or a \
                class initializer (::<clinit>). Use <init> for constructors. \
                Separate multiple filters with semicolon.\
                """);
                return false;
            }
        }
        if (isTimespan()) {
            try {
                line = Utilities.parseTimespan(line);
            } catch (IllegalArgumentException iae) {
                ui.println(iae.getMessage());
                return false;
            }
        }
        ui.println("Using: " + line);
        configure(line);
        return true;
    }

    private boolean isTimespan() {
        return getContentType().orElse("text").equals("timespan");
    }

    private boolean isMethodFilter() {
        return getContentType().orElse("text").equals("method-filter");
    }
}
