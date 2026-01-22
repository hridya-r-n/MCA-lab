/*
 * Copyright (c) 2012, 2024, Oracle and/or its affiliates. All rights reserved.
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

package com.sun.tools.javac.tree;

import javax.lang.model.util.Elements.DocCommentKind;

import com.sun.source.doctree.DocCommentTree;
import com.sun.source.doctree.ErroneousTree;

import com.sun.tools.javac.parser.Tokens.Comment;

/**
 * A table giving the doc comment, if any, for any tree node.
 *
 *  <p><b>This is NOT part of any supported API.
 *  If you write code that depends on this, you do so at your own
 *  risk.  This code and its internal interfaces are subject to change
 *  or deletion without notice.</b>
 */
public interface DocCommentTable {
    /**
     * Checks if a tree node has a corresponding doc comment.
     */
    boolean hasComment(JCTree tree);

    /**
     * Returns the Comment token containing the doc comment, if any, for a tree node.
     */
    Comment getComment(JCTree tree);

    /**
     * Returns the kind of the doc comment, if any, for a tree node.
     */
    DocCommentKind getCommentKind(JCTree tree);

    /**
     * Returns the plain text of the doc comment, if any, for a tree node.
     */
    String getCommentText(JCTree tree);

    /**
     * Returns the parsed form of the doc comment as a DocTree. If any errors
     * are detected during parsing, they will be reported via
     * {@link ErroneousTree} nodes within the resulting tree.
     */
    DocCommentTree getCommentTree(JCTree tree);

    /**
     * Sets the Comment to be associated with a tree node.
     */
    void putComment(JCTree tree, Comment c);
}
