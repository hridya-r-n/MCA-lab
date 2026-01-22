/*
 * Copyright (c) 2003, 2025, Oracle and/or its affiliates. All rights reserved.
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

package sun.font;

/*
 * This isn't a critical performance case, so don't do any
 * char->glyph map caching for Type1 fonts. The ones that are used
 * in composites will be cached there.
 */

import static sun.font.FontUtilities.isDefaultIgnorable;
import static sun.font.FontUtilities.isIgnorableWhitespace;

public final class Type1GlyphMapper extends CharToGlyphMapper {

    Type1Font font;
    FontScaler scaler;

    public Type1GlyphMapper(Type1Font font) {
        this.font = font;
        initMapper();
    }

    private void initMapper() {
        scaler = font.getScaler();
        try {
          missingGlyph = scaler.getMissingGlyphCode();
        } catch (FontScalerException fe) {
            scaler = FontScaler.getNullScaler();
            try {
                missingGlyph = scaler.getMissingGlyphCode();
            } catch (FontScalerException e) { //should not happen
                missingGlyph = 0;
            }
        }
    }

    public int getNumGlyphs() {
        try {
            return scaler.getNumGlyphs();
        } catch (FontScalerException e) {
            scaler = FontScaler.getNullScaler();
            return getNumGlyphs();
        }
    }

    public int getMissingGlyphCode() {
        return missingGlyph;
    }

    public boolean canDisplay(char ch) {
        try {
            return scaler.getGlyphCode(ch) != missingGlyph;
        } catch(FontScalerException e) {
            scaler = FontScaler.getNullScaler();
            return canDisplay(ch);
        }
    }

    public int charToGlyph(char ch) {
        if (isIgnorableWhitespace(ch) || isDefaultIgnorable(ch)) { // raw = false
            return INVISIBLE_GLYPH_ID;
        }
        try {
            return scaler.getGlyphCode(ch);
        } catch (FontScalerException e) {
            scaler = FontScaler.getNullScaler();
            return charToGlyph(ch);
        }
    }

    public int charToGlyph(int ch) {
        int glyph = charToGlyph(ch, false);
        return glyph;
    }

    public int charToGlyphRaw(int ch) {
        int glyph = charToGlyph(ch, true);
        return glyph;
    }

    private int charToGlyph(int ch, boolean raw) {
        if (ch < 0 || ch > 0xffff) {
            return missingGlyph;
        } else {
            if (isIgnorableWhitespace(ch) || (isDefaultIgnorable(ch) && !raw)) {
                return INVISIBLE_GLYPH_ID;
            }
            try {
                return scaler.getGlyphCode((char)ch);
            } catch (FontScalerException e) {
                scaler = FontScaler.getNullScaler();
                return charToGlyph(ch);
            }
        }
    }

    public void charsToGlyphs(int count, char[] unicodes, int[] glyphs) {
        /* The conversion into surrogates is misleading.
         * The Type1 glyph mapper only accepts 16 bit unsigned shorts.
         * If its > not in the range it can use assign the missing glyph.
         */
        for (int i=0; i<count; i++) {
            int code = unicodes[i]; // char is unsigned.

            if (code >= HI_SURROGATE_START &&
                code <= HI_SURROGATE_END && i < count - 1) {
                char low = unicodes[i + 1];

                if (low >= LO_SURROGATE_START &&
                    low <= LO_SURROGATE_END) {
                    code = (code - HI_SURROGATE_START) *
                        0x400 + low - LO_SURROGATE_START + 0x10000;
                    glyphs[i + 1] = INVISIBLE_GLYPH_ID;
                }
            }
            glyphs[i] = charToGlyph(code);
            if (code >= 0x10000) {
                i += 1; // Empty glyph slot after surrogate
            }
        }
    }

    public void charsToGlyphs(int count, int[] unicodes, int[] glyphs) {
        /* I believe this code path is never exercised. Its there mainly
         * for surrogates and/or the opentype engine which aren't likely
         * to be an issue for Type1 fonts. So no need to optimise it.
         */
        for (int i=0; i<count; i++) {
            glyphs[i] = charToGlyph(unicodes[i]);
        }
    }


    /* This variant checks if shaping is needed and immediately
     * returns true if it does. A caller of this method should be expecting
     * to check the return type because it needs to know how to handle
     * the character data for display.
     */
    public boolean charsToGlyphsNS(int count, char[] unicodes, int[] glyphs) {

        for (int i=0; i<count; i++) {
            int code = unicodes[i]; // char is unsigned.

            if (code >= HI_SURROGATE_START &&
                code <= HI_SURROGATE_END && i < count - 1) {
                char low = unicodes[i + 1];

                if (low >= LO_SURROGATE_START &&
                    low <= LO_SURROGATE_END) {
                    code = (code - HI_SURROGATE_START) *
                        0x400 + low - LO_SURROGATE_START + 0x10000;
                    glyphs[i + 1] = INVISIBLE_GLYPH_ID;
                }
            }

            glyphs[i] = charToGlyph(code);

            if (code < FontUtilities.MIN_LAYOUT_CHARCODE) {
                continue;
            }
            else if (FontUtilities.isComplexCharCode(code)) {
                return true;
            }
            else if (code >= 0x10000) {
                i += 1; // Empty glyph slot after surrogate
                continue;
            }
        }

        return false;
    }
}
