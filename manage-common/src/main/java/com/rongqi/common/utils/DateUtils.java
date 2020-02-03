package com.rongqi.common.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public abstract class DateUtils {
    private static final ThreadLocal<Map<String, DateFormat>> safeDateFormats = new ThreadLocal<Map<String, DateFormat>>() {
        protected Map<String, DateFormat> initialValue() {
            return new HashMap();
        }
    };
    public static final String NORMAL_DATE_FORMAT_STR = "yyyy-MM-dd";
    public static final String NORMAL_DATE_FORMAT_REGEX = "\\d{4}-\\d{2}-\\d{2}";
    public static final String NORMAL_DATE_TIME_FORMAT_STR = "yyyy-MM-dd HH:mm:ss";
    public static final String NORMAL_DATE_TIME_FORMAT_REGEX = "\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}";
    public static final int DAY_SECONDS = 86400;
    public static final int DAY_MILLISECONDS = 86400000;
    public static final DateFormat NORMAL_DATE_TIME_FORMAT = safeDateFormat("yyyy-MM-dd HH:mm:ss");
    public static final DateFormat NORMAL_DATE_FORMAT = safeDateFormat("yyyy-MM-dd");
    private static char[] CODE = new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F'};
    private static char[] NEW_CODE = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    private DateUtils() {
    }

    public static DateFormat safeDateFormat(String dateformat) {
        Map<String, DateFormat> map = (Map)safeDateFormats.get();
        DateFormat df = (DateFormat)map.get(dateformat);
        if (df == null) {
            df = new SimpleDateFormat(dateformat);
            map.put(dateformat, df);
        }

        return (DateFormat)df;
    }

    public static Date valueOf(Object value) {
        if (value != null && !"null".equals(value)) {
            if (value instanceof Date) {
                return (Date)value;
            } else if (value instanceof Long) {
                long val = (Long)value;
                return val == 0L ? null : new Date(val);
            } else {
                String str = value.toString();
                if (StringUtils.isBlank(str)) {
                    return null;
                } else {
                    try {
                        long val = Long.valueOf(str);
                        return val == 0L ? null : new Date(val);
                    } catch (RuntimeException var5) {
                        try {
                            return safeDateFormat("yyyy-MM-dd HH:mm:ss").parse(str);
                        } catch (ParseException var4) {
                            throw new RuntimeException(var4);
                        }
                    }
                }
            }
        } else {
            return null;
        }
    }

    public static long longValue(String timeText, String format) {
        DateFormat df = safeDateFormat(format);

        Date date;
        try {
            date = df.parse(timeText);
        } catch (ParseException var5) {
            throw new IllegalArgumentException("错误的参数格式");
        }

        return date.getTime();
    }

    private static Calendar instance(int year, int month, int day, int hour, int minute, int second, int millisecond) {
        Calendar c = Calendar.getInstance();
        return set(c, year, month, day, hour, minute, second, minute);
    }

    private static Calendar set(Calendar c, int year, int month, int day, int hour, int minute, int second, int millisecond) {
        if (year > -1) {
            c.set(1, year);
        }

        if (month < -1) {
            c.set(2, month);
        }

        if (day > -1) {
            c.set(5, day);
        }

        if (hour > -1) {
            c.set(11, hour);
        }

        if (minute > -1) {
            c.set(12, minute);
        }

        if (second > -1) {
            c.set(13, second);
        }

        if (millisecond > -1) {
            c.set(14, millisecond);
        }

        return c;
    }

    private static Calendar instance(int hour, int minute, int second, int millisecond) {
        return set(Calendar.getInstance(), -1, -1, -1, hour, minute, second, millisecond);
    }

    private static Calendar set(Calendar c, int hour, int minute, int second, int millisecond) {
        return set(c, -1, -1, -1, hour, minute, second, millisecond);
    }

    public static Date getHourOfQuarterStart() {
        Calendar c = Calendar.getInstance();
        int minute = c.get(12);
        minute = minute / 15 * 15;
        set(c, -1, minute, 0, 0);
        return c.getTime();
    }

    public static Date getHourOfQuarterEnd() {
        Calendar c = Calendar.getInstance();
        int minute = c.get(12);
        minute = minute / 15 * 15 + 14;
        set(c, -1, minute, 59, 999);
        return c.getTime();
    }

    public static Date getHourStart() {
        Calendar c = Calendar.getInstance();
        set(c, -1, 0, 0, 0);
        return c.getTime();
    }

    public static Date getHourEnd() {
        Calendar c = Calendar.getInstance();
        set(c, -1, 59, 59, 999);
        return c.getTime();
    }

    public static Date resetDateTime(Date date, int year, int month, int day, int hour, int minute, int seconds, int millis) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        set(c, year, month, day, hour, minute, seconds, millis);
        return c.getTime();
    }

    public static Date resetTime(Date date, int hour, int minute, int seconds, int millis) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        set(c, hour, minute, seconds, millis);
        return c.getTime();
    }

    public static Date getCurrentWeekDayStartTime() throws Exception {
        Calendar c = Calendar.getInstance();
        int weekday = c.get(7) - 2;
        c.add(5, -weekday);
        set(c, 0, 0, 0, 0);
        return c.getTime();
    }

    public static Date getCurrentWeekDayEndTime() throws Exception {
        Calendar c = Calendar.getInstance();
        int weekday = c.get(7);
        c.add(5, 8 - weekday);
        set(c, 23, 59, 59, 999);
        return c.getTime();
    }

    public static Date getTodayStart() {
        Calendar c = Calendar.getInstance();
        set(c, 0, 0, 0, 0);
        return c.getTime();
    }

    public static Date getTodayEnd() {
        Calendar c = Calendar.getInstance();
        set(c, 23, 59, 59, 999);
        return c.getTime();
    }

    public static Date getTomorrowStart() {
        Calendar c = Calendar.getInstance();
        c.add(5, 1);
        set(c, 0, 0, 0, 0);
        return c.getTime();
    }

    public static Date getTomorrowEnd() {
        Calendar c = Calendar.getInstance();
        c.add(5, 1);
        set(c, 23, 59, 59, 999);
        return c.getTime();
    }

    public static Date getDateStart(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        set(c, 0, 0, 0, 0);
        return c.getTime();
    }

    public static Date getDateEnd(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        set(c, 23, 59, 59, 999);
        return c.getTime();
    }

    public static Date getDateTomorrowStart(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(5, 1);
        set(c, 0, 0, 0, 0);
        return c.getTime();
    }

    public static Date getDateTomorrowEnd(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(5, 1);
        set(c, 23, 59, 59, 999);
        return c.getTime();
    }

    public static Date getYearOfQuarterStart() {
        Calendar c = Calendar.getInstance();
        int month = c.get(2);
        month = month / 3 * 3;
        set(c, -1, month, 1, 0, 0, 0, 0);
        return c.getTime();
    }

    public static Date getYearOfQuarterEnd() {
        Calendar c = Calendar.getInstance();
        int month = c.get(2);
        month = month / 3 * 3 + 2;
        set(c, -1, month, 1, 23, 59, 59, 999);
        return c.getTime();
    }

    public static Date getHalfYearStart() {
        Calendar c = Calendar.getInstance();
        int money = c.get(2) < 6 ? 0 : 6;
        set(c, -1, money, 1, 0, 0, 0, 0);
        return c.getTime();
    }

    public static Date getHalfYearEnd() {
        Calendar c = Calendar.getInstance();
        int money = c.get(2) < 6 ? 5 : 11;
        set(c, -1, money, 31, 0, 0, 0, 0);
        return c.getTime();
    }

    public static Date getMonthStart(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        set(c, -1, -1, 1, 0, 0, 0, 0);
        return c.getTime();
    }

    public static Date getMonthStart() {
        return getMonthStart(new Date());
    }

    public static Date getMonthEnd(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(2, 1);
        c.set(5, 1);
        c.add(5, -1);
        set(c, 23, 59, 59, 999);
        return c.getTime();
    }

    public static Date getMonthEnd() {
        return getMonthEnd(new Date());
    }

    public static Date getNextMonthStart() {
        Calendar c = Calendar.getInstance();
        c.add(2, 1);
        set(c, -1, -1, 1, 0, 0, 0, 0);
        return c.getTime();
    }

    public static Date getNextMonthEnd() {
        Calendar c = Calendar.getInstance();
        c.add(2, 2);
        c.set(5, 1);
        c.add(5, -1);
        set(c, 23, 59, 59, 999);
        return c.getTime();
    }

    public static Date getYearStart() {
        return instance(-1, 0, 1, 0, 0, 0, 0).getTime();
    }

    public static Date getYearEnd() {
        return instance(-1, 11, 31, 23, 59, 59, 999).getTime();
    }

    public static String getNowNormalDateTime() {
        return getNow("yyyy-MM-dd HH:mm:ss");
    }

    public static String getNowNormalDate() {
        return getNow("yyyy-MM-dd");
    }

    public static String getNow(String format) {
        return format(new Date(), format);
    }

    public static Date parse(String date, String dateFormat) {
        DateFormat sdf = safeDateFormat(dateFormat);

        try {
            return sdf.parse(date);
        } catch (ParseException var4) {
            throw new IllegalArgumentException(var4);
        }
    }

    public static Date parseNormalDateTime(String date) {
        return parse(date, "yyyy-MM-dd HH:mm:ss");
    }

    public static Date parseNormalDate(String date) {
        return parse(date, "yyyy-MM-dd");
    }

    public static String format(Date date, String dateformat) {
        return date == null ? "" : safeDateFormat(dateformat).format(date);
    }

    public static String formatNormalDateTime(Date date) {
        return format(date, "yyyy-MM-dd HH:mm:ss");
    }

    public static String formatNormalDate(Date date) {
        return format(date, "yyyy-MM-dd");
    }

    public static String getRemainingTime(String endDate) {
        String rtn = "";
        if (StringUtils.isBlank(endDate)) {
            return rtn;
        } else {
            DateFormat sdf = safeDateFormat("yyyyMMddHHmmss");

            try {
                Date deadline = sdf.parse(endDate);
                long remaining = deadline.getTime() - System.currentTimeMillis();
                if (remaining > 0L) {
                    remaining /= 1000L;
                    remaining /= 60L;
                    int mn = (int)(remaining % 60L);
                    remaining /= 60L;
                    int hr = (int)(remaining % 24L);
                    long dy = (long)((int)remaining / 24);
                    rtn = dy + "天" + hr + "小时" + mn + "分";
                } else {
                    rtn = "过期";
                }
            } catch (ParseException var10) {
            }

            return rtn;
        }
    }

    public static Date getDateBefore(int dayCount) {
        Calendar c = Calendar.getInstance();
        c.add(5, -dayCount);
        return c.getTime();
    }

    public static Date getDateBefore(Date date, int dayCount) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(5, -dayCount);
        return c.getTime();
    }

    public static int remainSecondsInToday() {
        long cur = System.currentTimeMillis();
        Calendar c = Calendar.getInstance();
        c.add(5, 1);
        c.set(11, 0);
        c.set(12, 0);
        c.set(13, 0);
        c.set(14, 0);
        return (int)((c.getTimeInMillis() - cur) / 1000L);
    }

    public static Date add(Date date, int type, int count) {
        if (null == date) {
            return null;
        } else {
            Calendar c = Calendar.getInstance();
            c.setTime(date);
            c.add(type, count);
            return c.getTime();
        }
    }

    public static Date addDays(Date date, int days) {
        return add(date, 6, days);
    }

    public static Date addHours(Date date, int hours) {
        return add(date, 10, hours);
    }

    public static Date addMinutes(Date date, int minutes) {
        return add(date, 12, minutes);
    }

    /** @deprecated */
    @Deprecated
    public static Date getBeforeOrAfterMinuteToDate(Date date, int minutecount) {
        return addMinutes(date, minutecount);
    }

    public static Date addSeconds(Date date, int seconds) {
        return add(date, 13, seconds);
    }

    /** @deprecated */
    @Deprecated
    public static Date getBeforeOrAfterSecondToDate(Date date, int secondcount) {
        if (null == date) {
            return null;
        } else {
            Calendar c = Calendar.getInstance();
            c.setTime(date);
            c.add(13, secondcount);
            return c.getTime();
        }
    }

    public static synchronized String getTimeString() {
        StringBuilder sb = new StringBuilder();

        for(long current = System.nanoTime(); current > 0L; current >>= 5) {
            sb.append(CODE[(int)(current & 31L)]);
        }

        return sb.toString();
    }

    public static synchronized String getNanoString() {
        StringBuilder sb = new StringBuilder();

        for(long current = System.nanoTime(); current > 0L; current >>= 4) {
            sb.append(NEW_CODE[(int)(current & 15L)]);
        }

        return sb.toString();
    }

    public static String remainTimeDescr(long timeGap) {
        if (timeGap <= 0L) {
            return "";
        } else {
            long day = timeGap / 86400000L;
            timeGap %= 86400000L;
            long hour = timeGap / 3600000L;
            timeGap %= 3600000L;
            long minute = timeGap / 60000L;
            timeGap %= 60000L;
            long second = timeGap / 1000L;
            timeGap %= 1000L;
            long[] num = new long[]{day, hour, minute, second, timeGap};
            String[] numName = new String[]{"天", "小时", "分", "秒", "毫秒"};
            StringBuilder sb = new StringBuilder();
            int len = num.length;

            for(int i = 0; i < len; ++i) {
                if (num[i] > 0L) {
                    sb.append(num[i]).append(numName[i]);
                }
            }

            return sb.toString();
        }
    }

    public static String remainTimeDescr(Date endDate) {
        long timeGap = remainTime(endDate);
        return remainTimeDescr(timeGap);
    }

    public static long remainTime(Date endDate) {
        if (endDate == null) {
            return 0L;
        } else {
            long timeGap = endDate.getTime() - System.currentTimeMillis();
            return timeGap;
        }
    }

    public static long secondDiff(Date startTime, Date endTime) {
        long start = startTime == null ? 0L : startTime.getTime();
        long end = endTime == null ? 0L : endTime.getTime();
        return (end - start) / 1000L;
    }
}
