package com.qf.webpro.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @description:日期处理工具类,提供字符串转为日期,日期转为字符串的相关方法
 * @author:pqc
 * @createTime:2020/11/13 20:28
 * @version: v1.0
 */
public class DateUtil {


    /**
     * 将字符串转为指定格式的Date类型
     * @param str 需要转换的字符串
     * @param p 指定的格式
     * @return 返回格式化后的Date类型数据
     * @throws ParseException
     */
    public  static Date stringToDate(String str,String p) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(p);
        return sdf.parse(str);
    }

    /**
     * 把指定的日期转为指定格式的字符串
     * @param date
     * @param p
     * @return
     */
    public static String dateToString(Date date,String p){
        SimpleDateFormat sdf = new SimpleDateFormat(p);
         return sdf.format(date);
    }
    /**
     * 把当前的日期转为指定格式的字符串
     * @param p
     * @return
     */
    public static String dateToString(String p){
        SimpleDateFormat sdf = new SimpleDateFormat(p);
        return sdf.format(new Date());
    }





}
