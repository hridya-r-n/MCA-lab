/*
 * Copyright (c) 2018, 2024, Oracle and/or its affiliates. All rights reserved.
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

package com.sun.jndi.ldap;

import java.util.*;
import java.util.concurrent.locks.ReentrantLock;
import javax.naming.NamingException;
import javax.naming.ldap.spi.LdapDnsProvider;
import javax.naming.ldap.spi.LdapDnsProviderResult;

/**
 * The {@code LdapDnsProviderService} is responsible for creating and providing
 * access to the registered {@code LdapDnsProvider}s. The {@link ServiceLoader}
 * is used to find and register any implementations of {@link LdapDnsProvider}.
 *
 * <p> Instances of this class are safe for use by multiple threads.
 */
final class LdapDnsProviderService {

    private static volatile LdapDnsProviderService service;
    private static final ReentrantLock LOCK = new ReentrantLock();
    private final ServiceLoader<LdapDnsProvider> providers;

    /**
     * Creates a new instance of LdapDnsProviderService
     */
    private LdapDnsProviderService() {
        providers = ServiceLoader.load(
                LdapDnsProvider.class,
                ClassLoader.getSystemClassLoader());
    }

    /**
     * Retrieves the singleton instance of LdapDnsProviderService.
     */
    static LdapDnsProviderService getInstance() {
        if (service != null) return service;
        LOCK.lock();
        try {
            if (service != null) return service;
            service = new LdapDnsProviderService();
        } finally {
            LOCK.unlock();
        }
        return service;
    }

    /**
     * Retrieves result from the first provider that successfully resolves
     * the endpoints. If no results are found when calling installed
     * subclasses of {@code LdapDnsProvider} then this method will fall back
     * to the {@code DefaultLdapDnsProvider}.
     *
     * @throws NamingException if the {@code url} is not valid or an error
     *                         occurred while performing the lookup.
     */
    LdapDnsProviderResult lookupEndpoints(String url, Hashtable<?,?> env)
        throws NamingException
    {
        LdapDnsProviderResult result = null;
        Hashtable<?, ?> envCopy = new Hashtable<>(env);
        LOCK.lock();
        try {
            Iterator<LdapDnsProvider> iterator = providers.iterator();
            while (result == null && iterator.hasNext()) {
                result = iterator.next().lookupEndpoints(url, envCopy)
                        .filter(r -> !r.getEndpoints().isEmpty())
                        .orElse(null);
            }
        } finally {
            LOCK.unlock();
        }

        if (result == null) {
            return new DefaultLdapDnsProvider().lookupEndpoints(url, env)
                .orElse(new LdapDnsProviderResult("", List.of()));
        }
        return result;
    }
}
