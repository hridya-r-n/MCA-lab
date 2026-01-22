/*
 * Copyright (c) 1997, 2006, Oracle and/or its affiliates. All rights reserved.
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

package com.sun.java.swing.plaf.windows;

import java.awt.*;

import javax.swing.AbstractButton;
import javax.swing.JComponent;
import javax.swing.JToggleButton;
import javax.swing.UIDefaults;
import javax.swing.UIManager;

import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

import javax.swing.plaf.*;

import javax.swing.plaf.basic.BasicBorders;
import javax.swing.plaf.basic.BasicToolBarUI;

import static com.sun.java.swing.plaf.windows.TMSchema.Part;


public final class WindowsToolBarUI extends BasicToolBarUI {

    public static ComponentUI createUI(JComponent c) {
        return new WindowsToolBarUI();
    }

    @Override
    protected void installDefaults() {
        if (XPStyle.getXP() != null) {
            setRolloverBorders(true);
        }
        super.installDefaults();
    }

    @Override
    protected Border createRolloverBorder() {
        if (XPStyle.getXP() != null) {
            return new EmptyBorder(3, 3, 3, 3);
        } else {
            return super.createRolloverBorder();
        }
    }

    @Override
    protected Border createNonRolloverBorder() {
        if (XPStyle.getXP() != null) {
            return new EmptyBorder(3, 3, 3, 3);
        } else {
            return super.createNonRolloverBorder();
        }
    }

    @Override
    public void paint(Graphics g, JComponent c) {
        XPStyle xp = XPStyle.getXP();
        if (xp != null) {
            xp.getSkin(c, Part.TP_TOOLBAR).paintSkin(g, 0, 0,
                        c.getWidth(), c.getHeight(), null, true);
        } else {
            super.paint(g, c);
        }
    }

    /**
     * {@inheritDoc}
     * @since 1.6
     */
    @Override
    protected Border getRolloverBorder(AbstractButton b) {
        XPStyle xp = XPStyle.getXP();
        if (xp != null) {
            return xp.getBorder(b, WindowsButtonUI.getXPButtonType(b));
        } else {
            return super.getRolloverBorder(b);
        }
    }
}
