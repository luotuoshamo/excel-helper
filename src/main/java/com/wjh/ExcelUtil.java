package com.wjh;

import com.wjh.domain.SheetHeader;
import com.wjh.enums.ExcelTypeEnum;
import com.wjh.parser.ExcelParser;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

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
     * 判断excel是否是xls
     */
    public static boolean isXls(File excelFile) throws Exception {
        return ExcelTypeEnum.XLS.equals(excelType(excelFile));
    }

    /**
     * 判断excel是否是xlsx
     */
    public static boolean isXlsx(File excelFile) throws Exception {
        return ExcelTypeEnum.XLSX.equals(excelType(excelFile));
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
}
