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

package jdk.internal.org.commonmark.parser;

import jdk.internal.org.commonmark.node.SourceSpan;

/**
 * A line or part of a line from the input source.
 *
 * @since 0.16.0
 */
public class SourceLine {

    private final CharSequence content;
    private final SourceSpan sourceSpan;

    public static SourceLine of(CharSequence content, SourceSpan sourceSpan) {
        return new SourceLine(content, sourceSpan);
    }

    private SourceLine(CharSequence content, SourceSpan sourceSpan) {
        if (content == null) {
            throw new NullPointerException("content must not be null");
        }
        this.content = content;
        this.sourceSpan = sourceSpan;
    }

    public CharSequence getContent() {
        return content;
    }

    public SourceSpan getSourceSpan() {
        return sourceSpan;
    }

    public SourceLine substring(int beginIndex, int endIndex) {
        CharSequence newContent = content.subSequence(beginIndex, endIndex);
        SourceSpan newSourceSpan = null;
        if (sourceSpan != null) {
            int columnIndex = sourceSpan.getColumnIndex() + beginIndex;
            int length = endIndex - beginIndex;
            if (length != 0) {
                newSourceSpan = SourceSpan.of(sourceSpan.getLineIndex(), columnIndex, length);
            }
        }
        return SourceLine.of(newContent, newSourceSpan);
    }
}
