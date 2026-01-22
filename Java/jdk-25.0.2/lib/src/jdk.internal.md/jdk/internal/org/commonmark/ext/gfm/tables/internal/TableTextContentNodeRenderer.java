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

package jdk.internal.org.commonmark.ext.gfm.tables.internal;

import jdk.internal.org.commonmark.ext.gfm.tables.TableBlock;
import jdk.internal.org.commonmark.ext.gfm.tables.TableBody;
import jdk.internal.org.commonmark.ext.gfm.tables.TableCell;
import jdk.internal.org.commonmark.ext.gfm.tables.TableHead;
import jdk.internal.org.commonmark.ext.gfm.tables.TableRow;
import jdk.internal.org.commonmark.node.Node;
import jdk.internal.org.commonmark.renderer.text.TextContentNodeRendererContext;
import jdk.internal.org.commonmark.renderer.text.TextContentWriter;

/**
 * The Table node renderer that is needed for rendering GFM tables (GitHub Flavored Markdown) to text content.
 */
public class TableTextContentNodeRenderer extends TableNodeRenderer {

    private final TextContentWriter textContentWriter;
    private final TextContentNodeRendererContext context;

    public TableTextContentNodeRenderer(TextContentNodeRendererContext context) {
        this.textContentWriter = context.getWriter();
        this.context = context;
    }

    protected void renderBlock(TableBlock tableBlock) {
        renderChildren(tableBlock);
        if (tableBlock.getNext() != null) {
            textContentWriter.write("\n");
        }
    }

    protected void renderHead(TableHead tableHead) {
        renderChildren(tableHead);
    }

    protected void renderBody(TableBody tableBody) {
        renderChildren(tableBody);
    }

    protected void renderRow(TableRow tableRow) {
        textContentWriter.line();
        renderChildren(tableRow);
        textContentWriter.line();
    }

    protected void renderCell(TableCell tableCell) {
        renderChildren(tableCell);
        textContentWriter.write('|');
        textContentWriter.whitespace();
    }

    private void renderLastCell(TableCell tableCell) {
        renderChildren(tableCell);
    }

    private void renderChildren(Node parent) {
        Node node = parent.getFirstChild();
        while (node != null) {
            Node next = node.getNext();

            // For last cell in row, we dont render the delimiter.
            if (node instanceof TableCell && next == null) {
                renderLastCell((TableCell) node);
            } else {
                context.render(node);
            }

            node = next;
        }
    }
}
