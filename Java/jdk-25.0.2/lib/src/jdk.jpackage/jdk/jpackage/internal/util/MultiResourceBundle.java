/*
 * Copyright (c) 2024, 2025, Oracle and/or its affiliates. All rights reserved.
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
package jdk.jpackage.internal.util;

import java.util.ArrayList;
import java.util.List;
import java.util.ListResourceBundle;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;
import static java.util.stream.Collectors.toMap;
import java.util.stream.Stream;
import jdk.internal.util.OperatingSystem;

public final class MultiResourceBundle extends ListResourceBundle {

    public static ResourceBundle create(String sharedResourceBundleName,
            Map<OperatingSystem, List<String>> platformResourceBundleNames) {
        List<String> bundleNames = new ArrayList<>();
        Optional.ofNullable(sharedResourceBundleName).ifPresent(bundleNames::add);
        Optional.ofNullable(platformResourceBundleNames.get(OperatingSystem.current())).ifPresent(bundleNames::addAll);
        if (bundleNames.isEmpty()) {
            throw new IllegalArgumentException("Empty resource bundle names list");
        } else {
            var resourceBundles = bundleNames.stream().map(ResourceBundle::getBundle).toArray(ResourceBundle[]::new);
            if (resourceBundles.length == 1) {
                return resourceBundles[0];
            } else {
                return new MultiResourceBundle(resourceBundles);
            }
        }
    }

    private MultiResourceBundle(ResourceBundle... bundles) {
        contents = Stream.of(bundles).map(bundle -> {
            return bundle.keySet().stream().map(key -> {
                return Map.entry(key, bundle.getObject(key));
            });
        }).flatMap(x -> x).collect(toMap(Map.Entry::getKey, Map.Entry::getValue, (o, n) -> {
            // Override old value with the new one
            return n;
        })).entrySet().stream().map(e -> {
            return new Object[]{e.getKey(), e.getValue()};
        }).toArray(Object[][]::new);
    }

    @Override
    protected Object[][] getContents() {
        return contents;
    }

    private final Object[][] contents;
}
