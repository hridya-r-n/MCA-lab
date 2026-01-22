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

package jdk.internal.org.commonmark.internal;

import jdk.internal.org.commonmark.internal.util.Escaping;
import jdk.internal.org.commonmark.node.LinkReferenceDefinition;

import java.util.LinkedHashMap;
import java.util.Map;

public class LinkReferenceDefinitions {

    // LinkedHashMap for determinism and to preserve document order
    private final Map<String, LinkReferenceDefinition> definitions = new LinkedHashMap<>();

    public void add(LinkReferenceDefinition definition) {
        String normalizedLabel = Escaping.normalizeLabelContent(definition.getLabel());

        // spec: When there are multiple matching link reference definitions, the first is used
        if (!definitions.containsKey(normalizedLabel)) {
            definitions.put(normalizedLabel, definition);
        }
    }

    public LinkReferenceDefinition get(String label) {
        String normalizedLabel = Escaping.normalizeLabelContent(label);
        return definitions.get(normalizedLabel);
    }
}
