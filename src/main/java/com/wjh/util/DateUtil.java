package com.wjh.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    /**
     * 所有日期、时间都用该格式
     */
    public static String DATE_MASK = "yyyy-MM-dd";
    public static String TIME_MASK = "HH:mm:ss";
    public static String DATE_TIME_MASK = "yyyy-MM-dd HH:mm:ss";

    private static SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_MASK);
    private static SimpleDateFormat timeFormat = new SimpleDateFormat(TIME_MASK);
    private static SimpleDateFormat dateTimeFormat = new SimpleDateFormat(DATE_TIME_MASK);

    /**
     * @param s s必须满足MASK
     */
    public static Date stringToDate(String s) throws Exception {
        if (s == null) return null;
        Type type = getType(s);
        if (type.equals(Type.DATE)) return dateFormat.parse(s);
        else if (type.equals(Type.TIME)) return timeFormat.parse(s);
        else if (type.equals(Type.DATE_TIME)) return dateTimeFormat.parse(s);
        throw new Exception("日期格式必须是【" + DATE_MASK + "】或【" + TIME_MASK + "】或【" + DATE_TIME_MASK + "】");
    }

    public static String dateToString(Date d) throws Exception {
        if (d == null) return null;
        Type type = getType(d);
        if (type.equals(Type.DATE)) return dateFormat.format(d);
        else if (type.equals(Type.TIME)) return timeFormat.format(d);
        else if (type.equals(Type.DATE_TIME)) return dateTimeFormat.format(d);
        throw new Exception("日期格式必须是【" + DATE_MASK + "】或【" + TIME_MASK + "】或【" + DATE_TIME_MASK + "】");
    }

    /**
     * 判断日期字符串的格式
     * 判断顺序：dateTime=>date=>time
     */
    private static Type getType(String s) {
        try {
            dateTimeFormat.parse(s);
            return Type.DATE_TIME;
        } catch (ParseException e) {
            try {
                dateFormat.parse(s);
                return Type.DATE;
            } catch (ParseException e1) {
                try {
                    timeFormat.parse(s);
                    return Type.TIME;
                } catch (ParseException e2) {
                    return Type.UNKNOWN;
                }
            }
        }
    }

    private static Type getType(Date d) {
        try {
            dateTimeFormat.format(d);
            return Type.DATE_TIME;
        } catch (Exception e) {
            try {
                dateFormat.format(d);
                return Type.DATE;
            } catch (Exception e1) {
                try {
                    timeFormat.format(d);
                    return Type.TIME;
                } catch (Exception e2) {
                    return Type.UNKNOWN;
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        String s = "2020-1-1 10:23:1";
        Date date = DateUtil.stringToDate(s);
        System.out.println(date);

        System.out.println(DateUtil.dateToString(date));
    }

    enum Type {
        DATE, TIME, DATE_TIME, UNKNOWN
    }
}
