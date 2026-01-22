/*
 * Copyright (c) 2022, 2024, Oracle and/or its affiliates. All rights reserved.
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
package java.lang.classfile.instruction;

import java.lang.classfile.*;

import jdk.internal.classfile.impl.AbstractInstruction;
import jdk.internal.classfile.impl.Util;

/**
 * Models a branching instruction (conditional or unconditional) in the {@code
 * code} array of a {@code Code} attribute.  Corresponding opcodes have a
 * {@linkplain Opcode#kind() kind} of {@link Opcode.Kind#BRANCH}.  Delivered as
 * a {@link CodeElement} when traversing the elements of a {@link CodeModel}.
 * <p>
 * A branch instruction is composite:
 * {@snippet lang=text :
 * // @link substring="BranchInstruction" target="#of":
 * BranchInstruction(
 *     Opcode opcode, // @link substring="opcode" target="#opcode()"
 *     Label target // @link substring="target" target="#target()"
 * )
 * }
 * <p>
 * Due to physical restrictions, some types of instructions cannot encode labels
 * too far away in the list of code elements.  In such cases, the {@link
 * ClassFile.ShortJumpsOption} controls how an invalid branch instruction model
 * is written by a {@link CodeBuilder}.
 *
 * @see Opcode.Kind#BRANCH
 * @see CodeBuilder#branch CodeBuilder::branch
 * @see ClassFile.ShortJumpsOption
 * @since 24
 */
public sealed interface BranchInstruction extends Instruction
        permits AbstractInstruction.BoundBranchInstruction,
                AbstractInstruction.UnboundBranchInstruction {
    /**
     * {@return the branch target of this instruction}
     */
    Label target();

    /**
     * {@return a branch instruction}
     *
     * @param op the opcode for the specific type of branch instruction,
     *           which must be of kind {@link Opcode.Kind#BRANCH}
     * @param target the target of the branch
     * @throws IllegalArgumentException if the opcode kind is not
     *         {@link Opcode.Kind#BRANCH}
     */
    static BranchInstruction of(Opcode op, Label target) {
        Util.checkKind(op, Opcode.Kind.BRANCH);
        return new AbstractInstruction.UnboundBranchInstruction(op, target);
    }
}
