## 概述
* 该框架用于读写excel（xls、xlsx）
* 基于Apache POI

## API
* 读excel
```java
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
    for (User user : users) {
        System.out.println(user);
    }
}
```
* 创建excel
```java

```
