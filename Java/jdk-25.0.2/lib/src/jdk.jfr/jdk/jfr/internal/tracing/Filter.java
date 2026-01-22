/*
 * Copyright (c) 2025, Oracle and/or its affiliates. All rights reserved.
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
package jdk.jfr.internal.tracing;

import jdk.internal.module.Checks;

/**
 * Class that represents the filter a user can specify for the MethodTrace and
 * MethodTiming event.
 */
public record Filter(String className, String methodName, String annotationName, Modification modification) {

    public static boolean isValid(String filter) {
        return of(filter, Modification.NONE) != null;
    }

    static Filter of(String filter, Modification modification) {
        if (filter.startsWith("@")) {
            return ofAnnotation(filter, modification);
        }
        if (filter.contains("::")) {
            return ofMethod(filter, modification);
        }
        return ofClass(filter, modification);
    }

    private static Filter ofAnnotation(String filter, Modification modification) {
        String annotation = filter.substring(1);
        if (Checks.isClassName(annotation)) {
            return new Filter(null, null, annotation, modification);
        }
        return null;
    }

    private static Filter ofMethod(String filter, Modification modification) {
        int index = filter.indexOf("::");
        String classPart = filter.substring(0, index);
        String methodPart = filter.substring(index + 2);
        if (methodPart.isEmpty()) {
            // Don't allow "foo.Bar::". User should specify "foo.Bar".
            return null;
        }

        if (isMethod(methodPart)) {
            // Method name only, i.e. "::baz"
            if (classPart.isEmpty()) {
                return new Filter(null, methodPart, null, modification);
            }
            // Fully qualified method name, i.e. "foo.Bar::baz"
            if (isValidClassName(classPart)) {
                return new Filter(classPart, methodPart, null, modification);
            }
        }
        return null;
    }

    private static boolean isMethod(String methodName) {
        if (methodName.equals("<clinit>") || methodName.equals("<init>")) {
            return true;
        }
        return Checks.isJavaIdentifier(methodName);
    }

    private static Filter ofClass(String filter, Modification modification) {
        if (isValidClassName(filter)) {
            return new Filter(filter, null, null, modification);
        }
        return null;
    }

    private static boolean isValidClassName(String text) {
        return Checks.isClassName(text);
    }
}
