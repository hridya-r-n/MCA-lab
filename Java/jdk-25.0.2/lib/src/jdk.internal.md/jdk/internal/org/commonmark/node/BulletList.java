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

public class BulletList extends ListBlock {

    private String marker;

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    /**
     * @return the bullet list marker that was used, e.g. {@code -}, {@code *} or {@code +}, if available, or null otherwise
     */
    public String getMarker() {
        return marker;
    }

    public void setMarker(String marker) {
        this.marker = marker;
    }

    /**
     * @deprecated use {@link #getMarker()} instead
     */
    @Deprecated
    public char getBulletMarker() {
        return marker != null && !marker.isEmpty() ? marker.charAt(0) : '\0';
    }

    /**
     * @deprecated use {@link #getMarker()} instead
     */
    @Deprecated
    public void setBulletMarker(char bulletMarker) {
        this.marker = bulletMarker != '\0' ? String.valueOf(bulletMarker) : null;
    }
}
