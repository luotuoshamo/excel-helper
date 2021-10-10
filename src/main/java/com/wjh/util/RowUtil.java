package com.wjh.util;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: 专题学习大师 topicstudy.cn 2021/10/10
 */
public class RowUtil {
    /**
     * 将row转成List<String>
     */
    public static List<String> rowToStringList(Row row) {
        int cellCount = getRowCellCount(row);// 该row有几个cell
        if (cellCount == 0) return null;

        List<String> cellValueList = new ArrayList(cellCount);
        for (int i = 0; i < cellCount; i++) {
            Cell cell = row.getCell(i);
            String cellValue = CellUtil.getCellValueAsString(cell);
            cellValueList.add(cellValue);
        }
        return cellValueList;
    }

    /**
     * 获取row中cell的个数
     * 注意：sheet.getLastRowNum(从0开始)和 row.getLastCellNum(从1开始)计数规则不同，
     * 这可看成是poi的不足
     */
    public static int getRowCellCount(Row row) {
        return row == null ? 0 : row.getLastCellNum();
    }
}
