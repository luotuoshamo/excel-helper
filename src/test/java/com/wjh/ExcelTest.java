package com.wjh;

import com.wjh.entity.MySheet;
import com.wjh.enums.ExcelTypeEnum;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ExcelTest {

    @Test
    public void parse() throws Exception {
        File excelFile = new File("src\\main\\resources\\tmp\\1.xls");
        MySheet mySheet = Excel.parse(excelFile, 0);
        System.out.println(mySheet);
    }


    /**
     * 创建有2个sheet的xls
     */
    @Test
    public void create() throws Exception {
        List<String> headList = Arrays.asList(new String[]{"姓名", "生日", "工资"});
        List<List<String>> dataList = new ArrayList();
        List<String> p1 = Arrays.asList(new String[]{"张三", "1999-1-1", "3000"});
        List<String> p2 = Arrays.asList(new String[]{"李四", "1982-12-23", "5000"});
        List<String> p3 = Arrays.asList(new String[]{"李白", "2000-6-13", "9865"});
        dataList.add(p1);
        dataList.add(p2);
        dataList.add(p3);

        List<MySheet> mySheetList = new ArrayList();
        mySheetList.add(new MySheet("员工信息表-202001", headList, dataList));
        mySheetList.add(new MySheet("员工信息表-202004", headList, dataList));
        Workbook workbook = Excel.create(ExcelTypeEnum.XLS, mySheetList);

        FileOutputStream fos = new FileOutputStream("src\\main\\resources\\tmp\\employee.xls");
        workbook.write(fos);
    }
}