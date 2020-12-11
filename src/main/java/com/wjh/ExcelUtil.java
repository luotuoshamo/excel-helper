package com.wjh;

import com.wjh.domain.SheetHeader;
import com.wjh.enums.ExcelTypeEnum;
import com.wjh.parser.XlsParser;

import java.util.List;

/**
 * 解析一个sheet
 */
public class ExcelUtil {
    public static List parseSheet(String excelPath, int sheetIndex, SheetHeader sheetHeader) throws Exception {
        List list = null;
        if (isXls(excelPath)) {
            list = XlsParser.parseSheet(excelPath, sheetIndex, sheetHeader);
        }
        return list;
    }

    /**
     * 判断excel是否是xls
     */
    public static boolean isXls(String excelPath) throws Exception {
        return excelPath.endsWith(".xls") || excelPath.endsWith(".XLS");
    }
}
