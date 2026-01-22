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
import java.util.List;

import javax.lang.model.element.Element;

import com.sun.source.doctree.DeprecatedTree;
import com.sun.source.doctree.DocTree;

import jdk.javadoc.doclet.Taglet;
import jdk.javadoc.internal.doclets.formats.html.HtmlConfiguration;
import jdk.javadoc.internal.doclets.formats.html.markup.HtmlStyles;
import jdk.javadoc.internal.doclets.toolkit.util.CommentHelper;
import jdk.javadoc.internal.html.Content;
import jdk.javadoc.internal.html.ContentBuilder;
import jdk.javadoc.internal.html.HtmlTree;

/**
 * A taglet that represents the {@code @deprecated} tag.
 */
public class DeprecatedTaglet extends BaseTaglet {

    DeprecatedTaglet(HtmlConfiguration config) {
        super(config, DocTree.Kind.DEPRECATED, false,
                EnumSet.of(Taglet.Location.MODULE, Taglet.Location.TYPE, Taglet.Location.CONSTRUCTOR, Taglet.Location.METHOD, Taglet.Location.FIELD));

    }

    @Override
    public Content getAllBlockTagOutput(Element element, TagletWriter tagletWriter) {
        var htmlWriter = tagletWriter.htmlWriter;

        ContentBuilder result = new ContentBuilder();
        CommentHelper ch = utils.getCommentHelper(element);
        List<? extends DeprecatedTree> deprs = utils.getDeprecatedTrees(element);
        if (utils.isTypeElement(element)) {
            if (utils.isDeprecated(element)) {
                result.add(HtmlTree.SPAN(HtmlStyles.deprecatedLabel,
                        htmlWriter.getDeprecatedPhrase(element)));
                if (!deprs.isEmpty()) {
                    List<? extends DocTree> commentTrees = ch.getDescription(deprs.get(0));
                    if (!commentTrees.isEmpty()) {
                        result.add(tagletWriter.commentTagsToOutput(element, null, commentTrees, false));
                    }
                }
            }
        } else {
            if (utils.isDeprecated(element)) {
                result.add(HtmlTree.SPAN(HtmlStyles.deprecatedLabel,
                        htmlWriter.getDeprecatedPhrase(element)));
                if (!deprs.isEmpty()) {
                    List<? extends DocTree> bodyTrees = ch.getBody(deprs.get(0));
                    Content body = tagletWriter.commentTagsToOutput(element, null, bodyTrees, false);
                    if (!body.isEmpty())
                        result.add(HtmlTree.DIV(HtmlStyles.deprecationComment, body));
                }
            } else {
                Element ee = utils.getEnclosingTypeElement(element);
                if (utils.isDeprecated(ee)) {
                    result.add(HtmlTree.SPAN(HtmlStyles.deprecatedLabel,
                            htmlWriter.getDeprecatedPhrase(ee)));
                }
            }
        }
        return result;

    }

}
