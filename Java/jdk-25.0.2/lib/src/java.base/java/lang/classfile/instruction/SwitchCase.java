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

import java.lang.classfile.Label;

import jdk.internal.classfile.impl.AbstractInstruction;

/**
 * Models a single case in a {@link LookupSwitchInstruction lookupswitch} or
 * {@link TableSwitchInstruction tableswitch} instruction.
 * <p>
 * A switch case is composite:
 * {@snippet lang=text :
 * // @link substring="SwitchCase" target="#of" :
 * SwitchCase(
 *     int caseValue, // @link substring="caseValue" target="#caseValue"
 *     Label target // @link substring="target" target="#target"
 * )
 * }
 *
 * @see LookupSwitchInstruction
 * @see TableSwitchInstruction
 * @since 24
 */
public sealed interface SwitchCase
        permits AbstractInstruction.SwitchCaseImpl {

    /** {@return the integer value corresponding to this case} */
    int caseValue();

    /** {@return the branch target corresponding to this case} */
    Label target();

    /**
     * {@return a new switch case}
     *
     * @param caseValue the integer value for the case
     * @param target the branch target for the case
     */
    static SwitchCase of(int caseValue, Label target) {
        return new AbstractInstruction.SwitchCaseImpl(caseValue, target);
    }
}
