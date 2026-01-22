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

/*
 * This class contains a map which records the locale list string for
 * each resource in sun.util.resources & sun.text.resources.
 * It is used to avoid loading non-existent localized resources so that
 * jar files won't be opened unnecessarily to look up them.
 */
package sun.util.locale.provider;

import java.util.HashMap;
import java.util.Map;
import static sun.util.locale.provider.LocaleProviderAdapter.Type;

public class BaseLocaleDataMetaInfo implements LocaleDataMetaInfo {

    private static final Map<String, String> resourceNameToLocales = HashMap.newHashMap(9);

    static {
        resourceNameToLocales.put("FormatData",
                                  "  en en-US ");

        resourceNameToLocales.put("CollationData",
                                  "  ");

        resourceNameToLocales.put("BreakIteratorInfo",
                                  "  ");

        resourceNameToLocales.put("BreakIteratorRules",
                                  "  ");

        resourceNameToLocales.put("TimeZoneNames",
                                  "  en ");

        resourceNameToLocales.put("LocaleNames",
                                  "  en ");

        resourceNameToLocales.put("CurrencyNames",
                                  "  en-US ");

        resourceNameToLocales.put("CalendarData",
                                  "  en ");

        resourceNameToLocales.put("AvailableLocales",
                                  " en en-US ");
    }

    /*
     * Gets the supported locales string based on the availability of
     * locale data resource bundles for each resource name.
     *
     * @param resourceName the resource name
     * @return the supported locale string for the passed in resource.
     */
    public static String getSupportedLocaleString(String resourceName) {
        return resourceNameToLocales.getOrDefault(resourceName, "");
    }

    @Override
    public Type getType() {
        return Type.JRE;
    }

    @Override
    public String availableLanguageTags(String category) {
        return getSupportedLocaleString(category);
    }
}
