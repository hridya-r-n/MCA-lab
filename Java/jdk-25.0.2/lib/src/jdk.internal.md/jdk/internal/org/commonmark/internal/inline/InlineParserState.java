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

import jdk.internal.org.commonmark.parser.beta.Position;
import jdk.internal.org.commonmark.parser.beta.Scanner;

public interface InlineParserState {

    /**
     * Return a scanner for the input for the current position (on the character that the inline parser registered
     * interest for).
     * <p>
     * Note that this always returns the same instance, if you want to backtrack you need to use
     * {@link Scanner#position()} and {@link Scanner#setPosition(Position)}.
     */
    Scanner scanner();
}
