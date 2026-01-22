/*
 * Copyright (c) 2023, 2024, Oracle and/or its affiliates. All rights reserved.
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
package jdk.internal.constant;

import java.lang.constant.PackageDesc;

/*
 * Implementation of {@code PackageDesc}
 * @param internalName must have been validated
 */
public record PackageDescImpl(String internalName) implements PackageDesc {

    @Override
    public String toString() {
        return String.format("PackageDesc[%s]", name());
    }
}
