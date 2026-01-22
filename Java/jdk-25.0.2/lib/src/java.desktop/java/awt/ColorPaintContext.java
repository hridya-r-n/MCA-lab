/*
 * Copyright (c) 1997, 2025, Oracle and/or its affiliates. All rights reserved.
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

package java.awt;

import java.awt.image.ColorModel;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import java.util.Arrays;

import sun.awt.image.IntegerComponentRaster;

final class ColorPaintContext implements PaintContext {

    private final int color;
    private volatile WritableRaster savedTile;

    ColorPaintContext(int color) {
        this.color = color;
    }

    @Override
    public void dispose() {
    }

    @Override
    public ColorModel getColorModel() {
        return ColorModel.getRGBdefault();
    }

    @Override
    public Raster getRaster(int x, int y, int w, int h) {
        WritableRaster t = savedTile;

        if (t == null || w > t.getWidth() || h > t.getHeight()) {
            t = getColorModel().createCompatibleWritableRaster(w, h);
            IntegerComponentRaster icr = (IntegerComponentRaster) t;
            Arrays.fill(icr.getDataStorage(), color);
            // Note - icr.markDirty() is unnecessary since icr is brand new
            if (w <= 64 && h <= 64) {
                savedTile = t;
            }
        }
        return t;
    }
}
