package com.kgc.utils;

import com.kgc.entity.KcReported;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

public class ExcelUtils {

    public static void exportToExcel(HttpServletResponse response, List<KcReported> data, String fileName) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Reported Data");

        // 创建表头
        Row headerRow = sheet.createRow(0);
        String[] headers = {"ID", "Code", "Storehouse ID", "Reported Type ID", "Approver By", "Approval Status", "Approver Remark", "Documenter By", "Create Time", "Create By", "Modification Time", "Modification By", "Is Del", "Approver Name", "Documenter Name", "Create Name", "Modification Name", "Storehouse Name", "Reported Type Name"};
        for (int col = 0; col < headers.length; col++) {
            Cell cell = headerRow.createCell(col);
            cell.setCellValue(headers[col]);
        }

        // 填充数据
        int rowNum = 1;
        for (KcReported reported : data) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(reported.getId());
            row.createCell(1).setCellValue(reported.getCode());
            row.createCell(2).setCellValue(reported.getStorehouseId());
            row.createCell(3).setCellValue(reported.getReportedTypeId());
            row.createCell(4).setCellValue(reported.getApproverBy());
            row.createCell(5).setCellValue(reported.getApprovalStatus());
            row.createCell(6).setCellValue(reported.getApproverRemark());
            row.createCell(7).setCellValue(reported.getDocumenterBy());
//            row.createCell(8).setCellValue(dateToLocalDateTime(reported.getCreateTime()));
            row.createCell(9).setCellValue(reported.getCreateBy());
//            row.createCell(10).setCellValue(dateToLocalDateTime(reported.getModificationTime()));
            row.createCell(11).setCellValue(reported.getModificationBy());
            row.createCell(12).setCellValue(reported.getIsDel());
            row.createCell(13).setCellValue(reported.getApproverName());
            row.createCell(14).setCellValue(reported.getDocumenterName());
            row.createCell(15).setCellValue(reported.getCreateName());
            row.createCell(16).setCellValue(reported.getModificationName());
            row.createCell(17).setCellValue(reported.getStorehouseName());
            row.createCell(18).setCellValue(reported.getReportedTypeName());
        }

        // 设置响应头
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=" + fileName + ".xlsx");

        // 写入响应输出流
        workbook.write(response.getOutputStream());
        workbook.close();
    }

    private static LocalDateTime dateToLocalDateTime(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }
}