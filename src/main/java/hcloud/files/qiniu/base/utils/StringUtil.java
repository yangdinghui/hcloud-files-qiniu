package hcloud.files.qiniu.base.utils;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public final class StringUtil {

    public static boolean isEmpty(String str) {
        return (str == null || str.equals(""));
    }


    public static String cutOutLast(String str, String symbol) {
        if (isEmpty(str)) {
            return null;
        }
        return str.substring(0, str.lastIndexOf(symbol));
    }


    public static String joinString(List list, String symbol) {
        String result = "";
        if (list != null) {
            for (Object o : list) {
                String temp = o.toString();
                if (temp.trim().length() > 0)
                    result = result + temp + symbol;
            }
            if (result.length() > 1) {
                result = result.substring(0, result.length() - 1);
            }
        }
        return result;
    }


    public static boolean requals(String str1, String str2) {
        if (str1 != null && str2 != null) {
            str2 = str2.replaceAll("\\s*", "");
            String[] arr = str2.split(",");
            for (String t : arr) {
                if (t.equals(str1.trim())) {
                    return true;
                }
            }
        }
        return false;
    }


    public static boolean requals(String str1, String str2, String split) {
        if (str1 != null && str2 != null) {
            str2 = str2.replaceAll("\\s*", "");
            String[] arr = str2.split(split);
            for (String t : arr) {
                if (t.equals(str1.trim())) {
                    return true;
                }
            }
        }
        return false;
    }


    public static String subStringOmit(String subject, int size) {
        if (subject != null && subject.length() > size) {
            subject = subject.substring(0, size) + "...";
        }
        return subject;
    }


    public static String subStringSymbol(String str, int len, String symbol) {
        String temp = "";
        if (str != null && str.length() > len) {
            temp = str.substring(0, len) + symbol;
        }
        return temp;
    }


    public static String getHideEmailPrefix(String email) {
        if (null != email) {
            int index = email.lastIndexOf('@');
            if (index > 0) {
                email = repeat("*", index).concat(email.substring(index));
            }
        }
        return email;
    }


    public static String repeat(String src, int num) {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < num; i++)
            s.append(src);
        return s.toString();
    }


    public static String ltrim(String str1, int num) {
        String tt = "";
        if (!isEmpty(str1) && str1.length() >= num) {
            tt = str1.substring(num, str1.length());
        }
        return tt;
    }


    public static String rtrim(String str, int num) {
        if (!isEmpty(str) && str.length() > num) {
            str = str.substring(0, str.length() - num);
        }
        return str;
    }


    public static List<String> parseString2List(String src, String pattern) {
        List<String> list = new ArrayList<String>();
        if (src != null) {
            String[] tt = src.split(pattern);
            list.addAll(Arrays.asList(tt));
        }
        return list;
    }


    public static String formatDouble(double f, String format) {
        DecimalFormat df = new DecimalFormat(format);
        return df.format(f);
    }


    public static String left(String input, int count) {
        if (isEmpty(input)) {
            return "";
        }
        count = (count > input.length()) ? input.length() : count;
        return input.substring(0, count);
    }


    public static String right(String input, int count) {
        if (isEmpty(input)) {
            return "";
        }
        count = (count > input.length()) ? input.length() : count;
        return input.substring(input.length() - count, input.length());
    }


    public static String replaceBlank(String str) {
        if (str != null) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(str);
            str = m.replaceAll("");
        }
        return str;
    }


    public static String string2Unicode(String string) {
        StringBuilder uni = new StringBuilder();
        for (int i = 0; i < string.length(); i++) {
            String temp = "\\u" + String.valueOf(Integer.toHexString(string.charAt(i)));
            uni.append(temp);
        }
        return uni.toString();
    }


    public static String unicode2String(String unicode) {
        StringBuilder str = new StringBuilder();
        String[] hex = unicode.split("\\\\u");
        for (int i = 1; i < hex.length; i++) {
            int data = Integer.parseInt(hex[i], 16);
            str.append((char) data);
        }
        return str.toString();
    }


    public static String trimPunct(String str) {
        if (isEmpty(str)) {
            return "";
        }
        return str.replaceAll("[\\pP\\p{Punct}]", "");
    }


    public static int countSubStr(String string, String str) {
        if (str == null || str.length() == 0 || string == null || string.length() == 0) {
            return 0;
        }
        int count = 0;
        int index = 0;
        while ((index = string.indexOf(str, index)) != -1) {
            count++;
            index += str.length();
        }
        return count;
    }


    public static String replaceFirst(String s, String sub, String with) {
        int i = s.indexOf(sub);
        if (i == -1) {
            return s;
        }
        return s.substring(0, i) + with + s.substring(i + sub.length());
    }


    public static String replaceLast(String s, String sub, String with) {
        int i = s.lastIndexOf(sub);
        if (i == -1) {
            return s;
        }
        return s.substring(0, i) + with + s.substring(i + sub.length());
    }


    public static String remove(String s, String sub) {
        int c = 0;
        int sublen = sub.length();
        if (sublen == 0) {
            return s;
        }
        int i = s.indexOf(sub, c);
        if (i == -1) {
            return s;
        }
        StringBuilder sb = new StringBuilder(s.length());
        do {
            sb.append(s.substring(c, i));
            c = i + sublen;
        } while ((i = s.indexOf(sub, c)) != -1);
        if (c < s.length()) {
            sb.append(s.substring(c, s.length()));
        }
        return sb.toString();
    }


    public static String upperFirstChar(String str) {
        if (isEmpty(str)) {
            return "";
        }
        char[] cs = str.toCharArray();
        if (cs[0] >= 'a' && cs[0] <= 'z') {
            cs[0] = (char) (cs[0] - ' ');
        }
        return String.valueOf(cs);
    }


    public static String lowerFirstChar(String str) {
        if (isEmpty(str)) {
            return "";
        }
        char[] cs = str.toCharArray();
        if (cs[0] >= 'A' && cs[0] <= 'Z') {
            cs[0] = (char) (cs[0] + ' ');
        }
        return String.valueOf(cs);
    }


    public static boolean rigthEquals(String str1, String str2, int length) {
        return right(str1, length).equals(right(str2, length));
    }


    public static boolean leftEquals(String str1, String str2, int length) {
        return left(str1, length).equals(left(str2, length));
    }


    public static String getRandomForOrder(int count) {
        String retMes = "";
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String orderId = df.format(new Date());
        StringBuffer sb = new StringBuffer();
        String str = "0123456789";
        Random r = new Random();
        for (int i = 0; i < count; i++) {
            int num = r.nextInt(str.length());
            sb.append(str.charAt(num));
            str = str.replace(str.charAt(num) + "", "");
        }
        return orderId + sb.toString();
    }


    public static String getRandomForFormat(String dateFormat, int count) {
        String retMes = "";
        SimpleDateFormat df = new SimpleDateFormat(dateFormat);
        String orderId = df.format(new Date());
        StringBuffer sb = new StringBuffer();
        String str = "0123456789";
        Random r = new Random();
        for (int i = 0; i < count; i++) {
            int num = r.nextInt(str.length());
            sb.append(str.charAt(num));
            str = str.replace(str.charAt(num) + "", "");
        }
        return orderId + sb.toString();
    }


    public static String getRandom(int length) {
        String val = "";
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            val = val + String.valueOf(random.nextInt(10));
        }
        return val;
    }


    public static List<String> subStrRegex(String soap, String regex) {
        if (isEmpty(soap) || isEmpty(regex)) {
            return null;
        }
        List<String> list = new ArrayList<String>();
        Pattern pattern = Pattern.compile(regex);
        Matcher m = pattern.matcher(soap);
        while (m.find()) {
            int i = 1;
            list.add(m.group(i));
            i++;
        }
        return list;
    }


    public static String subStrFristRegex(String soap, String regex) {
        if (isEmpty(soap) || isEmpty(regex)) {
            return "";
        }
        Pattern pattern = Pattern.compile(regex);
        Matcher m = pattern.matcher(soap);
        if (m.find()) {
            return m.group(1);
        }
        return "";
    }
}
