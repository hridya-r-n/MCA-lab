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

package jdk.internal.org.commonmark.internal.util;

import java.util.BitSet;

public class AsciiMatcher implements CharMatcher {
    private final BitSet set;

    private AsciiMatcher(Builder builder) {
        this.set = builder.set;
    }

    @Override
    public boolean matches(char c) {
        return set.get(c);
    }

    public Builder newBuilder() {
        return new Builder((BitSet) set.clone());
    }

    public static Builder builder() {
        return new Builder(new BitSet());
    }

    public static class Builder {
        private final BitSet set;

        private Builder(BitSet set) {
            this.set = set;
        }

        public Builder c(char c) {
            if (c > 127) {
                throw new IllegalArgumentException("Can only match ASCII characters");
            }
            set.set(c);
            return this;
        }

        public Builder range(char from, char toInclusive) {
            for (char c = from; c <= toInclusive; c++) {
                c(c);
            }
            return this;
        }

        public AsciiMatcher build() {
            return new AsciiMatcher(this);
        }
    }
}
