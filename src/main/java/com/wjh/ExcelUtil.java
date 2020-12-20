package com.wjh;

import com.wjh.domain.SheetHeader;
import com.wjh.enums.ExcelTypeEnum;
import com.wjh.parser.ExcelParser;
import com.wjh.util.MapUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * excel工具类
 * 也是使用者的入口
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
     *
     * @param excelType see {ExcelTypeEnum}
     * @return {Workbook}
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
    public static Workbook addSheetWithDataMaps(
            Workbook workbook,
            String sheetName,
            SheetHeader sheetHeader,
            List<Map<String, String>> dataMaps
    ) {
        Sheet sheet = workbook.createSheet(sheetName);
        // sheet表头的行（即first row）
        Row headRow = sheet.createRow(0);
        int headerItemCount = sheetHeader.getHeaderItemCount();// 表头的字段数

        // 设置sheet的表头内容
        for (int i = 0; i < headerItemCount; i++) {
            headRow.createCell(i).setCellValue(sheetHeader.getChineseName(i));
        }

        // 设置sheet的数据
        for (int i = 0; i < dataMaps.size(); i++) {// 一个map对应一行数据
            Row row = sheet.createRow(i + 1);// 第0行是表头
            // {name:wjh, gender:m}
            Map<String, String> map = dataMaps.get(i);
            for (int j = 0; j < headerItemCount; j++) {
                String englishName = sheetHeader.getEnglishName(j);
                String value = map.get(englishName);
                /*
                JDK源码：
                     public static String valueOf(Object obj) {
                        return (obj == null) ? "null" : obj.toString();
                    }
                 */
                if (value == null) value = "";
                row.createCell(j).setCellValue(value);
            }
        }
        return workbook;
    }

    /**
     * 给excel（workbook）创建sheet
     */
    public static <T> Workbook addSheet(
            Workbook workbook,
            String sheetName,
            SheetHeader sheetHeader,
            List<T> dataBeans
    ) throws Exception {
        List<Map<String, String>> dataMaps = new ArrayList<>();
        for (T bean : dataBeans) {
            Map<String, String> map = MapUtil.beanToMap(bean);
            dataMaps.add(map);
        }
        return addSheetWithDataMaps(workbook, sheetName, sheetHeader, dataMaps);
    }
}
