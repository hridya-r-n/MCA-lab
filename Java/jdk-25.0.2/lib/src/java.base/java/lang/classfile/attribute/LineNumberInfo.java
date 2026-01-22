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
package java.lang.classfile.attribute;

import java.lang.classfile.CodeBuilder;
import java.lang.classfile.instruction.LineNumber;

import jdk.internal.classfile.impl.UnboundAttribute;

/**
 * Models a single line number entry in the {@link LineNumberTableAttribute}.
 *
 * @see LineNumberTableAttribute#lineNumbers()
 * @see LineNumber
 * @since 24
 */
public sealed interface LineNumberInfo
        permits UnboundAttribute.UnboundLineNumberInfo {

    /**
     * {@return the index into the code array at which the code for this line
     * begins}
     */
    int startPc();

    /**
     * {@return the line number within the original source file}
     */
    int lineNumber();

    /**
     * {@return a line number description}
     *
     * @apiNote
     * The created entry cannot be written to a {@link CodeBuilder}.  Call
     * {@link CodeBuilder#lineNumber CodeBuilder::lineNumber} in the correct
     * order instead.
     *
     * @param startPc the starting index of the code array for this line
     * @param lineNumber the line number within the original source file
     */
    public static LineNumberInfo of(int startPc, int lineNumber) {
        return new UnboundAttribute.UnboundLineNumberInfo(startPc, lineNumber);
    }
}
