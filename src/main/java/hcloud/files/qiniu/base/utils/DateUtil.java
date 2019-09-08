package hcloud.files.qiniu.base.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class DateUtil {
    private static final Logger log = LoggerFactory.getLogger(DateUtil.class);


    private static final String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";


    private static final String DATE_FORMAT = "yyyy-MM-dd";


    private static final String TIME_FORMAT = "HH:mm:ss";


    private static ThreadLocal<SimpleDateFormat> ThreadDateTime = new ThreadLocal();
    private static ThreadLocal<SimpleDateFormat> ThreadDate = new ThreadLocal();
    private static ThreadLocal<SimpleDateFormat> ThreadTime = new ThreadLocal();

    private static SimpleDateFormat DateTimeInstance() {
        SimpleDateFormat df = ThreadDateTime.get();
        if (df == null) {
            df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            ThreadDateTime.set(df);
        }
        return df;
    }

    private static SimpleDateFormat DateInstance() {
        SimpleDateFormat df = ThreadDate.get();
        if (df == null) {
            df = new SimpleDateFormat("yyyy-MM-dd");
            ThreadDate.set(df);
        }
        return df;
    }

    private static SimpleDateFormat TimeInstance() {
        SimpleDateFormat df = ThreadTime.get();
        if (df == null) {
            df = new SimpleDateFormat("HH:mm:ss");
            ThreadTime.set(df);
        }
        return df;
    }


    private static SimpleDateFormat DateFormatInstance(String format) {
        return new SimpleDateFormat(format);
    }


    public static String currDateFormat(String format) {
        return DateFormatInstance(format).format(new Date());
    }


    public static String currentDateTime() {
        return DateTimeInstance().format(new Date());
    }


    public static String dateTime(Date date) {
        return DateTimeInstance().format(date);
    }


    public static Date dateTime(String datestr) {
        Date date = null;
        if (StringUtil.isEmpty(datestr)) {
            return null;
        }
        try {
            date = DateTimeInstance().parse(datestr);
        } catch (ParseException e) {
            log.error("日期转换异常", e);
        }
        return date;
    }


    public static String currentDate() {
        return DateInstance().format(new Date());
    }


    public static String date(Date date) {
        return DateInstance().format(date);
    }


    public static Date date(String dateStr) {
        Date date = null;
        if (StringUtil.isEmpty(dateStr)) {
            return null;
        }
        try {
            date = DateInstance().parse(dateStr);
        } catch (ParseException e) {
            log.error("日期转换异常", e);
        }
        return date;
    }


    public static String currentTime() {
        return TimeInstance().format(new Date());
    }


    public static String time(Date date) {
        return TimeInstance().format(date);
    }


    public static Date time(String dateStr) {
        Date date = null;
        if (StringUtil.isEmpty(dateStr)) {
            return null;
        }
        try {
            date = TimeInstance().parse(dateStr);
        } catch (ParseException e) {
            log.error("时间转换异常", e);
        }
        return date;
    }


    public static Date year(int year) {
        Calendar Cal = Calendar.getInstance();
        Cal.setTime(new Date());
        Cal.add(1, year);
        return Cal.getTime();
    }


    public static Date year(Date date, int year) {
        Calendar Cal = Calendar.getInstance();
        Cal.setTime(date);
        Cal.add(1, year);
        return Cal.getTime();
    }


    public static Date month(int month) {
        Calendar Cal = Calendar.getInstance();
        Cal.setTime(new Date());
        Cal.add(2, month);
        return Cal.getTime();
    }


    public static Date month(Date date, int month) {
        Calendar Cal = Calendar.getInstance();
        Cal.setTime(date);
        Cal.add(2, month);
        return Cal.getTime();
    }


    public static Date day(int day) {
        Calendar Cal = Calendar.getInstance();
        Cal.setTime(new Date());
        Cal.add(6, day);
        return Cal.getTime();
    }


    public static Date day(Date date, int day) {
        Calendar Cal = Calendar.getInstance();
        Cal.setTime(date);
        Cal.add(6, day);
        return Cal.getTime();
    }


    public static Date hour(float hour) {
        Calendar Cal = Calendar.getInstance();
        Cal.setTime(new Date());
        Cal.add(12, (int) (hour * 60.0F));
        return Cal.getTime();
    }


    public static Date hour(Date date, float hour) {
        Calendar Cal = Calendar.getInstance();
        Cal.setTime(date);
        Cal.add(12, (int) (hour * 60.0F));
        return Cal.getTime();
    }


    public static Date minute(int minute) {
        Calendar Cal = Calendar.getInstance();
        Cal.setTime(new Date());
        Cal.add(12, minute);
        return Cal.getTime();
    }


    public static Date minute(Date date, int minute) {
        Calendar Cal = Calendar.getInstance();
        Cal.setTime(date);
        Cal.add(12, minute);
        return Cal.getTime();
    }


    public static boolean isDate(String date) {
        try {
            DateTimeInstance().parse(date);
            return true;
        } catch (ParseException e) {
            e.printStackTrace();

            return false;
        }
    }


    public static long subtract(Date date1, Date date2) {
        return (date2.getTime() - date1.getTime()) / 1000L;
    }


    public static long subtract(String date1, String date2) {
        long rs = 0L;
        try {
            Date start = DateTimeInstance().parse(date1);
            Date end = DateTimeInstance().parse(date2);
            long cha = (end.getTime() - start.getTime()) / 1000L;
            rs = cha;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return rs;
    }


    public static int subtractMinute(String date1, String date2) {
        int rs = 0;
        try {
            Date start = DateTimeInstance().parse(date1);
            Date end = DateTimeInstance().parse(date2);
            long cha = (end.getTime() - start.getTime()) / 1000L;
            rs = (int) cha / 60;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return rs;
    }


    public static int subtractMinute(Date date1, Date date2) {
        long cha = date2.getTime() - date1.getTime();
        return (int) cha / 60000;
    }


    public static int subtractHour(Date date1, Date date2) {
        long cha = (date2.getTime() - date1.getTime()) / 1000L;
        return (int) cha / 3600;
    }


    public static int subtractHour(String date1, String date2) {
        int rs = 0;
        try {
            Date start = DateTimeInstance().parse(date1);
            Date end = DateTimeInstance().parse(date2);
            long cha = (end.getTime() - start.getTime()) / 1000L;
            rs = (int) cha / 3600;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return rs;
    }


    public static int subtractDay(String date1, String date2) {
        int rs = 0;
        try {
            Date start = DateTimeInstance().parse(date1);
            Date end = DateTimeInstance().parse(date2);
            long sss = (end.getTime() - start.getTime()) / 1000L;
            rs = (int) sss / 86400;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return rs;
    }

    public static int subtractMonth(String date1, String date2) {
        int result;
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        try {
            c1.setTime(DateInstance().parse(date1));
            c2.setTime(DateInstance().parse(date2));
            int year1 = c1.get(1);
            int month1 = c1.get(2);
            int year2 = c2.get(1);
            int month2 = c2.get(2);
            if (year1 == year2) {
                result = month2 - month1;
            } else {
                result = 12 * (year2 - year1) + month2 - month1;
            }
        } catch (ParseException e) {
            e.printStackTrace();
            result = -1;
        }
        return result;
    }


    public static int subtractMonth(Date date1, Date date2) {
        int result;
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(date1);
        c2.setTime(date2);
        int year1 = c1.get(1);
        int month1 = c1.get(2);
        int year2 = c2.get(1);
        int month2 = c2.get(2);
        if (year1 == year2) {
            result = month2 - month1;
        } else {
            result = 12 * (year2 - year1) + month2 - month1;
        }
        return result;
    }


    public static int subtractYear(String date1, String date2) {
        int result;
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        try {
            c1.setTime(DateInstance().parse(date1));
            c2.setTime(DateInstance().parse(date2));
            int year1 = c1.get(1);
            int year2 = c2.get(1);
            result = year2 - year1;
        } catch (ParseException e) {
            e.printStackTrace();
            result = -1;
        }
        return result;
    }


    public static int subtractYear(Date date1, Date date2) {
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(date1);
        c2.setTime(date2);
        int year1 = c1.get(1);
        int year2 = c2.get(1);
        return year2 - year1;
    }


    public static String subtractTime(String date1, String date2) {
        String result = "";
        try {
            Date start = DateTimeInstance().parse(date1);
            Date end = DateTimeInstance().parse(date2);
            long sss = (end.getTime() - start.getTime()) / 1000L;
            int hh = (int) sss / 3600;
            int mm = (int) (sss - (hh * 60 * 60)) / 60;
            int ss = (int) (sss - (hh * 60 * 60) - (mm * 60));
            result = hh + ":" + mm + ":" + ss;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }


    public static String subtractDate(String date1, String date2) {
        String result = "";
        try {
            Date start = DateTimeInstance().parse(date1);
            Date end = DateTimeInstance().parse(date2);
            long sss = (end.getTime() - start.getTime()) / 1000L;
            int dd = (int) sss / 86400;
            int hh = (int) (sss - (dd * 60 * 60 * 24)) / 3600;
            int mm = (int) (sss - (dd * 60 * 60 * 24) - (hh * 60 * 60)) / 60;
            int ss = (int) (sss - (dd * 60 * 60 * 24) - (hh * 60 * 60) - (mm * 60));
            result = dd + "-" + hh + ":" + mm + ":" + ss;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }


    public static int subDay(Date startTime, Date endTime) {
        int days = 0;
        Calendar can1 = Calendar.getInstance();
        can1.setTime(startTime);
        Calendar can2 = Calendar.getInstance();
        can2.setTime(endTime);
        int year1 = can1.get(1);
        int year2 = can2.get(1);

        Calendar can = null;
        if (can1.before(can2)) {
            days -= can1.get(6);
            days += can2.get(6);
            can = can1;
        } else {
            days -= can2.get(6);
            days += can1.get(6);
            can = can2;
        }
        for (int i = 0; i < Math.abs(year2 - year1); i++) {
            days += can.getActualMaximum(6);
            can.add(1, 1);
        }

        return days;
    }


    public static int subDay(String startTime, String endTime) {
        int days = 0;
        try {
            Date date1 = DateInstance().parse(DateInstance().format(DateTimeInstance().parse(startTime)));
            Date date2 = DateInstance().parse(DateInstance().format(DateTimeInstance().parse(endTime)));
            Calendar can1 = Calendar.getInstance();
            can1.setTime(date1);
            Calendar can2 = Calendar.getInstance();
            can2.setTime(date2);
            int year1 = can1.get(1);
            int year2 = can2.get(1);

            Calendar can = null;
            if (can1.before(can2)) {
                days -= can1.get(6);
                days += can2.get(6);
                can = can1;
            } else {
                days -= can2.get(6);
                days += can1.get(6);
                can = can2;
            }
            for (int i = 0; i < Math.abs(year2 - year1); i++) {
                days += can.getActualMaximum(6);
                can.add(1, 1);
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return days;
    }


    public static long subtimeBurst(String startDate, String endDate, String timeBurst) throws ParseException {
        Date start = DateTimeInstance().parse(startDate);
        Date end = DateTimeInstance().parse(endDate);
        return subtimeBurst(start, end, timeBurst);
    }


    public static long subtimeBurst(Date startDate, Date endDate, String timeBurst) throws ParseException {
        long second = 0L;
        Pattern p = Pattern.compile("^\\d{2}:\\d{2}-\\d{2}:\\d{2}");
        Matcher m = p.matcher(timeBurst);
        boolean falg = false;
        if (startDate.after(endDate)) {
            Date temp = startDate;
            startDate = endDate;
            endDate = temp;
            falg = true;
        }
        if (m.matches()) {
            String[] a = timeBurst.split("-");
            int day = subDay(startDate, endDate);
            if (day > 0) {
                long firstMintues = 0L;
                long lastMintues = 0L;
                long daySecond = 0L;
                String strDayStart = DateInstance().format(startDate) + " " + a[0] + ":00";
                String strDayEnd = DateInstance().format(startDate) + " " + a[1] + ":00";
                Date dayStart = DateTimeInstance().parse(strDayStart);
                Date dayEnd = DateTimeInstance().parse(strDayEnd);
                daySecond = subtract(dayStart, dayEnd);
                if ((startDate.after(dayStart) || startDate.equals(dayStart)) && startDate
                        .before(dayEnd)) {
                    firstMintues = (dayEnd.getTime() - startDate.getTime()) / 1000L;
                } else if (startDate.before(dayStart)) {
                    firstMintues = (dayEnd.getTime() - dayStart.getTime()) / 1000L;
                }
                dayStart = DateTimeInstance().parse(DateInstance().format(endDate) + " " + a[0] + ":00");
                dayEnd = DateTimeInstance().parse(DateInstance().format(endDate) + " " + a[1] + ":00");
                if (endDate.after(dayStart) && (endDate.before(dayEnd) || endDate.equals(dayEnd))) {
                    lastMintues = (endDate.getTime() - dayStart.getTime()) / 1000L;
                } else if (endDate.after(dayEnd)) {
                    lastMintues = (dayEnd.getTime() - dayStart.getTime()) / 1000L;
                }

                second = firstMintues + lastMintues;
                second += (day - 1) * daySecond;
            } else {
                String strDayStart = DateInstance().format(startDate) + " " + a[0] + ":00";
                String strDayEnd = DateInstance().format(startDate) + " " + a[1] + ":00";
                Date dayStart = DateTimeInstance().parse(strDayStart);
                Date dayEnd = DateTimeInstance().parse(strDayEnd);
                if ((startDate.after(dayStart) || startDate.equals(dayStart)) && startDate
                        .before(dayEnd) && endDate.after(dayStart) && (endDate
                        .before(dayEnd) || endDate.equals(dayEnd))) {
                    second = (endDate.getTime() - startDate.getTime()) / 1000L;
                } else {
                    if (startDate.before(dayStart)) {
                        if (endDate.before(dayEnd)) {
                            second = (endDate.getTime() - dayStart.getTime()) / 1000L;
                        } else {
                            second = (dayEnd.getTime() - dayStart.getTime()) / 1000L;
                        }
                    }
                    if (startDate.after(dayStart)) {
                        if (endDate.before(dayEnd)) {
                            second = (endDate.getTime() - startDate.getTime()) / 1000L;
                        } else {
                            second = (dayEnd.getTime() - startDate.getTime()) / 1000L;
                        }
                    }
                }
                if ((startDate.before(dayStart) && endDate.before(dayStart)) || (startDate
                        .after(dayEnd) && endDate.after(dayEnd))) {
                    second = 0L;
                }
            }
        } else {
            second = (endDate.getTime() - startDate.getTime()) / 1000L;
        }
        if (falg) {
            second = Long.parseLong("-" + second);
        }
        return second;
    }


    public static Date calculate(String date, int second, String timeBurst) {
        Date start = null;
        try {
            start = DateTimeInstance().parse(date);
            return calculate(start, second, timeBurst);
        } catch (ParseException e) {
            e.printStackTrace();

            return new Date();
        }
    }


    public static Date calculate(Date date, int second, String timeBurst) {
        Pattern p = Pattern.compile("^\\d{2}:\\d{2}-\\d{2}:\\d{2}");
        Matcher m = p.matcher(timeBurst);
        Calendar cal = Calendar.getInstance();
        if (m.matches()) {
            String[] a = timeBurst.split("-");
            try {
                Date dayStart = DateTimeInstance().parse(DateInstance().format(date) + " " + a[0] + ":00");
                Date dayEnd = DateTimeInstance().parse(DateInstance().format(date) + " " + a[1] + ":00");
                int DaySecond = (int) subtract(dayStart, dayEnd);
                int toDaySecond = (int) subtract(dayStart, dayEnd);
                if (second >= 0) {
                    if ((date.after(dayStart) || date.equals(dayStart)) && (date
                            .before(dayEnd) || date.equals(dayEnd))) {
                        cal.setTime(date);
                        toDaySecond = (int) subtract(date, dayEnd);
                    }
                    if (date.before(dayStart)) {
                        cal.setTime(dayStart);
                        toDaySecond = (int) subtract(dayStart, dayEnd);
                    }
                    if (date.after(dayEnd)) {
                        cal.setTime(day(dayStart, 1));
                        toDaySecond = 0;
                    }

                    if (second > toDaySecond) {
                        int day = (second - toDaySecond) / DaySecond;
                        int remainder = (second - toDaySecond) % DaySecond;
                        cal.setTime(day(dayStart, 1));
                        cal.add(6, day);
                        cal.add(13, remainder);
                    } else {
                        cal.add(13, second);
                    }
                } else {

                    if ((date.after(dayStart) || date.equals(dayStart)) && (date
                            .before(dayEnd) || date.equals(dayEnd))) {
                        cal.setTime(date);
                        toDaySecond = (int) subtract(date, dayStart);
                    }
                    if (date.before(dayStart)) {
                        cal.setTime(day(dayEnd, -1));
                        toDaySecond = 0;
                    }
                    if (date.after(dayEnd)) {
                        cal.setTime(dayEnd);
                        toDaySecond = (int) subtract(dayStart, dayEnd);
                    }
                    if (Math.abs(second) > Math.abs(toDaySecond)) {
                        int day = (Math.abs(second) - Math.abs(toDaySecond)) / DaySecond;
                        int remainder = (Math.abs(second) - Math.abs(toDaySecond)) % DaySecond;
                        cal.setTime(day(dayEnd, -1));
                        cal.add(6, Integer.valueOf("-" + day).intValue());
                        cal.add(13, Integer.valueOf("-" + remainder).intValue());
                    } else {
                        cal.add(13, second);
                    }
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } else {
            cal.setTime(date);
        }
        return cal.getTime();
    }


    public static boolean between(String startTime, String endTime, Date date) throws ParseException {
        return between(dateTime(startTime), dateTime(endTime), date);
    }


    public static boolean between(Date startTime, Date endTime, Date date) {
        return (date.after(startTime) && date.before(endTime));
    }


    public static String dateAddMinute(String timeStr, int addMinute) {
        String str = null;
        try {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            Date date = df.parse(timeStr);

            Calendar gc = new GregorianCalendar();
            gc.setTime(date);
            gc.add(12, addMinute);
            str = df.format(gc.getTime());
        } catch (Exception exception) {
        }

        return str;
    }


    public static long DatetimeToMillisecond(String dateTimeStr) {
        long retMillisecond = 0L;
        try {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            Date date = df.parse(dateTimeStr);

            retMillisecond = date.getTime();
        } catch (Exception exception) {
        }

        return retMillisecond;
    }


    public static int getSecondTimestamp(String dateTimeStr) {
        if (StringUtil.isEmpty(dateTimeStr)) {
            return 0;
        }
        return getSecondTimestamp(dateTime(dateTimeStr));
    }


    public static int getSecondTimestamp(Date date) {
        if (null == date) {
            return 0;
        }
        String timestamp = String.valueOf(date.getTime() / 1000L);
        return Integer.valueOf(timestamp).intValue();
    }


    public static String secToTime(int time) {
        String timeStr = null;
        int hour = 0;
        int minute = 0;
        int second = 0;
        if (time <= 0) {
            return " 秒";
        }
        minute = time / 60;
        if (minute < 60) {
            second = time % 60;
            timeStr = unitFormat(minute) + " 分钟 " + unitFormat(second) + " 秒";
        } else {
            hour = minute / 60;
            if (hour > 99)
                return "99 小时以上 ";
            minute %= 60;
            second = time - hour * 3600 - minute * 60;
            timeStr = unitFormat(hour) + " 小时 " + unitFormat(minute) + " 分钟 " + unitFormat(second) + " 秒";
        }

        return timeStr;
    }

    private static String unitFormat(int i) {
        String retStr = null;
        if (i >= 0 && i < 10) {
            retStr = "0" + Integer.toString(i);
        } else {
            retStr = "" + i;
        }
        return retStr;
    }
}
