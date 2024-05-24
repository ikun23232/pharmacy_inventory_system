package com.kgc.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author 15279
 * @description 功能描述
 * @create 2024/5/21 14:36
 */
public class CodeUtil {
    public static String createCode(String codeName){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String code = simpleDateFormat.format(new Date());
        return codeName+"-"+code;
    }
}
