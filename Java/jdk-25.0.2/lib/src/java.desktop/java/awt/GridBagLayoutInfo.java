/*
 * Copyright (c) 2006, 2025, Oracle and/or its affiliates. All rights reserved.
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

package java.awt;

import java.io.Serial;

/**
 * The {@code GridBagLayoutInfo} is an utility class for
 * {@code GridBagLayout} layout manager.
 * It stores align, size and baseline parameters for every component within a container.
 *
 * @see       java.awt.GridBagLayout
 * @see       java.awt.GridBagConstraints
 * @since 1.6
 */
public final class GridBagLayoutInfo implements java.io.Serializable {

    /**
     * Use serialVersionUID from JDK 1.6 for interoperability.
     */
    @Serial
    private static final long serialVersionUID = -4899416460737170217L;

    /**
     * @serial The number of cells: horizontal and vertical.
     */
    int width, height;

    /**
     * @serial The starting point for layout.
     */
    int startx, starty;

    /**
     * @serial The largest minWidth in each column.
     */
    int[] minWidth;

    /**
     * @serial The largest minHeight in each row.
     */
    int[] minHeight;

    /**
     * @serial The largest weight in each column.
     */
    double[] weightX;

    /**
     * @serial The largest weight in each row.
     */
    double[] weightY;

    /**
     * @serial Whether or not baseline layout has been requested and one of the
     * components has a valid baseline.
     */
    boolean hasBaseline;

    // These are only valid if hasBaseline is true and are indexed by
    // row.
    /**
     * @serial The type of baseline for a particular row. A mix of the
     * BaselineResizeBehavior constants {@code (1 << ordinal())}
     */
    short[] baselineType;

    /**
     * @serial Max ascent (baseline).
     */
    int[] maxAscent;

    /**
     * @serial Max descent (height - baseline)
     */
    int[] maxDescent;

    /**
     * Creates an instance of GridBagLayoutInfo representing {@code GridBagLayout}
     * grid cells with it's own parameters.
     * @param width the columns
     * @param height the rows
     * @since 1.6
     */
    GridBagLayoutInfo(int width, int height) {
        this.width = width;
        this.height = height;
    }

    /**
     * Returns true if the specified row has any component aligned on the
     * baseline with a baseline resize behavior of CONSTANT_DESCENT.
     */
    boolean hasConstantDescent(int row) {
        return ((baselineType[row] & (1 << Component.BaselineResizeBehavior.
                                      CONSTANT_DESCENT.ordinal())) != 0);
    }

    /**
     * Returns true if there is a baseline for the specified row.
     */
    boolean hasBaseline(int row) {
        return (hasBaseline && baselineType[row] != 0);
    }
}
