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

public class FormatData_ms_BN extends ListResourceBundle {
    @Override
    protected final Object[][] getContents() {
        final String[] metaValue_java_time_buddhist_DatePatterns = new String[] {
            "dd MMMM y G",
            "",
            "",
            "",
        };
        final String[] metaValue_buddhist_DatePatterns = new String[] {
            "dd MMMM y GGGG",
            "",
            "",
            "",
        };
        final Object[][] data = new Object[][] {
            { "java.time.generic.DatePatterns",
                new String[] {
                    "dd MMMM y G",
                    "d MMMM y G",
                    "dd/MM/y G",
                    "d/MM/y GGGGG",
                }
            },
            { "generic.DatePatterns",
                new String[] {
                    "dd MMMM y GGGG",
                    "d MMMM y GGGG",
                    "dd/MM/y GGGG",
                    "d/MM/y G",
                }
            },
            { "DatePatterns",
                new String[] {
                    "dd MMMM y",
                    "d MMMM y",
                    "d MMM y",
                    "d/MM/yy",
                }
            },
            { "java.time.buddhist.DatePatterns", metaValue_java_time_buddhist_DatePatterns },
            { "buddhist.DatePatterns", metaValue_buddhist_DatePatterns },
            { "java.time.japanese.DatePatterns", metaValue_java_time_buddhist_DatePatterns },
            { "japanese.DatePatterns",
                new String[] {
                    "dd MMMM yyyy GGGG",
                    "",
                    "",
                    "",
                }
            },
            { "java.time.roc.DatePatterns", metaValue_java_time_buddhist_DatePatterns },
            { "roc.DatePatterns", metaValue_buddhist_DatePatterns },
            { "java.time.islamic.DatePatterns", metaValue_java_time_buddhist_DatePatterns },
            { "islamic.DatePatterns", metaValue_buddhist_DatePatterns },
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
                    "\u00a4\u00a0#,##0.00",
                    "#,##0%",
                    "\u00a4#,##0.00;(\u00a4#,##0.00)",
                }
            },
        };
        return data;
    }
}
