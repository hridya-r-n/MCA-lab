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

package jdk.javadoc.internal.doclets.toolkit;

import java.util.Objects;

import javax.lang.model.element.Element;
import javax.lang.model.element.ModuleElement;
import javax.lang.model.element.PackageElement;
import javax.tools.FileObject;

import jdk.javadoc.internal.doclets.toolkit.util.Utils;

/**
 * This is a pseudo-element wrapper for doc-files html contents, essentially to
 * associate the doc-files' html documentation's {@code DocCommentTree} to an element.
 */
public class DocFileElement implements DocletElement {

    private final Element element;
    private final PackageElement packageElement;
    private final FileObject fo;

    /**
     * Creates a pseudo-element that wraps a {@code doc-files} documentation file.
     *
     * @param utils the standard utilities class
     * @param element the module element or package element that "owns" the {@code doc-files} subdirectory
     * @param fo the file object
     *
     * @throws IllegalArgumentException if the given element is not a module element or package element
     */
    public DocFileElement(Utils utils, Element element, FileObject fo) {
        this.element = element;
        this.fo = fo;

        switch (element.getKind()) {
            case MODULE -> {
                ModuleElement moduleElement = (ModuleElement) element;
                packageElement = utils.elementUtils.getPackageElement(moduleElement, "");
            }

            case PACKAGE ->
                packageElement = (PackageElement) element;

            default -> throw new IllegalArgumentException(element.getKind() + ":" + element);
        }
    }

    /**
     * {@return the element that "owns" the {@code doc-files} directory}
     */
    public Element getElement() {
        return element;
    }

    @Override
    public PackageElement getPackageElement() {
        return packageElement;
    }

    @Override
    public FileObject getFileObject() {
        return fo;
    }

    @Override
    public Kind getSubKind() {
        return Kind.DOCFILE;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DocFileElement that = (DocFileElement) o;
        return element.equals(that.element) && fo.equals(that.fo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(element, fo);
    }
}

