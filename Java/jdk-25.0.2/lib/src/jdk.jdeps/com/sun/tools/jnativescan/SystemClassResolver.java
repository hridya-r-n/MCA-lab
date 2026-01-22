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
package com.sun.tools.jnativescan;

import com.sun.tools.javac.platform.PlatformDescription;
import com.sun.tools.javac.platform.PlatformProvider;

import javax.tools.JavaFileManager;
import javax.tools.JavaFileObject;
import javax.tools.StandardLocation;
import java.io.IOException;
import java.io.InputStream;
import java.lang.classfile.ClassFile;
import java.lang.classfile.ClassModel;
import java.lang.constant.ClassDesc;
import java.lang.module.ModuleDescriptor;
import java.util.*;

class SystemClassResolver implements AutoCloseable {

    private final JavaFileManager platformFileManager;
    private final Map<String, String> packageToSystemModule;
    private final Map<ClassDesc, ClassModel> cache = new HashMap<>();

    private SystemClassResolver(JavaFileManager platformFileManager) {
        this.platformFileManager = platformFileManager;
        this.packageToSystemModule = packageToSystemModule(platformFileManager);
    }

    public static SystemClassResolver forRuntimeVersion(Runtime.Version version) {
        String platformName = String.valueOf(version.feature());
        PlatformProvider platformProvider = ServiceLoader.load(PlatformProvider.class).findFirst().orElseThrow();
        PlatformDescription platform;
        try {
            platform = platformProvider.getPlatform(platformName, null);
        } catch (PlatformProvider.PlatformNotSupported e) {
            throw new JNativeScanFatalError("Release: " + platformName + " not supported", e);
        }
        JavaFileManager fm = platform.getFileManager();
        return new SystemClassResolver(fm);
    }

    private static Map<String, String> packageToSystemModule(JavaFileManager platformFileManager) {
        try {
            Set<JavaFileManager.Location> locations = platformFileManager.listLocationsForModules(
                    StandardLocation.SYSTEM_MODULES).iterator().next();

            Map<String, String> result = new HashMap<>();
            for (JavaFileManager.Location loc : locations) {
                JavaFileObject jfo = platformFileManager.getJavaFileForInput(loc, "module-info", JavaFileObject.Kind.CLASS);
                ModuleDescriptor descriptor = ModuleDescriptor.read(jfo.openInputStream());
                for (ModuleDescriptor.Exports export : descriptor.exports()) {
                    if (!export.isQualified()) {
                        result.put(export.source(), descriptor.name());
                    }
                }
            }
            return result;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<ClassModel> lookup(ClassDesc desc) {
        return Optional.ofNullable(cache.computeIfAbsent(desc, _ -> {
            String qualName = JNativeScanTask.qualName(desc);
            String moduleName = packageToSystemModule.get(desc.packageName());
            if (moduleName != null) {
                try {
                    JavaFileManager.Location loc = platformFileManager.getLocationForModule(StandardLocation.SYSTEM_MODULES, moduleName);
                    JavaFileObject jfo = platformFileManager.getJavaFileForInput(loc, qualName, JavaFileObject.Kind.CLASS);
                    if (jfo == null) {
                        throw new JNativeScanFatalError("System class can not be found: " + qualName);
                    }
                    try (InputStream inputStream = jfo.openInputStream()) {
                        return ClassFile.of().parse(inputStream.readAllBytes());
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return null;
        }));
    }

    @Override
    public void close() throws IOException {
        platformFileManager.close();
    }
}
