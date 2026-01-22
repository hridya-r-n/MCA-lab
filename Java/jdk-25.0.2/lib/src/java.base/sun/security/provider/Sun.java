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

package sun.security.provider;

import java.util.*;
import java.security.*;

import static sun.security.util.SecurityConstants.PROVIDER_VER;


/**
 * The SUN Security Provider.
 *
 */
public final class Sun extends Provider {

    @java.io.Serial
    private static final long serialVersionUID = 6440182097568097204L;

    private static final String INFO = "SUN " +
    "(DSA key/parameter generation; DSA signing; SHA-1, MD5 digests; " +
    "SecureRandom; X.509 certificates; PKCS12, JKS & DKS keystores; " +
    "PKIX CertPathValidator; " +
    "PKIX CertPathBuilder; LDAP, Collection CertStores, JavaPolicy Policy; " +
    "JavaLoginConfig Configuration)";

    public Sun() {
        /* We are the SUN provider */
        super("SUN", PROVIDER_VER, INFO);

        Provider p = this;
        Iterator<Provider.Service> serviceIter = new SunEntries(p).iterator();

        while (serviceIter.hasNext()) {
            putService(serviceIter.next());
        }
    }
}
