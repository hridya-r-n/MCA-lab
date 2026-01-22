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

package java.lang.reflect;

import jdk.internal.access.JavaLangReflectAccess;
import jdk.internal.reflect.ConstructorAccessor;

/** Package-private class implementing the
    jdk.internal.access.JavaLangReflectAccess interface, allowing the java.lang
    package to instantiate objects in this package. */
final class ReflectAccess implements JavaLangReflectAccess {
    public <T> Constructor<T> newConstructorWithAccessor(Constructor<T> original, ConstructorAccessor accessor) {
        return original.newWithAccessor(accessor);
    }

    public byte[] getExecutableTypeAnnotationBytes(Executable ex) {
        return ex.getTypeAnnotationBytes();
    }

    public Class<?>[] getExecutableSharedParameterTypes(Executable ex) {
        return ex.getSharedParameterTypes();
    }

    public Class<?>[] getExecutableSharedExceptionTypes(Executable ex) {
        return ex.getSharedExceptionTypes();
    }

    //
    // Copying routines, needed to quickly fabricate new Field,
    // Method, and Constructor objects from templates
    //
    public Method      copyMethod(Method arg) {
        return arg.copy();
    }

    public Field       copyField(Field arg) {
        return arg.copy();
    }

    public <T> Constructor<T> copyConstructor(Constructor<T> arg) {
        return arg.copy();
    }

    @SuppressWarnings("unchecked")
    public <T extends AccessibleObject> T getRoot(T obj) {
        return (T) obj.getRoot();
    }

    public boolean isTrustedFinalField(Field f) {
        return f.isTrustedFinal();
    }

    public <T> T newInstance(Constructor<T> ctor, Object[] args, Class<?> caller)
        throws IllegalAccessException, InstantiationException, InvocationTargetException
    {
        return ctor.newInstanceWithCaller(args, true, caller);
    }
}
