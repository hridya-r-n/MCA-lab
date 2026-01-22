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

import static javax.lang.model.SourceVersion.*;
import javax.annotation.processing.SupportedSourceVersion;
import javax.annotation.processing.ProcessingEnvironment;

/**
 * A skeletal visitor for annotation values with default behavior
 * appropriate for a {@linkplain
 * ProcessingEnvironment#isPreviewEnabled preview} source version.
 *
 * @param <R> the return type of this visitor's methods
 * @param <P> the type of the additional parameter to this visitor's methods.
 *
 * @see javax.lang.model.util##expectedEvolution
 * <strong>Expected visitor evolution</strong>
 * @see AbstractAnnotationValueVisitor6##note_for_subclasses
 * <strong>Compatibility note for subclasses</strong>
 * @see AbstractAnnotationValueVisitor6
 * @see AbstractAnnotationValueVisitor7
 * @see AbstractAnnotationValueVisitor8
 * @see AbstractAnnotationValueVisitor9
 * @see AbstractAnnotationValueVisitor14
 * @since 23
 */
@SupportedSourceVersion(RELEASE_25)
@PreviewFeature(feature=PreviewFeature.Feature.LANGUAGE_MODEL, reflective=true)
public abstract class AbstractAnnotationValueVisitorPreview<R, P> extends AbstractAnnotationValueVisitor14<R, P> {

    /**
     * Constructor for concrete subclasses to call.
     */
    protected AbstractAnnotationValueVisitorPreview() {
        super();
    }
}
