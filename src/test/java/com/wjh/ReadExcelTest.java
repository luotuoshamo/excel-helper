package com.wjh;

import com.wjh.domain.SheetHeader;
import org.junit.Test;

import java.util.List;

public class ReadExcelTest {
    /**
     * HSSF － 提供读写Microsoft Excel XLS格式档案的功能
     * XSSF － 提供读写Microsoft Excel OOXML XLSX格式档案的功能（我们使用）
     */
    @Test
    public void testReadXls() throws Exception {
        SheetHeader sheetHeader = new SheetHeader();
        sheetHeader.add("姓名", "name", 0);
        sheetHeader.add("性别", "gender", 1);
        sheetHeader.add("身高", "height", 2);

        List list = ExcelUtil.parseSheet(
                "src/main/resources/test2.xls",
                0,
                sheetHeader
        );
        System.out.println(list);
    }


}
