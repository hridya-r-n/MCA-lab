/*
 * Copyright (c) 2012, 2025, Oracle and/or its affiliates. All rights reserved.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

/*
 * COPYRIGHT AND PERMISSION NOTICE
 *
 * Copyright (c) 1991-2022 Unicode, Inc. All rights reserved.
 * Distributed under the Terms of Use in https://www.unicode.org/copyright.html.
 *
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of the Unicode data files and any associated documentation
 * (the "Data Files") or Unicode software and any associated documentation
 * (the "Software") to deal in the Data Files or Software
 * without restriction, including without limitation the rights to use,
 * copy, modify, merge, publish, distribute, and/or sell copies of
 * the Data Files or Software, and to permit persons to whom the Data Files
 * or Software are furnished to do so, provided that either
 * (a) this copyright and permission notice appear with all copies
 * of the Data Files or Software, or
 * (b) this copyright and permission notice appear in associated
 * Documentation.
 *
 * THE DATA FILES AND SOFTWARE ARE PROVIDED "AS IS", WITHOUT WARRANTY OF
 * ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 * WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT OF THIRD PARTY RIGHTS.
 * IN NO EVENT SHALL THE COPYRIGHT HOLDER OR HOLDERS INCLUDED IN THIS
 * NOTICE BE LIABLE FOR ANY CLAIM, OR ANY SPECIAL INDIRECT OR CONSEQUENTIAL
 * DAMAGES, OR ANY DAMAGES WHATSOEVER RESULTING FROM LOSS OF USE,
 * DATA OR PROFITS, WHETHER IN AN ACTION OF CONTRACT, NEGLIGENCE OR OTHER
 * TORTIOUS ACTION, ARISING OUT OF OR IN CONNECTION WITH THE USE OR
 * PERFORMANCE OF THE DATA FILES OR SOFTWARE.
 *
 * Except as contained in this notice, the name of a copyright holder
 * shall not be used in advertising or otherwise to promote the sale,
 * use or other dealings in these Data Files or Software without prior
 * written authorization of the copyright holder.
 */

package sun.text.resources.cldr.ext;

import java.util.ListResourceBundle;

public class FormatData_kl extends ListResourceBundle {
    @Override
    protected final Object[][] getContents() {
        final String[] metaValue_MonthAbbreviations = new String[] {
            "jan",
            "febr",
            "mar",
            "apr",
            "maj",
            "jun",
            "jul",
            "aug",
            "sept",
            "okt",
            "nov",
            "dec",
            "",
        };
        final String[] metaValue_generic_DayNames = new String[] {
            "sapaat",
            "ataasinngorneq",
            "marlunngorneq",
            "pingasunngorneq",
            "sisamanngorneq",
            "tallimanngorneq",
            "arfininngorneq",
        };
        final String[] metaValue_generic_DayAbbreviations = new String[] {
            "sap",
            "ata",
            "mar",
            "pin",
            "sis",
            "tal",
            "arf",
        };
        final String[] metaValue_generic_TimePatterns = new String[] {
            "HH.mm.ss zzzz",
            "HH.mm.ss z",
            "HH.mm.ss",
            "HH.mm",
        };
        final String[] metaValue_MonthNames = new String[] {
            "januaarip",
            "februaarip",
            "marsip",
            "apriilip",
            "maajip",
            "juunip",
            "juulip",
            "aggustip",
            "septembarip",
            "oktobarip",
            "novembarip",
            "decembarip",
            "",
        };
        final Object[][] data = new Object[][] {
            { "generic.DayNames", metaValue_generic_DayNames },
            { "generic.DayAbbreviations", metaValue_generic_DayAbbreviations },
            { "generic.TimePatterns", metaValue_generic_TimePatterns },
            { "MonthNames", metaValue_MonthNames },
            { "standalone.MonthNames",
                new String[] {
                    "januaari",
                    "februaari",
                    "marsi",
                    "apriili",
                    "maaji",
                    "juuni",
                    "juuli",
                    "aggusti",
                    "septembari",
                    "oktobari",
                    "novembari",
                    "decembari",
                    "",
                }
            },
            { "MonthAbbreviations", metaValue_MonthAbbreviations },
            { "standalone.MonthAbbreviations", metaValue_MonthAbbreviations },
            { "DayNames", metaValue_generic_DayNames },
            { "standalone.DayNames", metaValue_generic_DayNames },
            { "DayAbbreviations", metaValue_generic_DayAbbreviations },
            { "standalone.DayAbbreviations", metaValue_generic_DayAbbreviations },
            { "TimePatterns", metaValue_generic_TimePatterns },
            { "PluralRules", "one:n = 1" },
            { "buddhist.MonthNames", metaValue_MonthNames },
            { "buddhist.MonthAbbreviations", metaValue_MonthAbbreviations },
            { "buddhist.DayNames", metaValue_generic_DayNames },
            { "buddhist.DayAbbreviations", metaValue_generic_DayAbbreviations },
            { "buddhist.TimePatterns", metaValue_generic_TimePatterns },
            { "japanese.MonthNames", metaValue_MonthNames },
            { "japanese.MonthAbbreviations", metaValue_MonthAbbreviations },
            { "japanese.DayNames", metaValue_generic_DayNames },
            { "japanese.DayAbbreviations", metaValue_generic_DayAbbreviations },
            { "japanese.TimePatterns", metaValue_generic_TimePatterns },
            { "roc.MonthNames", metaValue_MonthNames },
            { "roc.MonthAbbreviations", metaValue_MonthAbbreviations },
            { "roc.DayNames", metaValue_generic_DayNames },
            { "roc.DayAbbreviations", metaValue_generic_DayAbbreviations },
            { "roc.TimePatterns", metaValue_generic_TimePatterns },
            { "islamic.DayNames", metaValue_generic_DayNames },
            { "islamic.DayAbbreviations", metaValue_generic_DayAbbreviations },
            { "islamic.TimePatterns", metaValue_generic_TimePatterns },
            { "latn.NumberElements",
                new String[] {
                    ",",
                    ".",
                    ";",
                    "%",
                    "0",
                    "#",
                    "-",
                    "E",
                    "\u2030",
                    "\u221e",
                    "NaN",
                    "",
                    "",
                }
            },
            { "latn.NumberPatterns",
                new String[] {
                    "#,##0.###",
                    "\u00a4#,##0.00;\u00a4-#,##0.00",
                    "#,##0\u00a0%",
                    "",
                }
            },
            { "arab.NumberElements",
                new String[] {
                    "\u066b",
                    "\u066c",
                    "\u061b",
                    "\u066a\u061c",
                    "\u0660",
                    "#",
                    "\u061c-",
                    "\u0627\u0633",
                    "\u0609",
                    "\u221e",
                    "NaN",
                    "",
                    "",
                }
            },
            { "arab.NumberPatterns",
                new String[] {
                    "#,##0.###",
                    "#,##0.00\u00a0\u00a4",
                    "#,##0%",
                    "",
                }
            },
        };
        return data;
    }
}
