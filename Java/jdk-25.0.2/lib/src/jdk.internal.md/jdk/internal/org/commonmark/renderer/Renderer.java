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

package jdk.internal.org.commonmark.renderer;

import jdk.internal.org.commonmark.node.Node;

public interface Renderer {

    /**
     * Render the tree of nodes to output.
     *
     * @param node the root node
     * @param output output for rendering
     */
    void render(Node node, Appendable output);

    /**
     * Render the tree of nodes to string.
     *
     * @param node the root node
     * @return the rendered string
     */
    String render(Node node);
}
