/*
 * Copyright (c) 2015, 2025, Oracle and/or its affiliates. All rights reserved.
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

package jdk.jpackage.internal;

import static jdk.jpackage.internal.StandardBundlerParam.APP_NAME;
import static jdk.jpackage.internal.StandardBundlerParam.LAUNCHER_DATA;
import static jdk.jpackage.internal.StandardBundlerParam.PREDEFINED_APP_IMAGE;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.MessageFormat;
import java.util.Map;
import java.util.Objects;
import jdk.internal.util.OperatingSystem;
import jdk.jpackage.internal.model.ConfigException;
import jdk.jpackage.internal.model.PackagerException;


class AppImageBundler extends AbstractBundler {

    @Override
    public final String getName() {
        return I18N.getString("app.bundler.name");
    }

    @Override
    public final String getID() {
        return "app";
    }

    @Override
    public final String getBundleType() {
        return "IMAGE";
    }

    @Override
    public final boolean validate(Map<String, ? super Object> params)
            throws ConfigException {
        try {
            Objects.requireNonNull(params);

            if (!params.containsKey(PREDEFINED_APP_IMAGE.getID())
                    && !StandardBundlerParam.isRuntimeInstaller(params)) {
                LAUNCHER_DATA.fetchFrom(params);
            }

            if (paramsValidator != null) {
                paramsValidator.validate(params);
            }
        } catch (RuntimeException re) {
            if (re.getCause() instanceof ConfigException) {
                throw (ConfigException) re.getCause();
            } else {
                throw new ConfigException(re);
            }
        }

        return true;
    }

    @Override
    public final Path execute(Map<String, ? super Object> params,
            Path outputParentDir) throws PackagerException {

        final var predefinedAppImage = PREDEFINED_APP_IMAGE.fetchFrom(params);

        try {
            if (predefinedAppImage == null) {
                Path rootDirectory = createRoot(params, outputParentDir);
                appImageSupplier.prepareApplicationFiles(params, rootDirectory);
                return rootDirectory;
            } else {
                appImageSupplier.prepareApplicationFiles(params, predefinedAppImage);
                return predefinedAppImage;
            }
        } catch (PackagerException pe) {
            throw pe;
        } catch (RuntimeException|IOException ex) {
            Log.verbose(ex);
            throw new PackagerException(ex);
        }
    }

    @Override
    public final boolean supported(boolean runtimeInstaller) {
        return true;
    }

    @Override
    public final boolean isDefault() {
        return false;
    }

    @FunctionalInterface
    static interface AppImageSupplier {

        void prepareApplicationFiles(Map<String, ? super Object> params,
                Path root) throws PackagerException, IOException;
    }

    final AppImageBundler setAppImageSupplier(AppImageSupplier v) {
        appImageSupplier = v;
        return this;
    }

    final AppImageBundler setParamsValidator(ParamsValidator v) {
        paramsValidator = v;
        return this;
    }

    @FunctionalInterface
    interface ParamsValidator {
        void validate(Map<String, ? super Object> params) throws ConfigException;
    }

    private Path createRoot(Map<String, ? super Object> params,
            Path outputDirectory) throws PackagerException, IOException {

        IOUtils.writableOutputDir(outputDirectory);

        String imageName = APP_NAME.fetchFrom(params);
        if (OperatingSystem.isMacOS()) {
            imageName = imageName + ".app";
        }

        Log.verbose(MessageFormat.format(
                I18N.getString("message.creating-app-bundle"),
                imageName, outputDirectory.toAbsolutePath()));

        // Create directory structure
        Path rootDirectory = outputDirectory.resolve(imageName);
        if (Files.exists(rootDirectory)) {
            throw new PackagerException("error.root-exists",
                    rootDirectory.toAbsolutePath().toString());
        }

        Files.createDirectories(rootDirectory);

        return rootDirectory;
    }

    private ParamsValidator paramsValidator;
    private AppImageSupplier appImageSupplier;
}
