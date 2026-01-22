/*
 * Copyright (c) 2011, 2024, Oracle and/or its affiliates. All rights reserved.
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

package sun.swing;

import java.awt.Component;
import java.awt.Container;
import java.awt.Window;

import javax.swing.AbstractButton;
import javax.swing.JLabel;
import javax.swing.UIManager;

public final class MnemonicHandler {

    private static boolean isMnemonicHidden;

    private MnemonicHandler() {}

    /**
     * Gets the state of the hide mnemonic flag.
     * This only has meaning if this feature is supported by the underlying OS.
     *
     * @return true if mnemonics are hidden, otherwise, false
     */
    public static boolean isMnemonicHidden() {
        if (UIManager.getBoolean("Button.showMnemonics")) {
            // Do not hide mnemonics if the UI defaults do not support this
            isMnemonicHidden = false;
        }
        return isMnemonicHidden;
    }

    /**
     * Sets the state of the hide mnemonic flag. This flag is used by the
     * component UI delegates to determine if the mnemonic should be rendered.
     * This method is a non operation if the underlying operating system
     * does not support the mnemonic hiding feature.
     *
     * @param hide true if mnemonics should be hidden
     */
    public static void setMnemonicHidden(final boolean hide) {
        if (UIManager.getBoolean("Button.showMnemonics")) {
            // Do not hide mnemonics if the UI defaults do not support this
            isMnemonicHidden = false;
        } else {
            isMnemonicHidden = hide;
        }
    }

    /**
     * Repaints all the components with the mnemonics in the given window and all its owned windows.
     */
    public static void repaintMnemonicsInWindow(final Window w) {
        if (w == null || !w.isShowing()) {
            return;
        }

        final Window[] ownedWindows = w.getOwnedWindows();
        for (final Window element : ownedWindows) {
            repaintMnemonicsInWindow(element);
        }

        repaintMnemonicsInContainer(w);
    }

    /**
     * Repaints all the components with the mnemonics in container.
     * Recursively searches for all the subcomponents.
     */
    private static void repaintMnemonicsInContainer(final Container cont) {
        final Component[] elements = cont.getComponents();
        for (final Component c : elements) {
            if (c == null || !c.isVisible()) {
                continue;
            }

            if ((c instanceof AbstractButton b && b.getMnemonic() != '\0')
                || (c instanceof JLabel l && l.getDisplayedMnemonic() != '\0')) {
                c.repaint();
            } else if (c instanceof Container) {
                repaintMnemonicsInContainer((Container) c);
            }
        }
    }
}
