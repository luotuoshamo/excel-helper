package com.wjh;

import com.wjh.entity.MySheet;
import org.junit.Test;

import java.io.File;

public class ExcelTest {

    @Test
    public void readSheet() throws Exception {
        File excelFile = new File("src\\main\\resources\\1.xlsx");
        MySheet mySheet = Excel.readSheet(excelFile, 0);
        System.out.println(mySheet);
    }
}