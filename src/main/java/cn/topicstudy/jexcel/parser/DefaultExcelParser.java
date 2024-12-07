package cn.topicstudy.jexcel.parser;

import cn.topicstudy.jexcel.entity.MyExcel;
import cn.topicstudy.jexcel.entity.MySheet;
import cn.topicstudy.jexcel.enums.ExcelTypeEnum;
import cn.topicstudy.jexcel.util.RowUtil;
import cn.topicstudy.jexcel.util.SheetUtil;
import cn.topicstudy.jexcel.util.WorkbookUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class DefaultExcelParser implements ExcelParser {
    private Workbook workbook;

    public DefaultExcelParser(InputStream excelIs, ExcelTypeEnum excelType) throws IOException {
        this.workbook = WorkbookUtil.initWorkbook(excelIs, excelType);
        if (this.workbook == null) throw new RuntimeException("workbook初始化失败");
    }

    @Override
    public MyExcel parse() throws IOException {
        MyExcel myExcel = new MyExcel();
        int totalSheetCount = WorkbookUtil.totalSheetCount(workbook);
        for (int idx = 0; idx < totalSheetCount; idx++) {
            Sheet sheet = WorkbookUtil.sheetAt(workbook, idx);
            int sheetRowCount = SheetUtil.getSheetRowCount(sheet);
            // sheet是空白
            if (sheetRowCount == 0) {
                myExcel.getMySheets().add(null);
                continue;
            }
            // 解析sheet
            List<List<String>> rowList = new ArrayList(sheetRowCount);
            for (int i = 0; i < sheetRowCount; i++) {
                Row row = sheet.getRow(i);
                List<String> rowCellList = RowUtil.rowToStringList(row);
                rowList.add(rowCellList);
            }
            MySheet mySheet = new MySheet(sheet.getSheetName(), rowList);
            myExcel.getMySheets().add(mySheet);
        }

        return myExcel;
    }
}
