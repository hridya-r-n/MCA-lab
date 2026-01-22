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
package java.lang.classfile.constantpool;

import jdk.internal.classfile.impl.AbstractPoolEntry;

/**
 * Models a {@code CONSTANT_String_info} structure, or a string constant, in the
 * constant pool of a {@code class} file.
 * <p>
 * The use of a {@code StringEntry} is represented by a {@link String}.
 * Conversions are through {@link ConstantPoolBuilder#stringEntry(String)} and
 * {@link #stringValue()}.
 * <p>
 * A string entry is composite:
 * {@snippet lang=text :
 * // @link substring="StringEntry" target="ConstantPoolBuilder#stringEntry(Utf8Entry)" :
 * StringEntry(Utf8Entry utf8) // @link substring="utf8" target="#utf8()"
 * }
 *
 * @jvms 4.4.3 The {@code CONSTANT_String_info} Structure
 * @since 24
 */
public sealed interface StringEntry
        extends ConstantValueEntry
        permits AbstractPoolEntry.StringEntryImpl {
    /**
     * {@return the UTF constant pool entry describing the string contents}
     *
     * @see ConstantPoolBuilder#stringEntry(Utf8Entry)
     */
    Utf8Entry utf8();

    /**
     * {@return the string value for this entry}
     *
     * @apiNote
     * A {@code Utf8Entry} can be used directly as a {@link CharSequence} if
     * {@code String} functionalities are not strictly desired.  If only string
     * equivalence is desired, {@link #equalsString(String) equalsString} should
     * be used.  Reduction of string processing can significantly improve {@code
     * class} file reading performance.
     *
     * @see ConstantPoolBuilder#stringEntry(String)
     */
    String stringValue();

    /**
     * {@return whether this entry describes the same string as the provided string}
     *
     * @param value the string to compare to
     * @since 25
     */
    boolean equalsString(String value);
}
