/*
 * Copyright (c) 2015, 2024, Oracle and/or its affiliates. All rights reserved.
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
import com.sun.source.doctree.IndexTree;
import com.sun.source.doctree.TextTree;

import jdk.javadoc.doclet.Taglet;
import jdk.javadoc.internal.doclets.formats.html.HtmlConfiguration;
import jdk.javadoc.internal.html.Content;

/**
 * An inline taglet used to index a word or a phrase.
 * The enclosed text is interpreted as not containing HTML markup or
 * nested javadoc tags.
 */
public class IndexTaglet extends BaseTaglet {

    IndexTaglet(HtmlConfiguration config) {
        super(config, DocTree.Kind.INDEX, true, EnumSet.allOf(Taglet.Location.class));
    }

    @Override
    public Content getInlineTagOutput(Element element, DocTree tag, TagletWriter tagletWriter) {
        var context = tagletWriter.context;
        var indexTree = (IndexTree) tag;

        DocTree searchTerm = indexTree.getSearchTerm();
        String tagText = (searchTerm instanceof TextTree tt) ? tt.getBody() : "";
        if (tagText.charAt(0) == '"' && tagText.charAt(tagText.length() - 1) == '"') {
            tagText = tagText.substring(1, tagText.length() - 1);
        }
        tagText = utils.normalizeWhitespace(tagText);

        Content desc = tagletWriter.htmlWriter.commentTagsToContent(element, indexTree.getDescription(), context.within(indexTree));
        String descText = utils.normalizeWhitespace(extractText(desc));

        return tagletWriter.createAnchorAndSearchIndex(element, tagText, descText, tag);
    }

    // ugly but simple;
    // alternatives would be to walk the Content's tree structure, or to add new functionality to Content
    private String extractText(Content c) {
        return c.toString().replaceAll("<[^>]+>", "");
    }
}
