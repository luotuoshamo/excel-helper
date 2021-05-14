package com.wjh.creater;

import com.wjh.entity.MySheet;
import com.wjh.enums.ExcelTypeEnum;
import org.apache.poi.ss.usermodel.Workbook;

import java.util.List;

public interface ExcelCreater {
    Workbook create(ExcelTypeEnum excelTypeEnum, List<MySheet> mySheetList);

    Workbook create(List<MySheet> mySheetList);

    Workbook create(ExcelTypeEnum excelTypeEnum, MySheet mySheet) throws Exception;

    Workbook create(MySheet mySheet) throws Exception;
}
