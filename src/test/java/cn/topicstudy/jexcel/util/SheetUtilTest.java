package cn.topicstudy.jexcel.util;

import cn.topicstudy.jexcel.enums.ExcelTypeEnum;
import junit.framework.TestCase;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Assert;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class SheetUtilTest extends TestCase {
    private Workbook wbXls;
    private Workbook wbXlsx;

    public void setUp() throws Exception {
        super.setUp();
        File xlsFile = new File("src/test/resources/1.xls");
       File xlsxFile = new File("src/test/resources/1.xlsx");
       wbXls = WorkbookUtil.initWorkbook(new FileInputStream(xlsFile), ExcelTypeEnum.XLS);
       wbXlsx = WorkbookUtil.initWorkbook(new FileInputStream(xlsxFile), ExcelTypeEnum.XLSX);
    }

    public void tearDown() throws Exception {
    }

    public void testGetSheetRowCount() throws IOException {
        Sheet sheetXlsx0 = WorkbookUtil.sheetAt(wbXls, 0);
        Assert.assertEquals(4, SheetUtil.getSheetRowCount(sheetXlsx0));

        Sheet sheetXlsx1 = WorkbookUtil.sheetAt(wbXlsx, 1);
        Assert.assertEquals(3, SheetUtil.getSheetRowCount(sheetXlsx1));

        Sheet sheetXlsx2 = WorkbookUtil.sheetAt(wbXlsx, 2);
        Assert.assertEquals(0, SheetUtil.getSheetRowCount(sheetXlsx2));
    }
}