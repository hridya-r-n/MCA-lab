/*
 * Copyright (c) 2019, 2024, Oracle and/or its affiliates. All rights reserved.
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

package javax.lang.model.util;

import jdk.internal.javac.PreviewFeature;

import javax.annotation.processing.SupportedSourceVersion;
import javax.annotation.processing.ProcessingEnvironment;
import static javax.lang.model.SourceVersion.*;

/**
 * A simple visitor of program elements with default behavior
 * appropriate for a {@linkplain
 * ProcessingEnvironment#isPreviewEnabled preview} source version.
 *
 * Visit methods corresponding to {@code RELEASE_14} and earlier
 * language constructs call {@link #defaultAction defaultAction},
 * passing their arguments to {@code defaultAction}'s corresponding
 * parameters.
 *
 * @apiNote
 * Methods in this class may be overridden subject to their general
 * contract.
 *
 * @param <R> the return type of this visitor's methods.  Use {@code Void}
 *             for visitors that do not need to return results.
 * @param <P> the type of the additional parameter to this visitor's methods.  Use {@code Void}
 *              for visitors that do not need an additional parameter.
 *
 * @see javax.lang.model.util##expectedEvolution
 * <strong>Expected visitor evolution</strong>
 * @see AbstractAnnotationValueVisitor6##note_for_subclasses
 * <strong>Compatibility note for subclasses</strong>
 * @see SimpleElementVisitor6
 * @see SimpleElementVisitor7
 * @see SimpleElementVisitor8
 * @see SimpleElementVisitor9
 * @see SimpleElementVisitor14
 * @since 23
 */
@SupportedSourceVersion(RELEASE_25)
@PreviewFeature(feature=PreviewFeature.Feature.LANGUAGE_MODEL, reflective=true)
public class SimpleElementVisitorPreview<R, P> extends SimpleElementVisitor14<R, P> {
    /**
     * Constructor for concrete subclasses; uses {@code null} for the
     * default value.
     */
    protected SimpleElementVisitorPreview(){
        super(null);
    }

    /**
     * Constructor for concrete subclasses; uses the argument for the
     * default value.
     *
     * @param defaultValue the value to assign to {@link #DEFAULT_VALUE}
     */
    protected SimpleElementVisitorPreview(R defaultValue){
        super(defaultValue);
    }
}
