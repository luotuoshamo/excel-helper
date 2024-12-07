package cn.topicstudy.jexcel.util;

import cn.topicstudy.jexcel.enums.ExcelTypeEnum;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.InputStream;

/**
 * @Author: 专题学习大师 topicstudy.cn 2021/10/10
 * Workbook就是excel
 * 只能被调一次
 */
public class WorkbookUtil {
    public static Workbook initWorkbook(InputStream excelIs, ExcelTypeEnum excelType) throws IOException {
        if (excelIs == null) throw new RuntimeException("excelIs不可为空");
        if (excelType == null) throw new RuntimeException("excelType不可为空");
        return ExcelTypeEnum.XLS.equals(excelType) ? new HSSFWorkbook(excelIs) : new XSSFWorkbook(excelIs);
    }

    public static Workbook initWorkbook(ExcelTypeEnum excelType) throws IOException {
        if (excelType == null) throw new RuntimeException("excelType不可为空");
        return ExcelTypeEnum.XLS.equals(excelType) ? new HSSFWorkbook() : new XSSFWorkbook();
    }

    /**
     * 从excel中获取sheet
     * HSSF    xls
     * XSSF    xlsx
     *
     * @param index 从0开始
     * @return org.apache.poi.ss.usermodel.*
     */
    public static Sheet sheetAt(Workbook wb, int index) throws IOException {
        return wb == null ? null : wb.getSheetAt(index);
    }

    /**
     * workbook中共有多少个sheet
     *
     * @return
     */
    public static int totalSheetCount(Workbook wb) {
        return wb == null ? 0 : wb.getNumberOfSheets();
    }
}
