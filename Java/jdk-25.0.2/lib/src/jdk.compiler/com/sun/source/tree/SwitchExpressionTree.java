/*
 * Copyright (c) 2018, 2024, Oracle and/or its affiliates. All rights reserved.
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

import java.util.List;

/**
 * A tree node for a {@code switch} expression.
 *
 * For example:
 * <pre>
 *   switch ( <em>expression</em> ) {
 *     <em>cases</em>
 *   }
 * </pre>
 *
 * @jls 15.28 {@code switch} Expressions
 *
 * @since 14
 */
public interface SwitchExpressionTree extends ExpressionTree {
    /**
     * Returns the expression for the {@code switch} expression.
     * @return the expression
     */
    ExpressionTree getExpression();

    /**
     * Returns the cases for the {@code switch} expression.
     * @return the cases
     */
    List<? extends CaseTree> getCases();
}
