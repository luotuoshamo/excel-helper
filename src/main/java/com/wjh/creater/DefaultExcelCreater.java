package com.wjh.creater;

import com.wjh.entity.MySheet;
import com.wjh.enums.ExcelTypeEnum;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.ArrayList;
import java.util.List;

public class DefaultExcelCreater implements ExcelCreater {
    public Workbook create(ExcelTypeEnum excelType, MySheet mySheet) throws Exception {
        if (mySheet == null) throw new Exception("mySheet不可为null");

        Workbook excel = ExcelTypeEnum.XLS.equals(excelType) ? new HSSFWorkbook() : new XSSFWorkbook();
        String name = mySheet.getName();
        Sheet sheet = name == null ? excel.createSheet() : excel.createSheet(name);

        List<String> headList = mySheet.getHeadList();
        List<List<String>> dataList = mySheet.getDataList();
        List<List<String>> rowList = new ArrayList();
        rowList.add(headList);
        rowList.addAll(dataList);

        for (int i = 0; i < rowList.size(); i++) {
            List<String> rowValueList = rowList.get(i);
            Row row = sheet.createRow(i);
            for (int j = 0; j < rowValueList.size(); j++) {
                String cellValue = rowValueList.get(j);
                Cell cell = row.createCell(j);
                cell.setCellValue(cellValue);
            }
        }
        return excel;
    }


    @Override
    public Workbook create(MySheet mySheet) throws Exception {
        return create(ExcelTypeEnum.XLSX, mySheet);
    }

    @Override
    public Workbook create(ExcelTypeEnum excelTypeEnum, List<MySheet> mySheetList) {

        return null;
    }

    public Workbook create(List<MySheet> mySheetList) {
        return null;
    }
}
