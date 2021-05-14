package com.wjh.parser;

import com.wjh.entity.MySheet;

import java.io.File;

public interface ExcelParser {
    MySheet parse(File excelFile, int sheetIndex) throws Exception;
}
