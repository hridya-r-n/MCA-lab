/*
 * Copyright (c) 2015, 2025, Oracle and/or its affiliates. All rights reserved.
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

package sun.security.util;

import sun.security.x509.AlgorithmId;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.PEMRecord;
import java.security.Security;
import java.util.Arrays;
import java.util.Base64;
import java.util.HexFormat;
import java.util.Objects;
import java.util.regex.Pattern;

/**
 * A utility class for PEM format encoding.
 */
public class Pem {
    private static final char WS = 0x20;  // Whitespace
    private static final byte[] CRLF = new byte[] {'\r', '\n'};

    // Default algorithm from jdk.epkcs8.defaultAlgorithm in java.security
    public static final String DEFAULT_ALGO;

    // Pattern matching for EKPI operations
    private static final Pattern PBE_PATTERN;

    // Pattern matching for stripping whitespace.
    private static final Pattern STRIP_WHITESPACE_PATTERN;

    // Lazy initialized PBES2 OID value
    private static ObjectIdentifier PBES2OID;

    // Lazy initialized singleton encoder.
    private static Base64.Encoder b64Encoder;

    static {
        String algo = Security.getProperty("jdk.epkcs8.defaultAlgorithm");
        DEFAULT_ALGO = (algo == null || algo.isBlank()) ?
            "PBEWithHmacSHA256AndAES_128" : algo;
        PBE_PATTERN = Pattern.compile("^PBEWith.*And.*",
            Pattern.CASE_INSENSITIVE);
        STRIP_WHITESPACE_PATTERN = Pattern.compile("\\s+");
    }

    public static final String CERTIFICATE = "CERTIFICATE";
    public static final String X509_CRL = "X509 CRL";
    public static final String ENCRYPTED_PRIVATE_KEY = "ENCRYPTED PRIVATE KEY";
    public static final String PRIVATE_KEY = "PRIVATE KEY";
    public static final String RSA_PRIVATE_KEY = "RSA PRIVATE KEY";
    public static final String PUBLIC_KEY = "PUBLIC KEY";
    // old PEM types per RFC 7468
    public static final String X509_CERTIFICATE = "X509 CERTIFICATE";
    public static final String X_509_CERTIFICATE = "X.509 CERTIFICATE";
    public static final String CRL = "CRL";

    /**
     * Decodes a PEM-encoded block.
     *
     * @param input the input string, according to RFC 1421, can only contain
     *              characters in the base-64 alphabet and whitespaces.
     * @return the decoded bytes
     */
    public static byte[] decode(String input) {
        byte[] src = STRIP_WHITESPACE_PATTERN.matcher(input).replaceAll("").
            getBytes(StandardCharsets.ISO_8859_1);
        return Base64.getDecoder().decode(src);
    }

    /**
     * Return the OID for a given PBE algorithm.  PBES1 has an OID for each
     * algorithm, while PBES2 has one OID for everything that complies with
     * the formatting.  Therefore, if the algorithm is not PBES1, it will
     * return PBES2.  Cipher will determine if this is a valid PBE algorithm.
     * PBES2 specifies AES as the cipher algorithm, but any block cipher could
     * be supported.
     */
    public static ObjectIdentifier getPBEID(String algorithm) {

        // Verify pattern matches PBE Standard Name spec
        if (!PBE_PATTERN.matcher(algorithm).matches()) {
            throw new IllegalArgumentException("Invalid algorithm format.");
        }

        // Return the PBES1 OID if it matches
        try {
            return AlgorithmId.get(algorithm).getOID();
        } catch (NoSuchAlgorithmException e) {
            // fall-through
        }

        // Lazy initialize
        if (PBES2OID == null) {
            try {
                // Set to the hardcoded OID in KnownOID.java
                PBES2OID = AlgorithmId.get("PBES2").getOID();
            } catch (NoSuchAlgorithmException e) {
                // Should never fail.
                throw new IllegalArgumentException(e);
            }
        }
        return PBES2OID;
    }

    /*
     * RFC 7468 has some rules what generators should return given a historical
     * type name.  This converts read in PEM to the RFC.  Change the type to
     * be uniform is likely to help apps from not using all 3 certificate names.
     */
    private static String typeConverter(String type) {
        return switch (type) {
            case Pem.X509_CERTIFICATE, Pem.X_509_CERTIFICATE -> Pem.CERTIFICATE;
            case Pem.CRL -> Pem.X509_CRL;
            default -> type;
        };
    }

    /**
     * Read the PEM text and return it in it's three components:  header,
     * base64, and footer.
     *
     * The method will leave the stream after reading the end of line of the
     * footer or end of file
     * @param is an InputStream
     * @param shortHeader if true, the hyphen length is 4 because the first
     *                    hyphen is assumed to have been read.  This is needed
     *                    for the CertificateFactory X509 implementation.
     * @return a new PEMRecord
     * @throws IOException on IO errors or PEM syntax errors that leave
     * the read position not at the end of a PEM block
     * @throws EOFException when at the unexpected end of the stream
     * @throws IllegalArgumentException when a PEM syntax error occurs,
     * but the read position in the stream is at the end of the block, so
     * future reads can be successful.
     */
    public static PEMRecord readPEM(InputStream is, boolean shortHeader)
        throws IOException {
        Objects.requireNonNull(is);

        int hyphen = (shortHeader ? 1 : 0);
        int eol = 0;

        ByteArrayOutputStream os = new ByteArrayOutputStream(6);
        // Find starting hyphens
        do {
            int d = is.read();
            switch (d) {
                case '-' -> hyphen++;
                case -1 -> {
                    if (os.size() == 0) {
                        throw new EOFException("No data available");
                    }
                    throw new EOFException("No PEM data found");
                }
                default -> hyphen = 0;
            }
            os.write(d);
        } while (hyphen != 5);

        StringBuilder sb = new StringBuilder(64);
        sb.append("-----");
        hyphen = 0;
        int c;

        // Get header definition until first hyphen
        do {
            switch (c = is.read()) {
                case '-' -> hyphen++;
                case -1 -> throw new EOFException("Input ended prematurely");
                case '\n', '\r' -> throw new IOException("Incomplete header");
                default -> sb.append((char) c);
            }
        } while (hyphen == 0);

        // Verify header ending with 5 hyphens.
        do {
            switch (is.read()) {
                case '-' -> hyphen++;
                default ->
                    throw new IOException("Incomplete header");
            }
        } while (hyphen < 5);

        sb.append("-----");
        String header = sb.toString();
        if (header.length() < 16 || !header.startsWith("-----BEGIN ") ||
            !header.endsWith("-----")) {
            throw new IOException("Illegal header: " + header);
        }

        hyphen = 0;
        sb = new StringBuilder(1024);

        // Determine the line break using the char after the last hyphen
        switch (is.read()) {
            case WS -> {} // skip whitespace
            case '\r' -> {
                c = is.read();
                if (c == '\n') {
                    eol = '\n';
                } else {
                    eol = '\r';
                    sb.append((char) c);
                }
            }
            case '\n' -> eol = '\n';
            default ->
                throw new IOException("No EOL character found");
        }

        // Read data until we find the first footer hyphen.
        do {
            switch (c = is.read()) {
                case -1 ->
                    throw new EOFException("Incomplete header");
                case '-' -> hyphen++;
                case WS, '\t', '\r', '\n' -> {} // skip whitespace and tab
                default -> sb.append((char) c);
            }
        } while (hyphen == 0);

        String data = sb.toString();

        // Verify footer starts with 5 hyphens.
        do {
            switch (is.read()) {
                case '-' -> hyphen++;
                case -1 -> throw new EOFException("Input ended prematurely");
                default -> throw new IOException("Incomplete footer");
            }
        } while (hyphen < 5);

        hyphen = 0;
        sb = new StringBuilder(64);
        sb.append("-----");

        // Look for Complete header by looking for the end of the hyphens
        do {
            switch (c = is.read()) {
                case '-' -> hyphen++;
                case -1 -> throw new EOFException("Input ended prematurely");
                default -> sb.append((char) c);
            }
        } while (hyphen == 0);

        // Verify ending with 5 hyphens.
        do {
            switch (is.read()) {
                case '-' -> hyphen++;
                case -1 -> throw new EOFException("Input ended prematurely");
                default -> throw new IOException("Incomplete footer");
            }
        } while (hyphen < 5);

        while ((c = is.read()) != eol && c != -1 && c != WS) {
            // skip when eol is '\n', the line separator is likely "\r\n".
            if (c == '\r') {
                continue;
            }
            throw new IOException("Invalid PEM format:  " +
                "No EOL char found in footer:  0x" +
                HexFormat.of().toHexDigits((byte) c));
        }

        sb.append("-----");
        String footer = sb.toString();
        if (footer.length() < 14 || !footer.startsWith("-----END ") ||
            !footer.endsWith("-----")) {
            // Not an IOE because the read pointer is correctly at the end.
            throw new IOException("Illegal footer: " + footer);
        }

        // Verify the object type in the header and the footer are the same.
        String headerType = header.substring(11, header.length() - 5);
        String footerType = footer.substring(9, footer.length() - 5);
        if (!headerType.equals(footerType)) {
            throw new IOException("Header and footer do not " +
                "match: " + headerType + " " + footerType);
        }

        // If there was data before finding the 5 dashes of the PEM header,
        // backup 5 characters and save that data.
        byte[] preData = null;
        if (os.size() > 5) {
            preData = Arrays.copyOf(os.toByteArray(), os.size() - 5);
        }

        return new PEMRecord(typeConverter(headerType), data, preData);
    }

    public static PEMRecord readPEM(InputStream is) throws IOException {
        return readPEM(is, false);
    }

    private static String pemEncoded(String type, String base64) {
        return
            "-----BEGIN " + type + "-----\r\n" +
            base64 + (!base64.endsWith("\n") ? "\r\n" : "") +
            "-----END " + type + "-----\r\n";
    }

    /**
     * Construct a String-based encoding based off the type.  leadingData
     * is not used with this method.
     * @return PEM in a string
     */
    public static String pemEncoded(String type, byte[] der) {
        if (b64Encoder == null) {
            b64Encoder = Base64.getMimeEncoder(64, CRLF);
        }
        return pemEncoded(type, b64Encoder.encodeToString(der));
    }

    /**
     * Construct a String-based encoding based off the type.  leadingData
     * is not used with this method.
     * @return PEM in a string
     */
    public static String pemEncoded(PEMRecord pem) {
        String p = pem.content().replaceAll("(.{64})", "$1\r\n");
        return pemEncoded(pem.type(), p);
    }
}
