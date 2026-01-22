/*
 * Copyright (c) 2024, Oracle and/or its affiliates. All rights reserved.
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
package java.util.zip;

import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;

class Snippets {

    void deflaterInflaterExample() {
        // @start region="DeflaterInflaterExample"

        // Encode a String into bytes
        String inputString = "blahblahblah\u20AC\u20AC";
        byte[] input = inputString.getBytes(StandardCharsets.UTF_8);

        // Compress the bytes
        ByteArrayOutputStream compressedBaos = new ByteArrayOutputStream();
        try (Deflater compressor = new Deflater()) {
            compressor.setInput(input);
            // Let the compressor know that the complete input
            // has been made available
            compressor.finish();
            // Keep compressing the input till the compressor
            // is finished compressing
            while (!compressor.finished()) {
                // Use some reasonable size for the temporary buffer
                // based on the data being compressed
                byte[] tmpBuffer = new byte[100];
                int numCompressed = compressor.deflate(tmpBuffer);
                // Copy over the compressed bytes from the temporary
                // buffer into the final byte array
                compressedBaos.write(tmpBuffer, 0, numCompressed);
            }
        }

        // Decompress the bytes
        ByteArrayOutputStream decompressedBaos = new ByteArrayOutputStream();
        try (Inflater decompressor = new Inflater()) {
            byte[] compressed = compressedBaos.toByteArray();
            decompressor.setInput(compressed, 0, compressed.length);
            while (!decompressor.finished()) {
                // Use some reasonable size for the temporary buffer,
                // based on the data being decompressed; in this example,
                // we use a small buffer size
                byte[] tmpBuffer = new byte[100];
                int numDecompressed = 0;
                try {
                    numDecompressed = decompressor.inflate(tmpBuffer);
                } catch (DataFormatException dfe) {
                    // Handle the exception suitably, in this example
                    // we just rethrow it
                    throw new RuntimeException(dfe);
                }
                // Copy over the decompressed bytes from the temporary
                // buffer into the final byte array
                decompressedBaos.write(tmpBuffer, 0, numDecompressed);
            }
        }
        // Decode the bytes into a String
        String outputString = decompressedBaos.toString(StandardCharsets.UTF_8);

        // @end
    }

}