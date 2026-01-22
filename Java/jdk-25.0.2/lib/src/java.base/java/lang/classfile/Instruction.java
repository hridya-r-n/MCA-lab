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
import java.lang.classfile.instruction.*;

import jdk.internal.classfile.impl.AbstractInstruction;

/**
 * Models an executable instruction in the {@code code} array of the {@link
 * CodeAttribute Code} attribute of a method.  The order of instructions in
 * a {@link CodeModel} is significant.
 * <p>
 * The {@link #opcode() opcode} identifies the operation of an instruction.
 * Each {@linkplain Opcode#kind() kind} of opcode has its own modeling interface
 * for instructions.
 *
 * @see Opcode
 * @jvms 6.5 Instructions
 * @sealedGraph
 * @since 24
 */
public sealed interface Instruction extends CodeElement
        permits ArrayLoadInstruction, ArrayStoreInstruction, BranchInstruction,
                ConstantInstruction, ConvertInstruction, DiscontinuedInstruction,
                FieldInstruction, InvokeDynamicInstruction, InvokeInstruction,
                LoadInstruction, StoreInstruction, IncrementInstruction,
                LookupSwitchInstruction, MonitorInstruction, NewMultiArrayInstruction,
                NewObjectInstruction, NewPrimitiveArrayInstruction, NewReferenceArrayInstruction,
                NopInstruction, OperatorInstruction, ReturnInstruction,
                StackInstruction, TableSwitchInstruction,
                ThrowInstruction, TypeCheckInstruction, AbstractInstruction {

    /**
     * {@return the operation of this instruction}
     */
    Opcode opcode();

    /**
     * {@return the size in bytes of this instruction}
     * This value is equal to {@link Opcode#sizeIfFixed()
     * opcode().sizeIfFixed()} if it is not {@code -1}.
     */
    int sizeInBytes();
}
