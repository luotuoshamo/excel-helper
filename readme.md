Jexcel is a framework for parsing and creating the excel, which based on Apache POI.
Xls is the older formater of excel (excel87-2003), however xlsx is the new version (excel2007+).


# Examples
```xml
  <dependency>
  <groupId>io.github.topicstudy</groupId>
  <artifactId>jexcel</artifactId>
  <version>RELEASE</version>
</dependency>
```

## Example of parsing an excel.

  ```java
  public void testParse() throws IOException {
      File xlsFile = new File("src/test/resources/1.xls")
          MyExcel me = ExcelUtil.parse(xlsFile);
      System.out.println(me.getMySheets());
  }
  ```
## Example of creating an excel.

  ```java
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
  ```

  