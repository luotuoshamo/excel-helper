package com.wjh;


import com.wjh.creater.DefaultExcelCreater;
import com.wjh.creater.ExcelCreater;
import com.wjh.entity.MySheet;
import com.wjh.parser.DefaultExcelParser;
import com.wjh.parser.ExcelParser;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;

/**
 * 入口
 */
public class Excel {
    private Excel() {
    }

    private static ExcelParser excelParser = new DefaultExcelParser();
    private static ExcelCreater excelCreater = new DefaultExcelCreater() {
    };

    public static MySheet parse(File excelFile, int sheetIndex) throws Exception {
        return excelParser.parse(excelFile, sheetIndex);
    }

    public static Workbook create(MySheet mySheet) throws Exception {
        return excelCreater.create(mySheet);
    }

}
