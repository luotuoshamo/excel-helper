package cn.topicstudy.jexcel.creator;

import org.apache.poi.ss.usermodel.Workbook;

import java.io.IOException;

public interface ExcelCreator {
    Workbook create(  ) throws IOException;
}
