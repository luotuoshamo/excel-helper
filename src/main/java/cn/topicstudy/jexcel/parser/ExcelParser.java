package cn.topicstudy.jexcel.parser;

import cn.topicstudy.jexcel.entity.MyExcel;

import java.io.IOException;

public interface ExcelParser {
    MyExcel parse() throws IOException;
}
