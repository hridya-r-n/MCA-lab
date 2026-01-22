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

package jdk.jpackage.internal.pipeline;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class TaskSpecBuilder<T> {

    public TaskSpecBuilder(T task) {
        this.task = Objects.requireNonNull(task);
    }

    public T task() {
        return task;
    }

    public TaskSpecBuilder<T> addDependent(T v) {
        Objects.requireNonNull(v);
        dependents.add(v);
        return this;
    }

    public TaskSpecBuilder<T> addDependency(T v) {
        Objects.requireNonNull(v);
        dependencies.add(v);
        return this;
    }

    public TaskSpecBuilder<T> addDependencies(Collection<? extends T> v) {
        Objects.requireNonNull(v);
        v.forEach(Objects::requireNonNull);
        dependencies.addAll(v);
        return this;
    }

    public TaskSpecBuilder<T> addDependents(Collection<? extends T> v) {
        Objects.requireNonNull(v);
        v.forEach(Objects::requireNonNull);
        dependents.addAll(v);
        return this;
    }

    public List<DirectedEdge<T>> createLinks() {
        List<DirectedEdge<T>> edges = new ArrayList<>();

        for (var dependency : dependencies) {
            edges.add(DirectedEdge.create(dependency, task));
        }

        for (var dependent : dependents) {
            edges.add(DirectedEdge.create(task, dependent));
        }

        return edges;
    }

    private final Set<T> dependencies = new LinkedHashSet<>();
    private final Set<T> dependents = new LinkedHashSet<>();
    private final T task;
}
