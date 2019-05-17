package com.syz.globalexception.handler.utils;


import org.apache.commons.lang3.StringUtils;

public class StringUtil {
    public StringUtil() {
    }

    public static boolean isNullOrBlank(String s) {
        return isNullOrBlank(s, false);
    }

    public static String null2Str(String origin) {
        return origin == null ? "" : origin.trim();
    }

    public static String null2Str(Object origin) {
        return null2Str(origin, "");
    }

    public static String null2Str(Object origin, String def) {
        return origin == null ? def : origin.toString().trim();
    }

    public static boolean isNullOrBlank(String s, boolean doTrim) {
        if (doTrim) {
            s = StringUtils.trim(s);
        }

        return StringUtils.isEmpty(s);
    }

    public static String[] split(String toSplit, String delimiter) {
        return StringUtils.split(toSplit, delimiter);
    }

    public static String[] splitTwo(String toSplit, String delimiter) {
        if (isNotEmpty(toSplit) && isNotEmpty(delimiter)) {
            int offset = toSplit.indexOf(delimiter);
            if (offset < 0) {
                return null;
            } else {
                String beforeDelimiter = toSplit.substring(0, offset);
                String afterDelimiter = toSplit.substring(offset + delimiter.length());
                return new String[]{beforeDelimiter, afterDelimiter};
            }
        } else {
            return null;
        }
    }

    public static boolean isNotEmpty(String str) {
        return !StringUtils.isEmpty(str);
    }

    public static String toString(Object obj) {
        return obj == null ? null : obj.toString();
    }

    public static String capitalize(String s) {
        return StringUtils.capitalize(s);
    }

    public static String underlineToCamel(String str) {
        str = str.toLowerCase();
        String[] pattern = str.split("_");
        StringBuilder builder = new StringBuilder();

        for(int i = 0; i < pattern.length; ++i) {
            if (i == 0) {
                builder.append(pattern[i]);
            } else {
                builder.append(pattern[i].substring(0, 1).toUpperCase());
                builder.append(pattern[i].substring(1));
            }
        }

        return builder.toString();
    }

    public static String dotPkgToSlash(String pkgName) {
        return pkgName.replaceAll("\\.", "/");
    }
}
