/*
 * Copyright (c) 2014, 2024, Oracle and/or its affiliates. All rights reserved.
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

package jdk.internal.access;

/**
 * SharedSecrets interface used for the access from java.text.Bidi
 */

public interface JavaAWTFontAccess {

    // java.awt.font.TextAttribute constants
    public Object getTextAttributeConstant(String name);

    // java.awt.font.NumericShaper
    public void shape(Object shaper, char[] text, int start, int count);
}
