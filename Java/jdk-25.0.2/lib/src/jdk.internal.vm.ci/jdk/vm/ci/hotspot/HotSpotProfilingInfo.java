/*
 * Copyright (c) 2012, 2025, Oracle and/or its affiliates. All rights reserved.
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
package jdk.vm.ci.hotspot;

import jdk.vm.ci.meta.ProfilingInfo;

/**
 * Extends {@link ProfilingInfo} with HotSpot specific profiling info.
 */
public interface HotSpotProfilingInfo extends ProfilingInfo {

    /**
     * Returns {@code MethodData::_compiler_counters._nof_decompiles}.
     */
    int getDecompileCount();

    /**
     * Returns {@code MethodData::_compiler_counters._nof_overflow_recompiles}.
     */
    int getOverflowRecompileCount();

    /**
     * Returns {@code MethodData::_compiler_counters._nof_overflow_traps}.
     */
    int getOverflowTrapCount();
}
