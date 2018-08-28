package com.leekitman.jiewendb.common.utils;

import com.leekitman.jiewendb.common.exception.OpCommonUtilException;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class DateUtils {

    private static final Date MIN_DATE;
    private static final Date MAX_DATE;

    static {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            MIN_DATE = sdf.parse("2000-01-01 00:00:00");
            MAX_DATE = sdf.parse("2222-01-01 00:00:00");
        } catch (ParseException e) {
            throw new OpCommonUtilException("日期转换错误");
        }
    }

    private static void validateDateNull(Date date) {
        if (date == null) {
            throw new OpCommonUtilException("参数的日期不能为null");
        }
    }

    private static void validatePatternEmpty(String pattern) {
        if (pattern == null || pattern.trim().isEmpty()) {
            throw new OpCommonUtilException("格式化模版不能为空");
        }
    }

    /**
     * 获取指定日期的 00:00:00
     */
    public static Date getDateFirst(Date date) {
        validateDateNull(date);
        long zero = date.getTime() / (1000 * 3600 * 24) * (1000 * 3600 * 24) - TimeZone.getDefault().getRawOffset();
        return new Date(zero);
    }

    /**
     * 获取指定日期的 23:59:59
     */
    public static Date getDateLast(Date date) {
        validateDateNull(date);
        long zero = date.getTime() / (1000 * 3600 * 24) * (1000 * 3600 * 24) - TimeZone.getDefault().getRawOffset();
        long twelve = zero + 24 * 60 * 60 * 1000 - 1;
        return new Date(twelve);
    }

    /**
     * 返回两个时间前后的“小时”单位差
     *
     * @param one 靠后的时间
     * @param two 靠前的时间
     */
    public static Long getDifferentHours(Date one, Date two) {
        validateDateNull(one);
        validateDateNull(one);
        Long diff = one.getTime() - two.getTime();
        return diff / (1000 * 60 * 60);
    }

    /**
     * 返回两个时间前后的“毫秒”单位差
     *
     * @param one 靠后的时间
     * @param two 靠前的时间
     */
    public static Long getDifferentMillisecond(Date one, Date two) {
        validateDateNull(one);
        validateDateNull(one);
        return one.getTime() - two.getTime();
    }

    /**
     * 去掉指定日期的时间，只保留到日
     */
    public static Date getDateDay(Date date) throws ParseException {
        validateDateNull(date);
        String pattern = "yyyyMMdd";
        String yyyyMMdd = formatDateTime(date, pattern);
        return ParseDateTime(yyyyMMdd, pattern);

    }

    /**
     * 去掉指定日期的时间，只保留到日（返回字符串）
     */
    public static String getDateDayString(Date date) {
        validateDateNull(date);
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return df.format(date);
    }

    /**
     * 格式化时间，返回yyyy-MM-dd HH:mm:ss格式字符串
     */
    public static String formatSimpleDateTime(Date date) {
        validateDateNull(date);
        try {
            return formatDateTime(date, "yyyy-MM-dd HH:mm:ss");
        } catch (Exception e) {
            throw new OpCommonUtilException("");
        }
    }

    /**
     * 自定义模版格式化日期时间
     *
     * @param date    日期时间
     * @param pattern 格式化模版
     */
    public static String formatDateTime(Date date, String pattern) {
        validateDateNull(date);
        validatePatternEmpty(pattern);
        try {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return df.format(date);
        } catch (Exception e) {
            throw new OpCommonUtilException("自定义模版格式化日期时间异常，检查pattern参数是否符合规定");
        }
    }

    /**
     * 自定义模版解析日期时间
     *
     * @param date    日期时间字符串
     * @param pattern 格式化模版
     */
    public static Date ParseDateTime(String date, String pattern) {
        StringUtil.isBlank(pattern);
        validatePatternEmpty(pattern);
        try {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return df.parse(date);
        } catch (Exception e) {
            throw new OpCommonUtilException("自定义模版解析日期时间异常，请检查参数是否符合规定");
        }
    }

    /**
     * 判断两个日期是否为同一天
     */
    public static boolean isSameDay(Date one, Date two) throws ParseException {
        validateDateNull(one);
        validateDateNull(one);
        return getDateDay(one).equals(getDateDay(two));
    }

}
