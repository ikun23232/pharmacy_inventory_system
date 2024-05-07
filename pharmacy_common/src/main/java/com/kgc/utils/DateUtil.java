package com.kgc.utils;

import java.util.Date;

/**
 * @author: 欧洋宏
 * 时间工具类
 **/
public class DateUtil {
    // 获取时间戳
    public static boolean checkReplaytimestamp(String timestamp){
        // 检查时间戳格式
        if (!timestamp.matches("\\d+")) {
            // 时间戳格式无效
            return false;
        }
        // 将时间戳转换为日期
        Date timestampDate = new Date(Long.parseLong(timestamp));

        // 获取当前时间
        Date currentDate = new Date();

        long timeDiff = currentDate.getTime() - timestampDate.getTime();
        // 检查时间差
        if (timeDiff > 1000*60) {
            // 时间戳无效，可能是重放攻击
            return false;
        }
        return true;
    }

}
