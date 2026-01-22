/*
 * Copyright (c) 2003, 2024, Oracle and/or its affiliates. All rights reserved.
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

package jdk.javadoc.internal.doclets.formats.html.taglets;

import java.util.EnumSet;

import javax.lang.model.element.Element;

import com.sun.source.doctree.DocTree;
import com.sun.source.doctree.LiteralTree;

import jdk.javadoc.doclet.Taglet;
import jdk.javadoc.internal.doclets.formats.html.HtmlConfiguration;
import jdk.javadoc.internal.html.Content;
import jdk.javadoc.internal.html.HtmlTree;
import jdk.javadoc.internal.html.Text;

/**
 * An inline taglet used to denote literal text, possibly in monospace font.
 *
 * For example, the text:
 * <blockquote>  {@code {@literal a<B>c}}  </blockquote>
 * displays as:
 * <blockquote>  {@literal a<B>c}  </blockquote>
 *
 * <p> The tag {@code {@code ...}} is equivalent to
 * {@code <code>{@literal ...}</code>}.
 *
 * For example, the text:
 * <blockquote>  The type {@code {@code List<P>}}  </blockquote>
 * displays as:
 * <blockquote>  The type {@code List<P>}  </blockquote>
 */
public class LiteralTaglet extends BaseTaglet {
    LiteralTaglet(HtmlConfiguration config, DocTree.Kind tagKind) {
        super(config, tagKind, true, EnumSet.allOf(Taglet.Location.class));
    }

    @Override
    public Content getInlineTagOutput(Element element, DocTree tag, TagletWriter tagletWriter) {
        var literalTree = (LiteralTree) tag;
        var body = Text.of(Text.normalizeNewlines(literalTree.getBody().getBody()));
        return tag.getKind() == DocTree.Kind.CODE ? HtmlTree.CODE(body) : body;
    }
}
