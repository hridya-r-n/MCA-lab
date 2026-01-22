/*
 * Copyright (c) 1999, 2024, Oracle and/or its affiliates. All rights reserved.
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

/**
 * <p>Provides the classes which implement advanced dynamic
 * loading.  See the chapter <em>Advanced Dynamic Loading</em> in
 * the <a href="#spec">JMX Specification</a>.</p>
 *
 * <p>An MBean that is of a subclass of {@link
 * java.lang.ClassLoader} can be used as a class loader to create
 * other MBeans via the method {@link
 * javax.management.MBeanServer#createMBean(String, ObjectName,
 * ObjectName, Object[], String[])}, and to instantiate arbitrary
 * objects via the method {@link
 * javax.management.MBeanServer#instantiate(String, ObjectName,
 * Object[], String[])}.</p>
 *
 * <p>Every MBean Server has a <em>class loader repository</em>
 * containing all MBeans registered in that MBean Server that
 * are of a subclass of {@link java.lang.ClassLoader}.  The class
 * loader repository is used by the forms of the
 * <code>createMBean</code> and <code>instantiate</code> methods
 * in the {@link javax.management.MBeanServer MBeanServer}
 * interface that do not have an explicit loader parameter.</p>
 *
 * <p>If an MBean implements the interface {@link
 * javax.management.loading.PrivateClassLoader PrivateClassLoader},
 * then it is not added to the class loader repository.</p>
 *
 * @see <a id="spec" href="https://jcp.org/aboutJava/communityprocess/mrel/jsr160/index2.html">
 * JMX Specification, version 1.4</a>
 *
 * @since 1.5
 */
package javax.management.loading;
