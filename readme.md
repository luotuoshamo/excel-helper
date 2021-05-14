## 概述
* 该框架用于解析和创建excel（xls、xlsx）
* 该项目只依赖Apache POI 4.1.2和JDK 8
* POI官方文档 `http://poi.apache.org/components/spreadsheet/index.html`

## API
* 解析excel

  ```java
  @Test
  public void parse() throws Exception {
  	File excelFile = new File("src\\main\\resources\\1.xls");
  	MySheet mySheet = Excel.parse(excelFile, 0);
  	System.out.println(mySheet);
  }
  ```

  ```
  MySheet{headList=[ID, 名称, 数量, 日期], dataList=[[1, 西红柿, 100, 1/2/20], [2, 苹果, 25, 5/15/21], [3]], dataMapList=[{数量=100, 日期=1/2/20, 名称=西红柿, ID=1}, {数量=25, 日期=5/15/21, 名称=苹果, ID=2}, {ID=3}]}
  ```

  ![image-20210514220640973](readme.assets\image-20210514220640973.png)

* 创建excel

  ```java
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
  
          FileOutputStream fos = new FileOutputStream("employee.xlsx");
          workbook.write(fos);
  ```
  
  ![image-20210514220742432](readme.assets\image-20210514220742432.png)
  
  