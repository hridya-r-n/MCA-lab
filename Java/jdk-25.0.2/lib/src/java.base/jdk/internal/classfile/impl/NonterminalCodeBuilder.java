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
package jdk.internal.classfile.impl;

import java.lang.classfile.CodeBuilder;
import java.lang.classfile.Label;
import java.lang.classfile.constantpool.ConstantPoolBuilder;

public abstract sealed class NonterminalCodeBuilder implements CodeBuilder
    permits ChainedCodeBuilder, BlockCodeBuilderImpl {
    protected final TerminalCodeBuilder terminal;
    protected final CodeBuilder parent;

    public NonterminalCodeBuilder(CodeBuilder parent) {
        this.parent = parent;
        this.terminal = findTerminal(parent);
    }

    static TerminalCodeBuilder findTerminal(CodeBuilder cob) {
        return cob instanceof NonterminalCodeBuilder ncb ?
                ncb.terminal : (TerminalCodeBuilder) cob;
    }

    @Override
    public int receiverSlot() {
        return terminal.receiverSlot();
    }

    @Override
    public int parameterSlot(int paramNo) {
        return terminal.parameterSlot(paramNo);
    }

    @Override
    public ConstantPoolBuilder constantPool() {
        return terminal.constantPool();
    }

    @Override
    public Label newLabel() {
        return terminal.newLabel();
    }
}
