/*
 * Copyright (c) 2001, 2024, Oracle and/or its affiliates. All rights reserved.
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

package jdk.internal.access;

import java.lang.reflect.*;
import jdk.internal.reflect.*;

/** An interface which gives privileged packages Java-level access to
    internals of java.lang.reflect. Use as a last resort! */
public interface JavaLangReflectAccess {
    /**
     * Creates a new root constructor from the original one, with
     * a custom accessor. Used by serialization hooks.
     */
    <T> Constructor<T> newConstructorWithAccessor(Constructor<T> original, ConstructorAccessor accessor);

    /** Gets the byte[] that encodes TypeAnnotations on an Executable. */
    public byte[] getExecutableTypeAnnotationBytes(Executable ex);

    /** Gets the shared array of parameter types of an Executable. */
    public Class<?>[] getExecutableSharedParameterTypes(Executable ex);

    /** Gets the shared array of exception types of an Executable. */
    public Class<?>[] getExecutableSharedExceptionTypes(Executable ex);

    // Copying routines, needed to quickly fabricate new Field,
    // Method, and Constructor objects from templates

    /** Makes a "child" copy of a Method */
    public Method      copyMethod(Method arg);

    /** Makes a "child" copy of a Field */
    public Field       copyField(Field arg);

    /** Makes a "child" copy of a Constructor */
    public <T> Constructor<T> copyConstructor(Constructor<T> arg);

    /** Gets the root of the given AccessibleObject object; null if arg is the root */
    public <T extends AccessibleObject> T getRoot(T obj);

    /** Tests if this is a trusted final field */
    public boolean isTrustedFinalField(Field f);

    /** Returns a new instance created by the given constructor with access check */
    public <T> T newInstance(Constructor<T> ctor, Object[] args, Class<?> caller)
        throws IllegalAccessException, InstantiationException, InvocationTargetException;
}
