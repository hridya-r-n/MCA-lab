/*
 * Copyright (c) 2021, Oracle and/or its affiliates. All rights reserved.
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

package sun.net.httpserver;

/**
 * Provides utility methods for checking header field names and quoted strings.
 */
public class Utils {

    // ABNF primitives defined in RFC 7230
    private static final boolean[] TCHAR = new boolean[256];
    private static final boolean[] QDTEXT = new boolean[256];
    private static final boolean[] QUOTED_PAIR = new boolean[256];

    static {
        char[] allowedTokenChars =
                ("!#$%&'*+-.^_`|~0123456789" +
                        "abcdefghijklmnopqrstuvwxyz" +
                        "ABCDEFGHIJKLMNOPQRSTUVWXYZ").toCharArray();
        for (char c : allowedTokenChars) {
            TCHAR[c] = true;
        }
        for (char c = 0x20; c <= 0xFF; c++) {
            QDTEXT[c] = true;
        }
        QDTEXT[0x22] = false;  // (")   illegal
        QDTEXT[0x5c] = false;  // (\)   illegal
        QDTEXT[0x7F] = false;  // (DEL) illegal

        for (char c = 0x20; c <= 0xFF; c++) {
            QUOTED_PAIR[c] = true;
        }
        QUOTED_PAIR[0x09] = true;  // (\t)    legal
        QUOTED_PAIR[0x7F] = false; // (DEL) illegal
    }

    /*
     * Validates an RFC 7230 field-name.
     */
    public static boolean isValidName(String token) {
        for (int i = 0; i < token.length(); i++) {
            char c = token.charAt(i);
            if (c > 255 || !TCHAR[c]) {
                return false;
            }
        }
        return !token.isEmpty();
    }

    /*
     * Validates an RFC 7230 quoted-string.
     */
    public static boolean isQuotedStringContent(String token) {
        for (int i = 0; i < token.length(); i++) {
            char c = token.charAt(i);
            if (c > 255) {
                return false;
            } else if (c == 0x5c) {  // check if valid quoted-pair
                if (i == token.length() - 1 || !QUOTED_PAIR[token.charAt(i++)]) {
                    return false;
                }
            } else if (!QDTEXT[c]) {
                return false; // illegal char
            }
        }
        return true;
    }

    /* Throw IAE if illegal character found.  isValue is true if String is
     * a value. Otherwise it is header name
     */
    public static void checkHeader(String str, boolean isValue) {
        int len = str.length();
        for (int i=0; i<len; i++) {
            char c = str.charAt(i);
            if (c == '\r') {
                if (!isValue) {
                    throw new IllegalArgumentException("Illegal CR found in header");
                }
                // is allowed if it is followed by \n and a whitespace char
                if (i >= len - 2) {
                    throw new IllegalArgumentException("Illegal CR found in header");
                }
                char c1 = str.charAt(i+1);
                char c2 = str.charAt(i+2);
                if (c1 != '\n') {
                    throw new IllegalArgumentException("Illegal char found after CR in header");
                }
                if (c2 != ' ' && c2 != '\t') {
                    throw new IllegalArgumentException("No whitespace found after CRLF in header");
                }
                i+=2;
            } else if (c == '\n') {
                throw new IllegalArgumentException("Illegal LF found in header");
            } else if (c > 255) {
                throw new IllegalArgumentException("Illegal character found in header");
            }
        }
    }

}
