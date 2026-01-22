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
import java.lang.classfile.AttributedElement;
import java.lang.classfile.ClassFileElement;
import java.lang.classfile.CompoundElement;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;

public abstract sealed class AbstractUnboundModel<E extends ClassFileElement>
        extends AbstractElement
        implements CompoundElement<E>, AttributedElement
        permits BufferedCodeBuilder.Model, BufferedFieldBuilder.Model, BufferedMethodBuilder.Model {
    final List<E> elements;
    private List<Attribute<?>> attributes;

    public AbstractUnboundModel(List<E> elements) {
        this.elements = Collections.unmodifiableList(elements);
    }

    @Override
    public void forEach(Consumer<? super E> consumer) {
        elements.forEach(consumer);
    }

    @Override
    public Stream<E> elementStream() {
        return elements.stream();
    }

    @Override
    public List<E> elementList() {
        return elements;
    }

    @Override
    public List<Attribute<?>> attributes() {
        if (attributes == null)
            attributes = elements.stream()
                                 .<Attribute<?>>mapMulti((e, sink) -> {
                                     if (e instanceof Attribute<?> attr) {
                                         sink.accept(attr);
                                     }
                                 })
                                 .toList();
        return attributes;
    }
}
