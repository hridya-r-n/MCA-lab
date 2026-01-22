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

package jdk.internal.org.commonmark.node;

/**
 * A node that uses delimiters in the source form (e.g. <code>*bold*</code>).
 */
public interface Delimited {

    /**
     * @return the opening (beginning) delimiter, e.g. <code>*</code>
     */
    String getOpeningDelimiter();

    /**
     * @return the closing (ending) delimiter, e.g. <code>*</code>
     */
    String getClosingDelimiter();
}
