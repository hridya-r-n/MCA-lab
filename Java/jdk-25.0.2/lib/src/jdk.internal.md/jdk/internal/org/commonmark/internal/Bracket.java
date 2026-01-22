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

package jdk.internal.org.commonmark.internal;

import jdk.internal.org.commonmark.node.Text;
import jdk.internal.org.commonmark.parser.beta.Position;

/**
 * Opening bracket for links (<code>[</code>) or images (<code>![</code>).
 */
public class Bracket {

    public final Text node;

    /**
     * The position of the marker for the bracket (<code>[</code> or <code>![</code>)
     */
    public final Position markerPosition;

    /**
     * The position of the content (after the opening bracket)
     */
    public final Position contentPosition;

    /**
     * Whether this is an image or link.
     */
    public final boolean image;

    /**
     * Previous bracket.
     */
    public final Bracket previous;

    /**
     * Previous delimiter (emphasis, etc) before this bracket.
     */
    public final Delimiter previousDelimiter;

    /**
     * Whether this bracket is allowed to form a link/image (also known as "active").
     */
    public boolean allowed = true;

    /**
     * Whether there is an unescaped bracket (opening or closing) anywhere after this opening bracket.
     */
    public boolean bracketAfter = false;

    static public Bracket link(Text node, Position markerPosition, Position contentPosition, Bracket previous, Delimiter previousDelimiter) {
        return new Bracket(node, markerPosition, contentPosition, previous, previousDelimiter, false);
    }

    static public Bracket image(Text node, Position markerPosition, Position contentPosition, Bracket previous, Delimiter previousDelimiter) {
        return new Bracket(node, markerPosition, contentPosition, previous, previousDelimiter, true);
    }

    private Bracket(Text node, Position markerPosition, Position contentPosition, Bracket previous, Delimiter previousDelimiter, boolean image) {
        this.node = node;
        this.markerPosition = markerPosition;
        this.contentPosition = contentPosition;
        this.image = image;
        this.previous = previous;
        this.previousDelimiter = previousDelimiter;
    }
}
