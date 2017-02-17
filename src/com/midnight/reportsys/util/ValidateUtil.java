package com.midnight.reportsys.util;

import java.util.Collection;

/**
 * 校验有效性
 * @author Midnight
 * @create 2016-08-20 13:07
 */
public class ValidateUtil {

    //判断字符串的有效性
    public static boolean isValid(String str){
        if(str == null || "".equals(str.trim())){
            return false;
        }
        return  true;
    }

    //判断数组的有效性
    public static boolean isValid(Object[] arr){
        if(arr == null || arr.length == 0){
           return false;
        }
       return true;
    }

    //判断集合的有效性
    public static boolean isValid(Collection col){
        if(col == null || col.isEmpty()){
           return false;
        }
        return true;
    }
}
