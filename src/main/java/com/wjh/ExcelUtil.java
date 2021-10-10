package com.wjh;

import com.wjh.creator.DefaultExcelCreator;
import com.wjh.creator.ExcelCreator;
import com.wjh.entity.MyExcel;
import com.wjh.enums.ExcelTypeEnum;
import com.wjh.parser.DefaultExcelParser;
import com.wjh.parser.ExcelParser;
import org.apache.poi.ss.usermodel.*;

import java.io.*;

public class ExcelUtil {
    /**
     * 解析excel
     *
     * @param excelIs   excel文件
     * @param excelType excel类型
     * @return
     */
    public static MyExcel parse(InputStream excelIs, ExcelTypeEnum excelType) throws IOException {
        ExcelParser excelParser = new DefaultExcelParser(excelIs, excelType);
        if (excelIs == null) throw new RuntimeException("excelIs不可为空");
        if (excelType == null) throw new RuntimeException("excelType不可为空");
        return excelParser.parse();
    }

    /**
     * 解析excel
     */
    public static MyExcel parse(File excelFile, ExcelTypeEnum excelType) throws IOException {
        if (excelFile == null) throw new RuntimeException("excelFile不可为空");
        return parse(new FileInputStream(excelFile), excelType);
    }

    /**
     * 解析excel
     */
    public static MyExcel parse(File excelFile) throws IOException {
        if (excelFile == null) throw new RuntimeException("excelFile不可为空");
        return parse(excelFile, ExcelUtil.getExcelType(excelFile));
    }

    /**
     * 获取excel的类型
     */
    public static ExcelTypeEnum getExcelType(File excelFile) {
        if (excelFile == null) throw new RuntimeException("excelFile不可为空");
        String excelFileName = excelFile.getName().toLowerCase();
        ExcelTypeEnum excelType;
        if (excelFileName.endsWith(".xls")) excelType = ExcelTypeEnum.XLS;
        else if (excelFileName.endsWith(".xlsx")) excelType = ExcelTypeEnum.XLSX;
        else throw new RuntimeException("【" + excelFile.getName() + "】不是excel");
        return excelType;
    }

    /**
     * 创建excel
     */
    public static Workbook create(MyExcel myExcel) throws IOException {
        ExcelCreator excelCreator = new DefaultExcelCreator(myExcel);
        return excelCreator.create();
    }
}
