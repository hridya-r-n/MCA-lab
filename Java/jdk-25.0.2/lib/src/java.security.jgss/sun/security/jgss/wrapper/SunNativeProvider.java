/*
 * Copyright (c) 2005, 2024, Oracle and/or its affiliates. All rights reserved.
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

package sun.security.jgss.wrapper;

import java.io.Serial;
import java.security.Provider;

import jdk.internal.util.OperatingSystem;
import jdk.internal.util.StaticProperty;
import org.ietf.jgss.Oid;
import static sun.security.util.SecurityConstants.PROVIDER_VER;

/**
 * Defines the Sun NativeGSS provider for plugging in the
 * native GSS mechanisms to Java GSS.
 *
 * List of supported mechanisms depends on the local
 * machine configuration.
 *
 * @author Yu-Ching Valerie Peng
 */

public final class SunNativeProvider extends Provider {

    @Serial
    private static final long serialVersionUID = -238911724858694204L;

    private static final String NAME = "SunNativeGSS";
    private static final String INFO = "Sun Native GSS provider";
    private static final String MF_CLASS =
        "sun.security.jgss.wrapper.NativeGSSFactory";

    static final boolean DEBUG =
        Boolean.getBoolean("sun.security.nativegss.debug");

    static void debug(String message) {
        if (message == null) {
            throw new NullPointerException();
        }
        System.err.println(NAME + ": " + message);
    }

    private static final Oid[] MECH_OIDS = getMechOIDs();

    @SuppressWarnings("restricted")
    private static Oid[] getMechOIDs() {
        try {
            // Ensure the InetAddress class is loaded before
            // loading j2gss. The library will access this class
            // and a deadlock might happen. See JDK-8210373.
            Class.forName("java.net.InetAddress");
            System.loadLibrary("j2gss");
        } catch (ClassNotFoundException | Error err) {
            if (DEBUG) {
                debug("No j2gss library found!");
                err.printStackTrace();
            }
            return null;
        }
        String[] gssLibs;
        String defaultLib = System.getProperty("sun.security.jgss.lib");
        if (defaultLib == null || defaultLib.trim().equals("")) {
            gssLibs = switch (OperatingSystem.current()) {
                case LINUX -> new String[]{
                        "libgssapi.so",
                        "libgssapi_krb5.so",
                        "libgssapi_krb5.so.2",
                };
                case MACOS -> new String[]{
                        "libgssapi_krb5.dylib",
                        "/usr/lib/sasl2/libgssapiv2.2.so",
                };
                case WINDOWS -> new String[]{
                        // Full path needed, DLL is in jre/bin
                        StaticProperty.javaHome() + "\\bin\\sspi_bridge.dll",
                };
                case AIX -> new String[]{
                        "/opt/freeware/lib64/libgssapi_krb5.so",
                };
                default -> new String[0];
            };
        } else {
            gssLibs = new String[]{ defaultLib };
        }
        for (String libName: gssLibs) {
             if (GSSLibStub.init(libName, DEBUG)) {
                 if (DEBUG) {
                     debug("Loaded GSS library: " + libName);
                 }
                 Oid[] mechs = GSSLibStub.indicateMechs();
                 if (DEBUG) {
                     for (Oid mech : mechs) {
                         debug("Native MF for " + mech);
                     }
                 }
                 return mechs;
             }
         }
         return null;
     }

    // initialize INSTANCE after MECH_OIDS is constructed
    static final Provider INSTANCE = new SunNativeProvider();

    public SunNativeProvider() {
        /* We are the Sun NativeGSS provider */
        super(NAME, PROVIDER_VER, INFO);

        if (MECH_OIDS != null) {
            for (Oid mech : MECH_OIDS) {
                putService(new Service(this, "GssApiMechanism",
                        mech.toString(), MF_CLASS, null, null));
            }
        }
    }
}
