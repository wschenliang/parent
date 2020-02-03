package com.rongqi.common.utils;


import java.util.Collection;
import java.util.Iterator;

public class CollectionUtils {


    public static boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    public static <T> String toString(Collection<T> values) {
        return toString(values, "");
    }

    public static <T> String toString(Collection<T> values, String gap) {
        if (values != null && !values.isEmpty()) {
            Iterator<T> itr = values.iterator();
            if (values.size() == 1) {
                return String.valueOf(itr.next());
            } else {
                StringBuilder sb = new StringBuilder();
                sb.append(itr.next());

                while(itr.hasNext()) {
                    sb.append(gap).append(itr.next());
                }

                return sb.toString();
            }
        } else {
            return "";
        }
    }

    public static boolean isNotEmpty(Collection<?> childrens) {
        return !isEmpty(childrens);
    }
}
