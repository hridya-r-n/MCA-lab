/*
 * Copyright (c) 2010, 2024, Oracle and/or its affiliates. All rights reserved.
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

package jdk.javadoc.internal.html;

import java.io.IOException;
import java.io.Writer;
import java.util.Objects;

/**
 * Class for generating a comment for HTML pages of javadoc output.
 */
public class Comment extends Content {

    private final String commentText;

    /**
     * Constructor to construct a Comment object.
     *
     * @param comment comment text for the comment
     */
    public Comment(String comment) {
        commentText = Objects.requireNonNull(comment);
    }

    @Override
    public boolean isEmpty() {
        return commentText.isEmpty();
    }

    @Override
    public boolean write(Writer out, String newline, boolean atNewline) throws IOException {
        if (!atNewline) {
            out.write(newline);
        }
        out.write("<!-- ");
        out.write(commentText.replace("\n", newline));
        out.write(" -->");
        out.write(newline);
        return true;
    }
}
