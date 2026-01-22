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

package jdk.internal.org.commonmark.internal.renderer.text;

public abstract class ListHolder {
    private static final String INDENT_DEFAULT = "   ";
    private static final String INDENT_EMPTY = "";

    private final ListHolder parent;
    private final String indent;

    ListHolder(ListHolder parent) {
        this.parent = parent;

        if (parent != null) {
            indent = parent.indent + INDENT_DEFAULT;
        } else {
            indent = INDENT_EMPTY;
        }
    }

    public ListHolder getParent() {
        return parent;
    }

    public String getIndent() {
        return indent;
    }
}
