package com.wjh;


import com.wjh.entity.MySheet;
import com.wjh.reader.DefaultExcelReader;
import com.wjh.reader.ExcelReader;

import java.io.File;

/**
 * 入口
 */
public class Excel {
    private Excel() {
    }

    private static ExcelReader excelReader = new DefaultExcelReader();

    public static MySheet readSheet(File excelFile,  int sheetIndex) throws Exception {
        return excelReader.readSheet(excelFile,sheetIndex);
    }

}
