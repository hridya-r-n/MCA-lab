/*
 * Copyright (c) 2000, 2024, Oracle and/or its affiliates. All rights reserved.
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

package java.lang;

import jdk.internal.vm.annotation.IntrinsicCandidate;

/**
 * Utility class for string encoding and decoding.
 */
class StringCoding {

    private StringCoding() { }

    /**
     * Count the number of leading non-zero ascii chars in the range.
     */
    public static int countNonZeroAscii(String s) {
        byte[] value = s.value();
        if (s.isLatin1()) {
            return countNonZeroAsciiLatin1(value, 0, value.length);
        } else {
            return countNonZeroAsciiUTF16(value, 0, s.length());
        }
    }

    /**
     * Count the number of non-zero ascii chars in the range.
     */
    public static int countNonZeroAsciiLatin1(byte[] ba, int off, int len) {
        int limit = off + len;
        for (int i = off; i < limit; i++) {
            if (ba[i] <= 0) {
                return i - off;
            }
        }
        return len;
    }

    /**
     * Count the number of leading non-zero ascii chars in the range.
     */
    public static int countNonZeroAsciiUTF16(byte[] ba, int off, int strlen) {
        int limit = off + strlen;
        for (int i = off; i < limit; i++) {
            char c = StringUTF16.charAt(ba, i);
            if (c == 0 || c > 0x7F) {
                return i - off;
            }
        }
        return strlen;
    }

    public static boolean hasNegatives(byte[] ba, int off, int len) {
        return countPositives(ba, off, len) != len;
    }

    /**
     * Count the number of leading positive bytes in the range.
     *
     * @implSpec the implementation must return len if there are no negative
     *   bytes in the range. If there are negative bytes, the implementation must return
     *   a value that is less than or equal to the index of the first negative byte
     *   in the range.
     */
    @IntrinsicCandidate
    public static int countPositives(byte[] ba, int off, int len) {
        int limit = off + len;
        for (int i = off; i < limit; i++) {
            if (ba[i] < 0) {
                return i - off;
            }
        }
        return len;
    }

    @IntrinsicCandidate
    public static int implEncodeISOArray(byte[] sa, int sp,
                                         byte[] da, int dp, int len) {
        int i = 0;
        for (; i < len; i++) {
            char c = StringUTF16.getChar(sa, sp++);
            if (c > '\u00FF')
                break;
            da[dp++] = (byte)c;
        }
        return i;
    }

    @IntrinsicCandidate
    public static int implEncodeAsciiArray(char[] sa, int sp,
                                           byte[] da, int dp, int len)
    {
        int i = 0;
        for (; i < len; i++) {
            char c = sa[sp++];
            if (c >= '\u0080')
                break;
            da[dp++] = (byte)c;
        }
        return i;
    }

}
