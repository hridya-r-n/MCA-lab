/*
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

/*
 * This file is available under and governed by the GNU General Public
 * License version 2 only, as published by the Free Software Foundation.
 * However, a notice that is now available elsewhere in this distribution
 * accompanied the original version of this file, and, per its terms,
 * should not be removed.
 */

package jdk.internal.org.commonmark.ext.gfm.tables;

import jdk.internal.org.commonmark.node.CustomNode;

/**
 * Table cell of a {@link TableRow} containing inline nodes.
 */
public class TableCell extends CustomNode {

    private boolean header;
    private Alignment alignment;
    private int width;

    /**
     * @return whether the cell is a header or not
     */
    public boolean isHeader() {
        return header;
    }

    public void setHeader(boolean header) {
        this.header = header;
    }

    /**
     * @return the cell alignment or {@code null} if no specific alignment
     */
    public Alignment getAlignment() {
        return alignment;
    }

    public void setAlignment(Alignment alignment) {
        this.alignment = alignment;
    }

    /**
     * @return the cell width (the number of dash and colon characters in the delimiter row of the table for this column)
     */
    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * How the cell is aligned horizontally.
     */
    public enum Alignment {
        LEFT, CENTER, RIGHT
    }

}
