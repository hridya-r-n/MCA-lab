/*
 * Copyright (c) 1997, 2024, Oracle and/or its affiliates. All rights reserved.
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

package jdk.javadoc.internal.doclets.formats.html;

import java.util.SortedSet;

import javax.lang.model.element.PackageElement;

import jdk.javadoc.internal.doclets.formats.html.Navigation.PageMode;
import jdk.javadoc.internal.doclets.formats.html.markup.BodyContents;
import jdk.javadoc.internal.doclets.formats.html.markup.HtmlStyles;
import jdk.javadoc.internal.doclets.toolkit.util.ClassTree;
import jdk.javadoc.internal.doclets.toolkit.util.DocFileIOException;
import jdk.javadoc.internal.doclets.toolkit.util.DocPath;
import jdk.javadoc.internal.doclets.toolkit.util.DocPaths;
import jdk.javadoc.internal.html.Content;
import jdk.javadoc.internal.html.ContentBuilder;
import jdk.javadoc.internal.html.HtmlTree;

/**
 * Generate Class Hierarchy page for all the Classes in this run.  Use
 * ClassTree for building the Tree. The name of
 * the generated file is "overview-tree.html" and it is generated in the
 * current or the destination directory.
 */
public class TreeWriter extends AbstractTreeWriter {

    /**
     * Packages in this run.
     */
    SortedSet<PackageElement> packages;

    /**
     * True if there are no packages specified on the command line,
     * False otherwise.
     */
    private final boolean classesOnly;

    protected BodyContents bodyContents;

    /**
     * Constructor to construct TreeWriter object.
     *
     * @param configuration the current configuration of the doclet
     * @param classTree the tree being built.
     */
    public TreeWriter(HtmlConfiguration configuration, ClassTree classTree) {
        super(configuration, DocPaths.OVERVIEW_TREE, classTree);
        packages = configuration.packages;
        classesOnly = packages.isEmpty();
        this.bodyContents = new BodyContents();
    }

    @Override
    public void buildPage() throws DocFileIOException {
        HtmlTree body = getBody();
        Content headContent = contents.hierarchyForAllPackages;
        var heading = HtmlTree.HEADING(Headings.PAGE_TITLE_HEADING,
                HtmlStyles.title, headContent);
        var div = HtmlTree.DIV(HtmlStyles.header, heading);
        Content mainContent = new ContentBuilder();
        mainContent.add(div);
        addPackageTreeLinks(mainContent);
        addTree(classTree.classes(), "doclet.Class_Hierarchy", mainContent);
        addTree(classTree.interfaces(), "doclet.Interface_Hierarchy", mainContent);
        addTree(classTree.annotationInterfaces(), "doclet.Annotation_Type_Hierarchy", mainContent);
        addTree(classTree.enumClasses(), "doclet.Enum_Hierarchy", mainContent);
        addTree(classTree.recordClasses(), "doclet.Record_Class_Hierarchy", mainContent);
        body.add(bodyContents
                .addMainContent(mainContent)
                .setFooter(getFooter()));
        printHtmlDocument(null, "class tree", body);
    }

    /**
     * Add the links to all the package tree files.
     *
     * @param content the content to which the links will be added
     */
    protected void addPackageTreeLinks(Content content) {
        //Do nothing if only unnamed package is used
        if (isUnnamedPackage()) {
            return;
        }
        if (!classesOnly) {
            var span = HtmlTree.SPAN(HtmlStyles.packageHierarchyLabel,
                    contents.packageHierarchies);
            content.add(span);
            var ul = HtmlTree.UL(HtmlStyles.horizontal).addStyle(HtmlStyles.contentsList);
            int i = 0;
            for (PackageElement pkg : packages) {
                // If the package name length is 0 or if -nodeprecated option
                // is set and the package is marked as deprecated, do not include
                // the page in the list of package hierarchies.
                if (pkg.isUnnamed() ||
                        (options.noDeprecated() && utils.isDeprecated(pkg))) {
                    i++;
                    continue;
                }
                DocPath link = pathString(pkg, DocPaths.PACKAGE_TREE);
                var li = HtmlTree.LI(links.createLink(link,
                        getLocalizedPackageName(pkg)));
                if (i < packages.size() - 1) {
                    li.add(", ");
                }
                ul.add(li);
                i++;
            }
            content.add(ul);
        }
    }

    /**
     * {@return a new HTML BODY element}
     */
    private HtmlTree getBody() {
        String title = resources.getText("doclet.Window_Class_Hierarchy");
        HtmlTree bodyTree = getBody(getWindowTitle(title));
        bodyContents.setHeader(getHeader(PageMode.TREE));
        return bodyTree;
    }

    private boolean isUnnamedPackage() {
        return packages.size() == 1 && packages.first().isUnnamed();
    }
}
