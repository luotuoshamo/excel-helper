package com.wjh.util;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;

/**
 * @Author: 专题学习大师 topicstudy.cn 2021/10/10
 */
public class CellUtil {
    /**
     * 忽略单元格中内容的类型，全当作string
     * @return "", if cell is null
     */
    public static String getCellValueAsString(Cell cell) {
        return new DataFormatter().formatCellValue(cell);
    }
}
