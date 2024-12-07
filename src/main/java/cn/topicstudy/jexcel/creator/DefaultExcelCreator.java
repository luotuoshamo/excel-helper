package cn.topicstudy.jexcel.creator;

import cn.topicstudy.jexcel.entity.MyExcel;
import cn.topicstudy.jexcel.entity.MySheet;
import cn.topicstudy.jexcel.util.WorkbookUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DefaultExcelCreator implements ExcelCreator {
    private MyExcel myExcel;

    public DefaultExcelCreator(MyExcel myExcel) {
        this.myExcel = myExcel;
        if (this.myExcel == null) throw new RuntimeException("myExcel不可为null");
    }

    public Workbook create() throws IOException {
        Workbook wb = WorkbookUtil.initWorkbook(myExcel.getExcelType());
        List<MySheet> mySheets = myExcel.getMySheets();
        if (mySheets == null) return wb;
        for (MySheet mySheet : mySheets) {
            addSheetToExcel(wb, mySheet);
        }
        return wb;
    }

    private void addSheetToExcel(Workbook wb, MySheet mySheet) {
        if (wb == null) throw new RuntimeException("wb不可为空");
        String name = mySheet.getName();
        if (name == null) name = "";
        Sheet sheet = wb.createSheet(name);

        List<String> headList = mySheet.getHeadList();
        List<List<String>> dataList = mySheet.getDateList();
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
    }
}
