package com.wjh;

import com.wjh.domain.SheetHeader;
import com.wjh.domain.User;
import com.wjh.enums.ExcelTypeEnum;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;

import java.io.FileOutputStream;
import java.util.*;

public class WriteExcelTest {
    @Test
    public void testWriteExcel() throws Exception {
        SheetHeader sheetHeader = new SheetHeader();
        sheetHeader.add("姓名", "name");
        sheetHeader.add("性别", "gender");

        User user1= new User();
        user1.setName("王jh");
        user1.setGender("m");
        user1.setBirthday(new Date());

        User user2 = new User();
        user2.setName("张boss");


        List<User> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);

        Workbook workbook = ExcelUtil.createExcel(ExcelTypeEnum.XLSX);
        ExcelUtil.addSheet(workbook,"sht1",sheetHeader,users);
        ExcelUtil.addSheet(workbook,"sht2",sheetHeader,users);

        workbook.write(new FileOutputStream("D:/tmp/t2.xlsx"));
    }
}
