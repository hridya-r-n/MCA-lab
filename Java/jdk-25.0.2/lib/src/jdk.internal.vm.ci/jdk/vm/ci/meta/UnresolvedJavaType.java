/*
 * Copyright (c) 2011, 2024, Oracle and/or its affiliates. All rights reserved.
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
 */
package jdk.vm.ci.meta;

/**
 * Implementation of {@link JavaType} for unresolved HotSpot classes.
 */
public final class UnresolvedJavaType implements JavaType {
    private final String name;

    /**
     * The reason type resolution failed. Can be null.
     */
    private final Throwable cause;

    @Override
    public String getName() {
        return name;
    }

    private UnresolvedJavaType(String name, Throwable cause) {
        this.name = name;
        this.cause = cause;
        assert name.length() == 1 && JavaKind.fromPrimitiveOrVoidTypeChar(name.charAt(0)) != null || name.charAt(0) == '[' || name.charAt(name.length() - 1) == ';' : name;
    }

    /**
     * Creates an unresolved type for a valid {@link JavaType#getName() type name}.
     */
    public static UnresolvedJavaType create(String name) {
        return new UnresolvedJavaType(name, null);
    }

    /**
     * Creates an unresolved type for a valid {@link JavaType#getName() type name}.
     */
    public static UnresolvedJavaType create(String name, Throwable cause) {
        return new UnresolvedJavaType(name, cause);
    }

    /**
     * Gets the exception, if any, representing the reason type resolution resulted in this object.
     */
    public Throwable getCause() {
        return cause;
    }

    @Override
    public JavaType getComponentType() {
        if (getName().charAt(0) == '[') {
            return new UnresolvedJavaType(getName().substring(1), null);
        }
        return null;
    }

    @Override
    public JavaType getArrayClass() {
        return new UnresolvedJavaType('[' + getName(), null);
    }

    @Override
    public JavaKind getJavaKind() {
        return JavaKind.Object;
    }

    @Override
    public int hashCode() {
        return getName().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof UnresolvedJavaType)) {
            return false;
        }
        UnresolvedJavaType that = (UnresolvedJavaType) obj;
        return this.getName().equals(that.getName());
    }

    @Override
    public String toString() {
        return "UnresolvedJavaType<" + getName() + ">";
    }

    @Override
    public ResolvedJavaType resolve(ResolvedJavaType accessingClass) {
        return accessingClass.lookupType(this, true);
    }
}
