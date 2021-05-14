package com.wjh;

import com.wjh.entity.MySheet;
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
        File excelFile = new File("src\\main\\resources\\1.xls");
        MySheet mySheet = Excel.parse(excelFile, 0);
        System.out.println(mySheet);
    }

    @Test
    public void create() throws Exception {
        List<String> headList = Arrays.asList(new String[]{"姓名", "性别", "生日", "工资"});

        List<String> p1 = Arrays.asList(new String[]{"张三", "男", "1999-1-1", "3000"});
        List<String> p2 = Arrays.asList(new String[]{"李四", "女", "1982-12-23", "5000"});
        List<String> p3 = Arrays.asList(new String[]{"李白", "男", "2000-6-13", "9865"});
        List<List<String>> dataList = new ArrayList();
        dataList.add(p1);
        dataList.add(p2);
        dataList.add(p3);

        MySheet mySheet = new MySheet(headList, dataList);
        mySheet.setName("员工信息表-202001");
        Workbook workbook = Excel.create(mySheet);

        FileOutputStream fos = new FileOutputStream("tmp/employee.xlsx");
        workbook.write(fos);
    }
}