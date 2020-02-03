package com.rongqi.common.utils;

import java.lang.reflect.Type;
import java.util.Map;

/**
 *
 * @author chenliang
 * @time 2019/11/17
 * @email cleverlove8@outlook.com
 */
public class MapUtils {


    public static boolean isNotEmpty(Map<String, String> properties) {
        return properties != null && properties.keySet().size() >= 1;
    }

    public static boolean isEmpty(Map<Class<?>, Map<Type, Type>> typeMap) {
        return typeMap == null || typeMap.keySet().size() == 0;
    }
}
