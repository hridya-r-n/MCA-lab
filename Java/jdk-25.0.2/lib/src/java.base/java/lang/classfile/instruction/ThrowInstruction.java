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

import java.lang.classfile.CodeBuilder;
import java.lang.classfile.CodeElement;
import java.lang.classfile.CodeModel;
import java.lang.classfile.Instruction;
import java.lang.classfile.Opcode;

import jdk.internal.classfile.impl.AbstractInstruction;

/**
 * Models an {@link Opcode#ATHROW athrow} instruction in the {@code code} array of a
 * {@code Code} attribute.  Delivered as a {@link CodeElement} when traversing
 * the elements of a {@link CodeModel}.
 * <p>
 * A throw instruction has no visible state.
 *
 * @see Opcode.Kind#THROW_EXCEPTION
 * @see CodeBuilder#athrow CodeBuiler::athrow
 * @since 24
 */
public sealed interface ThrowInstruction extends Instruction
        permits AbstractInstruction.UnboundThrowInstruction {

    /**
     * {@return a throw instruction}
     */
    static ThrowInstruction of() {
        return new AbstractInstruction.UnboundThrowInstruction();
    }
}
