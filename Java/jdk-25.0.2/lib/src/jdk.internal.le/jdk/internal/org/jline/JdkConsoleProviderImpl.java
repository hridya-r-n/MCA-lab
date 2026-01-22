/*
 * Copyright (c) 2022, 2025, Oracle and/or its affiliates. All rights reserved.
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

package jdk.internal.org.jline;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.Locale;

import jdk.internal.io.JdkConsole;
import jdk.internal.io.JdkConsoleProvider;
import jdk.internal.org.jline.reader.EndOfFileException;
import jdk.internal.org.jline.reader.LineReader;
import jdk.internal.org.jline.reader.LineReaderBuilder;
import jdk.internal.org.jline.terminal.Terminal;
import jdk.internal.org.jline.terminal.TerminalBuilder;
import jdk.internal.org.jline.terminal.TerminalBuilder.SystemOutput;

/**
 * JdkConsole/Provider implementations for jline
 */
public class JdkConsoleProviderImpl implements JdkConsoleProvider {

    /**
     * {@inheritDoc}
     */
    @Override
    public JdkConsole console(boolean isTTY, Charset inCharset, Charset outCharset) {
        return new LazyDelegatingJdkConsoleImpl(inCharset, outCharset);
    }

    private static class LazyDelegatingJdkConsoleImpl implements JdkConsole {
        private final Charset outCharset;
        private volatile boolean jlineInitialized;
        private volatile JdkConsole delegate;

        public LazyDelegatingJdkConsoleImpl(Charset inCharset, Charset outCharset) {
            this.outCharset = outCharset;
            this.delegate = new jdk.internal.io.JdkConsoleImpl(inCharset, outCharset);
        }

        @Override
        public PrintWriter writer() {
            return getDelegate(true).writer();
        }

        @Override
        public Reader reader() {
            return getDelegate(true).reader();
        }

        @Override
        public JdkConsole println(Object obj) {
            JdkConsole delegate = getDelegate(false);

            delegate.println(obj);
            flushOldDelegateIfNeeded(delegate);

            return this;
        }

        @Override
        public JdkConsole print(Object obj) {
            JdkConsole delegate = getDelegate(false);

            delegate.print(obj);
            flushOldDelegateIfNeeded(delegate);

            return this;
        }

        @Override
        public JdkConsole format(Locale locale, String format, Object... args) {
            JdkConsole delegate = getDelegate(false);

            delegate.format(locale, format, args);
            flushOldDelegateIfNeeded(delegate);

            return this;
        }

        @Override
        public String readLine(Locale locale, String format, Object... args) {
            return getDelegate(true).readLine(locale, format, args);
        }

        @Override
        public String readLine() {
            return getDelegate(true).readLine();
        }

        @Override
        public char[] readPassword(Locale locale, String format, Object... args) {
            return getDelegate(true).readPassword(locale, format, args);
        }

        @Override
        public char[] readPassword() {
            return getDelegate(true).readPassword();
        }

        @Override
        public void flush() {
            getDelegate(false).flush();
        }

        @Override
        public Charset charset() {
            return outCharset;
        }

        private void flushOldDelegateIfNeeded(JdkConsole oldDelegate) {
            if (oldDelegate != getDelegate(false)) {
                //if the delegate changed in the mean time, make sure the original
                //delegate is flushed:
                oldDelegate.flush();
            }
        }

        private JdkConsole getDelegate(boolean needsJLine) {
            if (!needsJLine || jlineInitialized) {
                return delegate;
            }

            return initializeJLineDelegate();
        }

        private synchronized JdkConsole initializeJLineDelegate() {
            JdkConsole newDelegate = delegate;

            if (jlineInitialized) {
                return newDelegate;
            }

            try {
                Terminal terminal = TerminalBuilder.builder().encoding(outCharset)
                                                   .exec(false)
                                                   .nativeSignals(false)
                                                   .systemOutput(SystemOutput.SysOut)
                                                   .build();
                newDelegate = new JdkConsoleImpl(terminal);
            } catch (IllegalStateException ise) {
                //cannot create a non-dumb, non-exec terminal,
                //use the standard Console:
            } catch (IOException ioe) {
                //something went wrong, keep the existing delegate
            }

            delegate = newDelegate;
            jlineInitialized = true;

            return newDelegate;
        }
    }

    /**
     * An implementation of JdkConsole, which act as a delegate for the
     * public Console class.
     */
    private static class JdkConsoleImpl implements JdkConsole {
        private final Terminal terminal;
        private volatile LineReader jline;

        @Override
        public PrintWriter writer() {
            return terminal.writer();
        }

        @Override
        public Reader reader() {
            return terminal.reader();
        }

        @Override
        public JdkConsole println(Object obj) {
            writer().println(obj);
            writer().flush();
            return this;
        }

        @Override
        public JdkConsole print(Object obj) {
            writer().print(obj);
            writer().flush();
            return this;
        }

        @Override
        public JdkConsole format(Locale locale, String format, Object ... args) {
            writer().format(locale, format, args).flush();
            return this;
        }

        @Override
        public String readLine(Locale locale, String format, Object ... args) {
            try {
                initJLineIfNeeded();
                return jline.readLine(String.format(locale, format, args).replace("%", "%%"));
            } catch (EndOfFileException eofe) {
                return null;
            }
        }

        @Override
        public String readLine() {
            try {
                initJLineIfNeeded();
                return jline.readLine();
            } catch (EndOfFileException eofe) {
                return null;
            }
        }

        @Override
        public char[] readPassword(Locale locale, String format, Object ... args) {
            try {
                initJLineIfNeeded();
                return jline.readLine(String.format(locale, format, args).replace("%", "%%"), '\0')
                            .toCharArray();
            } catch (EndOfFileException eofe) {
                return null;
            } finally {
                jline.zeroOut();
            }
        }

        @Override
        public char[] readPassword() {
            return readPassword(Locale.getDefault(Locale.Category.FORMAT), "");
        }

        @Override
        public void flush() {
            terminal.flush();
        }

        @Override
        public Charset charset() {
            return terminal.encoding();
        }

        public JdkConsoleImpl(Terminal terminal) {
            this.terminal = terminal;
        }

        private void initJLineIfNeeded() {
            LineReader jline = this.jline;
            if (jline == null) {
                synchronized (this) {
                    jline = this.jline;
                    if (jline == null) {
                        jline = LineReaderBuilder.builder()
                                                 .option(LineReader.Option.DISABLE_EVENT_EXPANSION, true)
                                                 .terminal(terminal)
                                                 .build();
                        this.jline = jline;
                    }
                }
            }
        }
    }
}
