/*
 * Copyright (c) 2024, Alibaba Group Holding Limited. All Rights Reserved.
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

package jdk.internal.util;

import jdk.internal.vm.annotation.ForceInline;

/**
 * Helper to JDK UTF putChar and Calculate length
 *
 * @since 24
 */
public abstract class ModifiedUtf {
    private ModifiedUtf() {
    }

    @ForceInline
    public static int putChar(byte[] buf, int offset, char c) {
        if (c != 0 && c < 0x80) {
            buf[offset++] = (byte) c;
        } else if (c >= 0x800) {
            buf[offset    ] = (byte) (0xE0 | c >> 12 & 0x0F);
            buf[offset + 1] = (byte) (0x80 | c >> 6  & 0x3F);
            buf[offset + 2] = (byte) (0x80 | c       & 0x3F);
            offset += 3;
        } else {
            buf[offset    ] = (byte) (0xC0 | c >> 6 & 0x1F);
            buf[offset + 1] = (byte) (0x80 | c      & 0x3F);
            offset += 2;
        }
        return offset;
    }

    /**
     * Calculate the utf length of a string
     * @param str input string
     * @param countNonZeroAscii the number of non-zero ascii characters in the prefix calculated by JLA.countNonZeroAscii(str)
     */
    @ForceInline
    public static int utfLen(String str, int countNonZeroAscii) {
        int utflen = str.length();
        for (int i = utflen - 1; i >= countNonZeroAscii; i--) {
            int c = str.charAt(i);
            if (c >= 0x80 || c == 0)
                utflen += (c >= 0x800) ? 2 : 1;
        }
        return utflen;
    }
}
