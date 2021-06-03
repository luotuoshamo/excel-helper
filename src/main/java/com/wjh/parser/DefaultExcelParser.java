package com.wjh.parser;


import com.wjh.entity.MySheet;
import com.wjh.enums.ExcelTypeEnum;
import com.wjh.util.ExcelUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class DefaultExcelParser implements ExcelParser {
    @Override
    public MySheet parse(File excelFile, int sheetIndex) throws Exception {
        ExcelTypeEnum excelType = ExcelUtil.getExcelType(excelFile);
        return parse(new FileInputStream(excelFile), excelType, sheetIndex);
    }

    @Override
    public MySheet parse(InputStream excelIs, ExcelTypeEnum excelType, int sheetIndex) throws Exception {
        Sheet sheet = ExcelUtil.sheetAt(excelIs, excelType, sheetIndex);
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
