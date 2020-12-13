package com.wjh.util;

public class RowUtil {
    /**
     * 该行有几个单元格
     * 如果该行为空 return 0
     */
    public static int rowCellCount(org.apache.poi.ss.usermodel.Row row) {
        if (row == null) return 0;
        return row.getLastCellNum();//有1个单元格则返回1
    }

    public static boolean isBlankRow(org.apache.poi.ss.usermodel.Row row) {
        return rowCellCount(row) == 0;
    }
}
