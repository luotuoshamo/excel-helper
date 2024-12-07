package cn.topicstudy.jexcel.util;

import cn.topicstudy.jexcel.enums.ExcelTypeEnum;
import junit.framework.TestCase;
import org.apache.poi.ss.usermodel.Sheet;
import org.junit.Assert;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

public class RowUtilTest extends TestCase {
    private File xlsFile = null;
    private File xlsxFile = null;
    Sheet xlsSheet0 = null;

    public void setUp() throws Exception {
        super.setUp();
        xlsFile = new File("src/test/resources/1.xls");
        xlsxFile = new File("src/test/resources/1.xlsx");
        xlsSheet0 = WorkbookUtil.sheetAt(WorkbookUtil.initWorkbook(new FileInputStream(xlsFile), ExcelTypeEnum.XLS), 0);
    }

    public void tearDown() throws Exception {
    }

    public void testRowToStringList() throws IOException {
        List<String> list = RowUtil.rowToStringList(xlsSheet0.getRow(0));
        Assert.assertEquals(4, list.size());
        Assert.assertEquals("名称", list.get(1));
    }

    public void testGetRowCellCount() {
        int count = RowUtil.getRowCellCount(xlsSheet0.getRow(0));
        Assert.assertEquals(4, count);
    }
}