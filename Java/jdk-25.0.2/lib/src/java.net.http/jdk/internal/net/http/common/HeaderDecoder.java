/*
 * Copyright (c) 2023, 2025, Oracle and/or its affiliates. All rights reserved.
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
package jdk.internal.net.http.common;

import java.net.http.HttpHeaders;

public class HeaderDecoder extends ValidatingHeadersConsumer {

    private final HttpHeadersBuilder headersBuilder;

    public HeaderDecoder(Context context) {
        super(context);
        this.headersBuilder = new HttpHeadersBuilder();
    }

    @Override
    public void onDecoded(CharSequence name, CharSequence value) {
        String n = name.toString();
        String v = value.toString();
        super.onDecoded(n, v);
        addHeader(n, v);
    }

    protected void addHeader(String name, String value) {
        headersBuilder.addHeader(name, value);
    }

    public HttpHeaders headers() {
        return headersBuilder.build();
    }
}
