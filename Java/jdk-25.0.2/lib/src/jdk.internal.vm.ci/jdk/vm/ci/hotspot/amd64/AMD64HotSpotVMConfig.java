/*
 * Copyright (c) 2011, 2025, Oracle and/or its affiliates. All rights reserved.
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
package jdk.vm.ci.hotspot.amd64;

import jdk.vm.ci.hotspot.HotSpotVMConfigAccess;
import jdk.vm.ci.hotspot.HotSpotVMConfigStore;

/**
 * Used to access AMD64 specific native configuration details.
 */
class AMD64HotSpotVMConfig extends HotSpotVMConfigAccess {

    AMD64HotSpotVMConfig(HotSpotVMConfigStore config) {
        super(config);
    }

    final boolean useCompressedOops = getFlag("UseCompressedOops", Boolean.class);
    final long vmVersionFeatures = getFieldAddress("VM_Version::_features", "VM_Version::VM_Features");
    final long vmFeaturesFeaturesOffset = getFieldOffset("VM_Version::VM_Features::_features_bitmap[0]", Long.class, "uint64_t");
    final long vmFeaturesFeaturesSize = getFieldValue("VM_Version::VM_Features::_features_bitmap_size", Long.class, "int");
}
