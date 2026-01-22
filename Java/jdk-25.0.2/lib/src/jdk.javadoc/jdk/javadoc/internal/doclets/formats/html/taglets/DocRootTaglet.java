/*
 * Copyright (c) 2001, 2024, Oracle and/or its affiliates. All rights reserved.
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

import jdk.javadoc.doclet.Taglet;
import jdk.javadoc.internal.doclets.formats.html.HtmlConfiguration;
import jdk.javadoc.internal.html.Content;
import jdk.javadoc.internal.html.Text;

/**
 * An inline taglet representing {@code {@docRoot}}.  This taglet is
 * used to get the relative path to the document's root output
 * directory.
 */
public class DocRootTaglet extends BaseTaglet {
    DocRootTaglet(HtmlConfiguration config) {
        super(config, DocTree.Kind.DOC_ROOT, true, EnumSet.allOf(Taglet.Location.class));
    }

    @Override
    public Content getInlineTagOutput(Element holder, DocTree tag, TagletWriter tagletWriter) {
        var htmlWriter = tagletWriter.htmlWriter;
        var pathToRoot = htmlWriter.pathToRoot;
        return Text.of(pathToRoot.isEmpty() ? "." : pathToRoot.getPath());
    }
}
