/*
 * Copyright (c) 2005, 2024, Oracle and/or its affiliates. All rights reserved.
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

package com.sun.source.tree;

/**
 * A tree node for an {@code instanceof} expression.
 *
 * For example:
 * <pre>
 *   <em>expression</em> instanceof <em>type</em>
 *
 *   <em>expression</em> instanceof <em>pattern</em>
 * </pre>
 *
 * @jls 15.20.2 The instanceof Operator
 *
 * @author Peter von der Ah&eacute;
 * @author Jonathan Gibbons
 * @since 1.6
 */
public interface InstanceOfTree extends ExpressionTree {

    /**
     * Returns the expression to be tested.
     * @return the expression
     */
    ExpressionTree getExpression();

    /**
     * Returns the type for which to check, or {@code null} if this {@code instanceof}
     * uses a pattern other the {@link BindingPatternTree}.
     *
     * <p>For {@code instanceof} without a pattern, i.e. in the following form:
     * <pre>
     *   <em>expression</em> instanceof <em>type</em>
     * </pre>
     * returns the type.
     *
     * <p>For {@code instanceof} with a {@link BindingPatternTree}, i.e. in the following form:
     * <pre>
     *   <em>expression</em> instanceof <em>type</em> <em>variable_name</em>
     * </pre>
     * returns the type.
     *
     * <p>For instanceof with a pattern, i.e. in the following form:
     * <pre>
     *   <em>expression</em> instanceof <em>pattern</em>
     * </pre>
     * returns {@code null}.
     *
     * @return the type or {@code null} if this {@code instanceof} uses a pattern other than
     *         the {@linkplain BindingPatternTree}
     * @see #getPattern()
     */
    Tree getType();

    /**
     * Returns the tested pattern, or {@code null} if this {@code instanceof} does not use
     * a pattern.
     *
     * <p>For instanceof with a pattern, i.e. in the following form:
     * <pre>
     *   <em>expression</em> instanceof <em>pattern</em>
     * </pre>
     * returns the pattern.
     *
     * <p>For {@code instanceof} without a pattern, i.e. in the following form:
     * <pre>
     *   <em>expression</em> instanceof <em>type</em>
     * </pre>
     * returns {@code null}.
     *
     * @return the tested pattern, or {@code null} if this {@code instanceof} does not use a pattern
     * @since 16
     */
    PatternTree getPattern();

}
