/*
 * Copyright (c) 2003, Oracle and/or its affiliates. All rights reserved.
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

import java.awt.FontFormatException;
import java.awt.font.FontRenderContext;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

/*
 * This class should never be invoked on the windows implementation
 * So the constructor throws a FontFormatException, which is caught
 * and the font is ignored.
 */

public final class NativeFont extends PhysicalFont {

    /**
     * Verifies native font is accessible.
     * @throws FontFormatException if the font can't be located.
     */
    public NativeFont(String platName, boolean isBitmapDelegate)
        throws FontFormatException {

        throw new FontFormatException("NativeFont not used on Windows");
    }

    static boolean hasExternalBitmaps(String platName) {
        return false;
    }

    @Override
    public CharToGlyphMapper getMapper() {
        return null;
    }

    PhysicalFont getDelegateFont() {
        return null;
    }

    @Override
    FontStrike createStrike(FontStrikeDesc desc) {
        return null;
    }

    public Rectangle2D getMaxCharBounds(FontRenderContext frc) {
        return null;
    }

    @Override
    StrikeMetrics getFontMetrics(long pScalerContext) {
        return null;
    }

    @Override
    public GeneralPath getGlyphOutline(long pScalerContext,
                                       int glyphCode,
                                       float x, float y) {
        return null;
    }

    @Override
    public  GeneralPath getGlyphVectorOutline(long pScalerContext,
                                              int[] glyphs, int numGlyphs,
                                              float x, float y) {
        return null;
    }


    @Override
    long getGlyphImage(long pScalerContext, int glyphCode) {
        return 0L;
    }


    @Override
    void getGlyphMetrics(long pScalerContext, int glyphCode,
                         Point2D.Float metrics) {
    }


    @Override
    float getGlyphAdvance(long pScalerContext, int glyphCode) {
        return 0f;
    }

    @Override
    Rectangle2D.Float getGlyphOutlineBounds(long pScalerContext,
                                            int glyphCode) {
        return new Rectangle2D.Float(0f, 0f, 0f, 0f);
    }
}
