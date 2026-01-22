/*
 * Copyright (c) 2019, 2023, Oracle and/or its affiliates. All rights reserved.
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

package javax.lang.model.element;

import javax.lang.model.type.TypeMirror;
import javax.lang.model.type.TypeKind;

/**
 * Represents a record component.
 *
 * @jls 8.10.1 Record Components
 * @since 16
 */
public interface RecordComponentElement extends Element {
    /**
     * {@return the type of this record component}
     *
     * Note that the types of record components range over {@linkplain
     * TypeKind many kinds} of types, including primitive types,
     * declared types, and array types.
     *
     * @see TypeKind
     */
    @Override
    TypeMirror asType();

    /**
     * {@return the enclosing element of this record component}
     *
     * The enclosing element of a record component is the record class
     * declaring the record component.
     */
    @Override
    Element getEnclosingElement();

    /**
     * {@return the simple name of this record component}
     *
     * <p>The name of each record component must be distinct from the
     * names of all other record components of the same record.
     *
     * @jls 6.2 Names and Identifiers
     */
    @Override
    Name getSimpleName();

    /**
     * Returns the executable element for the accessor associated with the
     * given record component.
     *
     * @return the record component accessor.
     */
    ExecutableElement getAccessor();
}
