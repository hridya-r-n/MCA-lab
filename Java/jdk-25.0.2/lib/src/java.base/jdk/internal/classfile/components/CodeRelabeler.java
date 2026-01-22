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
package jdk.internal.classfile.components;

import java.lang.classfile.CodeBuilder;
import java.lang.classfile.CodeTransform;
import java.lang.classfile.Label;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.function.BiFunction;

import jdk.internal.classfile.impl.CodeRelabelerImpl;

import static java.util.Objects.requireNonNull;

/**
 * A code relabeler is a {@link CodeTransform} replacing all occurrences
 * of {@link java.lang.classfile.Label} in the transformed code with new instances.
 * All {@link java.lang.classfile.instruction.LabelTarget} instructions are adjusted accordingly.
 * Relabeled code graph is identical to the original.
 * <p>
 * Primary purpose of CodeRelabeler is for repeated injections of the same code blocks.
 * Repeated injection of the same code block must be relabeled, so each instance of
 * {@link java.lang.classfile.Label} is bound in the target bytecode exactly once.
 */
public sealed interface CodeRelabeler extends CodeTransform permits CodeRelabelerImpl {

    /**
     * Creates a new instance of CodeRelabeler.
     * @return a new instance of CodeRelabeler
     */
    static CodeRelabeler of() {
        return of(new IdentityHashMap<>());
    }

    /**
     * Creates a new instance of CodeRelabeler storing the label mapping into the provided map.
     * @param map label map actively used for relabeling
     * @return a new instance of CodeRelabeler
     */
    static CodeRelabeler of(Map<Label, Label> map) {
        requireNonNull(map);
        return of((l, cob) -> map.computeIfAbsent(l, ll -> cob.newLabel()));
    }

    /**
     * Creates a new instance of CodeRelabeler using provided {@link java.util.function.BiFunction}
     * to re-label the code.
     * @param mapFunction function for remapping labels in the source code model
     * @return a new instance of CodeRelabeler
     */
    static CodeRelabeler of(BiFunction<Label, CodeBuilder, Label> mapFunction) {
        return new CodeRelabelerImpl(requireNonNull(mapFunction));
    }
}
