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

package jdk.internal.org.commonmark.parser.block;

import jdk.internal.org.commonmark.node.Block;
import jdk.internal.org.commonmark.node.SourceSpan;
import jdk.internal.org.commonmark.parser.InlineParser;
import jdk.internal.org.commonmark.parser.SourceLine;

public abstract class AbstractBlockParser implements BlockParser {

    @Override
    public boolean isContainer() {
        return false;
    }

    @Override
    public boolean canHaveLazyContinuationLines() {
        return false;
    }

    @Override
    public boolean canContain(Block childBlock) {
        return false;
    }

    @Override
    public void addLine(SourceLine line) {
    }

    @Override
    public void addSourceSpan(SourceSpan sourceSpan) {
        getBlock().addSourceSpan(sourceSpan);
    }

    @Override
    public void closeBlock() {
    }

    @Override
    public void parseInlines(InlineParser inlineParser) {
    }

}
