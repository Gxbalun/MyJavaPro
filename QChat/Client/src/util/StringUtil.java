package util;

/**
 * @description:字符串常用操作工具类
 * @author:pqc
 * @createTime:2020/11/9 19:19
 * @version: v1.0
 */
public class StringUtil {


   /*
     用于截取字符串文件名的后缀名
     He.llo.java
    */
    public static String subFileType(String filName){
         return filName.substring(filName.lastIndexOf(".")+1);
    }



}
