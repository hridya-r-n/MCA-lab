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

package jdk.internal.org.commonmark.node;

/**
 * Node visitor.
 * <p>
 * Implementations should subclass {@link AbstractVisitor} instead of implementing this directly.
 */
public interface Visitor {

    void visit(BlockQuote blockQuote);

    void visit(BulletList bulletList);

    void visit(Code code);

    void visit(Document document);

    void visit(Emphasis emphasis);

    void visit(FencedCodeBlock fencedCodeBlock);

    void visit(HardLineBreak hardLineBreak);

    void visit(Heading heading);

    void visit(ThematicBreak thematicBreak);

    void visit(HtmlInline htmlInline);

    void visit(HtmlBlock htmlBlock);

    void visit(Image image);

    void visit(IndentedCodeBlock indentedCodeBlock);

    void visit(Link link);

    void visit(ListItem listItem);

    void visit(OrderedList orderedList);

    void visit(Paragraph paragraph);

    void visit(SoftLineBreak softLineBreak);

    void visit(StrongEmphasis strongEmphasis);

    void visit(Text text);

    void visit(LinkReferenceDefinition linkReferenceDefinition);

    void visit(CustomBlock customBlock);

    void visit(CustomNode customNode);
}
