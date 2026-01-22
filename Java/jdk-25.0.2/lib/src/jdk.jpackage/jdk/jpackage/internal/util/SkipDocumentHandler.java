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
package jdk.jpackage.internal.util;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import javax.xml.stream.XMLStreamWriter;

final class SkipDocumentHandler implements InvocationHandler {

    public SkipDocumentHandler(XMLStreamWriter target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        switch (method.getName()) {
            case "writeStartDocument", "writeEndDocument" -> {
            }
            default -> method.invoke(target, args);
        }
        return null;
    }

    private final XMLStreamWriter target;
}
