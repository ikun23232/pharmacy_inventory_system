package com.kgc.utils;


import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.poi.excel.ExcelWriter;
import cn.hutool.poi.excel.StyleSet;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.kgc.annotation.ExcelFiled;
import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.ss.usermodel.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 15279
 * @description 功能描述
 * @create 2024/5/11 10:23
 */
public class ExeclUtil {

    public static <T> void write(List<T> excelDataBody, Class clazz, HttpServletResponse response, String fileName) throws IOException {
        try {
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(  ".xlsx", "UTF-8"));
            if (CollectionUtils.isEmpty(excelDataBody) || StringUtils.isEmpty(fileName)) {
                //数据为空或文件名为空，返回错误信息
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                return;
            }
            writeExcel(fileName, "admin", excelDataBody, response.getOutputStream(), clazz);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 导出excel,需自己配置response中的ContentType与Header,需加入fileName
     * response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
     * response.setHeader("Content-Disposition","attachment;filename="+fileName+".xlsx");
     *
     * @param fileTitle     文件标题
     * @param outputUser    导出用户
     * @param excelDataBody excel表数据体
     * @param outputStream  response.getOut()响应体输出流
     * @param clazz         导出数据对象
     * @throws IOException ioexception
     */
    public static <T> void writeExcel(String fileTitle, String outputUser, List<T> excelDataBody, OutputStream outputStream, Class clazz) throws IOException {
        cn.hutool.poi.excel.ExcelWriter writer = cn.hutool.poi.excel.ExcelUtil.getWriter(true);
        StyleSet style = writer.getStyleSet();
        Sheet sheet = writer.getSheet();
        // 设置水平与垂直居中
        style.setAlign(HorizontalAlignment.CENTER, VerticalAlignment.CENTER);
        // 添加别名并对别名计数
        int mergeTotalRowNum = getMergeTotalRowNum(clazz);
        int excelFiledNum = writerAlias(writer, clazz, 2, 0, mergeTotalRowNum) - 1;
        style.setBackgroundColor(IndexedColors.WHITE, true);
        // 解析数据
        List<List<String>> rows = new ArrayList<>();
        parseList(rows, null, 0, excelDataBody, clazz);
        // 添加表格标题
        writer.merge(excelFiledNum, fileTitle);
        // 添加导入用户
        writer.merge(excelFiledNum, "导出用户:" + outputUser);
        writer.setCurrentRow(2 + mergeTotalRowNum);
        // 添加表格数据体
        writer.write(rows, true);

        // 设置所有列为自动宽度，不考虑合并单元格
        setSizeColumn(writer.getSheet(), excelFiledNum);
        // 冻结行
        sheet.createFreezePane(-1, 2 + mergeTotalRowNum);

        // 设置第二行样式
        CellStyle rowStyle = writer.getOrCreateRowStyle(2);
        rowStyle.setAlignment(HorizontalAlignment.RIGHT);
        writer.setRowStyle(2, rowStyle);

        writer.flush(outputStream, true);
        writer.close();
        IoUtil.close(outputStream);
    }

    /**
     * 解析列表
     *
     * @param rows   列表行
     * @param parRow 父列表行
     * @param parNum 父字段数量
     * @param list   解析集合
     * @param clazz  解析类型
     */
    public static <T> void parseList(List<List<String>> rows, List<String> parRow, int parNum, List<T> list, Class clazz) {
        int fieldNum = 0;
        boolean isAdd = true;
        for (T t : list) {
            List<String> row;
            if (parRow != null && fieldNum == 0) {
                row = parRow;
            } else {
                row = new ArrayList<>();
                for (int i = 0; i < parNum; i++) {
                    row.add("");
                }
            }
            for (Field field : clazz.getDeclaredFields()) {
                if (field.isAnnotationPresent(ExcelFiled.class)) {
                    ExcelFiled annotation = field.getAnnotation(ExcelFiled.class);
                    String methodName = "get" + field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1);
                    Method method = ReflectUtil.getMethod(clazz, methodName);
                    if (annotation.type() == String.class) {
                        Object object = ReflectUtil.invoke(t, method);
                        if (object == null) {
                            row.add("");
                        } else {
                            row.add(ReflectUtil.invoke(t, method).toString());
                        }
                    } else {
                        List<Object> objectList = ReflectUtil.invoke(t, method);
                        if (objectList != null && !objectList.isEmpty()) {
                            parseList(rows, row, parNum + row.size(), objectList, annotation.type());
                            isAdd = false;
                        } else {
                            nullToEmpty(row, annotation.type());
                        }
                    }
                    fieldNum++;
                }
            }
            if (isAdd) {
                rows.add(row);
            }
            isAdd = true;
        }
    }

    /**
     * null转空
     *
     * @param row   数据行
     * @param clazz 数据对象
     */
    public static void nullToEmpty(List<String> row, Class clazz) {
        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(ExcelFiled.class)) {
                ExcelFiled annotation = field.getAnnotation(ExcelFiled.class);
                if (annotation.type() == String.class) {
                    row.add("");
                }
            }
        }
    }


    /**
     * 写入别名
     *
     * @param writer 写入对象
     * @param clazz  类型
     * @return 返回总列数
     */
    public static int writerAlias(ExcelWriter writer, Class clazz, int firstRow, int firstColumn, int mergeTotalRowNum) {
        int excelFiledNum = 0;
        int total = 0;
        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(ExcelFiled.class)) {
                ExcelFiled annotation = field.getAnnotation(ExcelFiled.class);
                if (annotation.type() == String.class) {
                    if (firstRow != firstRow + mergeTotalRowNum - 1) {
                        writer.merge(firstRow, firstRow + mergeTotalRowNum - 1, firstColumn + excelFiledNum, firstColumn + excelFiledNum, annotation.value(), writer.getCellStyle());
                    } else {
                        writer.writeCellValue(firstColumn + excelFiledNum, firstRow, annotation.value());
                    }
                } else {
                    int mergeFirstColumn = excelFiledNum;
                    excelFiledNum += writerAlias(writer, annotation.type(), firstRow + 1, firstColumn + excelFiledNum, mergeTotalRowNum - 1);
                    excelFiledNum--;
                    writer.merge(firstRow, firstRow, mergeFirstColumn, excelFiledNum, annotation.value(), writer.getCellStyle());
                }
                excelFiledNum++;
            }
        }
        if (total < excelFiledNum) {
            total = excelFiledNum;
        }
        return total;
    }

    /**
     * 获取总合并行数
     *
     * @param clazz 获取的行对象
     * @return 返回总行数
     */
    public static int getMergeTotalRowNum(Class clazz) {
        int rowNum = 1;
        int maxNum = 1;
        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(ExcelFiled.class)) {
                ExcelFiled annotation = field.getAnnotation(ExcelFiled.class);
                if (annotation.type() != String.class) {
                    rowNum += getMergeTotalRowNum(annotation.type());
                    if (maxNum < rowNum) {
                        maxNum = rowNum;
                    }
                    rowNum = 1;
                }
            }
        }
        return maxNum;
    }

    /**
     * 自适应宽度(中文支持)
     *
     * @param sheet sheet
     * @param size  因为for循环从0开始，size值为 列数-1
     */
    public static void setSizeColumn(Sheet sheet, int size) {
        for (int columnNum = 0; columnNum <= size; columnNum++) {
            int columnWidth = sheet.getColumnWidth(columnNum) / 256;
            for (int rowNum = 0; rowNum <= sheet.getLastRowNum(); rowNum++) {
                Row currentRow;
                //当前行未被使用过
                if (sheet.getRow(rowNum) == null) {
                    currentRow = sheet.createRow(rowNum);
                } else {
                    currentRow = sheet.getRow(rowNum);
                }

                if (currentRow.getCell(columnNum) != null) {
                    Cell currentCell = currentRow.getCell(columnNum);
                    if (currentCell.getCellType() == CellType.STRING) {
                        int length = currentCell.getStringCellValue().getBytes().length;
                        if (columnWidth < length) {
                            columnWidth = length;
                        }
                    }
                }
            }
            sheet.setColumnWidth(columnNum, columnWidth * 256);
        }
    }


}