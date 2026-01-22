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

import java.lang.classfile.constantpool.Utf8Entry;
import java.lang.constant.ClassDesc;

import jdk.internal.classfile.impl.AnnotationImpl;
import jdk.internal.classfile.impl.TemporaryConstantPool;

/**
 * Models an element-value pair in the {@code element_value_pairs}
 * table in the {@code annotation} structure defined in JVMS
 * {@jvms 4.7.16} or the {@code type_annotation} structure defined
 * in JVMS {@jvms 4.7.20}.
 * <p>
 * Two {@code AnnotationElement} objects should be compared using the
 * {@link Object#equals(Object) equals} method.
 *
 * @see Annotation
 * @see java.lang.reflect.AnnotatedElement Annotations in core reflection
 * @jvms 4.7.16.1 The {@code element_value} structure
 * @since 24
 */
public sealed interface AnnotationElement
        permits AnnotationImpl.AnnotationElementImpl {

    /**
     * {@return the element name}
     *
     * @apiNote
     * In Java source code, by convention, the name of the sole element in a
     * single-element annotation interface is {@code value}. (JLS {@jls 9.6.1})
     * This is the case for single-element annotations (JLS {@jls 9.7.3}) and
     * container annotations for multiple annotations (JLS {@jls 9.6.3}).
     */
    Utf8Entry name();

    /**
     * {@return the element value}
     */
    AnnotationValue value();

    /**
     * {@return an element-value pair}
     * @param name the name of the key
     * @param value the associated value
     */
    static AnnotationElement of(Utf8Entry name,
                                AnnotationValue value) {
        return new AnnotationImpl.AnnotationElementImpl(name, value);
    }

    /**
     * {@return an element-value pair}
     * @param name the name of the key
     * @param value the associated value
     */
    static AnnotationElement of(String name,
                                AnnotationValue value) {
        return of(TemporaryConstantPool.INSTANCE.utf8Entry(name), value);
    }

    /**
     * {@return an element-value pair for a class-valued element}
     * @param name the name of the key
     * @param value the associated value
     * @see AnnotationValue#ofClass(ClassDesc) AnnotationValue::ofClass
     */
    static AnnotationElement ofClass(String name,
                                     ClassDesc value) {
        return of(name, AnnotationValue.ofClass(value));
    }

    /**
     * {@return an element-value pair for a string-valued element}
     * @param name the name of the key
     * @param value the associated value
     * @see AnnotationValue#ofString(String) AnnotationValue::ofString
     */
    static AnnotationElement ofString(String name,
                                      String value) {
        return of(name, AnnotationValue.ofString(value));
    }

    /**
     * {@return an element-value pair for a long-valued element}
     * @param name the name of the key
     * @param value the associated value
     * @see AnnotationValue#ofLong(long) AnnotationValue::ofLong
     */
    static AnnotationElement ofLong(String name,
                                    long value) {
        return of(name, AnnotationValue.ofLong(value));
    }

    /**
     * {@return an element-value pair for an int-valued element}
     * @param name the name of the key
     * @param value the associated value
     * @see AnnotationValue#ofInt(int) AnnotationValue::ofInt
     */
    static AnnotationElement ofInt(String name,
                                   int value) {
        return of(name, AnnotationValue.ofInt(value));
    }

    /**
     * {@return an element-value pair for a char-valued element}
     * @param name the name of the key
     * @param value the associated value
     * @see AnnotationValue#ofChar(char) AnnotationValue::ofChar
     */
    static AnnotationElement ofChar(String name,
                                    char value) {
        return of(name, AnnotationValue.ofChar(value));
    }

    /**
     * {@return an element-value pair for a short-valued element}
     * @param name the name of the key
     * @param value the associated value
     * @see AnnotationValue#ofShort(short) AnnotationValue::ofShort
     */
    static AnnotationElement ofShort(String name,
                                     short value) {
        return of(name, AnnotationValue.ofShort(value));
    }

    /**
     * {@return an element-value pair for a byte-valued element}
     * @param name the name of the key
     * @param value the associated value
     * @see AnnotationValue#ofByte(byte) AnnotationValue::ofByte
     */
    static AnnotationElement ofByte(String name,
                                    byte value) {
        return of(name, AnnotationValue.ofByte(value));
    }

    /**
     * {@return an element-value pair for a boolean-valued element}
     * @param name the name of the key
     * @param value the associated value
     * @see AnnotationValue#ofBoolean(boolean) AnnotationValue::ofBoolean
     */
    static AnnotationElement ofBoolean(String name,
                                       boolean value) {
        return of(name, AnnotationValue.ofBoolean(value));
    }

    /**
     * {@return an element-value pair for a double-valued element}
     * @param name the name of the key
     * @param value the associated value
     * @see AnnotationValue#ofDouble(double) AnnotationValue::ofDouble
     */
    static AnnotationElement ofDouble(String name,
                                      double value) {
        return of(name, AnnotationValue.ofDouble(value));
    }

    /**
     * {@return an element-value pair for a float-valued element}
     * @param name the name of the key
     * @param value the associated value
     * @see AnnotationValue#ofFloat(float) AnnotationValue::ofFloat
     */
    static AnnotationElement ofFloat(String name,
                                     float value) {
        return of(name, AnnotationValue.ofFloat(value));
    }

    /**
     * {@return an element-value pair for an annotation-valued element}
     * @param name the name of the key
     * @param value the associated value
     * @see AnnotationValue#ofAnnotation AnnotationValue::ofAnnotation
     */
    static AnnotationElement ofAnnotation(String name,
                                          Annotation value) {
        return of(name, AnnotationValue.ofAnnotation(value));
    }

    /**
     * {@return an element-value pair for an array-valued element}
     * @param name the name of the key
     * @param values the associated values
     * @see AnnotationValue#ofArray(AnnotationValue...) AnnotationValue::ofArray
     */
    static AnnotationElement ofArray(String name,
                                     AnnotationValue... values) {
        return of(name, AnnotationValue.ofArray(values));
    }
}

