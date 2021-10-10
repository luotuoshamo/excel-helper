



# 介绍

jexcel用于解析和创建excel（包括xls和xlsx），基于Apache POI 4.1.2（`https://poi.apache.org/`），JDK基于1.8。操作excel时比较复杂的是处理各种数据类型，可以将所有数据都用String类型处理。

xls是老版excel（excel87-2003）,xlsx是新版excel（excel2007+）

# 依赖

| 依赖       | 版本  | Maven自动下载,使用使无需手动添加 |
| ---------- | ----- | -------------------------------- |
| JDK        | 1.8   | N                                |
| Apache POI | 4.1.2 | Y                                |

# 使用

* 在pom.xml中加入：

```xml
<repositories>
    <repository>
        <id>jexcel</id>
        <name>GitHub OWNER Apache Maven Packages</name>
        <url>https://topicstudy.github.io/jexcel/maven-repo/</url>
    </repository>
</repositories>
<dependencies>
    <dependency>
        <groupId>com.wjh</groupId>
        <artifactId>jexcel</artifactId>
        <version>1.0.0</version>
    </dependency>
</dependencies>
```

> 加入上面的配置后`mvn install`一下；如果还是爆红需要重启IDEA
>
> 如果`mvn install`失败过，请删掉本地maven仓库中安装失败的项目，在执行`mvn install`

* 示例-解析excel

  ```java
  public void testParse() throws IOException {
      File xlsFile = new File("src/test/resources/1.xls")
          MyExcel me = ExcelUtil.parse(xlsFile);
      System.out.println(me.getMySheets());
      Assert.assertEquals(3, me.getMySheets().size());
  }
  ```

* 示例-创建excel

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

  # 联系我

  | 微信   | topicstudy                    |
  | ------ | ----------------------------- |
  | Gitee  | https://gitee.com/topicstudy  |
  | Github | https://github.com/topicstudy |

  