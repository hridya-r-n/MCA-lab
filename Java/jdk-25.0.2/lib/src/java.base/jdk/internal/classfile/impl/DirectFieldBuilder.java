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

package jdk.internal.classfile.impl;

import java.lang.classfile.CustomAttribute;
import java.lang.classfile.FieldBuilder;
import java.lang.classfile.FieldElement;
import java.lang.classfile.FieldModel;
import java.lang.classfile.constantpool.Utf8Entry;
import java.util.function.Consumer;

import static java.util.Objects.requireNonNull;

public final class DirectFieldBuilder
        extends AbstractDirectBuilder<FieldModel>
        implements TerminalFieldBuilder, Util.Writable {
    private final Utf8Entry name;
    private final Utf8Entry desc;
    private int flags;

    public DirectFieldBuilder(SplitConstantPool constantPool,
                              ClassFileImpl context,
                              Utf8Entry name,
                              Utf8Entry type,
                              int flags,
                              FieldModel original) {
        super(constantPool, context);
        setOriginal(original);
        this.name = requireNonNull(name);
        this.desc = requireNonNull(type);
        this.flags = flags;
    }

    @Override
    public FieldBuilder with(FieldElement element) {
        if (element instanceof AbstractElement ae) {
            ae.writeTo(this);
        } else {
            writeAttribute((CustomAttribute<?>) requireNonNull(element));
        }
        return this;
    }

    public DirectFieldBuilder run(Consumer<? super FieldBuilder> handler) {
        handler.accept(this);
        return this;
    }

    @Override
    public FieldBuilder withFlags(int flags) {
        setFlags(flags);
        return this;
    }

    void setFlags(int flags) {
        this.flags = flags;
    }

    @Override
    public void writeTo(BufWriterImpl buf) {
        buf.writeU2U2U2(flags, buf.cpIndex(name), buf.cpIndex(desc));
        attributes.writeTo(buf);
    }
}
