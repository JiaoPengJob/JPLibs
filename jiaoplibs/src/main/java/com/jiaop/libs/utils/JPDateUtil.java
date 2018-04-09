package com.jiaop.libs.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by JiaoP
 * 时间帮助类
 */
public class JPDateUtil {

    public static final String DF_YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    public static final String DF_YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";
    public static final String DF_YYYY_MM_DD = "yyyy-MM-dd";
    public static final String DF_HH_MM_SS = "HH:mm:ss";
    public static final String DF_HH_MM = "HH:mm";
    private final static long MINUTE = 60 * 1000;// 1分钟
    private final static long HOUR = 60 * MINUTE;// 1小时
    private final static long DAY = 24 * HOUR;// 1天
    private final static long MONTH = 31 * DAY;// 月
    private final static long YEAR = 12 * MONTH;// 年

    /**
     * 根据日期判断星座
     *
     * @param month 月份
     * @param day   天数
     * @return
     */
    public static String getAstro(int month, int day) {
        String[] starArr = {"魔羯座", "水瓶座", "双鱼座", "牡羊座", "金牛座", "双子座", "巨蟹座", "狮子座", "处女座", "天秤座", "天蝎座", "射手座"};
        int[] DayArr = {22, 20, 19, 21, 21, 21, 22, 23, 23, 23, 23, 22};  // 两个星座分割日
        if (month <= 0 || day <= 0) {
            return "猴年马月座";
        } else if (month > 12 || day > 31) {
            return "猴年马月座";
        }
        int index = month;
        // 所查询日期在分割日之前，索引-1，否则不变
        if (day < DayArr[month - 1]) {
            index = index - 1;
        }
        // 返回索引指向的星座string
        return starArr[index];
    }

    /**
     * 将日期格式化成友好的字符串：几分钟前、几小时前、几天前、几月前、几年前、刚刚
     *
     * @param date 日期
     * @return
     */
    public static String formatFriendly(Date date) {
        if (date == null) {
            return null;
        }
        long diff = new Date().getTime() - date.getTime();
        long r = 0;
        if (diff > YEAR) {
            r = (diff / YEAR);
            return r + "年前";
        }
        if (diff > MONTH) {
            r = (diff / MONTH);
            return r + "个月前";
        }
        if (diff > DAY) {
            r = (diff / DAY);
            return r + "天前";
        }
        if (diff > HOUR) {
            r = (diff / HOUR);
            return r + "个小时前";
        }
        if (diff > MINUTE) {
            r = (diff / MINUTE);
            return r + "分钟前";
        }
        return "刚刚";
    }

    /**
     * 获取系统当前日期
     *
     * @return Date
     */
    public static Date getCurrentDate() {
        return new Date();
    }

    /**
     * 获取系统当前日期
     *
     * @return Date
     */
    public static String getCurrentDate(String formatString) {
        return date2String(new Date(), formatString);
    }

    /**
     * 将Date类型转换为指定格式String类型
     *
     * @param data
     * @param formatType
     * @return
     */
    public static String date2String(Date data, String formatType) {
        return new SimpleDateFormat(formatType).format(data);
    }

    /**
     * 将long类型转换为String类型
     *
     * @param currentTime
     * @param formatType
     * @return
     * @throws ParseException
     */
    public static String long2String(long currentTime, String formatType)
            throws ParseException {
        Date date = longToDate(currentTime, formatType);
        String strTime = date2String(date, formatType);
        return strTime;
    }

    /**
     * 将String类型转换为Date类型
     *
     * @param strTime
     * @param formatType
     * @return
     * @throws ParseException
     */
    public static Date string2Date(String strTime, String formatType)
            throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat(formatType);
        Date date = null;
        date = formatter.parse(strTime);
        return date;
    }

    /**
     * 将long类型转换为Date类型
     *
     * @param currentTime
     * @param formatType
     * @return
     * @throws ParseException
     */
    public static Date longToDate(long currentTime, String formatType)
            throws ParseException {
        Date dateOld = new Date(currentTime); // 根据long类型的毫秒数生命一个date类型的时间
        String sDateTime = date2String(dateOld, formatType); // 把date类型的时间转换为string
        Date date = string2Date(sDateTime, formatType); // 把String类型转换为Date类型
        return date;
    }

    /**
     * 将String类型转换为long类型
     *
     * @param strTime
     * @param formatType
     * @return
     * @throws ParseException
     */
    public static long string2Long(String strTime, String formatType)
            throws ParseException {
        Date date = string2Date(strTime, formatType); // String类型转成date类型
        if (date == null) {
            return 0;
        } else {
            long currentTime = date2Long(date); // date类型转成long类型
            return currentTime;
        }
    }

    /**
     * 将Date类型转换为long类型
     *
     * @param date
     * @return
     */
    public static long date2Long(Date date) {
        return date.getTime();
    }

    /**
     * 判断是否大于当前时间
     *
     * @param time
     * @param formatString
     * @return
     */
    public static boolean judgeCurrTime(String time, String formatString) {
        SimpleDateFormat sdf = new SimpleDateFormat(formatString);
        Date date = new Date();
        try {
            date = sdf.parse(time);
            long t = date.getTime();
            long round = System.currentTimeMillis();
            if (t - round > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        return false;
    }

    /**
     * 判断是否大于当前时间
     *
     * @param time
     * @return
     */
    public static boolean judgeCurrTime(long time) {
        long round = System.currentTimeMillis();
        if (time - round > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 比较后面的时间是否大于前面的时间
     *
     * @param time1
     * @param time2
     * @param formatString
     * @return
     */
    public static boolean judgeTime2Time(String time1, String time2, String formatString) {
        SimpleDateFormat sdf = new SimpleDateFormat(formatString);
        try {
            Date date1 = sdf.parse(time1);
            Date date2 = sdf.parse(time2);
            long l1 = date1.getTime() / 1000;
            long l2 = date2.getTime() / 1000;
            if (l2 - l1 > 0) {
                return true;
            } else {
                return false;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 得到明天的日期
     *
     * @param formatString
     * @return
     */
    public static String getTomorrowDate(String formatString) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 1);
        SimpleDateFormat sdf = new SimpleDateFormat(formatString);
        String tomorrow = sdf.format(calendar.getTime());
        return tomorrow;
    }

    /**
     * 得到昨天的日期
     *
     * @param formatString
     * @return
     */
    public static String getYesterdayDate(String formatString) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -1);
        SimpleDateFormat sdf = new SimpleDateFormat(formatString);
        String yestoday = sdf.format(calendar.getTime());
        return yestoday;
    }

    /**
     * 获取格林威治时间 即1970年至今的秒数
     */
    public static long getGMTime() {
        int round = (int) (System.currentTimeMillis() / 1000);
        return round;
    }

    /**
     * @param date
     * @return
     */

    public static final int WEEKDAYS = 7;

    public static String[] WEEK = {"日", "一", "二", "三", "四", "五", "六"};

    /**
     * 日期转换为对应的星期
     *
     * @param date
     * @param weekString 星期的展示文字
     * @return
     */
    public static String DateToWeek(Date date, String weekString) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int dayIndex = calendar.get(Calendar.DAY_OF_WEEK);
        if (dayIndex < 1 || dayIndex > WEEKDAYS) {
            return null;
        }
        return weekString + WEEK[dayIndex - 1];
    }

    /**
     * 获取当前10位时间戳
     *
     * @return
     */
    public static String getCurrentTimestamp10() {
        return String.format("%010d", System.currentTimeMillis() / 1000);
    }

    /**
     * 获取当前13位时间戳
     *
     * @return
     */
    public static String getCurrentTimestamp13() {
        return String.valueOf(System.currentTimeMillis());
    }

    /**
     * 时间字符串转时间戳
     *
     * @param timeString
     * @param formatString
     * @return
     */
    public static String getTime(String timeString, String formatString) {
        String timeStamp = null;
        SimpleDateFormat sdf = new SimpleDateFormat(formatString);
        Date d;
        try {
            d = sdf.parse(timeString);
            long l = d.getTime();
            timeStamp = String.valueOf(l);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return timeStamp;
    }
}
