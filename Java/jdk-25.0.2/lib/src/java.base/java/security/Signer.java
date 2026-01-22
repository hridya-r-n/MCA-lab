/*
 * Copyright (c) 1996, 2024, Oracle and/or its affiliates. All rights reserved.
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

import java.io.*;

/**
 * This class is used to represent an Identity that can also digitally
 * sign data.
 *
 * <p>The management of a signer's private keys is an important and
 * sensitive issue that should be handled by subclasses as appropriate
 * to their intended use.
 *
 * @see Identity
 *
 * @author Benjamin Renaud
 * @since 1.1
 *
 * @deprecated This class is deprecated and subject to removal in a future
 *     version of Java SE. It has been replaced by
 *     {@code java.security.KeyStore}, the {@code java.security.cert} package,
 *     and {@code java.security.Principal}.
 */
@Deprecated(since="1.2", forRemoval=true)
@SuppressWarnings("removal")
public abstract class Signer extends Identity {

    @java.io.Serial
    private static final long serialVersionUID = -1763464102261361480L;

    /**
     * The signer's private key.
     *
     * @serial
     */
    private PrivateKey privateKey;

    /**
     * Creates a {@code Signer}. This constructor should only be used for
     * serialization.
     */
    protected Signer() {
        super();
    }


    /**
     * Creates a {@code Signer} with the specified identity name.
     *
     * @param name the identity name.
     */
    public Signer(String name) {
        super(name);
    }

    /**
     * Creates a {@code Signer} with the specified identity name and scope.
     *
     * @param name the identity name.
     *
     * @param scope the scope of the identity.
     *
     * @throws    KeyManagementException if there is already an identity
     * with the same name in the scope.
     */
    public Signer(String name, IdentityScope scope)
    throws KeyManagementException {
        super(name, scope);
    }

    /**
     * Returns this signer's private key.
     *
     * @return this signer's private key, or {@code null} if the private key has
     * not yet been set.
     */
    public PrivateKey getPrivateKey() {
        return privateKey;
    }

    /**
     * Sets the key pair (public key and private key) for this {@code Signer}.
     *
     * @param pair an initialized key pair.
     *
     * @throws    InvalidParameterException if the key pair is not
     * properly initialized.
     * @throws    KeyException if the key pair cannot be set for any
     * other reason.
     */
    public final void setKeyPair(KeyPair pair)
    throws InvalidParameterException, KeyException {
        PublicKey pub = pair.getPublic();
        PrivateKey priv = pair.getPrivate();

        if (pub == null || priv == null) {
            throw new InvalidParameterException();
        }
        setPublicKey(pub);
        privateKey = priv;
    }

    String printKeys() {
        String keys = "";
        PublicKey publicKey = getPublicKey();
        if (publicKey != null && privateKey != null) {
            keys = "\tpublic and private keys initialized";

        } else {
            keys = "\tno keys";
        }
        return keys;
    }

    /**
     * Returns a string of information about the {@code Signer}.
     *
     * @return a string of information about the {@code Signer}.
     */
    public String toString() {
        return "[Signer]" + super.toString();
    }
}
