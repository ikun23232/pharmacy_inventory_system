package com.kgc.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * 精度处理类
 * @author: 欧洋宏
 **/
public class BigDecimalUtils {
    // 加法
    public static BigDecimal add(BigDecimal a, BigDecimal b) {
        return a.add(b);
    }

    // 减法
    public static BigDecimal subtract(BigDecimal a, BigDecimal b) {
        return a.subtract(b);
    }

    // 乘法
    public static BigDecimal multiply(BigDecimal a, BigDecimal b) {
        return a.multiply(b);
    }



    // 设置精度
    public static BigDecimal setScale(BigDecimal a, int scale) {
        return a.setScale(scale, RoundingMode.HALF_UP); // 设置精度并使用四舍五入
    }



}
