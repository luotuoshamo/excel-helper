package cn.topicstudy.jexcel.util;

import org.apache.poi.ss.usermodel.Sheet;


/**
 * @Author: 专题学习大师 topicstudy.cn 2021/10/10
 */
public class SheetUtil {
    /**
     * 获取sheet的总行数，包括标题
     *
     * @return 返回最后一个非空行的行数，例如最后一个非空行是第3行，则返回3
     */
    public static int getSheetRowCount(Sheet sheet) {
        return sheet == null ? 0 : sheet.getLastRowNum() + 1;
    }
}
