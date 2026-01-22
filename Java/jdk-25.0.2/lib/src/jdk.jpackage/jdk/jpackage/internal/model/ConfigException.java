/*
 * Copyright (c) 2012, 2025, Oracle and/or its affiliates. All rights reserved.
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

package jdk.jpackage.internal.model;

import jdk.jpackage.internal.util.LocalizedExceptionBuilder;
import jdk.jpackage.internal.util.StringBundle;

/**
 * Signals that error has occurred at configuration phase.
 * <p>
 * It contains an error message and an optional advice message on how to correct the error.
 * <p>
 * The preferred way to construct instances of this class is to use
 * {@link #build(StringBundle)}, or {@link #build(StringBundle, Throwable)},
 * or {@link #build(StringBundle, String, Object...)} methods.
 *
 * {@snippet :
 * StringBundle i18n = getStringBundle(); // Some way to obtain a string bundle with localized messages
 *
 * throw ConfigException.build(i18n)
 *         .message("error.no.name")
 *         .advice("error.no.name.advice")
 *         .create();
 * }
 */
public class ConfigException extends Exception {
    private static final long serialVersionUID = 1L;
    private final String advice;

    public ConfigException(String msg, String advice) {
        super(msg);
        this.advice = advice;
    }

    public ConfigException(String msg, String advice, Throwable cause) {
        super(msg, cause);
        this.advice = advice;
    }

    public ConfigException(Throwable cause) {
        super(cause);
        this.advice = null;
    }

    public String getAdvice() {
        return advice;
    }

    public static Builder build(StringBundle i18n) {
        return new Builder(i18n);
    }

    public static Builder build(StringBundle i18n, String msgId, Object ... args) {
        return build(i18n).message(msgId, args);
    }

    public static Builder build(StringBundle i18n, Throwable t) {
        return build(i18n).causeAndMessage(t);
    }

    /**
     * Builds {@link ConfigException} instances.
     */
    public static class Builder extends LocalizedExceptionBuilder<Builder> {

        public Builder advice(String adviceId, Object ... args) {
            advice = formatString(adviceId, args);
            return this;
        }

        private Builder(StringBundle i18n) {
            super(i18n);
        }

        public ConfigException create() {
            return create(this::create);
        }

        private ConfigException create(String msg, Throwable cause) {
            return new ConfigException(msg, advice, cause);
        }

        private String advice;
    }

    /**
     * Throws the cause of the given {@link RuntimeException} exception
     * as {@link ConfigException} if the cause is of this type or re-throws the given
     * {@link RuntimeException} exception as-is otherwise.
     * <p>
     * Never return a value. It always throws some exception object.
     *
     * @param ex exception to re-throw
     * @return doesn't return value
     * @throws ConfigException
     */
    public static RuntimeException rethrowConfigException(RuntimeException ex) throws ConfigException {
        if (ex.getCause() instanceof ConfigException configEx) {
            throw configEx;
        } else {
            throw ex;
        }
    }
}
