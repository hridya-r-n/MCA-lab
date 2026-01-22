/*
 * Copyright (c) 1994, 2025, Oracle and/or its affiliates. All rights reserved.
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

/*
 *      FTP stream opener
 */

package sun.net.www.protocol.ftp;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.Proxy;
import java.util.Objects;

import static sun.net.util.ProxyUtil.copyProxy;

/** open an ftp connection given a URL */
public class Handler extends java.net.URLStreamHandler {

    protected int getDefaultPort() {
        return 21;
    }

    protected boolean equals(URL u1, URL u2) {
        String userInfo1 = u1.getUserInfo();
        String userInfo2 = u2.getUserInfo();
        return super.equals(u1, u2) && Objects.equals(userInfo1, userInfo2);
    }

    protected java.net.URLConnection openConnection(URL u)
        throws IOException {
        return openConnection(u, null);
    }

    protected java.net.URLConnection openConnection(URL u, Proxy proxy)
        throws IOException {
        FtpURLConnection connection;
        try {
            connection = new FtpURLConnection(u, copyProxy(proxy));
        } catch (IllegalArgumentException e) {
            var mfue = new MalformedURLException(e.getMessage());
            mfue.initCause(e);
            throw mfue;
        }
        return connection;
    }
}
