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
package jdk.incubator.vector;

import jdk.internal.vm.vector.VectorSupport;

import java.util.Locale;
import java.util.Set;

import static jdk.incubator.vector.Util.requires;
import static jdk.internal.util.Architecture.isX64;
import static jdk.internal.vm.vector.Utils.debug;

/**
 * Enumerates CPU ISA extensions supported by the JVM on the current hardware.
 */
/*package-private*/ class CPUFeatures {
    private static final Set<String> features = getCPUFeatures();

    private static Set<String> getCPUFeatures() {
        String featuresString = VectorSupport.getCPUFeatures();
        debug(featuresString);

        if (featuresString.equals("")) return Set.of();

        String[] features = featuresString.toLowerCase(Locale.ROOT)
                                          .split(",? "); // " " or ", " are used as a delimiter by JVM
        assert validateFeatures(features);
        return Set.of(features);
    }

    private static boolean validateFeatures(String[] features) {
        for (String s : features) {
            assert s != null && s.matches("[a-z0-9._]+") : String.format("Invalid CPU feature name: '%s'", s);
        }
        return true;
    }

    private static boolean hasFeature(String feature) {
        return features.contains(feature.toLowerCase(Locale.ROOT));
    }

    public static class X64 {
        public static boolean SUPPORTS_AVX      = hasFeature("avx");
        public static boolean SUPPORTS_AVX2     = hasFeature("avx2");
        public static boolean SUPPORTS_AVX512F  = hasFeature("avx512f");
        public static boolean SUPPORTS_AVX512DQ = hasFeature("avx512dq");

        static {
            requires(isX64(), "unsupported platform");

            debug("AVX=%b; AVX2=%b; AVX512F=%b; AVX512DQ=%b",
                  SUPPORTS_AVX, SUPPORTS_AVX2, SUPPORTS_AVX512F, SUPPORTS_AVX512DQ);

            assert SUPPORTS_AVX512F == (VectorShape.getMaxVectorBitSize(int.class)   == 512);
            assert SUPPORTS_AVX2    == (VectorShape.getMaxVectorBitSize(byte.class)  >= 256);
            assert SUPPORTS_AVX     == (VectorShape.getMaxVectorBitSize(float.class) >= 256);
        }
    }

    public static Set<String> features() {
        return features;
    }
}
