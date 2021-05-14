package com.wjh.util;

import com.wjh.enums.ExcelTypeEnum;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

public class ExcelUtil {
    /**
     * 获取从excel中获取sheet
     * HSSF   .xls
     * XSSF    xlsx
     * <p>
     * org.apache.poi.ss.usermodel.*
     */
    public static Sheet sheetAt(File excelFile, int index) throws Exception {
        if (excelFile == null) throw new Exception("excelFile不可为空");
        FileInputStream fis = new FileInputStream(excelFile);

        ExcelTypeEnum excelType = getExcelType(excelFile);
        if (excelType.equals(ExcelTypeEnum.XLS)) {
            HSSFWorkbook excel = new HSSFWorkbook(fis);
            return excel.getSheetAt(index);
        }

        XSSFWorkbook excel = new XSSFWorkbook(fis);
        return excel.getSheetAt(index);
    }

    /**
     * sheet的行数
     */
    public static int getSheetRowCount(Sheet sheet) {
        return sheet == null ? 0 : sheet.getLastRowNum() + 1;
    }

    /**
     * 获取row中cell的个数
     * 注意：sheet.getLastRowNum()和 row.getLastCellNum()计数规则不同，这可看成是poi的不足
     */
    public static int getRowCellCount(Row row) {
        return row == null ? 0 : row.getLastCellNum();
    }

    /**
     * 将row转成List<String>
     */
    public static List<String> rowToStringList(Row row) {
        int cellCount = getRowCellCount(row);// 该row有个cell
        if (cellCount == 0) return null;

        List<String> cellValueList = new ArrayList();
        for (int i = 0; i < cellCount; i++) {
            Cell cell = row.getCell(i);
            String cellValue = getCellValueAsString(cell);
            cellValueList.add(cellValue);
        }
        return cellValueList;
    }

    /**
     * 忽略单元格中内容的类型，全当作string
     */
    public static String getCellValueAsString(Cell cell) {
        return new DataFormatter().formatCellValue(cell);
    }

    public static ExcelTypeEnum getExcelType(File excelFile) throws Exception {
        if (excelFile == null) throw new Exception("excelFile不可为空");
        String excelFileName = excelFile.getName().toLowerCase();
        ExcelTypeEnum excelType;
        if (excelFileName.endsWith(".xls")) excelType = ExcelTypeEnum.XLS;
        else if (excelFileName.endsWith(".xlsx")) excelType = ExcelTypeEnum.XLSX;
        else throw new Exception("【" + excelFile.getName() + "】不是excel");
        return excelType;
    }

}
