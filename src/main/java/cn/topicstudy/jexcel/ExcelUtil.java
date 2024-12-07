package cn.topicstudy.jexcel;

import cn.topicstudy.jexcel.creator.DefaultExcelCreator;
import cn.topicstudy.jexcel.creator.ExcelCreator;
import cn.topicstudy.jexcel.entity.MyExcel;
import cn.topicstudy.jexcel.entity.MySheet;
import cn.topicstudy.jexcel.enums.ExcelTypeEnum;
import cn.topicstudy.jexcel.parser.DefaultExcelParser;
import cn.topicstudy.jexcel.parser.ExcelParser;
import org.apache.poi.ss.usermodel.*;

import java.io.*;
import java.util.List;

public class ExcelUtil {

    /**
     * Only parse one
     */
    /**
     * 解析excel
     *
     * @param excelIs   excel文件
     * @param excelType excel类型
     * @return
     */
    public static MyExcel parse(InputStream excelIs, ExcelTypeEnum excelType) {
        ExcelParser excelParser = null;
        try {
            excelParser = new DefaultExcelParser(excelIs, excelType);
            if (excelIs == null) throw new RuntimeException("excelIs不可为空");
            if (excelType == null) throw new RuntimeException("excelType不可为空");
            return excelParser.parse();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> List<T> parse(InputStream excelIs, ExcelTypeEnum excelType, Class<T> cls) {
        if (cls == null) {
            return null;
        }

        MyExcel excel = parse(excelIs, excelType);
        try {
            T obj = cls.newInstance();
            List<MySheet> mySheets = excel.getMySheets();
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return null;
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
