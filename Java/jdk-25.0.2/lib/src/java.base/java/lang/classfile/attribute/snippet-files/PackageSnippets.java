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
package java.lang.classfile.attribute.snippet;

import java.lang.classfile.AttributedElement;
import java.lang.classfile.Attributes;
import java.lang.classfile.CodeBuilder;
import java.lang.classfile.MethodModel;
import java.lang.classfile.attribute.RuntimeVisibleAnnotationsAttribute;
import java.util.List;

class PackageSnippets {

    // @start region=hasDeprecated
    private static final String DEPRECATED_DESC = Deprecated.class.descriptorString();

    static boolean hasDeprecated(AttributedElement element) {
        var annotations = element.findAttribute(Attributes.runtimeVisibleAnnotations())
                .map(RuntimeVisibleAnnotationsAttribute::annotations)
                .orElse(List.of());
        for (var anno : annotations) {
            // equalsString reduces extra computations for raw UTF-8 entries
            if (anno.className().equalsString(DEPRECATED_DESC)) {
                return true;
            }
        }
        return false;
    }
    // @end

    // @start region=reuseStackMaps
    static void reuseStackMaps(MethodModel oldMethod, CodeBuilder cob) {
        var oldCode = oldMethod.code().orElseThrow();
        // The StackMapTable attribute is not streamed in CodeModel, so this is
        // the only way to obtain it
        // @link substring="findAttribute" target="AttributedElement#findAttribute" :
        var stackMaps = oldCode.findAttribute(Attributes.stackMapTable());
        stackMaps.ifPresent(cob); // Note: CodeBuilder is a Consumer<CodeElement>
    }
    // @end
}
