package com.wjh.parser;

import com.wjh.domain.SheetHeader;
import com.wjh.util.Constant;
import com.wjh.util.MapUtil;
import com.wjh.util.RowUtil;
import com.wjh.util.SheetUtil;
import org.apache.poi.ss.usermodel.*;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 解析Excel
 */
public class ExcelParser {
    private ExcelParser() {
    }

    private static ExcelParser parser = new ExcelParser();

    public static ExcelParser getParser() {
        return parser;
    }

    /**
     * 将sheet解析成List<Map<String,String>
     */
    public List<Map<String, String>> parseSheetToMapList(
            InputStream excelInputStream,
            int sheetIndex,
            SheetHeader sheetHeader) throws Exception {
        Sheet sheet = getSheet(excelInputStream, sheetIndex);
        if (SheetUtil.isBlankSheet(sheet)) {
            throw new Exception("parseSheetToMapList时传入的excel为空");
        }

        // 将sheet中的数据解析成List<Map<String, String>>
        List<Map<String, String>> sheetDataList = new ArrayList<>();
        int totalRowCount = SheetUtil.totalRowCount(sheet);
        for (int i = 1; i < totalRowCount; i++) {
            Row row = sheet.getRow(i);
            Map<String, String> rowMap = new HashMap<>();
            for (int j = 0; j < RowUtil.rowCellCount(row); j++) {
                String value = getStringValue(row.getCell(j));
                String englishName = sheetHeader.getEnglishName(j);
                rowMap.put(englishName, value);
            }
            sheetDataList.add(rowMap);
        }
        return sheetDataList;
    }

    /**
     * 获取从excel中获取sheet
     */
    private Sheet getSheet(InputStream excelInputStream, int sheetIndex) throws Exception {
        /*
         * WorkbookFactory.create(File f)会判断f是否存在 不存在则报错
         * 所以不使用create(File f)这个构造器
         * 发现file的name是test.xls则file.exists()返回true（可能是JDK8的BUG，已反馈Oracle）
         *
         * HSSF － 提供读写Microsoft Excel XLS格式档案的功能
         * XSSF － 提供读写Microsoft Excel OOXML XLSX格式档案的功能（我们使用）
         */
        Workbook workbook = WorkbookFactory.create(excelInputStream);
        return workbook.getSheetAt(sheetIndex);
    }

    /**
     * 将单元格数据转成String返回
     * 单元格的数据不管是什么类型都转成String
     */
    private String getStringValue(Cell cell) throws Exception {
        CellType cellTypeEnum = cell.getCellTypeEnum();
        if (CellType.STRING.equals(cellTypeEnum)) {
            return cell.getStringCellValue();
        } else if (CellType.NUMERIC.equals(cellTypeEnum)) {
            return String.valueOf(cell.getNumericCellValue());
        } else {
            throw new Exception("单元格的数据类型是【" + cellTypeEnum
                    + "】，现在还不支持，可将详情发送至邮箱："
                    + Constant.MY_EMAIL);
        }
    }

    /**
     * 将sheet解析成List<Bean>例如List<User> User是实体类
     */
    public <T> List<T> parseSheetToBeanList(InputStream excelInputStream, int sheetIndex, SheetHeader sheetHeader, Class<T> beanClass) throws Exception {
        List<T> beanList = new ArrayList<>();

        List<Map<String, String>> mapList = parseSheetToMapList(excelInputStream, sheetIndex, sheetHeader);
        for (Map<String, String> map : mapList) {
            T t = MapUtil.mapToBean(map, beanClass);
            beanList.add(t);
        }
        return beanList;
    }
}
