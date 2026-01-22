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

public class FormatData_en_HK extends ListResourceBundle {
    @Override
    protected final Object[][] getContents() {
        final String metaValue_generic_DateFormatItem_MMMMEd = "E, d MMMM";
        final String metaValue_generic_DateFormatItem_yMd = "d/M/y";
        final String[] metaValue_java_time_generic_DatePatterns = new String[] {
            "EEEE, d MMMM, y G",
            "d MMMM, y G",
            "d MMM, y G",
            "d/M/yy GGGGG",
        };
        final String[] metaValue_generic_DatePatterns = new String[] {
            "EEEE, d MMMM, y GGGG",
            "d MMMM, y GGGG",
            "d MMM, y GGGG",
            "d/M/yy G",
        };
        final Object[][] data = new Object[][] {
            { "java.time.generic.DatePatterns", metaValue_java_time_generic_DatePatterns },
            { "generic.DatePatterns", metaValue_generic_DatePatterns },
            { "generic.DateFormatItem.yyyyMMMd", "d MMM, y G" },
            { "generic.DateFormatItem.yyyyMMMEd", "E, d MMM, y G" },
            { "generic.DateFormatItem.yyyyMEd", "E, d/M/y GGGGG" },
            { "generic.DateFormatItem.yyyyMd", "d/M/y GGGGG" },
            { "generic.DateFormatItem.MMMMEd", metaValue_generic_DateFormatItem_MMMMEd },
            { "generic.DateFormatItem.yMd", metaValue_generic_DateFormatItem_yMd },
            { "DatePatterns",
                new String[] {
                    "EEEE, d MMMM y",
                    "d MMMM y",
                    "d MMM y",
                    "d/M/y",
                }
            },
            { "DateFormatItem.MMMMEd", metaValue_generic_DateFormatItem_MMMMEd },
            { "DateFormatItem.yMd", metaValue_generic_DateFormatItem_yMd },
            { "java.time.buddhist.DatePatterns", metaValue_java_time_generic_DatePatterns },
            { "buddhist.DatePatterns", metaValue_generic_DatePatterns },
            { "buddhist.DateFormatItem.MMMMEd", metaValue_generic_DateFormatItem_MMMMEd },
            { "buddhist.DateFormatItem.yMd", metaValue_generic_DateFormatItem_yMd },
            { "java.time.japanese.DatePatterns", metaValue_java_time_generic_DatePatterns },
            { "japanese.DatePatterns",
                new String[] {
                    "EEEE, d MMMM, yyyy GGGG",
                    "d MMMM, yyyy GGGG",
                    "d MMM, y GGGG",
                    "d/M/yy G",
                }
            },
            { "japanese.DateFormatItem.MMMMEd", metaValue_generic_DateFormatItem_MMMMEd },
            { "japanese.DateFormatItem.yMd", metaValue_generic_DateFormatItem_yMd },
            { "java.time.roc.DatePatterns", metaValue_java_time_generic_DatePatterns },
            { "roc.DatePatterns", metaValue_generic_DatePatterns },
            { "roc.DateFormatItem.MMMMEd", metaValue_generic_DateFormatItem_MMMMEd },
            { "roc.DateFormatItem.yMd", metaValue_generic_DateFormatItem_yMd },
            { "java.time.islamic.DatePatterns", metaValue_java_time_generic_DatePatterns },
            { "islamic.DatePatterns", metaValue_generic_DatePatterns },
            { "islamic.DateFormatItem.MMMMEd", metaValue_generic_DateFormatItem_MMMMEd },
            { "islamic.DateFormatItem.yMd", metaValue_generic_DateFormatItem_yMd },
            { "islamic-civil.DateFormatItem.MMMMEd", metaValue_generic_DateFormatItem_MMMMEd },
            { "islamic-civil.DateFormatItem.yMd", metaValue_generic_DateFormatItem_yMd },
            { "islamic-umalqura.DateFormatItem.MMMMEd", metaValue_generic_DateFormatItem_MMMMEd },
            { "islamic-umalqura.DateFormatItem.yMd", metaValue_generic_DateFormatItem_yMd },
        };
        return data;
    }
}
