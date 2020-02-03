package com.rongqi.common.utils;

/**
 *
 * @author chenliang
 * @time 2019/11/24
 * @email cleverlove8@outlook.com
 */
public class CloseUtils {

    public static void close(AutoCloseable... streams){
        for (AutoCloseable stream : streams) {
            if (stream != null){
                try {
                    stream.close();
                } catch (Exception ignored) {
                }
            }
        }
    }

}
