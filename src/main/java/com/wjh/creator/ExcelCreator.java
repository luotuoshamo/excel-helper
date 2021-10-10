package com.wjh.creator;

import com.wjh.entity.MyExcel;
import com.wjh.entity.MySheet;
import com.wjh.enums.ExcelTypeEnum;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.IOException;
import java.util.List;

public interface ExcelCreator {
    Workbook create(  ) throws IOException;
}
