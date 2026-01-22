/*
 * Copyright (c) 2022, 2025, Oracle and/or its affiliates. All rights reserved.
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
package java.lang.classfile;

import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;

import jdk.internal.classfile.impl.TransformImpl;

import static java.util.Objects.requireNonNull;

/**
 * A transformation on streams of {@link FieldElement}.
 * <p>
 * Refer to {@link ClassFileTransform} for general guidance and caution around
 * the use of transforms for structures in the {@code class} file format.
 * <p>
 * A field transform can be lifted to a class transform via {@link
 * ClassTransform#transformingFields(FieldTransform)}, transforming only
 * the {@link FieldModel} among the class members and passing all other elements
 * to the builders.
 *
 * @see FieldModel
 * @see ClassBuilder#transformField
 * @since 24
 */
@FunctionalInterface
public non-sealed interface FieldTransform
        extends ClassFileTransform<FieldTransform, FieldElement, FieldBuilder> {

    /**
     * A field transform that passes all elements to the builder.
     */
    FieldTransform ACCEPT_ALL = new FieldTransform() {
        @Override
        public void accept(FieldBuilder builder, FieldElement element) {
            builder.with(element);
        }
    };

    /**
     * Creates a stateful field transform from a {@link Supplier}.  The supplier
     * will be invoked for each transformation.
     *
     * @param supplier a {@link Supplier} that produces a fresh transform object
     *                 for each traversal
     * @return the stateful field transform
     */
    static FieldTransform ofStateful(Supplier<FieldTransform> supplier) {
        return new TransformImpl.SupplierFieldTransform(requireNonNull(supplier));
    }

    /**
     * Creates a field transform that passes each element through to the builder,
     * and calls the specified function when transformation is complete.
     *
     * @param finisher the function to call when transformation is complete
     * @return the field transform
     */
    static FieldTransform endHandler(Consumer<FieldBuilder> finisher) {
        requireNonNull(finisher);
        return new FieldTransform() {
            @Override
            public void accept(FieldBuilder builder, FieldElement element) {
                builder.with(element);
            }

            @Override
            public void atEnd(FieldBuilder builder) {
                finisher.accept(builder);
            }
        };
    }

    /**
     * Creates a field transform that passes each element through to the builder,
     * except for those that the supplied {@link Predicate} is true for.
     *
     * @param filter the predicate that determines which elements to drop
     * @return the field transform
     */
    static FieldTransform dropping(Predicate<FieldElement> filter) {
        requireNonNull(filter);
        return (b, e) -> {
            if (!filter.test(e))
                b.with(e);
        };
    }

    /**
     * @implSpec
     * The default implementation returns this field transform chained with another
     * field transform from the argument. Chaining of two transforms requires to
     * involve a chained builder serving as a target builder for this transform
     * and also as a source of elements for the downstream transform.
     */
    @Override
    default FieldTransform andThen(FieldTransform t) {
        return new TransformImpl.ChainedFieldTransform(this, requireNonNull(t));
    }
}
