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
package java.lang.classfile;

import java.lang.classfile.attribute.RuntimeInvisibleAnnotationsAttribute;
import java.lang.classfile.attribute.RuntimeInvisibleParameterAnnotationsAttribute;
import java.lang.classfile.attribute.RuntimeVisibleAnnotationsAttribute;
import java.lang.classfile.attribute.RuntimeVisibleParameterAnnotationsAttribute;
import java.lang.classfile.constantpool.Utf8Entry;
import java.lang.constant.ClassDesc;
import java.util.List;

import jdk.internal.classfile.impl.AnnotationImpl;
import jdk.internal.classfile.impl.TemporaryConstantPool;
import jdk.internal.classfile.impl.Util;

/**
 * Models an {@code annotation} structure (JVMS {@jvms 4.7.16}) or part of a {@code
 * type_annotation} structure (JVMS {@jvms 4.7.20}). This model indicates the
 * interface of the annotation and a set of element-value pairs.
 * <p>
 * This model can reconstruct an annotation, given the location of the modeled
 * structure in the {@code class} file and the definition of the annotation
 * interface.
 * <p>
 * Two {@code Annotation} objects should be compared using the {@link
 * Object#equals(Object) equals} method.
 *
 * @apiNote
 * For Java programs, the location of the modeled structure indicates the source code
 * element or type (JLS {@jls 9.7.4}) on which the reconstructed annotation appears,
 * and the annotation interface definition determines whether the reconstructed annotation has
 * elements with default values (JLS {@jls 9.6.2}), and whether the reconstructed annotation
 * is a container annotation for multiple annotations (JLS {@jls 9.7.5}).
 *
 * @see java.lang.annotation.Annotation
 * @see java.lang.reflect.AnnotatedElement Annotations in core reflection
 * @see TypeAnnotation
 * @see RuntimeVisibleAnnotationsAttribute
 * @see RuntimeInvisibleAnnotationsAttribute
 * @see RuntimeVisibleParameterAnnotationsAttribute
 * @see RuntimeInvisibleParameterAnnotationsAttribute
 *
 * @since 24
 */
public sealed interface Annotation
        permits AnnotationImpl {

    /**
     * {@return the constant pool entry holding the {@linkplain Class#descriptorString
     * descriptor string} of the annotation interface}
     *
     * @see java.lang.annotation.Annotation#annotationType()
     */
    Utf8Entry className();

    /**
     * {@return the annotation interface, as a symbolic descriptor}
     *
     * @see java.lang.annotation.Annotation#annotationType()
     */
    default ClassDesc classSymbol() {
        return Util.fieldTypeSymbol(className());
    }

    /**
     * {@return the element-value pairs of the annotation}
     */
    List<AnnotationElement> elements();

    /**
     * {@return an annotation}
     * @param annotationClass the constant pool entry holding the descriptor string
     *                        of the annotation interface
     * @param elements the element-value pairs of the annotation
     */
    static Annotation of(Utf8Entry annotationClass,
                         List<AnnotationElement> elements) {
        return new AnnotationImpl(annotationClass, elements);
    }

    /**
     * {@return an annotation}
     * @param annotationClass the constant pool entry holding the descriptor string
     *                        of the annotation interface
     * @param elements the element-value pairs of the annotation
     */
    static Annotation of(Utf8Entry annotationClass,
                         AnnotationElement... elements) {
        return of(annotationClass, List.of(elements));
    }

    /**
     * {@return an annotation}
     * @param annotationClass the descriptor of the annotation interface
     * @param elements the element-value pairs of the annotation
     */
    static Annotation of(ClassDesc annotationClass,
                         List<AnnotationElement> elements) {
        return of(TemporaryConstantPool.INSTANCE.utf8Entry(annotationClass), elements);
    }

    /**
     * {@return an annotation}
     * @param annotationClass the descriptor of the annotation interface
     * @param elements the element-value pairs of the annotation
     */
    static Annotation of(ClassDesc annotationClass,
                         AnnotationElement... elements) {
        return of(TemporaryConstantPool.INSTANCE.utf8Entry(annotationClass), elements);
    }
}
