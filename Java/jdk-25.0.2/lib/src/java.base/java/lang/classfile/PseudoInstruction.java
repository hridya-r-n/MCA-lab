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
package java.lang.classfile;

import java.lang.classfile.attribute.CodeAttribute;
import java.lang.classfile.instruction.CharacterRange;
import java.lang.classfile.instruction.ExceptionCatch;
import java.lang.classfile.instruction.LabelTarget;
import java.lang.classfile.instruction.LineNumber;
import java.lang.classfile.instruction.LocalVariable;
import java.lang.classfile.instruction.LocalVariableType;

import jdk.internal.classfile.impl.AbstractPseudoInstruction;

/**
 * Models metadata about a {@link CodeModel}, derived from the {@link
 * CodeAttribute Code} attribute itself or its attributes.
 * <p>
 * Order is significant for some pseudo-instructions relative to {@link
 * Instruction}s, such as {@link LabelTarget} or {@link LineNumber}.  Some
 * pseudo-instructions can be omitted in reading and writing according to
 * certain {@link ClassFile.Option}s.  These are specified in the corresponding
 * modeling interfaces.
 *
 * @sealedGraph
 * @since 24
 */
public sealed interface PseudoInstruction
        extends CodeElement
        permits CharacterRange, ExceptionCatch, LabelTarget, LineNumber, LocalVariable, LocalVariableType, AbstractPseudoInstruction {
}
