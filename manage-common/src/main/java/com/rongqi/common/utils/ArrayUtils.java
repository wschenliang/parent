package com.rongqi.common.utils;

import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArrayUtils {


    public ArrayUtils() {
    }

    public static int length(boolean[] array) {
        return array == null ? 0 : array.length;
    }

    public static int length(byte[] array) {
        return array == null ? 0 : array.length;
    }

    public static int length(short[] array) {
        return array == null ? 0 : array.length;
    }

    public static int length(int[] array) {
        return array == null ? 0 : array.length;
    }

    public static int length(long[] array) {
        return array == null ? 0 : array.length;
    }

    public static int length(float[] array) {
        return array == null ? 0 : array.length;
    }

    public static int length(double[] array) {
        return array == null ? 0 : array.length;
    }

    public static int length(char[] array) {
        return array == null ? 0 : array.length;
    }

    public static int length(Object[] array) {
        return array == null ? 0 : array.length;
    }

    public static boolean isEmpty(boolean[] values) {
        return values == null || values.length < 1;
    }

    public static boolean isNotEmpty(boolean[] values) {
        return !isEmpty(values);
    }

    public static boolean isEmpty(byte[] values) {
        return values == null || values.length < 1;
    }

    public static boolean isNotEmpty(byte[] values) {
        return !isEmpty(values);
    }

    public static boolean isEmpty(short[] values) {
        return values == null || values.length < 1;
    }

    public static boolean isNotEmpty(short[] values) {
        return !isEmpty(values);
    }

    public static boolean isEmpty(int[] values) {
        return values == null || values.length < 1;
    }

    public static boolean isNotEmpty(int[] values) {
        return !isEmpty(values);
    }

    public static boolean isEmpty(long[] values) {
        return values == null || values.length < 1;
    }

    public static boolean isNotEmpty(long[] values) {
        return !isEmpty(values);
    }

    public static boolean isEmpty(char[] values) {
        return values == null || values.length < 1;
    }

    public static boolean isNotEmpty(char[] values) {
        return !isEmpty(values);
    }

    public static boolean isEmpty(float[] values) {
        return values == null || values.length < 1;
    }

    public static boolean isNotEmpty(float[] values) {
        return !isEmpty(values);
    }

    public static boolean isEmpty(double[] values) {
        return values == null || values.length < 1;
    }

    public static boolean isNotEmpty(double[] values) {
        return !isEmpty(values);
    }

    public static <T> boolean isEmpty(T[] values) {
        return values == null || values.length < 1;
    }

    public static <T> boolean isNotEmpty(T[] values) {
        return !isEmpty(values);
    }

    public static <T> T[] clone(T[] source) {
        return source == null ? null : Arrays.copyOf(source, source.length);
    }

    public static <T> T[] merge(T[] source, T[] target) {
        if (source == null) {
            return target;
        } else if (target == null) {
            return source;
        } else {
            int slen = source.length;
            int tlen = target.length;
            T[] newArray = Arrays.copyOf(source, slen + tlen);
            int i = slen;
            int j = 0;

            for(int len = slen + tlen; i < len; ++j) {
                newArray[i] = target[j];
                ++i;
            }

            return newArray;
        }
    }

    public static <T> T get(T[] array, int i) {
        int len = length(array);
        return i < len ? array[i] : null;
    }

    public static <T> T getLast(T[] array) {
        return isEmpty(array) ? null : array[array.length - 1];
    }

    public static <T> boolean in(T value, T[] values) {
        if (isEmpty(values)) {
            return false;
        } else {
            Object[] var2 = values;
            int var3 = values.length;

            for(int var4 = 0; var4 < var3; ++var4) {
                T v = (T) var2[var4];
                if (value.equals(v)) {
                    return true;
                }
            }

            return false;
        }
    }

    public static <T> boolean notIn(T value, T[] values) {
        return !in(value, values);
    }

    public static <T> List<T> toList(T... values) {
        if (isEmpty(values)) {
            return new ArrayList();
        } else {
            List<T> list = new ArrayList(values.length);
            Object[] var2 = values;
            int var3 = values.length;

            for(int var4 = 0; var4 < var3; ++var4) {
                T value = (T) var2[var4];
                list.add(value);
            }

            return list;
        }
    }

    public static <T> String toString(T[] values) {
        return toString(values, "");
    }

    public static <T> String toString(T[] values, String gap) {
        if (values != null && values.length != 0) {
            if (values.length == 1) {
                return String.valueOf(values[0]);
            } else {
                StringBuilder sb = new StringBuilder();
                sb.append(values[0]);
                int i = 1;

                for(int len = values.length; i < len; ++i) {
                    sb.append(gap).append(values[i]);
                }

                return sb.toString();
            }
        } else {
            return "";
        }
    }

    public static Object newInstance(GenericArrayType targetComponetType, int len) {
        Type ctype = targetComponetType.getGenericComponentType();

        int size;
        for(size = 1; ctype instanceof GenericArrayType; ++size) {
            ctype = ((GenericArrayType)ctype).getGenericComponentType();
        }

        int[] sizes = new int[size];
        sizes[0] = len;
        Object target;
        if (ctype instanceof Class) {
            target = Array.newInstance((Class)ctype, sizes);
        } else if (ctype instanceof ParameterizedType) {
            target = Array.newInstance((Class)((ParameterizedType)ctype).getRawType(), sizes);
        } else {
            Type[] bounds;
            if (ctype instanceof WildcardType) {
                bounds = ((WildcardType)ctype).getUpperBounds();
                target = Array.newInstance((Class)bounds[0], sizes);
            } else {
                if (!(ctype instanceof TypeVariable)) {
                    throw new UnsupportedOperationException(ctype + " is not support");
                }

                bounds = ((TypeVariable)ctype).getBounds();
                target = Array.newInstance((Class)bounds[0], sizes);
            }
        }

        return target;
    }


    private static <T extends Comparable<T>> T maxByNegtive(T[] array, int negtive) {
        if (isEmpty((Object[])array)) {
            return null;
        } else {
            T max = array[0];
            int i = 1;

            for(int len = array.length; i < len; ++i) {
                T data = array[i];
                if (data != null && (max == null || max.compareTo(data) * negtive < 0)) {
                    max = data;
                }
            }

            return max;
        }
    }

    public static <T extends Comparable<T>> T max(T[] array) {
        return maxByNegtive(array, 1);
    }

    public static <T extends Comparable<T>> T min(T[] array) {
        return maxByNegtive(array, -1);
    }
}
