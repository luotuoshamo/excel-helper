package com.wjh;

import com.wjh.domain.SheetHeader;
import com.wjh.enums.ExcelTypeEnum;
import com.wjh.parser.ExcelParser;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 解析excel中的一个sheet
 */
public class ExcelUtil {

    /**
     * 将sheet解析成List<Map<String,String>
     */
    public static List<Map<String, String>> parseSheetToMapList(
            FileInputStream excelFileInputStream,
            int sheetIndex,
            SheetHeader sheetHeader) throws Exception {
        return ExcelParser.getParser()
                .parseSheetToMapList(excelFileInputStream, sheetIndex, sheetHeader);
    }

    /**
     * 将sheet解析成List<Bean>例如List<User> User是实体类
     */
    public static <T> List<T> parseSheetToBeanList(InputStream excelInputStream, int sheetIndex, SheetHeader sheetHeader, Class<T> clazz) throws Exception {
        return ExcelParser.getParser()
                .parseSheetToBeanList(excelInputStream, sheetIndex, sheetHeader, clazz);
    }


    /**
     * 获取excel的类型
     */
    public static ExcelTypeEnum excelType(File excelFile) throws Exception {
        if (excelFile == null) throw new Exception("excelFile为空");
        String excelFileName = excelFile.getName();
        if (excelFileName == null) throw new Exception("excelFileName为空");

        excelFileName = excelFileName.toLowerCase();
        if (excelFileName.endsWith(".xls")) return ExcelTypeEnum.XLS;
        else if (excelFileName.endsWith(".xlsx")) return ExcelTypeEnum.XLSX;
        throw new Exception("excel的格式不是xls、xlsx，暂时只支持xls、xlsx");
    }

    /**
     * 创建excel
     */
    public static Workbook createExcel(ExcelTypeEnum excelType) throws Exception {
        if (ExcelTypeEnum.XLSX.equals(excelType))
            return new XSSFWorkbook();
        else if (ExcelTypeEnum.XLS.equals(excelType))
            return new HSSFWorkbook();
        throw new Exception("excelType只支持xls和xlsx");
    }


    /**
     * 给excel（workbook）创建sheet
     */
    public static Workbook addSheet(
            Workbook workbook,
            String sheetName,
            SheetHeader sheetHeader,
            List<Map<String, String>> dataMaps) {
        Sheet sheet = workbook.createSheet(sheetName);

        // 表头
        Row headRow = sheet.createRow(0);
        int headerItemCount = sheetHeader.getHeaderItemCount();
        for (int i = 0; i < headerItemCount; i++) {
            headRow.createCell(i).setCellValue(sheetHeader.getChineseName(i));
        }

        for (int i = 0; i < dataMaps.size(); i++) {// 一个map对应一行
            Row row = sheet.createRow(i + 1);// 第0行是表头
            Map<String, String> map = dataMaps.get(i);
            int j = 0;
            for (String key : map.keySet()) {
                Cell cell = row.createCell(j++);
                cell.setCellValue(map.get(key));
            }
        }
        return workbook;
    }
}
