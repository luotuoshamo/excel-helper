package cn.topicstudy.jexcel.util;

import junit.framework.TestCase;

import java.io.File;
import java.io.IOException;

public class WorkbookUtilTest extends TestCase {
    private File xlsFile = null;
    private File xlsxFile = null;

    public void setUp() throws Exception {
        super.setUp();
        xlsFile = new File("src/test/resources/1.xls");
        xlsxFile = new File("src/test/resources/1.xlsx");
    }

    public void tearDown() throws Exception {
    }

    public void testInitWorkbook() {
        // see testSheetAt
    }

    public void testSheetAt() throws IOException {
//        Sheet sheetXls = WorkbookUtil.sheetAt(xlsFile, ExcelTypeEnum.XLS, 0);
//        Assert.assertEquals("Sheet1", sheetXls.getSheetName());
//
//        Sheet sheetXlsx = WorkbookUtil.sheetAt(xlsxFile, 0);
//        Assert.assertEquals("Sheet1", sheetXlsx.getSheetName());
    }

    public void testTotalSheetCount() throws IOException {

    }
}