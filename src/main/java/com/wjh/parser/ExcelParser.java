package com.wjh.parser;

import com.wjh.entity.MySheet;
import com.wjh.enums.ExcelTypeEnum;

import java.io.File;
import java.io.InputStream;

public interface ExcelParser {
    MySheet parse(File excelFile, int sheetIndex) throws Exception;

    MySheet parse(InputStream excelIs, ExcelTypeEnum excelType, int sheetIndex) throws Exception;
}
