package com.wjh.reader;

import com.wjh.entity.MySheet;

import java.io.File;

public interface ExcelReader {
    MySheet readSheet(File excelFile, int sheetIndex) throws Exception;
}
