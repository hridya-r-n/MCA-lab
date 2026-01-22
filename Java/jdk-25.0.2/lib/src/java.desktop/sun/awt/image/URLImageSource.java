/*
 * Copyright (c) 1995, 2024, Oracle and/or its affiliates. All rights reserved.
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

package sun.awt.image;

import java.io.InputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.MalformedURLException;

public class URLImageSource extends InputStreamImageSource {
    URL url;
    URLConnection conn;
    String actualHost;
    int actualPort;

    public URLImageSource(URL u) {
        this.url = u;
    }

    public URLImageSource(String href) throws MalformedURLException {
        this(newURL(null, href));
    }

    public URLImageSource(URL u, URLConnection uc) {
        this(u);
        conn = uc;
    }

    public URLImageSource(URLConnection uc) {
        this(uc.getURL(), uc);
    }

    private synchronized URLConnection getConnection() throws IOException {
        URLConnection c;
        if (conn != null) {
            c = conn;
            conn = null;
        } else {
            c = url.openConnection();
        }
        return c;
    }

    protected ImageDecoder getDecoder() {
        InputStream is = null;
        String type = null;
        URLConnection c = null;
        try {
            c = getConnection();
            is = c.getInputStream();
            type = c.getContentType();
            URL u = c.getURL();
            if (u != url && (!u.getHost().equals(url.getHost()) ||
                             u.getPort() != url.getPort()))
            {
                // The image is allowed to come from either the host/port
                // listed in the original URL, or it can come from one other
                // host/port that the URL is redirected to.  More than that
                // and we give up and just throw a SecurityException.
                // This is not directly related to SecurityManager so keep it.
                if (actualHost != null && (!actualHost.equals(u.getHost()) ||
                                           actualPort != u.getPort()))
                {
                    throw new SecurityException("image moved!");
                }
                actualHost = u.getHost();
                actualPort = u.getPort();
            }
        } catch (IOException e) {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e2) {
                }
            } else if (c instanceof HttpURLConnection) {
                ((HttpURLConnection)c).disconnect();
            }
            return null;
        }

        ImageDecoder id = decoderForType(is, type);
        if (id == null) {
            id = getDecoder(is);
        }

        if (id == null) {
            // probably, no appropriate decoder
            if  (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                }
            } else if (c instanceof HttpURLConnection) {
                ((HttpURLConnection)c).disconnect();
            }
        }
        return id;
    }

    @SuppressWarnings("deprecation")
    private static URL newURL(URL context, String spec) throws MalformedURLException {
        return new URL(context, spec);
    }
}
