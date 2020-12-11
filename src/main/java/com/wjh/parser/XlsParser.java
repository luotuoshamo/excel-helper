package com.wjh.parser;

import com.wjh.domain.SheetHeader;
import com.wjh.util.RowUtil;
import com.wjh.util.SheetUtil;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 解析xls
 */
public class XlsParser {
    public static List parseSheet(String xlsPath, int sheetIndex, SheetHeader sheetHeader) throws Exception {
        HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(xlsPath));
        HSSFSheet sheet = workbook.getSheetAt(sheetIndex);

        if (SheetUtil.isBlankSheet(sheet)) {
            throw new Exception("excel为空");
        }

        // 解析sheet数据
        List<Map<String, String>> sheetDataList = new ArrayList<>();
        int totalRowCount = SheetUtil.totalRowCount(sheet);
        for (int i = 1; i < totalRowCount; i++) {
            HSSFRow row = sheet.getRow(i);

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
     * 将单元格数据转成字符串返回
     */
    private static String getStringValue(HSSFCell cell) throws Exception {
        CellType cellTypeEnum = cell.getCellTypeEnum();
        if (CellType.STRING.equals(cellTypeEnum)) {
            return cell.getStringCellValue();
        } else if (CellType.NUMERIC.equals(cellTypeEnum)) {
            return String.valueOf(cell.getNumericCellValue());
        } else {
            throw new Exception("单元格的数据类型是【" + cellTypeEnum + "】,现在还不支持");
        }
    }
}
