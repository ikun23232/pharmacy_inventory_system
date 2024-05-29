package com.kgc.utils;
import java.util.Date;
import java.text.SimpleDateFormat;
/**
 * @author daidai
 */
public class TimeUtil {
    public static String getCurrentTime(String code) {
        Date now = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDate = sdf.format(now);
        return code + "-" + formattedDate;
    }
}
