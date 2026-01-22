/*
 * Copyright (c) 2022, 2025, Oracle and/or its affiliates. All rights reserved.
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

package java.lang.classfile.attribute;

import java.lang.classfile.Attribute;
import java.lang.classfile.AttributeMapper;
import java.lang.classfile.AttributeMapper.AttributeStability;
import java.lang.classfile.Attributes;
import java.lang.classfile.ClassFile;
import java.lang.classfile.CodeBuilder;
import java.lang.classfile.CodeModel;
import java.lang.classfile.instruction.CharacterRange;
import java.util.List;

import jdk.internal.classfile.impl.BoundAttribute;
import jdk.internal.classfile.impl.UnboundAttribute;

/**
 * Models the {@link Attributes#characterRangeTable() CharacterRangeTable}
 * attribute, which is a bidirectional mapping from ranges of positions in the
 * source file to ranges of indices into the {@code code} array.  Its entries
 * are delivered as {@link CharacterRange}s when traversing the elements of a
 * {@link CodeModel}, toggled by {@link ClassFile.DebugElementsOption}.
 * <p>
 * The {@code CharacterRangeTable} attribute consists of an array of {@linkplain
 * CharacterRangeInfo character range entries}.  The character range entries
 * form a forest data structure: any two range entries are either disjoint, or
 * if they overlap, then one entry must be enclosed within the other, both in
 * {@code code} array indices and source file character positions.  The
 * character range entries may appear in any order.
 * <p>
 * This attribute only appears on {@code Code} attributes, permits multiple
 * appearances but should only {@linkplain AttributeMapper#allowMultiple()
 * appear once} in a {@code Code} attribute.  It has a data dependency on
 * {@linkplain AttributeStability#LABELS labels}.
 * <p>
 * This attribute cannot be sent to a {@link CodeBuilder}; its entries can be
 * constructed with {@link CharacterRange}, resulting in at most one
 * attribute instance in the built {@code Code} attribute.
 * <p>
 * This attribute is not predefined in the Java SE Platform.  This is a
 * JDK-specific nonstandard attribute produced by the reference implementation
 * of the system Java compiler, defined by the {@code jdk.compiler} module.
 *
 * @see Attributes#characterRangeTable()
 * @see CompilationIDAttribute
 * @see SourceIDAttribute
 * @since 24
 */
public sealed interface CharacterRangeTableAttribute
        extends Attribute<CharacterRangeTableAttribute>
        permits BoundAttribute.BoundCharacterRangeTableAttribute,
                UnboundAttribute.UnboundCharacterRangeTableAttribute {

    /**
     * {@return the entries of the character range table}
     */
    List<CharacterRangeInfo> characterRangeTable();

    /**
     * {@return a {@code CharacterRangeTable} attribute}
     *
     * @apiNote
     * The created attribute cannot be written to a {@link CodeBuilder}.  Use
     * {@link CodeBuilder#characterRange CodeBuilder::characterRange} instead.
     *
     * @param ranges the descriptions of the character ranges
     */
    static CharacterRangeTableAttribute of(List<CharacterRangeInfo> ranges) {
        return new UnboundAttribute.UnboundCharacterRangeTableAttribute(ranges);
    }
}

