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
import java.lang.classfile.AttributeMapper;
import java.util.Arrays;

public class AttributeHolder {
    private static final Attribute<?>[] EMPTY_ATTRIBUTE_ARRAY = {};
    private int attributesCount = 0;
    private Attribute<?>[] attributes = EMPTY_ATTRIBUTE_ARRAY;

    public <A extends Attribute<A>> void withAttribute(Attribute<?> a) {
        if (a == null)
            return;

        @SuppressWarnings("unchecked")
        AttributeMapper<A> am = (AttributeMapper<A>) a.attributeMapper();
        int attributesCount = this.attributesCount;
        var attributes = this.attributes;
        if (!am.allowMultiple()) {
            // remove if
            for (int i = attributesCount - 1; i >= 0; i--) {
                if (attributes[i].attributeMapper() == am) {
                    attributesCount--;
                    System.arraycopy(attributes, i + 1, attributes, i, attributesCount - i);
                }
            }
        }

        // add attribute
        if (attributesCount >= attributes.length) {
            int newCapacity = attributesCount + 4;
            this.attributes = attributes = Arrays.copyOf(attributes, newCapacity);
        }
        attributes[attributesCount] = a;
        this.attributesCount = attributesCount + 1;
    }

    public int size() {
        return attributesCount;
    }

    public void writeTo(BufWriterImpl buf) {
        int attributesCount = this.attributesCount;
        buf.writeU2(attributesCount);
        for (int i = 0; i < attributesCount; i++) {
            Util.writeAttribute(buf, attributes[i]);
        }
    }

    @SuppressWarnings("unchecked")
    <A extends Attribute<A>> A get(AttributeMapper<A> am) {
        for (int i = 0; i < attributesCount; i++) {
            Attribute<?> a = attributes[i];
            if (a.attributeMapper() == am)
                return (A) a;
        }
        return null;
    }

    boolean isPresent(AttributeMapper<?> am) {
        for (int i = 0; i < attributesCount; i++) {
            if (attributes[i].attributeMapper() == am)
                return true;
        }
        return false;
    }
}
