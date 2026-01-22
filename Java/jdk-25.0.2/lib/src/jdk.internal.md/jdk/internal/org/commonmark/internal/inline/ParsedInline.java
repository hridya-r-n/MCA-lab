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

package jdk.internal.org.commonmark.internal.inline;

import jdk.internal.org.commonmark.node.Node;
import jdk.internal.org.commonmark.parser.beta.Position;

public abstract class ParsedInline {

    protected ParsedInline() {
    }

    public static ParsedInline none() {
        return null;
    }

    public static ParsedInline of(Node node, Position position) {
        if (node == null) {
            throw new NullPointerException("node must not be null");
        }
        if (position == null) {
            throw new NullPointerException("position must not be null");
        }
        return new ParsedInlineImpl(node, position);
    }
}
