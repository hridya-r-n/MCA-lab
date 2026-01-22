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

public abstract class ListBlock extends Block {

    private boolean tight;

    /**
     * @return whether this list is tight or loose
     * @see <a href="https://spec.commonmark.org/0.28/#tight">CommonMark Spec for tight lists</a>
     */
    public boolean isTight() {
        return tight;
    }

    public void setTight(boolean tight) {
        this.tight = tight;
    }

}
