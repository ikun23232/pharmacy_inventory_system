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

    public static void writeExcel(List data,HttpServletResponse response, String fileName) throws IOException {
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
