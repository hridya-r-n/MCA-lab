/*
 * Copyright (c) 2023, 2024, Oracle and/or its affiliates. All rights reserved.
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

/**
 * <h2>Provides interfaces describing code instructions for the {@link java.lang.classfile} library.</h2>
 *
 * The {@code java.lang.classfile.instruction} package contains interfaces describing code instructions.
 * Implementations of these interfaces are immutable.
 * <p>
 * Unless otherwise specified, passing {@code null} or an array or collection containing a {@code null} element as an
 * argument to a constructor or method of any Class-File API class or interface will cause a {@link NullPointerException}
 * to be thrown.
 *
 * <h2 id="reading">Reading of instructions</h2>
 * Instructions and pseudo-instructions are usually accessed from a {@link CodeModel}, such as {@link CodeModel#forEach
 * CodeModel::forEach}, and categorized by pattern-matching.
 * <p>
 * When read from {@code class} files, instructions are lazily inflated; the contents of these instructions, besides the
 * bare structure, are not evaluated to speed up parsing.  Instructions to users interest, such as those filtered by the
 * pattern matching, have their contents read on demand, to avoid unnecessary reading of unrelated instructions in a code
 * array.
 * <p>
 * Due to the lazy nature of {@code class} file parsing, {@link IllegalArgumentException} indicating malformed
 * {@code class} file data can be thrown at any method invocation.  For example, an instruction object for a {@link
 * TypeCheckInstruction} may be obtained from a {@code CodeModel}, but the subsequent invocation of {@link
 * TypeCheckInstruction#type() .type()} may fail with {@code IllegalArgumentException} because the instruction refers
 * to a bad constant pool index.
 *
 * <h2 id="writing">Writing of instructions</h2>
 * Writing of instructions happen on {@link CodeBuilder}.  The most basic way to write instructions is to pass an
 * instruction object to {@link CodeBuilder#with CodeBuilder::with}, which supports all valid instructions.
 * Yet, {@code CodeBuilder} provides a lot of {@linkplain CodeBuilder##instruction-factories convenience factory methods}
 * for easy creation of instructions, named by their mnemonic.  These accessors are more concise, and often more
 * efficient at run-time than passing instruction objects.
 * <p>
 * Due to restrictions in the {@code class} file format, some instructions may not be representable in a {@code CodeBuilder}.
 * In some scenarios, such as for {@link BranchInstruction}, Class-File API options control if alternatives can be used
 * in code generation instead.  Otherwise, they can be configured to fail-fast to ensure the parity of {@code CodeBuilder}
 * commands with the generated {@code code} array data.
 *
 * @jvms 6.5 Instructions
 * @since 24
 */
package java.lang.classfile.instruction;

import java.lang.classfile.CodeBuilder;
import java.lang.classfile.CodeModel;

