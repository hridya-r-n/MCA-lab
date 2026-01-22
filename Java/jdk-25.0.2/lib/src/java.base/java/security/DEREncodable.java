/*
 * Copyright (c) 2025, Oracle and/or its affiliates. All rights reserved.
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

package java.security;

import jdk.internal.javac.PreviewFeature;

import javax.crypto.EncryptedPrivateKeyInfo;
import java.security.cert.X509CRL;
import java.security.cert.X509Certificate;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * This interface is implemented by security API classes that contain
 * binary-encodable key or certificate material.
 * These APIs or their subclasses typically provide methods to convert
 * their instances to and from byte arrays in the Distinguished
 * Encoding Rules (DER) format.
 *
 * @see AsymmetricKey
 * @see KeyPair
 * @see PKCS8EncodedKeySpec
 * @see X509EncodedKeySpec
 * @see EncryptedPrivateKeyInfo
 * @see X509Certificate
 * @see X509CRL
 * @see PEMRecord
 *
 * @since 25
 */

@PreviewFeature(feature = PreviewFeature.Feature.PEM_API)
public sealed interface DEREncodable permits AsymmetricKey, KeyPair,
    PKCS8EncodedKeySpec, X509EncodedKeySpec, EncryptedPrivateKeyInfo,
    X509Certificate, X509CRL, PEMRecord {
}
