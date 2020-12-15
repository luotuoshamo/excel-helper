package com.wjh;

import com.wjh.domain.SheetHeader;
import com.wjh.enums.ExcelTypeEnum;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import java.io.FileOutputStream;
import java.util.*;

public class WriteExcelTest {
    @Test
    public void f() throws Exception {
        SheetHeader sheetHeader = new SheetHeader();
        sheetHeader.add("姓名", "name");
        sheetHeader.add("性别", "gender");

        List<Map<String, String>> maps = new ArrayList<>();
        Map<String, String> map1 = new HashMap();
        Map<String, String> map2 = new HashMap();

        map1.put("name", "wwjh");
        map1.put("gender", "男");

        map2.put("name", "jik");
        map2.put("gender", "女");

        maps.add(map1);
        maps.add(map2);

        Workbook workbook = ExcelUtil.createExcel(ExcelTypeEnum.XLSX);
        ExcelUtil.addSheet(workbook, "shhet1", sheetHeader, maps);
        workbook.write(new FileOutputStream("D:/tmp/t2.xlsx"));
    }
}
