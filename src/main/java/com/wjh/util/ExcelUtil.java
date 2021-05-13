package com.wjh.util;

import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ExcelUtil {
    /**
     * 获取从excel中获取sheet
     * HSSF 提供读写Microsoft Excel XLS格式档案的功能
     * XSSF 提供读写Microsoft Excel OOXML XLSX格式档案的功能
     * <p>
     * org.apache.poi.ss.usermodel.*
     */
    public static Sheet sheetAt(File excelFile, int index) throws Exception {
        Workbook excel = WorkbookFactory.create(excelFile);
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
        int cellCount = getRowCellCount(row);
        if (cellCount == 0) return null;

        List<String> list = new ArrayList();
        for (int i = 0; i < cellCount; i++) {
            String cellValue = getCellValueAsString(row.getCell(i));
            list.add(cellValue);
        }
        return list;
    }

    /**
     * 忽略单元格中内容的类型，全当作string
     */
    public static String getCellValueAsString(Cell cell) {
        DataFormatter df = new DataFormatter();
        String cellValue = df.formatCellValue(cell);
        return cellValue;
    }
}
