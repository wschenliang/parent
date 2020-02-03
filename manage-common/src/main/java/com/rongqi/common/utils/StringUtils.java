package com.rongqi.common.utils;

import java.util.Collection;

public class StringUtils {
    public static boolean isBlank(String key) {
        return key == null || "".equals(key);
    }

    public static boolean isNotBlank(String pageContent) {
        return !isBlank(pageContent);
    }

    public static <T> String toString(Collection<T> values) {
        return CollectionUtils.toString(values);
    }

    public static <T> String toString(Collection<T> values, String gap) {
        return CollectionUtils.toString(values, gap);
    }

    public static <T> String toString(T[] values) {
        return ArrayUtils.toString(values);
    }

    public static <T> String toString(T[] values, String gap) {
        return ArrayUtils.toString(values, gap);
    }

    public static boolean hasBlank(String... keys) {
        for (String key : keys) {
            if (isBlank(key)){
                return true;
            }
        }
        return false;
    }

    public static String substringIfBeforeFirstChar(String text, char chr) {
        if (text == null){
            return text;
        }else {
            int index = text.indexOf(chr);
            return index < 0? text : text.substring(0, index);
        }
    }
}
