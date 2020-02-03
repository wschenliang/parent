package com.rongqi.common.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

public class ByteUtils {

    public static final byte ZERO = 0;
    public static final byte ONE = 1;

    private ByteUtils(){}

    public static byte parse(Object value){
        if(value == null){
            throw new NullPointerException();
        }
        if(value instanceof Number){
            return ((Number)value).byteValue();
        }else{
            if(value instanceof Boolean){
                return (((Boolean)value) ? ONE : ZERO);
            } else {
                String str = value.toString();
                if(StringUtils.isBlank(str)){
                    return ZERO;
                }
                return Byte.parseByte(str);
            }
        }
    }

    public static byte parse(Object value, byte defaultValue){
        if(value == null){
            return defaultValue;
        }
        return parse(value);
    }

    public static Byte valueOf(Object value){
        if(value == null || "null".equals(value)){
            return null;
        } else if(value instanceof Byte){
            return (Byte)value;
        } else if(value instanceof String){
            if(StringUtils.isBlank((String)value)){
                return null;
            }
        }
        return parse(value);
    }

    public static Byte[] toArray(String data, String split){
        if(data == null || data.length() == 0){
            return null;
        }
        String[] arr = data.split(split);
        int i = 0, len = arr.length;
        Byte[] dataArr = new Byte[len];
        for(String str : arr) {
            if(StringUtils.isBlank(str)) {
                continue;
            }
            dataArr[i++] = Byte.valueOf(str);
        }
        return Arrays.copyOf(dataArr, i);
    }

    public static byte[] inputToByte(InputStream istream) throws IOException {
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            byte[] buff = new byte[10240];
            int index = istream.read(buff);
            while (index != -1) {
                outputStream.write(buff, 0, index);
                index = istream.read(buff);
            }
            outputStream.flush();
            return outputStream.toByteArray();
        }finally {
            if (istream != null){
                CloseUtils.close(istream);
            }
        }

    }
}
