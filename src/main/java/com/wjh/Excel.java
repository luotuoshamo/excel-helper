package com.wjh;


import com.wjh.creater.DefaultExcelCreater;
import com.wjh.creater.ExcelCreater;
import com.wjh.entity.MySheet;
import com.wjh.enums.ExcelTypeEnum;
import com.wjh.parser.DefaultExcelParser;
import com.wjh.parser.ExcelParser;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.InputStream;
import java.util.List;

/**
 * 入口
 */
public class Excel {
    private Excel() {
    }

    private static ExcelParser excelParser = new DefaultExcelParser();
    private static ExcelCreater excelCreater = new DefaultExcelCreater();

    public static MySheet parse(File excelFile, int sheetIndex) throws Exception {
        return excelParser.parse(excelFile, sheetIndex);
    }

    public static MySheet parse(InputStream excelIs, ExcelTypeEnum excelType, int sheetIndex) throws Exception {
        return excelParser.parse(excelIs, excelType, sheetIndex);
    }

    public static Workbook create(MySheet mySheet) throws Exception {
        return excelCreater.create(mySheet);
    }

    public static Workbook create(List<MySheet> mySheetList) throws Exception {
        return excelCreater.create(mySheetList);
    }

    public static Workbook create(ExcelTypeEnum excelType, MySheet mySheet) throws Exception {
        return excelCreater.create(excelType, mySheet);
    }

    public static Workbook create(ExcelTypeEnum excelType, List<MySheet> mySheetList) throws Exception {
        return excelCreater.create(excelType, mySheetList);
    }

}
