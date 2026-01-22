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
package jdk.internal.classfile.impl;

import java.lang.classfile.CodeBuilder;
import java.lang.classfile.CodeElement;
import java.lang.classfile.Label;
import jdk.internal.classfile.components.CodeRelabeler;
import java.lang.classfile.instruction.*;
import java.util.function.BiFunction;

public record CodeRelabelerImpl(BiFunction<Label, CodeBuilder, Label> mapFunction) implements CodeRelabeler {

    public Label relabel(Label label, CodeBuilder cob) {
        return mapFunction.apply(label, cob);
    }

    @Override
    public void accept(CodeBuilder cob, CodeElement coe) {
        switch (coe) {
            case BranchInstruction bi ->
                cob.branch(
                        bi.opcode(),
                        relabel(bi.target(), cob));
            case LookupSwitchInstruction lsi ->
                cob.lookupswitch(
                        relabel(lsi.defaultTarget(), cob),
                        lsi.cases().stream().map(c ->
                                SwitchCase.of(
                                        c.caseValue(),
                                        relabel(c.target(), cob))).toList());
            case TableSwitchInstruction tsi ->
                cob.tableswitch(
                        tsi.lowValue(),
                        tsi.highValue(),
                        relabel(tsi.defaultTarget(), cob),
                        tsi.cases().stream().map(c ->
                                SwitchCase.of(
                                        c.caseValue(),
                                        relabel(c.target(), cob))).toList());
            case LabelTarget lt ->
                cob.labelBinding(
                        relabel(lt.label(), cob));
            case ExceptionCatch ec ->
                cob.exceptionCatch(
                        relabel(ec.tryStart(), cob),
                        relabel(ec.tryEnd(), cob),
                        relabel(ec.handler(), cob),
                        ec.catchType());
            case LocalVariable lv ->
                cob.localVariable(
                        lv.slot(),
                        lv.name().stringValue(),
                        lv.typeSymbol(),
                        relabel(lv.startScope(), cob),
                        relabel(lv.endScope(), cob));
            case LocalVariableType lvt ->
                cob.localVariableType(
                        lvt.slot(),
                        lvt.name().stringValue(),
                        lvt.signatureSymbol(),
                        relabel(lvt.startScope(), cob),
                        relabel(lvt.endScope(), cob));
            case CharacterRange chr ->
                cob.characterRange(
                        relabel(chr.startScope(), cob),
                        relabel(chr.endScope(), cob),
                        chr.characterRangeStart(),
                        chr.characterRangeEnd(),
                        chr.flags());
            default ->
                cob.with(coe);
        }
    }

}
