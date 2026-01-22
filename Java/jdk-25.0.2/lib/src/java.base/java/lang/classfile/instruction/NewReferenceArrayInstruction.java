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
import java.lang.classfile.constantpool.ClassEntry;

import jdk.internal.classfile.impl.AbstractInstruction;

/**
 * Models a {@link Opcode#ANEWARRAY anewarray} instruction in the {@code code}
 * array of a {@code Code} attribute.  Delivered as a {@link CodeElement}
 * when traversing the elements of a {@link CodeModel}.
 * <p>
 * A new reference array instruction is composite:
 * {@snippet lang=text :
 * // @link substring="NewReferenceArrayInstruction" target="#of" :
 * NewReferenceArrayInstruction(ClassEntry componentType) // @link substring="componentType" target="#componentType"
 * }
 *
 * @see Opcode.Kind#NEW_REF_ARRAY
 * @see CodeBuilder#newarray CodeBuilder::anewarray
 * @jvms 6.5.anewarray <em>anewarray</em>
 * @since 24
 */
public sealed interface NewReferenceArrayInstruction extends Instruction
        permits AbstractInstruction.BoundNewReferenceArrayInstruction, AbstractInstruction.UnboundNewReferenceArrayInstruction {
    /**
     * {@return the component type of the array}
     */
    ClassEntry componentType();

    /**
     * {@return a new reference array instruction}
     *
     * @param componentType the component type of the array
     */
    static NewReferenceArrayInstruction of(ClassEntry componentType) {
        return new AbstractInstruction.UnboundNewReferenceArrayInstruction(componentType);
    }
}
