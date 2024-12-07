package cn.topicstudy.jexcel;

import cn.topicstudy.jexcel.entity.MyExcel;
import cn.topicstudy.jexcel.entity.MySheet;
import cn.topicstudy.jexcel.enums.ExcelTypeEnum;
import junit.framework.TestCase;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Assert;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ExcelUtilTest extends TestCase {
    private File xlsFile = null;
    private File xlsxFile = null;

    public void setUp() throws Exception {
        super.setUp();
        xlsFile = new File("src/test/resources/1.xls");
        xlsxFile = new File("src/test/resources/1.xlsx");
    }

    public void testParse() throws IOException {
        MyExcel me = ExcelUtil.parse(xlsFile);
        System.out.println(me.getMySheets());
        Assert.assertEquals(3, me.getMySheets().size());
    }

    public void testGetExcelType() {
        // see testParse
    }

    public void testCreate() throws IOException {
        MyExcel myExcel = new MyExcel();
        myExcel.setName("员工信息表(create)");
        myExcel.setExcelType(ExcelTypeEnum.XLS);
        List<MySheet> mySheets = new ArrayList();
        myExcel.setMySheets(mySheets);

        // 第一个sheet
        List<String> headList = Arrays.asList(new String[]{"姓名", "生日", "工资"});
        List<List<String>> dataList = new ArrayList();
        dataList.add(Arrays.asList(new String[]{"张三", "1999-1-1", "13000"}));
        dataList.add(Arrays.asList(new String[]{"李四", "1982-12-23", "21000"}));
        dataList.add(Arrays.asList(new String[]{"李白", "2000-6-13", "9600"}));
        dataList.add(Arrays.asList(new String[]{"金海洋", "2000-6-13", "8800"}));
        mySheets.add(new MySheet("员工信息表-2020", headList, dataList));

        // 第二个sheet（为了测试方便，假设这两个sheet内容一样）
        mySheets.add(new MySheet("员工信息表-2021", headList, dataList));

        // 创建excel并存到外存
        Workbook workbook = ExcelUtil.create(myExcel);
        FileOutputStream fos = new FileOutputStream(String.format("src/test/resources/%s.xls", myExcel.getName()));
        workbook.write(fos);
        if (fos != null) fos.close();
    }
}