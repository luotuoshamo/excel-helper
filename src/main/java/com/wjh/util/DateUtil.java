package com.wjh.util;

import javax.crypto.MacSpi;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.SimpleFormatter;

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
     * s必须满足MASK
     */
    public static Date stringToDate(String s) throws Exception {
        Type type = getType(s);
        if (type.equals(Type.DATE)) return dateFormat.parse(s);
        else if (type.equals(Type.TIME)) return timeFormat.parse(s);
        else if (type.equals(Type.DATE_TIME)) return dateFormat.parse(s);
        throw new Exception("日期格式必须是【" + DATE_MASK + "】或【" + TIME_MASK + "】或【" + DATE_TIME_MASK + "】");
    }

    private static Type getType(String s) {
        try {
            dateFormat.parse(s);
            return Type.DATE;
        } catch (ParseException e) {
            try {
                timeFormat.parse(s);
                return Type.TIME;
            } catch (ParseException e1) {
                try {
                    dateTimeFormat.parse(s);
                    return Type.DATE_TIME;
                } catch (ParseException e2) {
                    return Type.UNKNOW;
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        String s = "2020-1-1 10:23:1";
        Date date = DateUtil.stringToDate(s);
        System.out.println(date);
    }

    enum Type {
        DATE, TIME, DATE_TIME, UNKNOW
    }
}
