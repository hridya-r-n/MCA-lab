/*
 * Copyright (c) 2015, 2024, Oracle and/or its affiliates. All rights reserved.
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
package jdk.internal.jimage.decompressor;

import java.io.IOException;
import java.nio.ByteOrder;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import jdk.internal.jimage.decompressor.ResourceDecompressor.StringsProvider;

/**
 * Entry point to decompress resources.
 *
 * @implNote This class needs to maintain JDK 8 source compatibility.
 *
 * It is used internally in the JDK to implement jimage/jrtfs access,
 * but also compiled and delivered as part of the jrtfs.jar to support access
 * to the jimage file provided by the shipped JDK by tools running on JDK 8.
 */
public final class Decompressor {

    private final Map<Integer, ResourceDecompressor> pluginsCache = new HashMap<>();

    public Decompressor() {
    }

    /**
     * Decompress a resource.
     * @param order Byte order.
     * @param provider Strings provider
     * @param content The resource content to uncompress.
     * @return A fully uncompressed resource.
     * @throws IOException
     */
    public byte[] decompressResource(ByteOrder order, StringsProvider provider,
            byte[] content) throws IOException {
        Objects.requireNonNull(order);
        Objects.requireNonNull(provider);
        Objects.requireNonNull(content);
        CompressedResourceHeader header;
        do {
            header = CompressedResourceHeader.readFromResource(order, content);
            if (header != null) {
                ResourceDecompressor decompressor =
                        pluginsCache.get(header.getDecompressorNameOffset());
                if (decompressor == null) {
                    String pluginName =
                            provider.getString(header.getDecompressorNameOffset());
                    if (pluginName == null) {
                        throw new IOException("Plugin name not found");
                    }
                    decompressor = ResourceDecompressorRepository.
                            newResourceDecompressor(pluginName);
                    if (decompressor == null) {
                        throw new IOException("Plugin not found: " + pluginName);
                    }

                    pluginsCache.put(header.getDecompressorNameOffset(), decompressor);
                }
                try {
                    content = decompressor.decompress(provider, content,
                            CompressedResourceHeader.getSize(), header.getUncompressedSize());
                } catch (Exception ex) {
                    throw new IOException(ex);
                }
            }
        } while (header != null);
        return content;
    }
}
