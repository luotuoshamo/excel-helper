//package com.wjh;
//
//import com.wjh.domain.SheetHeader;
//import org.junit.Test;
//
//import java.io.File;
//import java.util.List;
//import java.util.Map;
//
//public class DwEmpTest {
//    @Test
//    public void loadDataToMySql() throws Exception {
//        SheetHeader sheetHeader = new SheetHeader();
//        sheetHeader.add("上级部门", "higher_dept", 0);
//        sheetHeader.add("部门名称", "dept", 1);
//        sheetHeader.add("姓名", "name", 2);
//        sheetHeader.add("在职状态", "state", 3);
//
//        List<Map<String, String>> list = ExcelUtil.parseSheet(
//                new File("src/main/resources/dwemp.xls"),
//                0,
//                sheetHeader
//        );
//
//        // load to mysql
//        Integer CONNECTION_MAX_COUNT = 2;
//        String DB_URL = "jdbc:mysql://127.0.0.1:3306/wjhdb?useSSL=false&serverTimezone=GMT%2B8";
//        String DB_USER = "root";
//        String DB_PWD = "1234";
//        JdbcUtil.initJdbcConfig(DB_URL, DB_USER, DB_PWD);
//
//        for (Map<String, String> emp : list) {
//            Integer update = JdbcUtil.update(
//                    "insert into dwemp(higher_dept,dept,name,state) values(?,?,?,?)",
//                    emp.get("higher_dept"),
//                    emp.get("dept"),
//                    emp.get("name"),
//                    emp.get("state")
//            );
//            System.out.println(update+"=="+emp);
//        }
//
//        System.out.println(list);
//    }
//}
