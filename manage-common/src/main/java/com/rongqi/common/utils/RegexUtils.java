package com.rongqi.common.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexUtils {
    public RegexUtils() {
    }

    public static int[] findPosition(String text, String regex) {
        return findPosition(text, regex, 0, text == null ? 0 : text.length());
    }

    public static int[] findPosition(String text, String regex, int start, int end) {
        if (text == null) {
            return null;
        } else {
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(text);
            m.region(start, end);

            int len;
            do {
                if (!m.find()) {
                    return null;
                }

                len = m.groupCount();
            } while(len <= 0);

            return new int[]{m.start(1), m.end(1)};
        }
    }

    public static String findMatch(String text, String regex) {
        return findMatch(text, regex, 0, text == null ? 0 : text.length());
    }

    public static String findMatch(String text, String regex, int start, int end) {
        if (text == null) {
            return null;
        } else {
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(text);
            m.region(start, end);

            int len;
            do {
                if (!m.find()) {
                    return null;
                }

                len = m.groupCount();
            } while(len <= 0);

            return m.group(1);
        }
    }

    public static List<String> findFirstMatch(String text, String regex) {
        return findFirstMatch(text, regex, 0, text == null ? 0 : text.length());
    }

    public static List<String> findFirstMatch(String text, String regex, int start, int end) {
        if (text == null) {
            return null;
        } else {
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(text);
            m.region(start, end);

            int len;
            do {
                if (!m.find()) {
                    return null;
                }

                len = m.groupCount();
            } while(len <= 0);

            List<String> matches = new ArrayList(len);

            for(int i = 1; i <= len; ++i) {
                matches.add(m.group(i));
            }

            return matches;
        }
    }

    public static List<String> findMatches(String text, String regex) {
        return findMatches(text, regex, 0, text == null ? 0 : text.length());
    }

    public static List<String> findMatches(String text, String regex, int start, int end) {
        if (text == null) {
            return null;
        } else {
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(text);
            m.region(start, end);
            ArrayList results = new ArrayList();

            while(m.find()) {
                int len = m.groupCount();

                for(int i = 1; i <= len; ++i) {
                    results.add(m.group(i));
                }
            }

            return results;
        }
    }

    public static LinkedHashMap<String, String> findMatchesMap(String text, String regex) {
        return findMatchesMap(text, regex, false);
    }

    public static LinkedHashMap<String, String> findMatchesMap(String text, String regex, boolean reverse) {
        return findMatchesMap(text, regex, reverse, 0, text == null ? 0 : text.length());
    }

    public static LinkedHashMap<String, String> findMatchesMap(String text, String regex, boolean reverse, int start, int end) {
        List<String> matchList = findMatches(text, regex, start, end);
        LinkedHashMap<String, String> map = new LinkedHashMap();
        int size = matchList.size();
        if (size % 2 != 0) {
            throw new IllegalArgumentException("正则表达式" + regex + "捕获组为偶数个");
        } else {
            Iterator itr = matchList.iterator();

            while(itr.hasNext()) {
                String key = (String)itr.next();
                String value = (String)itr.next();
                if (reverse) {
                    map.put(value, key);
                } else {
                    map.put(key, value);
                }
            }

            return map;
        }
    }

    public static boolean hasMatch(String regex, String text) {
        if (text == null) {
            return regex == null;
        } else if (regex == null) {
            return false;
        } else {
            Matcher m = Pattern.compile(regex).matcher(text);
            return m.find();
        }
    }

    public static boolean isAllHasMatches(String regex, String... texts) {
        String[] var2 = texts;
        int var3 = texts.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            String text = var2[var4];
            if (!hasMatch(regex, text)) {
                return false;
            }
        }

        return false;
    }

    public static boolean isAllMatches(String regex, String... texts) {
        if (regex == null) {
            return false;
        } else {
            String[] var2 = texts;
            int var3 = texts.length;

            for(int var4 = 0; var4 < var3; ++var4) {
                String text = var2[var4];
                if (text == null) {
                    return false;
                }

                if (!text.matches(regex)) {
                    return false;
                }
            }

            return true;
        }
    }

    public static String replaceFirst(String text, String regex, String replaceText) {
        return text == null ? text : text.replaceFirst(regex, replaceText);
    }

    public static String replaceAll(String text, String regex, String replaceText) {
        return text == null ? text : text.replaceAll(regex, replaceText);
    }

    public static String replace(String text, String regex, RegexUtils.ReplaceCallback callback) {
        StringBuffer sb = new StringBuffer();
        Matcher m = Pattern.compile(regex).matcher(text);

        while(true) {
            int len;
            do {
                if (!m.find()) {
                    m.appendTail(sb);
                    return sb.toString();
                }

                len = m.groupCount();
            } while(len < 1);

            int start = m.start();
            StringBuilder group = new StringBuilder(m.group(0));

            for(int i = len; i > 0; --i) {
                String t = callback.toReplacement(m.group(i), i);
                if (t == null) {
                    t = "";
                } else {
                    t = t.replace("\\", "\\\\").replace("$", "\\$");
                }

                group.replace(m.start(i) - start, m.end(i) - start, t);
            }

            m.appendReplacement(sb, group.toString());
        }
    }

    public interface ReplaceCallback {
        String toReplacement(String var1, int var2);
    }
}
