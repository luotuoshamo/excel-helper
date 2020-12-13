package com.wjh.util;

public class SheetUtil {
    /**
     * sheet的行数
     * 特殊情况1-断行
     * 第一行不空，第二行全空，第三行不空  return 3
     * 第一行空，第二行不空  return 2
     * <p>
     * 特殊情况2-空
     * sheet为null或sheet中无数据  return 0
     * <p>
     * 正常情况
     * sheet中有1行数据 return 1
     * sheet中有2行数据 return 2
     */
    public static int totalRowCount(org.apache.poi.ss.usermodel.Sheet sheet) {
        if (sheet == null) return 0;
        // 无数据返回0，有一行数据也返回0
        int lastRowNum = sheet.getLastRowNum();
        // sheet有0行、1行数据
        if (lastRowNum == 0) {
            if (sheet.getRow(0) == null) return 0;// sheet有0行数据
            else return 1;
        }
        return lastRowNum + 1;
    }

    /**
     * sheet为null或sheet中没数据 return false
     */
    public static boolean isBlankSheet(org.apache.poi.ss.usermodel.Sheet sheet) {
        return totalRowCount(sheet) == 0;
    }
}
