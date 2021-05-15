



# 概述

* 该工具用于解析和创建excel（xls、xlsx）

* 该项目只依赖Apache POI 4.1.2和JDK 8

* POI官方文档 `http://poi.apache.org/components/spreadsheet/index.html`

# 使用
1. `git clone https://github.com/luotuoshamo/excel-helper.git`
2. `mvn package`
3. 生成的jar在这里 `excel-helper\target\excel-helper-1.0.0.jar`

# API

## 解析excel

`MySheet mySheet = Excel.parse(File excelFile, int sheetIndex);`

## 创建excel

* 创建xlsx(只有1个sheet)
   `Workbook create(MySheet mySheet)`
   
*  创建xlsx（有多个sheet）
   `Workbook create(List<MySheet> mySheetList)`
   
*  创建xlsx或xls(只有1个sheet)
   `Workbook create(ExcelTypeEnum excelTypeEnum, MySheet mySheet) `
   
*  创建xlsx或xls(只有多个sheet)
   `Workbook create(ExcelTypeEnum excelTypeEnum, List<MySheet> mySheetList)`
   
   > Workbook : org.apache.poi.ss.usermodel.Workbook
   >
   > MySheet ： com.wjh.entity.MySheet
   >
   > ExcelTypeEnum ：com.wjh.enums.ExcelTypeEnum

# 例子

## 解析excel
代码：

```java
@Test
public void parse() throws Exception {
	File excelFile = new File("src\\main\\resources\\1.xls");
	MySheet mySheet = Excel.parse(excelFile, 0);
	System.out.println(mySheet);
}
```
输出：
```
MySheet{headList=[ID, 名称, 数量, 日期], dataList=[[1, 西红柿, 100, 1/2/20], [2, 苹果, 25, 5/15/21], [3]], dataMapList=[{数量=100, 日期=1/2/20, 名称=西红柿, ID=1}, {数量=25, 日期=5/15/21, 名称=苹果, ID=2}, {ID=3}]}
```
解析的excel:
![image-20210514220640973](readme.assets\image-20210514220640973.png)

## 创建excel

创建有2个sheet的xls

代码：
```java
@Test
public void create() throws Exception {
	List<String> headList = Arrays.asList(new String[]{"姓名", "生日", "工资"});
	List<List<String>> dataList = new ArrayList();
	List<String> p1 = Arrays.asList(new String[]{"张三", "1999-1-1", "3000"});
	List<String> p2 = Arrays.asList(new String[]{"李四", "1982-12-23", "5000"});
	List<String> p3 = Arrays.asList(new String[]{"李白", "2000-6-13", "9865"});
	dataList.add(p1);
	dataList.add(p2);
	dataList.add(p3);

	List<MySheet> mySheetList = new ArrayList();
	mySheetList.add(new MySheet("员工信息表-202001", headList, dataList));
	mySheetList.add(new MySheet("员工信息表-202004", headList, dataList));
	Workbook workbook = Excel.create(ExcelTypeEnum.XLS, mySheetList);

	FileOutputStream fos = new FileOutputStream("src\\main\\resources\\tmp\\employee.xls");
	workbook.write(fos);
}
```

创建的excel：

![image-20210515203003060](\readme.assets\image-20210515203003060.png)