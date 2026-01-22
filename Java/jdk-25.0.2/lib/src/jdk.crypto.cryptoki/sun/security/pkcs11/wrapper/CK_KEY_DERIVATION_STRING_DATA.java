/*
 * Copyright (c) 2025, Red Hat, Inc.
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

package sun.security.pkcs11.wrapper;

/**
 * class CK_KEY_DERIVATION_STRING_DATA provides the parameters to the
 * CKM_DES_ECB_ENCRYPT_DATA, CKM_DES3_ECB_ENCRYPT_DATA,
 * CKM_AES_ECB_ENCRYPT_DATA, CKM_CONCATENATE_BASE_AND_DATA,
 * CKM_CONCATENATE_DATA_AND_BASE, CKM_XOR_BASE_AND_DATA,
 * CKM_CAMELLIA_ECB_ENCRYPT_DATA, CKM_ARIA_ECB_ENCRYPT_DATA and
 * CKM_SEED_ECB_ENCRYPT_DATA mechanisms.<p>
 * <b>PKCS#11 structure:</b>
 * <pre>
 * typedef struct CK_KEY_DERIVATION_STRING_DATA {
 *     CK_BYTE_PTR pData;
 *     CK_ULONG ulLen;
 * } CK_KEY_DERIVATION_STRING_DATA;
 * </pre>
 *
 */
public class CK_KEY_DERIVATION_STRING_DATA {

    /**
     * <b>PKCS#11:</b>
     * <pre>
     *   CK_BYTE_PTR pData;
     *   CK_ULONG ulLen;
     * </pre>
     */
    public final byte[] pData;

    public CK_KEY_DERIVATION_STRING_DATA(byte[] pData) {
        this.pData = pData;
    }

    /**
     * Returns the string representation of CK_KEY_DERIVATION_STRING_DATA.
     *
     * @return the string representation of CK_KEY_DERIVATION_STRING_DATA
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(Constants.INDENT);
        sb.append("pData: ");
        sb.append(Functions.toHexString(pData));
        sb.append(Constants.NEWLINE);

        sb.append(Constants.INDENT);
        sb.append("ulLen: ");
        sb.append(Functions.getLength(pData));
        sb.append(Constants.NEWLINE);

        return sb.toString();
    }
}
