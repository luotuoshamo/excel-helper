package com.wjh.reader;


import com.wjh.entity.MySheet;
import com.wjh.util.ExcelUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DefaultExcelReader implements ExcelReader {
    @Override
    public MySheet readSheet(File excelFile, int sheetIndex) throws Exception {
        Sheet sheet = ExcelUtil.sheetAt(excelFile, sheetIndex);
        int sheetRowCount = ExcelUtil.getSheetRowCount(sheet);
        // sheet中无数据，或只有表头
        if (sheetRowCount == 0 || sheetRowCount == 1) return null;

        List<List<String>> rowList = new ArrayList();
        for (int i = 0; i < sheetRowCount; i++) {
            Row row = sheet.getRow(i);
            List<String> cellValueList = ExcelUtil.rowToStringList(row);
            rowList.add(cellValueList);
        }

        MySheet mySheet = new MySheet(rowList);
        return mySheet;
    }
}
