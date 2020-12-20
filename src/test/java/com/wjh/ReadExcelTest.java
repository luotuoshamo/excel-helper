package com.wjh;

import com.wjh.domain.SheetHeader;
import com.wjh.domain.User;
import org.junit.Test;

import java.io.FileInputStream;
import java.util.List;

public class ReadExcelTest {

    @Test
    public void testReadXls() throws Exception {
        SheetHeader sheetHeader = new SheetHeader();
        sheetHeader.add("姓名", "name");
        sheetHeader.add("性别", "gender");
        sheetHeader.add("身高", "height");

        List<User> users = ExcelUtil.parseSheetToBeanList(
                new FileInputStream("src/main/resources/test/test.xls"),
                0,
                sheetHeader,
                User.class
        );
        for (User user : users)
            System.out.println(user);
    }


    @Test
    public void testReadXlsx() throws Exception {
        SheetHeader sheetHeader = new SheetHeader();
        sheetHeader.add("姓名", "name", 0);
        sheetHeader.add("性别", "gender", 1);
        sheetHeader.add("身高", "height", 2);

        List<User> users = ExcelUtil.parseSheetToBeanList(
                new FileInputStream("src/main/resources/test/test.xlsx"),
                0,
                sheetHeader,
                User.class
        );
        for (User user : users)
            System.out.println(user);
    }
}
