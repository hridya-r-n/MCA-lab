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
import java.lang.classfile.TypeKind;

import jdk.internal.classfile.impl.AbstractInstruction;
import jdk.internal.classfile.impl.Util;

/**
 * Models an array load instruction in the {@code code} array of a {@code Code}
 * attribute.  Corresponding opcodes have a {@linkplain Opcode#kind() kind}
 * of {@link Opcode.Kind#ARRAY_LOAD}.  Delivered as a {@link CodeElement} when
 * traversing the elements of a {@link CodeModel}.
 * <p>
 * An array load instruction is composite:
 * {@snippet lang=text :
 * // @link substring="ArrayLoadInstruction" target="CodeBuilder#arrayLoad(TypeKind)" :
 * ArrayLoadInstruction(TypeKind typeKind) // @link substring="typeKind" target="#typeKind"
 * }
 * where {@code typeKind} is not {@link TypeKind#VOID void}, and {@link
 * TypeKind#BOOLEAN boolean} is converted to {@link TypeKind#BYTE byte}.
 *
 * @see Opcode.Kind#ARRAY_LOAD
 * @see CodeBuilder#arrayLoad CodeBuilder::arrayLoad
 * @since 24
 */
public sealed interface ArrayLoadInstruction extends Instruction
        permits AbstractInstruction.UnboundArrayLoadInstruction {
    /**
     * {@return the component type of the array}  The {@link TypeKind#BYTE byte}
     * type load instruction {@link Opcode#BALOAD baload} also operates on
     * {@link TypeKind#BOOLEAN boolean} arrays, so this never returns
     * {@code boolean}.
     */
    TypeKind typeKind();

    /**
     * {@return an array load instruction}
     *
     * @param op the opcode for the specific type of array load instruction,
     *           which must be of kind {@link Opcode.Kind#ARRAY_LOAD}
     * @throws IllegalArgumentException if the opcode kind is not
     *         {@link Opcode.Kind#ARRAY_LOAD}
     */
    static ArrayLoadInstruction of(Opcode op) {
        Util.checkKind(op, Opcode.Kind.ARRAY_LOAD);
        return new AbstractInstruction.UnboundArrayLoadInstruction(op);
    }
}
