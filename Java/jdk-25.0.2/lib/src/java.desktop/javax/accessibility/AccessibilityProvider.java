/*
 * Copyright (c) 2015, 2024, Oracle and/or its affiliates. All rights reserved.
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

package javax.accessibility;

/**
 * Service Provider Interface (SPI) for Assistive Technology.
 * <p>
 * This service provider class provides mappings from the platform specific
 * accessibility APIs to the Java Accessibility API.
 * <p>
 * Each service provider implementation is named and can be activated via the
 * {@link #activate} method. Service providers can be loaded when the default
 * {@link java.awt.Toolkit toolkit} is initialized.
 *
 * @apiNote There will typically be one provider per platform, such as Windows
 *          or Linux, to support accessibility for screen readers and
 *          magnifiers. However, more than one service provider can be
 *          activated. For example, a test tool which provides visual results
 *          obtained by interrogating the Java Accessibility API can be
 *          activated along with the activation of the support for screen
 *          readers and screen magnifiers.
 * @see java.awt.Toolkit#getDefaultToolkit
 * @see java.util.ServiceLoader
 * @since 9
 */
public abstract class AccessibilityProvider {

    /**
     * Initializes a new accessibility provider.
     */
    protected AccessibilityProvider() {
    }

    /**
     * Returns the name of this service provider. This name is used to locate a
     * requested service provider.
     *
     * @return the name of this service provider
     */
    public abstract String getName();

    /**
     * Activates the support provided by this service provider.
     */
    public abstract void activate();
}
