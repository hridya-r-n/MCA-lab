/*
 * Copyright (c) 2024, Oracle and/or its affiliates. All rights reserved.
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

package sun.security.util.math;

/**
 * An interface for the field of integers modulo a prime number. An
 * implementation of this interface can be used to get properties of the
 * field and to produce field elements of type ImmutableIntegerModuloP from
 * other objects and representations of field elements.
 */

public interface IntegerMontgomeryFieldModuloP extends IntegerFieldModuloP {
    ImmutableIntegerModuloP fromMontgomery(ImmutableIntegerModuloP m);
    IntegerFieldModuloP residueField();
}
