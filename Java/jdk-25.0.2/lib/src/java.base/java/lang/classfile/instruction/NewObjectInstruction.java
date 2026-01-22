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
 * Models a {@link Opcode#NEW new} instruction in the {@code code} array of a {@code Code}
 * attribute.  Delivered as a {@link CodeElement} when traversing the elements
 * of a {@link CodeModel}.
 * <p>
 * A new object instruction is composite:
 * {@snippet lang=text :
 * // @link substring="NewObjectInstruction" target="#of" :
 * NewObjectInstruction(ClassEntry className) // @link substring="className" target="#className"
 * }
 * where the {@code className} is a non-abstract class.
 *
 * @see Opcode.Kind#NEW_OBJECT
 * @see CodeBuilder#new_ CodeBuilder::new_
 * @jvms 6.5.new <em>new</em>
 * @since 24
 */
public sealed interface NewObjectInstruction extends Instruction
        permits AbstractInstruction.BoundNewObjectInstruction, AbstractInstruction.UnboundNewObjectInstruction {

    /**
     * {@return the type of object to create}
     */
    ClassEntry className();

    /**
     * {@return a new object instruction}
     *
     * @param className the type of object to create
     */
    static NewObjectInstruction of(ClassEntry className) {
        return new AbstractInstruction.UnboundNewObjectInstruction(className);
    }
}
