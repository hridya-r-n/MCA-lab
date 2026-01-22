/*
 * Copyright (c) 2012, 2024, Oracle and/or its affiliates. All rights reserved.
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

package sun.util.locale.provider;

import java.text.spi.BreakIteratorProvider;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import java.util.stream.Stream;

/*
 * FallbackProviderAdapter implementation. Fallback provider serves the
 * following purposes:
 *
 * - Locale data for ROOT, in case CLDR provider is absent.
 * - Locale data for BreakIterator/Collator resources for all locales.
 * - "Gan-nen" support for SimpleDateFormat (provides "FirstYear" for
 *   Japanese locales).
 */
public class FallbackLocaleProviderAdapter extends JRELocaleProviderAdapter {
    // Required locales/langtags
    private static final Locale[] AVAILABLE_LOCS = {Locale.US, Locale.ENGLISH, Locale.ROOT};
    private static final Set<String> AVAILABLE_LANGTAGS = Set.of("en-US", "en", "und");

    private volatile BreakIteratorProvider breakIteratorProvider;

    /**
     * Returns the type of this LocaleProviderAdapter
     */
    @Override
    public LocaleProviderAdapter.Type getAdapterType() {
        return Type.FALLBACK;
    }

    @Override
    public Locale[] getAvailableLocales() {
        return Stream.concat(Arrays.stream(super.getAvailableLocales()), Stream.of(AVAILABLE_LOCS))
                .distinct().toArray(Locale[]::new);
    }

    @Override
    protected Set<String> createLanguageTagSet(String category) {
        var s = new HashSet<>(super.createLanguageTagSet(category));
        s.addAll(AVAILABLE_LANGTAGS);
        return s;
    }

    @Override
    public boolean isSupportedProviderLocale(Locale locale, Set<String> langtags) {
        if (Locale.ROOT.equals(locale)) {
            return true;
        }

        locale = locale.stripExtensions();
        return langtags.contains(locale.toLanguageTag());
    }

    @Override
    // In order to correctly report supported locales
    public BreakIteratorProvider getBreakIteratorProvider() {
        if (breakIteratorProvider == null) {
            BreakIteratorProvider provider = new BreakIteratorProviderImpl(
                                    getAdapterType(),
                                    getLanguageTagSet("BreakIteratorRules"));

            synchronized (this) {
                if (breakIteratorProvider == null) {
                    breakIteratorProvider = provider;
                }
            }
        }
        return breakIteratorProvider;
    }
}
