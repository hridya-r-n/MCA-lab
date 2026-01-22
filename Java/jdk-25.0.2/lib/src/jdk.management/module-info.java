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

import java.lang.management.ManagementFactory;

/**
 * Defines JDK-specific management interfaces for the JVM.
 *
 * <p> This module contains the JDK's extensions to the standard implementation
 * of the {@link java.lang.management} API and also defines the management
 * interfaces for some other components of the platform.
 *
 * <p> All platform MBeans are registered in the <em>platform MBeanServer</em>
 * which can be obtained with {@link ManagementFactory#getPlatformMBeanServer}.
 *
 * @moduleGraph
 * @since 9
 */
module jdk.management {
    requires transitive java.management;

    exports com.sun.management;
    exports jdk.management;

    provides sun.management.spi.PlatformMBeanProvider with
        com.sun.management.internal.PlatformMBeanProviderImpl;
}

