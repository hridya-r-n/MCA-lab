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

import java.lang.classfile.Attribute;
import java.lang.classfile.ClassReader;
import java.lang.classfile.attribute.RecordComponentInfo;
import java.lang.classfile.constantpool.Utf8Entry;
import java.util.List;

public final class BoundRecordComponentInfo
        implements RecordComponentInfo {

    private final ClassReader reader;
    private final int startPos, attributesPos;
    private List<Attribute<?>> attributes;

    public BoundRecordComponentInfo(ClassReader reader, int startPos) {
        this.reader = reader;
        this.startPos = startPos;
        attributesPos = startPos + 4;
    }

    @Override
    public Utf8Entry name() {
        return reader.readEntry(startPos, Utf8Entry.class);
    }

    @Override
    public Utf8Entry descriptor() {
        return reader.readEntry(startPos + 2, Utf8Entry.class);
    }

    @Override
    public List<Attribute<?>> attributes() {
        if (attributes == null) {
            attributes = BoundAttribute.readAttributes(null, reader, attributesPos, reader.customAttributes());
        }
        return attributes;
    }
}
