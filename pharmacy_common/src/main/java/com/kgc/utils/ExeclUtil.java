package com.kgc.utils;


import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.kgc.entity.CgddOrder;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 15279
 * @description 功能描述
 * @create 2024/5/11 10:23
 */
public class ExeclUtil {
//    /**
//     * 获取对象列表
//     *
//     * @param maps  excel数据
//     * @param clazz clazz 类
//     * @return < t >对象列表
//     * @throws InstantiationException    实例化异常
//     * @throws IllegalAccessException    非法访问异常
//     * @throws NoSuchMethodException    方法不存在异常
//     * @throws InvocationTargetException 调用目标异常
//     */
//    public static<T> List<T> getObjectList(List<Map<String,Object>> maps, Class<T> clazz) throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
//        Field[] fields = clazz.getDeclaredFields();
//        List<T> list = new ArrayList<>();
//        for (Map<String, Object> map : maps) {
//            T t = clazz.getDeclaredConstructor().newInstance();
//            for (Field field : fields) {
//                if (field.isAnnotationPresent(ExcelFiled.class)) {
//                    ExcelFiled annotation = field.getAnnotation(ExcelFiled.class);
//                    String name = annotation.value();
//                    field.setAccessible(true);
//                    if(field.getType()==Integer.class){
//                        field.set(t, Convert.toInt(map.get(name)));
//                    }
//                    else if(field.getType()==Double.class){
//                        field.set(t, Convert.toDouble(map.get(name)));
//                    }
//                    else{
//                        field.set(t, map.get(name));
//                    }
//                }
//            }
//            list.add(t);
//        }
//        return list;
//    }
//
//    /**
//     * 读取excel
//     *
//     * @param inputStream excel文件输入流
//     * @param clazz       要反射的类
//     * @return < t >对象列表
//     * @throws InstantiationException    实例化异常
//     * @throws IllegalAccessException    非法访问异常
//     * @throws InvocationTargetException 调用目标异常
//     * @throws NoSuchMethodException     方法不存在异常
//     */
//    public static <T> List<T> readExcel(InputStream inputStream, Class<T> clazz) throws InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
//        ExcelReader reader = cn.hutool.poi.excel.ExcelUtil.getReader(inputStream);
//        List<Map<String,Object>> readAll = reader.readAll();
//        return getObjectList(readAll, clazz);
//    }
//
//    /**
//     * 导出excel
//     *
//     * @param rows     数据列表
//     * @param response 响应
//     * @param fileName 文件名称
//     * @throws IOException ioexception
//     */

    public static void writeExcel(List<List<Object>> data,HttpServletResponse response, String fileName) throws IOException {
        try {
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(  ".xlsx", "UTF-8"));
            if (CollectionUtils.isEmpty(data) || StringUtils.isEmpty(fileName)) {
                //数据为空或文件名为空，返回错误信息
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                return;
            }
            EasyExcel.write(response.getOutputStream())
                    .head(CgddOrder.class)
                    .excelType(ExcelTypeEnum.XLSX)
                    .sheet(fileName)
                    .doWrite(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
