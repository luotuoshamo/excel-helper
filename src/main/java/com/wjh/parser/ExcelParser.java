package com.wjh.parser;

import com.wjh.entity.MyExcel;
import com.wjh.entity.MySheet;
import com.wjh.enums.ExcelTypeEnum;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public interface ExcelParser {
    MyExcel parse() throws IOException;
}
