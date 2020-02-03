package com.rongqi.common.utils;

import java.util.Arrays;
import java.util.List;

public class IpUtils {

    private static final long MAX_IP = 4294967295L;
    private static final char DOT = '.';
    public static final String OX0_OXFF_REGEX = "([0-9]{1,2}|[0-1][0-9]{2}|2[0-4][0-9]|25[0-5])";
    public static final String IP_REGEX = "([0-9]{1,2}|[0-1][0-9]{2}|2[0-4][0-9]|25[0-5])(?:\\.([0-9]{1,2}|[0-1][0-9]{2}|2[0-4][0-9]|25[0-5])){3}";
    public static final List<String> LOCAL_IPS = Arrays.asList("0:0:0:0:0:0:0:1", "localhost", "0.0.0.0", "0.0.0.0.0.0.1");
    public static final long[][] BETWEENS = new long[][]{{S2L("127.0.0.0"), S2L("127.255.255.255")}, {S2L("10.0.0.0"), S2L("10.255.255.255")}, {S2L("172.16.0.0"), S2L("172.31.255.255")}, {S2L("192.168.0.0"), S2L("192.168.255.255")}};
    private static final long FIRST = 4278190080L;
    private static final long SECOND = 16711680L;
    private static final long THIRD = 65280L;
    private static final long FOURTH = 255L;

    private IpUtils() {
    }

    public static long S2L(String ipv4) {
        if (ipv4 == null) {
            throw new NullPointerException("参数不能为空");
        } else {
            ipv4 = ipv4.trim();
            if (!ipv4.matches("([0-9]{1,2}|[0-1][0-9]{2}|2[0-4][0-9]|25[0-5])(?:\\.([0-9]{1,2}|[0-1][0-9]{2}|2[0-4][0-9]|25[0-5])){3}")) {
                throw new IllegalArgumentException("IPv4地址格式错误");
            } else {
                long lip = 0L;
                int begin = 0;

                String num;
                for(int end = ipv4.indexOf(46); end > 0; end = ipv4.indexOf(46, begin)) {
                    num = ipv4.substring(begin, end);
                    lip = lip << 8 | Long.parseLong(num);
                    begin = end + 1;
                }

                num = ipv4.substring(begin);
                lip = lip << 8 | Long.parseLong(num);
                return lip;
            }
        }
    }

    public static String L2S(long ip) {
        if (ip >= 0L && ip <= 4294967295L) {
            StringBuilder sb = new StringBuilder();
            sb.append((ip & 4278190080L) >> 24).append('.');
            sb.append((ip & 16711680L) >> 16).append('.');
            sb.append((ip & 65280L) >> 8).append('.');
            sb.append(ip & 255L);
            return sb.toString();
        } else {
            throw new IllegalArgumentException("IPv4地址数值超出范围");
        }
    }

    public static String getNetAddress(String ip, String mask) {
        return L2S(getNetLongAddr(ip, mask));
    }

    public static long getNetLongAddr(String ip, String mask) {
        long ipLong = S2L(ip);
        long maskLong = S2L(mask);
        return ipLong & maskLong;
    }

    public static boolean isIpAddr(String ip) {
        return ip != null && ip.matches("([0-9]{1,2}|[0-1][0-9]{2}|2[0-4][0-9]|25[0-5])(?:\\.([0-9]{1,2}|[0-1][0-9]{2}|2[0-4][0-9]|25[0-5])){3}");
    }

    public static String[] getIpInfo(String ipAddrWithMask) {
        if (ipAddrWithMask == null) {
            throw new NullPointerException("address is empty");
        } else if (!ipAddrWithMask.matches("\\d+\\.\\d+\\.\\d+\\.\\d+/\\d+")) {
            throw new IllegalArgumentException(ipAddrWithMask + " is not match!");
        } else {
            int index = ipAddrWithMask.indexOf(47);
            String ipAddr = ipAddrWithMask.substring(0, index);
            String ipMaskNum = ipAddrWithMask.substring(index + 1);
            int count = 0;
            int num = Integer.parseInt(ipMaskNum);
            StringBuilder sb = new StringBuilder();

            while(true) {
                ++count;
                if (count >= 5) {
                    sb.deleteCharAt(sb.length() - 1);
                    return new String[]{ipAddr, sb.toString(), ipMaskNum};
                }

                if (num < 1) {
                    sb.append("0.");
                } else if (num >= 8) {
                    sb.append("255.");
                } else {
                    int c = 0;

                    int d;
                    for(d = 0; c < num; d += 1 << 8 - c) {
                        ++c;
                    }

                    sb.append(d).append('.');
                }

                num -= 8;
            }
        }
    }

    public static boolean isLanIp(String ipv4) {
        if (ipv4 == null) {
            return false;
        } else if (LOCAL_IPS.contains(ipv4)) {
            return true;
        } else {
            long ipLong = S2L(ipv4);
            long[][] var3 = BETWEENS;
            int var4 = var3.length;

            for(int var5 = 0; var5 < var4; ++var5) {
                long[] between = var3[var5];
                if (ipLong >= between[0] && ipLong <= between[1]) {
                    return true;
                }
            }

            return false;
        }
    }

}
